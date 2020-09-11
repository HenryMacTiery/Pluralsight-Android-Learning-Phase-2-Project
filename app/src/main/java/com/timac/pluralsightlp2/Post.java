package com.timac.pluralsightlp2;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("Email Address")
    private String emailAddress;
    @SerializedName("Name")
    private String name;
    @SerializedName("Last Name")
    private String lastName;
    @SerializedName("Link to Project")
    private String link;

    public Post(String emailAddress, String name, String lastName, String link) {
        this.emailAddress = emailAddress;
        this.name = name;
        this.lastName = lastName;
        this.link = link;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public String getLink() {
        return link;
    }
}
