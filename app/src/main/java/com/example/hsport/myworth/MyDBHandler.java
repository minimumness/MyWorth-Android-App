package com.example.hsport.myworth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Huan Min Gan on 30/8/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    //Saving the user's information to this file called products.db.
    //The db extension tells Android there is a database file in here
    private static final String DATABASE_NAME = "MyWorth.db";

    //Initialise Current Accounts table
    public static final String TABLE_CURRENT_ACCOUNTS = "Current_Accounts";
    public static final String COLUMN_BANK_ID = "_id";
    public static final String COLUMN_BANK_NAME = "Name";
    public static final String COLUMN_BANK_CURRENCY = "Currency";
    public static final String COLUMN_BANK_AMOUNT = "Amount";
    public static final String COLUMN_BANK_UPDATED_DATE = "Date";

    //Initialise Bonds Table
    public static final String TABLE_BONDS = "Bonds";
    public static final String COLUMN_BOND_ID = "_id";
    public static final String COLUMN_BOND_NAME = "Name";
    public static final String COLUMN_BOND_TYPE = "Type";
    public static final String COLUMN_BOND_CURRENCY = "Currency";
    public static final String COLUMN_BOND_NOMINAL_VALUE = "Nominal_Value";
    public static final String COLUMN_BOND_MARKET_VALUE = "Market_Value";
    public static final String COLUMN_BOND_COUPON_RATE = "Coupon_Rate";
    public static final String COLUMN_BOND_EXPIRY_DATE = "Expiry_Date";
    public static final String COLUMN_BOND_UPDATED_DATE = "Date";

    //Initialise Fixed Deposits Table
    public static final String TABLE_FIXED_DEPOSITS = "Fixed Deposits";
    public static final String COLUMN_FIXED_DEPOSITS_ID = "_id";
    public static final String COLUMN_FIXED_DEPOSITS_NAME = "Name";
    public static final String COLUMN_FIXED_DEPOSITS_TYPE = "Type";
    public static final String COLUMN_FIXED_DEPOSITS_CURRENCY = "Currency";
    public static final String COLUMN_FIXED_DEPOSITS_VALUE = "Value";
    public static final String COLUMN_FIXED_DEPOSITS_INTEREST_RATE = "Interest_Rate";
    public static final String COLUMN_FIXED_DEPOSITS_EXPIRY_DATE = "Expiry_Date";
    public static final String COLUMN_FIXED_DEPOSITS_UPDATED_DATE = "Date";

    //Initialise Equities Table
    public static final String TABLE_EQUITIES = "Equities";
    public static final String COLUMN_EQUITIES_ID = "_id";
    public static final String COLUMN_EQUITIES_NAME = "Name";
    public static final String COLUMN_EQUITIES_CURRENCY = "Currency";
    public static final String COLUMN_EQUITIES_CURRENT_PRICE = "Current_Price";
    public static final String COLUMN_EQUITIES_NUMBER_OF_SHARES = "Number_of_Shares";
    public static final String COLUMN_EQUITIES_PRICE_OF_SHARES = "Price_of_Shares";
    public static final String COLUMN_EQUITIES_BOUGHT_ON_DATE = "Bought_on_Date";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //On first run of this, the tables needs to be created with the name and columns specified
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_CURRENT_ACCOUNTS + "(" +
                COLUMN_BANK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BANK_NAME + " TEXT, " +
                COLUMN_BANK_CURRENCY + " TEXT, " +
                COLUMN_BANK_AMOUNT + " DOUBLE(12,2), " +
                COLUMN_BANK_UPDATED_DATE + " TEXT " +");";
        db.execSQL(query);

        String query1 = "CREATE TABLE " + TABLE_BONDS + "(" +
                COLUMN_BOND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOND_NAME + " TEXT, " +
                COLUMN_BOND_TYPE + " TEXT, " +
                COLUMN_BOND_CURRENCY + " TEXT, " +
                COLUMN_BOND_NOMINAL_VALUE + " DOUBLE(12,2), " +
                COLUMN_BOND_MARKET_VALUE + " DOUBLE(12,2), " +
                COLUMN_BOND_COUPON_RATE + " DOUBLE(12,2), " +
                COLUMN_BOND_EXPIRY_DATE + " TEXT, " +
                COLUMN_BOND_UPDATED_DATE + " TEXT " +");";
        db.execSQL(query1);

        String query2 = "CREATE TABLE " + TABLE_EQUITIES + "(" +
                COLUMN_EQUITIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EQUITIES_NAME + " TEXT, " +
                COLUMN_EQUITIES_CURRENCY + " TEXT, " +
                COLUMN_EQUITIES_CURRENT_PRICE + " DOUBLE(12,2), " +
                COLUMN_EQUITIES_NUMBER_OF_SHARES + " DOUBLE(12,2), " +
                COLUMN_EQUITIES_PRICE_OF_SHARES + " DOUBLE(12,2), " +
                COLUMN_EQUITIES_BOUGHT_ON_DATE + " TEXT " +");";
        db.execSQL(query2);

        /*String query2 = "CREATE TABLE " + TABLE_FIXED_DEPOSITS + "(" +
                COLUMN_FIXED_DEPOSITS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIXED_DEPOSITS_NAME + " TEXT, " +
                COLUMN_FIXED_DEPOSITS_TYPE + " TEXT, " +
                COLUMN_FIXED_DEPOSITS_CURRENCY + " TEXT, " +
                COLUMN_FIXED_DEPOSITS_VALUE + " DOUBLE(12,2), " +
                COLUMN_FIXED_DEPOSITS_INTEREST_RATE + " TEXT, " +
                COLUMN_FIXED_DEPOSITS_EXPIRY_DATE + " TEXT, " +
                COLUMN_FIXED_DEPOSITS_UPDATED_DATE + " TEXT " +");";
        db.execSQL(query2);*/
    }

    //When database is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENT_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BONDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIXED_DEPOSITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUITIES);
        onCreate(db);
    }

    //***************Adding and deleting rows in the DB*************//
    public void DBaddCurrentAccount(Object_Account Bank){
        ContentValues values = new ContentValues();
        //Specifies column name and the value to put in
        values.put(COLUMN_BANK_NAME, Bank.get_BankName());
        values.put(COLUMN_BANK_CURRENCY, Bank.get_BankCurrency());
        values.put(COLUMN_BANK_AMOUNT, Bank.get_BankAmount());
        values.put(COLUMN_BANK_UPDATED_DATE, Bank.get_BankDate());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CURRENT_ACCOUNTS, null, values);
        db.close();
    }

    public void DBdeleteCurrentAccount(Object_Account Bank){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CURRENT_ACCOUNTS +" WHERE " + COLUMN_BANK_NAME + "='" + Bank.get_BankName() +
                "' AND " + COLUMN_BANK_AMOUNT + " =" + Bank.get_BankAmount() + ";");
    }

    public void DBaddBond(Object_Bond Bond){
        ContentValues values = new ContentValues();
        //Specifies column name and the value to put in
        values.put(COLUMN_BOND_NAME, Bond.get_BondName());
        values.put(COLUMN_BOND_TYPE, Bond.get_BondType());
        values.put(COLUMN_BOND_CURRENCY, Bond.get_BondCurrency());
        values.put(COLUMN_BOND_NOMINAL_VALUE, Bond.get_BondNominalValue());
        values.put(COLUMN_BOND_MARKET_VALUE, Bond.get_BondMarketValue());
        values.put(COLUMN_BOND_COUPON_RATE, Bond.get_BondCouponRate());
        values.put(COLUMN_BOND_EXPIRY_DATE, Bond.get_BondExpiryDate());
        values.put(COLUMN_BOND_UPDATED_DATE, Bond.get_BondUpdatedDate());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_BONDS, null, values);
        db.close();
    }

    public void DBdeleteBond(Object_Bond Bond){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BONDS +" WHERE " + COLUMN_BOND_NAME + "='" + Bond.get_BondName() +
                "';");
    }

    public void DBaddEquity(Object_Equities Equity){
        ContentValues values = new ContentValues();
        //Specifies column name and the value to put in
        values.put(COLUMN_EQUITIES_NAME, Equity.get_EquityName());
        values.put(COLUMN_EQUITIES_CURRENCY, Equity.get_EquityCurrency());
        values.put(COLUMN_EQUITIES_CURRENT_PRICE, Equity.get_EquityCurrentPrice());
        values.put(COLUMN_EQUITIES_NUMBER_OF_SHARES, Equity.get_EquityNumberofShares());
        values.put(COLUMN_EQUITIES_PRICE_OF_SHARES, Equity.get_EquityBoughtPrice());
        values.put(COLUMN_EQUITIES_BOUGHT_ON_DATE, Equity.get_EquityBoughtDate());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EQUITIES, null, values);
        db.close();
    }

    //***************Printing databases to activites*************//
    public String CAdatabaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CURRENT_ACCOUNTS + " WHERE 1 ORDER BY " + COLUMN_BANK_NAME;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);

        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_BANK_NAME))!= null){
                dbString += c.getString(c.getColumnIndex(COLUMN_BANK_NAME));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_BANK_CURRENCY));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_BANK_AMOUNT));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_BANK_UPDATED_DATE));
                dbString += ";";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public String CAtotal(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        //CHANGE THE QUERY
        String query = "SELECT " + COLUMN_BANK_CURRENCY + ","
                +  "SUM(" + COLUMN_BANK_AMOUNT + ") AS " + COLUMN_BANK_AMOUNT
                + " FROM " + TABLE_CURRENT_ACCOUNTS
                + " GROUP BY " + COLUMN_BANK_CURRENCY + ";";

        //String query = "SELECT CURRENCY, Sum(Amount) AS Amount FROM Current_Accounts GROUP BY Currency;";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);

        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_BANK_CURRENCY))!= null){
                dbString += c.getString(c.getColumnIndex(COLUMN_BANK_CURRENCY));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_BANK_AMOUNT));
                dbString += "\n";
            }
            c.moveToNext();
        }
        return dbString;
    }

    public String BondsdatabaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BONDS + " WHERE 1 ORDER BY " + COLUMN_BOND_NAME;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //dbString+=c.getCount();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_BOND_NAME)) != null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_BOND_NAME));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_BOND_TYPE));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_BOND_CURRENCY));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_BOND_NOMINAL_VALUE));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_BOND_MARKET_VALUE));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_BOND_COUPON_RATE));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_BOND_EXPIRY_DATE));
                dbString += ";";
            }
            c.moveToNext();
            db.close();
        }
        return dbString;
    }

    public String Bondstotal(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        //CHANGE THE QUERY
        String query = "SELECT " + COLUMN_BOND_CURRENCY + ","
                +  "SUM(" + COLUMN_BOND_MARKET_VALUE + ") AS " + COLUMN_BOND_MARKET_VALUE
                + " FROM " + TABLE_BONDS
                + " GROUP BY " + COLUMN_BOND_CURRENCY + ";";

        Cursor c = db.rawQuery(query, null);

        //Move to the first row in your results
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_BANK_CURRENCY))!= null){
                dbString += c.getString(c.getColumnIndex(COLUMN_BOND_CURRENCY));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_BOND_MARKET_VALUE));
                dbString += "\n";
            }
            c.moveToNext();
        }
        return dbString;
    }

    public String EquitiesdatabaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_EQUITIES + " WHERE 1 ORDER BY " + COLUMN_EQUITIES_NAME;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //dbString+=c.getCount();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_BOND_NAME)) != null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_EQUITIES_NAME));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_EQUITIES_CURRENCY));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_EQUITIES_CURRENT_PRICE));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_EQUITIES_NUMBER_OF_SHARES));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_EQUITIES_PRICE_OF_SHARES));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex(COLUMN_EQUITIES_BOUGHT_ON_DATE));
                dbString += ";";
            }
            c.moveToNext();
            db.close();
        }
        return dbString;
    }
}