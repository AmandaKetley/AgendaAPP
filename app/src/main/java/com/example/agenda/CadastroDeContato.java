package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroDeContato extends AppCompatActivity {

    EditText et_nome, et_telefone;
    Button btn_fechar, btn_gravar, btn_consultar;

    CxMsg msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nome = findViewById(R.id.et_nome);
        et_telefone = findViewById(R.id.et_telefone);
        btn_gravar = findViewById(R.id.btn_gravar);
        btn_consultar = findViewById(R.id.btn_consultar);
        btn_fechar = findViewById(R.id.btn_voltar_consult);

        BancoDados.abrir_banco(this);
        BancoDados.abrirOuCriarTabela(this);
        BancoDados.fecharDB();
    }

    public void inserir(View v) {
        String st_nome, st_telefone;
        st_nome = et_nome.getText().toString();
        st_telefone = et_telefone.getText().toString();
        if(st_nome.length() != 0 && st_telefone.length() != 0){
            BancoDados.inserir(st_nome, st_telefone, this);
            et_nome.setText(null);
            et_telefone.setText(null);
        }else{
            msg.mostrar("Prencha todos os campos", this);
        }
    }

    public void abrir_tela_consulta(View v) {
        Intent it_tela_consultar = new Intent(this, TelaConsulta.class);
        startActivity(it_tela_consultar);
    }

    public void fechar_tela(View v) {
        this.finish();
    }

}