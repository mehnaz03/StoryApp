package com.mehnaz.storytimes;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;

import android.os.Bundle;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
@BindView(R.id.progressBar2)ProgressBar progressBar;
        VideoView videoView;
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        int index = 0;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_video);
            ButterKnife.bind(this);
            videoView = findViewById(R.id.videoView);
            final MediaController mediacontroller = new MediaController(this);
            mediacontroller.setAnchorView(videoView);


            videoView.setMediaController(mediacontroller);

            videoView.setVideoURI(Uri.parse(arrayList.get(index)));
            videoView.requestFocus();

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                            progressBar.setVisibility(View.GONE);
                            videoView.setMediaController(mediacontroller);
                            mediacontroller.setAnchorView(videoView);

                        }
                    });
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                    if (index++ == arrayList.size()) {
                        index = 0;
                        mp.release();
                        Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                    } else {
                        videoView.setVideoURI(Uri.parse(arrayList.get(index)));
                        videoView.start();
                    }


                }
            });

            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                  //  Log.d("API123", "What " + what + " extra " + extra);
                    return false;
                }
            });


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
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

