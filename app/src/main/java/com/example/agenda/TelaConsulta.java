package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.*;

public class TelaConsulta extends AppCompatActivity {

    EditText et_nome, et_telefone;
    Button btn_voltar, btn_anterior, btn_proximo;

    Cursor cursor;

    CxMsg msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);

        et_nome = findViewById(R.id.et_nome_consulta);
        et_telefone = findViewById(R.id.et_telefone_consulta);

        btn_anterior = findViewById(R.id.btn_anterior);
        btn_proximo = findViewById(R.id.btn_proximo);
        btn_voltar = findViewById(R.id.btn_voltar_consult);

        cursor = BancoDados.buscar_dados(this);
        if (cursor.getCount() != 0) {
            mostrar_dados();
        } else {
            msg.mostrar("Nenhum registro foi encontrado", this);
        }
    }

    public void fechar_tela(View v) {
        this.finish();
    }

    @SuppressLint("Range")
    public void mostrar_dados() {
        et_nome.setText(cursor.getString(cursor.getColumnIndex("nome")));
        et_telefone.setText(cursor.getString(cursor.getColumnIndex("fone")));
    }

    public void proximo_registro(View v) {
        try {
            cursor.moveToNext();
            mostrar_dados();
        } catch (Exception ex) {
            if (cursor.isAfterLast()) {
                msg.mostrar("Não existem mais registros", this);
            } else {
                msg.mostrar("Erro ao navegar pelos registros", this);
            }
        }

    }

    public void registro_anterior(View v) {
        try {
            cursor.moveToPrevious();
            mostrar_dados();
        } catch (Exception ex) {
            if (cursor.isBeforeFirst()) {
                msg.mostrar("Não existem mais registros", this);
            } else {
                msg.mostrar("Erro ao navegar pelos registros", this);
            }
        }

    }

}