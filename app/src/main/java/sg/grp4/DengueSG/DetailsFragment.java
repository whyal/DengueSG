package sg.grp4.DengueSG;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    View view;
    TextView  textView;

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
        //Title TextView
        String titleString = "<h1>About Dengue</h1>";
        TextView title = view.findViewById(R.id.textViewTitle);
        title.setText(Html.fromHtml(titleString));
        // TextView
        textView = view.findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod()); //For scrolling

        String sourceString =
                "Dengue fever is a disease caused by the dengue virus which is transmitted to humans via the bite of an infected mosquito. It is currently widespread in Singapore and in the region of Southeast Asia. The prevalence of the virus is closely tied to the prevalence of the Aedes mosquito."
                + "<br> <br> <br>"
                + "<b>" + "<h3>Biting Behaviour</h3>" + "</b>"
                + "The Aedes mosquito is most active during daylight, for approximately two hours after sunrise and several hours before sunset. Only the female species bite whereas the male feed on fruits"
                + "<b>" + "<h3>Breeding Habits</h3>" + "</b>"
                + "The mosquito rest indoors, in closets and other dark places. Outside, they rest where it is cool and shaded. It is adapted to breed around human dwellings and prefer to lay its eggs in stale water which contain no other living species."
                + "<b>" + "<h3>Life cycle of a mosquito</h3>" + "</b>"
                + "The Aedes mosquito have 4 life stages: egg, larva, pupa and adult. Eggs will take a few days to several months to hatch after being laid, depending on the environment. Larvae are aquatic and develop into pupae in as little as 5 days, which later develop into adult flying mosquitoes in 2-3 days. The lifespan of an adult female mosquito is 40-50 days while the male lives a shorter life of just 10 days."
                + "<b>" + "<h3>Types of Dengue</h3>" + "</b>"
                + "There are four different serotypes of Dengue fever: DENV-1, -2, -3 and -4. These serotypes are classified into 2 groups: Severe Dengue – commonly referred to as ‘Dengue haemorrhagic fever/Dengue shock syndrome (DHF/DSS)’ and Classic Dengue fever (DF). Severe Dengue can be life threatening while a classic dengue fever can be treated with early detection. Though, the symptoms of a classic dengue fever may get worse and become fatal."
                + "<b>" + "<h3>Transmission</h3>" + "</b>"
                + "Bites from infected mosquitoes are the only mode of transmission. Mosquitoes acquire virus when they feed on viraemic host, after which the virus infects many tissues of the mosquito, including the salivary glands. The mosquitoes, who have been infected, transmit the virus by injecting its virus-containing saliva into a non-immune host during subsequent blood meals."
                ;

        textView.setText(Html.fromHtml(sourceString));
        return view;
    }
}
