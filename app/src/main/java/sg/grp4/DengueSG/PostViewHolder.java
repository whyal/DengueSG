package sg.grp4.DengueSG;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView userName, posts;
    public View v;

    public PostViewHolder(View sV) {

        super(sV);
        v = sV;
        userName = sV.findViewById(R.id.nameTv);
        posts = sV.findViewById(R.id.postTv);

    }


}
