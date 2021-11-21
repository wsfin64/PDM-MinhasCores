package com.example.minhascores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BancoHelper extends SQLiteOpenHelper {
    public BancoHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "dados", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table cores(" +
                "id integer primary key autoincrement," +
                "descricao text," +
                "red integer," +
                "green integer," +
                "blue integer)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table cores");
        this.onCreate(db);
    }


}
