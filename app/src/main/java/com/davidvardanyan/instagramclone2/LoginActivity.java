package com.davidvardanyan.instagramclone2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail,edtLoginPassword;
    private Button btnLoginActivity,btnSignUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });

        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);


        btnLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLoginActivity:

                if (edtLoginEmail.getText().toString().equals("")
                        ||  edtLoginPassword.getText().toString().equals("")){

                    FancyToast.makeText(LoginActivity.this,
                            "Email and Password are required", Toast.LENGTH_SHORT,
                            FancyToast.INFO,true).show();
                }else {

                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                            edtLoginPassword.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {

                                    if (user != null && e == null) {
                                        FancyToast.makeText(LoginActivity.this, user.getUsername() +
                                                        " is logged in successfully", Toast.LENGTH_SHORT,
                                                FancyToast.SUCCESS, true).show();

                                        transitionToSocialMediaActivity();
                                    }else {
                                        FancyToast.makeText(LoginActivity.this,
                                                        "Wrong Email or Password", Toast.LENGTH_SHORT,
                                                FancyToast.INFO, true).show();
                                    }
                                }
                            });

                }

                break;
            case  R.id.btnSignUpLoginActivity:
                break;
        }
    }

    public void root2LayoutTapped(View view){

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    private void transitionToSocialMediaActivity(){

        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
