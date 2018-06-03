package com.example.vishnuk.pick_a_bite.TabActivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vishnuk.pick_a_bite.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Salads extends Fragment {


    public Salads() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salads, container, false);
    }

}
