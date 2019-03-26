package com.example.ramanbindal.panakeia;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ContactDetails extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4;
    Button btn;
    ImageView iv;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    User u;
    String uid;
    Map<request,String> a=new HashMap<request,String>();
    private static final int REQUEST_CALL = 1;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        tv1=(TextView)findViewById(R.id.textView8);
        tv2=(TextView)findViewById(R.id.textView11);
        tv3=(TextView)findViewById(R.id.textView12);
        tv4=(TextView)findViewById(R.id.textView13);
        btn=(Button)findViewById(R.id.button5);
        iv=(ImageView)findViewById(R.id.imageView5);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
                uid= extras.getString("id");

        }
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to database node
        mFirebaseDatabase = mFirebaseInstance.getReference();

        Query query = mFirebaseDatabase.child("users").orderByKey().equalTo(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        u = postSnapshot.getValue(User.class);

                        tv2.setText("Name- "+u.name);
                        tv3.setText("Email id- "+u.email);
                        tv4.setText("Phone No.- "+u.phone);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ContactDetails.this, "Failed to read request" + databaseError.toException(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+u.phone));
                if (ContextCompat.checkSelfPermission(ContactDetails.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ContactDetails.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }else {
                    startActivity(i);
                }



            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CALL:
            {
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    startActivity(i);
                }else{

                    Toast.makeText(ContactDetails.this, "Failed to make call.Please grand permission",
                            Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

}
