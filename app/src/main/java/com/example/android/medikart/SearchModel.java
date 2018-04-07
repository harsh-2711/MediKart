package com.example.android.medikart;

import ir.mirrajabi.searchdialog.core.Searchable;

/**
 * Created by Jay Dev on 07-04-2018.
 */

public class SearchModel implements Searchable {

    private String mTitle;

    public SearchModel(String mTitle)
    {
        this.mTitle = mTitle;
    }

    public void setTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }

    @Override
    public String getTitle(){
        return mTitle;
    }
}
