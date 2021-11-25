package org.jack.ReAt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="office.db";
    private static final int VERSION=3;
    public MyDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null,VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("MyDbHelper", "onCreate: " );
        String sql="create table if not exists "+ EmTbOp.EM_TB_NAME +
                "("+EmTbOp.ID+" integer primary key autoincrement," +
                EmTbOp.NAME+" text not null," +
                EmTbOp.AGE+" integer not null," +
                EmTbOp.GENDER+" text default \"男\");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists " + EmTbOp.EM_TB_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }





}
