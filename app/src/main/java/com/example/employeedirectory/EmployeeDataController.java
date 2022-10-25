package com.example.employeedirectory;

import android.util.Log;

import com.example.employeedirectory.handlers.JSONHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class EmployeeDataController {

    private String jsonURL;
    private ArrayList<EmployeeEntity> employees;

    public EmployeeDataController(String jsonURL){
        this.jsonURL = jsonURL;
    }

    //Get the list of employees, only requerying if the current list is null
    public ArrayList<EmployeeEntity> getEmployees(){
        if(employees != null){
            return employees;
        }
        else{
            return fetchEmployees();
        }
    }

    //Parse employee data json
    public ArrayList<EmployeeEntity> parseJSON(JSONObject jsonEmployees){
        try {
            if (jsonEmployees != null && jsonEmployees.has("employees") && jsonEmployees.get("employees") instanceof JSONArray) {
                ArrayList<EmployeeEntity> employeeEntityArray = new ArrayList<EmployeeEntity>();
                JSONArray jsonEmployeeArray = (JSONArray) jsonEmployees.get("employees");
                for (int i = 0; i < jsonEmployeeArray.length(); i++) {
                    Object current = jsonEmployeeArray.get(i);
                    if(current instanceof JSONObject){
                        EmployeeEntity ee = EmployeeEntity.buildEmployeeEntityFromJSON((JSONObject) current);
                        if(ee != null){
                            employeeEntityArray.add(ee);
                        }
                    }
                }
                return employeeEntityArray;
            }
        }
        catch(Exception e){
            Log.w("EmployeeDirectory","EmployeeDataController Exception",e);
        }
        return null;
    }

    //Build the employees list from the network again
    public ArrayList<EmployeeEntity> fetchEmployees(){
        JSONHandler jsonHandler = new JSONHandler();
        employees = parseJSON(jsonHandler.getJSONObjectFromURL(jsonURL));
        //Group by team then sort by name
        if(employees != null) {
            Collections.sort(employees, new Comparator<EmployeeEntity>() {
                @Override
                public int compare(EmployeeEntity e1, EmployeeEntity e2) {
                    return (e1.getTeam() + e1.getFullName()).compareTo((e2.getTeam() + e2.getFullName()));
                }
            });
        }
        return employees;
    }

}
