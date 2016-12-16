package entities;

/**
 * Programmers: Jhed Factolerin & Ramon Mercader<br>
 * Program: AdminController.java <br>
 * Date: December 2016 <br>
 * version 1.0
 */
public class Category {

    private String categoryID;
    private String category;

    public Category() {
    }

    public Category(String categoryID, String category) {
        this.categoryID = categoryID;
        this.category = category;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
