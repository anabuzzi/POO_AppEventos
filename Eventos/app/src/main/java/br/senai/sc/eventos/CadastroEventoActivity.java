package br.senai.sc.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.time.LocalDate;

import br.senai.sc.eventos.modelo.Evento;

import static br.senai.sc.eventos.CodesEnum.RESULT_CODE_NEW_EDITED_EVENT;
import static br.senai.sc.eventos.CodesEnum.RESULT_CODE_NEW_EVENT;

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

        String dateText = editTextParams.getEditTextDate().getText().toString().trim();
        String name = editTextParams.getEditTextNome().getText().toString().trim();
        LocalDate date = dateText.isEmpty() ? null : LocalDate.parse(dateText);
        String location = editTextParams.getEditTextLocation().getText().toString().trim();

        Evento evento = new Evento(eventoId, name, date, location);
        Intent intent = new Intent();

        String tipoEvento = edition ? "eventoEditado" : "novoEvento";
        int resultCode = edition ? RESULT_CODE_NEW_EDITED_EVENT.getValue() : RESULT_CODE_NEW_EVENT.getValue();

        EventoValidator validator = new EventoValidator(evento, new android.app.AlertDialog.Builder(this), editTextParams);
        if (validator.isEventoValido()) {
            intent.putExtra(tipoEvento, evento);
            setResult(RESULT_CODE_NEW_EDITED_EVENT.getValue(), intent);
            setResult(resultCode, intent);
            finish();
        } else {
            validator.apresentaDialogCamposInvalidos();
        }
    }


}