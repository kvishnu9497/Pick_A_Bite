package com.example.vishnuk.pick_a_bite.OrderSummary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.vishnuk.pick_a_bite.BadgeCounters;
import com.example.vishnuk.pick_a_bite.MainActivity;
import com.example.vishnuk.pick_a_bite.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.vishnuk.pick_a_bite.TabbedActivity.mNotificationCounter;

public class Order_Summary extends AppCompatActivity{
    private List<OrderSummarySample> orderAdapterList = new ArrayList<>();
    OrderSummaryAdapter orderSummaryAdapter;
    Spinner counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        counter = (Spinner)findViewById(R.id.counter);
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

        LinearLayout edit = (LinearLayout) findViewById (R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order_Summary.this,OrderSummaryEdited.class);
                startActivity(intent);
            }
        });
    }

    private void add_Order_Summary_Items() {
        OrderSummarySample orderSummarySample = new OrderSummarySample("Chicken Kabab with Paneer Masala", "₹120");
        orderAdapterList.add(orderSummarySample);

        orderSummarySample = new OrderSummarySample("Chicken 65 with Arabic Fragment", "₹210" );
        orderAdapterList.add(orderSummarySample);

        orderSummarySample = new OrderSummarySample("American Cheese Chicken Gravy", "₹17500" );
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
}
