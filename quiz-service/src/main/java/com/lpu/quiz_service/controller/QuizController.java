package com.lpu.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.quiz_service.model.QuestionWrapper;
import com.lpu.quiz_service.model.QuizDto;
import com.lpu.quiz_service.model.Response;
import com.lpu.quiz_service.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	 @Autowired
	    QuizService quizService;

	    @PostMapping("create")
	    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
	        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
	    }
	    @PostMapping("get/{id}")
	    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
	        return quizService.getQuizQuestions(id);
	    }

	    @PostMapping("submit")
	    public ResponseEntity<Integer> submitQuiz( @RequestBody List<Response> responses){
	        return quizService.calculateResult(responses);
	    }

}
