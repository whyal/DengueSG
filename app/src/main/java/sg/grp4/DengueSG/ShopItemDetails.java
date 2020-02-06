package sg.grp4.DengueSG;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopItemDetails extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvprice;
    private ImageView img;
    private Button btnCheckOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_shop_item_details);

        tvtitle = (TextView) findViewById(R.id.txttitle);
        tvdescription = (TextView) findViewById(R.id.txtDesc);
        tvprice = (TextView) findViewById(R.id.txtPrice);
        img = (ImageView) findViewById(R.id.itemThumbnail);
        btnCheckOut = (Button) findViewById(R.id.btn_checkout);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        String Price = intent.getExtras().getString("Price");
        final int image = intent.getExtras().getInt("Thumbnail") ;

        // Setting values
        tvtitle.setText(Title);
        tvdescription.setText(Description);
        tvprice.setText(Price);
        img.setImageResource(image);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(ShopItemDetails.this,CheckOutActivity.class);

                // passing data to the book activity
                newIntent.putExtra("Title",tvtitle.getText());
                newIntent.putExtra("Description",tvdescription.getText());
                newIntent.putExtra("Price",tvprice.getText());
                newIntent.putExtra("Thumbnail",image);
                // start the activity
                ShopItemDetails.this.startActivity(newIntent);
            }
        });
    }


}
