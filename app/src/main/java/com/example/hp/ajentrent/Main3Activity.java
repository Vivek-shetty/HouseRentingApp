package com.example.hp.ajentrent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
//import android.app.SearchManager;
//import android.widget.SearchView.OnQueryTextListener;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.io.ByteArrayOutputStream;

public class Main3Activity extends AppCompatActivity {

    //    implements View.OnClickListener
//    TextView textemail;
//    Button signout;
    FirebaseAuth firebaseAuth;
    private static final String TAG = "ImageAndDescription";

    RecyclerView mrecyclerView;
    FirebaseDatabase fdatabase;
    DatabaseReference Dbref;
    FloatingActionButton floatButton;
    private ViewHolder viewHolder;
    private Query query;
    private FirebaseRecyclerAdapter<OwnerUploadModel, ViewHolder> FRAdapter;
    public FirebaseRecyclerAdapter<OwnerUploadModel, ViewHolder> adapter;
    public FirebaseRecyclerOptions<OwnerUploadModel> options;private FirebaseRecyclerOptions<OwnerUploadModel> option;
    // private MenuItem menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);




        try {
            //ActionBar
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("AgentRent");

            firebaseAuth = FirebaseAuth.getInstance();

            mrecyclerView = findViewById(R.id.recycleView);
            mrecyclerView.setHasFixedSize(true);
            floatButton = findViewById(R.id.Floatbutton);


            mrecyclerView = findViewById(R.id.recycleView);
            mrecyclerView.setHasFixedSize(true);

            mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

            //send query to firebase
            fdatabase = FirebaseDatabase.getInstance();
            Dbref = fdatabase.getReference("uploads");


            options = new FirebaseRecyclerOptions.Builder<OwnerUploadModel>().setQuery(Dbref, OwnerUploadModel.class).build();

            FRAdapter = new FirebaseRecyclerAdapter<OwnerUploadModel, ViewHolder>(options) {

                @Override
                protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull final OwnerUploadModel model) {
                    holder.setDetails(getApplicationContext(), model.getLocation(), model.getAmount(), model.getHouse_type(), model.getmImageUrl());
                    holder.ParenetLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: clicked on");
                            Toast.makeText(Main3Activity.this, "click", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Main3Activity.this, Descriptive.class);

                            intent.putExtra("Image", model.getmImageUrl());
                            intent.putExtra("Amount", model.getAmount());
                            intent.putExtra("HouseType", model.getHouse_type());
                            intent.putExtra("Ac/Non-Ac", model.getAc_nonAc());
                            intent.putExtra("Furnished", model.getFurnished());
                            intent.putExtra("Location", model.getLocation());
                            intent.putExtra("phoneNum",model.getPhoneNum());
                            intent.putExtra("Pets", model.getPets_Allowed());
                            intent.putExtra("WashingMachine", model.getWashing_machine());
                            intent.putExtra("SquareArea", model.getSquare_Area());
                            intent.putExtra("Bath", model.getAttched_Bathroom());
                            startActivity(intent);
                        }
                    });
                }

                @NonNull
                @Override
                public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nrf, parent, false);

                    return new ViewHolder(view);
                }
            };

            mrecyclerView.setAdapter(FRAdapter);
        } catch (Exception e) {
            Log.e("Error on contact", e.getMessage());
        }

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, UploadActivity.class);
                startActivity(intent);
            }
        });
    }


    //search view
    private void firebaseSearch(String searchText) {
        Query firebaseSearchQuery = Dbref.orderByChild("location").startAt(searchText).endAt(searchText + "\uf8ff");

        option = new FirebaseRecyclerOptions.Builder<OwnerUploadModel>().setQuery(firebaseSearchQuery, OwnerUploadModel.class).build();

        adapter = new FirebaseRecyclerAdapter<OwnerUploadModel, ViewHolder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull OwnerUploadModel model) {
                holder.setDetails(getApplicationContext(), model.getLocation(), model.getAmount(), model.getHouse_type(), model.getmImageUrl());


            }
//
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        FRAdapter.startListening();
    }

    protected void onStop() {
        super.onStop();
        FRAdapter.stopListening();
    }


    //firebaseAuth=FirebaseAuth.getInstance();
    public void signOut() {
        {
                firebaseAuth.signOut();
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);

            }
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu;this adds item to the action bar if it present
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                firebaseSearch(query);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                //fillter as u type
                firebaseSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        //hadle other action bar item clicks here
        if(id==R.id.action_settings)
        {
            //TODO
           // signhout();
            signOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


