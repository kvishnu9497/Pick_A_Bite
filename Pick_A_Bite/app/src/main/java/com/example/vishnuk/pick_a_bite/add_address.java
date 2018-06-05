package com.example.vishnuk.pick_a_bite;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vishnuk.pick_a_bite.OrderSummary.Order_Summary;

import static com.example.vishnuk.pick_a_bite.TabbedActivity.mNotificationCounter;

public class add_address extends AppCompatActivity {

    private int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hometoolbar, menu);
        if (TabbedActivity.mNotificationCounter >= 0 ) {
            BadgeCounters.update(this,
                    menu.findItem(R.id.ic_shopping_cart),
                    R.drawable.ic_shopping_cart_36dp,
                    BadgeCounters.BadgeColor.BLUE,
                    TabbedActivity.mNotificationCounter);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.ic_shopping_cart) {
            if (TabbedActivity.mNotificationCounter >= 0 ) {
                Intent intent = new Intent(this,Order_Summary.class);
                startActivity(intent);
            }
        }
        if (id == android.R.id.home)
        {
            // todo: goto back activity from here
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void perform_action(View view) {
    }
}
