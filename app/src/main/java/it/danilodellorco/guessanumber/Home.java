package it.danilodellorco.guessanumber;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Home extends AppCompatActivity{
    int tentatives = 0;
    int maxTentatives = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView numTentative = (TextView) findViewById(R.id.tvTentatives);
        final TextView maxTentative = (TextView) findViewById(R.id.tvMaxTentatives);
        numTentative.setText(new Integer(tentatives).toString());
        maxTentative.setText(new Integer(maxTentatives).toString());
        final TextView tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvStatus.setVisibility(View.INVISIBLE);

        final ImageButton btnImage = (ImageButton) findViewById(R.id.btnImage);
        btnImage.setImageResource(R.drawable.question);
        Random r = new Random();
        final int cpu = r.nextInt(100 - 1) + 1;
        final Button btnSubmit= (Button) findViewById(R.id.btnSubmit);
        final Toast toast = Toast.makeText(getApplicationContext(), "Numero Inviato", Toast.LENGTH_SHORT);
        final EditText etInput = (EditText) findViewById(R.id.etInput);

        btnImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnSubmit.setEnabled(true);
                tentatives = 0;
                numTentative.setText(new Integer(tentatives).toString());
                etInput.getText().clear();
                btnImage.setImageResource(R.drawable.question);
                tentatives = 0;
                tvStatus.setVisibility(View.INVISIBLE);
                toast.setText("Partita Riavviata!");
                toast.show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Editable input = etInput.getText();
                int usr = Integer.parseInt(input.toString());
                if (usr < cpu && tentatives+1<=maxTentatives){
                    btnImage.setImageResource(R.drawable.up);
                    toast.setText("Hai inserito un numero troppo basso, riprova!");
                    toast.show();
                    tentatives = tentatives +1;
                    numTentative.setText(new Integer(tentatives).toString());
                }
                else if (usr > cpu && tentatives+1<=maxTentatives){
                    btnImage.setImageResource(R.drawable.down);
                    toast.setText("Hai inserito un numero troppo alto, riprova!");
                    toast.show();
                    tentatives = tentatives +1;
                    numTentative.setText(new Integer(tentatives).toString());
                }
                else if (usr == cpu){
                    btnImage.setImageResource(R.drawable.smile);
                    tvStatus.setText("HAI VINTO");
                    tvStatus.setTextColor(Color.GREEN);
                    tvStatus.setVisibility(View.VISIBLE);
                    btnSubmit.setEnabled(false);
                }
                else{
                    btnImage.setImageResource(R.drawable.sigh);
                    toast.show();
                    btnSubmit.setEnabled(false);
                    tvStatus.setText("HAI PERSO");
                    tvStatus.setTextColor(Color.RED);
                    tvStatus.setVisibility(View.VISIBLE);

                }
                etInput.getText().clear();
            }
        });
    }

}
