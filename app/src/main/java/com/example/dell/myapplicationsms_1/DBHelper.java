package com.example.dell.myapplicationsms_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.dell.myapplicationsms_1.Constants.EIGHT_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.FIRST_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.NINE_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.SECOND_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.SEVEN_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.THIRD_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.FOURTH_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.FIRTH_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.SIX_COLUMN;

/**
 * Created by Dell on 2/9/2017.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BUDGET.db";
    public static final String CONTACTS_TABLE_NAME = "itemDetails";
    public static final String CONTACTS_COLUMN_itemName = "itemName";
    public static final String CONTACTS_COLUMN_itemQuantity = "itemQuantity";
    public static final String CONTACTS_COLUMN_itemUnitPrice = "itemUnitPrice";
    public static final String CONTACTS_COLUMN_itemTotalPrice = "itemTotalPrice";
    public static final String CONTACTS_COLUMN_itemBuyFrom = "itemBuyFrom";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        // creating database if it does not exist
        db.execSQL("CREATE TABLE budgetDetailsTable " + "(idB integer PRIMARY KEY autoincrement, budgetName text ,initialPrice text,totalPrice text, deference text,notes text, alarmDate text, dateCreated text,itemBudgetMatchcode text)");
        db.execSQL("CREATE TABLE ItemDetailsTable " + "(idI integer PRIMARY KEY autoincrement ,itemName text,itemQuantity text,itemUnitPrice text, itemTotalPrice text,itemBuyFrom text,itemBudgetMatchcode text)");
    }


    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // if the table exists then drop it
        db.execSQL("DROP TABLE IF EXISTS budgetDetailsTable");
        db.execSQL("DROP TABLE IF EXISTS ItemDetailsTable");
        onCreate(db);
    }

    //METHOD TO INSERT DATA
    public boolean insertItemDetails( Integer one, String itemName, String itemQuantity, String itemUnitPrice, String itemTotalPrice, String itemBuyFrom,String itemBudgetMatchcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO ItemDetailsTable (itemName, itemQuantity, itemUnitPrice,itemTotalPrice,itemBuyFrom,itemBudgetMatchcode)" +
                " VALUES ('" + itemName + "','" + itemQuantity + "','" + itemUnitPrice + "','" + itemTotalPrice + "','" + itemBuyFrom + "','" + itemBudgetMatchcode + "');");
        return true;
    }
public int  countItems(String itemBudgetMatchcode){
        SQLiteDatabase db = this.getReadableDatabase();
//hard-coded SQL-select command with no arguments
        String mySQL="SELECT COUNT(*) AS Total FROM ItemDetailsTable WHERE itemBudgetMatchcode = '"+itemBudgetMatchcode+"'";
        Cursor c1 = db.rawQuery(mySQL, null);
        int index = c1.getColumnIndex("Total");
//advance to the next record (first rec. if necessary)
        c1.moveToNext();

    int theTotal= c1.getInt(index);
   return theTotal;
}
    public double  countItemTotal(String itemBudgetMatchcode){
        SQLiteDatabase db = this.getReadableDatabase();
//hard-coded SQL-select command with no arguments
        String mySQL="SELECT SUM(itemTotalPrice) AS Total FROM ItemDetailsTable WHERE itemBudgetMatchcode = '"+itemBudgetMatchcode+"'";
        Cursor c1 = db.rawQuery(mySQL, null);
        int index = c1.getColumnIndex("Total");
//advance to the next record (first rec. if necessary)
        c1.moveToNext();

        double theTotal= c1.getInt(index);
        return theTotal;
    }

    //GETTING ALL DATA
    public ArrayList getAllItems(String itemBudgetMatchcode) {
        ArrayList<HashMap<String, String>> array_list = new ArrayList<HashMap<String, String>>();
        hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM ItemDetailsTable WHERE itemBudgetMatchcode = '" + itemBudgetMatchcode + "' ORDER BY `idI` DESC  ", null);
        res.moveToFirst();
        while(res.isAfterLast() == false){

            String itemNam = res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemName));
            String bfrom = res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemBuyFrom));
            String unitp =res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemUnitPrice));
            String qunatity = res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemQuantity));
            String totalp =res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemTotalPrice));
            String selectedCode = res.getString(res.getColumnIndex("idI"));

            HashMap<String,String> temp=new HashMap<String, String>();
            temp.put(FIRST_COLUMN, itemNam);
            temp.put(SECOND_COLUMN,bfrom);
            temp.put(THIRD_COLUMN, unitp);
            temp.put(FOURTH_COLUMN, qunatity);
            temp.put(FIRTH_COLUMN, totalp);
            temp.put(SIX_COLUMN, selectedCode);

            //add hashmap to arraylist
            array_list.add(temp);

            res.moveToNext(); }
        return array_list;
    }

    //GETTING ALL DATA
    public ArrayList getAllBudget( String itemBudgetMatchcode) {
        ArrayList<HashMap<String, String>> array_list = new ArrayList<HashMap<String, String>>();
        hp = new HashMap();        HashMap<String,String> temp=new HashMap<String, String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM budgetDetailsTable WHERE itemBudgetMatchcode = '" + itemBudgetMatchcode + "'", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){

            String count = String.valueOf(countItems(itemBudgetMatchcode));
            String budgetName = res.getString(res.getColumnIndex("budgetName"));
            String initialPrice= res.getString(res.getColumnIndex("initialPrice"));
            String totalPrice =res.getString(res.getColumnIndex("totalPrice"));
            String deference = res.getString(res.getColumnIndex("deference"));
            String alarmDate =res.getString(res.getColumnIndex("alarmDate"));
            String notes = res.getString(res.getColumnIndex("notes"));
            String dateCreated =res.getString(res.getColumnIndex("dateCreated"));
                   itemBudgetMatchcode = res.getString(res.getColumnIndex("itemBudgetMatchcode"));


            temp.put(FIRST_COLUMN, budgetName);
            temp.put(SECOND_COLUMN,initialPrice);
            temp.put(THIRD_COLUMN, totalPrice);
            temp.put(FOURTH_COLUMN, deference);
            temp.put(FIRTH_COLUMN, alarmDate);
            temp.put(SIX_COLUMN, notes);
            temp.put(SEVEN_COLUMN, dateCreated);
            temp.put(EIGHT_COLUMN, itemBudgetMatchcode);
            temp.put(NINE_COLUMN,count );

            //add hashmap to arraylist
            array_list.add(temp);


            res.moveToNext(); }

        return array_list;
    }

    //METHOD TO INSERT DATA
    public boolean insertBudgetDetails(String budgetName, String initialPrice, String totalPrice, String deference, String notes, String alarmDate, String dateCreated, String itemBudgetMatchcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("budgetName",budgetName);
        contentValues.put("initialPrice", initialPrice);
        contentValues.put("totalPrice", totalPrice);
        contentValues.put("deference", deference);
        contentValues.put("notes", notes);
        contentValues.put("alarmDate", alarmDate);
        contentValues.put("dateCreated", dateCreated);
        contentValues.put("itemBudgetMatchcode", itemBudgetMatchcode);

        db.insert("budgetDetailsTable", null, contentValues);
        return true;
    }

    //GETTING DATA
    public Cursor getItemData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM ItemDetailsTable WHERE idI="+id+"", null );
        return res;
    }

    //GETTING DATA
    public Cursor getBudgetData(String budegtName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM budgetDetailsTable WHERE idI="+budegtName+"", null );
        return res;
    }







    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows; }

    //UPDATING DATA
    public boolean updateItemDetailsTable (
            Integer id, String itemName, String itemQuantity, String itemUnitPrice, String itemTotalPrice,String itemBuyFrom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemName",itemName);
        contentValues.put("itemQuantity", itemQuantity);
        contentValues.put("itemUnitPrice", itemUnitPrice);
        contentValues.put("itemTotalPrice", itemTotalPrice);
        contentValues.put("itemBuyFrom", itemBuyFrom);
        db.update("ItemDetailsTable", contentValues, "idI = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    //UPDATING DATA
    public boolean updatebudgetDetailsTable(
            String budgetName, String initialPrice, String totalPrice, String deference, String notes, String alarmDate, String dateCreated, String itemBudgetMatchcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("budgetName",budgetName);
        contentValues.put("initialPrice", initialPrice);
        contentValues.put("totalPrice", totalPrice);
        contentValues.put("deference", deference);
        contentValues.put("notes", notes);
        contentValues.put("alarmDate", alarmDate);
        contentValues.put("dateCreated ", dateCreated );
        contentValues.put("dateCreated ", dateCreated );
        db.update("budgetDetailsTable", contentValues, "budgetName = ? ", new String[]{budgetName});
        return true;
    }


    //DELETING DATA
    public void deleteItemDetails (Integer itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqldelete = "DELETE FROM ItemDetailsTable WHERE idI = '"+itemId+"'";
        db.execSQL(sqldelete);
    }



    //DELETING DATA
    public Integer deletebudgetDetailsTable (String budgetName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("budgetDetailsTable", "budgetName = ? ", new String[] { budgetName });
    }


    public void updatebudgetDetailsTableName(String s, String itemBudgetMatchcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("budgetName", s);
        db.update("budgetDetailsTable", cv, "itemBudgetMatchcode = ?", new String[]{itemBudgetMatchcode});
    }

    public void updatebudgetDetailsTableUnitPrice(String s, String diff, String totalPrice, String itemBudgetMatchcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("initialPrice",s);
        cv.put("deference",diff);
        cv.put("totalPrice", totalPrice);

        db.update("budgetDetailsTable", cv, "itemBudgetMatchcode = ?", new String[]{itemBudgetMatchcode});
    }

    public void deleteAllBudgetDetails(String itemBudgetMatchcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqldelete = "DELETE FROM budgetDetailsTable WHERE itemBudgetMatchcode = '"+itemBudgetMatchcode+"'";
        db.execSQL(sqldelete);
    }

    public void deleteAllItemDetails(String itemBudgetMatchcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqldelete = "DELETE FROM ItemDetailsTable WHERE itemBudgetMatchcode = '"+itemBudgetMatchcode+"'";
        db.execSQL(sqldelete);
    }

    public  ArrayList getSavedBudgets() {

        ArrayList<HashMap<String, String>> array_list = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> temp=new HashMap<String, String>();
        hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM budgetDetailsTable  ORDER BY `idB` DESC ", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            res.moveToNext();

            String budgetName = res.getString(res.getColumnIndex("budgetName"));
            String initialPrice= res.getString(res.getColumnIndex("initialPrice"));
            String totalPrice =res.getString(res.getColumnIndex("totalPrice"));
            String deference = res.getString(res.getColumnIndex("deference"));
            String alarmDate =res.getString(res.getColumnIndex("alarmDate"));
            String notes = res.getString(res.getColumnIndex("notes"));
            String dateCreated =res.getString(res.getColumnIndex("dateCreated"));
            String itemBudgetMatchcode = res.getString(res.getColumnIndex("itemBudgetMatchcode"));

          //  String count = String.valueOf(countItems(itemBudgetMatchcode));


            temp.put(FIRST_COLUMN, budgetName);
            temp.put(SECOND_COLUMN,initialPrice);
            temp.put(THIRD_COLUMN, totalPrice);
            temp.put(FOURTH_COLUMN, deference);
            temp.put(FIRTH_COLUMN, alarmDate);
            temp.put(SIX_COLUMN, notes);
            temp.put(SEVEN_COLUMN, dateCreated);
            temp.put(EIGHT_COLUMN, itemBudgetMatchcode);
         //   temp.put(NINE_COLUMN, count);

            //add hashmap to arraylist
            array_list.add(temp);
            res.moveToNext();
            }

        return array_list;

    }
} //////////////END CLASS///////////////