package com.example.employeedirectory.handlers;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONHandler {

    //Fetch JSONObject from a given url
    public JSONObject getJSONObjectFromURL(String path){
        try {
            URL url = new URL(path);
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String jsonString = "";
            String nextLine;
            while ((nextLine = jsonReader.readLine()) != null){
                jsonString += nextLine;
            }
            return new JSONObject(jsonString);
        }
        catch(IOException e){
            Log.w("EmployeeDirectory","JSONHandler IOException",e);
        }
        catch(JSONException e2){
            Log.w("EmployeeDirectory","JSONHandler JSONException",e2);
        }
        return null;
    }
}
