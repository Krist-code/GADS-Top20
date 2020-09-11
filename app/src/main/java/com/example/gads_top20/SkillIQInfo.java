package com.example.gads_top20;

public class SkillIQInfo {

    private String mSkillName;
    private String mSkillScores;
    private String mSkillCountry;

    public SkillIQInfo() {
    }

    public SkillIQInfo(String skillName, String skillScores, String skillCountry) {
        mSkillName = skillName;
        mSkillScores = skillScores;
        mSkillCountry = skillCountry;
    }

    public String getSkillName() {
        return mSkillName;
    }

    public void setSkillName(String skillName) {
        mSkillName = skillName;
    }

    public String getSkillScores() {
        return mSkillScores;
    }

    public void setSkillScores(String skillScores) {
        mSkillScores = skillScores;
    }

    public String getSkillCountry() {
        return mSkillCountry;
    }

    public void setSkillCountry(String skillCountry) {
        mSkillCountry = skillCountry;
    }
}
