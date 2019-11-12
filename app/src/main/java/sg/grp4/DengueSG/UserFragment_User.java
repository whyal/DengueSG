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

public class UserFragment_User extends Fragment {

    Button btnSignOut;
    TextView nameTv;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_user, container, false);

        btnSignOut = v.findViewById(R.id.signoutBtn);
        nameTv = v.findViewById(R.id.name);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        nameTv.setText("Hi, " + mFirebaseUser.getEmail());

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent inToWelcome = new Intent(getActivity(), MainActivity.class);
                inToWelcome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                inToWelcome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(inToWelcome);
            }
        });

        return v;
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
