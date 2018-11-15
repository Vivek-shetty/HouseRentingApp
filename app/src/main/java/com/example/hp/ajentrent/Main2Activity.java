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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

     EditText Fname,Lname,EditEmail,pasword,proffession;
     Spinner Gender;
    //private EditText Email,pasword;
     Button btnsubmit;
     TextView EditReg;
    getters getobj;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase FDB;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth= FirebaseAuth.getInstance();
        FDB=FirebaseDatabase.getInstance();
        ref=FDB.getReference("ajentrent");
        if(firebaseAuth.getCurrentUser()!=null)
        {
            //profile page
            finish();
            startActivity(new Intent(getApplicationContext(),Main3Activity.class));
        }
        Fname=(EditText)findViewById(R.id.textView5);
        Lname=(EditText)findViewById(R.id.textView6);

        EditEmail=(EditText)findViewById(R.id.textView);
        pasword=(EditText)findViewById(R.id.textView2);
        Gender=findViewById(R.id.gender);
        proffession=(EditText)findViewById(R.id.textView9);

        btnsubmit=(Button) findViewById(R.id.button);
        progressDialog =new ProgressDialog(this);
        EditReg=(TextView)findViewById(R.id.textView3);



        btnsubmit.setOnClickListener(this);
        EditReg.setOnClickListener(this);



    }

    private void registerUser()
    {
        final String fname=Fname.getText().toString().trim();
        final String lname=Lname.getText().toString().trim();
        final String email=EditEmail.getText().toString().trim();

        final String password = pasword.getText().toString().trim();
        final String gender=Gender.getSelectedItem().toString();
        final String Proffession=proffession.getText().toString().trim();
        getobj=new getters(fname,lname,email,password,gender,Proffession);

        if(TextUtils.isEmpty(fname))
        {
            Toast.makeText(this,"please enter First Name",Toast.LENGTH_LONG).show();
            return;
        } if(TextUtils.isEmpty(lname))
    {
        Toast.makeText(this,"please enter Last Name",Toast.LENGTH_LONG).show();
        return;
    } if(TextUtils.isEmpty(email))
    {
        Toast.makeText(this,"please enter email",Toast.LENGTH_LONG).show();
        return;
    } if(TextUtils.isEmpty(password))
    {
        Toast.makeText(this,"please enter Password",Toast.LENGTH_LONG).show();
        return;
    }
        if(TextUtils.isEmpty(gender))
        {
            Toast.makeText(this,"please enter Gender",Toast.LENGTH_LONG).show();
            return;
        }  if(TextUtils.isEmpty(Proffession))
    {
        Toast.makeText(this,"please enter Your Proffession",Toast.LENGTH_LONG).show();
        return;
    }

        progressDialog.setMessage("Registering please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //start profile
                            //
                           // UserDetails();
                            FirebaseUser user=firebaseAuth.getCurrentUser();
                            String Uid=user.getUid();

                            ref.child(Uid).setValue(getobj);
                            Toast.makeText(Main2Activity.this, "Registered", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else
                        {
                            Toast.makeText(Main2Activity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                    }
                });
    }



    @Override
    public void onClick(View view) {
        if(view==btnsubmit)
        {
            registerUser();
        }
        if(view==EditReg)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
