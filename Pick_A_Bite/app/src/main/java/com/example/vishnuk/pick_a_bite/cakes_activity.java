package com.example.vishnuk.pick_a_bite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class cakes_activity extends AppCompatActivity {

    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        EditText editText = (EditText) findViewById(R.id.editText) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences badge_count;
        badge_count = getSharedPreferences("Saving badge count", Context.MODE_PRIVATE);
        //set the sharedpref
        SharedPreferences.Editor editor = badge_count.edit();
        editor.putInt("counter", counter);
        editor.apply();
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences badge_count;
        badge_count = getSharedPreferences("Saving badge count", Context.MODE_PRIVATE);
        //get the sharepref
        counter = badge_count.getInt("counter", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        TabbedActivity.mNotificationCounter = counter;
        // Create a condition (hide it if the count < 0)
        if (TabbedActivity.mNotificationCounter >= 0) {
            BadgeCounters.update(this,
                    menu.findItem(R.id.cart_badge_in_tabview),
                    R.drawable.ic_shopping_cart_36dp,
                    BadgeCounters.BadgeColor.BLUE,
                    counter);
        } else {
            BadgeCounters.hide(menu.findItem(R.id.cart_badge_in_tabview));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case R.id.cart_badge_in_tabview:
                counter++;
                TabbedActivity.mNotificationCounter = counter;
                BadgeCounters.update(item, counter);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
