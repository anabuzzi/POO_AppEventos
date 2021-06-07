package br.senai.sc.eventos;

import android.widget.EditText;

import br.senai.sc.eventos.modelo.Evento;

public class EditTextParams {
    EditText editTextNome;
    EditText editTextDate;

    public EditTextParams(EditText editTextNome, EditText editTextDate) {
        this.editTextNome = editTextNome;
        this.editTextDate = editTextDate;
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


    public void setEditTextLocation(EditText editTextLocation) {
    }
    public void setEditTextsFromEvento(Evento evento) {
        this.editTextNome.setText(evento.getNome());
        this.editTextDate.setText(String.valueOf(evento.getData()));
    }
}

