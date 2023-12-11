package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemBaseAdapter extends BaseAdapter {

    Context context;
    List<ProductEntity> products;
    LayoutInflater inflater;

    public ItemBaseAdapter(Context context,List<ProductEntity> products)
    {
        this.context = context;
        this.products = products;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_item_list_view,null);
        TextView textView = (TextView) convertView.findViewById(R.id.singleItemListView);
        textView.setText(products.get(position).toString());
        return convertView;
    }
}
