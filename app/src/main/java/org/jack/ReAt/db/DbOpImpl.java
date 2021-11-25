package org.jack.ReAt.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbOpImpl implements DbOp{
    private SQLiteDatabase sqLiteDatabase;
    public DbOpImpl(SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase=sqLiteDatabase;
    }
    @Override
    public long insert(String table, String nullColumnHack, ContentValues values) {
        return sqLiteDatabase.insert(table,nullColumnHack,values);
    }

    @Override
    public int delete(String table, String whereClause, String[] whereArgs) {
        return sqLiteDatabase.delete(table, whereClause, whereArgs);
    }

    @Override
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return sqLiteDatabase.update(table, values, whereClause, whereArgs);
    }

    @Override
    public Cursor query(boolean distinct, String table, String[] columns,
                        String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy, String limit) {
        return sqLiteDatabase.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }


}
