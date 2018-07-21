package com.example.caneeraj.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class screen_edit extends AppCompatActivity {

    String new_text;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_for_todo);
        Intent intent = getIntent();
        new_text = intent.getStringExtra(com.example.caneeraj.todo.code_message.EDIT_DATA);
        position = intent.getIntExtra(com.example.caneeraj.todo.code_message.ITEM_POS,-1);
        EditText edited_data = (EditText) findViewById(R.id.textView);
        edited_data.setText(new_text);
    }

    public void savedata(View v)
    {
        String changedData = ((EditText)findViewById(R.id.textView)).getText().toString();
        if(changedData.equals(""))
        {

        }
        else {
            Intent intent = new Intent();
            intent.putExtra(com.example.caneeraj.todo.code_message.ITEM_POS, position);
            intent.putExtra(com.example.caneeraj.todo.code_message.MESSAGE, changedData);
            setResult(com.example.caneeraj.todo.code_message.RESULT_CODE_NEW, intent);
            finish();
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra(code_message.ITEM_POS,position);
        setResult(3,intent);
        finish();
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}
