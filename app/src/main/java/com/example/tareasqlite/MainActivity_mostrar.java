package com.example.tareasqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.provider.SyncStateContract.Helpers;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.example.tareasqlite.database.DBAdapter;
import android.view.View;

public class MainActivity_mostrar extends AppCompatActivity {
    DBAdapter helper=new DBAdapter(this);
    SimpleCursorAdapter dataAdapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mostrar);
                Cursor cursor = helper.fetchAllTodos();
                String[] columnas = {DBAdapter.KEY_ROWID, DBAdapter.KEY_CATEGORY, DBAdapter.KEY_SUMMARY, DBAdapter.KEY_DESCRIPTION};

                int[] donde = {R.id.txtcod, R.id.txtCat, R.id.txtRes, R.id.txtDes};
                dataAdapter = new SimpleCursorAdapter(this, R.layout.activity_main_mostrar, cursor, columnas, donde);
                lv = (ListView) findViewById(R.id.listView1);
                lv.setAdapter(dataAdapter);
    }
}
