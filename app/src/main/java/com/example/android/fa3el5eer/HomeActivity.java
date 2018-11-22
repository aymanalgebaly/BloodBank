package com.example.android.fa3el5eer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.fa3el5eer.donationRequest.RequestActivity;
import com.example.android.fa3el5eer.favourites_articals.FavoriteFragment;
import com.example.android.fa3el5eer.login.LoginActivity;
import com.example.android.fa3el5eer.notificationsList.NotificationsActivity;
import com.example.android.fa3el5eer.notificationsSettings.NotificationSettingsFragment;
import com.example.android.fa3el5eer.notifications_count.NotificationsCountResponse;
import com.example.android.fa3el5eer.notifications_count.NotificationsCount_Api;
import com.example.android.fa3el5eer.report.ReportFragment;
import com.example.android.fa3el5eer.settingsFace.ContactWithUsFragment;
import com.nex3z.notificationbadge.NotificationBadge;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private SharedPreferences preferences;
    private TextView mail,name,title;
    private ImageView imageView;
    private Fragment fragment;
    private String url = "http://ipda3.com/blood-bank/api/v1/notifications-count?" +
            "api_token=Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
    //    private TabLayout tabLayout;
//    private ViewPager viewPager;
//    private ViewPagerAdapter viewPagerAdapter;
    private NotificationBadge notificationBadge;
    private int count = 0;
    private FloatingActionButton fab;
    private int SELECT_IMAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = getSharedPreferences("user",MODE_PRIVATE);


        notificationBadge = findViewById(R.id.badge);
        notificationBadge.setNumber(++count);

        title = findViewById(R.id.toolbar_title);
        title.setText("الرئيسيه");

        ImageView notification_icon = findViewById(R.id.notification_icon);

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                notificationBadge.setNumber(count);
                Intent intent = new Intent(HomeActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });

        frameLayout = findViewById(R.id.frame);


        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, RequestActivity.class);
                startActivity(i);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RequestActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        View headerLayout = navigationView.getHeaderView(0);

//        mail = headerLayout.findViewById(R.id.textView);
//        String email = preferences.getString("email", "");
//        mail.setText(email);
//
//        name = headerLayout.findViewById(R.id.text_name_navi);
//        String name1 = preferences.getString("name", "");
//        name.setText(name1);

//        imageView = headerLayout.findViewById(R.id.imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
//            }
//        });


        navigationView.setNavigationItemSelectedListener(this);


        setupBadge();

        navigationView.setCheckedItem(R.id.homeFragment);
        fragment = new HomeFragment();
        DisplayFragment(fragment);

    }

    void setupBadge() {


        Retrofit retrofit = RetrofitClient.getInstant();
        NotificationsCount_Api notificationsCount_api = retrofit.create(NotificationsCount_Api.class);
        Call<NotificationsCountResponse> notificationsCountResponse = notificationsCount_api.getNotificationsCountResponse(url);
        notificationsCountResponse.enqueue(new Callback<NotificationsCountResponse>() {
            @Override
            public void onResponse(Call<NotificationsCountResponse> call, Response<NotificationsCountResponse> response) {

                if (response.body().getStatus() == 1) {
                    count = response.body().getData().getNotificationsCount();
                }
            }

            @Override
            public void onFailure(Call<NotificationsCountResponse> call, Throwable t) {

                Log.i("count", "error=  " + t.getMessage());

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.info) {
            fragment = new MyInfoFragment();
            DisplayFragment(fragment);
            fab.setVisibility(View.INVISIBLE);
            title.setText("معلوماتي");


        } else if (id == R.id.notification) {
            fragment = new NotificationSettingsFragment();
            DisplayFragment(fragment);
            fab.setVisibility(View.INVISIBLE);
            title.setText("اعدادات الاشعارات");

        } else if (id == R.id.favorite) {
            fragment = new FavoriteFragment();
            DisplayFragment(fragment);
            fab.setVisibility(View.INVISIBLE);
            title.setText("المفضلة");

        } else if (id == R.id.home) {
            fragment = new HomeFragment();
            DisplayFragment(fragment);
            title.setText("الرئيسية");

        } else if (id == R.id.info_for_use) {
            fragment = new InfoToUseFragment();
            DisplayFragment(fragment);
            fab.setVisibility(View.INVISIBLE);
            title.setText("تعليمات الاستخدام");

        } else if (id == R.id.report) {
            fragment = new ReportFragment();
            DisplayFragment(fragment);
            fab.setVisibility(View.INVISIBLE);
            title.setText("ابلاغ");

        } else if (id == R.id.contact_with_us) {
            fragment = new ContactWithUsFragment();
            DisplayFragment(fragment);
            fab.setVisibility(View.INVISIBLE);
            title.setText("تواصل معنا");

        } else if (id == R.id.about_app) {
            fragment = new AboutAppFragment();
            DisplayFragment(fragment);
            fab.setVisibility(View.INVISIBLE);
            title.setText("عن التطبيق");

        } else if (id == R.id.mark_app) {
            fragment = new RateAppFragment();
            DisplayFragment(fragment);
            fab.setVisibility(View.INVISIBLE);
            title.setText("تقيم التطبيق علي المتجر");

        } else if (id == R.id.logOut) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.isDrawerOpen(Gravity.RIGHT);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void DisplayFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == SELECT_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//                if (data != null) {
//                    try {
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(HomeActivity.this.getContentResolver(), data.getData());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else if (resultCode == Activity.RESULT_CANCELED)  {
//                Toast.makeText(HomeActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
