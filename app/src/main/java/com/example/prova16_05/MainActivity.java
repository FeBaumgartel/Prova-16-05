package com.example.prova16_05;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvLista = findViewById(R.id.lvLista);

        DAL dal = new DAL(this);
        Cursor cursor = dal.loadAll();
        String[] fields = new String[] {CreateDatabase.NOME, CreateDatabase.IDADE, CreateDatabase.MORTALIDADE, CreateDatabase.ID};
        int[] ids = {R.id.tvNome, R.id.tvIdade, R.id.tvMortalidade};
        adapter = new SimpleCursorAdapter(MainActivity.this,
                R.layout.layout_lista, cursor, fields, ids, 0);

        lvLista.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.Cadastrar);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}