package sg.grp4.DengueSG;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserFragment extends Fragment {

    Button btnSignOut;
    TextView user;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_user, container,false);

        View mView = inflater.inflate(R.layout.fragment_user, container, false);

        btnSignOut = mView.findViewById(R.id.signOutBtn);
        user = mView.findViewById(R.id.userEmail);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        user.setText("Hi, " + mFirebaseUser.getEmail());

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent inToWelcome = new Intent(getActivity(), WelcomeActivity.class);
                inToWelcome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                inToWelcome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(inToWelcome);
            }
        });

        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
