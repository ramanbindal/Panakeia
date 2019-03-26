package com.example.ramanbindal.panakeia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4;
    Button btn;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        tv1=(TextView)findViewById(R.id.textView15);
        tv2=(TextView)findViewById(R.id.textView9);
        tv3=(TextView)findViewById(R.id.textView10);
        tv4=(TextView)findViewById(R.id.textView14);
        btn=(Button)findViewById(R.id.button7);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference();

        FirebaseUser us = FirebaseAuth.getInstance().getCurrentUser();
        if (us != null) {
            // User is signed in
            userId = us.getUid();
            Query query = mFirebaseDatabase.child("users").orderByKey().equalTo(userId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // dataSnapshot is the "issue" node with all children with id 0
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            User u = postSnapshot.getValue(User.class);

                            tv2.setText("Name- "+u.name);
                            tv3.setText("Email id- "+u.email);
                            tv4.setText("Phone No.- "+u.phone);

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    Toast.makeText(UserProfile.this, "Failed to read request" + databaseError.toException(),
                            Toast.LENGTH_SHORT).show();

                }
            });

        }

            btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                Intent intent = new Intent(UserProfile.this, simpleactivity.class);
                startActivity(intent);
            }
        });


    }
}
