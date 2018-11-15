package com.example.hp.ajentrent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Descriptive extends AppCompatActivity {

    private TextView textTitle,DetailTitle,pplace;
    private ImageView mImageTV;
    boolean isImageFitToScreen;
    Button signOut;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "Description activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptive);
        firebaseAuth= FirebaseAuth.getInstance();
        //actionbar
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("House Details");


        //set back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //intialize views
        textTitle=findViewById(R.id.titleView2);
        mImageTV=findViewById(R.id.imageView2);

           getIncomingIntent();


        mImageTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    mImageTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    mImageTV.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    mImageTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    mImageTV.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });
    }

    private void getIncomingIntent(){
        Log.d(TAG, "get incoming intent: checking for incoming intent");
        if(getIntent().hasExtra("Image") && getIntent().hasExtra("Amount")){
            Log.d(TAG, "getIncomingIntent: found intent extra");

            String image = getIntent().getStringExtra("Image");
            int Amount = Integer.parseInt(getIntent().getExtras().get("Amount").toString());
            String housetype = getIntent().getStringExtra("HouseType");
            String acNonAc = getIntent().getStringExtra("Ac/Non-Ac");
            String furnished = getIntent().getStringExtra("Furnished");
            String squareArea = getIntent().getStringExtra("SquareArea");
            String washingMachine = getIntent().getStringExtra("WashingMachine");
            String location = getIntent().getStringExtra("Location");
            String pphoneNum=getIntent().getStringExtra("phoneNum");
            String pets = getIntent().getStringExtra("Pets");
            String bath = getIntent().getStringExtra("Bath");

            setContent(image,Amount,housetype,acNonAc, furnished,squareArea,washingMachine,location,pphoneNum,pets,bath);

        }
    }

    private void setContent(String image, int amount, String House_type,String ac, String furnished, String sq, String wash, String loctaion,String phonenumber, String pets, String bath) {

        Log.d(TAG, "setContent: setting name and image to the widget");

        TextView Amount = findViewById(R.id.titleView2);
        Amount.setText(String.valueOf(amount));

        ImageView img = findViewById(R.id.imageView2);
        Picasso.get().load(image).into(img);

        TextView houseType = findViewById(R.id.textHouseType);
        houseType.setText(House_type);

        TextView AC = findViewById(R.id.textAc);
        AC.setText(ac);

        TextView fur = findViewById(R.id.textFurnished);
        fur.setText(furnished);

        TextView squ = findViewById(R.id.textSquareArea);
        squ.setText(sq);

        TextView Wash = findViewById(R.id.textWashingMachine);
        Wash.setText(wash);

        TextView loc = findViewById(R.id.textLoaction);
        loc.setText(loctaion);

        TextView phone=findViewById(R.id.textPhone);
        phone.setText(phonenumber);

        TextView pet = findViewById(R.id.textPets);
        pet.setText(pets);

        TextView Bath = findViewById(R.id.textAttachedBathroom);
        Bath.setText(bath);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
