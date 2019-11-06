package sg.grp4.DengueSG;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfoFragment extends Fragment {

    public InfoFragment(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container,false);

        String[] dengueItems= {"Dengue sucks",
                "test 2",
                "test 3"};

        ListView listView = (ListView) view.findViewById(R.id.lv1);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                dengueItems
        );

        listView.setAdapter(listViewAdapter);
        return view;
    }
}
