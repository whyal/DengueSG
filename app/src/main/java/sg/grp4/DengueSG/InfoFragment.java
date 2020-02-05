package sg.grp4.DengueSG;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class InfoFragment extends Fragment {

    private Button aboutButton, symptomsButton; //add more variables, just use a coma ","

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container,false);

        aboutButton = (Button)view.findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, new DetailsFragment() ); //remember to change "DetailsFragment() another fragment name"
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        symptomsButton = (Button)view.findViewById(R.id.symptomsButton);
        symptomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, new Details3Fragment() ); //remember to change "DetailsFragment() another fragment name"
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }

}
