package com.innovate.himnario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Joel on 05-Apr-17.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_coro_activity);

        //Initializing texts
        TextView nombreText = (TextView) findViewById(R.id.nomCoroText);
        TextView numeroText = (TextView) findViewById(R.id.numText);
        TextView tonText = (TextView) findViewById(R.id.tonalidadText);
        TextView velText = (TextView) findViewById(R.id.velocidadText);
        TextView tiempoText = (TextView) findViewById(R.id.tiempoText);
        TextView cuerpoText = (TextView) findViewById(R.id.cuerpoText);
        TextView historiaText = (TextView) findViewById(R.id.historiaText);
        TextView autorletText = (TextView) findViewById(R.id.autorletText);
        TextView autormusText = (TextView) findViewById(R.id.autormusText);
        TextView citaTxt = (TextView) findViewById(R.id.citaTxt);
        TextView txtViewCita = (TextView) findViewById(R.id.txtViewCita);

        //Screen awake
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


    }
}
