package firstapp.om.secondproject.initial_setup;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


import firstapp.om.secondproject.R;
import firstapp.om.secondproject.updated.NavigationMainActivity;
import firstapp.om.secondproject.utils.GulfCollegeConstants;

public class SplashScreen extends AppCompatActivity {
   String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide action bar
        getSupportActionBar().hide();


        //-----------------------------
        //to hide notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //-----------------------------
        setContentView(R.layout.splash_screen);


//        if (getSharedPreferences(GulfCollegeConstants.LANG_FILE, MODE_PRIVATE).
//                getString(GulfCollegeConstants.LANG, null) != null
//                && getSharedPreferences(GulfCollegeConstants.LANG_FILE, MODE_PRIVATE).
//                getString(GulfCollegeConstants.LANG, null).
//                equalsIgnoreCase(GulfCollegeConstants.EN)) {
//            changeLang(GulfCollegeConstants.EN);
//        } else {
//            changeLang(GulfCollegeConstants.AR);
//        }
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent i=new Intent(SplashScreen.this,NavigationMainActivity.class);
               startActivity(i);
               finish();
            }
        }, 5000);
    }


}
