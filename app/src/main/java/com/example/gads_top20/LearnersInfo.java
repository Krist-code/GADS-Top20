package com.example.gads_top20;

public class LearnersInfo {

    private String mFullName;
    private String mLearnedHours;
    private String mCountry;

    public LearnersInfo() {
    }

    public LearnersInfo(String fullName, String learnedHours, String country) {
        mFullName = fullName;
        mLearnedHours = learnedHours;
        mCountry = country;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getLearnedHours() {
        return mLearnedHours;
    }

    public void setLearnedHours(String learnedHours) {
        mLearnedHours = learnedHours;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }
}
