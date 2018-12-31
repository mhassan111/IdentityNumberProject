package com.identitynumber.app.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.identitynumber.app.R;

public class ResearchListAdapter extends RecyclerView.Adapter<ResearchListAdapter.MyViewHolder> {

    private Context mContext;
    private String[] titles;
    private int[] icons;
    private onItemClick onItemClick;

    public ResearchListAdapter(Context mContext, String[] titles, int[] icons) {
        this.mContext = mContext;
        this.titles = titles;
        this.icons = icons;
    }

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView icon;

        private MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            icon = itemView.findViewById(R.id.leftIcon);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.research_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(clickListener);
        holder.title.setText(titles[position]);
        holder.icon.setImageResource(icons[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v != null) {
                Integer tag = (Integer) v.getTag();
                onItemClick.onClick(tag);
            }
        }
    };

    public interface onItemClick {
        public void onClick(int position);
    }
}