package com.example.ramanbindal.panakeia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class serviceform extends AppCompatActivity {
EditText et1,et2,et3;
    TextView tv;
    Button btn1;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceform);

        et1=(EditText)findViewById(R.id.editText4);
        et2=(EditText)findViewById(R.id.editText6);
        et3=(EditText)findViewById(R.id.editText7);

        tv=(TextView)findViewById(R.id.textView3);
        btn1=(Button)findViewById(R.id.button6);


        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference();





        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String type = et1.getText().toString().trim();
                final String ser = et2.getText().toString().trim();
                final String amount = et3.getText().toString().trim();

                FirebaseUser us = FirebaseAuth.getInstance().getCurrentUser();
                if (us != null) {
                    // User is signed in
                    userId = us.getUid();
                    request rq = new request(type, ser, amount);

                    mFirebaseDatabase.child("Requests").child(userId).setValue(rq);

                    Toast.makeText(serviceform.this, "Request Send to Server",
                            Toast.LENGTH_LONG).show();

                    startActivity(new Intent(serviceform.this, profile.class));
                    finish();

                    /* mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           request rq = dataSnapshot.getValue(request.class);
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {


                           Toast.makeText(serviceform.this, "Failed to read request" + databaseError.toException(),
                                   Toast.LENGTH_SHORT).show();
                       }
                   }
                   );*/


                }
                else
                {
                    Toast.makeText(serviceform.this, "Error", Toast.LENGTH_LONG).show();

                }

            }

        });



    }


}