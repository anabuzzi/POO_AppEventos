package br.senai.sc.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.senai.sc.eventos.database.EventoDAO;
import br.senai.sc.eventos.database.LocalDAO;
import br.senai.sc.eventos.modelo.Evento;
import br.senai.sc.eventos.modelo.Local;

public class ListarLocaisActivity extends AppCompatActivity {

    private ListView listViewLocais;
    private ArrayAdapter<Local> adapterLocais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_locais);
        listViewLocais = findViewById(R.id.listView_venues);
        definirOnClickListenerView();
        onLongClickListenerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterLocais = new ArrayAdapter<Local>(ListarLocaisActivity.this,
                android.R.layout.simple_list_item_1, localDAO.listar());
        listViewLocais.setAdapter(adapterLocais);
    }

    private void definirOnClickListenerView() {
        listViewLocais.setOnItemClickListener((parent, view, position, id) -> {
            Local localClicado = adapterLocais.getItem(position);
            Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocalActivity.class);
            intent.putExtra("localEdicao", localClicado);
            startActivity(intent);
        });
    }

    private void onLongClickListenerView() {
        listViewLocais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Local localClick = adapterLocais.getItem(position);
                LocalDAO localDAO = new LocalDAO(getBaseContext());
                localDAO.excluir(localClick);
                Toast.makeText(ListarLocaisActivity.this, "Local excluído com sucesso", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    public void onClickNovoLocal(View v) {
        Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocalActivity.class);
        startActivity(intent);
    }

    public void onClickEventos(View v) {
        Intent intent = new Intent(ListarLocaisActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}