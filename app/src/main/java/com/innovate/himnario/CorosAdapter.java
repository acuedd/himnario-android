package com.innovate.himnario;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joel on 28-Mar-17.
 */

public class CorosAdapter extends ArrayAdapter<Coro> {

    private Context context;
    private int flags;
    private ArrayList<Coro> allCoros;

    public CorosAdapter(Context context, ArrayList<Coro> mCoros, int flags){
        super(context, R.layout.list_item_coros);
        this.flags = flags;
        this.context = context;
        this.allCoros = new ArrayList<Coro>(mCoros);
    }

    @Override
    public int getCount() {
        return allCoros.size();
    }

    @Nullable
    @Override
    public Coro getItem(int position) {
        return allCoros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getPosition(Coro coro) {
        return allCoros.indexOf(coro);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Coro coro = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_coros, parent, false);
        }

        TextView labelNombre = (TextView)convertView.findViewById(R.id.nombreCoro);
        TextView labelTonalidad = (TextView)convertView.findViewById(R.id.tonText);
        TextView labelVelocidad = (TextView)convertView.findViewById(R.id.velText);

        labelNombre.setText(coro.nombre);
        labelTonalidad.setText(coro.tonalidad);
        labelVelocidad.setText(coro.vel_let);

        return convertView;
    }
}
