package com.innovate.himnario;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends ActionBarActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    TabHost tabHost;
    Spinner spinner;
    ListView listView;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference corosRef = rootRef.child("coros");

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


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //ListView setup
        String[] items = new String[]{"texto1", "texto2", "texto3"};
        final ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapterList);
       /* corosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(int i=0; i<dataSnapshot.getChildrenCount();i++) {
                    DatabaseReference coroRef = corosRef.child(dataSnapshot.getValue(String.class));
             //       adapterList.add(coroRef);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }
}

