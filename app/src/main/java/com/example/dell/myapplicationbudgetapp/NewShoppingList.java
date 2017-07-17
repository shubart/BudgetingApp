package com.example.dell.myapplicationbudgetapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewShoppingList extends Activity implements  View.OnClickListener, View.OnTouchListener {
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


    //////////////////////////////////////////////////////////////////////
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();

        //initialising edit text fields
        B_NAME_ET = (EditText) findViewById(R.id.Bnam_et);
        INITAL_AMOUNT_ET = (EditText) findViewById(R.id.initialAmount_et);
        ITEM_NAME_ET = (AutoCompleteTextView) findViewById(R.id.itemName_et);
        UNIT_PRICE_ET = (EditText) findViewById(R.id.unitprice_et);
        ITEM_QUANTITY_ET = (EditText) findViewById(R.id.quantity_et);
        ITEM_TOTAL_ET = (EditText) findViewById(R.id.itemtotal_et);
        BUY_FROM_ET = (AutoCompleteTextView) findViewById(R.id.buyfrom_et);
        B_TOTAL_ET = (EditText) findViewById(R.id.bugettotal_et);
        DIFFERENCE_ET = (EditText) findViewById(R.id.difference_et);
        NOTES_ET = (EditText) findViewById(R.id.notes_et);

        //initialising strings
        budgetName = null;
        initalAmount = null;
        itemName = null;
        unitPrice = null;
        itemQuantity = null;
        itemTotal = null;
        buyFrom = null;
        budgetTotal = null;
        difference = null;
        Notes = null;

        ItermCode = 1;
        itemCode ="";

        //TEXT WATCHER ON  QUANTITY
        ITEM_QUANTITY_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                total_item_Cost();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //TEXT WATCHER ON  UNIT PRICE
        UNIT_PRICE_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                total_item_Cost();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //TEXT WATCHER ON  UNIT PRICE
       B_NAME_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                itemCode = B_NAME_ET.getText().toString() + ItermCode;

            }

            @Override
            public void afterTextChanged(Editable s) {
                //my database
                mydb = new DBHelper(getApplicationContext(), itemCode);
            }
        });

        BUY_FROM_ET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BUY_FROM_LPW.show();
            }
        });


        //ARRAYS FOR THE AUTO COMPLETE TEXT FIELDS - ITEM NAMES
        ItemNames = new ArrayList<String>();
        ItemNames.add("Sugar");
        ItemNames.add("Soap");
        ItemNames.add("Bread");
        ItemNames.add("Butter");
        ItemNames.add("milk");
        ItemNames.add("tea");

        ShoppingMarkets = new ArrayList<String>();
        ShoppingMarkets.add("Any Where");
        ShoppingMarkets.add("Soweto Market - lusaka");
        ShoppingMarkets.add("Spar");
        ShoppingMarkets.add("Pick n pay");
        ShoppingMarkets.add("Game");
        ShoppingMarkets.add("Food Lovers");


        //SETTING THE ARRAY ADAPTER
        ArrayAdapter adapter1 = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1, ItemNames);
        ITEM_NAME_ET.setAdapter(adapter1);

      //SETTING THE ON TOUCH LISTENER
        BUY_FROM_ET.setOnTouchListener(this);
        //the list
        BUY_FROM_LPW = new ListPopupWindow(getApplicationContext());
        BUY_FROM_LPW.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ShoppingMarkets));

        BUY_FROM_LPW.setAnchorView(BUY_FROM_ET);
        BUY_FROM_LPW.setModal(true);

        BUY_FROM_LPW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Toitem = parent.getItemAtPosition(position).toString();
                BUY_FROM_ET.setText(Toitem);
                BUY_FROM_LPW.dismiss();
            }
        });



    }
    ///////END ON CREATE/////////////////////////

    @Override
    public void onClick(View v) {
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onNBudgetClick(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();

        switch (v.getId()) {

            case R.id.previewbtn:
               preview();




                break;

            case R.id.nBBackbtn:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.button2:

                fragmentTransaction.commit();

                Preview_Fragment preview_fragment = new Preview_Fragment();
                fragmentTransaction.replace(android.R.id.content, preview_fragment);

                break;

            case R.id.savebudgetbtn:
                saveBudget();
                break;

            case R.id.addtobudgetbtn:
                addToBudget();
                break;
        }
    }

    //ON TOUCH LISTENER FOR THE BUY FROM TEXT FIELD
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {

            case R.id.buyfrom_et:  //FROM

                int DRAWABLE_left = 2;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (event.getX() >= (v.getWidth() - ((EditText) v)
                            .getCompoundDrawables()[DRAWABLE_left].getBounds().width())) {
                        BUY_FROM_LPW.show();
                        return true;
                    }
                }

                break;
        }
        return false;
    }

    ///////////////////////////////////////

//CALCULATION OF ITEM COST
    public void total_item_Cost() {
        try {
            double unitPRICE = Double.parseDouble(UNIT_PRICE_ET.getText().toString());
            double itemQUANTITY = Double.parseDouble(ITEM_QUANTITY_ET.getText().toString());
            double product = unitPRICE * itemQUANTITY;

            ITEM_TOTAL_ET.setText("" + product);

            double budgetTotal = product + Double.parseDouble(B_TOTAL_ET.getText().toString());
            B_TOTAL_ET.setText(""+budgetTotal);

            double difference = Double.parseDouble(INITAL_AMOUNT_ET.getText().toString()) - budgetTotal;
            DIFFERENCE_ET.setText(""+difference);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Please Enter a Value ", Toast.LENGTH_SHORT).show();
        }

    }

    //ANIMATION OF ITEM NAME
    public void blink() {
        TextView txtView = (TextView)
                findViewById(R.id.itemName_et);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        txtView.startAnimation(animation1);

        TextView txt = ( TextView)  findViewById(R.id.itemNameTitle);
        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        txt.startAnimation(animation12);
    }

    ///////////method to add items to the budget list/////////////////
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void addToBudget() {
        blink();
        //CLEAR ANIMATION
        ITEM_NAME_ET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ITEM_NAME_ET.clearAnimation();
                TextView txt = (TextView) findViewById(R.id.itemNameTitle);
                txt.clearAnimation();
            }
        });




//get values
        itemName = ITEM_NAME_ET.getText().toString();
        unitPrice = UNIT_PRICE_ET.getText().toString();
        itemQuantity = ITEM_QUANTITY_ET .getText().toString();
        itemTotal = ITEM_TOTAL_ET .getText().toString();
        buyFrom = BUY_FROM_ET .getText().toString();

        budgetName = B_NAME_ET.getText().toString();
        initalAmount = INITAL_AMOUNT_ET .getText().toString();
        budgetTotal = B_TOTAL_ET .getText().toString();
        difference = DIFFERENCE_ET.getText().toString();
        Notes = NOTES_ET.getText().toString();
        String alamDate = "";
        String DateCreated = "";

//send item data to insert method
        mydb.insertItemDetails(itemName, itemQuantity, unitPrice, itemTotal, buyFrom,itemCode);
        //updating or inserting into budget table
       // mydb.updatebudgetDetailsTable(budgetName,initalAmount,budgetTotal,difference,Notes,alamDate,DateCreated);

        //clear the item fields
        ITEM_NAME_ET.setText("");
        UNIT_PRICE_ET.setText("0");
        ITEM_QUANTITY_ET.setText("1");
        BUY_FROM_ET.setText("");
    }
    /////////////////////////////////////////////

    ////////////method to save the whole budget///////////////////////
    public void saveBudget() {
        //get data from fields
        budgetName = B_NAME_ET.getText().toString();
        initalAmount = INITAL_AMOUNT_ET .getText().toString();
        budgetTotal = B_TOTAL_ET .getText().toString();
        difference = DIFFERENCE_ET.getText().toString();
        Notes = NOTES_ET.getText().toString();
        String AlamDate = "";
        String DateCreated = "";

        mydb.updatebudgetDetailsTable(budgetName,initalAmount,budgetTotal,difference,Notes,AlamDate,DateCreated);


        //clear All fields
        ITEM_NAME_ET.setText("");
        UNIT_PRICE_ET.setText("");
        ITEM_QUANTITY_ET.setText("");
        ITEM_TOTAL_ET.setText("0");
        B_NAME_ET.setText("");
        B_TOTAL_ET.setText("0");
        NOTES_ET.setText("");
        DIFFERENCE_ET.setText("0");
        INITAL_AMOUNT_ET.setText("0");

    }//


    public void preview() {



        //listview
        ArrayList array_list = mydb.getAllItems(itemCode);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        //adding it to the list view.
        obj = (ListView)findViewById(R.id.listView);
        obj.setAdapter(arrayAdapter);

        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "hi my love", Toast.LENGTH_SHORT).show();
            }
        });

       /* //clear All fields
        ITEM_NAME_ET.setText("");
        UNIT_PRICE_ET.setText("");
        ITEM_QUANTITY_ET.setText("");
        ITEM_TOTAL_ET.setText("0");
        B_NAME_ET.setText("");
        B_TOTAL_ET.setText("0");
        NOTES_ET.setText("");
        DIFFERENCE_ET.setText("0");
        INITAL_AMOUNT_ET.setText("0");  */

    }//


    } ////////////THE END OF MAIN CLASS////////////////////////////////

