package com.xerorex.buvit;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.media.Image;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    private final int NUMBER_OF_PUNCHES = 9;
    private ImageButton[] arrayOfButtons;
    ImageButtonListener buttonListener;
    Drawable punchImageUnpressed;
    Drawable punchImagePressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

//        float imageButtonDimension = (Float) getResources().getDimension(R.dimen.user_profile_activity_button_dimen);
//        Drawable punchUnpressed = getDrawable(R.drawable.user_profile_punch_unpressed);
//        Drawable punchPressed = getDrawable(R.drawable.user_profile_punch_pressed);
//
//        punchImageUnpressed = resizeImageButton(punchUnpressed, imageButtonDimension);
//        punchImagePressed = resizeImageButton(punchPressed, imageButtonDimension);

        setUserProfileTitle();
        addPunchMarks();
    }

    private void setUserProfileTitle() {
        TextView activityTitle = (TextView) findViewById(R.id.user_profile_acitivity_username);
        activityTitle.setText("Random Name");
    }

    private void addPunchMarks() {

        int WRAP_CONTENT = RelativeLayout.LayoutParams.WRAP_CONTENT;

        //Relative layout from xml file reference pull up
        RelativeLayout userProfileLayout = (RelativeLayout) findViewById(R.id.user_profile_acitivity_layout);
        arrayOfButtons = new ImageButton[NUMBER_OF_PUNCHES];

        //ButtonLayoutParams params configuration
        RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        buttonLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        //Gridlayout params configuration
        RelativeLayout.LayoutParams gridLayoutParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        gridLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        //GridLayout setup
        GridLayout gridLayout = new GridLayout(getApplicationContext());
        gridLayout.setColumnCount(5);
        gridLayout.setRowCount(2);
        gridLayoutParams.addRule(RelativeLayout.BELOW, R.id.search_user_activity_title);
        gridLayout.setLayoutParams(gridLayoutParams);

        userProfileLayout.addView(gridLayout);

        int counter = 100;
        int padding = Math.round((Float) getResources().getDimension(R.dimen.user_profile_activity_button_padding));

        buttonListener = new ImageButtonListener();

        //For loop populating gridlayout with buttons
        for(ImageButton thisButton: arrayOfButtons){
            thisButton = new ImageButton(this);

            thisButton.setPadding(padding, padding, padding, padding);
            thisButton.setLayoutParams(buttonLayoutParams);
            thisButton.setBackgroundColor(Color.WHITE);

            thisButton.setImageResource(R.drawable.user_profile_punch_unpressed);
            thisButton.setTag(R.drawable.user_profile_punch_unpressed);
            thisButton.setOnClickListener(buttonListener);

            thisButton.setId(counter++);
            gridLayout.addView(thisButton);
        }

        ImageButton freePunch = new ImageButton(this);

        freePunch.setPadding(padding, padding, padding, padding);
        freePunch.setLayoutParams(buttonLayoutParams);
        freePunch.setBackgroundColor(Color.WHITE);

        freePunch.setImageResource(R.drawable.user_profile_punch_free_false);

        freePunch.setId(counter++);
        gridLayout.addView(freePunch);


    }

    private class ImageButtonListener implements View.OnClickListener{

        public void onClick(View view){

            if(allPreviousPressed(view) && !view.getTag().equals(R.drawable.user_profile_punch_pressed)){
                ImageButton imageButton = (ImageButton) view;
                imageButton.setImageResource(R.drawable.user_profile_punch_pressed);
                imageButton.setTag(R.drawable.user_profile_punch_pressed);
                imageButton.setOnClickListener(buttonListener);
            }
            else if (view.getTag().equals(R.drawable.user_profile_punch_pressed)){
                ImageButton imageButton = (ImageButton) view;
                imageButton.setImageResource(R.drawable.user_profile_punch_unpressed);
                imageButton.setTag(R.drawable.user_profile_punch_unpressed);
                imageButton.setOnClickListener(buttonListener);
            }

        }

        private boolean allPreviousPressed(View imageButtonView){

            Log.d("testing", Integer.toString(imageButtonView.getId()));

            int counter = 1;
            for(int i = imageButtonView.getId(); i > 100; i--){
                Drawable previous = getResources().getDrawable(imageButtonView.getId() - counter++);
                Drawable compare = getResources().getDrawable(R.drawable.user_profile_punch_unpressed);

                if(compare.equals(previous)) {
                    Log.d("testing", "False");
                    return false;
                }
            }
            return true;
        }

    }

//    private Drawable resizeImageButton(Drawable image, float scaleFactor) {
//
//        int newWidth = Math.round(image.getIntrinsicWidth() * scaleFactor);
//        int newHeight =  Math.round(image.getIntrinsicHeight() * scaleFactor);
//
//        image.setBounds(0, 0, newWidth, newHeight);
//        ScaleDrawable sd = new ScaleDrawable(image, 0, newWidth, newHeight);
//        sd.setLevel(8000);
//
//        return sd;
//    }

}
