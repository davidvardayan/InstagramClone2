package com.davidvardanyan.instagramclone2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allKickboxers;

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

        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("kickboxer");
                parseQuery.getInBackground("hTZ9urAt23", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){
                          txtGetData.setText(object.get("name") + " -" + " Punch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });


            btnGetAllData.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    allKickboxers = "" ;

                    ParseQuery<ParseObject> queryAll  = ParseQuery.getQuery("kickboxer");
                    queryAll.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (e == null){
                                if (objects.size() > 0){
                                    for (ParseObject kickboxer : objects){
                                        allKickboxers = allKickboxers + kickboxer.get("name") + "\n";
                                    }
                                    FancyToast.makeText(SignUp.this, allKickboxers,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                                } else {
                                    FancyToast.makeText(SignUp.this, "Error",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                                }

                            }


                        }
                    });
                }
            });
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
