package com.example.webappmovie02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class CusotmListAdapter extends ArrayAdapter<Movie> {
    private Context aContext=null;
    private int aResource=0;
    public CusotmListAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.aContext = context;
        this.aResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflate = LayoutInflater.from(aContext);
        convertView =        inflate.inflate(aResource,parent,false);
        TextView _txtTitle =convertView.findViewById(R.id.txtTitle);
        TextView _txtYear =convertView.findViewById(R.id.txtYear);
        ImageView _imgView = convertView.findViewById(R.id.imgView);
         _txtTitle.setText(getItem(position).getTitle());
        _txtYear.setText(getItem(position).getYear());
        Picasso.get().load(getItem(position).getPoster()).into(_imgView);
        return convertView;
    }
}
