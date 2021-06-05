package br.senai.sc.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.senai.sc.eventos.database.LocalDAO;
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