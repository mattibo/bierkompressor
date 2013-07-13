package com.broeckel.bierkompressor.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.broeckel.bierkompressor.R;

/**
 * Created by Matti Borchers on 12.07.13.
 */
public class RandomFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_random, container, false);
    }
}
