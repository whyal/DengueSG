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
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));
        lstShopItems.add(new ShopItem("The Vegitarian","Description book",R.drawable.thevigitarian));


        RecyclerView myrv = (RecyclerView) getView().findViewById(R.id.recyclerview_id);
        ShopItemViewAdapter myAdapter = new ShopItemViewAdapter(getContext(),lstShopItems);
        myrv.setLayoutManager(new GridLayoutManager(getContext(),3));
        myrv.setAdapter(myAdapter);
    }
}
