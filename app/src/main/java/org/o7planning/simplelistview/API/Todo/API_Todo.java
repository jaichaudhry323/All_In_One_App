package org.o7planning.simplelistview.API.Todo;

public class API_Todo {

    String mUserId;
    String mId;
    String mTitle;
    Boolean mCompleted;

    API_Todo(int userid, int id, String title, Boolean completed) {
        mUserId = String.valueOf(userid);
        mId = String.valueOf(id);
        mTitle = title;
        mCompleted = completed;
    }

    String getmUserId() {
        return mUserId;
    }

    String getmId() {
        return mId;
    }

    String getmTitle() {
        return mTitle;
    }

    String getmCompleted() {
        return String.valueOf(mCompleted);
    }

}
