package com.tamunicipality.task;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tamunicipality.task.fragments.CountryFragment;
import com.tamunicipality.task.fragments.MainFragment;
import com.tamunicipality.task.model.Countries;
import com.tamunicipality.task.model.ICountriesResponseCallback;
import com.tamunicipality.task.net.countries.Country;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ICountriesResponseCallback,
        MainFragment.OnMainFragmentListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                actionBar.setBackgroundDrawable(getDrawable(R.drawable.actionbar_background));
            } else {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
            }

        }
        if(savedInstanceState == null) {
            Countries.getInstance().requestCountries(this);
        }
        else {
            Countries.getInstance().setCountriesResponseCallback(this);
        }
    }

    private void mountFragment(Fragment fragment, boolean setStack){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.detail_holder, fragment, fragment.getClass().getSimpleName());
        if(setStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Countries.getInstance().setCountriesResponseCallback(null);
    }

    @Override
    public void successful(List<String> regions, Countries countries) {
        Log.d(TAG, "successful regions="+regions.size());
        mountFragment(MainFragment.newInstance(MainFragment.TypeFragment.REGIONS,regions,null,null),false);
    }

    @Override
    public void error(String message) {
        if(!isFinishing()) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            AlertDialog dlg = new AlertDialog();
            Bundle args = new Bundle();
            args.putString(AlertDialog.MESSAGE, message);
            dlg.setArguments(args);
            dlg.show(ft, "dialog");
        }
    }

    @Override
    public void onSetRegion(String name) {
        Log.d(TAG, "onSetRegion region="+name);
        List<Country> country = Countries.getInstance().getCountriesByRegion(name);
        mountFragment(MainFragment.newInstance(MainFragment.TypeFragment.COUNTRIES,null,name,country),true);
    }

    @Override
    public void onSetCountry(Country country) {
        Log.d(TAG, "onSetCountry country=" + country.getName());
        mountFragment(CountryFragment.newInstance(country,Countries.getInstance().getBorderCountries(country)),true);
    }
}
