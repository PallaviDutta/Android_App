package com.example.userpc.android_app;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowList extends Fragment {

    ListView listView;


    public ShowList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_show_list, container, false);

        listView = (ListView)view.findViewById(R.id.listview);
        ArrayList<String> list = new ArrayList<String>();

        File file = new File(Environment.getExternalStorageDirectory(),File.separator+"Pictures/MyCameraApp");
        if(file.exists()) {
            File file_A[] = file.listFiles();

            for (int i = 0; i < file_A.length; i++) {
                String str = file_A[i].toString();
                int a = str.lastIndexOf("/") + 1;
                int b = str.length();
                str = str.substring(a, b);
                list.add(str);
            }
        }

        if (list.isEmpty()) {
            list.add("File Not Found");
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);




        return  view;
    }

}
