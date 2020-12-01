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

public class Entrar extends AppCompatActivity {

    EditText emailEntrar, senhaEntrar;
    Button botaoEntrar;
    TextView cadastreAqui;
    FirebaseAuth fAuth;
    ProgressBar progressBarEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        emailEntrar = findViewById(R.id.emailEntrar);
        senhaEntrar = findViewById(R.id.senhaEntrar);
        cadastreAqui = findViewById(R.id.cadastreAqui);
        botaoEntrar = findViewById(R.id.botaoEntrar);

        fAuth = FirebaseAuth.getInstance();
        progressBarEntrar = findViewById(R.id.progressBarEntrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEntrar.getText().toString().trim();
                String senha = senhaEntrar.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emailEntrar.setError("Campo e-mail está vazio!");
                    return;
                }

                if (TextUtils.isEmpty(senha)){
                    senhaEntrar.setError("Campo senha está vazio!");
                    return;
                }

                if (senha.length() < 6 ){
                    senhaEntrar.setError("Senha deve ser maior que 6 caracteres.");
                    return;
                }

                progressBarEntrar.setVisibility(View.VISIBLE);


                // autenticar usuario

                fAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Entrar.this, "Você entrou!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Entrar.this, "Erro" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarEntrar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        cadastreAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Cadastrar.class));
            }
        });
    }
}
