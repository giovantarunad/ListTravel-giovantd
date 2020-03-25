package com.example.listtravel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private TextView name;
    private TextView email;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.input_description);
        email = findViewById(R.id.input_description2);
        foto = findViewById(R.id.foto);

        Bundle extras = getIntent().getExtras();
        Bitmap bitmap = extras.getParcelable("foto");

        foto.setImageBitmap(bitmap);
        //name.setText(extras.getString(MainActivity.NAME_KEY));
    }

    public void handleStart(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
