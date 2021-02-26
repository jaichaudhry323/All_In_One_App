package org.o7planning.simplelistview.retrofit.Retrofit_Models;

public class UserRequest {

    String name;
    String job;

    public UserRequest(String name, String job) {
        this.job = job;
        this.name = name;
    }

    public UserRequest(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


}
