package com.innovate.himnario;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoroDetailTabletFragment extends Fragment {

    public Coro coro;

    public CoroDetailTabletFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_coro_detail_tablet, container, false);

        if (coro != null) {
            //Initializing texts
            TextView nombreText = (TextView) rootView.findViewById(R.id.nomCoroText);
            TextView numeroText = (TextView) rootView.findViewById(R.id.numText);
            TextView tonText = (TextView) rootView.findViewById(R.id.tonalidadText);
            TextView velText = (TextView) rootView.findViewById(R.id.velocidadText);
            TextView tiempoText = (TextView) rootView.findViewById(R.id.tiempoText);
            TextView cuerpoText = (TextView) rootView.findViewById(R.id.cuerpoText);
            TextView historiaText = (TextView) rootView.findViewById(R.id.historiaText);
            TextView autorletText = (TextView) rootView.findViewById(R.id.autorletText);
            TextView autormusText = (TextView) rootView.findViewById(R.id.autormusText);
            TextView citaTxt = (TextView) rootView.findViewById(R.id.citaTxt);
            TextView txtViewCita = (TextView) rootView.findViewById(R.id.txtViewCita);

            nombreText.setText(coro.nombre);
            numeroText.setText(coro.id);
            tonText.setText(coro.ton);
            velText.setText(coro.vel_let);
            tiempoText.setText(coro.tiempo);
            cuerpoText.setText(coro.cuerpo);
            historiaText.setText(coro.historia);
            autorletText.setText(coro.aut_let);
            autormusText.setText(coro.aut_mus);
            if (!coro.cita.equals("$")) {
                citaTxt.setText(coro.cita);
            } else {
                txtViewCita.setVisibility(View.INVISIBLE);
                citaTxt.setVisibility(View.INVISIBLE);
            }
        }
        return rootView;
    }

}
