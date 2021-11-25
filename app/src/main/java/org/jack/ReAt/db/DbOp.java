package org.jack.ReAt.db;

import android.content.ContentValues;
import android.database.Cursor;

public interface DbOp {
    long insert(String table, String nullColumnHack, ContentValues values);
    int delete(String table, String whereClause, String[] whereArgs);
    int update(String table, ContentValues values, String whereClause, String[] whereArgs);

    Cursor query(boolean distinct, String table, String[] columns,
                 String selection, String[] selectionArgs, String groupBy,
                 String having, String orderBy, String limit);
}
