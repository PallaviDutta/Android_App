package com.example.userpc.android_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Actions extends Fragment {


    public Actions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_actions, container, false);

        Button pic_click = (Button)view.findViewById(R.id.pic_clic_btn);
        pic_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Passing to the camera activity
                startActivity(new Intent(getContext(),CameraActivity.class));
            }
        });

        return view;
    }

}
