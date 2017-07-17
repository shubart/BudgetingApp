package com.example.dell.myapplicationsms_1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingBudget extends Activity {

    //////////////////Variables/////////////////////////////////////////////////////
    private EditText B_NAME_ET, INITAL_AMOUNT_ET, UNIT_PRICE_ET, ITEM_QUANTITY_ET, ITEM_TOTAL_ET, B_TOTAL_ET, DIFFERENCE_ET, NOTES_ET;
    private String budgetName, initalAmount, itemName, unitPrice, itemQuantity, itemTotal, buyFrom, budgetTotal, difference, Notes;
    private AutoCompleteTextView BUY_FROM_ET, ITEM_NAME_ET;
    private ArrayList<String> ItemNames, ShoppingMarkets;
    private ListPopupWindow BUY_FROM_LPW;
    private ListView obj;
    DBHelper mydb;
    private int ItermCode;
    private  String itemCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_budget);

    }
    ///////END ON CREATE/////////////////////////



}

