package com.jde.chatup.fab_handler.Fab_Model;

public class Fab_List_Model {
    private int fab_profile;
    private String fab_Obj_name;
    private String fab_obj_status;

    public Fab_List_Model(int fab_profile, String fab_Obj_name, String fab_obj_status) {
        this.fab_profile = fab_profile;
        this.fab_Obj_name = fab_Obj_name;
        this.fab_obj_status = fab_obj_status;
    }

    public int getFab_profile() {
        return fab_profile;
    }

    public void setFab_profile(int fab_profile) {
        this.fab_profile = fab_profile;
    }

    public String getFab_Obj_name() {
        return fab_Obj_name;
    }

    public void setFab_Obj_name(String fab_Obj_name) {
        this.fab_Obj_name = fab_Obj_name;
    }

    public String getFab_obj_status() {
        return fab_obj_status;
    }

    public void setFab_obj_status(String fab_obj_status) {
        this.fab_obj_status = fab_obj_status;
    }
}
