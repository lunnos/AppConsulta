package org.neocities.dolphintech.appconsulta;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.neocities.dolphintech.appconsulta.model.Consulta;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private Button btnMarcarConsulta;
    private ListView lvConsultas;
    private List<Consulta> consultas;
    private ArrayAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Query queryRef;
    private ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        btnMarcarConsulta = (Button) findViewById(R.id.btnMarcarConsulta);
        consultas = new ArrayList<>();
        lvConsultas = (ListView) findViewById(R.id.lvConsultas);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, consultas );
        lvConsultas.setAdapter( adapter );

        btnMarcarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaActivity.this, ConsultaActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Sair");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.toString().equals("Sair") ){
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        queryRef = reference.child("consultas").orderByChild("data");

        consultas.clear();

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String usuario = FirebaseAuth.getInstance().getCurrentUser().getUid();

                if ( usuario.equals("oPZggAL8kdVfDQ8mnJiG54uGqN63") ){
                    Consulta c = new Consulta();
                    c.setId( dataSnapshot.getKey() );
                    c.setNome( dataSnapshot.child("nome").getValue(String.class) );
                    c.setTelefone( dataSnapshot.child("telefone").getValue(String.class) );
                    c.setData( dataSnapshot.child("data").getValue(String.class) );
                    consultas.add( c );
                    adapter.notifyDataSetChanged();
                }else if ( usuario.equals(dataSnapshot.child("idUsuario").getValue(String.class))){
                    Consulta c = new Consulta();
                    c.setId( dataSnapshot.getKey() );
                    c.setNome( dataSnapshot.child("nome").getValue(String.class) );
                    c.setTelefone( dataSnapshot.child("telefone").getValue(String.class) );
                    c.setData( dataSnapshot.child("data").getValue(String.class) );
                    consultas.add( c );
                    adapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        queryRef.addChildEventListener( childEventListener );
    }

    @Override
    protected void onStop() {
        super.onStop();
        queryRef.removeEventListener( childEventListener );
    }
}
