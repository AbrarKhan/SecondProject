package firstapp.om.secondproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class ContactUS extends AppCompatActivity {
    TextView textView9,textView10;
    public static final int MY_PERMISSIONS_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        textView9=findViewById(R.id.textView9);
        textView10=findViewById(R.id.textView10);
        textView9.setText(Html.fromHtml(getString(R.string.mail_id)));
        textView10.setText(Html.fromHtml(getString(R.string.mail_id)));
    }

    public void open(View view) {
       int id= view.getId();
       switch(id){
           case R.id.textView9:

               Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
               emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               emailIntent.setType("vnd.android.cursor.item/email");
               emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"informabrar@gmail.com," +
                       "abrar.azzan@gmail.com"});
               emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
               emailIntent.putExtra(Intent.EXTRA_TEXT,"body");
               startActivity(Intent.createChooser(emailIntent, "chooser"));
               break;
           case R.id.textView10:
               Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:9876543"));
               if(ContextCompat.checkSelfPermission(ContactUS.this,
                       Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.shouldShowRequestPermissionRationale(ContactUS.this,
                            Manifest.permission.CALL_PHONE)){
                          //show the reason why app required this permission
                        //called permission rationale.
                    }
                    else{
                        ActivityCompat.requestPermissions(ContactUS.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST);
                    }
               }
               else{
                          startActivity(intent);
               }
               break;
           case R.id.textView11:
               Uri gmmIntentUri = Uri.parse("geo:23.6401,58.0927");
               Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
               mapIntent.setPackage("com.google.android.apps.maps");
               startActivity(mapIntent);
       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST:
                if(grantResults.length>0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
        }
    }
}
