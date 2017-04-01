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
        this.allCoros = new ArrayList<>(mCoros);
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

        View rowview = convertView;
        ViewHolder viewHolder;

        if(rowview == null) {
            //convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_coros, parent, false);
            viewHolder = new ViewHolder();
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = inflater.inflate(R.layout.list_item_coros, parent, false);

            viewHolder.labelNombre = (TextView)rowview.findViewById(R.id.nombreCoro);
            viewHolder.labelTonalidad = (TextView)rowview.findViewById(R.id.tonText);
            viewHolder.labelVelocidad = (TextView)rowview.findViewById(R.id.velText);
            rowview.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.labelNombre.setText(coro.nombre);
        viewHolder.labelTonalidad.setText(coro.ton);
        LegibleText.setVelocidad(coro.vel_let);
        String velocidad = LegibleText.getVelocidad();
        viewHolder.labelVelocidad.setText(velocidad);

        return rowview;
    }
}
