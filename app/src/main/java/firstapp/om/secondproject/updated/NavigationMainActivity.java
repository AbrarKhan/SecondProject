package firstapp.om.secondproject.updated;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import firstapp.om.secondproject.R;
import firstapp.om.secondproject.academic_faculties.UpdatedAcademicFragment;
import firstapp.om.secondproject.firebase_database.FirebaseAllBookFragment;
import firstapp.om.secondproject.firebase_database.FirebaseNewBookFragment;
import firstapp.om.secondproject.home.UpdatedHomeFragment;
import firstapp.om.secondproject.json.JSONFragment;
import firstapp.om.secondproject.moodle.AllStudentFragment;
import firstapp.om.secondproject.moodle.MoodleLoginFragment;
import firstapp.om.secondproject.moodle.RegistrationFragment;
import firstapp.om.secondproject.support_facilities.UpdatedSupportFragment;
import firstapp.om.secondproject.test.TestFragment;

public class NavigationMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        UpdatedHomeFragment.OnFragmentInteractionListener,
        UpdatedSupportFragment.OnFragmentInteractionListener,
        UpdatedAcademicFragment.OnFragmentInteractionListener ,
        MoodleLoginFragment.OnFragmentInteractionListener,
        RegistrationFragment.OnFragmentInteractionListener,
        AllStudentFragment.OnFragmentInteractionListener,
        JSONFragment.OnFragmentInteractionListener,
        TestFragment.OnFragmentInteractionListener,
FirebaseNewBookFragment.OnFragmentInteractionListener,
        FirebaseAllBookFragment.OnFragmentInteractionListener {

    private static final String TAG ="TAG"; ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        UpdatedHomeFragment homeFragment=UpdatedHomeFragment.newInstance(null,null);
        fragmentTransaction.replace(R.id.main_container,homeFragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        getSupportActionBar().show();
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
        getMenuInflater().inflate(R.menu.navigation_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_language) {
//            Locale current = getResources().getConfiguration().locale;
//            if (current.getLanguage().equalsIgnoreCase(GulfCollegeConstants.AR)) {
//                changeLang(GulfCollegeConstants.EN);
//            } else {
//                changeLang(GulfCollegeConstants.AR);
//            }
//            Intent intent = getIntent();
//            finish();
//            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        Fragment fragment=null;
        int id = item.getItemId();

         if (id == R.id.nav_home) {
           fragment=UpdatedHomeFragment.newInstance(null,null);

        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if (id == R.id.nav_academic) {
             fragment=UpdatedAcademicFragment.newInstance(null,null);

        } else if (id == R.id.nav_support) {
             fragment=UpdatedSupportFragment.newInstance(null,null);

        }
         else if (id == R.id.nav_register) {
             fragment=RegistrationFragment.newInstance(null,null);

         }
         else if (id == R.id.nav_registered) {
             fragment=AllStudentFragment.newInstance(null,null);

         }
         else if (id == R.id.nav_json) {
           fragment=JSONFragment.newInstance(null,null);

         }
         else if (id == R.id.nav_json_test) {
             fragment=TestFragment.newInstance(null,null);

         }
         else if (id == R.id.nav_firebase) {
             fragment=FirebaseNewBookFragment.newInstance(null,null);

         }
         if(fragment!=null){
             fragmentTransaction.replace(R.id.main_container,fragment);
             fragmentTransaction.commit();
         }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void backButtonPressed(View view) {
        onBackPressed();
    }
}
