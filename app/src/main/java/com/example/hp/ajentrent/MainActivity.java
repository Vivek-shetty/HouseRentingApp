package com.example.hp.ajentrent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.FirebaseFirestore;

//import java.util.HashMap;
//import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EditEmail, EditPass;
    Button btnsubmit;
    TextView EditReg;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    // private FirebaseFirestore fstore;
//    FirebaseDatabase database;
//    DatabaseReference ref;
//    getters getobj;
//    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null)
        {
            //profile page
            finish();
            startActivity(new Intent(getApplicationContext(),Main3Activity.class));
        }
        EditEmail = findViewById(R.id.textView);
        EditPass = findViewById(R.id.textView2);
        btnsubmit = findViewById(R.id.button);
        EditReg = (TextView) findViewById(R.id.textView3);
        progressDialog=new ProgressDialog(this);

        btnsubmit.setOnClickListener(this);
        EditReg.setOnClickListener(this);

    }

    private void userLogin()
    {
        String email=EditEmail.getText().toString().trim();
        String password=EditPass.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering please wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //start profile
                            finish();
                            startActivity(new Intent(getApplicationContext(),Main3Activity.class));
                        }else
                        {
                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                       progressDialog.dismiss();
                    }
                });
    }
        @Override
        public void onClick (View view){
        if(view==btnsubmit)
        {
            userLogin();
        }
        if(view==EditReg)
        {
            finish();
            startActivity(new Intent(this,Main2Activity.class));
        }

        }
    }

        //    }

        // fstore=FirebaseFirestore.getInstance();

//        btnsubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addfirebase();
//                String name= EditName.getText().toString();
//                String password=EditPass.getText().toString();
//                Map<String,String> UserMap=new HashMap<>();
//                 UserMap.put("name",name);
//                 UserMap.put("password",password);
////
//                 fstore.collection("user").add(UserMap);
//
//           }
//        });

//    private void getValues() {
////        getobj.setID(id.toString());
//        getobj.setName(EditName.getText().toString());
//        getobj.setPassword(EditPass.getText().toString());
//
//    }

//    public void addfirebase(View view) {
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                getValues();
//                ref.child("user").setValue(getobj);
//                Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }


//                } {
//                    @Override
//                    public void (View view) {
//
//                    }

//
//                                                     String name = EditName.getText().toString();
//                String password = EditPass.getText().toString();
//
//                if (!TextUtils.isEmpty(name)) {
//                    String id = databaseSample.push().getKey();
//                    getters obj = new getters(id, name, password);
//                    databaseSample.child(id).setValue(obj);
//                    Toast.makeText(this, "user added", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(this, "enter name", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }






//EditName=findViewById(R.id.textView);
//        EditPass=findViewById(R.id.textView2);
//        btnsubmit=findViewById(R.id.button);
//        database=FirebaseDatabase.getInstance();
//        databaseSample= database.getReference();
//        // fstore=FirebaseFirestore.getInstance();
//
//        btnsubmit.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        addfirebase();
////                String name= EditName.getText().toString();
////                String password=EditPass.getText().toString();
////                Map<String,String> UserMap=new HashMap<>();
////                 UserMap.put("name",name);
////                 UserMap.put("password",password);
//////
////                 fstore.collection("user").add(UserMap);
////
//        }
//        });
//        }
//
//private void addfirebase() {
//
//
//        String name = EditName.getText().toString();
//        String password = EditPass.getText().toString();
//
//        if (!TextUtils.isEmpty(name)) {
//        String id = databaseSample.push().getKey();
//        getters obj = new getters(id, name, password);
//        databaseSample.child(id).setValue(obj);
//        Toast.makeText(this, "user added", Toast.LENGTH_LONG).show();
//        }
//        else {
//        Toast.makeText(this, "enter name", Toast.LENGTH_LONG).show();
//        }
//
//        }
//{
//        "rules": {
//        ".read": true,
//        ".write": true,
//        "users": {
//        "$user": {
//        "name": {
//        ".validate": "newData.isString() && newData.val().length < 50"
//        },
//        "email": {
//        ".validate": "newData.isString() && newData.val().matches(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$/i)"
//        }
//        }
//        }
//        }
//        }





//working insert code

//    EditText EditName, EditPass;
//    Button btnsubmit;
//    // private FirebaseFirestore fstore;
//    FirebaseDatabase database;
//    DatabaseReference ref;
////    getters getobj;
////    String id;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        EditName = findViewById(R.id.textView);
//        EditPass = findViewById(R.id.textView2);
//        btnsubmit = findViewById(R.id.button);
//        database = FirebaseDatabase.getInstance();
//        ref = database.getReference("ajentrent");
////        getobj = new getters();
////        id = ref.push().getKey();
//        btnsubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String userId = ref.push().getKey();
//
//                String name = EditName.getText().toString();
//                String password = EditPass.getText().toString();
//                getters getobj = new getters(name, password);
//
//                //  getValues();
//                if (!TextUtils.isEmpty(name)) {
//                    ref.child(userId).setValue(getobj);
//                    Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_LONG).show();
//                }
//            }
//        });