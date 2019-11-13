package sg.grp4.DengueSG;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Details3Fragment extends Fragment {
    TextView textView;
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details3, container, false);
        textView = view.findViewById(R.id.textView3 );
        textView.setText("3.     Symptoms and Treatment\n" +
                " \n" +
                "Up to 40–80% of all dengue infections are asymptomatic,\n" +
                " \n" +
                "However, if you are showing any of these symptoms:\n" +
                " \n" +
                "-        Sudden onset of fever for 2-7 days\n" +
                "-        Severe headache with pain behind the eyes\n" +
                "-        Joint and muscle pain\n" +
                "-        Skin rashes\n" +
                "-        Nausea and vomiting\n" +
                "-        Mild bleeding (e.g. nose or gum bleed, or easy bruising of the Symptoms usually appear 4-7 days after being bitten (ranges from 3-14 days).\n" +
                "Please seek medical attention immediately.\n" +
                "There is no specific treatment for dengue fever, or its more serious forms. Treatment for dengue is supportive. In more severe cases, you may be hospitalised for aggressive emergency treatment, including fluid and electrolyte replacement, and/or blood transfusions.\n");
        return view;
    }
}
