package com.example.agricultureproducts.cart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CartDBOpenHelper extends SQLiteOpenHelper {
    public CartDBOpenHelper(@Nullable Context context, int version) {
        super(context, "ncpshop.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cart(cart_id INTEGER PRIMARY KEY AUTOINCREMENT,g_id,g_photo,g_name,g_type,g_price,g_num,g_check)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
