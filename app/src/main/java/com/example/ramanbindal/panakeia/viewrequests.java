package com.example.ramanbindal.panakeia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewrequests extends AppCompatActivity {
    TextView tv;
    ListView lv;
    ArrayList<request> al;
    ArrayList<String> uid;
    CustomAdapter cadp;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrequests);
        tv = (TextView) findViewById(R.id.textView4);
        lv = (ListView) findViewById(R.id.listView1);

        uid=new ArrayList<String>();

        al = new ArrayList<request>();
        cadp = new CustomAdapter(viewrequests.this, R.layout.row, al);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to database node
        mFirebaseDatabase = mFirebaseInstance.getReference();
        mFirebaseDatabase.child("Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Getting the data from snapshot

                    uid.add(postSnapshot.getKey());

                    request rq = postSnapshot.getValue(request.class);

                    cadp.add(rq);
                    lv.setAdapter(cadp);
                    }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(viewrequests.this, "Failed to read request" + databaseError.toException(),
                        Toast.LENGTH_SHORT).show();
            }
        }
        );







    }

    public class CustomAdapter extends ArrayAdapter<request> {

        int layout;
        Context c;
        ArrayList<request> al;

        public CustomAdapter(Context context, int res, ArrayList<request> al) {
            // TODO Auto-generated constructor stub
            super(context, res, al);

            c = context;
            layout = res;
            this.al = al;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            LayoutInflater lif = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = lif.inflate(layout, null);
            TextView tv1 = (TextView) convertView.findViewById(R.id.textView6);
            TextView tv2 = (TextView) convertView.findViewById(R.id.textView7);
            TextView tv3 = (TextView) convertView.findViewById(R.id.textView5);
            Button acc = (Button) convertView.findViewById(R.id.button4);
            request p = al.get(position);
            tv1.setText("Type- "+p.type);
            tv2.setText("Request- "+p.service);
            tv3.setText("Rs. "+p.amount);

            acc.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent i = new Intent(getApplicationContext(), ContactDetails.class);
                    i.putExtra("id",uid.get(position));
                    startActivity(i);

                   // Toast.makeText(getApplicationContext(), " contact user!"+uid.get(position), Toast.LENGTH_LONG).show();

                }
            });


            return convertView;

        }


    }
}