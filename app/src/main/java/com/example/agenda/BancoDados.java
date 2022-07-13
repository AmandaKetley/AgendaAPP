package com.example.agenda;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.view.View;
import android.content.ContextWrapper;


public class BancoDados {

    static CxMsg msg;
    static SQLiteDatabase db = null;
    static Cursor cursor;


    public static void abrir_banco(Activity act) {
        try {
            ContextWrapper cw = new ContextWrapper(act);
            db = cw.openOrCreateDatabase("bancoAgenda", MODE_PRIVATE, null);
        } catch (Exception ex) {
            msg.mostrar("Erro ao criar ou abrir Banco de Dados", act);
        }

    }

    public static void fecharDB() {
        db.close();
    }

    public static void abrirOuCriarTabela(Activity act) {
        try {
            db.execSQL("Create table if not exists contatos (id integer primary key, nome text, fone text);");
        } catch (Exception ex) {
            msg.mostrar("Erro ao Criar tabela", act);
        }
    }


    public static void inserir(String nome, String fone, Activity act) {
        abrir_banco(act);
        try {
            db.execSQL("insert into contatos(nome, fone) values ('" + nome + "','" + fone + "') ");
        } catch (Exception ex) {
            msg.mostrar("Erro ao inserir contato", act);
        } finally {
            msg.mostrar("Contato Salvo", act);
        }
        fecharDB();
    }

    public static Cursor buscar_dados(Activity act) {
        abrir_banco(act);
        cursor = db.query("contatos",
                new String[]{"nome", "fone"},
                null,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        return cursor;

    }
}
