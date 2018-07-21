package com.example.caneeraj.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class data_addition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_for_todo);
    }

    public void savedata(View v)
    {
        String DataText = ((EditText) findViewById(R.id.textView)).getText().toString();
        if(DataText.equals(""))
        {

        }
        else
        {
            Intent intent = new Intent();
            intent.putExtra(com.example.caneeraj.todo.code_message.MESSAGE_FIELD,DataText);
            setResult(com.example.caneeraj.todo.code_message.RESULT_CODE,intent);
            finish();
        }
    }




}

