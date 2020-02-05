package sg.grp4.DengueSG;

import android.graphics.Color;
import android.media.MediaPlayer;
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
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class InfoFragment extends Fragment {

    public InfoFragment(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container,false);

        //Items in list
        final String dengueItems[] = new String[] {"About Dengue",
                "How to prevent Dengue",
                "Symptoms and Treatment",
                "Statistics",
                "DF vs DHF",
                };

        final ListView listView = view.findViewById(R.id.lv1);
        listView.setCacheColorHint(Color.WHITE);
        //ArrayAdapter for ListView lv1
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                dengueItems
        );

        listView.setAdapter(listViewAdapter);

        //Onclicklistener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment selectedFragment = null;

                if ( position==0)
                {
                    selectedFragment = new DetailsFragment();
                }
                else if(position==1) {
                    selectedFragment = new Details2Fragment();
                }
                else if(position==2) {
                    selectedFragment = new Details3Fragment();
                }
                else if(position==3) {
                    selectedFragment = new Details4Fragment();
                }
                else if(position==4) {
                    selectedFragment = new Details5Fragment();
                }
                else if(position==5){
                    selectedFragment = new Details6Fragment();
                }
                else if(position==6){
                    selectedFragment = new Details7Fragment();
                }
                // must be after if statements
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, selectedFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }


}
