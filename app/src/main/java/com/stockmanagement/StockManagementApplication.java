package com.stockmanagement;

import android.app.Application;
import android.content.Context;


public class StockManagementApplication extends Application {

    public static StockManagementApplication application;

    public StockManagementApplication() {
        application = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static StockManagementApplication getApp() {
        if (application == null) {
            application = new StockManagementApplication();
        }
        return application;
    }

    public static Context getAppContext() {
        if (application == null) {
            application = new StockManagementApplication();
        }
        return application;
    }
}
