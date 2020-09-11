package com.example.gads_top20;

class PostSubmit {

    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mProjectLink;

    public PostSubmit() {
    }

    public PostSubmit(String firstName, String lastName, String email, String projectLink) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mProjectLink = projectLink;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getProjectLink() {
        return mProjectLink;
    }
}
