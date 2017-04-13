package com.innovate.himnario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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

    Spinner spinner;
    Button btnRapidos;
    Button btnLentos;
    Button btnMedios;
    SearchView searchView;
    LinearLayout searchLayout;
    ListView listView;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busqueda_tab, container, false);

        recyclerView = rootView.findViewById(R.id.coro_list);
        assert recyclerView != null;

        //Start to load data (todos los coros)
        listaDeCoros = new ArrayList<Coro>();
        final Query corosQuery = corosRef.orderByChild("orden");
        queryDataFromDB(corosQuery);

        if (rootView.findViewById(R.id.coro_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        return rootView;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(listaDeCoros));
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
            holder.velocidad.setText(coro.vel_let);
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
                setupRecyclerView((RecyclerView) recyclerView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadDataToListView(ArrayList<Coro> lista) {

    }
}
