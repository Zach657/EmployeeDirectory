package com.example.employeedirectory;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EmployeeDataController edc;
    private SwipeRefreshLayout listRefreshLayout;
    private RecyclerView employeeRecyclerListView;
    private EmployeeItemAdapter eia;
    private SwipeRefreshLayout emptyRefreshLayout;
    private TextView errorTextView;
    private boolean loadingData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set Up List
        listRefreshLayout = this.findViewById(R.id.listRefreshLayout);
        listRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshEmployees();
            }
        });
        employeeRecyclerListView = this.findViewById(R.id.employeeRecyclerListView);
        LinearLayoutManager employeeLayoutManager = new LinearLayoutManager(this);
        employeeRecyclerListView.setLayoutManager(employeeLayoutManager);
        employeeRecyclerListView.addItemDecoration(new DividerItemDecoration(employeeRecyclerListView.getContext(),employeeLayoutManager.getOrientation()));

        //Error view with pull to refresh function
        emptyRefreshLayout = this.findViewById(R.id.emptyRefreshLayout);
        emptyRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshEmployees();
            }
        });
        errorTextView = this.findViewById(R.id.errorTextView);

        //TODO: Hardcoded JSON URL, add app configuration page
        edc = new EmployeeDataController("https://s3.amazonaws.com/sq-mobile-interview/employees.json");
        listRefreshLayout.setRefreshing(true);
        loadEmployees();
    }

    //Asynchronously Load Employees
    private void loadEmployees(){
        ExecutorService employeeLoadExecutor = Executors.newSingleThreadExecutor();
        Handler dataLoadedHandler = new Handler(Looper.getMainLooper());
        employeeLoadExecutor.execute(new Runnable(){
            @Override
            public void run() {
                ArrayList<EmployeeEntity> employees;
                if(!loadingData){
                    loadingData = true;
                    employees = edc.getEmployees();
                }
                else{
                    return;
                }
                dataLoadedHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(employees == null || employees.size() == 0){
                            listRefreshLayout.setVisibility(View.GONE);
                            emptyRefreshLayout.setVisibility(View.VISIBLE);
                            if(employees == null){
                                errorTextView.setText("Unable to fetch employee data");
                            }
                            else{
                                errorTextView.setText("No valid employee data found");
                            }
                        }
                        else {
                            listRefreshLayout.setVisibility(View.VISIBLE);
                            emptyRefreshLayout.setVisibility(View.GONE);
                            eia = new EmployeeItemAdapter(employees);
                            employeeRecyclerListView.setAdapter(eia);
                            eia.notifyDataSetChanged();
                        }
                        listRefreshLayout.setRefreshing(false);
                        emptyRefreshLayout.setRefreshing(false);
                        loadingData = false;
                    }
                });
            }
        });
    }

    //Asynchronously Refresh Employees
    private void refreshEmployees(){
        ExecutorService employeeLoadExecutor = Executors.newSingleThreadExecutor();
        Handler dataLoadedHandler = new Handler(Looper.getMainLooper());
        employeeLoadExecutor.execute(new Runnable(){
            @Override
            public void run() {
                ArrayList<EmployeeEntity> employees;
                if(!loadingData){
                    loadingData = true;
                    employees = edc.fetchEmployees();
                }
                else{
                    return;
                }
                dataLoadedHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(employees == null || employees.size() == 0){
                            listRefreshLayout.setVisibility(View.GONE);
                            emptyRefreshLayout.setVisibility(View.VISIBLE);
                            if(employees == null){
                                errorTextView.setText("Unable to fetch employee data");
                            }
                            else{
                                errorTextView.setText("No valid employee data found");
                            }
                        }
                        else {
                            listRefreshLayout.setVisibility(View.VISIBLE);
                            emptyRefreshLayout.setVisibility(View.GONE);
                            eia = new EmployeeItemAdapter(employees);
                            employeeRecyclerListView.setAdapter(eia);
                            eia.notifyDataSetChanged();
                        }
                        listRefreshLayout.setRefreshing(false);
                        emptyRefreshLayout.setRefreshing(false);
                        loadingData = false;
                    }
                });
            }
        });
    }
}