package com.example.mariya.calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//тестовый коммент
public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
    Button buttonPlus, buttonMinus, buttonMult, buttonDiv, buttonAC, buttonEq, buttonBackspace;
    TextView editText;
    float pre, post;
    boolean plus,minus,mult,div, eq, firstAfter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b0 = (Button) findViewById(R.id.b0);
        buttonPlus = (Button) findViewById(R.id.plus);
        buttonMinus = (Button) findViewById(R.id.minus);
        buttonMult = (Button) findViewById(R.id.mult);
        buttonDiv = (Button) findViewById(R.id.div);
        buttonAC = (Button) findViewById(R.id.AC);
        buttonEq = (Button) findViewById(R.id.eq);
        buttonBackspace = (Button) findViewById(R.id.backspace);



        editText = (TextView) findViewById(R.id.textView);

        editText.setText("0");
        pre=0;
        post=0;
        String preString="";
        firstAfter=false;

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.b1:
                        if (editText.getText().toString().equals("0") || firstAfter) {
                            editText.setText(R.string.button1);
                            firstAfter=false;
                        } else {
                            //editText.setText(editText.getText().toString() + R.string.button1);
                            editText.append("1");
                        }
                        break;
                    case R.id.b2:
                        if (editText.getText().toString().equals("0") || firstAfter) {
                            editText.setText(R.string.button2);
                            firstAfter=false;

                        } else {
                            editText.append("2");
                        }
                        break;
                    case R.id.b3:
                        if (editText.getText().toString().equals("0") || firstAfter) {
                            editText.setText(R.string.button3);
                            firstAfter=false;

                        } else {
                            editText.append("3");
                        }
                        break;
                    case R.id.b4:
                        if (editText.getText().toString().equals("0") || firstAfter) {
                            editText.setText(R.string.button4);
                            firstAfter=false;

                        } else {
                            editText.append("4");
                        }
                        break;
                    case R.id.b5:
                        if (editText.getText().toString().equals("0") || firstAfter) {
                            editText.setText(R.string.button5);
                            firstAfter=false;

                        } else {
                            editText.append("5");
                        }
                        break;
                    case R.id.b6:
                        if (editText.getText().toString().equals("0") || firstAfter) {
                            editText.setText(R.string.button6);
                            firstAfter=false;

                        } else {
                            editText.append("6");
                        }
                        break;
                    case R.id.b7:
                        if (editText.getText().toString().equals("0") || firstAfter) {
                            editText.setText(R.string.button7);
                        } else {
                            editText.append("7");
                        }
                        break;
                    case R.id.b8:
                        if (editText.getText().toString().equals("0") || firstAfter) {
                            editText.setText(R.string.button8);
                            firstAfter=false;

                        } else {
                            editText.append("8");
                        }
                        break;
                    case R.id.b9:
                        if (editText.getText().toString().equals("0") || firstAfter) {
                            editText.setText(R.string.button9);
                            firstAfter=false;

                        } else {
                            editText.append("9");
                        }
                        break;
                    case R.id.b0:
                        if (editText.getText().toString().equals("0")) {
                            editText.append("0");
                            firstAfter=false;

                        }
                        break;
                    case R.id.backspace:
                        if (editText.getText().length()==1) {
                            editText.setText("0");
                        } else {
                            editText.setText(editText.getText().toString().substring(0,editText.getText().length()-1));

                        }
                        break;
                    case R.id.AC:
                        editText.setText("0");
                        break;
                    case R.id.plus:

                        if (plus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre + post;
                            editText.setText(Float.toString(pre));
                            plus=false;
                        } else if (minus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre - post;
                            editText.setText(Float.toString(pre));
                            minus=false;
                        } else if (mult) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre * post;
                            editText.setText(Float.toString(pre));
                            mult=false;
                        } else if (div) {
                            post = Float.parseFloat(editText.getText().toString());
                            if (post!=0) {
                                pre = pre / post;
                                editText.setText(Float.toString(pre));
                                div=false;
                            }
                        }

                        pre = Float.parseFloat(editText.getText().toString());
                        plus=true;
                        firstAfter=true;
                        break;
                    case R.id.minus:
                        if (plus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre + post;
                            editText.setText(Float.toString(pre));
                            plus=false;
                        } else if (minus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre - post;
                            editText.setText(Float.toString(pre));
                            minus=false;
                        } else if (mult) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre * post;
                            editText.setText(Float.toString(pre));
                            mult=false;
                        } else if (div) {
                            post = Float.parseFloat(editText.getText().toString());
                            if (post!=0) {
                                pre = pre / post;
                                editText.setText(Float.toString(pre));
                                div=false;
                            }

                        }

                        pre = Float.parseFloat(editText.getText().toString());
                        minus=true;
                        firstAfter=true;
                        break;
                    case R.id.mult:
                        if (plus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre + post;
                            editText.setText(Float.toString(pre));
                            plus=false;
                        } else if (minus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre - post;
                            editText.setText(Float.toString(pre));
                            minus=false;
                        } else if (mult) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre * post;
                            editText.setText(Float.toString(pre));
                            mult=false;
                        } else if (div) {
                            post = Float.parseFloat(editText.getText().toString());
                            if (post != 0) {
                                pre = pre / post;
                                editText.setText(Float.toString(pre));
                                div = false;
                            }

                        }

                        pre = Float.parseFloat(editText.getText().toString());
                        mult=true;
                        firstAfter=true;
                        break;
                    case R.id.div:
                        if (plus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre + post;
                            editText.setText(Float.toString(pre));
                            plus=false;
                        } else if (minus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre - post;
                            editText.setText(Float.toString(pre));
                            minus=false;
                        } else if (mult) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre * post;
                            editText.setText(Float.toString(pre));
                            mult=false;
                        } else if (div) {
                            post = Float.parseFloat(editText.getText().toString());
                            if (post!=0) {
                                pre = pre / post;
                                editText.setText(Float.toString(pre));
                                div=false;
                            }

                        }

                        pre = Float.parseFloat(editText.getText().toString());
                        div=true;
                        firstAfter=true;
                        break;
                    case R.id.eq:
                        eq=true;
                        if (plus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre + post;
                            editText.setText(Float.toString(pre));
                            plus=false;
                        } else if (minus) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre - post;
                            editText.setText(Float.toString(pre));
                            minus=false;
                        } else if (mult) {
                            post = Float.parseFloat(editText.getText().toString());
                            pre = pre * post;
                            editText.setText(Float.toString(pre));
                            mult=false;
                        } else if (div) {
                            post = Float.parseFloat(editText.getText().toString());
                            if (post != 0) {
                                pre = pre / post;
                                editText.setText(Float.toString(pre));
                                div = false;
                            }
                        }

                        firstAfter=true;
                        break;
                }

            }
        };


        b1.setOnClickListener(onClickListener);
        b2.setOnClickListener(onClickListener);
        b3.setOnClickListener(onClickListener);
        b4.setOnClickListener(onClickListener);
        b5.setOnClickListener(onClickListener);
        b6.setOnClickListener(onClickListener);
        b7.setOnClickListener(onClickListener);
        b8.setOnClickListener(onClickListener);
        b9.setOnClickListener(onClickListener);
        b0.setOnClickListener(onClickListener);
        buttonAC.setOnClickListener(onClickListener);
        buttonPlus.setOnClickListener(onClickListener);
        buttonMult.setOnClickListener(onClickListener);
        buttonMinus.setOnClickListener(onClickListener);
        buttonDiv.setOnClickListener(onClickListener);
        buttonEq.setOnClickListener(onClickListener);
        buttonBackspace.setOnClickListener(onClickListener);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
