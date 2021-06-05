package br.senai.sc.eventos;

import android.widget.EditText;

import br.senai.sc.eventos.modelo.Evento;

public class EditTextParams {
    EditText editTextNome;
    EditText editTextDate;
    EditText editTextLocation;

    public EditTextParams(EditText editTextNome, EditText editTextDate, EditText editTextLocation) {
        this.editTextNome = editTextNome;
        this.editTextDate = editTextDate;
        this.editTextLocation = editTextLocation;
    }

    public EditText getEditTextNome() {
        return editTextNome;
    }

    public void setEditTextNome(EditText editTextNome) {
        this.editTextNome = editTextNome;
    }

    public EditText getEditTextDate() {
        return editTextDate;
    }

    public void setEditTextDate(EditText editTextDate) {
        this.editTextDate = editTextDate;
    }

    public EditText getEditTextLocation() {
        return editTextLocation;
    }

    public void setEditTextLocation(EditText editTextLocation) {
        this.editTextLocation = editTextLocation;
    }
    public void setEditTextsFromEvento(Evento evento) {
        this.editTextNome.setText(evento.getNome());
        this.editTextDate.setText(String.valueOf(evento.getData()));
        this.editTextLocation.setText(evento.getLocal());
    }
}

