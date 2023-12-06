package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConvertorActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private Button buttonDecimal;
    private TextView sonucDecimal;
    private Spinner spinnerDecimal;
    private Button buttonByte;
    private TextView sonucByte;
    private Spinner spinnerByte;
    private EditText editTextNumber2;
    private RadioButton radioButton;
    private RadioButton radioButton2;
    private TextView sonucCelsius;
    private Button buttonCelsius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        initializeViews();

        setDecimalButtonClickListener();

        setDecimalSpinnerItemSelectedListener();

        setByteButtonClickListener();

        setByteSpinnerItemSelectedListener();

        setCelsiusButtonClickListener();

        setRadioButtonClickListener();
    }

    private void initializeViews() {
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonDecimal = findViewById(R.id.decimalButton);
        sonucDecimal = findViewById(R.id.sonucDecimal);
        spinnerDecimal = findViewById(R.id.decimalSpinner);
        buttonByte = findViewById(R.id.buttonByte);
        sonucByte = findViewById(R.id.sonucByte);
        spinnerByte = findViewById(R.id.spinner4);
        buttonCelsius = findViewById(R.id.buttonCelsius);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);

        initializeDecimalSpinner();
        initializeByteSpinner();
    }

    private void initializeDecimalSpinner() {
        List<String> categoriesD = new ArrayList<>();
        categoriesD.add("ikilik");
        categoriesD.add("sekizlik");
        categoriesD.add("onaltılık");

        ArrayAdapter<String> dataAdapterD = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesD);
        dataAdapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDecimal.setAdapter(dataAdapterD);
    }

    private void initializeByteSpinner() {
        List<String> categoriesB = new ArrayList<>();
        categoriesB.add("kilo byte");
        categoriesB.add("byte");
        categoriesB.add("kibibyte");
        categoriesB.add("bit");

        ArrayAdapter<String> dataAdapterB = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesB);
        dataAdapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerByte.setAdapter(dataAdapterB);
    }

    private void setDecimalButtonClickListener() {
        buttonDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertDecimal();
            }
        });
    }

    private void setDecimalSpinnerItemSelectedListener() {
        spinnerDecimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCategory = parentView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Seçilen Kategori (Decimal): " + selectedCategory,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    private void setByteButtonClickListener() {
        buttonByte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertByte();
            }
        });
    }

    private void setByteSpinnerItemSelectedListener() {
        spinnerByte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCategory = parentView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Seçilen Kategori (Byte): " + selectedCategory,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void convertDecimal() {
        String onlukSayiString = editTextNumber.getText().toString();

        try {
            int onlukSayi = Integer.parseInt(onlukSayiString);

            String selectedCategory = spinnerDecimal.getSelectedItem().toString();

            String resultText = "";
            switch (selectedCategory) {
                case "ikilik":
                    resultText = "Sonuç: İkilik-" + Integer.toBinaryString(onlukSayi);
                    break;
                case "sekizlik":
                    resultText = "Sonuç: Sekizlik-" + Integer.toOctalString(onlukSayi);
                    break;
                case "onaltılık":
                    resultText = "Sonuç: Onaltılık-" + Integer.toHexString(onlukSayi);
                    break;
            }

            sonucDecimal.setText(resultText);
        } catch (NumberFormatException e) {

            sonucDecimal.setText("Geçersiz giriş!");
        }
    }

    private void convertByte() {
        String megaByteString = editTextNumber.getText().toString();

        try {

            double megaByte = megaByteString.isEmpty() ? 0 : Double.parseDouble(megaByteString);

            String selectedCategory = spinnerByte.getSelectedItem().toString();
            String resultText = "";

            switch (selectedCategory) {
                case "kilo byte":
                    resultText = "Sonuç: " + convertMegaByteToKiloByte(megaByte) + " KB";
                    break;
                case "byte":
                    resultText = "Sonuç: " + convertMegaByteToByte(megaByte) + " Bytes";
                    break;
                case "kibibyte":
                    resultText = "Sonuç: " + convertMegaByteToKibiByte(megaByte) + " KiB";
                    break;
                case "bit":
                    resultText = "Sonuç: " + convertMegaByteToBit(megaByte) + " bits";
                    break;
            }

            sonucByte.setText(resultText);
        } catch (NumberFormatException e) {
            sonucByte.setText("Geçersiz giriş!");
        }
    }

    private void setCelsiusButtonClickListener() {
        buttonCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCelsius();
            }
        });
    }

    private void setRadioButtonClickListener() {
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Seçilen Birim: Fahrenheit", Toast.LENGTH_SHORT).show();
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Seçilen Birim: Kelvin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void convertCelsius() {
        String celsiusStr = editTextNumber2.getText().toString();

        try {

            double celsius = celsiusStr.isEmpty() ? 0 : Double.parseDouble(celsiusStr);
            String resultText = "";

            if (radioButton.isChecked()) {
                double fahrenheitValue = convertCelsiusToFahrenheit(celsius);
                resultText = "Sonuç: " + new DecimalFormat("#.##").format(fahrenheitValue) + " Fahrenheit";
            } else if (radioButton2.isChecked()) {
                double kelvinValue = convertCelsiusToKelvin(celsius);
                resultText = "Sonuç: " + new DecimalFormat("#.##").format(kelvinValue) + " Kelvin";
            } else {
                Toast.makeText(getApplicationContext(), "Lütfen bir birim seçin.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Sonucu TextView'de göster
            sonucCelsius.setText(resultText);
        } catch (NumberFormatException e) {
            sonucCelsius.setText("Geçersiz giriş!");
        }
    }

    private double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double convertCelsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private String convertMegaByteToKiloByte(double megaByte) {
        double kiloByte = megaByte * 1024;
        return new DecimalFormat("#.##").format(kiloByte);
    }

    private String convertMegaByteToByte(double megaByte) {
        double byteValue = megaByte * 1024 * 1024;
        return new DecimalFormat("#.##").format(byteValue);
    }

    private String convertMegaByteToKibiByte(double megaByte) {
        double kibiByte = megaByte * 1000;
        return new DecimalFormat("#.##").format(kibiByte);
    }

    private String convertMegaByteToBit(double megaByte) {
        double bitValue = megaByte * 8 * 1024 * 1024;
        return new DecimalFormat("#.##").format(bitValue);
    }
}
