package sg.grp4.DengueSG;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Details6Fragment extends Fragment {
    TextView textView;
    View view;
    MediaPlayer player;
    Button play,pause;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);
        Button button = view.findViewById(R.id.button);

        // Play OnClick
        play = view.findViewById(R.id.button2);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(view);
                Toast.makeText(getActivity(), "Play", Toast.LENGTH_SHORT).show();
            }
        });

        // Pause OnClick
        pause = view.findViewById(R.id.button3);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause(view);
                Toast.makeText(getActivity(),"Paused",Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, new InfoFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        String titleString = "<h1>Treatment</h1>";
        TextView title = view.findViewById(R.id.textViewTitle);
        title.setText(Html.fromHtml(titleString));

        //TextView
        textView = view.findViewById(R.id.textView);

        String sourceString =
                        "<b>" + "<h3>Treatment</h3>" + "</b>"
                        + "There is no specific medicine to treat dengue infection. If you think you may have dengue fever, you should use pain relievers with acetaminophen and avoid medicines with aspirin, which could worsen bleeding. You should also rest, drink plenty of fluids, and see your doctor."
                ;


        textView.setText(Html.fromHtml(sourceString));
        return view;
    }

    public void play(View v) {
        if(player == null) {
            player = MediaPlayer.create(getActivity(),R.raw.p2_tts_part6treat);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    public void pause(View v) {
        if (player == null) {
            player.pause();
        }
        player.pause();
    }


    public void stop(View v){
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null){
            player.release();
            player = null;
            Toast.makeText(getActivity(), "MediaPlayer Released", Toast.LENGTH_SHORT).show();
        }
    }
}
