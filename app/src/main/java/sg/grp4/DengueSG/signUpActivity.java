package sg.grp4.DengueSG;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class signUpActivity extends AppCompatActivity {

    private String fNameStr, lNameStr, emailStr, passwordStr;
    private EditText fName, lName, emailInput, passwordInput;
    private Button btnSignUp;
    private TextView tvLogin;

    private FirebaseStorage mFirebaseStorage;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //findViewById()s
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.signUpBtn);
        tvLogin = findViewById(R.id.loginNavi);

        mFirebaseAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()) {
                    //Upload data to the database
                    String sUemail = emailInput.getText().toString().trim();
                    String sUpassword = passwordInput.getText().toString().trim();

                    mFirebaseAuth.createUserWithEmailAndPassword(sUemail,sUpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserData();
                                Toast.makeText(signUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signUpActivity.this, loginActivity.class));
                            } else{
                                Toast.makeText(signUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(signUpActivity.this, loginActivity.class);
                startActivity(toLogin);
            }
        });

    }

    private Boolean validate(){
        Boolean result = false;
        fNameStr = fName.getText().toString();
        lNameStr = lName.getText().toString();
        passwordStr = passwordInput.getText().toString();
        emailStr = emailInput.getText().toString();

        if(fNameStr.isEmpty() || lNameStr.isEmpty() || passwordStr.isEmpty() || emailStr.isEmpty()){
            Toast.makeText(this, "Please fill in all the details", Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference().child("Users").child(mFirebaseAuth.getUid());

        User user = new User(fNameStr,lNameStr, emailStr);
        myref.setValue(user);
    }
}
