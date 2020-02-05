package sg.grp4.DengueSG;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

    private Button aboutButton, symptomsButton, preventionButton, riskButton, statisticsButton, treatmentButton; //add more variables, just use a coma ","

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container,false);

        aboutButton = (Button)view.findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, new DetailsFragment() );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        preventionButton = (Button)view.findViewById(R.id.preventionButton);
        preventionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, new Details2Fragment() );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        symptomsButton = (Button)view.findViewById(R.id.symptomsButton);
        symptomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, new Details3Fragment() );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        treatmentButton = (Button)view.findViewById(R.id.treatmentButton);
        treatmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, new Details6Fragment() );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        statisticsButton = (Button)view.findViewById(R.id.statsButton);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.nea.gov.sg/media/news/advisories/index/nea-urges-heightened-vigilance-as-dengue-cases-spike-1-june-2019"));
                startActivity(intent);
            }
        });

        riskButton = (Button)view.findViewById(R.id.riskButton);
        riskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, new Details5Fragment() );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

}
