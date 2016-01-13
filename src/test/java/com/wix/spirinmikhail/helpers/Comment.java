package com.wix.spirinmikhail.helpers;

import org.openqa.selenium.WebElement;

/**
 * Created by mikhails on 08.01.2016
 */
public class Comment {
    private Integer uniqueNumber;
    private String commentText;
    private Boolean isActive;
    private String categories;
    private WebElement item;

    public Comment(Integer num, String com, Boolean act, String cat) {
        this.uniqueNumber = num;
        this.commentText = com;
        this.isActive = act;
        this.categories = cat;
    }

    public Comment(Integer num, String com, Boolean act, String cat, WebElement itm) {
        this.uniqueNumber = num;
        this.commentText = com;
        this.isActive = act;
        this.categories = cat;
        this.item = itm;
    }

    public Integer getUniqueNumber() {
        return uniqueNumber;
    }

    public String getCommentText() {
        return commentText;
    }

    public Boolean getIsActive() { return isActive; }

    public String getCategories() {
        return categories;
    }

    public WebElement getItem() {
        return item;
    }
}
