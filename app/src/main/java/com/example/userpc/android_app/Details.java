package com.example.userpc.android_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Details extends AppCompatActivity {

    String u_name,u_age,u_address,u_gender;
    EditText name,age,address,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = (EditText)findViewById(R.id.name);
        age = (EditText)findViewById(R.id.age);
        address = (EditText)findViewById(R.id.address);
        gender = (EditText)findViewById(R.id.gender);



        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                u_name = name.getText().toString();
                u_age = age.getText().toString();
                u_address = address.getText().toString();
                u_gender = gender.getText().toString();

                if(u_name.equals("") || u_address.equals("") || u_age.equals("") || u_gender.equals(""))
                {
                    Toast.makeText(Details.this, "Empty Fields Not allowed", Toast.LENGTH_SHORT).show();
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
                
            }
        });
    }
}
