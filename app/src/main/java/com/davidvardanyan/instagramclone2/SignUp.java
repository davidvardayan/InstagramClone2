package com.davidvardanyan.instagramclone2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;



public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);

        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickPower);
        edtKickPower = findViewById(R.id.edtKickPower);
    }

    @Override
    public void onClick(View v) {

        final ParseObject kickboxer =  new ParseObject("kickboxer");
        kickboxer.put("name",edtName.getText().toString());
        kickboxer.put("punchSpeed",edtPunchSpeed.getText().toString());
        kickboxer.put("punchPower",edtPunchPower.getText().toString());
        kickboxer.put("kickSpeed",edtKickSpeed.getText().toString());
        kickboxer.put("kickPower",edtKickPower.getText().toString());
        kickboxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    //Toast.makeText(SignUp.this, kickboxer.get("name") + " is saved to server", Toast.LENGTH_SHORT).show();
                    FancyToast.makeText(SignUp.this, kickboxer.get("name") + " is saved to server",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                } else {
                    Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });

       

    }


//CLASS
}
