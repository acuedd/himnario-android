package com.innovate.himnario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Joel on 11-Apr-17.
 */

public class BusquedaTab extends Fragment {

    private boolean mTwoPane;
    private View recyclerView;
    private Data data;

    Spinner spinner;
    Button btnRapidos;
    Button btnLentos;
    Button btnMedios;
    SearchView searchView;
    LinearLayout searchLayout;

    //Firebase setup
    FirebaseDatabase database = Utils.getDatabase();
    DatabaseReference rootRef;
    DatabaseReference corosRef;

    ArrayList<Coro> listaDeCoros;
    ArrayList<Coro> listaFiltrada;
    ArrayList<String> velocidadesActivas = new ArrayList<>();

    boolean rapBtnAux = false;
    boolean medBtnAux = false;
    boolean lentBtnAux = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootRef = database.getReference();
        corosRef = rootRef.child("coros");
        data = new Data();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busqueda_tab, container, false);

        recyclerView = rootView.findViewById(R.id.coro_list);
        assert recyclerView != null;

        if (data.getListaCoros() != null) {
            //Something was already loaded
            listaDeCoros = data.getListaCoros();
            setupRecyclerView((RecyclerView) recyclerView, listaDeCoros);
        } else {
            //Start to load data (todos los coros)
            listaDeCoros = new ArrayList<Coro>();
            final Query corosQuery = corosRef.orderByChild("orden");
            queryDataFromDB(corosQuery);
        }

        if (rootView.findViewById(R.id.coro_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        searchLayout = (LinearLayout)rootView.findViewById(R.id.searchLayout);

        //Searchview setup
        searchView = (SearchView)rootView.findViewById(R.id.searchView);
        searchView.setQueryHint("Buscar coro");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listaFiltrada = new ArrayList<>();
                for(Coro coro: listaDeCoros) {
                    if (coro.nombre.toUpperCase().contains(s.toUpperCase())){
                        listaFiltrada.add(coro);
                    }
                }
                setupRecyclerView((RecyclerView) recyclerView, listaFiltrada);
                return false;
            }
        });

        return rootView;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, ArrayList<Coro> lista) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(lista));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private ArrayList<Coro> listaCoros;

        public SimpleItemRecyclerViewAdapter(ArrayList<Coro> mListaCoros) {
            listaCoros = mListaCoros;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_coros, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Coro coro = listaCoros.get(position);
            holder.coro = coro;
            holder.nombreCoro.setText(coro.nombre);
            LegibleText.setVelocidad(coro.vel_let);
            String legibleVel = LegibleText.getVelocidad();
            holder.velocidad.setText(legibleVel);
            holder.tonalidad.setText(coro.ton);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        CoroDetailFragment fragment = new CoroDetailFragment();
                        fragment.coro = coro;
                        getFragmentManager().beginTransaction()
                                .replace(R.id.coro_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, CoroDetailActivity.class);
                        intent.putExtra("CORO", (Parcelable) coro);
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return listaCoros.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView nombreCoro;
            public final TextView tonalidad;
            public final TextView velocidad;
            public Coro coro;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                nombreCoro = (TextView) view.findViewById(R.id.nombreCoro);
                tonalidad = (TextView) view.findViewById(R.id.tonText);
                velocidad = (TextView) view.findViewById(R.id.velText);
            }
        }
    }

    public void queryDataFromDB(Query query) {

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ListView setup
                for(DataSnapshot coroSnapshot: dataSnapshot.getChildren()) {
                    Coro coro = coroSnapshot.getValue(Coro.class);
                    int coroId = Integer.parseInt(coroSnapshot.getKey());
                    if (coroId < 3000){
                        listaDeCoros.add(coro);
                    }
                }
                setupRecyclerView((RecyclerView) recyclerView, listaDeCoros);
                data.setListaCoros(listaDeCoros);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
