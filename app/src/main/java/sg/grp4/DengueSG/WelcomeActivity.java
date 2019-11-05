package sg.grp4.DengueSG;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    Button loginBtn, signUpBtn;
    TextView guestTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        guestTv = findViewById(R.id.uAGtv);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLoginPage = new Intent(WelcomeActivity.this, loginActivity.class);
                startActivity(toLoginPage);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSignUpPage = new Intent(WelcomeActivity.this, signUpActivity.class);
                startActivity(toSignUpPage);
            }
        });

        //Need a boolean method for differentiation of users
        guestTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainPage = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(toMainPage);
            }
        });
    }
}
