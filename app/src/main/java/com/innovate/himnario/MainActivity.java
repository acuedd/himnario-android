package com.innovate.himnario;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference corosRef = rootRef.child("coros");

    ArrayList<Coro> listaCompletaCoros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost)findViewById(R.id.tabHost);
        spinner = (Spinner)findViewById(R.id.spinner);
        listView = (ListView)findViewById(R.id.corosList);

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

        listaCompletaCoros = new ArrayList<Coro>();

        final Query corosQuery = corosRef.orderByChild("orden");

        corosQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ListView setup
                for(int i = 0; i<10;i++){
                    listaCompletaCoros.add(dataSnapshot.getValue(Coro.class));
                }
                CorosAdapter mCorosAdapter = new CorosAdapter(getApplicationContext(), listaCompletaCoros, 0);
                listView.setAdapter(mCorosAdapter);
                listaCompletaCoros.clear();
                for(DataSnapshot coroSnapshot: dataSnapshot.getChildren()) {
                    listaCompletaCoros.add(coroSnapshot.getValue(Coro.class));
                }
                mCorosAdapter = new CorosAdapter(getApplicationContext(), listaCompletaCoros, 0);
                listView.setAdapter(mCorosAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

