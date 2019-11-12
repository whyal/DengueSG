package sg.grp4.DengueSG;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class loginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button btnLogin;
    TextView tvSignUp;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findViewById()s
        emailInput = findViewById(R.id.emailL);
        passwordInput = findViewById(R.id.passwordL);
        btnLogin = findViewById(R.id.logInBtn);
        tvSignUp = findViewById(R.id.signUpNavi);

        mFirebaseAuth = FirebaseAuth.getInstance();


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

                else if (!(email.isEmpty() && password.isEmpty())) {

                    //progressBar.setVisibility(View.VISIBLE);

                    mFirebaseAuth.signInWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(loginActivity.this, MainActivity.class));
                                    } else {
                                        Toast.makeText(loginActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
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
}
