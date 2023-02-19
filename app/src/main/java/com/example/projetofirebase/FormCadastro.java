package com.example.projetofirebase;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class FormCadastro extends AppCompatActivity {

    private EditText edit_nome_cadastro;
    private EditText edit_cadastro_email;
    private EditText edit_cadastro_senha;

    private Button button_cadastro;

    String[] mensagens = {"Preencha todos os campos", "cadastro Realizado com sucesso!"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        IniciarComponentes();

        button_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome  = edit_nome_cadastro.getText().toString();
                String email = edit_cadastro_email.getText().toString();
                String senha = edit_cadastro_senha.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() ){
                    Snackbar snackbar = Snackbar.make(view, mensagens [0],Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();

                }


            }
        });


            }

            private void IniciarComponentes(){

        edit_nome_cadastro = findViewById(R.id.edit_nome_cadastro);
        edit_cadastro_email = findViewById(R.id.edit_cadastro_email);
        edit_cadastro_senha = findViewById(R.id.edit_cadastro_senha);
        button_cadastro = findViewById(R.id.button_cadastro);



            }
        }



