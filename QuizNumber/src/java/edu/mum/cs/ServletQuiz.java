package edu.mum.cs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(name = "ServletQuiz", urlPatterns = {"/quiz"})
public class ServletQuiz extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        int answer = 0;
        if (quiz == null)
            quiz = new Quiz();
        if(request.getParameter("answer") != null)
            answer = Integer.parseInt(request.getParameter("answer"));
        if (answer == quiz.getAnswer(quiz.getCurrentQuestion()))
            quiz.setScore(quiz.getScore() + 1);
        if (quiz.getQuestionNumbers().size() != 5)
            printQuiz(quiz, response);
        else {
            printFinalQuiz(quiz, response);
            quiz = null;
        }
        session.setAttribute("quiz", quiz);
    }

    private void printFinalQuiz(Quiz quiz, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.print("<h1>The Number Quiz</h1>");
        out.print("<p>Your current score is "+quiz.getScore()+".</p><br/>");
        out.print("<p>You have completed The Number Quiz, with a score "+quiz.getScore()+" out of "+quiz.getQuestionNumbers().size()+".</p>");
        out.close();
    }

    private void printQuiz(Quiz quiz, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.print(" <h1>The Number Quiz</h1>");
        out.print("<form action=\"/quiz\" method=\"post\">");
        out.print("<p>Your current score is "+quiz.getScore()+".<br/>");
        out.print("Guess the number in the sequence.<br/>");
        String question = generateQuestion(quiz);
        out.print(question);
        out.print("<br/>");
        out.print("Your answer: <input name=\"answer\"/>");
        out.print("</p>");
        out.print("<br/>");
        out.print("<input type=\"submit\" value=\"Submit\"/>");
        out.print("</form>");
        out.close();
    }

    private String generateQuestion(Quiz quiz){
        StringBuilder b = new StringBuilder();
        Random random;
        int rand;
        do {
            rand = new Random().nextInt(5);
        }while (quiz.getQuestionNumbers().contains(rand));
        quiz.setCurrentQuestion(rand);
        quiz.setQuestionNumbers(rand);
        String[] qus = quiz.getQuestion(rand);
        for (int i = 0; i < qus.length; i++) {
            b.append(qus[i]);
            if (i != qus.length - 1)
                b.append(" ,");
        }
        return b.toString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}