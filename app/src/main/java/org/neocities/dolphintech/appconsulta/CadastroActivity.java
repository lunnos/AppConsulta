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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome, etEmail, etSenha, etConfirmarSenha;
    private Button btnCancel, btnConfirmar, btnLimpar;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String deuruim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etConfirmarSenha = (EditText) findViewById(R.id.etConfirmarSenha);
        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNome.setText("");
                etSenha.setText("");
                etConfirmarSenha.setText("");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( CadastroActivity.this, LoginActivity.class);
                startActivity( i );
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }

    private void cadastrar() {
        final String nome = etNome.getText().toString();
        final String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        String confirma = etConfirmarSenha.getText().toString();

        deuruim = "";

        if (nome.isEmpty()) {
            deuruim = "O campo Nome é obrigatório.";
        } else {
            if (email.isEmpty()) {
                deuruim = "O campo E-Mail é obrigatório.";
            } else {
                if (senha.isEmpty() || !senha.equals(confirma)) {
                    deuruim = "Ambos os campos de senha devem ser preenchidos e iguais.";
                } else {
                    final FirebaseAuth autenticacao = FirebaseAuth.getInstance();
                    autenticacao.createUserWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        database = FirebaseDatabase.getInstance();
                                        String idUsuario = autenticacao.getCurrentUser().getUid();
                                        reference = database.getReference("usuarios").child(idUsuario);
                                        reference.child("nome").setValue(nome);
                                        reference.child("email").setValue(email);


                                        finish();
                                    } else {
                                        deuruim = "Não foi possível completar a ação.";
                                    }
                                }

                            });
                }
            }
        }
        if ( !deuruim.isEmpty() ){
            Toast.makeText(this, deuruim, Toast.LENGTH_LONG).show();
        }
    }
  //
}
