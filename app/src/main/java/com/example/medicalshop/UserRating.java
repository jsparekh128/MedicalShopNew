package com.example.medicalshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class UserRating extends AppCompatActivity {
    RatingBar ratingbar1;
    Button btnrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rating);
        ratingbar1=(RatingBar)findViewById(R.id.appRatingBar);
        btnrate=(Button)findViewById(R.id.btnRate);
        LayerDrawable stars=(LayerDrawable)ratingbar1.getProgressDrawable();

        //Use for changing the color of RatingBar
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        btnrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserRating.this,"Thank You ! You Rated :"+String.valueOf(ratingbar1.getRating()),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(UserRating.this,HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

    }
}
