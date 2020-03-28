package com.example.listtravel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private ImageView foto;
    private EditText nameInput;
    private EditText emailInput;
    private ImageView fotoInput;
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameInput = findViewById(R.id.input_name);
        emailInput = findViewById(R.id.input_email);
        fotoInput = findViewById(R.id.foto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            return;
        }
        if(requestCode == GALERY_REQUEST_CODE){
            if(data != null){
                try{
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    fotoInput.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleFoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERY_REQUEST_CODE);
    }

    public void handleStart(View view) {
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();

        if(name.equals("")){
            Toast.makeText(getApplicationContext(), "Input your name!", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            fotoInput.buildDrawingCache();

            Bitmap foto = fotoInput.getDrawingCache();

            Bundle extras = new Bundle();
            intent.putExtras(extras);
            startActivity(intent);

        }
    }
}
