package org.jack.ReAt;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yanzhenjie.recyclerview.OnItemLongClickListener;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import org.jack.ReAt.bean.Person;
import org.jack.ReAt.db.DbUtils;
import org.jack.ReAt.db.EmTbOp;

import java.util.List;

public class EmployeeDisplayActivity extends AppCompatActivity {

    private SwipeRecyclerView recyclerView;
    private EmTbOp readableDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_display);
        recyclerView = findViewById(R.id.emp_list);
        readableDataBase = DbUtils.getReadableDataBase(EmTbOp.class);
        EmployeeAdapter employeeAdapter = new EmployeeAdapter();
        List<Person> personList = readableDataBase.query();
        employeeAdapter.setDataset(personList);
        recyclerView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int adapterPosition) {
                Toast.makeText(EmployeeDisplayActivity.this, adapterPosition+"被长按了", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
                SwipeMenuItem swipeMenuItem = new SwipeMenuItem(EmployeeDisplayActivity.this);
                swipeMenuItem.setBackground(R.drawable.delete);
                swipeMenuItem.setText("删除");
                swipeMenuItem.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//                swipeMenuItem.setWidth(70);
                swipeMenuItem.setTextSize(14);
                swipeMenuItem.setTextColorResource(R.color.white);
                rightMenu.addMenuItem(swipeMenuItem);
            }
        });

        OnItemMenuClickListener mItemMenuClickListener = new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();

                // 左侧还是右侧菜单：
                int direction = menuBridge.getDirection();
                // 菜单在Item中的Position：
//                int menuPosition = menuBridge.getPosition();
                Toast.makeText(EmployeeDisplayActivity.this, position+"删除被点击了", Toast.LENGTH_SHORT).show();

            }
        };
        recyclerView.setOnItemMenuClickListener(mItemMenuClickListener);

//        recyclerView.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new EmItemDecoration());
        recyclerView.setAdapter(employeeAdapter);

    }
}