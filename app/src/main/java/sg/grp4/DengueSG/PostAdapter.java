package sg.grp4.DengueSG;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private ArrayList<Posts> posts;
    private Context c;

    public PostAdapter(Context context, ArrayList<Posts> posts) {
        this.c = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.indi_post, parent, false);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {

        postViewHolder.userName.setText(posts.get(i).getName());
        postViewHolder.posts.setText(posts.get(i).getPost());

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
