package sg.grp4.DengueSG;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Details5Fragment extends Fragment {
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
                transaction.replace(R.id.fragment_layout, new InfoFragment() );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        //TextView
        textView = view.findViewById(R.id.textView );
        textView.setText("5.     DF vs DHF\n" +
                "Dengue Fever rarely causes death. However, the infection can progress into a more serious condition known as the Dengue Haemorrhagic Fever. Some patients with DF go on to develop DHF. The outlook for DHF depends on how early the condition is detected. People who receive care in the early stages of dengue infection will often recover.\n" +
                "The symptoms of DHF include:\n" +
                "-   \tRestlessness\n" +
                "-   \tAcute, or sudden, fever\n" +
                "-   \tSevere abdominal pain\n" +
                "-   \tBleeding or bruising under the skin\n" +
                "-   \tCold or clamming skin\n" +
                "-   \tNosebleeds\n" +
                "-   \tLarge decrease in blood pressure.\n" +
                "Complications from DHF may include:\n" +
                "-   \tSeizures\n" +
                "-   \tBrain damage\n" +
                "-   \tBlood clots\n" +
                "-   \tDamage to liver and lungs\n" +
                "-   \tHeart damage\n" +
                "-   \tShock\n" +
                "-   \tDeath\n" +
                "Who is at risk for DHF?\n" +
                "-   \tInfants and small children\n" +
                "-   \tPregnant women\n" +
                "-   \tOlder adults\n" +
                "-   \tThose with compromised immune system\n" +
                "How to spot an Aedes aegypti \n");
        return view;
    }

}
