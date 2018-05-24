package com.example.vishnuk.pick_a_bite;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import static com.example.vishnuk.pick_a_bite.TabbedActivity.mNotificationCounter;

public class Order_Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*TextView itemname = (TextView) findViewById(R.id.itemname);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensed-Regular.ttf");
        itemname.setTypeface(typeface);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        // Create a condition (hide it if the count < 0)
        if (mNotificationCounter >= 0) {
            BadgeCounters.update(this,
                    menu.findItem(R.id.cart_badge_in_tabview),
                    R.drawable.ic_shopping_cart_36dp,
                    BadgeCounters.BadgeColor.BLUE,
                    mNotificationCounter);
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

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
