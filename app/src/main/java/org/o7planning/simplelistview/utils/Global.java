package org.o7planning.simplelistview.utils;

public class Global {

    private String email ;
    private String password ;

    private String title="Not Set";
    private String content="Not Set";

    private static Global instance;

    // Getter/setter

    public static Global getInstance() {
        if (instance == null)
            instance = new Global();
        return instance;
    }

    private Global() {
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
