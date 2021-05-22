package br.senai.sc.eventos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.time.LocalDate;

import br.senai.sc.eventos.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private final int ResultCode_NewEvent = 10;
    private final int ResultCode_NewEditedEvent = 11;
    private final int ResultCodeDeleteEvent = 20;
    private boolean edition = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");

        carregarEvento();
    }


    private boolean validaCampos() {

        boolean camposInvalidos = false;

        EditText editTextNome = findViewById(R.id.editText_name);
        EditText editTextDate = findViewById(R.id.editText_date);
        EditText editTextLocation = findViewById(R.id.editText_location);
        String dateText = editTextDate.getText().toString();
        String name = editTextNome.getText().toString();
        LocalDate date = dateText.isEmpty() ? null : LocalDate.parse(dateText);
        String location = editTextLocation.getText().toString();

        if (isEmptyCampo(name) ) {
            editTextNome.requestFocus();
            camposInvalidos = true;
        } else if (date == null || isEmptyCampo(date.toString())){
            editTextDate.requestFocus();
            camposInvalidos = true;
        } else if (isEmptyCampo(location)) {
            editTextLocation.requestFocus();
            camposInvalidos = true;
        }

        if (camposInvalidos) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos obrigatórios não preenchidos. Favor verificar.");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
        return camposInvalidos;
    }


    private boolean isEmptyCampo(String valor) {

        return (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
    }


    private void carregarEvento() {

        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");

            EditText editTextNome = findViewById(R.id.editText_name);
            EditText editTextDate = findViewById(R.id.editText_date);
            EditText editTextLocation = findViewById(R.id.editText_location);

            editTextNome.setText(evento.getName());
            editTextDate.setText(String.valueOf(evento.getDate()));
            editTextLocation.setText(evento.getLocation());
            edition = true;
            id = evento.getId();

        }

    }

    public void onClickBack(View v) {
        finish();

    }

    public void onClickSave(View v) {

        EditText editTextNome = findViewById(R.id.editText_name);
        EditText editTextDate = findViewById(R.id.editText_date);
        EditText editTextLocation = findViewById(R.id.editText_location);
        String dateText = editTextDate.getText().toString();
        String name = editTextNome.getText().toString();
        LocalDate date = dateText.isEmpty() ? null : LocalDate.parse(dateText);
        String location = editTextLocation.getText().toString();

        Evento evento = new Evento(id, name, date, location);
        Intent intent = new Intent();

        if (edition) {
            intent.putExtra("eventoEditado", evento);
            setResult(ResultCode_NewEditedEvent, intent);

        } else {
            intent.putExtra("novoEvento", evento);
            setResult(ResultCode_NewEvent, intent);
        }

        boolean camposInvalidos = validaCampos();

        if (!camposInvalidos) {
            finish();
        }

    }

    public void onClickDelete(View v) {
        EditText editTextNome = findViewById(R.id.editText_name);
        EditText editTextDate = findViewById(R.id.editText_date);
        EditText editTextLocation = findViewById(R.id.editText_location);
        String dateText = editTextDate.getText().toString();
        String name = editTextNome.getText().toString();
        LocalDate date = dateText.isEmpty() ? null : LocalDate.parse(dateText);
        String location = editTextLocation.getText().toString();

        Intent intent = getIntent();
        Evento evento = (Evento) intent.getExtras().get("eventoDeletado");

        if (edition) {
            intent.putExtra("eventoEdicao", evento);
            setResult(ResultCodeDeleteEvent, intent);
        }
        finish();
    }


}