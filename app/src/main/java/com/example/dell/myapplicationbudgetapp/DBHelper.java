package com.example.dell.myapplicationbudgetapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dell on 2/3/2017.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyBUDGET";
    public static final String CONTACTS_TABLE_NAME = "itemDetails";
    public static final String CONTACTS_COLUMN_itemCode = "itemCode";
    public static final String CONTACTS_COLUMN_itemName = "itemName";
    public static final String CONTACTS_COLUMN_itemQuantity = "itemQuantity";
    public static final String CONTACTS_COLUMN_itemUnitPrice = "itemUnitPrice";
    public static final String CONTACTS_COLUMN_itemTotalPrice = "itemTotalPrice";
    public static final String CONTACTS_COLUMN_itemBuyFrom = "itemBuyFrom";

    private HashMap hp;

    public DBHelper(Context context, String DATABASE_NAME) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override public void onCreate(SQLiteDatabase db) {
    // creating database if it does not exist
    db.execSQL("create table budgetDetailsTable " + "(id integer primary key,budgetName text initialPrice text,totalPrice text, deference text,notes text, alarmDate text, dateCreated text)");
        db.execSQL("create table ItemDetailsTable " + "(id integer primary key ,itemName text,itemQuantity text,itemUnitPrice text, itemTotalPrice text,itemBuyFrom text,itemCode text)");
    }


    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // if the table exists then drop it
            db.execSQL("DROP TABLE IF EXISTS budgetDetailsTable");
        db.execSQL("DROP TABLE IF EXISTS ItemDetailsTable");
            onCreate(db);
    }

    //METHOD TO INSERT DATA
    public boolean insertItemDetails( String itemName, String itemQuantity, String itemUnitPrice, String itemTotalPrice, String itemBuyFrom,String itemCode) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("itemName",itemName);
    contentValues.put("itemQuantity", itemQuantity);
    contentValues.put("itemUnitPrice", itemUnitPrice);
    contentValues.put("itemTotalPrice", itemTotalPrice);
    contentValues.put("itemBuyFrom", itemBuyFrom);
        contentValues.put("itemCode", itemCode);
    db.insert("ItemDetailsTable", null, contentValues);
    return true;
}

    //GETTING ALL DATA
    public ArrayList getAllItems(String itemCod) {
        ArrayList array_list = new ArrayList();
        hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from ItemDetailsTable ", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemName)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemBuyFrom)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemUnitPrice)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemQuantity)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemTotalPrice)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemCode)));
            res.moveToNext(); }
        return array_list;
    }
    //METHOD TO INSERT DATA
    public boolean insertBudgetDetails (String budgetName, String initialPrice, String totalPrice, String deference,String notes,String alarmDate,String dateCreated ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("budgetName",budgetName);
        contentValues.put("initialPrice", initialPrice);
        contentValues.put("totalPrice", totalPrice);
        contentValues.put("deference", deference);
        contentValues.put("notes", notes);
        contentValues.put("alarmDate", alarmDate);
        contentValues.put("dateCreated ", dateCreated);
        db.insert("budgetDetailsTable", null, contentValues);
        return true;
    }

    //GETTING DATA
    public Cursor getItemData(int id){
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor res = db.rawQuery( "select * from ItemDetailsTable where idI="+id+"", null );
    return res;
}

    //GETTING DATA
    public Cursor getBudgetData(String budegtName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from budgetDetailsTable where idI="+budegtName+"", null );
        return res;
    }



    //GETTING ALL DATA
    public ArrayList getAllBudget() {
        ArrayList array_list = new ArrayList();
        hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from budgetDetailsTable", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){ array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_itemName)));
            res.moveToNext(); }
        return array_list;
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
        db.update("ItemDetailsTable", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
        }

    //UPDATING DATA
    public boolean updatebudgetDetailsTable (
            String budgetName, String initialPrice, String totalPrice, String deference,String notes,String alarmDate,String dateCreated ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("budgetName",budgetName);
        contentValues.put("initialPrice", initialPrice);
        contentValues.put("totalPrice", totalPrice);
        contentValues.put("deference", deference);
        contentValues.put("notes", notes);
        contentValues.put("alarmDate", alarmDate);
        contentValues.put("dateCreated ", dateCreated );
        db.update("budgetDetailsTable", contentValues, "budgetName = ? ", new String[]{budgetName});
        return true;
    }

    //DELETING DATA
public Integer deleteItemDetailsTable (Integer id) {
    SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("ItemDetailsTable", "id = ? ", new String[] { Integer.toString(id) });
        }

    //DELETING DATA
    public Integer deletebudgetDetailsTable (String budgetName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("budgetDetailsTable", "budgetName = ? ", new String[] { budgetName });
    }



} //////////////END CLASS///////////////