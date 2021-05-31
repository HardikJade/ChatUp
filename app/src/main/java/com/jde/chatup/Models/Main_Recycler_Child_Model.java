package com.jde.chatup.Models;

public class Main_Recycler_Child_Model {
    private int Model_Object_Profile;
    private String Model_Object_Name;
    private String Model_Object_Last_msg;
    private int Model_Object_unread_count;

    public Main_Recycler_Child_Model(int model_Object_Profile, String model_Object_Name, String model_Object_Last_msg, int model_Object_unread_count) {
        Model_Object_Profile = model_Object_Profile;
        Model_Object_Name = model_Object_Name;
        Model_Object_Last_msg = model_Object_Last_msg;
        Model_Object_unread_count = model_Object_unread_count;
    }


    public int getModel_Object_Profile() {
        return Model_Object_Profile;
    }

    public void setModel_Object_Profile(int model_Object_Profile) {
        Model_Object_Profile = model_Object_Profile;
    }

    public String getModel_Object_Name() {
        return Model_Object_Name;
    }

    public void setModel_Object_Name(String model_Object_Name) {
        Model_Object_Name = model_Object_Name;
    }

    public String getModel_Object_Last_msg() {
        return Model_Object_Last_msg;
    }

    public void setModel_Object_Last_msg(String model_Object_Last_msg) {
        Model_Object_Last_msg = model_Object_Last_msg;
    }

    public int getModel_Object_unread_count() {
        return Model_Object_unread_count;
    }

    public void setModel_Object_unread_count(int model_Object_unread_count) {
        Model_Object_unread_count = model_Object_unread_count;
    }
}
