package com.gojek.test.gojektest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import exception_handlling.CatchException;
import fragmentAll.TrendingRepoFragment;
import staticClasses.Config;

public class DashboardMange extends Activity {
    private TrendingRepoFragment trendingRepoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        AddFragment(Config.trendingRepoFragment, new TrendingRepoFragment());
    }


    public void AddFragment(Object... args) {
        try {
            String typeFragment = (String) args[0];
            android.app.Fragment fragment = (android.app.Fragment) args[1];
            Bundle bundle = new Bundle();

            Log.e("AddFragment", "AddFragment" + typeFragment);
            if (typeFragment.equalsIgnoreCase(Config.trendingRepoFragment)) {
                if (trendingRepoFragment != null) {
                    getFragmentManager().popBackStack();
                    trendingRepoFragment = null;
                }
                trendingRepoFragment = (TrendingRepoFragment) fragment;
            }
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment, typeFragment);
            transaction.addToBackStack(typeFragment);
            transaction.commit();
        } catch (Exception e) {
            CatchException.ExceptionSend(e);
        }
    }


    @Override
    public void onBackPressed() {
        try {
            int index = getFragmentManager().getBackStackEntryCount() - 1;
            FragmentManager.BackStackEntry backEntry = getFragmentManager().getBackStackEntryAt(index);
            String tag = backEntry.getName();
            if (tag.equalsIgnoreCase(Config.trendingRepoFragment)) {
            }
        } catch (Exception e) {
            CatchException.ExceptionSend(e);
        }

            try {
                FragmentManager fragmentManager = getFragmentManager();
                Log.e("Count", "Count" + fragmentManager.getBackStackEntryCount());

                if (fragmentManager.getBackStackEntryCount() == 1) {
                    finishAffinity();
                } else {
                    fragmentManager.popBackStack();
                }
            } catch (Exception e) {
                CatchException.ExceptionSend(e);
            }
    }
}
