package br.senai.sc.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.time.LocalDate;
import br.senai.sc.eventos.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private final int ResultCode_NewEvent = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");
    }

    public void onClickBack (View v){
        finish();

    }

    public void onClickSave (View v) {

        EditText editTextNome = findViewById(R.id.editText_name);
        EditText editTextDate = findViewById(R.id.editText_date);
        EditText editTextLocation = findViewById(R.id.editText_location);



        String name = editTextNome.getText().toString();
        LocalDate date = LocalDate.parse(editTextDate.getText().toString());
        String location = editTextLocation.getText().toString();

        Evento evento = new Evento (name, date, location);

        Intent intent = new Intent();
        intent.putExtra("novoEvento", evento);
        setResult(ResultCode_NewEvent, intent);


        finish();
    }


}