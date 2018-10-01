package org.neocities.dolphintech.appconsulta;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class ConsultaActivity extends AppCompatActivity {

    private Button btnData, btnLimparConsulta;
    private EditText etSintomas, etTelefoneConsulta, etData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        btnData = (Button) findViewById(R.id.btnData);
        btnLimparConsulta = (Button) findViewById(R.id.btnLimparConsulta);
        etData = (EditText) findViewById(R.id.etData);
        etSintomas = (EditText) findViewById(R.id.etSintomas);
        etTelefoneConsulta = (EditText) findViewById(R.id.etTelefoneConsulta);


        btnLimparConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              etSintomas.setText("");
              etData.setText("");
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

    private void carregarData(){
        AlertDialog.Builder alerta = new  AlertDialog.Builder(this);
        DatePicker calendario = new DatePicker(this);
        alerta.setView(calendario);
        alerta.setPositiveButton("Selecionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.show();

    }
}
