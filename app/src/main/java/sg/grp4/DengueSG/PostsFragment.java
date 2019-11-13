package sg.grp4.DengueSG;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PostsFragment extends Fragment {

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_posts, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        CheckIfExistingUser();



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
            transaction.replace(R.id.fragment_layout, new UserFragment_User() );
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
