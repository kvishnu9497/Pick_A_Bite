package com.example.vishnuk.pick_a_bite.OrderSummary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vishnuk.pick_a_bite.R;

import java.util.List;

public class OrderSummaryAdapterEdited extends RecyclerView.Adapter<OrderSummaryAdapterEdited.MyViewHolder> {
    private List<OrderSummarySample> OrderAdapterItems;
    private Context mContext;
    private Integer count[]= new Integer[100];

    public OrderSummaryAdapterEdited(List<OrderSummarySample> orderAdapterItems, Context mContext) {
        OrderAdapterItems = orderAdapterItems;
        this.mContext = mContext;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemname,rate;// init the item view's
        Spinner counter;
        MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            itemname = (TextView) itemView.findViewById(R.id.itemname);
            rate = (TextView) itemView.findViewById(R.id.rate);
            counter = (Spinner) itemView.findViewById(R.id.counter);

            for(int j=0;j<100;j++)
            {
                count[j]=j+1;
            }
        }
    }

    @NonNull
    @Override
    public OrderSummaryAdapterEdited.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(mContext).inflate(R.layout.order_model_edited, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderSummaryAdapterEdited.MyViewHolder holder, int position) {
        OrderSummarySample sample = OrderAdapterItems.get(position);
        holder.itemname.setText(sample.getItemname());
        holder.rate.setText(sample.getRate());

        //Creating the ArrayAdapter instance having the count
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(mContext, android.R.layout.simple_spinner_item,count);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        holder.counter.setAdapter(dataAdapter);
    }

    @Override
    public int getItemCount() {
        return OrderAdapterItems.size();
    }

    public void removeItem(int position)
    {
        OrderAdapterItems.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(OrderSummarySample sample,int position)
    {
        OrderAdapterItems.add(position,sample);
        notifyItemInserted(position);
    }
}
