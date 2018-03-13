package com.thiti.blocnote.Model;

/**
 * Note est une classe qui permet de modéliser les notes enregistrées dans la BDD
 */

public class Note {

    private long mId;
    private String mTitle;
    private String mContent;

    public long getId() {
        return mId;
    }

    public void setId(long Id) {
        mId = Id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Note(long id, String title, String content) {
        mId = id;
        mTitle = title;
        mContent = content;
    }

    public Note(String title) {
        this(-1,title, new String());
    }
}
