package com.example.mygame;

public class Hero {

    private String name;
    private Post post;
    private Post prevPost;
    private Operation operation;
    private String appearance;
    private Act[] conversation;
    private int[] emergence = new int[2];
    public int attitude;

    public Hero(String name, Post post){
        this.name = name;
        this.post = post;
    }

    public Hero(String name, Operation operation, String appearance){
        this.name = name;
        this.operation = operation;
        this.appearance = appearance;
    }

    public Hero(String name, Operation operation, String appearance, Act[] conversation, int[] emergence){
        this.name = name;
        this.operation = operation;
        this.appearance = appearance;
        this.conversation = conversation;
        this.emergence = emergence;
    }

    public Act getConversation(int id){
        return conversation[id];
    }

    public String getAppearance(){
        return appearance;
    }

    public void setPost(Post post){
        if (prevPost == null){
            this.post = post;
        }
        else {
            this.post = post;
            prevPost = null;
        }
    }

    public void dismissHero(){
        if (operation == null){
            prevPost = post;
            post = null;
        }
        else {
            post = null;
        }
    }

    public boolean isPolitician(){
        if (post != null){
            return true;
        }
        return false;
    }

    public boolean isOperation(){
        if (operation != null){
            return true;
        }
        return false;
    }

    public int[] getEmergence(){
        return emergence;
    }

    public String getName(){
        return name;
    }

    public Post getPost(){
        return post;
    }

    public Post getPrevPost(){
        return prevPost;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitudeChange){
        this.attitude = this.attitude + attitudeChange;
    }
}