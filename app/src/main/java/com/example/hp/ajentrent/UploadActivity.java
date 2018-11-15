package com.example.hp.ajentrent;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UploadActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
   // private static final int Gallery_Request = 1;

    private EditText EditAmount, EditAdress, EditArea, EditPhone;
    private Button mButtonChoose;
    private Button mButtonUpload;

    private ImageView mImageView;
    private ProgressBar progressBar ;
    private Spinner mSpinnerHouseType;
    private Spinner mSpinnerWashingManchine;
    private Spinner mSpinnerAc_nonac;
    private Spinner mSpinnerFurnished;
    private Spinner mSpinnerAttached;
    private Spinner mSpinnerPet;
    private ImageButton mImageButton;
    private FirebaseAuth firebaseAuth;
    private StorageReference mStorageRef;
    private FirebaseStorage Firestore;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    private Uri mImageUri, Url;

    private DatabaseReference mDatabaseRef, DatabaseRef;
    private FirebaseDatabase mFirbaseData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        firebaseAuth = FirebaseAuth.getInstance();
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("AgentRent");

        mButtonChoose = findViewById(R.id.button_choose);
        mButtonUpload = findViewById(R.id.button_upload);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        mImageView = findViewById(R.id.image_view);

        progressBar  = findViewById(R.id.progress_bar);

        EditAmount = findViewById(R.id.amount);
        EditAdress = findViewById(R.id.Location);
        EditArea = findViewById(R.id.SquareArea);
        EditPhone = findViewById(R.id.PhoneNum);
        mSpinnerHouseType = findViewById(R.id.spinner1);
        mSpinnerAc_nonac = findViewById(R.id.spinner2);
        mSpinnerFurnished = findViewById(R.id.spinner3);
        mSpinnerPet = findViewById(R.id.spinner4);
        mSpinnerWashingManchine = findViewById(R.id.spinner5);
        mSpinnerAttached = findViewById(R.id.spinner6);
        //mImageButton = findViewById(R.id.imageButton);

        Firestore = FirebaseStorage.getInstance();
        mStorageRef = Firestore.getInstance().getReference("Images");

        DatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mFirbaseData = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirbaseData.getReference("House_Description_Master");


        mButtonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });


        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });


        final ArrayList<String> ac_NonAc = new ArrayList<>();
        final ArrayList<String> attachedBathroom = new ArrayList<>();
        final ArrayList<String> furnished = new ArrayList<>();
        final ArrayList<String> houseType = new ArrayList<>();
        final ArrayList<String> petsAllowed = new ArrayList<>();
        final ArrayList<String> washingMachine = new ArrayList<>();

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //House type
                String s1 = (String) dataSnapshot.child("House_Type").child("01").getValue();
                String s2 = (String) dataSnapshot.child("House_Type").child("02").getValue();
                String s3 = (String) dataSnapshot.child("House_Type").child("03").getValue();
                String s4 = (String) dataSnapshot.child("House_Type").child("04").getValue();

                //ac_non ac
                String s5 = (String) dataSnapshot.child("Ac_NonAc").child("05").getValue();
                String s6 = (String) dataSnapshot.child("Ac_NonAc").child("06").getValue();

                //furnished
                String s7 = (String) dataSnapshot.child("Furnished").child("07").getValue();
                String s8 = (String) dataSnapshot.child("Furnished").child("08").getValue();
                String s9 = (String) dataSnapshot.child("Furnished").child("09").getValue();

                //Pets_allowed
                String s10 = (String) dataSnapshot.child("Pets_Allowed").child("10").getValue();
                String s11 = (String) dataSnapshot.child("Pets_Allowed").child("11").getValue();

                //Washing_machine
                String s12 = (String) dataSnapshot.child("Washing_Machine").child("12").getValue();
                String s13 = (String) dataSnapshot.child("Washing_Machine").child("13").getValue();

                //Attached_bathroom
                String s14 = (String) dataSnapshot.child("Attached_Bathroom").child("14").getValue();
                String s15 = (String) dataSnapshot.child("Attached_Bathroom").child("15").getValue();


                houseType.add(s1);
                houseType.add(s2);
                houseType.add(s3);
                houseType.add(s4);

                ac_NonAc.add(s5);
                ac_NonAc.add(s6);

                furnished.add(s7);
                furnished.add(s8);
                furnished.add(s9);

                petsAllowed.add(s10);
                petsAllowed.add(s11);

                washingMachine.add(s12);
                washingMachine.add(s13);

                attachedBathroom.add(s14);
                attachedBathroom.add(s15);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mDatabaseRef.addListenerForSingleValueEvent(eventListener);

        ArrayAdapter house_Type_Adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, houseType);
        mSpinnerHouseType.setAdapter(house_Type_Adapter);

        ArrayAdapter ac_NonAc_Adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ac_NonAc);
        mSpinnerAc_nonac.setAdapter(ac_NonAc_Adapter);

        ArrayAdapter furnished_Adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, furnished);
        mSpinnerFurnished.setAdapter(furnished_Adapter);

        ArrayAdapter pets_Allowed_Adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, petsAllowed);
        mSpinnerPet.setAdapter(pets_Allowed_Adapter);

        ArrayAdapter washingMachine_Adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, washingMachine);
        mSpinnerWashingManchine.setAdapter(washingMachine_Adapter);

        ArrayAdapter attached_Bathroom_Adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, attachedBathroom);
        mSpinnerAttached.setAdapter(attached_Bathroom_Adapter);

    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
            getFileExtension(mImageUri);
            fileReference.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();

                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {

                        Uri downUri = task.getResult();
                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        String formatDate = df.format(c);

                        String value = EditAmount.getText().toString();
                        int Amount = Integer.parseInt(value);

                        String house_type = mSpinnerHouseType.getSelectedItem().toString();
                        String address = EditAdress.getText().toString();
                        String area = EditArea.getText().toString();
                        String phone = EditPhone.getText().toString();
                        String ac_nonAc = mSpinnerAc_nonac.getSelectedItem().toString();
                        String furnished = mSpinnerFurnished.getSelectedItem().toString();
                        String pets_Allowed = mSpinnerPet.getSelectedItem().toString();
                        String washing_machine = mSpinnerWashingManchine.getSelectedItem().toString();
                        String attched_Bathroom = mSpinnerAttached.getSelectedItem().toString();
                        //String mstorageUrl=taskSnapshot.toString();
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        String Uid = user.getUid();
                        OwnerUploadModel upload = new OwnerUploadModel(Uid,formatDate, Amount, house_type, address, area, ac_nonAc, furnished, pets_Allowed, washing_machine, attched_Bathroom,
                                downUri.toString(),phone);

                        String uploadId = DatabaseRef.push().getKey();
                        DatabaseRef.child(uploadId).setValue(upload);

                        new Thread(new Runnable() {
                            public void run() {
                                while (progressStatus < 100) {
                                    progressStatus += 10;
                                    // Update the progress bar and display the
                                    //current value in the text view
                                    handler.post(new Runnable() {
                                        public void run() {
                                            progressBar.setProgress(progressStatus);

                                        }

                                    });
                                    try {
                                        // Sleep for 200 milliseconds.
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                               // progressStatus=0;
                            }

                            //
                        }).start();
                        Toast.makeText(UploadActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();

                    }
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

//        @Override
//        public boolean onSupportNavigateUp () {
//            onBackPressed();
//            return true;
//        }

}
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                            mProgressBar.setProgress((int) progress);
//                        }
//                    });
//
//            fileReference.putFile(mImageUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mProgressBar.setProgress(0);
//                                }
//                            }, 500);


