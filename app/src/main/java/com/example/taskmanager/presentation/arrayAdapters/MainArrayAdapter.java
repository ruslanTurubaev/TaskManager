package com.example.taskmanager.presentation.arrayAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.taskmanager.R;

import java.util.List;

public class MainArrayAdapter extends ArrayAdapter<MainListItems> {
    private Context mContext;
    private List<MainListItems> list;

    public MainArrayAdapter(@NonNull Context context, int resource, @NonNull List<MainListItems> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.list = objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.main_list_item, null);
        }

        MainListItems item = list.get(position);

        TextView description = listItem.findViewById(R.id.tvDescription);
        description.setText(item.getDescription());

        TextView status = listItem.findViewById(R.id.tvStatus);
        status.setText(item.getStatus());

        return listItem;
    }
}
