package org.jack.ReAt.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.jack.ReAt.bean.Person;

import java.util.ArrayList;
import java.util.List;

public class EmTbOp{
    public static final String EM_TB_NAME = "employee";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String GENDER = "gender";

    private DbOp dbOp;
    public EmTbOp(SQLiteDatabase sqLiteDatabase) {
        this.dbOp = new DbOpImpl(sqLiteDatabase);
    }

    public long insert(Person person) {
        ContentValues contentValues = new ContentValues();
//        contentValues.put(ID, person.getId());
        contentValues.put(NAME,person.getName());
        contentValues.put(AGE,person.getAge());
        contentValues.put(GENDER,person.getGender());
        return dbOp.insert(EM_TB_NAME, null, contentValues);
    }

    public int delete(){
        return dbOp.delete(EM_TB_NAME, null, null);
    }

    public List<Person> query() {
        List<Person> personList=null;
        Cursor cursor = dbOp.query(false, EM_TB_NAME, null, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (personList == null) {
                personList = new ArrayList<>();
            }
            int id=cursor.getInt(cursor.getColumnIndex(ID));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String gender = cursor.getString(cursor.getColumnIndex(GENDER));
            int age = cursor.getInt(cursor.getColumnIndex(AGE));
            Person person = new Person();
            person.setId(id);
            person.setName(name);
            person.setAge(age);
            person.setGender(gender);
            personList.add(person);
            cursor.moveToNext();
        }
        cursor.close();
        return personList;
    }

    public void close() {
        dbOp.close();
    }
}
