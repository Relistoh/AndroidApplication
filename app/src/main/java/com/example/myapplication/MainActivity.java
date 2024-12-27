package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получение элементов интерфейса
        EditText editTextA = findViewById(R.id.editText_a);
        EditText editTextX = findViewById(R.id.editText_x);
        EditText editTextC = findViewById(R.id.editText_c);
        CheckBox checkBoxShowFunction = findViewById(R.id.checkBox_showFunction);
        Spinner spinnerChoices = findViewById(R.id.spinner_choices);
        Button buttonCalculate = findViewById(R.id.button_calculate);
        TextView textViewResult = findViewById(R.id.textView_result);
        TextView textViewFunctionUsed = findViewById(R.id.textView_function_used);

        // Настройка Spinner (ComboBox) для выбора функции
        String[] functions = {"Линейная функция: y = ax + c", "Квадратичная функция: y = ax^2 + c", "Экспоненциальная функция: y = a * e^x"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, functions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoices.setAdapter(adapter);

        // Обработка нажатия на кнопку
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Получение введенных данных
                    double a = Double.parseDouble(editTextA.getText().toString());
                    double x = Double.parseDouble(editTextX.getText().toString());
                    double c = Double.parseDouble(editTextC.getText().toString());

                    // Получение выбранной функции из Spinner
                    String selectedFunction = spinnerChoices.getSelectedItem().toString();
                    double result = 0;
                    String functionUsed = "";

                    // Выполнение вычислений в зависимости от выбранной функции
                    if (selectedFunction.equals("Линейная функция: y = ax + c")) {
                        functionUsed = "Линейная функция";
                        result = a * x + c;
                    } else if (selectedFunction.equals("Квадратичная функция: y = ax^2 + c")) {
                        functionUsed = "Квадратичная функция";
                        result = a * Math.pow(x, 2) + c;
                    } else if (selectedFunction.equals("Экспоненциальная функция: y = a * e^x")) {
                        functionUsed = "Экспоненциальная функция";
                        result = a * Math.exp(x) + c;
                    }

                    // Вывод результата
                    textViewResult.setText("Результат: " + result);

                    // Проверяем, если CheckBox "Показать функцию" выбран
                    if (checkBoxShowFunction.isChecked()) {
                        textViewFunctionUsed.setText("Функция, использованная для вычисления: " + functionUsed);
                    } else {
                        textViewFunctionUsed.setText("");  // Скрываем информацию о функции
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Пожалуйста, введите корректные значения", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
