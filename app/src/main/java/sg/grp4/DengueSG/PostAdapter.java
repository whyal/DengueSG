package sg.grp4.DengueSG;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context mContext;
    private List<Post> mPosts;

    public PostAdapter(Context context, List<Post> posts) {
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.indi_post, viewGroup, false);

        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {



        Post postUpload = mPosts.get(i);
        postViewHolder.descPost.setText(postUpload.getText());
        postViewHolder.uNamePost.setText(postUpload.getUser());
        //postViewHolder.likesPost.setText(p);
        //Picasso.get().load(postUpload.)
        Picasso.get().load(postUpload.getImgUrl()).fit().centerCrop().into(postViewHolder.picPost);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        public TextView descPost, uNamePost, likesPost;
        public ImageView picPost, profilePicPost;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            descPost = itemView.findViewById(R.id.postTv);
            picPost = itemView.findViewById(R.id.postPic);
            uNamePost = itemView.findViewById(R.id.nameTv);
            likesPost = itemView.findViewById(R.id.mLikes);
            profilePicPost = itemView.findViewById(R.id.profilePic);


        }
    }
}


