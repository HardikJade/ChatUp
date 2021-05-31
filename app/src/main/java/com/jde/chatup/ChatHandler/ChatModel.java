package com.jde.chatup.ChatHandler;


public class ChatModel {

    //*****************************************
    //View Type Here
    private int ViewType;
    public int getViewType() {return ViewType;}
    public void setViewType(int viewType) {ViewType = viewType;}
    //*****************************************

    ///*****************************************
    //Chat Send Activity Here
    private String chatSendMatter;
    private int sendingState;
    private String chatSendTime;
    private boolean chatSendErr;

    public ChatModel(int viewType, String chatSendMatter, int sendingState, String chatSendTime, boolean chatSendErr) {
        ViewType = viewType;
        this.chatSendMatter = chatSendMatter;
        this.sendingState = sendingState;
        this.chatSendTime = chatSendTime;
        this.chatSendErr = chatSendErr;
    }

    public String getChatSendMatter() {return chatSendMatter;}

    public void setChatSendMatter(String chatSendMatter) {this.chatSendMatter = chatSendMatter;}

    public int getSendingState() {return sendingState;}

    public void setSendingState(int sendingState) {this.sendingState = sendingState;}

    public String getChatSendTime() {return chatSendTime;}

    public void setChatSendTime(String chatSendTime) {this.chatSendTime = chatSendTime;}

    public boolean isChatSendErr() {return chatSendErr;}

    public void setChatSendErr(boolean chatSendErr) {this.chatSendErr = chatSendErr;}

    ///*******************************************

    ///*******************************************
    //Chat Recieve Activity Here
    private String chatReciveMatter;
    private String recieveTime;

    public ChatModel(int viewType, String chatReciveMatter, String recieveTime) {
        ViewType = viewType;
        this.chatReciveMatter = chatReciveMatter;
        this.recieveTime = recieveTime;
    }

    public String getChatReciveMatter() {return chatReciveMatter;}

    public void setChatReciveMatter(String chatReciveMatter) {this.chatReciveMatter = chatReciveMatter;}

    public String getRecieveTime() {return recieveTime;}

    public void setRecieveTime(String recieveTime) {this.recieveTime = recieveTime;}

    ///*******************************************

    ///****************************************************
    // Chat Sneek Peek Here
    private String sneekPeekMatter;

    public ChatModel(int viewType, String sneekPeekMatter) {
        ViewType = viewType;
        this.sneekPeekMatter = sneekPeekMatter;
    }

    public String getSneekPeekMatter() {return sneekPeekMatter;}

    public void setSneekPeekMatter(String sneekPeekMatter) {this.sneekPeekMatter = sneekPeekMatter;}
    ///****************************************************
}
