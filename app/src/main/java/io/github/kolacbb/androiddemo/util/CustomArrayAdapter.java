package io.github.kolacbb.androiddemo.util;

import android.content.Context;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import io.github.kolacbb.androiddemo.R;

/**
 * Created by Kola on 2016/6/17.
 */
public class CustomArrayAdapter extends ArrayAdapter<ActivityItemDetails> {

    private LayoutInflater mInflater;

    public CustomArrayAdapter(Context context, ActivityItemDetails[] demos) {
        super(context, R.layout.list_item_data, demos);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView == null) {
            holder = new MyViewHolder();
            //通过LayoutInflater实例化布局
            convertView = mInflater.inflate(R.layout.list_item_data, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(holder);
        } else {
            // 通过Tag找到缓存的布局
            holder = (MyViewHolder) convertView.getTag();
        }

        ActivityItemDetails item = getItem(position);
        holder.title.setText(item.title);
        holder.description.setText(item.description);

        return convertView;
    }

    public final class MyViewHolder {
        public TextView title;
        public TextView description;
    }
}
