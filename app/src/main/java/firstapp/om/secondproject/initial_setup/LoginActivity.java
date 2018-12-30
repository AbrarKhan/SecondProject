package firstapp.om.secondproject.initial_setup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.regex.Pattern;

import firstapp.om.secondproject.R;
import firstapp.om.secondproject.databases.Student;
import firstapp.om.secondproject.updated.NavigationMainActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText mUser,mPass;
    private String user,pass,dUser,dPass;
    private Button login,goForRegister;
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
        setContentView(R.layout.activity_base);
        mUser=findViewById(R.id.userId);
        mPass=findViewById(R.id.password);
        login=findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=mUser.getText().toString().trim();
                pass=mPass.getText().toString().trim();
                if(TextUtils.isEmpty(user)){
                    mUser.setError("Please provide a mail ID");
                    return;
                }
                if (!checkEmail(user)){
                    Toast.makeText(LoginActivity.this,"Mail id is not valid",
                            Toast.LENGTH_LONG).show();
                    mUser.setError("Please provide a valid mail ID");
                    return;
                }
                if (TextUtils.isEmpty(pass) || pass.length()<6){
                    Toast.makeText(LoginActivity.this,"Password field is empty or" +
                                    "It should have six or more characters",
                            Toast.LENGTH_LONG).show();
                    mPass.setError("Please provide a valid Password should have at least six characters");
                    return;
                }
                mAuth.signInWithEmailAndPassword(user,pass).
                        addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,
                                            "Login Successful",Toast.LENGTH_LONG).show();
                                    Intent loginIntent=new Intent(LoginActivity.this,NavigationMainActivity.class);
                                    startActivity(loginIntent);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this,
                                "Login not Successful",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        goForRegister=findViewById(R.id.register);
        goForRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

      private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
        }
}
