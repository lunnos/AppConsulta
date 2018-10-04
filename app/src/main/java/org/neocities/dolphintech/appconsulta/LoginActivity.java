package org.neocities.dolphintech.appconsulta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin, etSenha;
    private Button btnCadastro, btnLogin;
    private FirebaseAuth autenticacao;
    private FirebaseAuth.AuthStateListener stateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btnCadastro = (Button)  findViewById(R.id.btnCadastro);
        btnLogin = (Button)  findViewById(R.id.btnLogin);

        autenticacao = FirebaseAuth.getInstance();

        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if ( user != null ) {
                    Intent i = new Intent( LoginActivity.this, ListaActivity.class);
                    startActivity( i );
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(i);
            }
        });
    }

    private void login(){
        String email = etLogin.getText().toString();
        String senha = etSenha.getText().toString();

        if ( ! email.isEmpty() ){
            autenticacao.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if ( ! task.isSuccessful()  ){
                                Toast.makeText( LoginActivity.this, "Usuário/Senha inválidos", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }
    }




    @Override
    protected void onStart() {
        super.onStart();
        autenticacao.addAuthStateListener(stateListener);
    }



    @Override
    protected void onStop() {
        super.onStop();
        autenticacao.removeAuthStateListener(stateListener);
    }
}
