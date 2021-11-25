package org.jack.ReAt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jack.ReAt.bean.Person;
import org.jack.ReAt.db.DbUtils;
import org.jack.ReAt.db.EmTbOp;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Button button,button1;
    private EmTbOp emTbOp,emTbOpR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emTbOp=DbUtils.getWritableDataBase(EmTbOp.class);
        emTbOpR = DbUtils.getReadableDataBase(EmTbOp.class);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Person> person = emTbOpR.query();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AddEmployeeActivity.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Person> person = emTbOpR.query();
                if (person!=null) {
                    Log.e(TAG, person.toString());
                }else{
                    Toast.makeText(MainActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void delete(View view) {
        if (emTbOp.delete()==0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "删除成功1", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {

    }


    @Override
    protected void onDestroy() {
        emTbOp.close();
        emTbOpR.close();
        super.onDestroy();
    }
}