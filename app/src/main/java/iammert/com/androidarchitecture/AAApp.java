package iammert.com.androidarchitecture;

import android.app.Application;

import iammert.com.androidarchitecture.di.AppComponent;
import iammert.com.androidarchitecture.di.AppModule;
import iammert.com.androidarchitecture.di.DaggerAppComponent;

/**
 * Created by mertsimsek on 20/05/2017.
 */

public class AAApp extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    private void initializeComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
