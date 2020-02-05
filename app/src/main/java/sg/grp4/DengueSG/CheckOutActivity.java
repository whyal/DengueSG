package sg.grp4.DengueSG;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CheckOutActivity extends AppCompatActivity {

    private TextView tvItemDetail;
    private ImageView itemImage;
    private Button btnCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_check_out);

        tvItemDetail = (TextView)findViewById(R.id.tvItemDetail);
        itemImage = (ImageView) findViewById(R.id.itemThumbnail);
        btnCheckOut = (Button) findViewById(R.id.checkOutBtn);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        String Price = intent.getExtras().getString("Price");
        final int image = intent.getExtras().getInt("Thumbnail") ;

        // Setting values
        tvItemDetail.setText("1x " + Title + " - " + Price);
        itemImage.setImageResource(image);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast=Toast.makeText(getApplicationContext(),"Transaction Successful",Toast.LENGTH_SHORT);
                toast.show();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(CheckOutActivity.this, MainActivity.class);
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(newIntent);

                    }
                }, 1500);
            }
        });
    }
}
