package com.example.listtravel;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import static com.example.listtravel.ListActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvNamawisata, tvAsalkota,tvHarga, tvJenis, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_detail);


        String image_detail = getIntent ().getStringExtra (EXTRA_URL);

        imageView  = findViewById(R.id.iv_img);
        tvNamawisata= findViewById(R.id.tv_nama);
        tvAsalkota= findViewById(R.id.tv_asal);
        tvHarga = findViewById(R.id.a);
        tvDesc = findViewById(R.id.text_desc);
        tvJenis = findViewById(R.id.tv_jenis);

        tvNamawisata.setText(getIntent().getStringExtra("nama"));
        tvAsalkota.setText(getIntent().getStringExtra("asal"));
        tvHarga.setText(getIntent().getStringExtra("harga"));
        tvDesc.setText(getIntent().getStringExtra("desc"));
        tvJenis.setText(getIntent().getStringExtra("jenis"));
        String image = getIntent().getStringExtra(EXTRA_URL);

        Picasso.get().load(image).into(imageView);
    }
}
