package entities;

/**
 * Programmers: Jhed Factolerin & Ramon Mercader<br>
 * Program: AdminController.java <br>
 * Date: December 2016 <br>
 * version 1.0
 */
public class UserQuestionAnswer {

    private String userID;
    private int questionID;
    private int choiceID;
    private int categoryID;
    private boolean isRight;

    public UserQuestionAnswer() {
    }

    public UserQuestionAnswer(String userID, int questionID, int choiceID, boolean isRight, int categoryID) {
        this.userID = userID;
        this.questionID = questionID;
        this.choiceID = choiceID;
        this.isRight = isRight;
        this.categoryID = categoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getUserID() {
        return userID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public int getChoiceID() {
        return choiceID;
    }

    public boolean getIsRight() {
        return isRight;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    

}
