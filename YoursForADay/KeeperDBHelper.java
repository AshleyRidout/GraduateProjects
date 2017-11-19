package com.example.ashleyridout.yoursforaday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by Ashley.Ridout on 9/25/2017.
 */

public class KeeperDBHelper extends SQLiteOpenHelper {

    public static final String KEEPER_ITEM_TABLE_NAME = "keeper_items";
    public static final String PRIMARY_KEY_NAME = "_id";
    public static final String FIELD1_NAME = "itemName";
    public static final String FIELD2_NAME = "itemPrice";
    public static final String FIELD3_NAME = "itemQuantity";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KeeperItems.db";


    public KeeperDBHelper(Context context) {
        // A database exists, named DATABASE_NAME, with TABLE_SPECIFICATIONS
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // create keeper_items table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_SPECIFICATIONS =
                // form: "CREATE TABLE keeper_items(_id INTEGER PRIMARY KEY, itemName TEXT,
                // itemPrice REAL, itemQuantity INTEGER)";
                "CREATE TABLE " + KEEPER_ITEM_TABLE_NAME + "(" +
                        PRIMARY_KEY_NAME + " INTEGER PRIMARY KEY, "
                        + FIELD1_NAME + " TEXT, "
                        + FIELD2_NAME + " REAL, "
                        + FIELD3_NAME + " INTEGER)";
        db.execSQL(TABLE_SPECIFICATIONS);
    }

    // drop table if needed from upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + KEEPER_ITEM_TABLE_NAME);
    }

    /**
     * @intent add keeper items name, price and quantity to keeper items table
     * //there is no need to input the id as leaving this field blank will cause
     * SQLite to autoincrement without causing problems associated with coding autoincrement
     * @preconditions keeper_items table exists
     * @postconditions values are inserted into keeper items table
     */
    public void addKeeperItem(final KeeperItem kItem) {
        // Create content values
        ContentValues values = new ContentValues();

        //values.put(PRIMARY_KEY_NAME, kItem.getID());
        values.put(FIELD1_NAME, kItem.getName());
        values.put(FIELD2_NAME, kItem.getPrice());
        values.put(FIELD3_NAME, kItem.getQuantity());

        // get the writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //insert values into database
        db.insert(KEEPER_ITEM_TABLE_NAME, null, values);

        //close the database
        db.close();
    }

    /**
     * @return kItem is all details for the inputted KeeperItem name
     * @intent retrieve keeper item info based on keeper item's name
     * @preconditions keeper_items table exists
     * @postconditions values are set for current id, name, price and quantity
     */
    public ArrayList<String> findKeeperItem(String name, Double price) {
        ArrayList<String> items = new ArrayList<String>();
        String query = "SELECT * FROM " + KEEPER_ITEM_TABLE_NAME + " WHERE "
                + FIELD1_NAME + " = \"" + name + "\" AND " + FIELD2_NAME + " <= \"" + price + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        //KeeperItem kItem = new KeeperItem();

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int iId = c.getInt(c.getColumnIndex("_id"));
                    String iName = c.getString(c.getColumnIndex("itemName"));
                    double iPrice = c.getDouble(c.getColumnIndex("itemPrice"));
                    items.add("ID: " + iId + "   Name: " + iName + "   Price: $" + iPrice);
                } while (c.moveToNext());
            }
            c.close();
            db.close();
        }
        return items;
    }

    public Cursor getAllData() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + KEEPER_ITEM_TABLE_NAME, null);
        return cursor;
    }
}
