package com.example.vishnuk.pick_a_bite;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.vishnuk.pick_a_bite.TabbedActivity.mNotificationCounter;

public class Order_Summary extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private List<OrderSummarySample> orderAdapterList = new ArrayList<>();
    OrderSummaryAdapter orderSummaryAdapter;

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

        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        // set a LinearLayoutManager with default orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView

        orderSummaryAdapter = new OrderSummaryAdapter(orderAdapterList,this);
        recyclerView.setAdapter(orderSummaryAdapter);
        add_Order_Summary_Items();
    }

    private void add_Order_Summary_Items() {
        OrderSummarySample orderSummarySample = new OrderSummarySample("Chicken Kabab with Paneer Masala", 100);
        orderAdapterList.add(orderSummarySample);

        orderSummarySample = new OrderSummarySample("Chicken 65 with Arabic Fragment", 120 );
        orderAdapterList.add(orderSummarySample);

        orderSummarySample = new OrderSummarySample("American Cheese Chicken Gravy", 179 );
        orderAdapterList.add(orderSummarySample);


        orderSummaryAdapter.notifyDataSetChanged();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "CardView clicked event ", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
