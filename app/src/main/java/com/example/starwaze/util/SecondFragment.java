package com.example.starwaze.util;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.starwaze.R;

import java.util.Random;

public class SecondFragment extends Fragment {
    Integer myArg;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View fragmentSecondLayout = inflater.inflate(R.layout.fragment_second, container, false);

        myArg = SecondFragmentArgs.fromBundle(getArguments()).getMyArg();
        Log.d("numéro", myArg.toString());

        // Inflate the layout for this fragment
        return fragmentSecondLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String headerText = getString(R.string.random_heading, myArg);
        TextView showRandomHeaderTextView = view.findViewById(R.id.textview_header);
        Log.d("nouveau headertxt ", headerText);
        showRandomHeaderTextView.setText(headerText);

        Integer count = myArg;
        Random random = new java.util.Random();
        Integer randomNumber = 0;
        if (count > 0) {
            randomNumber = random.nextInt(count + 1);
        }
        TextView randomView = view.getRootView().findViewById(R.id.textview_random);
        randomView.setText(randomNumber.toString());

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}
