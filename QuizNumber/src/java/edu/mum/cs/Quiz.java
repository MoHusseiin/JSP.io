package edu.mum.cs;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private int score;
    private int currentQuestion;

    private List<Integer> questionNumbers;

    public Quiz() {
        questionNumbers = new ArrayList<>();
        questionNumbers.add(0);
    }

    private String question [][] = {
        {"3", "1", "4", "1", "5"}, // pi
        {"1", "1", "2", "3", "5"}, // fib
        {"1", "4", "9", "16", "25"}, // squares
        {"2", "3", "5", "7", "11"}, // primes
        {"1", "2", "4", "8", "16"}  // power of 2
    };

    private int answer [] = { 9, 8, 36, 13, 32};

    public int getAnswer(int index) {
        if (index > answer.length - 1)
            return -1;
        return answer[index];
    }

    public String[] getQuestion(int index){
        if (index > question.length - 1)
            return null;
        return question[index];
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public void setQuestionNumbers(int questionNumbers) {
        this.questionNumbers.add(questionNumbers);
    }

    public List<Integer> getQuestionNumbers() {
        return questionNumbers;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "score=" + score +
                ", currentQuestion=" + currentQuestion +
                ", questionNumbers=" + questionNumbers +
                '}';
    }
}