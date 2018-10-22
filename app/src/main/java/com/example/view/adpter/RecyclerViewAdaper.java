package com.example.view.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.view.R;

import java.util.List;

/**
 * Created by TheShy on 2018/10/22 17:03.
 * Email:406262584@qq.com
 */
public class RecyclerViewAdaper extends RecyclerView.Adapter<RecyclerViewAdaper.MyViewHolder>{

    private Context mContext;
    private List<String> items;

    public  RecyclerViewAdaper() {
        super();
    }

    public RecyclerViewAdaper(Context mContext,  List<String> items) {
        this.mContext = mContext;
        this.items = items;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview,parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.item.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView item;
        public MyViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.tag_tv);

        }

    }
}
