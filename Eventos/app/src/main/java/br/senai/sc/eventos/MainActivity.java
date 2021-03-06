package br.senai.sc.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.senai.sc.eventos.database.EventoDAO;
import br.senai.sc.eventos.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    public static final String ACTIVITY_TITLE = "Eventos";
    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEvento;
    private int eventoId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(ACTIVITY_TITLE);
        listViewEventos = findViewById(R.id.listView_eventos);
        defineOnClickListenerView();
        onLongClickListenerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEvento = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.listar());
        listViewEventos.setAdapter(adapterEvento);
    }

    private void defineOnClickListenerView() {

        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClick = adapterEvento.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClick);
                startActivity(intent);
            }
        });
    }

    private void onLongClickListenerView() {
        listViewEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClick = adapterEvento.getItem(position);
                EventoDAO eventoDAO = new EventoDAO(getBaseContext());
                eventoDAO.excluir(eventoClick);
                Toast.makeText(MainActivity.this, "Evento exclu??do com sucesso", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    public void onClickNovoEvento(View v) {
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intent);
    }

    public void onClickLocais(View v) {
        Intent intent = new Intent(MainActivity.this, ListarLocaisActivity.class);
        startActivity(intent);
        finish();
    }

}