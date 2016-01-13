package com.wix.spirinmikhail.helpers;

import org.apache.xpath.operations.Bool;

/**
 * Created by mikhails on 08.01.2016
 */
public class Comment {
    private Integer uniqueNumber;
    private String commentText;
    private Boolean isActive;
    private String categories;

    public Comment(Integer num, String com, Boolean act, String cat) {
        this.uniqueNumber = num;
        this.commentText = com;
        this.isActive = act;
        this.categories = cat;
    }

    public Integer getUniqueNumber() {
        return uniqueNumber;
    }

    public String getCommentText() {
        return commentText;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getCategories() {
        return categories;
    }
}
