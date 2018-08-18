package com.example.unknown.package_assistant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TransferActivity extends AppCompatActivity {
    int[] IMAGES = {R.drawable.parcel1,R.drawable.parcel2,R.drawable.parcel3,R.drawable.parcel1,R.drawable.parcel2,R.drawable.parcel3};
    String[] PNAMES = {"Phone case", "Noodles", "Clothes","Phone case", "Noodles", "Clothes"};
    String[] PDestination = {"Destination 1","Destination 2","Destination 3","Destination 4","Destination 5","Destination 6"};
    ImageView TProfile;
    TextView TName,TEmail;
    Button TUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        TUpdate = (Button)findViewById(R.id.TUpdate);
        TProfile = (ImageView)findViewById(R.id.TProfile);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.seveneleven);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        TProfile.setImageDrawable(roundedBitmapDrawable);

        TName = (TextView)findViewById(R.id.TName);
        TName.setText(MainActivity.currentUser.name);
        TEmail = (TextView)findViewById(R.id.TEmail);
        TEmail.setText(MainActivity.currentUser.email);

        ListView listView = (ListView)findViewById(R.id.listView);
        ParcelAdapter parcelAdapter = new ParcelAdapter();
        listView.setAdapter(parcelAdapter);

        TUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    class ParcelAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.parcellayout,null);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.IMGParcel);
            TextView tvPName = (TextView)convertView.findViewById(R.id.tvPName);
            TextView tvPDestination = (TextView)convertView.findViewById(R.id.tvPDestination);

            imageView.setImageResource(IMAGES[i]);
            tvPName.setText(PNAMES[i]);
            tvPDestination.setText(PDestination[i]);
            return convertView;
        }
    }
}
