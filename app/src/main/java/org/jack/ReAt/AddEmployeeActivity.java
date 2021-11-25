package org.jack.ReAt;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jack.ReAt.bean.Person;
import org.jack.ReAt.db.DbUtils;
import org.jack.ReAt.db.EmTbOp;

public class AddEmployeeActivity extends AppCompatActivity {

    private String gender="男";
    private EditText name,age;
    private EmTbOp writableDataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        RadioGroup radioGroup = findViewById(R.id.gender);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male:
                        gender = "男";
                        break;
                    case R.id.female:
                        gender = "女";
                        break;
                }
            }
        });

        findViewById(R.id.add_to_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname=name.getText().toString().trim();
                String sage=age.getText().toString().trim();
                if (TextUtils.isEmpty(sname)) {
                    Toast.makeText(AddEmployeeActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(sage)) {
                    Toast.makeText(AddEmployeeActivity.this, "年龄不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!sage.matches("\\d+")) {
                    Toast.makeText(AddEmployeeActivity.this, "年龄必须是数字", Toast.LENGTH_SHORT).show();
                }
                writableDataBase = DbUtils.getWritableDataBase(EmTbOp.class);
                Person person = new Person();
                person.setName(sname);
                person.setAge(Integer.valueOf(sage));
                person.setGender(gender);
                long row=writableDataBase.insert(person);
                String opCode;
                if (row < 0) {
                    opCode = "插入数据库失败";
                } else {
                    opCode = "插入数据成功";
                }
                Toast.makeText(AddEmployeeActivity.this, opCode, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onDestroy() {
        if (writableDataBase!=null) {
            writableDataBase.close();
        }
        super.onDestroy();
    }
}
