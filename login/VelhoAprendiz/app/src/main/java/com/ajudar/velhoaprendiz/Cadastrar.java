package com.ajudar.velhoaprendiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastrar extends AppCompatActivity {

    EditText emailCadastrar, senhaCadastrar;
    Button botaoCadastrar;
    TextView entreAqui;
    FirebaseAuth fAuth;
    ProgressBar progressBarCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        emailCadastrar = findViewById(R.id.emailCadastrar);
        senhaCadastrar = findViewById(R.id.senhaCadastrar);
        entreAqui = findViewById(R.id.entreAqui);
        botaoCadastrar = findViewById(R.id.botaoCadastrar);

        fAuth = FirebaseAuth.getInstance();
        progressBarCadastrar = findViewById(R.id.progressBarCadastro);


        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }



        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailCadastrar.getText().toString().trim();
                String senha = senhaCadastrar.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emailCadastrar.setError("Campo e-mail está vazio!");
                    return;
                }

                if (TextUtils.isEmpty(senha)){
                    senhaCadastrar.setError("Campo senha está vazio!");
                    return;
                }

                if (senha.length() < 6 ){
                    senhaCadastrar.setError("Senha deve ser maior que 6 caracteres.");
                    return;
                }

                progressBarCadastrar.setVisibility(View.VISIBLE);


                //Cadastrar usuario no firebase
                fAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Cadastrar.this, "Cadastro realizado com sucesso!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }else{
                            Toast.makeText(Cadastrar.this, "Erro" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarCadastrar.setVisibility(View.GONE);
                        }
                }});
            }
        });

        entreAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Entrar.class));
            }
        });
    }
}
