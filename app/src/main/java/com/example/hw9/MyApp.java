package com.example.hw9;

import android.app.Application;

import com.lidroid.xutils.DbUtils;
import com.orhanobut.logger.Logger;


public class MyApp extends Application{

    private static MyApp mInstance;
    private DbUtils db;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("HW9");
        mInstance = this;
    }

    public static MyApp getmInstance() {
        return mInstance;
    }

    public DbUtils getDb() {
        if (db == null) {
            DbUtils.DaoConfig daoConfig = new DbUtils.DaoConfig(this);
            daoConfig.setDbName("datas.db");
            daoConfig.setDbVersion(1);
            db = DbUtils.create(daoConfig);
        }
        return db;
    }
}
