package firstapp.om.secondproject.initial_setup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import firstapp.om.secondproject.R;
import firstapp.om.secondproject.databases.MyDatabaseHelperClass;
import firstapp.om.secondproject.databases.Student;

public class RegisterActivity extends AppCompatActivity {
    private Button already_register,register;
    private EditText user_id,password;
    private String mUser,mPass;
    private FirebaseAuth mAuth;
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        user_id=findViewById(R.id.userId);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        already_register=findViewById(R.id.already_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser=user_id.getText().toString().trim();
                mPass=password.getText().toString().trim();
                if(TextUtils.isEmpty(mUser)){
                    user_id.setError("Please provide a mail ID");
                  return;
                }
                if (!checkEmail(mUser)){
                    Toast.makeText(RegisterActivity.this,"Mail id is not valid",
                            Toast.LENGTH_LONG).show();
                    user_id.setError("Please provide a valid mail ID");
                    return;
                }
                if (TextUtils.isEmpty(mPass) || mPass.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password field is empty or" +
                                    "It should have six or more characters",
                            Toast.LENGTH_LONG).show();
                    password.setError("Please provide a valid Password should have at least six characters");
                    return;
                }
           mAuth.createUserWithEmailAndPassword(mUser,mPass).
                   addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,
                                    "Registration Successful",Toast.LENGTH_LONG).show();
                            Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(loginIntent);
                          }
                       }
                   }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                  e.printStackTrace();
               }
           });

            }
        });
        already_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

}
