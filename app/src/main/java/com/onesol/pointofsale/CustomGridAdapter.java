package com.onesol.pointofsale;

import android.content.Context;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neal on 8/9/2016.
 */
public class CustomGridAdapter extends BaseAdapter implements Filterable
{
    private Context context;
    private ArrayList<String> items;
    private ArrayList<String> filterList;
    LayoutInflater mInflater;
    TextView textView;

    public CustomGridAdapter(Context context, ArrayList<String> items)
    {
        this.context = context;
        this.items = items;
        this.filterList = items;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.cell, null);
        }

        // TextView nameTxt = (TextView) convertView.findViewById(R.id.name);
        // ImageView img = (ImageView) convertView.findViewById(R.id.imageView);

        //  t = (TextView)findViewById(R.id.TextView01);
        textView = (TextView) convertView.findViewById(R.id.grid_item);
        //  t.setOnClickListener(this);

        // Set Data
        textView.setText(items.get(position));

        // img.setImageResource(items.get(position).getImg());

        return convertView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.indexOf(getItem(position));
    }


    @Override
    public Filter getFilter()
    {
        return new Filter(){
            @Override
            protected Filter.FilterResults performFiltering(CharSequence constraint)
            {
                Filter.FilterResults results = new Filter.FilterResults();
                ArrayList<String> filters = new ArrayList<String>();
                Log.i("HERE: ", String.valueOf(constraint.length()));
                if(constraint.length() == 0)
                {
                    results.values = filterList;
                    results.count = filterList.size();
                    return results;
                }
                if(constraint != null && constraint.length() >0)
                {
                    // Constarint to upper
                    constraint = constraint.toString().toLowerCase();


                    for(int i=0; i<filterList.size();i++)
                    {
                        if(filterList.get(i).contains(constraint))
                        {
                            filters.add(filterList.get(i));
                        }

                    }

                }
                results.values = filters;
                results.count = filters.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, Filter.FilterResults results)
            {
                items = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
