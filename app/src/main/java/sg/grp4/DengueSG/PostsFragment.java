package sg.grp4.DengueSG;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class PostsFragment extends Fragment {

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;


    private RecyclerView recyclerView;
    private PostAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_posts, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        CheckIfExistingUser();


        recyclerView = mView.findViewById(R.id.story);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new PostAdapter(getActivity(), getMyList());
        recyclerView.setAdapter(mAdapter);


        return mView;
    }

    private void CheckIfExistingUser (){
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser != null) {
            //continue whatever stuffs in here
            Toast.makeText(getActivity(), "Nice One", Toast.LENGTH_SHORT).show();
        }
        else {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_layout, this );
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private ArrayList<Posts> getMyList() {
        ArrayList<Posts> posts = new ArrayList<>();

        Posts posts1 = new Posts();
        posts1.setName("John");
        posts1.setPost("Hey Hey Hey");
        posts.add(posts1);

        posts1 = new Posts();
        posts1.setName("Robert");
        posts1.setPost("Hey Hey ");
        posts.add(posts1);

        posts1 = new Posts();
        posts1.setName("Einstein");
        posts1.setPost("Hey _ Hey");
        posts.add(posts1);

        posts1 = new Posts();
        posts1.setName("Lmao");
        posts1.setPost("Hey HIgh Hey");
        posts.add(posts1);

        return posts;
    }
}
