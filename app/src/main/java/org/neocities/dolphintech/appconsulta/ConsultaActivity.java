package org.neocities.dolphintech.appconsulta;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.neocities.dolphintech.appconsulta.model.Consulta;

public class ConsultaActivity extends AppCompatActivity {

    private Button btnData, btnLimparConsulta, btnConfirmarConsulta, btnCancelar;
    private EditText etSintomas, etTelefoneConsulta, etNome;
    private TextView tvData;
    private FirebaseDatabase database;
    private DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        btnData = (Button) findViewById(R.id.btnData);
        btnLimparConsulta = (Button) findViewById(R.id.btnLimparConsulta);
        tvData = (TextView) findViewById(R.id.tvData);
        etNome = (EditText) findViewById(R.id.etNome);
        etSintomas = (EditText) findViewById(R.id.etSintomas);
        etTelefoneConsulta = (EditText) findViewById(R.id.etTelefoneConsulta);
        btnConfirmarConsulta = (Button) findViewById(R.id.btnConfirmarConsulta);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnConfirmarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        btnLimparConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              etSintomas.setText("");
              tvData.setText("");
              etTelefoneConsulta.setText("");
            }
        });
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarData();
            }
        });
    }

    private void salvar(){
        String nome = etNome.getText().toString();
        String telefone = etTelefoneConsulta.getText().toString();
        String sintomas = etSintomas.getText().toString();
        String data =  tvData.getText().toString();
        String usuario = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if ( ! nome.isEmpty() ){
            database = FirebaseDatabase.getInstance();
            reference = database.getReference();

            Consulta c = new Consulta();
            c.setNome( nome );
            c.setTelefone( telefone );
            c.setSintomas( sintomas );
            c.setData( data );
            c.setIdUsuario( usuario );

            reference.child("consultas").push().setValue( c );

            finish();

        }
    }

    private void carregarData(){
        AlertDialog.Builder alerta = new  AlertDialog.Builder(this);
        final DatePicker calendario = new DatePicker(this);
        alerta.setView(calendario);
        alerta.setPositiveButton("Selecionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            int dia = calendario.getDayOfMonth();
            int mes = calendario.getMonth() + 1;
            int ano = calendario.getYear();
            String data = "";
            if (dia < 10){
                data+= "0";
                }
            data += dia + "/";
            if (mes < 10){
               data += "0";
                }
            data += mes + "/";
            data += ano;
            tvData.setText(data);
            }
        });
        alerta.show();
    }
}
