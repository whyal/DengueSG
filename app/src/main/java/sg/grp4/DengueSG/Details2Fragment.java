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

public class Details2Fragment extends Fragment {
    View view;
    TextView textView;
    MediaPlayer player;
    Button play, pause;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);
        Button button = view.findViewById(R.id.button);

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
                transaction.replace(R.id.fragment_layout, new InfoFragment() );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        TextView title = view.findViewById(R.id.textViewTitle);
        String titleString = "<h1>Prevent Dengue</h1>";
        title.setText(Html.fromHtml(titleString));
        //TextView
        textView = view.findViewById(R.id.textView);

        String sourceString =
                "<b>" + "<h3>Mozzie Wipeout</h3>" + "</b>"
                + "This is a simple, yet effective home practice to eradicate and prevent mosquitoes from breeding. With these five steps, all mosquitoes in your home as well as the neighbourhood will be exterminated! Protect yourselves and your loved ones from the mozzies."
                + "<b>" + "<h3>Stay protected</h3>" + "</b>"
                + "<b>Wear long sleeves</b> – Cover up every inch of your body when in or near potential breeding grounds for mosquitoes. Your clothes will act as an outer sheath, preventing you from significant exposure to the mosquito invasion, thus keeping you safe and sound!"
                + "<br><br><b>Light up your home</b> – Mosquitoes usually prefer damp and dark places. Have your home filled with streaming sunlight. This will keep off the virus breeding mosquitoes from camping up in your home. Also, make sure the windows and doors are closed during then night to prevent potential mosquito invasion."
                + "<br><br><b>Repel them</b> – It is essential that you use highly effective mosquito repellents and creams, no matter what time of day it is. This will keep those virus infected mosquitoes from feeding off on you."
                ;

        textView.setText(Html.fromHtml(sourceString));
        return view;
    }

    public void play(View v) {
        if(player == null) {
            player = MediaPlayer.create(getActivity(),R.raw.p2_tts_part2);
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


