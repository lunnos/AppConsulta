package org.neocities.dolphintech.appconsulta;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListaActivity extends AppCompatActivity {
    private Button btnMarcarConsulta;
    private ListView lvConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        btnMarcarConsulta = (Button)findViewById(R.id.btnMarcarConsulta);
        btnMarcarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaActivity.this, ConsultaActivity.class);
                startActivity(i);
            }
        });
    }
}
