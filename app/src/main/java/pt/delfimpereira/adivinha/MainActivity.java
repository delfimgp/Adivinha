package pt.delfimpereira.adivinha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String NUMERO_ADIVINHAR = "NUMERO_ADIVINHAR";
    public static final String TENTATIVAS = "TENTATIVAS";
    private Random random = new Random();
    private int numeroAdivinhar;
    private int tentativas;

    private EditText editTextNumero = null;
    private TextView textViewAcertou = null;
    private TextView textViewtentativas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            novoJogo();
        } else {
            numeroAdivinhar = savedInstanceState.getInt(NUMERO_ADIVINHAR);
            tentativas = savedInstanceState.getInt(TENTATIVAS);
            mostraTentativas();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(NUMERO_ADIVINHAR, numeroAdivinhar);
        outState.putInt(TENTATIVAS, tentativas);

        super.onSaveInstanceState(outState);
    }

    private void novoJogo() {
        numeroAdivinhar = random.nextInt(10) + 1;
        tentativas = 0;
        mostraTentativas();
        getTextViewAcertou().setText("");
        getEditTextNumero().setText("");
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

    public TextView getTextViewtentativas() {
        if (textViewtentativas == null) {
            textViewtentativas = findViewById(R.id.textViewTentativas);
        }

        return textViewtentativas;
    }

    public void adivinha(View view) {
        int numero = getNumero();

        if (numero < 1 || numero > 10) {
            getEditTextNumero().setError(getString(R.string.numero_invalido));
            return;
        }

        tentativas++;
        mostraTentativas();


        if (numero == numeroAdivinhar) {
            acertou();
        } else if (numero < numeroAdivinhar) {
            getTextViewAcertou().setText(R.string.numero_maior);
        } else {
            getTextViewAcertou().setText(R.string.numero_menor);
        }
    }

    private void mostraTentativas() {
        getTextViewtentativas().setText(getString(R.string.tentativas) + tentativas);
    }

    private void acertou() {
        getTextViewAcertou().setText(R.string.acertou);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.acertou);
        builder.setMessage(R.string.jogar_novamente);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                novoJogo();
            }
        });

        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.show();
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