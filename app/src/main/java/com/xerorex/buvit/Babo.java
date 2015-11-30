package com.xerorex.buvit;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Aatif Shah on 11/28/2015.
 */
public class Babo extends Application {

    @Override
    public void onCreate(){
        super.onCreate();


        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "QX7qPKrp4P7rBaTm8FOEpExKWfGkrv1vqhcUX8vH", "xoohKbT8dSwK1MMBQZHMFkrC7XirxMcoeZgc8cQR");

    }
}
