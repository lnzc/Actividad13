package com.example.tareasqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.provider.SyncStateContract.Helpers;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.example.tareasqlite.database.DBAdapter;
import android.view.View;

import java.util.ArrayList;

public class MainActivity_mostrar extends AppCompatActivity {
    DBAdapter helper=new DBAdapter(this);
    SimpleCursorAdapter dataAdapter;
    ListView lv;
    private ListView listview;
    private ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mostrar);
        Button btnregresa=(Button) findViewById(R.id.btnRegresar);
        listview = (ListView) findViewById(R.id.lista);
        names =new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        Cursor cur= helper.fetchAllTodos();

        if(cur.moveToFirst()){
            do{
                names.add(cur.getString(0)+ "  "+cur.getString(1)+ cur.getString(2)+ cur.getString(3));
            }while (cur.moveToNext());
        }
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity_mostrar.this, "Has pulsado: "+ names.get(position), Toast.LENGTH_LONG).show();
            }
        });
        btnregresa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
