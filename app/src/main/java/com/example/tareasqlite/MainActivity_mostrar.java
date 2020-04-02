package com.example.tareasqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
    ListView lv;
    private ListView listview;
    private ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mostrar);
        Button btnregresa = (Button) findViewById(R.id.btnRegresar);
        listview = (ListView) findViewById(R.id.lista);
        final DBAdapter helper = new DBAdapter(this).open();
        helper.open();

        names = new ArrayList<String>();
        names.clear();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        Cursor cur = helper.fetchAllTodos();

        if (cur.moveToFirst()) {

            do {
                names.add(cur.getString(0) + " - " + cur.getString(1) + " - " + cur.getString(2) + " - " + cur.getString(3));
            } while (cur.moveToNext());
        }
        helper.close();
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) listview.getItemAtPosition(position);
                for (int i = 0; i < value.length(); i++) {
                    if (value.charAt(i) == '-') {
                        value = value.substring(0, i);
                        Toast.makeText(getApplicationContext(), "ID: " + value.substring(0, i), Toast.LENGTH_SHORT).show();
                        opciones(Long.parseLong(value.trim()));
                        break;
                    }
                }
            }
        });
        btnregresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
        public boolean opciones (final Long value){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            DBAdapter dbHelper = new DBAdapter(this);
            final DBAdapter db = dbHelper.open();
            builder.setTitle("Selecciona una opción");
            // add a radio button list
            // add update
            builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("ID", value);
                    startActivity(i);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.deleteTodo(value);
                            dialog.dismiss();
                        }
                    });
                    // create and show the alert dialog
                    final AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }
    }
