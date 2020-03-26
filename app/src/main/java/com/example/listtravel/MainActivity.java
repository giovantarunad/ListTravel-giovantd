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


public class MainActivity extends AppCompatActivity {

    public static final String NAME_KEY = "name";
    public static final String EMAIL_KEY = "email";
    public static final String FOTO_KEY = "foto";

    private EditText nameInput;
    private EditText emailInput;

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALERY_REQUEST_CODE = 1;

    private ImageView fotoInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();

        fotoInput = findViewById(R.id.foto);

        nameInput = findViewById(R.id.input_description);
        emailInput = findViewById(R.id.input_description2);
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

    public void handleSubmit(View view) {
        String name = nameInput.getText().toString();

        if(name.equals("")){
            Toast.makeText(getApplicationContext(), "Input your name!", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, ProfileActivity.class);
            fotoInput.buildDrawingCache();

            Bitmap foto = fotoInput.getDrawingCache();

            Bundle extras = new Bundle();
            extras.putParcelable(FOTO_KEY, foto);

            intent.putExtras(extras);
            intent.putExtra(NAME_KEY, name);
            startActivity(intent);

        }
    }
    public void profileMenu(View v) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public void historyMenu(View v) {
        Intent i = new Intent(this, ListActivity.class);
        startActivity(i);
    }
}