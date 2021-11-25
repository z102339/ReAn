package org.jack.ReAt;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jack.ReAt.db.DbUtils;
import org.jack.ReAt.db.EmTbOp;

public class AddEmployeeActivity extends AppCompatActivity {

    private String gender;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
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

        EmTbOp writableDataBase = DbUtils.getWritableDataBase(EmTbOp.class);
//        writableDataBase.insert()
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
