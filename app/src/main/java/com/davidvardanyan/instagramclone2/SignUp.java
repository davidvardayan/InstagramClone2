package com.davidvardanyan.instagramclone2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

// 03.22

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail,edtUsername,edtPassword;
    private Button btnSignUp,btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");


        edtEmail = findViewById(R.id.edtEnterEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtEnterPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

         if (ParseUser.getCurrentUser() != null){
             ParseUser.getCurrentUser().logOut();
         }










    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnSignUp:

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUsername.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUp.this,appUser.getUsername() +
                                    " is signed up", Toast.LENGTH_SHORT,
                                    FancyToast.SUCCESS,true).show();
                        }else {
                            FancyToast.makeText(SignUp.this,
                                            e.getMessage() + "There was an error", Toast.LENGTH_SHORT,
                                    FancyToast.ERROR,true).show();
                        }

                        progressDialog.dismiss();
                    }
                });
                break;

            case R.id.btnLogIn:
                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
