package org.jack.ReAt;

import android.app.Application;

import org.jack.ReAt.db.DbUtils;

public class ReAtApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbUtils.initDbHelper(this);
    }
}
