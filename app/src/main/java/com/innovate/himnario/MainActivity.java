package com.innovate.himnario;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TabHost;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    TabHost tabHost;
    Spinner spinner;
    ListView listView;
    LinearLayout searchLayout;
    Button btnRapidos;
    Button btnLentos;
    Button btnMedios;
    SearchView searchView;

    //Firebase setup
    FirebaseDatabase database = Utils.getDatabase();
    DatabaseReference rootRef;
    DatabaseReference corosRef;

    ArrayList<Coro> listaCompletaCoros;
    ArrayList<Coro> listaFiltrada;
    ArrayList<String> velocidadesActivas = new ArrayList<>();

    boolean rapBtnAux = false;
    boolean medBtnAux = false;
    boolean lentBtnAux = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootRef = database.getReference();
        corosRef = rootRef.child("coros");

        //Start to load data (todos los coros)
        listaCompletaCoros = new ArrayList<Coro>();
        final Query corosQuery = corosRef.orderByChild("orden");
        loadDataFromDB(corosQuery);

        tabHost = (TabHost)findViewById(R.id.tabHost);
        spinner = (Spinner)findViewById(R.id.spinner);
        listView = (ListView)findViewById(R.id.corosList);
        searchView = (SearchView)findViewById(R.id.searchView);
        searchLayout = (LinearLayout)findViewById(R.id.searchLayout);

        //Buttons setup
        btnLentos = (Button)findViewById(R.id.buttonLentos);
        btnMedios = (Button)findViewById(R.id.buttonMedios);
        btnRapidos = (Button)findViewById(R.id.buttonRapidos);
        buttonSetup();

        //Tab setup
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("busqueda");
        tabSpec.setContent(R.id.tabBusqueda);
        tabSpec.setIndicator("Busqueda");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("listas");
        tabSpec.setContent(R.id.tabListas);
        tabSpec.setIndicator("Listas");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("ajustes");
        tabSpec.setContent(R.id.tabAjustes);
        tabSpec.setIndicator("Ajustes");
        tabHost.addTab(tabSpec);

        //Spinner setup
        String[] spinnerItems = new String[]{"Ton.","Do","Mib","Fa","Sol", "Sib"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        spinner.setAdapter(adapterSpinner);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;
            boolean isScrollingUp = false;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

                if (absListView.getId() == listView.getId()) {
                    final int currentFirstVisibleItem = listView.getFirstVisiblePosition();
                    if (currentFirstVisibleItem > mLastFirstVisibleItem){
                        isScrollingUp = false;
                        searchLayout.setVisibility(View.GONE);
                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem){
                        isScrollingUp = true;
                        searchLayout.setVisibility(View.VISIBLE);
                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    public void buttonSetup() {

        btnLentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!lentBtnAux) {

                    btnLentos.setBackgroundColor(0xFF7BAD3E);
                    velocidadesActivas.add("L");
                } else {
                    btnLentos.setBackgroundColor(Color.BLUE);
                    velocidadesActivas.remove("L");
                    for(int i = 0; i<velocidadesActivas.size();i++) {
                        Log.v(LOG_TAG, velocidadesActivas.get(i));
                    }
                }
                lentBtnAux = !lentBtnAux;
                filterContentForSearch("","Ton.");
            }
        });

        btnMedios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!medBtnAux) {

                    btnMedios.setBackgroundColor(0xFF7BAD3E);
                    velocidadesActivas.add("M");
                } else {

                    btnMedios.setBackgroundColor(Color.BLUE);
                    velocidadesActivas.remove("M");
                }
                medBtnAux = !medBtnAux;
                filterContentForSearch("","Ton.");
            }
        });

        btnRapidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!rapBtnAux) {

                    btnRapidos.setBackgroundColor(0xFF7BAD3E);
                    velocidadesActivas.add("R");
                } else {

                    btnRapidos.setBackgroundColor(Color.BLUE);
                    velocidadesActivas.remove("R");
                }
                rapBtnAux = !rapBtnAux;
                filterContentForSearch("","Ton.");
            }
        });
    }

    public void loadDataFromDB(Query query) {

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ListView setup
                for(DataSnapshot coroSnapshot: dataSnapshot.getChildren()) {
                    Coro coro = coroSnapshot.getValue(Coro.class);
                    int coroId = Integer.parseInt(coroSnapshot.getKey());
                    if (coroId < 3000){
                        listaCompletaCoros.add(coro);
                    }
                }
                loadDataToListView(listaCompletaCoros);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadDataToListView(ArrayList<Coro> lista) {
        CorosAdapter mCorosAdapter = new CorosAdapter(getApplicationContext(), lista, 0);
        listView.setAdapter(mCorosAdapter);
    }

    public void filterContentForSearch(String text, String tonalidad) {
        listaFiltrada = new ArrayList<>();

        if (velocidadesActivas.size() != 0) {
            for(Coro coro: listaCompletaCoros){
                if (tonalidad.equals("Ton.")) {         //Si no se esta filtrando por tonalidad
                    for(String velocidad: velocidadesActivas) {
                        if (coro.vel_let.equals(velocidad)) {
                            listaFiltrada.add(coro);
                        }
                    }
                } else {
                    if (coro.ton.equals(tonalidad)) {
                        for(String velocidad: velocidadesActivas) {
                            if (coro.vel_let.equals(velocidad)) {
                                listaFiltrada.add(coro);
                            }
                        }
                    }
                }
            }

            loadDataToListView(listaFiltrada);

        } else {
            //no se ha seleccionado ninguna lista. Cambiar esa linea porq si se filtra por tonalidades tenemos otro clavo
            loadDataToListView(listaCompletaCoros);
        }

    }
}

