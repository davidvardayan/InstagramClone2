package com.davidvardanyan.instagramclone2;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("2C7GtEVyNDuGmXOw37KZ5mtMnBaZwSSq12DRnzYK")
                // if defined
                .clientKey("xYb7oGwe9zK1dCO1CA8CXbu6hh3ybJNUEXrdeIc8")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
