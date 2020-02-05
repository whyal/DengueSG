package sg.grp4.DengueSG;

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

public class Details3Fragment extends Fragment {
    TextView textView;
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_layout, new InfoFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        String titleString = "<h1>Symptoms</h1>";
        TextView title = view.findViewById(R.id.textViewTitle);
        title.setText(Html.fromHtml(titleString));

        //TextView
        textView = view.findViewById(R.id.textView);

        String sourceString =
                "<b>" + "<h3>Symptoms</h3>" + "</b>"
                + "Some of the symptoms include : "
                + "<ul>" +
                    "<li>Sudden, high fever<li>" +
                    "<li>Severe headaches<li>" +
                    "<li>Pain behind the eyes<li>" +
                    "<li>Severe joint and muscle pain<li>" +
                    "<li>Fatigue<li>" +
                    "<li>Nausea<li>" +
                    "<li>Vomiting<li>" +
                    "<li>Skin rash, which appears two to five days after the onset of fever.<li>"
                + "</ul>"
               ;


        textView.setText(Html.fromHtml(sourceString));
        return view;
    }
}

