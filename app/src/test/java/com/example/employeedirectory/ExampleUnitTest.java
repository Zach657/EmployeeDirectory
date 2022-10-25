package com.example.employeedirectory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

import android.content.res.loader.AssetsProvider;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    //region EmployeeHandler
    @Test
    public void employeeValidDataTest(){
        EmployeeDataController edc = new EmployeeDataController("");
        ArrayList<EmployeeEntity> employees = null;
        try {
            JSONObject jsonTest = new JSONObject();
            JSONArray employeeArray = new JSONArray();
            JSONObject employee1 = new JSONObject();
            employee1.put("uuid","1234");
            employee1.put("full_name","Test User");
            employee1.put("email_address","testemail@gmail.com");
            employee1.put("team","core");
            employee1.put("employee_type","FULL_TIME");

            JSONObject employee2 = new JSONObject();
            employee2.put("uuid","1235");
            employee2.put("full_name","Test User");
            employee2.put("email_address","testemail@gmail.com");
            employee2.put("team","core");
            employee2.put("employee_type","FULL_TIME");

            employeeArray.put(employee1);
            employeeArray.put(employee2);
            jsonTest.put("employees",employeeArray);

            employees = edc.parseJSON(jsonTest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertTrue(employees != null && employees.size() == 2);
    }
    @Test
    public void employeeInvalidDataTest(){
        EmployeeDataController edc = new EmployeeDataController("");
        ArrayList<EmployeeEntity> employees = null;
        try {
            employees = (edc.parseJSON(new JSONObject(
                    "{\n" +
                            "\t\"not_employees\" : [   \n" +
                            "\t]\n" +
                            "}")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertTrue(employees == null);
    }
    @Test
    public void employeeInvalidDataTest2(){
        EmployeeDataController edc = new EmployeeDataController("");
        ArrayList<EmployeeEntity> employees = null;
        try {
            JSONObject jsonTest = new JSONObject();
            JSONArray employeeArray = new JSONArray();
            JSONObject employee1 = new JSONObject();
            employee1.put("uuid","1234");
            employee1.put("full_name","Test User");
            employee1.put("email_address","testemail@gmail.com");
            employee1.put("team","core");
            employee1.put("employee_type","FULL_TIME");

            //Missing UUID
            JSONObject employee2 = new JSONObject();
            employee2.put("full_name","Test User");
            employee2.put("email_address","testemail@gmail.com");
            employee2.put("team","core");
            employee2.put("employee_type","FULL_TIME");

            employeeArray.put(employee1);
            employeeArray.put(employee2);
            jsonTest.put("employees",employeeArray);
            employees = (edc.parseJSON(jsonTest));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertTrue(employees != null && employees.size() == 1);
    }
    @Test
    public void employeeInvalidDataTest3(){
        EmployeeDataController edc = new EmployeeDataController("");
        ArrayList<EmployeeEntity> employees = null;
        try {
            JSONObject jsonTest = new JSONObject();
            JSONArray employeeArray = new JSONArray();
            JSONObject employee1 = new JSONObject();
            //Missing Full Name
            employee1.put("uuid","1234");
            employee1.put("email_address","testemail@gmail.com");
            employee1.put("team","core");
            employee1.put("employee_type","FULL_TIME");

            //Missing UUID
            JSONObject employee2 = new JSONObject();
            employee2.put("full_name","Test User");
            employee2.put("email_address","testemail@gmail.com");
            employee2.put("team","core");
            employee2.put("employee_type","FULL_TIME");

            employeeArray.put(employee1);
            employeeArray.put(employee2);
            jsonTest.put("employees",employeeArray);
            employees = (edc.parseJSON(jsonTest));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertTrue(employees != null && employees.size() == 0);
    }
    @Test
    public void employeeEmptyDataTest(){
        EmployeeDataController edc = new EmployeeDataController("");
        ArrayList<EmployeeEntity> employees = null;
        try {
            employees = edc.parseJSON(new JSONObject("{\n" +
                    "  \"employees\" : [\n" +
                    "  " +
                    "]\n" +
                    "}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertTrue(employees != null && employees.size() == 0);
    }
    //endregion

    //TODO:Read JSON Resource Files
    private JSONObject getJSONObject(String path){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        BufferedReader jsonReader = new BufferedReader(new InputStreamReader(inputStream));
        String jsonString = "";
        String nextLine;
        try {
            while ((nextLine = jsonReader.readLine()) != null) {
                jsonString += nextLine;
            }
            return new JSONObject(jsonString);
        }
        catch(IOException e){

        }
        catch(JSONException e2){

        }
        return null;
    }
}