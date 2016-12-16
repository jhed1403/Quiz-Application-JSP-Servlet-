/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Choice;
import entities.Question;
import entities.User;
import entities.UserQuestionAnswer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Programmers: Jhed Factolerin & Ramon Mercader<br>
 * Program: QuizDB.java <br>
 * Date: December 2016 <br>
 * version 1.0
 */
public class QuizDB {

    /**
     * String for username*
     */
    private String user;
    /**
     * String for password*
     */
    private String password;
    /**
     * The driver name*
     */
    private String driver;
    /**
     * ResultSet object*
     */
    private ResultSet rs;
    /**
     * Statement object
     */
    private Statement st;
    /**
     * Connection Object*
     */
    private Connection con;
    /**
     * ResultSet object*
     */
    private ResultSet results;
    /**
     * ResultSet object*
     */
    private ResultSet rset;

    /**
     * Method to connect to the database
     */
    public void connect() {
        try {
            user = "";
            password = "";
            driver = "net.ucanaccess.jdbc.UcanaccessDriver";
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:ucanaccess://" + "E://Users//Quiz.mdb", user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load JDBC/ODBC Driver");
            System.out.println("Class Error: " + e);
        } catch (SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
        }
    }

    /**
     * Method to open the connection
     *
     * @param sSQL
     * @throws SQLException
     */
    public void open(String sSQL) throws SQLException {
        st = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery(sSQL);
        rs.next();
    }

    /**
     * Method to open the connection for JSP
     *
     * @param sSQL
     * @throws SQLException
     */
    public void openJSP(String sSQL) throws SQLException {
        st = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery(sSQL);
    }

    /**
     * Method to close the ResultSet Statement and Connection
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        rs.close();
        st.close();
        con.close();
    }

    /**
     * Method to get the ResultSet
     *
     * @return ResultSet
     */
    public ResultSet getResultSet() {
        return rs;
    }

    /**
     * Method to get the Statement
     *
     * @return Statement
     */
    public Statement getStatement() {
        return st;
    }

    /**
     * Method to register user
     *
     * @param one
     */
    public void registerUser(User one) {
        try {
            rs.moveToInsertRow();
            rs.updateString("Username", one.getUsername());
            rs.updateString("Password", one.getPassword());
            rs.insertRow();
            rs.moveToCurrentRow();
            rs = st.executeQuery("SELECT Username, Password FROM User");
            rs.next();
        } catch (SQLException e) {
            System.out.println("SQL Error on registerUser: " + e);
        }
    }

    /**
     * Method to check the login credentials
     *
     * @param one
     * @return one
     */
    public User login(User one) {

        String username = one.getUsername();
        String pass = one.getPassword();

        String searchQuery
                = "select * from User where Username='"
                + username
                + "' AND Password='"
                + pass
                + "'";
        try {
            rs = st.executeQuery(searchQuery);
            boolean found = rs.next();
            if (!found) {
                one.setValid(false);
            } else if (found) {
                one.setValid(true);
            }
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        return one;
    }

    /**
     * Method to check if the user is admin
     *
     * @param one
     * @return one
     */
    public User isAdmin(User one) {

        try {
            String username = one.getUsername();
            String pass = one.getPassword();

            String searchQuery
                    = "select IsAdmin from User where Username='"
                    + username
                    + "' AND Password='"
                    + pass
                    + "'";
            rs = st.executeQuery(searchQuery);
            rs.next();
            one.setIsAdmin(rs.getBoolean(1));
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        return one;
    }

    /**
     * Method to get the quiz
     *
     * @param one
     * @param categoryID
     * @param questionID
     * @return one
     */
    public Question getQuiz(Question one, int categoryID, int questionID) {
        try {
            String table = "";
            int i = categoryID;
            if (i == 1) {
                table = "JSPQuestion";
            } else if (i == 2) {
                table = "LinuxQuestion";
            } else if (i == 3) {
                table = "NetworkingQuestion";
            } else if (i == 4) {
                table = "JavaQuestion";
            } else if (i == 5) {
                table = "HTMLQuestion";
            } else if (i == 6) {
                table = "SQLQuestion";
            }
            rs = st.executeQuery("SELECT * FROM " + table + " WHERE QuestionID=" + questionID);
            rs.next();
            one.setQuestion(rs.getString(2));
        } catch (Exception ex) {
            System.out.println("getquiz error:" + ex);
        }
        return one;
    }

    /**
     * Method that moves the cursor to the first record
     *
     * @param one
     * @return one
     */
    public Question moveFirst(Question one) {
        try {
            if (!rs.isFirst()) {
                rs.first();
                one.setQuestion(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error on move first: " + e);
        }
        return one;
    }

    /**
     * Method that moves the cursor to the next record
     *
     * @param one
     * @return one
     */
    public Question moveNext(Question one) {
        try {
            if (!rs.isLast()) {
                rs.next();
                one.setQuestion(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error on move next: " + e);
        }
        return one;
    }

    /**
     * Method that moves the cursor to the previous record
     *
     * @param one
     * @return one
     */
    public Question movePrevious(Question one) {
        try {
            if (!rs.isFirst()) {
                rs.previous();
                one.setQuestion(rs.getString("Question"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error on move previous: " + e);
        }
        return one;
    }

    /**
     * Method that moves the cursor to the last record
     *
     * @param one
     * @return one
     */
    public Question moveLast(Question one) {
        try {
            if (!rs.isLast()) {
                rs.last();
                one.setQuestion(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error on move last: " + e);
        }
        return one;
    }

    /**
     * Method to get the Choices
     *
     * @param c
     * @param categoryID
     * @param questionID
     * @return one
     */
    public Choice getChoice(Choice c, int categoryID, int questionID) {
        try {
            String table = "";
            if (categoryID == 1) {
                table = "JSPChoices";
            } else if (categoryID == 2) {
                table = "LinuxChoices";
            } else if (categoryID == 3) {
                table = "NetworkingChoices";
            } else if (categoryID == 4) {
                table = "JavaChoices";
            } else if (categoryID == 5) {
                table = "HTMLChoices";
            } else if (categoryID == 6) {
                table = "SQLChoices";
            }
            results = st.executeQuery("SELECT * FROM " + table + " WHERE QuestionID=" + questionID);
            results.next();
            c.setChoice(results.getString(2));
            c.setIsRight(results.getBoolean("IsRight"));

        } catch (Exception ex) {
            System.out.println("getquiz error:" + ex);
        }
        return c;
    }

    /**
     * Method that moves the cursor to the next record
     *
     * @param c
     * @return c
     */
    public Choice nextChoice(Choice c) {
        try {
            if (!results.isLast()) {
                results.next();
                c.setChoice(results.getString("Choice"));
                c.setIsRight(results.getBoolean("IsRight"));
            }
        } catch (Exception ex) {
            System.out.println("getquiz error:" + ex);
        }
        return c;
    }

    /**
     * Method that moves the cursor to the previous record
     *
     * @param c
     * @return c
     */
    public Choice previousChoice(Choice c) {
        try {
            if (!results.isFirst()) {
                results.previous();
                c.setChoice(results.getString("Choice"));
            }
        } catch (Exception ex) {
            System.out.println("getquiz error:" + ex);
        }
        return c;
    }

    /**
     * Method to checks if the user's answer is correct
     *
     * @param c
     * @param i
     * @param a
     * @param categoryID
     * @return c
     */
    public Choice isRight(Choice c, int i, int a, int categoryID) {
        try {
            String table = "";
            if (categoryID == 1) {
                table = "JSPChoices";
            } else if (categoryID == 2) {
                table = "LinuxChoices";
            } else if (categoryID == 3) {
                table = "NetworkingChoices";
            } else if (categoryID == 4) {
                table = "JavaChoices";
            } else if (categoryID == 5) {
                table = "HTMLChoices";
            } else if (categoryID == 6) {
                table = "SQLChoices";
            }
            int z = 1;
            rset = st.executeQuery("select * from " + table + " where QuestionID=" + i);
            rset.next();
            while (z < a) {
                rset.next();
                z++;
            }
            c.setIsRight(rset.getBoolean("IsRight"));
        } catch (Exception ex) {
            System.out.println("getquiz error:" + ex);
        }
        return c;
    }

    /**
     * Method to get userID
     *
     * @param one
     * @param username
     * @return c
     */
    public User getUserID(User one, String username) {
        try {
            rs = st.executeQuery("select * from User where Username='" + username + "'");
            rs.next();
            one.setId(rs.getString(1));
        } catch (Exception ex) {
            System.out.println("getquiz error:" + ex);
        }
        return one;

    }

    /**
     * Method to check if the user's answer exists
     *
     * @param userID
     * @param questionID
     * @param categoryID
     * @return isExist
     */
    public boolean isAnswerExist(int userID, int questionID, int categoryID) {
        boolean isExist = false;
        try {

            String query = "SELECT COUNT(1) FROM UserQuestionAnswer WHERE UserID="
                    + userID + " AND QuestionID=" + questionID + " AND CategoryID="
                    + categoryID;
            rs = st.executeQuery(query);
            rs.next();
            if (rs.getString(1).equals("1")) {
                isExist = true;
            } else if (rs.getString(1).equals("0")) {
                isExist = false;
            }

        } catch (SQLException e) {
            System.out.println("SQL Error on registerUser: " + e);
        }
        return isExist;
    }

    /**
     * Method to update the answer of the user
     *
     * @param one
     * @param userID
     * @param questionID
     * @param categoryID
     */
    public void updateUserAnswer(UserQuestionAnswer one, int userID, int questionID, int categoryID) {
        try {
            String query = "UPDATE UserQuestionAnswer "
                    + "SET ChoiceID=" + one.getChoiceID() + ", "
                    + "IsRight=" + one.getIsRight()
                    + " WHERE UserID=" + userID + " AND QuestionID=" + questionID + " AND CategoryID="
                    + categoryID;

            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("SQL Error on registerUser: " + e);
        }
    }

    /**
     * Method to add the answer of the user
     *
     * @param one
     */
    public void registerUserAnswer(UserQuestionAnswer one) {
        try {
            String query
                    = "INSERT INTO UserQuestionAnswer (UserID, QuestionID, ChoiceID, IsRight, CategoryID) "
                    + "VALUES (" + one.getUserID() + ", "
                    + one.getQuestionID() + ", "
                    + one.getChoiceID() + ", "
                    + one.getIsRight() + ", "
                    + one.getCategoryID() + ")";

            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("SQL Error on registerUser: " + e);
        }
    }

    /**
     * Method to count the correct answer
     *
     * @param userID
     * @param categoryID
     * @return correct
     */
    public int countCorrectAnswer(int userID, int categoryID) {
        int correct = 0;
        try {
            String query = "SELECT COUNT(*) FROM UserQuestionAnswer WHERE UserID="
                    + userID + " AND CategoryID="
                    + categoryID + " AND IsRight=true";
            rs = st.executeQuery(query);
            rs.next();
            correct = rs.getInt(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return correct;
    }

    /**
     * Method to add the result of the user
     *
     * @param one
     * @param mark
     */
    public void insertResult(UserQuestionAnswer one, int mark) {
        try {
            String query
                    = "INSERT INTO Results (UserID, Mark, CategoryID) "
                    + "VALUES (" + one.getUserID() + ", "
                    + mark + ", "
                    + one.getCategoryID() + ")";

            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("SQL Error on registerUser: " + e);
        }
    }

    /**
     * Method to update the result of the user
     *
     * @param one
     * @param mark
     */
    public void updateResult(UserQuestionAnswer one, int mark) {
        try {
            String query = "UPDATE Results "
                    + "SET Mark=" + mark
                    + " WHERE UserID=" + one.getUserID() + " AND CategoryID="
                    + one.getCategoryID();

            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("SQL Error on registerUser: " + e);
        }
    }

    /**
     * Method to check if the user has taken the quiz
     *
     * @param userID
     * @param categoryID
     * @return isExist
     */
    public boolean isResultExists(int userID, int categoryID) {
        boolean isExist = false;
        try {

            String query = "SELECT COUNT(1) FROM Results WHERE UserID="
                    + userID + " AND CategoryID="
                    + categoryID;
            rs = st.executeQuery(query);
            rs.next();
            if (rs.getString(1).equals("1")) {
                isExist = true;
            } else if (rs.getString(1).equals("0")) {
                isExist = false;
            }

        } catch (SQLException e) {
            System.out.println("SQL Error on registerUser: " + e);
        }
        return isExist;
    }

    /**
     * Method that updates record
     *
     * @param one
     * @param a
     * @param categoryID
     * @throws java.lang.Exception
     */
    public void updateQuestion(Question one, int a, int categoryID) throws Exception {
        try {
            String table = "";
            int i = categoryID;
            if (i == 1) {
                table = "JSPQuestion";
            } else if (i == 2) {
                table = "LinuxQuestion";
            } else if (i == 3) {
                table = "NetworkingQuestion";
            } else if (i == 4) {
                table = "JavaQuestion";
            } else if (i == 5) {
                table = "HTMLQuestion";
            } else if (i == 6) {
                table = "SQLQuestion";
            }
            String query = "UPDATE " + table + " SET "
                    + "Question = '" + one.getQuestion() + "' "
                    + "WHERE QuestionID =" + a;
            st.executeUpdate(query);

        } catch (SQLException e) {
            throw new Exception("BookCode already exists!");
        }

    }

    /**
     * Method that updates record
     *
     * @param one
     * @param i
     * @param isRight
     * @param categoryID
     * @throws Exception
     */
    public void updateChoices(Choice one, int i, boolean isRight, int categoryID) throws Exception {
        try {
            String table = "";
            if (categoryID == 1) {
                table = "JSPChoices";
            } else if (categoryID == 2) {
                table = "LinuxChoices";
            } else if (categoryID == 3) {
                table = "NetworkingChoices";
            } else if (categoryID == 4) {
                table = "JavaChoices";
            } else if (categoryID == 5) {
                table = "HTMLChoices";
            } else if (categoryID == 6) {
                table = "SQLChoices";
            }
            String query = "UPDATE " + table + " SET "
                    + "Choice = '" + one.getChoice() + "', "
                    + "IsRight = " + isRight
                    + " WHERE ChoiceID =" + i;
            st.executeUpdate(query);

        } catch (SQLException e) {
            throw new Exception("BookCode already exists!");
        }

    }

    /**
     * Method that deletes record
     *
     * @param a
     * @param categoryID
     */
    public void deleteQuestion(int a, int categoryID) {
        try {
            String table = "";
            String table1 = "";
            int i = categoryID;
            if (i == 1) {
                table = "JSPQuestion";
                table1 = "JSPChoices";
            } else if (i == 2) {
                table = "LinuxQuestion";
                table1 = "LinuxChoices";
            } else if (i == 3) {
                table = "NetworkingQuestion";
                table1 = "NetworkingChoices";
            } else if (i == 4) {
                table = "JavaQuestion";
                table1 = "JavaChoices";
            } else if (i == 5) {
                table = "HTMLQuestion";
                table1 = "HTMLChoices";
            } else if (i == 6) {
                table = "SQLQuestion";
                table1 = "SQLChoices";
            }

            String query = "DELETE FROM " + table
                    + " WHERE QuestionID= " + a + " AND CategoryID=" + categoryID;
            st.executeUpdate(query);
            query = "DELETE FROM " + table1
                    + " WHERE QuestionID= " + a;
            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("SQL Error on deleteRecord: " + e);
        }
    }

    /**
     * Method to add question
     *
     * @param one
     * @param categoryID
     */
    public void addQuestion(Question one, int categoryID) {
        try {
            String table = "";
            int i = categoryID;
            if (i == 1) {
                table = "JSPQuestion";
            } else if (i == 2) {
                table = "LinuxQuestion";
            } else if (i == 3) {
                table = "NetworkingQuestion";
            } else if (i == 4) {
                table = "JavaQuestion";
            } else if (i == 5) {
                table = "HTMLQuestion";
            } else if (i == 6) {
                table = "SQLQuestion";
            }
            String query
                    = "INSERT INTO " + table + " (Question, CategoryID) "
                    + "VALUES ('" + one.getQuestion() + "', "
                    + categoryID + ")";
            st.executeUpdate(query);
        } catch (SQLException e) {

        }
    }

    /**
     * Method to add choices
     *
     * @param one
     * @param categoryID
     * @param questionID
     */
    public void addChoices(Choice one, int categoryID, int questionID) {
        try {
            String table = "";
            if (categoryID == 1) {
                table = "JSPChoices";
            } else if (categoryID == 2) {
                table = "LinuxChoices";
            } else if (categoryID == 3) {
                table = "NetworkingChoices";
            } else if (categoryID == 4) {
                table = "JavaChoices";
            } else if (categoryID == 5) {
                table = "HTMLChoices";
            } else if (categoryID == 6) {
                table = "SQLChoices";
            }
            String query
                    = "INSERT INTO " + table + " (Choice, IsRight, QuestionID) "
                    + "VALUES ('" + one.getChoice() + "', '"
                    + one.getIsRight() + "', "
                    + questionID + ")";
            st.executeUpdate(query);
        } catch (SQLException e) {

        }
    }

    /**
     * Method to delete the answers of the user
     *
     * @param userID
     * @param categoryID
     */
    public void deleteUserQuestionAnswer(int userID, int categoryID) {
        try {
            String query = "DELETE FROM UserQuestionAnswer"
                    + " WHERE UserID= " + userID + " AND CategoryID=" + categoryID;
            st.executeUpdate(query);
        } catch (SQLException e) {

        }
    }

}
