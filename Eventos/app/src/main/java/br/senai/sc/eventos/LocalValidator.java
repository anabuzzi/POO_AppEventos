package br.senai.sc.eventos;

import android.app.AlertDialog;
import android.text.TextUtils;

import br.senai.sc.eventos.modelo.Local;

public class LocalValidator {

    private Local local;
    private AlertDialog.Builder alertDialogBuilder;
    private boolean isLocalValido;
    private EditTextParamsLocal editTextParamsLocal;

    public LocalValidator(Local local, AlertDialog.Builder alertDialogBuilder, EditTextParamsLocal editTextParamsLocal) {
        this.local = local;
        this.alertDialogBuilder = alertDialogBuilder;
        this.editTextParamsLocal = editTextParamsLocal;
        this.isLocalValido = true;
        validar();
    }

    private void validar() {
        if (isNomeLocalInvalido()) {
            editTextParamsLocal.getEditTextNomeLocal().requestFocus();
            this.isLocalValido = false;
        }
        if (isNomeCidadeInvalida()) {
            editTextParamsLocal.getEditTextNomeCidade().requestFocus();
            this.isLocalValido = false;
        }
        if (isBairroInvalido()) {
            editTextParamsLocal.getEditTextNomeBairro().requestFocus();
            this.isLocalValido = false;
        }
        if (isCapacidadeMaximaInvalida()) {
            editTextParamsLocal.getEditTextCapacidadeMaxima().requestFocus();
            this.isLocalValido = false;
        }
    }

    public boolean isNomeLocalValido() {
        return !TextUtils.isEmpty(this.local.getLocal());
    }

    public boolean isNomeLocalInvalido() {
        return !isNomeLocalValido();
    }

    public boolean isNomeCidadeValida() {
        return this.local.getCidade() != null && local.getCidade() != "";
    }

    public boolean isNomeCidadeInvalida() {
        return !isNomeCidadeValida();
    }

    public boolean isBairroValido() {
        return this.local.getBairro() != null && local.getBairro() != "";
    }

    public boolean isBairroInvalido() {
        return !isBairroValido();
    }

    public boolean isCapacidadeMaximaValida() {
        return local.getCapacidadeMaxima() != 0;
    }

    public boolean isCapacidadeMaximaInvalida() {
        return !isCapacidadeMaximaValida();
    }

    public void apresentaDialogCamposInvalidos() {
        alertDialogBuilder.setTitle("Aviso");
        alertDialogBuilder.setMessage("Há campos obrigatórios não preenchidos. Favor verificar.");
        alertDialogBuilder.setNeutralButton("OK", null);
        alertDialogBuilder.show();
    }

    public boolean isLocalValido() {
        return isLocalValido;
    }
}
