package com.example.prova16_05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class DAL {
    private static final String TAG = "DAL";

    private SQLiteDatabase db;
    private CreateDatabase database;

    DAL(Context context) {
        database = new CreateDatabase(context);
    }

    boolean insert(String nome, String idade, String numleucocitos, String glicemia, String ast, String ldh, String pontuacao, String mortalidade, String litaseBiliar) {
        ContentValues values;
        long result;

        // Obtemos um acesso ao banco com permissão de escrita
        db = database.getWritableDatabase();

        // Par de nomes de colunas + valores, para atualização no banco
        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.IDADE, idade);
        values.put(CreateDatabase.NUMLEUCOCITOS, numleucocitos);
        values.put(CreateDatabase.GLICEMIA, glicemia);
        values.put(CreateDatabase.AST, ast);
        values.put(CreateDatabase.LDH, ldh);
        values.put(CreateDatabase.PONTUACAO, pontuacao);
        values.put(CreateDatabase.MORTALIDADE, mortalidade);
        values.put(CreateDatabase.LITASEBILIAR, litaseBiliar);

        // efetivamente insere o registro no banco, fechando o acesso em seguida
        result = db.insert(CreateDatabase.TABLE, null, values);
        db.close();

        // Reporta um erro caso tenha acontecido
        if (result == -1) {
            Log.e(TAG, "insert: Erro inserindo registro");
            return false;
        }

        return true;
    }

    Cursor loadAll() {
        Cursor cursor;
        String[] fields = {CreateDatabase.ID, CreateDatabase.NOME};
        db = database.getReadableDatabase();

        // SELECT _id, title FROM book
        // Consulta equivalente:
        // String sql = "SELECT _id, title FROM book";
        // cursor = db.rawQuery(sql, null);
        cursor = db.query(CreateDatabase.TABLE, fields, null,
                null, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}
