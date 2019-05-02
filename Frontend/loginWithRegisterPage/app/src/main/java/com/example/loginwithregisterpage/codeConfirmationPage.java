package com.example.loginwithregisterpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class codeConfirmationPage extends AppCompatActivity {


    private EditText codeSpace;
    private String theEnteredCode;
    private Button sumbitBtn;
    final String codeFromDB = getCodeFromDB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_confirmation_page);


         codeSpace = findViewById(R.id.codeSpace);
         theEnteredCode =  codeSpace.getText().toString();
         sumbitBtn = findViewById(R.id.submitButton);





         sumbitBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(theEnteredCode.equals(codeFromDB)) {
                     sendVerification();
                     openSignInPage();
                 }

             }
         });


    }

    /*
    Get request to receive the new users code from the database
     */
    private String getCodeFromDB()
    {
        //TODO
        String code = "";

        return code;
    }

    /*
    Post request to send that the user has entered the correct code
     */
    private void sendVerification()
    {
        //TODO

    }

    private void openSignInPage()
    {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
