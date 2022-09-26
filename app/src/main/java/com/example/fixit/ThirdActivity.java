package com.example.fixit;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.os.CountDownTimer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class ThirdActivity extends Activity {


    public static String generateNumber()
    {
        Random rnd = new Random();
        int[] prefixDigits = new int[]{0,2,4};
        String s = "05";
        // Generate Prefix Digit
        s += prefixDigits[rnd.nextInt(prefixDigits.length)];
        for(int i=1;i<=7;i++) {
            s += rnd.nextInt(10);
        }
        return s;
    }


    Button callBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);



        callBtn = findViewById(R.id.call);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String num = generateNumber();
                intent.setData(Uri.parse("tel:" + num));
                startActivity(intent);
            }
        });



        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button submit = (Button) findViewById(R.id.submit);

        final TextView SubmitText = (TextView) findViewById(R.id.SubmitText);
        Button Back2 = findViewById(R.id.Back2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitText.setText("דירגת את האפליקציה:" + ratingBar.getRating());
            }
        });

        Back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(intent);
            }

        });




    }


}
