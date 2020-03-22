package it.danilodellorco.guessanumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity{
    int cpu = 0;
    int tentatives;
    int maxTentatives;
    int rangeMin;
    int rangeMax;

    public class Holder implements View.OnClickListener, View.OnLongClickListener {
        Button btnSubmit;
        Button btnSettings;
        EditText etInput;
        TextView tvTentatives;
        TextView tvMaxTentatives;
        TextView tvStatus;
        TextView tvChoosenText;
        TextView tvChoosenNum;
        ImageButton btnImage;
        Toast toast;

        public Holder() {
            toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

            btnSettings = findViewById(R.id.btnSettings);
            btnSubmit = findViewById(R.id.btnSubmit);
            etInput = findViewById(R.id.etInput);
            tvTentatives = findViewById(R.id.tvTentatives);
            tvMaxTentatives = findViewById(R.id.tvMaxTentatives);
            tvStatus = findViewById(R.id.tvStatus);
            btnImage = findViewById(R.id.btnImage);
            tvChoosenNum = findViewById(R.id.tvChoosenNum);
            tvChoosenText = findViewById(R.id.tvChoosenText);

            btnImage.setOnClickListener(this);
            btnSubmit.setOnClickListener(this);
            btnImage.setOnLongClickListener(this);
            btnSettings.setOnClickListener(this);

            newGame();
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnImage) { //Premuto pulsante di reset
                newGame();
            }

            if (v.getId() == R.id.btnSettings) {
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            }

            if (v.getId() == R.id.btnSubmit) { //Premuto pulante di submit

                if (etInput.getText().toString().equals("")) {
                    toast.setText("Inserisci un Numero!");
                    toast.show();
                }
                else {
                    int usr = Integer.parseInt(etInput.getText().toString());
                    if (usr < cpu && tentatives + 1 <= maxTentatives) {
                        tentatives = tentatives + 1;
                        btnImage.setImageResource(R.drawable.up);
                        toast.setText("Hai inserito un numero troppo basso, riprova!");
                        toast.show();
                        tvTentatives.setText(Integer.toString(tentatives));
                        rangeMin = usr;
                        etInput.setHint(rangeText(rangeMin,rangeMax));
                    } else if (usr > cpu && tentatives + 1 <= maxTentatives) {
                        tentatives = tentatives + 1;
                        btnImage.setImageResource(R.drawable.down);
                        toast.setText("Hai inserito un numero troppo alto, riprova!");
                        toast.show();
                        tvTentatives.setText(Integer.toString(tentatives));
                        rangeMax = usr;
                        etInput.setHint(rangeText(rangeMin,rangeMax));
                    } else if (usr == cpu) {
                        tvChoosenNum.setVisibility(View.VISIBLE);
                        tvChoosenText.setVisibility(View.VISIBLE);
                        btnImage.setImageResource(R.drawable.smile);
                        tvStatus.setText("HAI VINTO");
                        tvStatus.setTextColor(Color.GREEN);
                        tvStatus.setVisibility(View.VISIBLE);
                        btnSubmit.setEnabled(false);
                    } else {
                        tvChoosenNum.setVisibility(View.VISIBLE);
                        tvChoosenText.setVisibility(View.VISIBLE);
                        tvStatus.setTextColor(Color.RED);
                        tvStatus.setVisibility(View.VISIBLE);
                        btnSubmit.setEnabled(false);
                        btnImage.setImageResource(R.drawable.sigh);
                        tvStatus.setText("HAI PERSO");
                        toast.show();
                    }
                    etInput.getText().clear();
                }
            }
        }

        public void newGame(){
            tentatives = 0;
            maxTentatives = 7;
            rangeMin = 1;
            rangeMax = 100;
            cpu = new Random().nextInt(100 - 1) + 1;
            tvTentatives.setText(Integer.toString(tentatives));
            tvMaxTentatives.setText(Integer.toString(maxTentatives));
            etInput.setHint(rangeText(rangeMin,rangeMax));

            tvChoosenNum.setVisibility(View.INVISIBLE);
            tvChoosenText.setVisibility(View.INVISIBLE);
            tvStatus.setVisibility(View.INVISIBLE);

            tvChoosenNum.setText(Integer.toString(cpu));
            btnImage.setImageResource(R.drawable.question);

            toast.setText("Nuova Partita!");
            toast.show();
        }

        public String rangeText(int min,int max){
            return "[" + Integer.toString(min) + " - " + Integer.toString(max) + "]";
        }

        @Override
        public boolean onLongClick(View v) {
            rangeMin = 0;
            rangeMax = 200;
            tentatives = 0;
            cpu = new Random().nextInt(200 - 1) + 1;
            etInput.setHint(rangeText(rangeMin,rangeMax));
            tvChoosenNum.setText(Integer.toString(cpu));
            tvTentatives.setText(Integer.toString(tentatives));
            tvStatus.setVisibility(View.INVISIBLE);
            tvChoosenNum.setVisibility(View.INVISIBLE);
            tvChoosenText.setVisibility(View.INVISIBLE);
            btnSubmit.setEnabled(true);
            etInput.getText().clear();
            btnImage.setImageResource(R.drawable.devil);
            toast.setText("Partita Riavviata!");
            toast.show();
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Holder holder = new Holder();

    }
}
