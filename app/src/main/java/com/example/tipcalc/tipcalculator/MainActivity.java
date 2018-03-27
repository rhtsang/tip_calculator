package com.example.tipcalc.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText billEditText;
    private SeekBar tipSeekbar;
    private TextView tipPctTextView;
    private TextView tipAmtTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billEditText = (EditText) findViewById(R.id.billEditTextId);
        tipSeekbar = (SeekBar) findViewById(R.id.tipSeekbarId);
        tipPctTextView = (TextView) findViewById(R.id.tipPctTextViewId);
        tipAmtTextView = (TextView) findViewById(R.id.tipAmtTextViewId);

        Toast.makeText(getApplication(), "Hello!", Toast.LENGTH_SHORT).show();

        billEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!billEditText.getText().toString().equals("")) {
                    //tipAmtTextView.setText("You should tip $" + Float.toString(calculate()) + '0');
                    tipAmtTextView.setText("You should tip $" + String.format("%.2f",calculate()));
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid bill amount.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        tipSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tipPctTextView.setText(Integer.toString(tipSeekbar.getProgress()) + '%');

                if (!billEditText.getText().toString().equals("")) {
                    //tipAmtTextView.setText("You should tip $" + Float.toString(calculate()) + '0');
                    tipAmtTextView.setText("You should tip $" + String.format("%.2f",calculate()));
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid bill amount.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private float calculate() {
        float result;
        float tipMultiplier;
        float billAmt;

        tipMultiplier = tipSeekbar.getProgress() / 100f;
        billAmt = Float.parseFloat(billEditText.getText().toString());
        result = billAmt * tipMultiplier;

        return result;
    }

}
