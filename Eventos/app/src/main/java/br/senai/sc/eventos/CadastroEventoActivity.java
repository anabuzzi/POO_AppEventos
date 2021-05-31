package br.senai.sc.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.senai.sc.eventos.database.EventoDAO;
import br.senai.sc.eventos.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    public static final String ACTIVITY_TITLE = "Cadastro de Evento";
    private boolean edition = false;
    private int eventoId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle(ACTIVITY_TITLE);
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

            this.edition = true;
            this.eventoId = evento.getId();
        }
    }

    private boolean isIntentValid(Intent intent, String tipoEvento) {
        return intent != null && intent.getExtras() != null &&
                intent.getExtras().get(tipoEvento) != null;
    }

    public void onClickBack(View v) {
        finish();
    }

    public void onClickSave(View v) {
        EditTextParams editTextParams = new EditTextParams(findViewById(R.id.editText_name), findViewById(R.id.editText_date), findViewById(R.id.editText_location));

        String format = "dd-MM-yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        String dateText = editTextParams.getEditTextDate().getText().toString().trim();
        String name = editTextParams.getEditTextNome().getText().toString().trim();
        LocalDate date = dateText.isEmpty() ? null : LocalDate.parse(dateText, formatter);
        String location = editTextParams.getEditTextLocation().getText().toString().trim();

        Evento evento = new Evento(eventoId, name, date, location);
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