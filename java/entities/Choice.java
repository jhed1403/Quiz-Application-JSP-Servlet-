package entities;

import java.io.Serializable;

/**
 * Programmers: Jhed Factolerin & Ramon Mercader<br>
 * Program: AdminController.java <br>
 * Date: December 2016 <br>
 * version 1.0
 */
public class Choice implements Serializable {

    private int choiceID;
    private String choice;
    private boolean isRight;
    private String questionID;

    public Choice() {

    }

    public Choice(int choiceID, String choice, boolean isRight, String questionID) {
        this.choiceID = choiceID;
        this.choice = choice;
        this.isRight = isRight;
        this.questionID = questionID;
    }

    public int getChoiceID() {
        return choiceID;
    }

    public String getChoice() {
        return choice;
    }

    public boolean getIsRight() {
        return isRight;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

}
