package com.example.loginshareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static SharedPreferences mSharedPref;
    private final String sharedPrefFile = "com.example.loginshareapp";
    static String KEY = "key";
    private EditText et1, et2;
    private Button btn1;
    private Boolean mLogin;
    public static final String MESSAGE_EXTRA = "MESSAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        btn1 = findViewById(R.id.btn1);
        mLogin = mSharedPref.getBoolean(KEY, false);

        if(mLogin){
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLogin = true;
                SharedPreferences.Editor editor = mSharedPref.edit();
                editor.putBoolean(KEY, true);
                editor.apply();
                String message = et1.getText().toString();
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                intent.putExtra(MESSAGE_EXTRA, message);
                startActivityForResult(intent, 1);
            }
        });
    }

    static void getLogout(){
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(KEY, false);
        editor.apply();
    }

    // if(et1.getText().equals("pakjoko") && et2.getText().equals("yangpentingcuan"))
}