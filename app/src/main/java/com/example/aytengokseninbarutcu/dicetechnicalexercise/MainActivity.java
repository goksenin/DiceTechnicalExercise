package com.example.aytengokseninbarutcu.dicetechnicalexercise;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aytengokseninbarutcu.dicetechnicalexercise.Fragments.DisplayDetailByTagFragment;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Fragments.GetAllTagsFragment;

public class MainActivity extends AppCompatActivity implements GetAllTagsFragment.OnGetAllTagsFragmentInteractionListener,
DisplayDetailByTagFragment.OnDisplayDetailByTagFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetAllTagsFragment userProfileFragment = new GetAllTagsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameMainContent, userProfileFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onGetAllTagsFragmentInteraction(String tag) {

        DisplayDetailByTagFragment userProfileFragment = DisplayDetailByTagFragment.newInstance(tag);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameMainContent, userProfileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDisplayDetailByTagFragmentInteraction() {

    }
}
