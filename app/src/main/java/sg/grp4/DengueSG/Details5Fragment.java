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

public class Details5Fragment extends Fragment {
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
                transaction.replace(R.id.fragment_layout, new InfoFragment() );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        String titleString = "<h1>DF vs DHF</h1>";
        TextView title = view.findViewById(R.id.textViewTitle);
        title.setText(Html.fromHtml(titleString));
        //TextView
        textView = view.findViewById(R.id.textView );
        String sourceString =
                "Dengue Fever rarely causes death. However, the infection can progress into a more serious condition known as the Dengue Haemorrhagic Fever. Some patients with DF go on to develop DHF. The outlook for DHF depends on how early the condition is detected. People who receive care in the early stages of dengue infection will often recover."
                        + "<b>" + "<h3>The symptoms of DHF include :</h3>" + "</b>"
                        + "<ul>" +
                            "<li>Restlessness<li>" +
                            "<li>Acute, or sudden, fever<li>" +
                            "<li>Severe abdominal pain<li>" +
                            "<li>Bleeding or bruising under the skin<li>" +
                            "<li>Cold or clamming skin<li>" +
                            "<li>Nosebleeds<li>"
                        + "</ul>"
                        + "<b>" + "<h3>Complications from DHF may include :</h3>" + "</b>"
                            + "<ul>" +
                            "<li>Seizures<li>" +
                            "<li>Brain damage<li>" +
                            "<li>Blood clots<li>" +
                            "<li>Damage to liver and lungs<li>" +
                            "<li>Heart damage<li>" +
                            "<li>Shock<li>" +
                            "<li>Death<li>"
                        + "</ul>"
                        + "<b>" + "<h3>Who is at risk for DHF? : </h3>" + "</b>"
                        + "<ul>" +
                            "<li>Infants and small children<li>" +
                            "<li>Pregnant women<li>" +
                            "<li>Older adults<li>" +
                            "<li>Those with compromised immune system<li>"
                        + "</ul>"
                ;

        textView.setText(Html.fromHtml(sourceString));
        return view;
    }

    public void play(View v) {
        if(player == null) {
            player = MediaPlayer.create(getActivity(),R.raw.p2_tts_part4);
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
