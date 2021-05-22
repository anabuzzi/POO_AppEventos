package br.senai.sc.eventos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.time.LocalDate;
import java.util.ArrayList;
import br.senai.sc.eventos.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    private final int RequestCodeNewEvent = 1;
    private final int ResultCodeNewEvent = 10;
    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Eventos");

        listViewEventos = findViewById(R.id.listView_eventos);
        ArrayList<Evento> eventos = this.createEvent();
        adapterEvento = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventos);
        listViewEventos.setAdapter(adapterEvento);
    }

    private ArrayList<Evento> createEvent(){
        ArrayList<Evento> eventos = new ArrayList<Evento>();
//        eventos.add(new Evento("Evento aniversario", LocalDate.of(2020,02,21), "Florianópolis"));
//        eventos.add(new Evento("Evento ferias",  LocalDate.of(2020,02,21), "Florianópolis"));
//        eventos.add(new Evento("Evento viagem",  LocalDate.of(2020,02,21), "Florianópolis"));


        return eventos;
    }

    public void onClickNewEvent (View v){

        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivityForResult(intent, RequestCodeNewEvent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RequestCodeNewEvent && resultCode == ResultCodeNewEvent){
           Evento evento =  (Evento) data.getExtras().getSerializable("novoEvento");
           this.adapterEvento.add(evento);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}