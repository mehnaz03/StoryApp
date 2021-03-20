package com.mehnaz.storytimes;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeriesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.recyclerview)
    RecyclerView listView;
    SeriesAdapter adapter;
    ArrayList<FruitModel> allSampleData;

    ArrayList<FruitModel> imageModelArrayList;


    private int[] myImageList = new int[]{R.drawable.apple, R.drawable.mango,R.drawable.straw, R.drawable.pineapple,R.drawable.orange,R.drawable.blue,R.drawable.water};
    private String[] myImageNameList = new String[]{"Apple","Mango" ,"Strawberry","Pineapple","Orange","Blueberry","Watermelon"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ButterKnife.bind(this);
        successResult();


    }
    private void successResult()
    {
        // data to populate the RecyclerView with
        //  String[] data = {"Bousorama", "Bousorama", "Bousorama"};


        imageModelArrayList = eatFruits();
        adapter = new SeriesAdapter(this, imageModelArrayList);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));





        //  createDummyData();

        allSampleData = eatFruits();
        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.recyclerviewParent);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);
        my_recycler_view.setAdapter(adapter);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        allSampleData = eatFruits();
        RecyclerView my_recycler_view2 = (RecyclerView) findViewById(R.id.recyclerviewParent3);
        RecyclerViewDataAdapter adapter2 = new RecyclerViewDataAdapter(this, allSampleData);
        my_recycler_view2.setAdapter(adapter2);
        my_recycler_view2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        allSampleData = eatFruits();
        RecyclerView my_recycler_view3 = (RecyclerView) findViewById(R.id.recyclerviewParent4);
        RecyclerViewDataAdapter adapter3 = new RecyclerViewDataAdapter(this, allSampleData);
        my_recycler_view3.setAdapter(adapter3);
        my_recycler_view3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }
    private ArrayList<FruitModel> eatFruits(){

        ArrayList<FruitModel> list = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            FruitModel fruitModel = new FruitModel();
            fruitModel.setName(myImageNameList[i]);
            fruitModel.setImage_drawable(myImageList[i]);
            list.add(fruitModel);
        }

        return list;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this,DashBoardActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_series) {
            Intent intent = new Intent(this,SeriesActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_category) {
            Intent intent = new Intent(this,CategoriesActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_search) {
            Intent intent = new Intent(this,SearchActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_favorite) {
            Intent intent = new Intent(this,FavoriteActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_settings) {


        } else if (id == R.id.nav_share) {

            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }

        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(this,HelpActivity.class);
            this.startActivity(intent);
        }
        else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this,LoginActivity.class);
            this.startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
