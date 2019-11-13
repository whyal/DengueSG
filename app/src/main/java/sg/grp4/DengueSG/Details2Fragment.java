package sg.grp4.DengueSG;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Details2Fragment extends Fragment {
    View view;
    TextView textView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);
        textView = view.findViewById(R.id.textView);
        textView.setText("2.     How to prevent dengue\n" +
                "Mozzie Wipeout (BIG BANNER)\n" +
                "This is a simple, yet effective home practice to eradicate and prevent mosquitoes from breeding. With these five steps, all mosquitoes in your home as well as the neighbourhood will be exterminated! Protect yourselves and your loved ones from the mozzies.  \n" +
                " \n" +
                "Stay protected (PICTURE)\n" +
                "Wear long sleeves – Cover up every inch of your body when in or near potential breeding grounds for mosquitoes. Your clothes will act as an outer sheath, preventing you from significant exposure to the mosquito invasion, thus keeping you safe and sound! (PICTURE\n" +
                "Light up your home – Mosquitoes usually prefer damp and dark places. Have your home filled with streaming sunlight. This will keep off the virus breeding mosquitoes from camping up in your home. Also, make sure the windows and doors are closed during then night to prevent potential mosquito invasion. (PICTURE\n" +
                "Repel them – It is essential that you use highly effective mosquito repellents and creams, no matter what time of day it is. This will keep those virus infected mosquitoes from feeding off on you.\n");
        return view;
    }
}


