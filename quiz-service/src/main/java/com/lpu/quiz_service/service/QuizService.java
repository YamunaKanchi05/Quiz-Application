package com.lpu.quiz_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lpu.quiz_service.dao.QuizDao;
import com.lpu.quiz_service.feign.PaymentClient;
import com.lpu.quiz_service.feign.QuizInterface;
import com.lpu.quiz_service.model.PaymentRequest;
import com.lpu.quiz_service.model.PaymentResponse;
import com.lpu.quiz_service.model.QuestionWrapper;
import com.lpu.quiz_service.model.Quiz;
import com.lpu.quiz_service.model.Response;

import org.springframework.http.HttpStatus;
@Service
public class QuizService {
    
    @Autowired
    QuizDao quizDao;
    @Autowired
    private PaymentClient paymentClient;
    @Autowired
    QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
		// TODO Auto-generated method stub
		List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
		
	}

	public ResponseEntity<?> getQuizQuestions(Integer id, boolean isPremium, Long amount) {
	    Optional<Quiz> optionalQuiz = quizDao.findById(id);
	    if (optionalQuiz.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
	    }

	    Quiz quiz = optionalQuiz.get();

	    if (isPremium) {
	        if (amount == null || amount < 5000) {
	            return ResponseEntity.badRequest().body("Amount must be at least ₹50 for premium quiz");
	        }

	        // Prepare payment request
	        PaymentRequest request = new PaymentRequest();
	        request.setQuizTitle(quiz.getTitle());
	        request.setAmount(amount);
	        request.setCurrency("inr");
	        request.setQuantity(1L);

	        // Call payment service
	        ResponseEntity<PaymentResponse> paymentResponse = paymentClient.checkout(request);
	        return ResponseEntity.ok(paymentResponse);
	    }

	    // If not premium, return quiz questions
	    List<Integer> questionIds = quiz.getQuestionIds();
	    ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
	    return questions;
	}


	public ResponseEntity<Integer> calculateResult(List<Response> responses) {
		// TODO Auto-generated method stub
		 ResponseEntity<Integer> score = quizInterface.getScore(responses);
	      return score;
	}
	

}
