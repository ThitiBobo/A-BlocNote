package com.thiti.blocnote;

/**
 * Created by canard on 23/02/18.
 */

public class Note {

    private String mTitle;
    private String mContent;

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

    public Note(String title, String content) {
        mTitle = title;
        mContent = content;
    }

    public Note(String title) {
        this(title,"il y a rien dans ta note PD !!!");
    }
}
