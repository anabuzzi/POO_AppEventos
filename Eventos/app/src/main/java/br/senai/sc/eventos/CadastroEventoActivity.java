package br.senai.sc.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.senai.sc.eventos.database.EventoDAO;
import br.senai.sc.eventos.database.LocalDAO;
import br.senai.sc.eventos.modelo.Evento;
import br.senai.sc.eventos.modelo.Local;

public class CadastroEventoActivity extends AppCompatActivity {

    public static final String ACTIVITY_TITLE = "Cadastro de Evento";
    private int eventoId = 0;
    private Spinner spinnerLocais;
    private ArrayAdapter<Local> locaisAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle(ACTIVITY_TITLE);
        spinnerLocais = findViewById(R.id.spinner_venues);
        carregarLocais();
        carregarEvento();
    }

    private void carregarEvento() {
        Intent intent = getIntent();

        String tipoEventoEdicao = "eventoEdicao";
        if (isIntentValid(intent, tipoEventoEdicao)) {
            Evento evento = (Evento) intent.getExtras().get(tipoEventoEdicao);
            EditTextParams editTextParams = new EditTextParams(
                    findViewById(R.id.editText_name),
                    findViewById(R.id.editText_date),
                    findViewById(R.id.editText_location));
            editTextParams.setEditTextsFromEvento(evento);
            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocais.setSelection(posicaoLocal);
            this.eventoId = evento.getId();
        }
    }

    private void carregarLocais() {
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        locaisAdapter = new ArrayAdapter<Local>(CadastroEventoActivity.this,
                android.R.layout.simple_spinner_item,
                localDAO.listar());
        spinnerLocais.setAdapter(locaisAdapter);
    }

    private boolean isIntentValid(Intent intent, String tipoEvento) {
        return intent != null && intent.getExtras() != null &&
                intent.getExtras().get(tipoEvento) != null;
    }

    private int obterPosicaoLocal(Local local) {
        for (int posicao = 0; posicao < locaisAdapter.getCount(); posicao++) {
            if (locaisAdapter.getItem(posicao).getId() == local .getId()) {
                return posicao;
            }
        }
        return 0;
    }

    public void onClickBack(View v) {
        finish();
    }

    public void onClickSave(View v) {
        EditTextParams editTextParams = new EditTextParams(findViewById(R.id.editText_name), findViewById(R.id.editText_date), findViewById(R.id.editText_location));

        String name = editTextParams.getEditTextNome().getText().toString().trim();
        String date = editTextParams.getEditTextDate().getText().toString().trim();
        int posicaoLocal = spinnerLocais.getSelectedItemPosition();
        Local local = (Local) locaisAdapter.getItem(posicaoLocal);
        Evento evento = new Evento(eventoId, name, date, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean salvou = eventoDAO.salvar(evento);
        EventoValidator validator = new EventoValidator(evento, new android.app.AlertDialog.Builder(this), editTextParams);
        if (validator.isEventoValido() && salvou) {
            finish();
        } else {
            validator.apresentaDialogCamposInvalidos();
        }
    }


}