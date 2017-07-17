package com.example.dell.myapplicationsms_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class SavedBudgets extends AppCompatActivity {
    /** Declaring variables**/
    private ListView savedBudgets_LV;
    private ArrayList<HashMap<String,String>> BudgetsList;
    DBHelper mydb;

    /** create method**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_budgets);

        //initialising variables
        savedBudgets_LV = (ListView) findViewById(R.id.listView_saved_budgets);
        BudgetsList = new  ArrayList<HashMap<String,String>>();
        mydb = new DBHelper(getApplicationContext());

        //make a call to retrieve all the saved Budgets
        BudgetsList = mydb.getSavedBudgets();

        //calling custom adapter
        CustomListAdapter adapter = new CustomListAdapter(this,BudgetsList);
        savedBudgets_LV.setAdapter(adapter);

    }
}
