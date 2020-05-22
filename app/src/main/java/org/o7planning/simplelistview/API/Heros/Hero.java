package org.o7planning.simplelistview.API.Heros;

public class Hero {

    String mName, mImageUrl;

    public Hero(String name, String imageurl) {
        mName = name;
        mImageUrl = imageurl;
    }

    public String getName() {
        return mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

}
