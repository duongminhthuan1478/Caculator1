package com.example.dell.caculator;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CaculatorFragment extends Fragment {
    TextView numberTextView;
    TextView resultTextView;

    private Button buttonNumber0, buttonNumber1, buttonNumber2, buttonNumber3, buttonNumber4,
            buttonNumber5, buttonNumber6, buttonNumber7, buttonNumber8, buttonNumber9;
    private Button buttonSub, buttonAdd, buttonMultip, buttonDiv, buttonEquals,
            buttonPercent, buttonAC, buttonDot;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char PERCENT = '%';

    private final char EQU = 0;
    private double value1 = Double.NaN;
    private double value2;

    private char ACTION;

    private final String SHARED_PREFERENCES_NAME = "save_result";
    private final String SAVE_RESULT = "result";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_caculator, container, false);

        numberTextView = (TextView) rootView.findViewById(R.id.textview_number);
        buttonNumber0 = (Button)  rootView.findViewById(R.id.button0);
        buttonNumber1 = (Button)  rootView.findViewById(R.id.button1);
        buttonNumber2 = (Button)  rootView.findViewById(R.id.button2);
        buttonNumber3 = (Button)  rootView.findViewById(R.id.button3);
        buttonNumber4 = (Button)  rootView.findViewById(R.id.button4);
        buttonNumber5 = (Button)  rootView.findViewById(R.id.button5);
        buttonNumber6 = (Button)  rootView.findViewById(R.id.button6);
        buttonNumber7 = (Button)  rootView.findViewById(R.id.button7);
        buttonNumber8 = (Button)  rootView.findViewById(R.id.button8);
        buttonNumber9 = (Button)  rootView.findViewById(R.id.button9);
        buttonSub = (Button)  rootView.findViewById(R.id.buttonSub);
        buttonAdd = (Button)  rootView.findViewById(R.id.buttonAdd);
        buttonMultip = (Button)  rootView.findViewById(R.id.buttonMultip);
        buttonDiv = (Button)  rootView.findViewById(R.id.buttonDiv);
        buttonEquals = (Button)  rootView.findViewById(R.id.buttonEquals);
        buttonAC = (Button)  rootView.findViewById((R.id.buttonAC));
        buttonDot = (Button)  rootView.findViewById(R.id.buttonDot);
        buttonPercent = (Button)  rootView.findViewById(R.id.buttonPercent);
        resultTextView = (TextView)  rootView.findViewById(R.id.textview_display);

        //display OptionsMenu
        setHasOptionsMenu(true );

        receiveDataFromButton();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        readData();


    }

    public void receiveDataFromButton() {
        buttonNumber0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "0");
            }
        });

        buttonNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "1");
            }
        });
        buttonNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "2");
            }
        });

        buttonNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "3");
            }
        });
        buttonNumber4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "4");
            }
        });

        buttonNumber5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "5");
            }
        });
        buttonNumber6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "6");
            }
        });

        buttonNumber7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "7");
            }
        });
        buttonNumber8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "8");
            }
        });

        buttonNumber9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + "9");
            }
        });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTextView.setText(numberTextView.getText().toString() + ".");
            }
        });


        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = SUBTRACTION;
                resultTextView.setText(String.valueOf(value1) + "-");
                numberTextView.setText("");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = ADDITION;
                resultTextView.setText(String.valueOf(value1) + "+");
                numberTextView.setText("");
            }
        });
        buttonMultip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = MULTIPLICATION;
                resultTextView.setText(String.valueOf(value1) + "*");
                numberTextView.setText("");
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = DIVISION;
                resultTextView.setText(String.valueOf(value1) + "/");
                numberTextView.setText("");
            }
        });
        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = PERCENT;
                resultTextView.setText(String.valueOf(value1) + "%");
                numberTextView.setText("");

            }
        });
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = EQU;
//                resultTextView.setText(removeTrailingZero(resultTextView.getText().toString()
//                        + String.valueOf(value2) + "= "));
                numberTextView.setText(removeTrailingZero(String.valueOf(value1)));


            }
        });

        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberTextView.getText().length() > 0) {
                    CharSequence name = numberTextView.getText().toString();
                    numberTextView.setText(name.subSequence(0, name.length() - 1));
                } else {
                    clear();
                }
            }
        });

    }

    private void clear() {
        value1 = Double.NaN;
        value2 = Double.NaN;
        numberTextView.setText(null);
        resultTextView.setText(null);
    }





    private String removeTrailingZero(String formattingInput) {
        if (!formattingInput.contains(".")) {
            return formattingInput;
        }
        int dotPosition = formattingInput.indexOf(".");
        String newValue = formattingInput.substring(dotPosition, formattingInput.length());
        if (newValue.equals(".0")) {
            return formattingInput.substring(0, dotPosition);
        }
        return formattingInput;
    }

    private void compute() {

        if (!Double.isNaN(value1)) {
            value2 = Double.parseDouble(numberTextView.getText().toString());
            switch (ACTION) {
                case ADDITION:
                    value1 = value1 + value2;
                    break;
                case SUBTRACTION:
                    value1 = value1 - value2;
                    break;
                case MULTIPLICATION:
                    value1 = value1 * value2;
                    break;
                case DIVISION:
                    value1 = value1 / value2;
                    break;
                case PERCENT:
                    value1 = (value1 + value2) / 100;
                    break;
                case EQU:
                    break;
            }


        } else {
            value1 = Double.parseDouble(numberTextView.getText().toString());

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                clear();
                break;
            case R.id.menu_save:
                writeData();
                onStart();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void writeData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SAVE_RESULT, (long) value1);
        editor.apply();
        Toast.makeText(getActivity(), "Save Success fully", Toast.LENGTH_SHORT).show();
        //editor.commit();
    }

    private void readData() {
        SharedPreferences sharedPreferences =getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        double result = sharedPreferences.getLong(SAVE_RESULT,1);
        numberTextView.setText(String.valueOf(result));

    }
}
