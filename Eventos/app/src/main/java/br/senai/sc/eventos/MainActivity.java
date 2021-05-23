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

import java.util.ArrayList;

import br.senai.sc.eventos.modelo.Evento;

import static br.senai.sc.eventos.CodesEnum.isCodesNewEvent;
import static br.senai.sc.eventos.CodesEnum.isCodesDeletedEvent;
import static br.senai.sc.eventos.CodesEnum.isCodesNewEditedEvent;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (isCodesNewEvent(requestCode, resultCode)) {
            Evento novoEvento = (Evento) data.getExtras().getSerializable("novoEvento");
            adicionaEvento(novoEvento);
        } else if (isCodesNewEditedEvent(requestCode, resultCode)) {
            Evento eventoEditado = (Evento) data.getExtras().getSerializable("eventoEditado");
            editaEvento(eventoEditado);
        } else if (isCodesDeletedEvent(requestCode, resultCode)) {
            Evento eventoDeletado = (Evento) data.getExtras().getSerializable("eventoEdicao");
            removeEvento(eventoDeletado);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void adicionaEvento(Evento evento) {
        evento.setId(++eventoId);
        this.adapterEvento.add(evento);
    }

    private void editaEvento(Evento evento) {
        for (int i = 0; i < adapterEvento.getCount(); i++) {
            Evento eventoExistente = adapterEvento.getItem(i);
            if (eventoExistente.getId() == evento.getId()) {
                adapterEvento.remove(eventoExistente);
                adapterEvento.insert(evento, i);
                break;
            }
        }
    }

    private void removeEvento(Evento evento) {
        this.adapterEvento.remove(evento);
    }
}