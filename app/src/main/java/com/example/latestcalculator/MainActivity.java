package com.example.latestcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;
import android.widget.TextView;

import android.os.Bundle;
import android.view.View;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MaterialButton button_c, button_open_bracket, button_closed_bracket, button_equal, button_ac;
    MaterialButton button_add, button_subtract, button_multiply, button_divide, button_decimal;
    MaterialButton button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    TextView result, solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
        assignId(button_c, R.id.button_c);
        assignId(button_open_bracket, R.id.button_open_bracket);
        assignId(button_closed_bracket, R.id.button_closed_bracket);
        assignId(button_equal, R.id.button_equals);
        assignId(button_ac, R.id.button_ac);
        assignId(button_add, R.id.button_add);
        assignId(button_subtract, R.id.button_subtract);
        assignId(button_multiply, R.id.button_multiply);
        assignId(button_divide, R.id.button_divide);
        assignId(button_decimal, R.id.button_decimal);


    }

    void assignId(MaterialButton b, int id){
        b = findViewById(id);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String text = button.getText().toString();
        String data_to_calculate = solution.getText().toString();

        if(text.equals("AC")){
            solution.setText("");
            result.setText("0");
            return ;
        }

        if(text.equals("=")){
            solution.setText(result.getText());
            return ;
        }

        if(text.equals("C")){
            data_to_calculate = data_to_calculate.substring(0, data_to_calculate.length()-1);
        }
        else{
            data_to_calculate += text;
        }


        solution.setText(data_to_calculate);

        String finalResult = getResult(data_to_calculate);

        if(!finalResult.equals("Error")){
            result.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }
        catch (Exception e){
            return "Error";
        }
    }
}