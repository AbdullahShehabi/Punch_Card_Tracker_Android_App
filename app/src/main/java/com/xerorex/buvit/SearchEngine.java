package com.xerorex.buvit;

/**
 * Created by Aatif Shah on 11/24/2015.
 */
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Binder;
import android.support.annotation.Nullable;
import android.content.ServiceConnection;

import com.parse.ParseObject;
import com.xerorex.buvit.DataHandler.MyLocalBinder;

import java.util.ArrayList;
import java.util.List;


public class SearchEngine extends Service {

    private final IBinder profileBinder = new MyLocalBinder();
    ParseConvertor convertor;
    DataHandler dataService;
    boolean dataServiceIsBound = false;
    ProfileHandler profileService;
    boolean profileServiceIsBound = false;
    private UserProfile[] array;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return profileBinder;}

    @Override
    public void onCreate(){
        Intent dataIntent = new Intent(this, DataHandler.class);
        bindService(dataIntent, dataConnection, Context.BIND_AUTO_CREATE);

        Intent profileIntent = new Intent(this, ProfileHandler.class);
        bindService(profileIntent, profileConnection, Context.BIND_AUTO_CREATE);

        List<ParseObject> list = dataService.getParseObjects();

        array = new UserProfile[list.size()+1];
        for(int i = 0; i < array.length; i++)
            array[i] = new UserProfile();

        convertor.listToArray(list, array);

    }
    private static ArrayList<UserProfile> sliced = new ArrayList<>();
    public static void narrow(UserProfile[] a, String input){
        //sorts array
        QuickSort.quicksort(a);

        //returns all values that match with input
        System.out.println(slice(a, input));

    }

    private static ArrayList<UserProfile> slice(UserProfile[] a, String input){

        for(int i = 0; i < a.length; i++){
            if(a[i].getFullName().substring(0, input.length()).equalsIgnoreCase(input))
                sliced.add(a[i]);

        }
        return sliced;
    }

    public static void dice(){
        for(UserProfile i:sliced)
            System.out.println(i);
    }

    public class MyLocalBinder extends Binder{
        SearchEngine getService(){return SearchEngine.this; }
    }

    private ServiceConnection dataConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DataHandler.MyLocalBinder dataBinder = (DataHandler.MyLocalBinder) service;
            dataService = dataBinder.getService();
            dataServiceIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            dataServiceIsBound = false;
        }
    };

    private ServiceConnection profileConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ProfileHandler.MyLocalBinder profileBinder = (ProfileHandler.MyLocalBinder) service;
            profileService = profileBinder.getService();
            profileServiceIsBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            profileServiceIsBound = false;
        }
    };

}
