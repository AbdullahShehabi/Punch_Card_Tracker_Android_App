package com.xerorex.buvit;

import java.io.Serializable;

/**
 * Created by Aatif Shah on 11/24/2015.
 */
public class UserProfile implements Comparable<UserProfile>, Serializable {

    private String first_name;
    private String last_name;
    private String email_address;
    private String objectID;
    private int numOfPunches;

    public UserProfile(){
        this(null, null, null, 0);
    }

    public UserProfile(String first, String last, String email, int numPunches){
        first_name = first;
        last_name = last;
        email_address = email;
        numOfPunches = numPunches;
    }

    public String getFirst_name(){return first_name;}

    public String getLast_name() {return last_name;}

    public String getEmail_address() {return email_address;}

    public String getFullName(){return first_name+" "+last_name;}

    public String getObjectID(){ return objectID;}

    public int getNumOfPunches(){ return numOfPunches;}



    public void setFirst_Name(String a){ first_name = a;}

    public void setLast_Name(String a) {last_name = a;}

    public void setEmail_address(String a)
    {
        email_address = a;
    }

    public void setObjectID(String a){ objectID = a; }

    public void setNumOfPunches(int a){ numOfPunches = a; }


    @Override
    public String toString(){
        return getFullName();
    }

    @Override
    public int compareTo(UserProfile b){
        return this.getFullName().compareTo(b.getFullName());
    }

}
