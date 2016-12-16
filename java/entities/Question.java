package entities;

/**
 * Programmers: Jhed Factolerin & Ramon Mercader<br>
 * Program: AdminController.java <br>
 * Date: December 2016 <br>
 * version 1.0
 */
public class Question {

    private String questionID;
    private String question;
    private String categoryID;

    public Question() {

    }

    public Question(String questionID, String question, String categoryID) {
        this.questionID = questionID;
        this.question = question;
        this.categoryID = categoryID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getQuestion() {
        return question;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

}
