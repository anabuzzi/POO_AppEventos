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
                startActivityForResult(intent, CodesEnum.REQUEST_CODE_EVENT_EDITION.getValue());
            }
        });

        listViewEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClick = adapterEvento.getItem(position);
                adapterEvento.remove(eventoClick);
                Toast.makeText(MainActivity.this, "Evento excluído com sucesso", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    public void onClickNovoEvento(View v) {
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivityForResult(intent, CodesEnum.REQUEST_CODE_NEW_EVENT.getValue());
    }

    private void removeEvento(Evento evento) {
        this.adapterEvento.remove(evento);
    }
}