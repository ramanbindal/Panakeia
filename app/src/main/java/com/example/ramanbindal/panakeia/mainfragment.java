package com.example.ramanbindal.panakeia;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class mainfragment extends Fragment  {

/*
    public mainfragment() {
        // Required empty public constructor
    }
*/
    public TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainfragment, container, false);
          tv=(TextView) view.findViewById(R.id.textView2);
       /* btn1=(Button)view.findViewById(R.id.button4);
        btn2=(Button)view.findViewById(R.id.button3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        {
            @Override
            public void onClick(View view) {


             ServiceRequest fragment = new ServiceRequest();
                FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.fragment_main, fragment);
                fragmentTransaction.commit();
                 ServiceRequest fragment = new ServiceRequest();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_main,fragment);
                fragmentTransaction.commit();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_main,
                                new ServiceRequest()).commit();
                //startActivity(new Intent(mainfragment.this, serviceform.class));
                Intent i = new Intent(getActivity(), signup.class);
                getActivity().startActivity(i);


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getActivity(),signup.class);
                i.setClass(getActivity(), serviceform.class);
                startActivity(i);


            }
        });




        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_mainfragment, container, false);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button4)
        {
            Intent i = new Intent(getActivity(), signup.class);
            getActivity().startActivity(i);

        }
        else if(view.getId()==R.id.button3)
        {
            Intent i= new Intent(getActivity(),signup.class);
            i.setClass(getActivity(), signup.class);
            startActivity(i);

        }
    }
    */

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mainfragment, container, false);
    }
    }
