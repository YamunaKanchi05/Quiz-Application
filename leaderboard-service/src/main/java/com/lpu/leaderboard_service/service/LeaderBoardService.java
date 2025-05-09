package com.lpu.leaderboard_service.service;

import com.lpu.leaderboard_service.feign.QuizServiceClient;
import com.lpu.leaderboard_service.model.Response;
import com.lpu.leaderboard_service.model.ScoreEntry;
import com.lpu.leaderboard_service.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaderBoardService {

    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private QuizServiceClient quizServiceClient;

    // Add score to the leaderboard
    
    public ResponseEntity<ScoreEntry> submitQuizAndSaveScore(Integer quizId, Integer userId, List<Response> responses) {
        // Call quiz service to calculate score
        ResponseEntity<Integer> response = quizServiceClient.submitQuiz(responses);
        Integer score = response.getBody();

        // Save score to leaderboard
        ScoreEntry scoreEntry = new ScoreEntry();
        scoreEntry.setQuizId(quizId);
        scoreEntry.setUserId(userId);
        scoreEntry.setScore(score);
        scoreEntry.setDateTime(LocalDateTime.now());
      return   new ResponseEntity<>(scoreRepository.save(scoreEntry), HttpStatus.CREATED);
       
    }
  

    // Get top scores from the leaderboard
    public ResponseEntity<List<ScoreEntry>> getTopScores() {
        // Fetch the top scores (e.g., top 10 scores)
        List<ScoreEntry> topScores = scoreRepository.findTop10ByOrderByScoreDesc();
        // Return the top scores with HTTP 200 status
        return new ResponseEntity<>(topScores, HttpStatus.OK);
    }

    // Get scores for a specific user
    public ResponseEntity<List<ScoreEntry>> getUserScores(Integer userId) {
        // Fetch scores based on userId
        List<ScoreEntry> userScores = scoreRepository.findByUserIdOrderByDateTimeDesc(userId);
        // Return the user's scores with HTTP 200 status
        return new ResponseEntity<>(userScores, HttpStatus.OK);
    }
}
