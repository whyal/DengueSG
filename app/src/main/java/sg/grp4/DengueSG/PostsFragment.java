package sg.grp4.DengueSG;

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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class PostsFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter mAdapter;

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

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
            Toast.makeText(getActivity(), "Community", Toast.LENGTH_SHORT).show();
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

        Posts p = new Posts();
        p.setName("John");
        p.setPost("New dengue hotspot around Yishun Area, be careful!");
        posts.add(p);

        p = new Posts();
        p.setName("Robert");
        p.setPost("There is a high number of people that has gotten Dengue in my neighbourhood");
        posts.add(p);

        p = new Posts();
        p.setName("LeKeesha");
        p.setPost("Hey hey hey");
        posts.add(p);

        p = new Posts();
        p.setName("Mark");
        p.setPost("Dummy Text Dummy Text Dummy Text Dummy Text Dummy Text Dummy Text Dummy Text");
        posts.add(p);

        p = new Posts();
        p.setName("Tim");
        p.setPost("Dummy Text Dummy Text Dummy Text Dummy Text Dummy Text Dummy Text Dummy Text");
        posts.add(p);

        p = new Posts();
        p.setName("Tyrone");
        p.setPost("Dummy Text Dummy Text Dummy Text Dummy Text Dummy Text Dummy Text Dummy Text");
        posts.add(p);

        return posts;
    }
}
