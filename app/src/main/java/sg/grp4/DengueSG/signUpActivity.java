package sg.grp4.DengueSG;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();

        //findViewById()s
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.signUpBtn);
        tvLogin = findViewById(R.id.loginNavi);

        //Once user clicks sign up button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(signUpActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }

                //Validations : Check if email exists in 
                else if (!(email.isEmpty() && password.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(signUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(signUpActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                            }

                            else {
                                startActivity(new Intent(signUpActivity.this, MainActivity.class));
                                Toast.makeText(signUpActivity.this, "Successfully Sign Up!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                
                else {
                    Toast.makeText(signUpActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
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
