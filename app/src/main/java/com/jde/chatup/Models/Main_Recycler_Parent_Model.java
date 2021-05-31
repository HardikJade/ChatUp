package com.jde.chatup.Models;

import java.util.ArrayList;

public class Main_Recycler_Parent_Model {

    private String model_head_text;
    private ArrayList<Main_Recycler_Child_Model> main_recycler_child_model;


    public Main_Recycler_Parent_Model(String model_head_text, ArrayList<Main_Recycler_Child_Model> main_recycler_child_model) {
        this.model_head_text = model_head_text;
        this.main_recycler_child_model = main_recycler_child_model;
    }

    public String getModel_head_text() {
        return model_head_text;
    }

    public void setModel_head_text(String model_head_text) {
        this.model_head_text = model_head_text;
    }

    public ArrayList<Main_Recycler_Child_Model> getMain_recycler_child_model() {
        return main_recycler_child_model;
    }

    public void setMain_recycler_child_model(ArrayList<Main_Recycler_Child_Model> main_recycler_child_model) {
        this.main_recycler_child_model = main_recycler_child_model;
    }
}
