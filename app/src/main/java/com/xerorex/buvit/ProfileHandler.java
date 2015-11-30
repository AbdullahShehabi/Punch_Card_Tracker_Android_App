package com.xerorex.buvit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ProfileHandler extends Service {

    private final IBinder profileBinder = new MyLocalBinder();

    public ProfileHandler() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return profileBinder;
    }

    public void addUserProfile(String first, String last, String email, int numOfPunches){
        ParseObject newProfile = new ParseObject("UserProfile");

        newProfile.put("first_name", first);
        newProfile.put("last_name", last);
        newProfile.put("email", email);
        newProfile.put("punches", numOfPunches);
        newProfile.saveInBackground();
    }

    public void addUserProfile(UserProfile profile){

        ParseObject newProfile = new ParseObject("UserProfile");

        newProfile.put("first_name", profile.getFirst_name());
        newProfile.put("last_name", profile.getLast_name());
        newProfile.put("email", profile.getEmail_address());
        newProfile.put("punches", profile.getEmail_address());
        newProfile.saveInBackground();

    }

    public void test(){
        Log.d("testing", "testing");
    }

    public void removeUserProfile(UserProfile deletable){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserProfile");
        query.getInBackground(deletable.getObjectID(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object will be your game score
                    object.deleteInBackground();
                } else {
                    // something went wrong
                }
            }
        });
    }

    public void updateUserProfile(final UserProfile updatable){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserProfile");
        query.getInBackground(updatable.getObjectID(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object will be your game score
                    object.put("punches",updatable.getNumOfPunches());
                } else {
                    // something went wrong
                }
            }
        });
    }

    public class MyLocalBinder extends Binder{
        ProfileHandler getService(){
            return ProfileHandler.this;
        }
    }


}







