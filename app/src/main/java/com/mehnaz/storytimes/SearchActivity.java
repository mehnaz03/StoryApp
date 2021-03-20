package com.mehnaz.storytimes;

import android.content.Intent;


import android.os.Bundle;



import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    SearchView searchView;
    ListView listView;
    ArrayList<String> listItems;
   ArrayAdapter<String> adapter;
 //SearchAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        listItems = new ArrayList<>();
        listItems.add("Apple");
        listItems.add("Banana");
        listItems.add("Pineapple");
        listItems.add("Orange");
        listItems.add("Lychee");
        listItems.add("Guava");
        listItems.add("Peach");
        listItems.add("Melon");
        listItems.add("Watermelon");
        listItems.add("Papaya");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(listItems.contains(query)){
                    adapter.getFilter().filter(query);
                    listView.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(SearchActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                return false;
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
}
