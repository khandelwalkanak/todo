package com.example.caneeraj.todo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
//.

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String messageText;
    int pos;
    //.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, com.example.caneeraj.todo.screen_edit.class);
                intent.putExtra(com.example.caneeraj.todo.code_message.EDIT_DATA,arrayList.get(position));
                intent.putExtra(com.example.caneeraj.todo.code_message.ITEM_POS,position);

                startActivityForResult(intent, com.example.caneeraj.todo.code_message.REQUEST_CODE_NEW);
            }
        });
        try {
            Scanner scan = new Scanner(openFileInput("todo.txt"));
            while(scan.hasNextLine())
            {
                String data = scan.nextLine();
                arrayAdapter.add(data);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    public void onClick(View v)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, com.example.caneeraj.todo.data_addition.class);
        startActivityForResult(intent, com.example.caneeraj.todo.code_message.REQUEST_CODE);
    }

    public void onBackPressed() {
        try {
            PrintWriter printwriter = new PrintWriter(openFileOutput("todo.txt", Context.MODE_PRIVATE));
            for(String data : arrayList)
                printwriter.println(data);
            printwriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == com.example.caneeraj.todo.code_message.RESULT_CODE)
        {
            messageText = data.getStringExtra(com.example.caneeraj.todo.code_message.MESSAGE_FIELD);
            arrayList.add(messageText);
            arrayAdapter.notifyDataSetChanged();
        }
        else if (resultCode == com.example.caneeraj.todo.code_message.RESULT_CODE_NEW)
        {
            messageText = data.getStringExtra(com.example.caneeraj.todo.code_message.MESSAGE);
            pos = data.getIntExtra(com.example.caneeraj.todo.code_message.ITEM_POS,-1);
            arrayList.set(pos,messageText);
            arrayAdapter.notifyDataSetChanged();
        }


        else if(resultCode == 3)
        {
            pos= data.getIntExtra(code_message.ITEM_POS,-1);
            arrayList.remove(pos);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}

