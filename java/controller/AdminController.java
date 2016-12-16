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
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    /**QuizDB object**/
    private QuizDB quiz;
    /**ResultSet object**/
    private ResultSet results;
    /**User object**/
    private User user;
    /**Listens to the buttons**/
    private String button;
    /**Message to be sent**/
    private String message;
    /**Listens to parameters**/
    private String param;
    /**Question object**/
    private Question question;
    /**Choice object**/
    private Choice choice;
    /**UserQuestionAnswer object**/
    private UserQuestionAnswer userAnswer;
    /**Determines the category**/
    private int categoryID;
    /**ChoiceID of the user's answer**/
    private int choiceID;
    /**Index of the question**/
    private int counter;
    /**Checks if add is clicked**/
    private boolean isAdd;
    /**Checks if edit is clicked**/
    private boolean isEdit;
    /**Checks the radiobutton**/
    private String checked;
    /**Total number of questions**/
    private int rows;
    /**Disables button**/
    private String disabled;
    /**Disables button**/
    private String prevDisabled;

    @Override
    public void init() throws ServletException {
        checked = "";
        counter = 1;
        quiz = new QuizDB();
        quiz.connect();
        user = new User();
        question = new Question();
        choice = new Choice();
        question = new Question();
        userAnswer = new UserQuestionAnswer();
        choiceID = 1;
        isAdd = false;
        disabled = "";
        prevDisabled = "disabled";
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();;
        try {
            button = request.getParameter("button");
            param = request.getParameter("category");

            //Next
            if ("next".equals(button)) {
                //Redirecting to next page
                if (categoryID > 0) {
                    if (counter < rows) {
                        counter++;
                        if (counter == rows) {
                            disabled = "disabled";
                        } else {
                            disabled = "";
                        }
                        request.setAttribute("disabled", disabled);
                        quiz.getQuiz(question, categoryID, counter);
                        request.setAttribute("question", counter + ". " + question.getQuestion());
                        quiz.getChoice(choice, categoryID, counter);
                        if (choice.getIsRight() == true) {
                            checked = "checked";
                        } else {
                            checked = "";
                        }
                        request.setAttribute("IsRight1", checked);
                        request.setAttribute("choice1", choice.getChoice());
                        quiz.nextChoice(choice);
                        if (choice.getIsRight() == true) {
                            checked = "checked";
                        } else {
                            checked = "";
                        }
                        request.setAttribute("IsRight2", checked);
                        request.setAttribute("choice2", choice.getChoice());
                        quiz.nextChoice(choice);
                        if (choice.getIsRight() == true) {
                            checked = "checked";
                        } else {
                            checked = "";
                        }
                        request.setAttribute("IsRight3", checked);
                        request.setAttribute("choice3", choice.getChoice());
                        quiz.nextChoice(choice);
                        if (choice.getIsRight() == true) {
                            checked = "checked";
                        } else {
                            checked = "";
                        }
                        request.setAttribute("IsRight4", checked);
                        request.setAttribute("choice4", choice.getChoice());
                        request.getRequestDispatcher("adminView.jsp").forward(request, response);
                    }
                }

                //Previous
            } else if ("previous".equals(button)) {
                if (categoryID > 0) {
                    if (counter > 1) {
                        counter--;
                        if (counter == 1) {
                            prevDisabled = "disabled";
                        } else {
                            prevDisabled = "";
                        }
                        request.setAttribute("prevDisabled", prevDisabled);
                        quiz.getQuiz(question, categoryID, counter);
                        request.setAttribute("question", counter + ". " + question.getQuestion());
                        quiz.getChoice(choice, categoryID, counter);
                        if (choice.getIsRight() == true) {
                            checked = "checked";
                        } else {
                            checked = "";
                        }
                        request.setAttribute("IsRight1", checked);
                        request.setAttribute("choice1", choice.getChoice());
                        quiz.nextChoice(choice);
                        if (choice.getIsRight() == true) {
                            checked = "checked";
                        } else {
                            checked = "";
                        }
                        request.setAttribute("IsRight2", checked);
                        request.setAttribute("choice2", choice.getChoice());
                        quiz.nextChoice(choice);
                        if (choice.getIsRight() == true) {
                            checked = "checked";
                        } else {
                            checked = "";
                        }
                        request.setAttribute("IsRight3", checked);
                        request.setAttribute("choice3", choice.getChoice());
                        quiz.nextChoice(choice);
                        if (choice.getIsRight() == true) {
                            checked = "checked";
                        } else {
                            checked = "";
                        }
                        request.setAttribute("IsRight4", checked);
                        request.setAttribute("choice4", choice.getChoice());
                        request.getRequestDispatcher("adminView.jsp").forward(request, response);
                    }
                }

                //Add
            } else if ("add".equals(button)) {
                isAdd = true;
                request.getRequestDispatcher("add.jsp").forward(request, response);

                //First
            } else if ("first".equals(button)) {
                counter = 1;
                if (counter == 1) {
                    prevDisabled = "disabled";
                } else {
                    prevDisabled = "";
                }
                request.setAttribute("prevDisabled", prevDisabled);
                quiz.getQuiz(question, categoryID, counter);
                request.setAttribute("question", counter + ". " + question.getQuestion());
                quiz.getChoice(choice, categoryID, counter);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight1", checked);
                request.setAttribute("choice1", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight2", checked);
                request.setAttribute("choice2", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight3", checked);
                request.setAttribute("choice3", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight4", checked);
                request.setAttribute("choice4", choice.getChoice());
                request.getRequestDispatcher("adminView.jsp").forward(request, response);

                //Last
            } else if ("last".equals(button)) {
                counter = rows;
                if (counter == rows) {
                    disabled = "disabled";
                } else {
                    disabled = "";
                }
                request.setAttribute("disabled", disabled);
                quiz.getQuiz(question, categoryID, counter);
                request.setAttribute("question", counter + ". " + question.getQuestion());
                quiz.getChoice(choice, categoryID, counter);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight1", checked);
                request.setAttribute("choice1", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight2", checked);
                request.setAttribute("choice2", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight3", checked);
                request.setAttribute("choice3", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight4", checked);
                request.setAttribute("choice4", choice.getChoice());
                request.getRequestDispatcher("adminView.jsp").forward(request, response);

                //Delete
            } else if ("delete".equals(button)) {
                quiz.deleteQuestion(counter, categoryID);
                rows--;
                if (counter == 1) {
                    counter++;
                } else {
                    counter--;
                }
                quiz.getQuiz(question, categoryID, counter);
                request.setAttribute("question", counter + ". " + question.getQuestion());
                quiz.getChoice(choice, categoryID, counter);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight1", checked);
                request.setAttribute("choice1", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight2", checked);
                request.setAttribute("choice2", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight3", checked);
                request.setAttribute("choice3", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight4", checked);
                request.setAttribute("choice4", choice.getChoice());
                request.getRequestDispatcher("adminView.jsp").forward(request, response);

                //Back
            } else if ("back".equals(button)) {
                counter = 1;
                request.getRequestDispatcher("admin.jsp").forward(request, response);

                //Edit
            } else if ("edit".equals(button)) {
                isEdit = true;
                quiz.getQuiz(question, categoryID, counter);
                request.setAttribute("question", counter + ". " + question.getQuestion());
                quiz.getChoice(choice, categoryID, counter);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight1", checked);
                request.setAttribute("choice1", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight2", checked);
                request.setAttribute("choice2", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight3", checked);
                request.setAttribute("choice3", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight4", checked);
                request.setAttribute("choice4", choice.getChoice());
                request.getRequestDispatcher("edit.jsp").forward(request, response);

                //Save
            } else if ("save".equals(button)) {
                if (isEdit) {
                    String updatedQuestion = request.getParameter("q");
                    question.setQuestion(updatedQuestion.substring(3));
                    quiz.updateQuestion(question, counter, categoryID);
                    String updatedChoice = request.getParameter("code1");
                    boolean updatedIsRight = Boolean.parseBoolean(request.getParameter("correct1"));
                    choice.setChoice(updatedChoice);
                    choiceID = 1 + ((counter - 1) * 4);
                    quiz.updateChoices(choice, choiceID, updatedIsRight, categoryID);
                    updatedChoice = request.getParameter("code2");
                    updatedIsRight = Boolean.parseBoolean(request.getParameter("correct2"));
                    choice.setChoice(updatedChoice);
                    choiceID = 2 + ((counter - 1) * 4);
                    quiz.updateChoices(choice, choiceID, updatedIsRight, categoryID);
                    updatedChoice = request.getParameter("code3");
                    updatedIsRight = Boolean.parseBoolean(request.getParameter("correct3"));
                    choice.setChoice(updatedChoice);
                    choiceID = 3 + ((counter - 1) * 4);
                    quiz.updateChoices(choice, choiceID, updatedIsRight, categoryID);
                    updatedChoice = request.getParameter("code4");
                    updatedIsRight = Boolean.parseBoolean(request.getParameter("correct4"));
                    choice.setChoice(updatedChoice);
                    choiceID = 4 + ((counter - 1) * 4);
                    quiz.updateChoices(choice, choiceID, updatedIsRight, categoryID);
                    quiz.getQuiz(question, categoryID, counter);
                    request.setAttribute("question", counter + ". " + question.getQuestion());
                    quiz.getChoice(choice, categoryID, counter);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight1", checked);
                    request.setAttribute("choice1", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight2", checked);
                    request.setAttribute("choice2", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight3", checked);
                    request.setAttribute("choice3", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight4", checked);
                    request.setAttribute("choice4", choice.getChoice());
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                    isEdit = false;
                } else if (isAdd == true) {
                    String addedQuestion = request.getParameter("q");
                    int qID = ++rows;
                    question.setQuestion(addedQuestion);
                    quiz.addQuestion(question, categoryID);

                    String addedChoice = request.getParameter("code1");
                    boolean addedIsRight = Boolean.parseBoolean(request.getParameter("correct1"));
                    choice.setChoice(addedChoice);
                    choice.setIsRight(addedIsRight);
                    quiz.addChoices(choice, categoryID, qID);

                    addedChoice = request.getParameter("code2");
                    addedIsRight = Boolean.parseBoolean(request.getParameter("correct2"));
                    choice.setChoice(addedChoice);
                    choice.setIsRight(addedIsRight);
                    quiz.addChoices(choice, categoryID, qID);

                    addedChoice = request.getParameter("code3");
                    addedIsRight = Boolean.parseBoolean(request.getParameter("correct3"));
                    choice.setChoice(addedChoice);
                    choice.setIsRight(addedIsRight);
                    quiz.addChoices(choice, categoryID, qID);

                    addedChoice = request.getParameter("code4");
                    addedIsRight = Boolean.parseBoolean(request.getParameter("correct4"));
                    choice.setChoice(addedChoice);
                    choice.setIsRight(addedIsRight);
                    quiz.addChoices(choice, categoryID, qID);

                    counter = qID;
                    quiz.getQuiz(question, categoryID, counter);
                    request.setAttribute("question", counter + ". " + question.getQuestion());
                    quiz.getChoice(choice, categoryID, counter);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight1", checked);
                    request.setAttribute("choice1", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight2", checked);
                    request.setAttribute("choice2", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight3", checked);
                    request.setAttribute("choice3", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight4", checked);
                    request.setAttribute("choice4", choice.getChoice());
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                    isAdd = false;
                }

                //Cancel
            } else if (param.equals("cancel")) {
                isAdd = false;
                isEdit = false;
                quiz.getQuiz(question, categoryID, counter);
                request.setAttribute("question", counter + ". " + question.getQuestion());
                quiz.getChoice(choice, categoryID, counter);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight1", checked);
                request.setAttribute("choice1", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight2", checked);
                request.setAttribute("choice2", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight3", checked);
                request.setAttribute("choice3", choice.getChoice());
                quiz.nextChoice(choice);
                if (choice.getIsRight() == true) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                request.setAttribute("IsRight4", checked);
                request.setAttribute("choice4", choice.getChoice());
                request.getRequestDispatcher("adminView.jsp").forward(request, response);

                //JSP
            } else if (param.equals("jsp")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    categoryID = 1;
                    String table = "";
                    if (categoryID == 1) {
                        table = "JSPQuestion";
                    } else if (categoryID == 2) {
                        table = "LinuxQuestion";
                    } else if (categoryID == 3) {
                        table = "NetworkingQuestion";
                    } else if (categoryID == 4) {
                        table = "JavaQuestion";
                    } else if (categoryID == 5) {
                        table = "HTMLQuestion";
                    } else if (categoryID == 6) {
                        table = "SQLQuestion";
                    }
                    quiz.open("SELECT * FROM " + table);
                    results = quiz.getResultSet();
                    quiz.moveLast(question);
                    rows = results.getRow();
                    quiz.moveFirst(question);
                    counter = 1;
                    request.setAttribute("prevDisabled", prevDisabled);
                    quiz.getQuiz(question, categoryID, counter);
                    request.setAttribute("question", counter + ". " + question.getQuestion());
                    quiz.getChoice(choice, categoryID, counter);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight1", checked);
                    request.setAttribute("choice1", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight2", checked);
                    request.setAttribute("choice2", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight3", checked);
                    request.setAttribute("choice3", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight4", checked);
                    request.setAttribute("choice4", choice.getChoice());
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                }

                //Java
            } else if (param.equals("java")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    categoryID = 4;
                    String table = "";
                    if (categoryID == 1) {
                        table = "JSPQuestion";
                    } else if (categoryID == 2) {
                        table = "LinuxQuestion";
                    } else if (categoryID == 3) {
                        table = "NetworkingQuestion";
                    } else if (categoryID == 4) {
                        table = "JavaQuestion";
                    } else if (categoryID == 5) {
                        table = "HTMLQuestion";
                    } else if (categoryID == 6) {
                        table = "SQLQuestion";
                    }
                    quiz.open("SELECT * FROM " + table);
                    results = quiz.getResultSet();
                    quiz.moveLast(question);
                    rows = results.getRow();
                    quiz.moveFirst(question);
                    counter = 1;
                    request.setAttribute("prevDisabled", prevDisabled);
                    quiz.getQuiz(question, categoryID, counter);
                    request.setAttribute("question", counter + ". " + question.getQuestion());
                    quiz.getChoice(choice, categoryID, counter);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight1", checked);
                    request.setAttribute("choice1", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight2", checked);
                    request.setAttribute("choice2", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight3", checked);
                    request.setAttribute("choice3", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight4", checked);
                    request.setAttribute("choice4", choice.getChoice());
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                }

                //HTML
            } else if (param.equals("html")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    categoryID = 5;
                    String table = "";
                    if (categoryID == 1) {
                        table = "JSPQuestion";
                    } else if (categoryID == 2) {
                        table = "LinuxQuestion";
                    } else if (categoryID == 3) {
                        table = "NetworkingQuestion";
                    } else if (categoryID == 4) {
                        table = "JavaQuestion";
                    } else if (categoryID == 5) {
                        table = "HTMLQuestion";
                    } else if (categoryID == 6) {
                        table = "SQLQuestion";
                    }
                    quiz.open("SELECT * FROM " + table);
                    results = quiz.getResultSet();
                    quiz.moveLast(question);
                    rows = results.getRow();
                    quiz.moveFirst(question);
                    counter = 1;
                    request.setAttribute("prevDisabled", prevDisabled);
                    quiz.getQuiz(question, categoryID, counter);
                    request.setAttribute("question", counter + ". " + question.getQuestion());
                    quiz.getChoice(choice, categoryID, counter);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight1", checked);
                    request.setAttribute("choice1", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight2", checked);
                    request.setAttribute("choice2", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight3", checked);
                    request.setAttribute("choice3", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight4", checked);
                    request.setAttribute("choice4", choice.getChoice());
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                }

                //Linux
            } else if (param.equals("linux")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    categoryID = 2;
                    String table = "";
                    if (categoryID == 1) {
                        table = "JSPQuestion";
                    } else if (categoryID == 2) {
                        table = "LinuxQuestion";
                    } else if (categoryID == 3) {
                        table = "NetworkingQuestion";
                    } else if (categoryID == 4) {
                        table = "JavaQuestion";
                    } else if (categoryID == 5) {
                        table = "HTMLQuestion";
                    } else if (categoryID == 6) {
                        table = "SQLQuestion";
                    }
                    quiz.open("SELECT * FROM " + table);
                    results = quiz.getResultSet();
                    quiz.moveLast(question);
                    rows = results.getRow();
                    quiz.moveFirst(question);
                    counter = 1;
                    request.setAttribute("prevDisabled", prevDisabled);
                    quiz.getQuiz(question, categoryID, counter);
                    request.setAttribute("question", counter + ". " + question.getQuestion());
                    quiz.getChoice(choice, categoryID, counter);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight1", checked);
                    request.setAttribute("choice1", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight2", checked);
                    request.setAttribute("choice2", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight3", checked);
                    request.setAttribute("choice3", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight4", checked);
                    request.setAttribute("choice4", choice.getChoice());
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                }

                //Networking
            } else if (param.equals("networking")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    categoryID = 3;
                    String table = "";
                    if (categoryID == 1) {
                        table = "JSPQuestion";
                    } else if (categoryID == 2) {
                        table = "LinuxQuestion";
                    } else if (categoryID == 3) {
                        table = "NetworkingQuestion";
                    } else if (categoryID == 4) {
                        table = "JavaQuestion";
                    } else if (categoryID == 5) {
                        table = "HTMLQuestion";
                    } else if (categoryID == 6) {
                        table = "SQLQuestion";
                    }
                    quiz.open("SELECT * FROM " + table);
                    results = quiz.getResultSet();
                    quiz.moveLast(question);
                    rows = results.getRow();
                    quiz.moveFirst(question);
                    counter = 1;
                    request.setAttribute("prevDisabled", prevDisabled);
                    quiz.getQuiz(question, categoryID, counter);
                    request.setAttribute("question", counter + ". " + question.getQuestion());
                    quiz.getChoice(choice, categoryID, counter);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight1", checked);
                    request.setAttribute("choice1", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight2", checked);
                    request.setAttribute("choice2", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight3", checked);
                    request.setAttribute("choice3", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight4", checked);
                    request.setAttribute("choice4", choice.getChoice());
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                }

                //SQL
            } else if (param.equals("sql")) {
                if (request.getSession().getAttribute("userName") == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    categoryID = 6;
                    String table = "";
                    if (categoryID == 1) {
                        table = "JSPQuestion";
                    } else if (categoryID == 2) {
                        table = "LinuxQuestion";
                    } else if (categoryID == 3) {
                        table = "NetworkingQuestion";
                    } else if (categoryID == 4) {
                        table = "JavaQuestion";
                    } else if (categoryID == 5) {
                        table = "HTMLQuestion";
                    } else if (categoryID == 6) {
                        table = "SQLQuestion";
                    }
                    quiz.open("SELECT * FROM " + table);
                    results = quiz.getResultSet();
                    quiz.moveLast(question);
                    rows = results.getRow();
                    quiz.moveFirst(question);
                    counter = 1;
                    request.setAttribute("prevDisabled", prevDisabled);
                    quiz.getQuiz(question, categoryID, counter);
                    request.setAttribute("question", counter + ". " + question.getQuestion());
                    quiz.getChoice(choice, categoryID, counter);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight1", checked);
                    request.setAttribute("choice1", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight2", checked);
                    request.setAttribute("choice2", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight3", checked);
                    request.setAttribute("choice3", choice.getChoice());
                    quiz.nextChoice(choice);
                    if (choice.getIsRight() == true) {
                        checked = "checked";
                    } else {
                        checked = "";
                    }
                    request.setAttribute("IsRight4", checked);
                    request.setAttribute("choice4", choice.getChoice());
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
