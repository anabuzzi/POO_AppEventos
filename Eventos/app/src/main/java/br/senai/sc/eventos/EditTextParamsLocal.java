package br.senai.sc.eventos;

import android.widget.EditText;

import br.senai.sc.eventos.modelo.Evento;
import br.senai.sc.eventos.modelo.Local;

public class EditTextParamsLocal {
    EditText editTextNomeLocal;
    EditText editTextNomeCidade;
    EditText editTextNomeBairro;
    EditText editTextCapacidadeMaxima;

    public EditTextParamsLocal(EditText editTextNomeLocal, EditText editTextNomeCidade, EditText editTextNomeBairro, EditText editTextCapacidadeMaxima) {
        this.editTextNomeLocal = editTextNomeLocal;
        this.editTextNomeCidade = editTextNomeCidade;
        this.editTextNomeBairro = editTextNomeBairro;
        this.editTextCapacidadeMaxima = editTextCapacidadeMaxima;
    }

    public EditText getEditTextNomeLocal() {
        return editTextNomeLocal;
    }

    public void setEditTextNomeLocal(EditText editTextNomeLocal) {
        this.editTextNomeLocal = editTextNomeLocal;
    }

    public EditText getEditTextNomeCidade() {
        return editTextNomeCidade;
    }

    public void setEditTextNomeCidade(EditText editTextNomeCidade) {
        this.editTextNomeCidade = editTextNomeCidade;
    }

    public EditText getEditTextNomeBairro() {
        return editTextNomeBairro;
    }

    public void setEditTextNomeBairro(EditText editTextNomeBairro) {
        this.editTextNomeBairro = editTextNomeBairro;
    }

    public EditText getEditTextCapacidadeMaxima() {
        return editTextCapacidadeMaxima;
    }

    public void setEditTextCapacidadeMaxima(EditText editTextCapacidadeMaxima) {
        this.editTextCapacidadeMaxima = editTextCapacidadeMaxima;
    }

    public void setEditTextsFromLocal(Local local) {
        this.editTextNomeLocal.setText(local.getLocal());
        this.editTextNomeCidade.setText(local.getCidade());
        this.editTextNomeBairro.setText(local.getBairro());
        this.editTextCapacidadeMaxima.setText(local.getCapacidadeMaxima());
    }
}
