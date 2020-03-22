package it.danilodellorco.guessanumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static it.danilodellorco.guessanumber.MainActivity.MAX_TENTS;
import static it.danilodellorco.guessanumber.MainActivity.MAX_VALUE;
import static it.danilodellorco.guessanumber.MainActivity.MIN_VALUE;
import static it.danilodellorco.guessanumber.MainActivity.SHARED_PREFS;

public class SettingsActivity extends AppCompatActivity {

    public class Holder2 implements View.OnClickListener{
        Spinner spMin;
        Spinner spMax;
        EditText etMaxTentatives;
        Button btnSave;
        Button btnDefault;
        Toast toast;

        public Holder2(){
            toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
            spMin = findViewById(R.id.spMin);
            spMax = findViewById(R.id.spMax);
            etMaxTentatives = findViewById(R.id.etMaxTentatives);
            btnSave = findViewById(R.id.btnSave);
            btnSave.setOnClickListener(this);
            btnDefault = findViewById(R.id.btnDefault);
            btnDefault = findViewById(R.id.btnDefault);
            btnDefault.setOnClickListener(this);

            Toast toast = Toast.makeText(getApplicationContext(), "dsfds", Toast.LENGTH_SHORT);
            toast.show();
            arrayInitializer();
        }

        public void arrayInitializer(){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getBaseContext(),
                    R.array.choices, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spMin.setAdapter(adapter);
            spMax.setAdapter(adapter);
        }

        @Override
        public void onClick(View v) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int min,max,ten;
            if (v.getId() == R.id.btnSave) {
                min = Integer.parseInt(spMin.getSelectedItem().toString());
                max = Integer.parseInt(spMax.getSelectedItem().toString());
                if (etMaxTentatives.getText().toString().equals("")) {
                    toast.setText("Inserisci il numero di tentativi!");
                    toast.show();
                }
                ten = Integer.parseInt(etMaxTentatives.getText().toString());
            }
            else {
                min = 1;
                max = 100;
                ten = 7;
            }
            if (min<max && ten>0) {
                editor.putInt(MIN_VALUE, min);
                editor.putInt(MAX_VALUE, max);
                editor.putInt(MAX_TENTS, ten);
                editor.apply();
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
            else{
                toast.setText("Inserisci dei dati coerenti!");
                toast.show();
            }

        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Holder2 holder2 = new Holder2();
    }
}
