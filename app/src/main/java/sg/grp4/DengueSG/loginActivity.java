package sg.grp4.DengueSG;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class loginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button btnLogin;
    TextView tvSignUp;

    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        //findViewById()s
        emailInput = findViewById(R.id.emailL);
        passwordInput = findViewById(R.id.passwordL);
        btnLogin = findViewById(R.id.logInBtn);
        tvSignUp = findViewById(R.id.signUpNavi);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if (mFirebaseUser != null) {
                    Toast.makeText(loginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                
                else {
                    Toast.makeText(loginActivity.this, "Existing User!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                //Validations : If email field is empty
                if (email.isEmpty()) {
                    emailInput.setError("Please fill in your Email");
                    emailInput.requestFocus();
                }

                //Validations : If password field is empty
                else if (password.isEmpty()) {
                    passwordInput.setError("Please fill in your Password");
                    passwordInput.requestFocus();
                }

                //Validations : If both fields are empty
                else if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(loginActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }

                //Validations : Check if email exists in
                else if (!(email.isEmpty() && password.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(loginActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Intent inToMain = new Intent(loginActivity.this, MainActivity.class);
                                        startActivity(inToMain);
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(loginActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSignUp = new Intent(loginActivity.this, signUpActivity.class);
                startActivity(toSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }
}
