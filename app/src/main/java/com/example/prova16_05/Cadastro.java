package com.example.prova16_05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        Button btnAdd = findViewById(R.id.Cadastro);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAL dal = new DAL(Cadastro.this);
                EditText etNome = findViewById(R.id.Nome);
                EditText etIdade = findViewById(R.id.Idade);
                EditText etLeucocitos = findViewById(R.id.Leucocitos);
                EditText etGlicemia = findViewById(R.id.Glicemia);
                EditText etAst = findViewById(R.id.AST);
                EditText etLdh = findViewById(R.id.LDH);
                TextView tvPontuacao = findViewById(R.id.Pontuacao);
                TextView tvMortalidade = findViewById(R.id.Mortalidade);
                CheckBox cbLitaseBiliar = findViewById(R.id.LitaseBiliar);

                int pont=0;
                String litaseBiliar="null";
                if(cbLitaseBiliar.isSelected()){
                    if(Integer.parseInt(etIdade.getText().toString()) > 70){
                        pont++;
                    }
                    if(Integer.parseInt(etLeucocitos.getText().toString())> 18000){
                        pont++;
                    }
                    if(Integer.parseInt(etGlicemia.getText().toString()) > 12.2){
                        pont++;
                    }
                    if(Integer.parseInt(etAst.getText().toString())> 250){
                        pont++;
                    }
                    if(Integer.parseInt(etLdh.getText().toString())> 400){
                        pont++;
                    }
                    litaseBiliar="true";
                }else{
                    if(Integer.parseInt(etIdade.getText().toString()) > 55){
                        pont++;
                    }
                    if(Integer.parseInt(etLeucocitos.getText().toString())> 16000){
                        pont++;
                    }
                    if(Integer.parseInt(etGlicemia.getText().toString())> 11){
                        pont++;
                    }
                    if(Integer.parseInt(etAst.getText().toString())> 250){
                        pont++;
                    }
                    if(Integer.parseInt(etLdh.getText().toString())> 350){
                        pont++;
                    }
                    litaseBiliar="false";
                }
                tvPontuacao.setText(""+pont);
                if(pont<3){
                    tvMortalidade.setText("2%");
                }else if(pont<5){
                    tvMortalidade.setText("15%");
                }else if(pont<7){
                    tvMortalidade.setText("40%");
                }


                String nome = etNome.getText().toString();
                String idade = etIdade.getText().toString();
                String leucocitos = etLeucocitos.getText().toString();
                String glicemia = etGlicemia.getText().toString();
                String ast = etAst.getText().toString();
                String ldh = etLdh.getText().toString();
                String pontuacao = tvPontuacao.getText().toString();
                String mortalidade = tvMortalidade.getText().toString();

                if (dal.insert(nome, idade, leucocitos,glicemia,ast,ldh,pontuacao,mortalidade,litaseBiliar)) {
                    Toast.makeText(Cadastro.this,
                            "Registro Inserido com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Cadastro.this,
                            "Erro ao inserir registro!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
