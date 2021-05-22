package br.senai.sc.eventos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

import br.senai.sc.eventos.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    private final int RequestCodeNewEvent = 1;
    private final int ResultCodeNewEvent = 10;
    private final int RequestCodeEventEdition = 2;
    private final int ResultCodeNewEditedEvent = 11;
    private final int ResultCodeDeleteEvent = 20;
    private final int RequestCodeDeleteEvent = 30;

    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEvento;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Eventos");

        listViewEventos = findViewById(R.id.listView_eventos);
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        adapterEvento = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventos);
        listViewEventos.setAdapter(adapterEvento);

        defineOnClickListenerView();

    }

    // Edição de itens da lista
    private void defineOnClickListenerView() {

        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClick = adapterEvento.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClick);
                startActivityForResult(intent, RequestCodeEventEdition);
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

    public void onClickNewEvent(View v) {

        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivityForResult(intent, RequestCodeNewEvent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RequestCodeNewEvent && resultCode == ResultCodeNewEvent) {
            Evento evento = (Evento) data.getExtras().getSerializable("novoEvento");
            evento.setId(++id);
            this.adapterEvento.add(evento);
        } else if (requestCode == RequestCodeEventEdition && resultCode == ResultCodeNewEditedEvent) {
            Evento eventoEditado = (Evento) data.getExtras().getSerializable("eventoEditado");
            for (int i = 0; i < adapterEvento.getCount(); i++) {
                Evento evento = adapterEvento.getItem(i);
                if (evento.getId() == eventoEditado.getId()) {
                    adapterEvento.remove(evento);
                    adapterEvento.insert(eventoEditado, i);
                    break;
                }
            }
        } else if (requestCode == RequestCodeDeleteEvent || resultCode == ResultCodeDeleteEvent) {
            Evento eventoDeletado = (Evento) data.getExtras().getSerializable("eventoEdicao");
            this.adapterEvento.remove(eventoDeletado);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}