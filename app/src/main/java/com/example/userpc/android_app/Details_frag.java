package com.example.userpc.android_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Details_frag extends Fragment {
    String u_name,u_age,u_address,u_gender;
    EditText name,age,address,gender;

    public Details_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_details_frag, container, false);

        name = (EditText)view.findViewById(R.id.name);
        age = (EditText)view.findViewById(R.id.age);
        address = (EditText)view.findViewById(R.id.address);
        gender = (EditText)view.findViewById(R.id.gender);

        Button submit = (Button)view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                u_name = name.getText().toString();
                u_age = age.getText().toString();
                u_address = address.getText().toString();
                u_gender = gender.getText().toString();

                if(u_name.equals("") || u_address.equals("") || u_age.equals("") || u_gender.equals(""))
                {
                    Toast.makeText(getContext(), "Empty Fields Not allowed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    File r_file = android.os.Environment.getExternalStorageDirectory();
                    File f_dir = new File(r_file.getAbsolutePath()+ "/Inventrom");
                    f_dir.mkdirs();
                    File file =new File(f_dir,u_name+".txt");
                    try{
                        FileOutputStream fout = new FileOutputStream(file);
                        PrintWriter writer = new PrintWriter(fout);
                        writer.print("a. Name :");
                        writer.println(u_name);
                        writer.print("b. Age :");
                        writer.println(u_age);
                        writer.print("c. Address :");
                        writer.println(u_address);
                        writer.print("d. Gender :");
                        writer.println(u_gender);
                        writer.flush();
                        writer.close();
                        fout.close();
                    }catch (Exception e){
                        Log.i("","File not found");
                    }
                }
                startActivity(new Intent(getActivity(),Graph.class));
            }
        });


        return view;
    }

}
