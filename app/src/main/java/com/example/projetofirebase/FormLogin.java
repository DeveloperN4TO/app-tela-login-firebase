package com.example.projetofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class FormLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;

    private EditText edit_email;
    private EditText edit_senha;
    private Button bt_entrar;
    private ProgressBar progressbar;

    String[] mensagens = {"Preencha todos os campos", "Login efetuado com sucesso!"};
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        IniciarComponenetes();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (FormLogin.this,FormCadastro.class);
                startActivity(intent);
            }
        });

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){

                    Snackbar snackbar = Snackbar.make(view, mensagens [0],Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();

                }else {
                    AutenticarUsuario(view);
                }

            }
        });

    }

    private void AutenticarUsuario(View view){

        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressbar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();

                        }
                    },3000);

                }else  {
                    String erro;

                    try {
                        throw Objects.requireNonNull(task.getException());
                    }catch (Exception e){
                        erro = "Erro ao logar usuário";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro,Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();

                }

            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if( usuarioAtual != null){
           TelaPrin telaPrin = new TelaPrin();
        }
    }

    private void TelaPrincipal(){
        Log.d("LOGIN", "Abrindo tela principal");
        Intent intent = new Intent(FormLogin.this,TelaPrin.class);
        startActivity(intent);
        finish();


    }
    private void IniciarComponenetes(){

        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_entrar = findViewById(R.id.bt_entrar);
        progressbar = findViewById(R.id.progressbar);

    }
}