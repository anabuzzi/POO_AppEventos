package br.senai.sc.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import br.senai.sc.eventos.database.EventoDAO;
import br.senai.sc.eventos.database.LocalDAO;
import br.senai.sc.eventos.modelo.Evento;
import br.senai.sc.eventos.modelo.Local;

public class CadastroLocalActivity extends AppCompatActivity {

    public static final String ACTIVITY_TITLE = "Cadastro de Local";
    private int localId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle(ACTIVITY_TITLE);
        carregarLocal();
    }

    private void carregarLocal() {
        Intent intent = getIntent();

        String tipoLocalEdicao = "localEdicao";
        if (isLocalValid(intent, tipoLocalEdicao)) {
            Local local = (Local) intent.getExtras().get(tipoLocalEdicao);
            EditTextParamsLocal editTextParamsLocal = new EditTextParamsLocal(
                    findViewById(R.id.editText_nameLocal),
                    findViewById(R.id.editTextT_nameCity),
                    findViewById(R.id.editText_nameNeighborhood),
                    findViewById(R.id.editText_maxCapacity));
            editTextParamsLocal.setEditTextsFromLocal(local);
            this.localId = local.getId();
        }
    }

    private boolean isLocalValid(Intent intent, String tipoLocal) {
        return intent != null && intent.getExtras() != null &&
                intent.getExtras().get(tipoLocal) != null;
    }

    public void onClickBack(View v) {
        finish();
    }

    public void onClickSave(View v) {
        EditTextParamsLocal editTextParamsLocal = new EditTextParamsLocal(findViewById(R.id.editText_nameLocal), findViewById(R.id.editTextT_nameCity),
                findViewById(R.id.editText_nameNeighborhood), findViewById(R.id.editText_maxCapacity));

        String nameLocal = editTextParamsLocal.getEditTextNomeLocal().getText().toString().trim();
        String nameCity = editTextParamsLocal.getEditTextNomeCidade().getText().toString().trim();
        String nameNeighborhood = editTextParamsLocal.getEditTextNomeBairro().getText().toString().trim();
        int capacidadeMaxima = Integer.parseInt(editTextParamsLocal.getEditTextCapacidadeMaxima().getText().toString().trim());

        Local local = new Local(localId, nameLocal, nameCity, nameNeighborhood, capacidadeMaxima);
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        boolean salvou = localDAO.salvar(local);
        LocalValidator validator = new LocalValidator(local, new android.app.AlertDialog.Builder(this), editTextParamsLocal);
        if (validator.isLocalValido() && salvou) {
            finish();
        } else {
            validator.apresentaDialogCamposInvalidos();
        }
    }
}