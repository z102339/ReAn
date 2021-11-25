package org.jack.ReAt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DbUtils {
    private static MyDbHelper myDbHelper;
    public static void initDbHelper(Context context){
        myDbHelper = new MyDbHelper(context);
    }

    public static  <T> T getWritableDataBase(Class<T> clazz){
        try {
           Constructor<T> constructor= clazz.getConstructor(SQLiteDatabase.class);
           constructor.setAccessible(true);
            return constructor.newInstance(myDbHelper.getWritableDatabase());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getReadableDataBase(Class<T> clazz){
        try {
            Constructor<T> constructor= clazz.getConstructor(SQLiteDatabase.class);
            constructor.setAccessible(true);
            return constructor.newInstance(myDbHelper.getReadableDatabase());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
