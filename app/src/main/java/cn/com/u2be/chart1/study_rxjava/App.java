package cn.com.u2be.chart1.study_rxjava;

import android.app.Application;

import org.xutils.x;

/**
 * Created by alek on 2016/6/27.
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
