package com.example.vishnuk.pick_a_bite.OrderSummary;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vishnuk.pick_a_bite.BadgeCounters;
import com.example.vishnuk.pick_a_bite.MainActivity;
import com.example.vishnuk.pick_a_bite.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.vishnuk.pick_a_bite.TabbedActivity.mNotificationCounter;

public class OrderSummaryEdited extends AppCompatActivity {
    private List<OrderSummarySample> orderAdapterList = new ArrayList<>();
    OrderSummaryAdapterEdited orderSummaryAdapterEdited;
    Spinner counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary_edited);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        counter = (Spinner)findViewById(R.id.counter);
        /*TextView itemname = (TextView) findViewById(R.id.itemname);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensed-Regular.ttf");
        itemname.setTypeface(typeface);*/

        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_edit);
        recyclerView.setHasFixedSize(true);
        // set a LinearLayoutManager with default orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView

        orderSummaryAdapterEdited = new OrderSummaryAdapterEdited(orderAdapterList,this);
        recyclerView.setAdapter(orderSummaryAdapterEdited);
        add_Order_Summary_Items();

        LinearLayout done = (LinearLayout) findViewById (R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderSummaryEdited.this,Order_Summary.class);
                startActivity(intent);
            }
        });

        //SWIPE REMOVE ELEMENTS FROM RECYCLERVIEW
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                if (viewHolder instanceof OrderSummaryAdapterEdited.MyViewHolder) {
                    // get the removed item name to display it in snack bar
                    String name = orderAdapterList.get(viewHolder.getAdapterPosition()).getItemname();

                    // backup of removed item for undo purpose
                    final OrderSummarySample deletedItem = orderAdapterList.get(viewHolder.getAdapterPosition());
                    final int deletedIndex = viewHolder.getAdapterPosition();

                    // remove the item from recycler view
                    orderSummaryAdapterEdited.removeItem(viewHolder.getAdapterPosition());

                    // showing snack bar with Undo option
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), name + " removed from cart!", Snackbar.LENGTH_LONG);

                    TextView snackbarActionTextView = (TextView) snackbar.getView().findViewById( android.support.design.R.id.snackbar_action );
                    snackbarActionTextView.setTextSize( 14 );
                    snackbarActionTextView.setTypeface(snackbarActionTextView.getTypeface(), Typeface.BOLD);

                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            // undo is selected, restore the deleted item
                            orderSummaryAdapterEdited.restoreItem(deletedItem, deletedIndex);
                        }
                    });
                    snackbar.setDuration(5500);
                    snackbar.setActionTextColor(getResources().getColor(R.color.light_blue));
                    snackbar.show();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void add_Order_Summary_Items() {
        OrderSummarySample orderSummarySample = new OrderSummarySample("Chicken Kabab with Paneer Masala", "₹120");
        orderAdapterList.add(orderSummarySample);

        orderSummarySample = new OrderSummarySample("Chicken 65 with Arabic Fragment", "₹210" );
        orderAdapterList.add(orderSummarySample);

        orderSummarySample = new OrderSummarySample("American Cheese Chicken Gravy", "₹17500" );
        orderAdapterList.add(orderSummarySample);

        orderSummaryAdapterEdited.notifyDataSetChanged();
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
                    BadgeCounters.BadgeColor.LIGHT_BLUE,
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
