package com.example.mycloset;

public class Account {
    public String Username;
    public String Password;
    public int UserID;


    public void setUsername(String Username){
        this.Username = Username;
    }
    public void setPassword (String Password){
        this.Password = Password;
    }

    public void setUserID (int UserID){
        this.UserID = UserID;
    }

    public String getUsername(){
        return Username;
    }
    public String getPassword(){
        return Password;
    }
    public int getUserID() {
        return UserID;
    }
    //TODO Edit Account and delete Account Methods

}
