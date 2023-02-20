package com.example.projetofirebase;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FormCadastro extends AppCompatActivity {

    private EditText edit_nome_cadastro;
    private EditText edit_cadastro_email;
    private EditText edit_cadastro_senha;

    private Button button_cadastro;

    String[] mensagens = {"Preencha todos os campos", "Cadastro Realizado com sucesso!"};
    String usuarioID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        IniciarComponentes();

        button_cadastro.setOnClickListener(view -> {

            String nome  = edit_nome_cadastro.getText().toString();
            String email = edit_cadastro_email.getText().toString();
            String senha = edit_cadastro_senha.getText().toString();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() ){
                Snackbar snackbar = Snackbar.make(view, mensagens [0],Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.RED);
                snackbar.setTextColor(Color.WHITE);
                snackbar.show();

            }else{

                CadastrarUsuario(view);

            }

        });

     }

            private void CadastrarUsuario(View view){

                String email = edit_cadastro_email.getText().toString();
                String senha = edit_cadastro_senha.getText().toString();

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {

                    if (task.isSuccessful()){

                        SalvarDadosUsuario();

                        Snackbar snackbar = Snackbar.make(view, mensagens [1],Snackbar.LENGTH_LONG);
                        snackbar.setBackgroundTint(Color.GREEN);
                        snackbar.setTextColor(Color.WHITE);
                        snackbar.show();
                    }else{
                        String erro;
                        try {
                            throw Objects.requireNonNull(task.getException());

                        }catch (FirebaseAuthWeakPasswordException exception) {

                            erro = "Erro: Digite uma senha com minimo de 6 caracteres!";

                        }catch (FirebaseAuthUserCollisionException exception) {

                            erro = "Erro: Conta ja cadastrada!";

                        }catch (FirebaseAuthInvalidCredentialsException exception) {

                            erro = "Erro: Email invalido!";


                        }catch (Exception exception){

                            erro = "Erro ao cadastrar usuário!";

                        }
                        Snackbar snackbar = Snackbar.make(view, erro,Snackbar.LENGTH_LONG);
                        snackbar.setBackgroundTint(Color.RED);
                        snackbar.setTextColor(Color.WHITE);
                        snackbar.show();


                    }

                });

            }

            private  void SalvarDadosUsuario(){

            String nome = edit_nome_cadastro.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String,Object> usuarios = new HashMap<>();
                usuarios.put("nome",nome);

                usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                DocumentReference documentoReference = db.collection("Usuarios").document(usuarioID);
                documentoReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d("db", "Sucesso ao salvar os dados");

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("db_error", "Erro ao salvar os dados" + e.toString());

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



