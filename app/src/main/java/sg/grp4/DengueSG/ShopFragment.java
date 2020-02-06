package sg.grp4.DengueSG;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    List<ShopItem> lstShopItems ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lstShopItems = new ArrayList<>();
        lstShopItems.add(new ShopItem("Bug Spray","Provides long protection from mosquitoes, ticks, biting flies, gnats & chiggers. Also repels chiggers, fleas, gnats.","$ 15",R.drawable.bugspray));
        lstShopItems.add(new ShopItem("Gravitrap","The Gravitrap is a simple, hay infusion-filled cylindrical trap with a sticky inner surface to serve as an oviposition site for gravid female Aedes mosquitoes. Wire gauze fitted above the water level minimizes the risk of it being an unwanted breeding habitat.","$ 25",R.drawable.gravitrap));
        lstShopItems.add(new ShopItem("Insect Killer","The Electronic Insect Killer works by attracting flying insects with its high-intensity UV light and octenol lure. As insects fly toward the light, they pass through a vertical rod electrified grid and are killed quickly and cleanly. Rugged, weatherproof polycarbonate construction will not rust, crack, or fade.","$ 55",R.drawable.insectkillermachine));
        lstShopItems.add(new ShopItem("Mozzie Candles","Citronella candles are similar to a typical house candle-the only difference is the ingredients that make up the candle. Instead of including oils that make your home smell nice, they contain citronella oil and lemongrass which are known for keeping mosquitoes away.","$ 20",R.drawable.mozziecandles));
        lstShopItems.add(new ShopItem("Mozzie Dunks","Mosquito Dunks (Bti Briquets) is a highly selective microbial insecticide effective against mosquito in a variety of habitats. When applied to the water surface, Mosquito Dunks float on water and slowly release a long-term biological mosquito larvicide.","$ 30",R.drawable.mozziedunks));
        lstShopItems.add(new ShopItem("Mozzie Wipes","Wipes that help repel mosquitoes and other insects","$ 10",R.drawable.mozziewipes));


        RecyclerView myrv = (RecyclerView) getView().findViewById(R.id.recyclerview_id);
        ShopItemViewAdapter myAdapter = new ShopItemViewAdapter(getContext(),lstShopItems);
        myrv.setLayoutManager(new GridLayoutManager(getContext(),3));
        myrv.setAdapter(myAdapter);
    }
}
