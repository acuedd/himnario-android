package com.innovate.himnario;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoroDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    public Coro coro;

    public CoroDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_coro_activity, container, false);

        // Show the dummy content as text in a TextView.
        if (coro != null) {
          //  ((TextView) rootView.findViewById(R.id.coro_detail)).setText(mItem.details);
        }

        return rootView;
    }

}
