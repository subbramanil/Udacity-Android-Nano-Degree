package com.udacity.learning.portfolio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class SimplePortfolioActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SimplePortfolioActivity.class.getSimpleName();

    //region Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_portfolio);

        // Approach: 1
        // 1. Get the view components. ex., Button
        Button button1 = (Button) findViewById(R.id.appBtn1);

        //2. Set the listener
        button1.setOnClickListener(this);
    }

    //endregion


    //region Event Listeners

    // Approach: 2
    // Set the event handler in the xml itself

    /**
     * method to handle the "onClick" event of the buttons
     *
     * @param view : Button that's been clicked
     */
    public void launchActivity(View view) {

        Button btn = (Button) view;

        Toast.makeText(this, "This button will launch " + btn.getText() + " App", Toast.LENGTH_SHORT).show();

        //Launch Different activities based on selection

        /*switch (view.getId()) {
            case R.id.appBtn1:
                break;
            case R.id.appBtn2:
                break;
            case R.id.appBtn3:
                break;
            case R.id.appBtn4:
                break;
            case R.id.appBtn5:
                break;
            case R.id.appBtn6:
                break;
        }*/
    }

    @Override
    public void onClick(View view) {
        // Approach 1
        // 3. Handle the onclick event based on the selection
        Button btn = (Button) view;
        Toast.makeText(this, "This button will launch " + btn.getText() + " App", Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.appBtn1:
                break;
            case R.id.appBtn2:
                break;
            case R.id.appBtn3:
                break;
            case R.id.appBtn4:
                break;
            case R.id.appBtn5:
                break;
            case R.id.appBtn6:
                break;
        }
    }

    //endregion
}
