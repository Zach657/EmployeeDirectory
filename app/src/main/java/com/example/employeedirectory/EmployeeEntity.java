package com.example.employeedirectory;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class EmployeeEntity {

    //Required
    private String uuid;
    private String fullName;
    private String emailAddress;
    private String team;
    private String employeeType;

    //Optional
    private String phoneNumber;
    private String biography;
    private String photoURLSmall;
    private String photoURLLarge;

    public static final HashSet<String> EmployeeTypes = new HashSet<String>(Arrays.asList("FULL_TIME","PART_TIME","CONTRACTOR"));

    //Minimum Requirements Constructor
    public EmployeeEntity(String uuid, String fullName, String emailAddress, String team, String employeeType){
        this.uuid = uuid;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.team = team;
        this.employeeType = employeeType;
    }

    //Minimum Requirements + Optional Parameters Constructor
    public EmployeeEntity(String uuid, String fullName, String emailAddress, String team, String employeeType, HashMap<String, String> optionalParameters){
        this(uuid, fullName, emailAddress, team, employeeType);
        //Add Optional Parameters
        if(optionalParameters.containsKey("phone_number")){
            this.phoneNumber = optionalParameters.get("phone_number");
        }
        if(optionalParameters.containsKey("biography")){
            this.biography = optionalParameters.get("biography");
        }
        if(optionalParameters.containsKey("photo_url_small")){
            this.photoURLSmall = optionalParameters.get("photo_url_small");
        }
        if(optionalParameters.containsKey("photo_url_large")){
            this.photoURLLarge = optionalParameters.get("photo_url_large");
        }
    }

    public static EmployeeEntity buildEmployeeEntityFromJSON(JSONObject jsonEmployee) {
        //Ensure the JSON Object at least has all of the required field keys
        try {
            if (jsonEmployee.has("uuid") && jsonEmployee.has("full_name") && jsonEmployee.has("email_address")
                    && jsonEmployee.has("team") && jsonEmployee.has("employee_type")) {

                String uuid = jsonEmployee.get("uuid").toString();
                String fullName = jsonEmployee.get("full_name").toString();
                String emailAddress = jsonEmployee.get("email_address").toString();
                String team = jsonEmployee.get("team").toString();
                String employeeType = jsonEmployee.get("employee_type").toString().toUpperCase();

                //Make sure they are all not empty
                if (!uuid.isEmpty() && !fullName.isEmpty() && !emailAddress.isEmpty() && !team.isEmpty()
                        && !employeeType.isEmpty() && EmployeeEntity.EmployeeTypes.contains(employeeType)) {
                    HashMap<String, String> optionalParameters = new HashMap<String, String>();
                    if (jsonEmployee.has("phone_number")) {
                        optionalParameters.put("phone_number", (String) jsonEmployee.get("phone_number").toString());
                    }
                    if (jsonEmployee.has("biography")) {
                        optionalParameters.put("biography", (String) jsonEmployee.get("biography").toString());
                    }
                    if (jsonEmployee.has("photo_url_small")) {
                        optionalParameters.put("photo_url_small", (String) jsonEmployee.get("photo_url_small").toString());
                    }
                    if (jsonEmployee.has("photo_url_large")) {
                        optionalParameters.put("photo_url_large", (String) jsonEmployee.get("photo_url_large").toString());
                    }
                    return new EmployeeEntity(uuid, fullName, emailAddress, team, employeeType, optionalParameters);
                }
            }
        } catch (NullPointerException e) {
            Log.w("EmployeeDirectory","EmployeeEntity NullPointerException",e);
        } catch (JSONException e2) {
            Log.w("EmployeeDirectory","EmployeeEntity JSONException",e2);
        }
        return null;
    }

    //region $getters
    //Required Fields
    public String getUuid(){
        return uuid;
    }
    public String getFullName() {
        return fullName;
    }
    public String getTeam() {
        return team;
    }
    public String getEmployeeType() {
        return employeeType;
    }
    public String getEmailAddress() {
        return emailAddress;
    }

    //Optional Fields
    public String getPhoneNumber() {
        if(phoneNumber != null){
            return phoneNumber;
        }
        return "";
    }
    public String getBiography() {
        if(biography != null){
            return biography;
        }
        return "";
    }
    public String getPhotoURLSmall() {
        if(photoURLSmall != null){
            return photoURLSmall;
        }
        return "";
    }
    public String getPhotoURLLarge() {
        if(photoURLLarge != null){
            return photoURLLarge;
        }
        return "";
    }
    //endregion

}
