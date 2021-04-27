package com.example.biotechgeneral;

public class ForumClass {

   private String forumName;
   private String description;
   private String forumType;
   private String forumAnswers;

    public ForumClass() {

    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForumType(String trim) {
        return forumType;
    }

    public void setForumType(String forumType) {
        this.forumType = forumType;
    }

    public String getForumAnswers() {
        return forumAnswers;
    }

    public void setForumAnswers(String forumAnswers) {
        this.forumAnswers = forumAnswers;
    }
}
