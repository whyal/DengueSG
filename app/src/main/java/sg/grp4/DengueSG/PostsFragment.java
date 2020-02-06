package sg.grp4.DengueSG;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PostAdapter mPostAdapter;


    private DatabaseReference mDatabaseReference;
    private List<Post> mPosts;

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    FloatingActionButton addBtn;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_posts, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        CheckIfExistingUser();

        addBtn = mView.findViewById(R.id.add_post);

        mRecyclerView = mView.findViewById(R.id.story);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PostActivity.class));
            }
        });

        mPosts = new ArrayList<>();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Posts");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    mPosts.add(post);
                }

                mPostAdapter = new PostAdapter(getActivity(), mPosts);

                mRecyclerView.setAdapter(mPostAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
