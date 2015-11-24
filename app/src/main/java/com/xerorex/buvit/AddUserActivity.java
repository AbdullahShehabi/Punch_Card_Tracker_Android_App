package com.xerorex.buvit;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    private boolean submitButtonState = false;
    private boolean fname = false;
    private boolean lname = false;
    private boolean email = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        configureTextFields();
    }

    private void configureTextFields() {

        enableSubmitButton(fname, lname, email);

        final EditText firstNameField = (EditText) findViewById(R.id.firstNameField);
        final EditText lastNameField = (EditText) findViewById(R.id.lastNameField);
        final EditText emailAddressField = (EditText) findViewById(R.id.emailAddress);

        firstNameField.setHint("First Name");
        lastNameField.setHint("Last Name");
        emailAddressField.setHint("Email");

        firstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                if (text.length() != 0)
                    fname = true;
                else {
                    fname = false;
                }

                enableSubmitButton(fname, lname, email);
            }
        });

        lastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                if(text.length() != 0)
                    lname = true;
                else{
                    lname = false;
                }

                enableSubmitButton(fname, lname, email);
            }
        });

        emailAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                if (text.length() != 0)
                    email = true;
                else {
                    email = false;
                }

                enableSubmitButton(fname, lname, email);
            }
        });

    }

    private void enableSubmitButton(boolean fname, boolean lname, boolean email){

        ImageButton submitButton = (ImageButton) findViewById(R.id.addUserSubmitButton);

        //Change if statements to confirm name and email are real inputs
        if(fname && lname && email) {
            Drawable submitButtonIcon = getResources().getDrawable(R.drawable.submit_true);
            submitButton.setImageDrawable(submitButtonIcon);

            //Enter Submit button functionality here

            submitButtonState = true;
        }
        else{
            Drawable submitButtonIcon = getResources().getDrawable(R.drawable.submit_false);
            submitButton.setImageDrawable(submitButtonIcon);

            submitButtonState = false;
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
