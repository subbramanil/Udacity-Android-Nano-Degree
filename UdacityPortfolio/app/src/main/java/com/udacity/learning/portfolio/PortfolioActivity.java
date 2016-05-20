package com.udacity.learning.portfolio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PortfolioActivity extends AppCompatActivity implements AppsAdapter.AppItemClickListener {

    private static final String TAG = PortfolioActivity.class.getSimpleName();
    private String arrayOfApps[] = {"Popular Movies", "Stock hawk", "Build it Bigger",
            "Make your app Material", "Go Ubiquitous", "Capstone"};


    //region Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        // Get View components
        RecyclerView appsListView = (RecyclerView) findViewById(R.id.apps_btn_list);

        if (appsListView != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            appsListView.setLayoutManager(layoutManager);
            List<String> appList = new ArrayList<>();
            Collections.addAll(appList, arrayOfApps);
            AppsAdapter appsAdapter = new AppsAdapter(this, this, appList);
            appsListView.setAdapter(appsAdapter);
        }
    }

    //endregion


    //region Event Listeners
    @Override
    public void onAppBtnClick(View view, int position) {
        Toast.makeText(this, "This button will launch " + arrayOfApps[position] + "App", Toast.LENGTH_SHORT).show();
    }

    //endregion
}
