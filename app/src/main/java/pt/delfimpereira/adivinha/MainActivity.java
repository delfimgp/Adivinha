package pt.delfimpereira.adivinha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random random= new Random();
    private int numeroAdivinhar;
    private int tentativas;

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

    public void adivinha(View view) {
        int numero = getNumero();

    }

    private int getNumero() {
        EditText editTextNumero = findViewById(R.id.editTextNumero);

        String s = editTextNumero.getText().toString();

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            editTextNumero.setError("Número inválido. Introduza um número entre 1 e 10");
            e.printStackTrace();
            return 0;
        }
    }
}