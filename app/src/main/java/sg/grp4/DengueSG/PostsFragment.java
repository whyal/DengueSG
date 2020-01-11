package sg.grp4.DengueSG;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class PostsFragment extends Fragment {

    private RecyclerView recyclerView;
    //private PostAdapter mAdapter;

    Button addBtn;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_posts, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        CheckIfExistingUser();

        addBtn = mView.findViewById(R.id.add_post);
//        recyclerView = mView.findViewById(R.id.story);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PostActivity.class));
            }
        });


        return mView;
    }

    private void CheckIfExistingUser() {
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser != null) {
            //continue whatever stuffs in here
            Toast.makeText(getActivity(), "Community", Toast.LENGTH_SHORT).show();
        } else {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_layout, this);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
