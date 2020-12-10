package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Class variables; also are called 'Fields'

    private TextView resultText;
    private Button CalculateBtn;
    private RadioButton radioButtonFemale;
    private RadioButton radioButtonMale;
    private EditText editAge;
    private EditText editFeed;
    private EditText editInch;
    private EditText editWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        //saat button calculate di klik
        setUpButtonClickListener();

        //resultText.setText("I update my Text View from manual code");

//        String alertText = "This is my variable text ";
//        kalau toast otomatis langsung gk perlu nyebutin class java nya langsung this
//        Toast.makeText(this,alertText,Toast.LENGTH_LONG).show();




    }

    //CTRL + F + ENTER = refactor class di declare ke class utama dlm bentuk private class
    //CTRL + M + ENTER = refactor sesuatu ke dalam method
    //Method untuk fungsi button
    private void setUpButtonClickListener() {
        CalculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //running code everytime you click
                //Toast.makeText(MainActivity.this, "Hello Calculator BMI!", Toast.LENGTH_LONG).show();
                double bmiResult = calculateBMI();

                String ageText = editAge.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    DisplayResult(bmiResult);
                } else {
                    DisplayGuindance(bmiResult);
                }
            }
        });
    }

    //Method untuk menghitung BMI awal + formula
    private double calculateBMI() {
        //1. get value from edt
        String feedText = editFeed.getText().toString();
        String inchText = editInch.getText().toString();
        String weightText = editWeight.getText().toString();

        //resultText.setText("Age : "+ ageText +", Feed : "+ feedText + ", Inc : " + inchText +", Weight : " + weightText );
        //calculate BMI

        //parse integer dri String
        //converting the number 'Strings' into Integer variables
        //int age = Integer.parseInt(ageText);
        int feed = Integer.parseInt(feedText);
        int inch = Integer.parseInt(inchText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feed * 12) + inch;

        //Height in meters is the inchs multiplied by0.0254
        double heightInMeters = totalInches * 0.0254;

        // BMI formula = weight in kg divided by height in meters square(pangkat)
        //double bmi = weight / (heightInMeters * heightInMeters);
        return weight / (heightInMeters * heightInMeters);
        // We must convert the decimal /double into a string for our textView
        // String bmiTextResult = String.valueOf(bmi);

    }

    //Method menampilkan hasil result
    private void DisplayResult(double bmi) {
        //decimal format
        DecimalFormat myDecimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormat.format(bmi);

        //resultText.setText("Your result of BMI : " +bmiTextResult);

        String fullResultString;
        if (bmi < 18.5) {
            //Display underweight
            fullResultString = bmiTextResult + "- You are underweight";
        } else if (bmi > 25) {
            //Display overweight
            fullResultString = bmiTextResult + "- You are overweight";
        } else {
            //healty
            fullResultString = bmiTextResult + "- You are healthy weight";
        }

        resultText.setText(fullResultString);

    }

    //Logic arahan yg terkait dengan umur dibawah 18 thn
    private void DisplayGuindance(double bmi) {
        DecimalFormat myDecimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormat.format(bmi);

        String fullResultString;

        if (radioButtonMale.isChecked()) {
            // Display for boy Guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor  for the healthy rance for boys";
        } else if (radioButtonFemale.isChecked()) {
            // Display for women Guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor  for the healthy rance for girls";
        } else {
            // Display for general Guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor  for the healthy rance for the healthy range";
        }

        resultText.setText(fullResultString);
    }

    //private hanya bisa di akses oleh kelar nya aja
    public void findView() {

        resultText = findViewById(R.id.text_view_bawah);

        radioButtonFemale = findViewById(R.id.rdb_female);
        radioButtonMale = findViewById(R.id.rdb_male);

        editAge = findViewById(R.id.edt_age);
        editFeed = findViewById(R.id.edt_feed);
        editInch = findViewById(R.id.edt_inch);
        editWeight = findViewById(R.id.edt_weight);

        CalculateBtn = findViewById(R.id.btn_calculate);

    }
}
