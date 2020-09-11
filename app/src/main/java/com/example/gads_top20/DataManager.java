package com.example.gads_top20;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager myInstance = null;
    private static List<LearnersInfo> mLearnersList = new ArrayList<LearnersInfo>();
    private static List<SkillIQInfo> mSkillIQList = new ArrayList<SkillIQInfo>();

    private DataManager() {
    }

    public static DataManager getInstance(){

        if (myInstance == null){
            myInstance = new DataManager();
//            initializedLearners();
//            initializedSkills();
        }
        return myInstance;
    }

    public void getLearnerFromJson(String jsonString){
        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";

        try{
            mLearnersList.clear();
            JSONArray jsonLearners = new JSONArray(jsonString);
            int numOfLearners = jsonLearners.length();

            for (int i=0; i<numOfLearners; i++){
                JSONObject learner = jsonLearners.getJSONObject(i);
                LearnersInfo retrievedLearner = new LearnersInfo(learner.getString(NAME), learner.getString(HOURS), learner.getString(COUNTRY));
                mLearnersList.add(retrievedLearner);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getSkillIQFromJson(String jsonString){
        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";

        try{
            mSkillIQList.clear();
            JSONArray jsonSkillIQ = new JSONArray(jsonString);
            int numOfSkillIQ = jsonSkillIQ.length();

            for (int i = 0; i < numOfSkillIQ; i++){
                JSONObject skilliq = jsonSkillIQ.getJSONObject(i);
                SkillIQInfo retrievedSkillIQ = new SkillIQInfo(skilliq.getString(NAME), skilliq.getString(SCORE), skilliq.getString(COUNTRY));
                mSkillIQList.add(retrievedSkillIQ);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<LearnersInfo> getLearnersList() {
        return mLearnersList;
    }

    public List<SkillIQInfo> getSkillIQList() {
        return mSkillIQList;
    }

//    initialization section
    private static void initializedLearners(){
        mLearnersList.add(new LearnersInfo("Kiwameh Christopher", "223", "Cameroon"));
        mLearnersList.add(new LearnersInfo("Liwi Doch", "213", "Kenya"));
        mLearnersList.add(new LearnersInfo("Kristo Pher", "243", "Nigeria"));
        mLearnersList.add(new LearnersInfo("Lopez Perez", "213", "South Africa"));
        mLearnersList.add(new LearnersInfo("Krist Norazo", "200", "Uganda"));
        mLearnersList.add(new LearnersInfo("Kihim karij", "222", "Tchad"));
        mLearnersList.add(new LearnersInfo("Kouam Elevate", "223", "Angola"));
        mLearnersList.add(new LearnersInfo("Kamdem Christian", "223", "Congo"));
        mLearnersList.add(new LearnersInfo("Salam Noutorn", "240", "Egypt"));
        mLearnersList.add(new LearnersInfo("moustafa Hamed", "203", "Mali"));
        mLearnersList.add(new LearnersInfo("Kaijo Gislent", "200", "Gabon"));
    }

    private static void initializedSkills(){
        mLearnersList.add(new LearnersInfo("Kiwameh Christopher", "323", "Cameroon"));
        mLearnersList.add(new LearnersInfo("Liwi Doch", "313", "Kenya"));
        mLearnersList.add(new LearnersInfo("Kristo Pher", "343", "Nigeria"));
        mLearnersList.add(new LearnersInfo("Lopez Perez", "313", "South Africa"));
        mLearnersList.add(new LearnersInfo("Krist Norazo", "300", "Uganda"));
        mLearnersList.add(new LearnersInfo("Karhim Narij", "322", "Tchad"));
        mLearnersList.add(new LearnersInfo("Kouam Elevate", "323", "Angola"));
        mLearnersList.add(new LearnersInfo("Kamdem Christian", "323", "Congo"));
        mLearnersList.add(new LearnersInfo("Salam Noutorn", "340", "Egypt"));
        mLearnersList.add(new LearnersInfo("moustafa Hamed", "303", "Mali"));
        mLearnersList.add(new LearnersInfo("Kaijo Gislent", "300", "Gabon"));
    }
}
