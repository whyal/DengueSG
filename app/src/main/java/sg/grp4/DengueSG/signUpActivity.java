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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUpActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button btnSignUp;
    TextView tvLogin;
    ProgressBar progressBar;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //findViewById()s
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.signUpBtn);
        tvLogin = findViewById(R.id.loginNavi);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        mFirebaseAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                String sUemail = emailInput.getText().toString();
                String sUpassword = passwordInput.getText().toString();

                //Validations : If email field is empty
                if (sUemail.isEmpty()) {
                    emailInput.setError("Please fill in your Email");
                    emailInput.requestFocus();
                }

                //Validations : If password field is empty
                else if (sUpassword.isEmpty()) {
                    passwordInput.setError("Please fill in your Password");
                    passwordInput.requestFocus();
                }

                //Validations : If both fields are empty
                else if (sUemail.isEmpty() && sUpassword.isEmpty()) {
                    Toast.makeText(signUpActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }

                else if (!(sUemail.isEmpty() && sUpassword.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        Toast.makeText(signUpActivity.this, "Register Successfully",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(signUpActivity.this, loginActivity.class));
                                    }

                                    else {
                                        Toast.makeText(signUpActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();
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
}
