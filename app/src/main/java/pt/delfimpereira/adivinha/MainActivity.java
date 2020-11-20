package pt.delfimpereira.adivinha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random random = new Random();
    private int numeroAdivinhar;
    private int tentativas;

    private EditText editTextNumero = null;
    private TextView textViewAcertou = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        novoJogo();
    }

    private void novoJogo() {
        numeroAdivinhar = random.nextInt(10) + 1;
        tentativas = 0;
    }

    private EditText getEditTextNumero() {
        if (editTextNumero == null) {
            editTextNumero =  findViewById(R.id.editTextNumero);
        }

        return editTextNumero;
    }

    private TextView getTextViewAcertou() {
        if (textViewAcertou == null) {
            textViewAcertou = findViewById(R.id.textViewAcertou);
        }

        return  textViewAcertou;
    }

    public void adivinha(View view) {
        int numero = getNumero();

        if (numero < 1 || numero > 10) {
            getEditTextNumero().setError(getString(R.string.numero_invalido));
            return;
        }

        if (numero == numeroAdivinhar) {
            getTextViewAcertou().setText(R.string.acertou);
        } else if (numero < numeroAdivinhar) {
            getTextViewAcertou().setText(R.string.numero_maior);
        } else {
            getTextViewAcertou().setText(R.string.numero_menor);
        }
    }

    private int getNumero() {
        String s = getEditTextNumero().getText().toString();

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}