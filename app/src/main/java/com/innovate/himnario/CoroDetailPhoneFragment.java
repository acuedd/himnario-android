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
public class CoroDetailPhoneFragment extends Fragment {

    Coro coro;

    public CoroDetailPhoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_coro_detail_phone, container, false);

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

            String dollar = "$";
            nombreText.setText(coro.nombre);
            numeroText.setText(Integer.toString(coro.id));

            LegibleText.setTonalidad(coro.ton, 1);
            tonText.setText(LegibleText.getTonalidad());

            LegibleText.setVelocidad(coro.vel_let);
            velText.setText(LegibleText.getVelocidad());

            tiempoText.setText(Integer.toString(coro.tiempo));
            cuerpoText.setText(coro.cuerpo);

            if (!coro.historia.equals(dollar)) {
                historiaText.setText(coro.historia);
            } else {
                historiaText.setVisibility(View.INVISIBLE);
            }

            if (!coro.aut_let.equals(dollar)) {
                autorletText.setText(coro.aut_let);
            } else {
                autorletText.setText("Desconocido");
            }

            if (!coro.aut_mus.equals(dollar)) {
                autormusText.setText(coro.aut_mus);
            } else {
                autormusText.setText("Desconocido");
            }

            if (!coro.cita.equals(dollar)) {
                citaTxt.setText(coro.cita);
            } else {
                txtViewCita.setVisibility(View.INVISIBLE);
                citaTxt.setVisibility(View.INVISIBLE);
            }
        }
        return rootView;
    }

}
