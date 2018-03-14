package com.johnstrack.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *  Created by John on 3/14/2018 at 2:41 PM.
 */

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;

    public StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        updateMyDatabase (db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        updateMyDatabase (db, 0, DB_VERSION);
    }

    private static void insertDrink (SQLiteDatabase db, String name, String description, int resourceId) {

        ContentValues drinkValues = new ContentValues();
        drinkValues.put("name", name);
        drinkValues.put("description", description);
        drinkValues.put("image_resource_id", resourceId);
        db.insert("drink", null, drinkValues);
    }

    private void updateMyDatabase (SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE drink (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT, "
                    + "description TEXT, "
                    + "image_resource_id INTEGER);");

            insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertDrink(db, "Cappuccino", "Espresso, hot milk, and steamed-milk foam", R.drawable.cappuccino);
            insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);
        }

        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE drink ADD COLUMN favorite NUMERIC;");
        }
    }
}