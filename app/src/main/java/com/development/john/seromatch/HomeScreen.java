package com.development.john.seromatch;

import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class HomeScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        TabHost mTabHost = (TabHost)findViewById(R.id.tabHost);
        mTabHost.setup();
        //Lets add the first Tab
        TabHost.TabSpec mSpec = mTabHost.newTabSpec("First Tab");
        mSpec.setContent(R.id.first_Tab);
        mSpec.setIndicator("First Tab");
        mTabHost.addTab(mSpec);
        //Lets add the second Tab
        mSpec = mTabHost.newTabSpec("Second Tab");
        mSpec.setContent(R.id.second_Tab);
        mSpec.setIndicator("Second Tab");
        mTabHost.addTab(mSpec);
        //Lets add the third Tab
        mSpec = mTabHost.newTabSpec("Third Tab");
        mSpec.setContent(R.id.third_Tab);
        mSpec.setIndicator("Third Tab");
        mTabHost.addTab(mSpec);
    }


}
