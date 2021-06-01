package br.senai.sc.eventos;

import android.app.AlertDialog;
import android.text.TextUtils;

import br.senai.sc.eventos.modelo.Evento;

public class EventoValidator {

    private Evento evento;
    private AlertDialog.Builder alertDialogBuilder;
    private boolean isEventoValido;
    private EditTextParams editTextParams;


    public EventoValidator(Evento evento, AlertDialog.Builder alertDialogBuilder, EditTextParams editTextParams) {
        this.evento = evento;
        this.alertDialogBuilder = alertDialogBuilder;
        this.editTextParams = editTextParams;
        this.isEventoValido = true;
        validar();
    }

    private void validar() {
        if (isNomeInvalido()) {
            editTextParams.getEditTextNome().requestFocus();
            this.isEventoValido = false;
        }
        if (isDataInvalida()) {
            editTextParams.getEditTextDate().requestFocus();
            this.isEventoValido = false;
        }
        if (isLocalInvalido()) {
            editTextParams.getEditTextLocation().requestFocus();
            this.isEventoValido = false;
        }
    }

    public boolean isNomeValido() {
        return !TextUtils.isEmpty(this.evento.getNome());
    }

    public boolean isNomeInvalido() {
        return !isNomeValido();
    }

    public boolean isDataValida() {
        return this.evento.getData() != null && evento.getData() != "";
    }

    public boolean isDataInvalida() {
        return !isDataValida();
    }

    public boolean isLocalValido() {
        return !TextUtils.isEmpty(this.evento.getLocal());
    }

    public boolean isLocalInvalido() {
        return !isLocalValido();
    }

    public void apresentaDialogCamposInvalidos() {
        alertDialogBuilder.setTitle("Aviso");
        alertDialogBuilder.setMessage("Há campos obrigatórios não preenchidos. Favor verificar.");
        alertDialogBuilder.setNeutralButton("OK", null);
        alertDialogBuilder.show();
    }

    public boolean isEventoValido() {
        return isEventoValido;
    }
}
