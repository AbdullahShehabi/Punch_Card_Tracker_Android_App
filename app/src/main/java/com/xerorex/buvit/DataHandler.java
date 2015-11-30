package com.xerorex.buvit;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Binder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Aatif Shah on 11/25/2015.*/


public class DataHandler extends Service {

    private List<ParseObject> parseObjects;


    IBinder dataBinder = new MyLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return dataBinder;}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserProfile");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                parseObjects = objects;
            }


        });



        this.stopService(intent);

        return 0;
    }

    public List<ParseObject> getParseObjects(){
        return parseObjects;
    }


    public class MyLocalBinder extends Binder{
        DataHandler getService(){
            return DataHandler.this;
        }
    }

}
