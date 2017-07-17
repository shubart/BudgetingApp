package com.example.dell.myapplicationsms_1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import static com.example.dell.myapplicationsms_1.Constants.EIGHT_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.FIRST_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.NINE_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.SECOND_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.SEVEN_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.SIX_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.THIRD_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.FOURTH_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.FIRTH_COLUMN;

public class CreateShoppingList extends Activity implements  View.OnClickListener, View.OnTouchListener {

//////////////////Variables/////////////////////////////////////////////////////
    private EditText B_NAME_ET, INITAL_AMOUNT_ET, UNIT_PRICE_ET, ITEM_QUANTITY_ET, ITEM_TOTAL_ET, B_TOTAL_ET, DIFFERENCE_ET, NOTES_ET;
    private Button CANCEL_EDIT,DONE_EDIT,ADD_BUDGET;
    private LinearLayout itemsLayout,parent1LinearLayout;
    private String itemBudgetMatchcode,DateCreated,codeDate ,budgetName, initalAmount, itemName, unitPrice, itemQuantity, itemTotal, buyFrom, budgetTotal, difference, Notes, selecteDbudgetName,selecteDinitalAmount, selecteDitemName, selecteDunitPrice, selecteDitemQuantity, selecteDitemTotal, selecteDbuyFrom, selecteDbudgetTotal, selecteDdifference, selecteDNotes, selecteDAlarm, selecteDCreatedDate, selecteDCode;
    private AutoCompleteTextView BUY_FROM_ET, ITEM_NAME_ET;
    private ArrayList<String> ItemNames, ShoppingMarkets;
    private ArrayList<HashMap<String, String>> budgetDets;
    private ArrayList<HashMap<String, String>> ItmList;

    private ListPopupWindow BUY_FROM_LPW;
    private ListView obj;
    private int ItermCount;
    private boolean budgetDetailsEdited;
    DBHelper mydb;

    private String updateTotal,updateInitial,updateProduct,updateDiff;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shopping_list);


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

        //creating database
        mydb = new DBHelper(getApplicationContext());

        //initialising strings

        DateCreated = new SimpleDateFormat("yyyy / MM / dd",
                Locale.getDefault()).format(new Date());
        codeDate = new SimpleDateFormat("MMdd/m",
                Locale.getDefault()).format(new Date());

        budgetName = null; initalAmount = null; itemName = null;  unitPrice = null; itemQuantity = null;itemTotal = null;buyFrom = null; budgetTotal = null; difference = null; Notes = null; itemBudgetMatchcode = null;selecteDbudgetName = B_NAME_ET.getText().toString(); selecteDinitalAmount = null; selecteDitemName = null;  selecteDunitPrice = null; selecteDitemQuantity = null;selecteDitemTotal = null;selecteDbuyFrom = null; selecteDbudgetTotal = null; selecteDdifference = null; selecteDNotes = null;  selecteDAlarm = null; selecteDCreatedDate = null; selecteDCode = null;

        updateDiff = null;  updateProduct = null;  updateInitial = null;  updateTotal = null; budgetDetailsEdited = false;

        ItermCount= 1; //to watch the number of items on the list

        CANCEL_EDIT = (Button) findViewById(R.id.canceledit);
        DONE_EDIT = (Button) findViewById(R.id.doneedit);
        ADD_BUDGET = (Button) findViewById(R.id.addtobudgetbtn);

        itemsLayout = (LinearLayout) findViewById(R.id.linearLayout3);
        parent1LinearLayout= (LinearLayout) findViewById(R.id.parent2LinearLayout);


        //hide two buttons
      normalPage();


        //TEXT WATCHER ON QUANTITY
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

        //TEXT WATCHER ON  ITEM_NAME_ET
        ITEM_NAME_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
              //  Toast.makeText(getApplicationContext(), "Please Enter Just ONE Item Only ", Toast.LENGTH_SHORT).show();
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
        //TEXT WATCHER ON  B_NAME_ET
        B_NAME_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ItermCount == 1) {
                    itemBudgetMatchcode = B_NAME_ET.getText().toString() + codeDate;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ItermCount == 1) {

                } else {
                    //update budget table
                    mydb.updatebudgetDetailsTableName(B_NAME_ET.getText().toString(), itemBudgetMatchcode);
                    budgetDetailsEdited = true;
                }//end if
            }
        }); //end watcher on  B_NAME_ET


        //TEXT WATCHER ON  INITAL_AMOUNT_ET
        INITAL_AMOUNT_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ItermCount == 1) {
                    itemBudgetMatchcode = B_NAME_ET.getText().toString() + codeDate;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                   initialAmtChanged();
            }
        }); //end watcher on  INITAL_AMOUNT_ET


        BUY_FROM_ET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BUY_FROM_LPW.show();
            }
        });

    budgetDets = new ArrayList<HashMap<String, String>>() ;
     ItmList = new ArrayList<HashMap<String, String>>();

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
        ShoppingMarkets.add("Shoprite");
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

//new calculating
    public void initialAmtChanged(){
        try {
            double ITotal;
            if ((UNIT_PRICE_ET.getText().toString().equals("")) && (ITEM_TOTAL_ET.getText().toString().equals(""))
                && (BUY_FROM_ET.getText().toString().equals(""))  && (ITEM_QUANTITY_ET.getText().toString().equals(""))) {
           ITotal= mydb.countItemTotal(itemBudgetMatchcode);

        }else {
          ITotal = mydb.countItemTotal(itemBudgetMatchcode) + Double.parseDouble(ITEM_TOTAL_ET.getText().toString());
        }

          //  double ITotal = mydb.countItemTotal(itemBudgetMatchcode) + Double.parseDouble(ITEM_TOTAL_ET.getText().toString());
            double diff = Double.parseDouble(INITAL_AMOUNT_ET.getText().toString()) - ITotal;
            mydb.updatebudgetDetailsTableUnitPrice(INITAL_AMOUNT_ET.getText().toString(), String.valueOf(diff), String.valueOf(ITotal), itemBudgetMatchcode);
            B_TOTAL_ET.setText("" + ITotal);
            DIFFERENCE_ET.setText("" + diff);

            if (diff < 1) {
                B_TOTAL_ET.setBackgroundResource(R.drawable.difference);
            } else {
                B_TOTAL_ET.setBackgroundResource(R.drawable.postivediff);
            }



        budgetDetailsEdited = true;
    }catch (Exception e){
        //
    }
    }
//CALCULATION OF ITEM COST
public void total_item_Cost() {
        try {
        double unitPRICE = Double.parseDouble(UNIT_PRICE_ET.getText().toString());
        double itemQUANTITY = Double.parseDouble(ITEM_QUANTITY_ET.getText().toString());
        double product = unitPRICE * itemQUANTITY;

        ITEM_TOTAL_ET.setText(""+ product);

            //update budget table
            double ITotal= mydb.countItemTotal(itemBudgetMatchcode) ;
        double budgetTotal = product + ITotal;
        B_TOTAL_ET.setText(""+budgetTotal);

        double difference = Double.parseDouble(INITAL_AMOUNT_ET.getText().toString()) - budgetTotal;
        DIFFERENCE_ET.setText(""+difference);

            if (difference < 1){
                B_TOTAL_ET.setBackgroundResource(R.drawable.difference);
            }else{
                B_TOTAL_ET.setBackgroundResource(R.drawable.postivediff);
            }

        }catch (Exception e){
            //
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

    public void editPage(){
        itemsLayout.setBackgroundResource(R.drawable.budget_border);
        parent1LinearLayout.setBackgroundResource(R.color.paren1linearlyot);

        CANCEL_EDIT.setVisibility(View.VISIBLE);
        DONE_EDIT.setVisibility(View.VISIBLE);
        CANCEL_EDIT.setClickable(true);
        DONE_EDIT.setClickable(true);
        ADD_BUDGET.setVisibility(View.INVISIBLE);
        ADD_BUDGET.setClickable(false);
    }
    public void normalPage(){
        itemsLayout.setBackgroundResource(0);
        parent1LinearLayout.setBackgroundResource(0);

        CANCEL_EDIT.setVisibility(View.INVISIBLE);
        DONE_EDIT.setVisibility(View.INVISIBLE);
        CANCEL_EDIT.setClickable(false);
        DONE_EDIT.setClickable(false);
        ADD_BUDGET.setVisibility(View.VISIBLE);
        ADD_BUDGET.setClickable(true);

    }

    public void deleteItem(){
        //delete the item from the item table
        int item_c = Integer.parseInt(selecteDCode);
        mydb.deleteItemDetails(item_c);
        Toast.makeText(getApplicationContext(),"Item Deleted Successfully",Toast.LENGTH_LONG).show();
        //decrease item counter
        ItermCount--;

        double newBudgetTotal = Double.parseDouble(selecteDbudgetTotal) - Double.parseDouble(selecteDitemTotal);
        double newDiff = Double.parseDouble(selecteDinitalAmount) - newBudgetTotal;

        selecteDbudgetTotal =String.valueOf(newBudgetTotal);
        selecteDdifference = String.valueOf(newDiff);
        B_TOTAL_ET.setText(selecteDbudgetTotal);
        DIFFERENCE_ET.setText(selecteDdifference);

        //update budget table
        mydb.updatebudgetDetailsTable(selecteDbudgetName, selecteDinitalAmount, String.valueOf(newBudgetTotal), String.valueOf(newDiff),
                selecteDNotes, selecteDAlarm, selecteDCreatedDate, selecteDCode);

    }

    public void deleteSelectedBudget(){
       mydb.deleteAllBudgetDetails(itemBudgetMatchcode);
        mydb.deleteAllItemDetails(itemBudgetMatchcode);

    }

    public void editItem() {
        if ((!UNIT_PRICE_ET.getText().toString().equals("")) && (!ITEM_TOTAL_ET.getText().toString().equals(""))
                && (!BUY_FROM_ET.getText().toString().equals("")) && (!B_NAME_ET.getText().toString().equals("")) && (!ITEM_QUANTITY_ET.getText().toString().equals("")) &&
                (!INITAL_AMOUNT_ET.getText().toString().equals(""))
                ) {
            //get values
            itemName = ITEM_NAME_ET.getText().toString();
            unitPrice = UNIT_PRICE_ET.getText().toString();
            itemQuantity = ITEM_QUANTITY_ET.getText().toString();
            itemTotal = ITEM_TOTAL_ET.getText().toString();
            buyFrom = BUY_FROM_ET.getText().toString();

            initalAmount = INITAL_AMOUNT_ET.getText().toString();
            budgetTotal = B_TOTAL_ET.getText().toString();
            difference = DIFFERENCE_ET.getText().toString();


            mydb.updateItemDetailsTable(Integer.valueOf(selecteDCode), itemName, itemQuantity, unitPrice, itemTotal, buyFrom);
            Toast.makeText(getApplicationContext(), "Edit Successful", Toast.LENGTH_LONG).show();

            double newBudgetTotal = Double.parseDouble(selecteDbudgetTotal) - Double.parseDouble(selecteDitemTotal);
            double newDiff = Double.parseDouble(selecteDinitalAmount) - newBudgetTotal;
            selecteDbudgetTotal = String.valueOf(newBudgetTotal);
            selecteDdifference = String.valueOf(newDiff);

            //update budget table
            mydb.updatebudgetDetailsTable(selecteDbudgetName, selecteDinitalAmount, String.valueOf(newBudgetTotal), String.valueOf(newDiff),
                    selecteDNotes, selecteDAlarm, selecteDCreatedDate, selecteDCode);

            //clear the item fields
            ITEM_NAME_ET.setText("");
            UNIT_PRICE_ET.setText("");
            ITEM_QUANTITY_ET.setText("");
            BUY_FROM_ET.setText("");
            ITEM_TOTAL_ET.setText("");

            B_TOTAL_ET.setText(selecteDbudgetTotal);
            DIFFERENCE_ET.setText(selecteDdifference);

            if (newDiff < 1){
                B_TOTAL_ET.setBackgroundResource(R.drawable.difference);
            }else{
                B_TOTAL_ET.setBackgroundResource(R.drawable.border);
            }

            blink();
            normalPage();
            previewItems();
            previewBudget();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please Fill in All The Details", Toast.LENGTH_SHORT).show();
        }
    }



public void addToBudget() {

    try{

        if ( (!UNIT_PRICE_ET.getText().toString().equals("")) &&  (!ITEM_TOTAL_ET.getText().toString().equals(""))
                && (!BUY_FROM_ET.getText().toString().equals("")) && (!B_NAME_ET.getText().toString().equals("")) && (!ITEM_QUANTITY_ET.getText().toString().equals("")) &&
                (!INITAL_AMOUNT_ET.getText().toString().equals(""))
                ) {

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

            if(ItermCount == 1){
                //send budget data to insert method
                mydb.insertBudgetDetails(budgetName,initalAmount,budgetTotal,difference,Notes,alamDate,DateCreated,itemBudgetMatchcode);
                mydb.insertItemDetails( ItermCount,itemName, itemQuantity, unitPrice, itemTotal, buyFrom, itemBudgetMatchcode);
                ItermCount++;
                previewBudget();
            }else {
                //send item data to insert method
                mydb.insertItemDetails(ItermCount,itemName, itemQuantity, unitPrice, itemTotal, buyFrom,itemBudgetMatchcode);
                ItermCount++;

                if(budgetDetailsEdited == true){  previewBudget();}
            }


        //clear the item fields
        ITEM_NAME_ET.setText("");
        UNIT_PRICE_ET.setText("");
        ITEM_QUANTITY_ET.setText("");
        BUY_FROM_ET.setText("");
            ITEM_TOTAL_ET.setText("");

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
    previewItems();

        } else {
            Toast.makeText(getApplicationContext(),
                    "Please Fill in All The Details", Toast.LENGTH_SHORT).show();
        }

    }catch (Exception e){   Toast.makeText(getApplicationContext(),"Please Fill in all Details",Toast.LENGTH_LONG).show();
    }
        }
/////////////////////////////////////////////


    public  void previewItems(){
      double now=  mydb.countItemTotal(itemBudgetMatchcode);
        updateTotal = String.valueOf(now);

        //TWO
        try{
            ItmList =  mydb.getAllItems(itemBudgetMatchcode);

            //THEN FORMAT FOR VIEWING
            final Spinner ITEMSspin = (Spinner)findViewById(R.id.spinner1);



            ITEMSspin.setBackgroundResource(R.drawable.item_border);
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(CreateShoppingList.this, ItmList, R.layout.item_list,
                    new String[] {FIRST_COLUMN, SECOND_COLUMN,THIRD_COLUMN , FOURTH_COLUMN,FIRTH_COLUMN}, new int[] { R.id.itemNam,R.id.buyFrm, R.id.unitPrc, R.id.quantity, R.id.itemTotalP});

            ITEMSspin.setAdapter(sAdap);

            final AlertDialog.Builder viewDetail = new AlertDialog.Builder(CreateShoppingList.this);
            ITEMSspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> arg0, View selectedItemView,  // what happens when an item has been selected
                                           int position, long id) {

                    selecteDitemName = ItmList.get(position).get(FIRST_COLUMN).toString();
                    selecteDbuyFrom = ItmList.get(position).get(SECOND_COLUMN).toString();
                    selecteDunitPrice = ItmList.get(position).get(THIRD_COLUMN).toString();
                    selecteDitemQuantity = ItmList.get(position).get(FOURTH_COLUMN).toString();
                    selecteDitemTotal = ItmList.get(position).get(FIRTH_COLUMN).toString();
                    selecteDCode = ItmList.get(position).get(SIX_COLUMN).toString();



                    viewDetail.setIcon(android.R.drawable.btn_star_big_on);
                    viewDetail.setTitle("Item Details");
                    viewDetail.setMessage("ITEM NAME      :   " + selecteDitemName + "\n"
                            + "Buy From          :   " +  selecteDbuyFrom + "\n"
                            + "Unit Price       K :   " + selecteDunitPrice + "\n"
                            + "Quantity           :   " + selecteDitemQuantity + "\n"
                            + "Total Item Price : K " + selecteDitemTotal + "\n"
                            + "ItemCode            :   " +  selecteDbudgetName+selecteDCode);
                    viewDetail.setPositiveButton("Delete",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    //ALERT FOR DELETE
                                    final AlertDialog.Builder viewDelete = new AlertDialog.Builder(CreateShoppingList.this);
                                    viewDelete.setIcon(android.R.drawable.ic_delete);
                                    viewDelete.setTitle("Are You Sure");
                                    viewDelete.setMessage("       You Want to Delete this Item ?");
                                    viewDelete.setPositiveButton("Yes",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,
                                                                    int which) {

                                                deleteItem();
                                                    //calling the preview methods
                                                    previewBudget();
                                                    previewItems();

                                                    dialog.dismiss();
                                                }
                                            });
                                    viewDelete.setNegativeButton("No",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,
                                                                    int which) {
                                                    // TODO Auto-generated method stub
                                                    dialog.dismiss();
                                                }
                                            });

                                    viewDelete.show();////END ALERT DELETE

                                    dialog.dismiss();
                                }
                            });
                    viewDetail.setNegativeButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                    dialog.dismiss();
                                }
                            });
                    viewDetail.setNeutralButton("Edit",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                   //Show buttons
                                   editPage();
                                    //close dialog
                                    dialog.dismiss();
                                    //setting text to the edit text boxes
                                    ITEM_NAME_ET.setText(selecteDitemName);
                                    UNIT_PRICE_ET .setText(selecteDunitPrice);
                                    ITEM_QUANTITY_ET .setText( selecteDitemQuantity);
                                    ITEM_TOTAL_ET .setText(selecteDitemTotal);
                                    BUY_FROM_ET .setText(selecteDbuyFrom);
                                   //change items background





                                    //
                                }
                            });
                    viewDetail.show();
                }

                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    Toast.makeText(CreateShoppingList.this,
                            "Your Selected : Nothing",
                            Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Sorry no items to show",Toast.LENGTH_LONG).show();
        }

    }
public  void previewBudget(){


    //ONE
    try{
        budgetDets =   mydb.getSavedBudgets();

        //THEN FORMAT FOR VIEWING
        final Spinner bspin = (Spinner)findViewById(R.id.spinner2);
       // bspin.setBackgroundResource(android.R.drawable.alert_light_frame);
        bspin.setBackgroundResource(R.drawable.budget_border);
        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(CreateShoppingList.this, budgetDets, R.layout.budget_list,
                new String[] {FIRST_COLUMN, SEVEN_COLUMN,SECOND_COLUMN,THIRD_COLUMN , FOURTH_COLUMN,NINE_COLUMN}, new int[] { R.id.itemNam,R.id.buyFrm, R.id.unitPrc, R.id.quantity, R.id.itemTotalP, R.id.notesView});

        bspin.setAdapter(sAdap);



       bspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> arg0, View selectedItemView,  // what happens when an item has been selected
                                      int position, long id) {

               final AlertDialog.Builder viewDetail = new AlertDialog.Builder(CreateShoppingList.this);


               selecteDbudgetName = budgetDets.get(position).get(FIRST_COLUMN).toString();
               selecteDCreatedDate= budgetDets.get(position).get(SEVEN_COLUMN).toString();
               selecteDinitalAmount = budgetDets.get(position).get(SECOND_COLUMN).toString();
               selecteDbudgetTotal = budgetDets.get(position).get(THIRD_COLUMN).toString();
               selecteDdifference = budgetDets.get(position).get(FOURTH_COLUMN).toString();
               selecteDNotes = budgetDets.get(position).get(SIX_COLUMN).toString();
              selecteDAlarm = budgetDets.get(position).get(SEVEN_COLUMN).toString();
               selecteDCode = budgetDets.get(position).get(EIGHT_COLUMN).toString();
               String newCount = budgetDets.get(position).get(NINE_COLUMN).toString() ;


               viewDetail.setIcon(android.R.drawable.btn_star_big_on);
               viewDetail.setTitle("Budget Details");
               viewDetail.setMessage("BUDGET NAME  :   " + selecteDbudgetName + "\n"
                       + "Date Created      :   " + selecteDCreatedDate + "\n\n"
                       + "Initial Price         :  K  " + selecteDinitalAmount + "\n"
                       + "Budget Total      :  K " + selecteDbudgetTotal + "\n"
                       + "Difference          :  K " + selecteDdifference + "\n\n"
                       + "Number Of Items  :   " + newCount + "  Items" + "\n"
                       + "Alarm Date         :   " + selecteDAlarm + "\n"
                       + "NOTES                :   " + selecteDNotes + "\n"
                       + "Budget Code     :   " + selecteDCode);

               viewDetail.setNegativeButton("OK",
                       new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog,
                                               int which) {
                               // TODO Auto-generated method stub

                               dialog.dismiss();
                           }
                       });
               viewDetail.setNeutralButton(" ",
                       new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog,
                                               int which) {
                               // TODO Auto-generated method stub
                              // Toast.makeText(getApplicationContext(), "Still under development", Toast.LENGTH_LONG).show();
                               dialog.dismiss();
                           }
                       });
               viewDetail.show();
           }

           public void onNothingSelected(AdapterView<?> arg0) {
               // TODO Auto-generated method stub
               Toast.makeText(CreateShoppingList.this,
                       "Your Selected : Nothing",
                       Toast.LENGTH_SHORT).show();
           }
        });

    }catch (Exception e){
        Toast.makeText(getApplicationContext(),"Sorry no items to show",Toast.LENGTH_LONG).show();
    }



        }



///////////what happens when button has been clicked///////////////////////////////

@Override
public void onClick(View v) {

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
public void onNBudgetClick(View v) {

        switch (v.getId()) {
            case R.id.previewTxt:
                    previewBudget();
                  previewItems();

                break;

            case R.id.backButton:
                //ALERT FOR SAVE
                final AlertDialog.Builder viewDetail1 = new AlertDialog.Builder(CreateShoppingList.this);
                viewDetail1.setIcon(android.R.drawable.ic_dialog_alert);
                viewDetail1.setTitle("Wait");
                viewDetail1.setMessage("       Any Changes Will Be Lost. ");
                viewDetail1.setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(getApplicationContext(),"Budget Saved Successfully",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(CreateShoppingList.this, MainActivity.class));
                                dialog.dismiss();
                            }
                        });
                viewDetail1.setNegativeButton("Don't Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                deleteSelectedBudget();
                                startActivity(new Intent(CreateShoppingList.this, MainActivity.class));
                                dialog.dismiss();
                            }
                        });

                viewDetail1.show(); ///END ALERT SAVE

                break;
            case R.id.saveButton:
                //ALERT FOR SAVE
                final AlertDialog.Builder viewDetail = new AlertDialog.Builder(CreateShoppingList.this);
                viewDetail.setIcon(android.R.drawable.ic_menu_save);
                viewDetail.setTitle("Save");
                viewDetail.setMessage("       You Want to Save this Budget ?");
                viewDetail.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(getApplicationContext(),"Budget Saved Successfully",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CreateShoppingList.this,MainActivity.class);
                                CreateShoppingList.this.startActivity(intent);

                                dialog.dismiss();
                            }
                        });
                viewDetail.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                deleteSelectedBudget();
                                dialog.dismiss();
                            }
                        });

                viewDetail.show(); ///END ALERT SAVE

                 break;



            case R.id.addtobudgetbtn:
                 addToBudget();
                break;

             case R.id.newButton:
                 deleteSelectedBudget();
                 Bundle savedInstanceState = null;
                 onCreate(savedInstanceState);
                 break;
            case R.id.canceledit:
                normalPage();

                break;
            case R.id.doneedit:
                editItem();

                break;
        }
        }


}

