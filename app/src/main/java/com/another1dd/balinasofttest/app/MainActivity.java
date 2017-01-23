package com.another1dd.balinasofttest.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.another1dd.balinasofttest.R;
import com.another1dd.balinasofttest.rest.model.Offer;
import com.another1dd.balinasofttest.rest.model.Param;
import com.another1dd.balinasofttest.rest.model.Yml;
import com.another1dd.balinasofttest.rest.service.ApiInterface;
import com.orm.SugarRecord;
import com.orm.util.NamingHelper;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private String BASE_URL = "http://ufa.farfor.ru";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mFragmentManager  = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new CatalogFragment()).commit();
        navigationView.setCheckedItem(0);



        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        ApiInterface service = client.create(ApiInterface.class);
        Call<Yml> call = service.getUfaFarforRu("ukAXxeJYZN");

        call.enqueue(new Callback<Yml>() {
            @Override
            public void onResponse(Call<Yml> call, Response<Yml> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)

                    Yml result = response.body();
                    List<Offer> offers = result.getShop().getOffers();
                    SugarRecord.deleteAll(Offer.class);
                    SugarRecord.executeQuery("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + NamingHelper.toSQLName(Offer.class) + "'");
                    SugarRecord.deleteAll(Param.class);
                    SugarRecord.executeQuery("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + NamingHelper.toSQLName(Param.class) + "'");


                    SugarRecord.saveInTx(offers);
                    ArrayList<Param> allParams = new ArrayList<>();


                    for (Offer offer : offers)
                    {
                        List<Param> params = offer.getParam();
                        if (params != null)
                        {   for (Param param : params)
                            {
                                param.setOfferId(offer.getId());
                            }
                            allParams.addAll(params);
                        }

                    }
                    SugarRecord.saveInTx(allParams);

                    Log.d("Connection", "Соединение установлено");
                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.d("Connection", "Ошибка подключения к http://ufa.farfor.ru/");
                }
            }

            @Override
            public void onFailure(Call<Yml> call, Throwable t) {

                if(t instanceof SocketTimeoutException){
                    Log.d("Connection", "Socket Time out. Please try again.");
                }else {
                    Log.d("Connection", t.getMessage());
                }
            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_catalog) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView, new CatalogFragment()).commit();
        } else if (id == R.id.nav_contacts) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView, new ContactsFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
