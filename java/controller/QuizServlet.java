package controller;

import entities.Choice;
import entities.Question;
import entities.User;
import entities.UserQuestionAnswer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.QuizDB;

/**
 * Programmers: Jhed Factolerin & Ramon Mercader<br>
 * Program: AdminController.java <br>
 * Date: December 2016 <br>
 * version 1.0
 */
@WebServlet(name = "QuizServlet", urlPatterns = {"/QuizServlet"})
public class QuizServlet extends HttpServlet
{

    /** QuizDB object* */
    private QuizDB quiz;
    /** ResultSet object* */
    private ResultSet results;
    /** User object* */
    private User user;
    /** Listens to buttons* */
    private String button;
    /** Message to be sent* */
    private String message;
    /** Listens to parameter* */
    private String param;
    /** Question object* */
    private Question question;
    /** Choice object* */
    private Choice choice;
    /** UserQuestionAnswer object* */
    private UserQuestionAnswer userAnswer;
    /** Index of the question* */
    private int questionIndex;
    /** Determines the category* */
    private int categoryID;
    /** ID of the user's choice* */
    private int choiceID;
    /** index of the quiz* */
    private int counter;
    ;
    /**Stores userID**/
    private String userID;
    /** Stores userName* */
    private String userName;
    /** Total number of question* */
    private int rows;
    /** Disables button* */
    private String disabled;
    /** Disables button* */
    private String finishDisabled;

    @Override
    public void init () throws ServletException
    {
        counter = 1;
        questionIndex = 1;
        quiz = new QuizDB();
        quiz.connect();
        user = new User();
        question = new Question();
        choice = new Choice();
        question = new Question();
        userAnswer = new UserQuestionAnswer();
        choiceID = 1;
        finishDisabled = "disabled";
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();;
        try {
            button = request.getParameter("button");
            param = request.getParameter("category");

            //Display next question
            if ("next".equals(button)) {
                if (request.getSession().getAttribute("userName") != null) {
                    //Saving the user's answer to the database
                    String answer = request.getParameter("option");
                    if ("A".equals(answer)) {
                        choiceID = 1 + ((counter - 1) * 4);
                        userAnswer.setChoiceID(choiceID);
                        userAnswer.setQuestionID(counter);
                        userAnswer.setUserID(userID);
                        quiz.isRight(choice, counter, 1, categoryID);
                        userAnswer.setIsRight(choice.getIsRight());
                        userAnswer.setCategoryID(categoryID);

                        if (quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.updateUserAnswer(userAnswer, Integer.parseInt(userID), counter, categoryID);
                        }
                        else if (!quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.registerUserAnswer(userAnswer);
                        }
                    }
                    else if ("B".equals(answer)) {
                        choiceID = 2 + ((questionIndex - 1) * 4);
                        userAnswer.setChoiceID(choiceID);
                        userAnswer.setQuestionID(counter);
                        userAnswer.setUserID(userID);
                        quiz.isRight(choice, counter, 2, categoryID);
                        userAnswer.setIsRight(choice.getIsRight());
                        userAnswer.setCategoryID(categoryID);
                        if (quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.updateUserAnswer(userAnswer, Integer.parseInt(userID), counter, categoryID);
                        }
                        else if (!quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.registerUserAnswer(userAnswer);
                        }

                    }
                    else if ("C".equals(answer)) {
                        choiceID = 3 + ((questionIndex - 1) * 4);
                        userAnswer.setChoiceID(choiceID);
                        userAnswer.setQuestionID(counter);
                        userAnswer.setUserID(userID);
                        quiz.isRight(choice, counter, 3, categoryID);
                        userAnswer.setIsRight(choice.getIsRight());
                        userAnswer.setCategoryID(categoryID);
                        if (quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.updateUserAnswer(userAnswer, Integer.parseInt(userID), counter, categoryID);
                        }
                        else if (!quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.registerUserAnswer(userAnswer);
                        }
                    }
                    else if ("D".equals(answer)) {
                        choiceID = 4 + ((questionIndex - 1) * 4);
                        userAnswer.setChoiceID(choiceID);
                        userAnswer.setQuestionID(counter);
                        userAnswer.setUserID(userID);
                        quiz.isRight(choice, counter, 4, categoryID);
                        userAnswer.setIsRight(choice.getIsRight());
                        userAnswer.setCategoryID(categoryID);
                        if (quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.updateUserAnswer(userAnswer, Integer.parseInt(userID), counter, categoryID);
                        }
                        else if (!quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.registerUserAnswer(userAnswer);
                        }
                    }

                    if (counter < rows) {
                        counter++;
                        if (counter == rows) {
                            disabled = "disabled";
                            finishDisabled = "";
                        }
                        request.setAttribute("fDisabled", finishDisabled);
                        request.setAttribute("disabled", disabled);
                        quiz.getQuiz(question, categoryID, counter);
                        request.setAttribute("question", counter + ". " + question.getQuestion());
                        quiz.getChoice(choice, categoryID, counter);
                        request.setAttribute("choice1", choice.getChoice());
                        quiz.nextChoice(choice);
                        request.setAttribute("choice2", choice.getChoice());
                        quiz.nextChoice(choice);
                        request.setAttribute("choice3", choice.getChoice());
                        quiz.nextChoice(choice);
                        request.setAttribute("choice4", choice.getChoice());
                        request.getRequestDispatcher("quiz.jsp").forward(request, response);
                    }
                }

                //Submit the quiz
            }
            else if ("submitQuiz".equals(button)) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                else {
                    String answer = request.getParameter("option");
                    if ("A".equals(answer)) {
                        choiceID = 1 + ((counter - 1) * 4);
                        userAnswer.setChoiceID(choiceID);
                        userAnswer.setQuestionID(counter);
                        userAnswer.setUserID(userID);
                        quiz.isRight(choice, counter, 1, categoryID);
                        userAnswer.setIsRight(choice.getIsRight());
                        userAnswer.setCategoryID(categoryID);

                        if (quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.updateUserAnswer(userAnswer, Integer.parseInt(userID), counter, categoryID);
                        }
                        else if (!quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.registerUserAnswer(userAnswer);
                        }
                    }
                    else if ("B".equals(answer)) {
                        choiceID = 2 + ((questionIndex - 1) * 4);
                        userAnswer.setChoiceID(choiceID);
                        userAnswer.setQuestionID(counter);
                        userAnswer.setUserID(userID);
                        quiz.isRight(choice, counter, 2, categoryID);
                        userAnswer.setIsRight(choice.getIsRight());
                        userAnswer.setCategoryID(categoryID);
                        if (quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.updateUserAnswer(userAnswer, Integer.parseInt(userID), counter, categoryID);
                        }
                        else if (!quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.registerUserAnswer(userAnswer);
                        }

                    }
                    else if ("C".equals(answer)) {;
                        choiceID = 3 + ((questionIndex - 1) * 4);
                        userAnswer.setChoiceID(choiceID);
                        userAnswer.setQuestionID(counter);
                        userAnswer.setUserID(userID);
                        quiz.isRight(choice, counter, 3, categoryID);
                        userAnswer.setIsRight(choice.getIsRight());
                        userAnswer.setCategoryID(categoryID);
                        if (quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.updateUserAnswer(userAnswer, Integer.parseInt(userID), counter, categoryID);
                        }
                        else if (!quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.registerUserAnswer(userAnswer);
                        }
                    }
                    else if ("D".equals(answer)) {
                        choiceID = 4 + ((questionIndex - 1) * 4);
                        userAnswer.setChoiceID(choiceID);
                        userAnswer.setQuestionID(counter);
                        userAnswer.setUserID(userID);
                        quiz.isRight(choice, counter, 4, categoryID);
                        userAnswer.setIsRight(choice.getIsRight());
                        userAnswer.setCategoryID(categoryID);
                        if (quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.updateUserAnswer(userAnswer, Integer.parseInt(userID), counter, categoryID);
                        }
                        else if (!quiz.isAnswerExist(Integer.parseInt(userID), counter, categoryID)) {
                            quiz.registerUserAnswer(userAnswer);
                        }
                    }
                    int correct = quiz.countCorrectAnswer(Integer.parseInt(user.getId()), categoryID);
                    if (quiz.isResultExists(Integer.parseInt(user.getId()), categoryID)) {
                        quiz.updateResult(userAnswer, correct);
                    }
                    else {
                        quiz.insertResult(userAnswer, correct);
                    }
                    request.setAttribute("correct", correct);
                    request.setAttribute("total", rows);
                    request.getRequestDispatcher("score.jsp").forward(request, response);
                }

                //Button to start the quiz 
            }
            else if ("start".equals(button)) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                else {
                    userName = (String) request.getParameter("userName");
                    userID = request.getParameter("id");
                    user.setUsername(userName);
                    user.setId(userID);
                    String table = "";
                    if (categoryID == 1) {
                        table = "JSPQuestion";
                    }
                    else if (categoryID == 2) {
                        table = "LinuxQuestion";
                    }
                    else if (categoryID == 3) {
                        table = "NetworkingQuestion";
                    }
                    else if (categoryID == 4) {
                        table = "JavaQuestion";
                    }
                    else if (categoryID == 5) {
                        table = "HTMLQuestion";
                    }
                    else if (categoryID == 6) {
                        table = "SQLQuestion";
                    }
                    quiz.open("SELECT * FROM " + table);
                    results = quiz.getResultSet();
                    int i = 0;
                    if (i == 0) {
                        quiz.moveLast(question);
                        rows = results.getRow();
                        quiz.moveFirst(question);
                        i++;
                    }
                    counter = 1;
                    quiz.deleteUserQuestionAnswer(Integer.parseInt(userID), categoryID);
                    request.setAttribute("fDisabled", finishDisabled);
                    quiz.getQuiz(question, categoryID, counter);
                    request.setAttribute("question", counter + ". " + question.getQuestion());
                    quiz.getChoice(choice, categoryID, counter);
                    request.setAttribute("choice1", choice.getChoice());
                    quiz.nextChoice(choice);
                    request.setAttribute("choice2", choice.getChoice());
                    quiz.nextChoice(choice);
                    request.setAttribute("choice3", choice.getChoice());
                    quiz.nextChoice(choice);
                    request.setAttribute("choice4", choice.getChoice());
                    request.getRequestDispatcher("quiz.jsp").forward(request, response);
                }

                //Redirecting to each categories' main page   
            }
            else if (param.equals("jsp")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                else {
                    categoryID = 1;
                    response.sendRedirect("quizJsp.jsp?categoryID=1");
                }
            }
            else if (param.equals("java")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                else {
                    categoryID = 4;
                    response.sendRedirect("quizJsp.jsp?categoryID=4");
                }
            }
            else if (param.equals("html")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                else {
                    categoryID = 5;
                    response.sendRedirect("quizJsp.jsp?categoryID=5");
                }
            }
            else if (param.equals("linux")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                else {
                    categoryID = 2;
                    response.sendRedirect("quizJsp.jsp?categoryID=2");
                }
            }
            else if (param.equals("networking")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                else {
                    categoryID = 3;
                    response.sendRedirect("quizJsp.jsp?categoryID=3");
                }
            }
            else if (param.equals("sql")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                else {
                    categoryID = 6;
                    response.sendRedirect("quizJsp.jsp?categoryID=6");
                }
            }
        }
        catch (Exception e) {
            out.println(e.getMessage());
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo ()
    {
        return "Short description";
    }// </editor-fold>

}
