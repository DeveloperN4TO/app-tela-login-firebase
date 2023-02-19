package com.example.projetofirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FormCadastro extends AppCompatActivity {

    private EditText edit_nome_cadastro;
    private EditText edit_cadastro_email;
    private EditText edit_cadastro_senha;

    private Button button_cadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        IniciarComponentes();

        button_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                

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



