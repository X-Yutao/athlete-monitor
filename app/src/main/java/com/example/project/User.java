package com.example.project;

public class User {
    public String username;
    public String TelNumber;
    public String homeAddress;
    public String emailAddress;
    public String password;
    public String type;
    public String friend_request_from;
    public int height;
    public int weight;
    public int age;
    public User(String Un,String TN,String HAD,String ED,String pw,String t, String f,int h,int w,int a){
        username=Un;
        TelNumber=TN;
        homeAddress=HAD;
        emailAddress=ED;
        password=pw;
        friend_request_from=f;
        type=t;
        height=h;
        weight=w;
        age=a;
    }
    //getter
    public String getFriend_request_from(User u1){
        return friend_request_from;
    }
    public String getUsername(User u1){
        return username;
    }
    public String getTelNumber(User u1){
        return TelNumber;
    }
    public String getHomeAddress(User u1){
        return homeAddress;
    }
    public String getEmailAddress(User u1){
        return emailAddress;
    }
    public String getPassword(User u1){
        return password;
    }
    public String getType(User u1){
        return type;
    }
    public int getHeight(User u1){
        return height;
    }
    public int getWeight(User u1){
        return weight;
    }
    public int getAge(User u1){
        return age;
    }
    //setter
    public void setFriend_request_from(String f){
        friend_request_from=f;
    }
    public void setUsername(String un){
        username=un;
    }
    public void setTelNumber(String tn){
        TelNumber=tn;
    }
    public void setEmailAddress(String ed){
        emailAddress=ed;
    }
    public void setHomeAddress(String hw){
        homeAddress=hw;
    }
    public void setPassword(String p){
        password=p;
    }
    public void setType(String t){
        type=t;
    }
    public void setHeight(int h){
        height=h;
    }
    public void setWeight(int w){
        weight=w;
    }
    public void setAge(int a){
        age=a;
    }
}
