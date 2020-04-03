package com.example.listtravel;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listtravel.Adapter.MyAdapter;
import com.example.listtravel.Model.Model;

import java.util.ArrayList;
import java.util.Collections;

public class ListActivity extends AppCompatActivity implements MyAdapter.Onclick {

    public static final String EXTRA_URL = "imageUrl";

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_list);

        preferences = this.getSharedPreferences ( "MY_DATA", MODE_PRIVATE );

        recyclerView = findViewById(R.id.recycler_view);

        getPlayers ();
    }

    //    add models to arraylist
    private void getPlayers(){
        ArrayList<Model> models = new ArrayList<>();
        Model p = new Model("Gunung Ijen", "Banyuwangi","Rp 65.000","Gunung/Kawah","Kawah Ijen adalah sebuah danau kawah yang bersifat asam yang berada di puncak Gunung Ijen dengan kedalaman danau 200 meter dan luas kawah mencapai 5.466 Hektar. Danau kawah Ijen dikenal merupakan danau air asam kuat terbesar di dunia[1]. Kawah Ijen berada dalam wilayah Cagar Alam Taman Wisata Ijen Kabupaten Bondowoso dan Kabupaten Banyuwangi, Jawa Timur. Fenomena eternal blue fire atau api biru abadi berada di dalam kawah Ijen, dan pemandangan alami ini hanya terjadi di dua tempat di dunia yaitu Islandia dan Ijen. Blue fire hanya dapat dilihat oleh mata manusia saat tidak ada cahaya, karenanya waktu ideal untuk melihatnya adalah jam 2 hingga jam 4 dinihari, karena pendakian Gunung Ijen baru mulai dibuka jam 2 dinihari. Dari Kawah Ijen, kita dapat melihat pemandangan gunung lain yang ada di kompleks Pegunungan Ijen, di antaranya adalah puncak Gunung Marapi yang berada di timur Kawah Ijen, Gunung Raung, Gunung Suket, dan Gunung Rante.","https://id.wikipedia.org/wiki/Berkas:Kawah_Ijen_-East_Java_-Indonesia-31July2009.jpg");
        models.add(p);
        p = new Model("Gili Ketapang", "Probolinggo","Rp 65.000","Pulau","Gili Ketapang adalah sebuah desa dan pulau kecil di Selat Madura, tepatnya 8 km di lepas pantai utara Probolinggo. Secara administratif, pulau ini termasuk wilayah Kecamatan Sumberasih, Kabupaten Probolinggo, Jawa Timur.\n" +
                "\n" +
                "Luas wilayahnya sekitar 68 ha, dan jumlah penduduknya 7.600 jiwa (2004), yang sebagian besar adalah Suku Madura dan bermata pencaharian sebagai nelayan. Penduduk pulau ini dikenal relatif makmur. Gili Ketapang merupakan salah satu tujuan wisata alam di Kabupaten Probolinggo. Pulau terebut dihubungkan dengan Pulau Jawa dengan perahu motor melalui Pelabuhan Tanjung Tembaga, Kota Probolinggo, dengan waktu tempuh sekitar 30 menit.\n" +
                "\n" +
                "Menurut legenda setempat, pulau ini dulunya menyatu dengan daratan Desa Ketapang (Pulau Jawa), yang kemudian secara gaib bergerak lamban ke tengah laut, karena gempa yang dahsyat akibat letusan Gunung Semeru. Nama Gili Ketapang berasal dari bahasa Madura, gili yang artinya mengalir, dan Ketapang merupakan nama asal desa tersebut. ","asasa");
        models.add(p);

        String mShortSetting = preferences.getString ( "Sort", "Ascending" );
        if(mShortSetting.equals ( "Ascending" )){
            Collections.sort ( models, Model.BY_TITTLE_ASCENDING );
        }else if(mShortSetting.equals ( "Descending" )){
            Collections.sort ( models, Model.BY_TITTLE_DESCENDING );
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        myAdapter = new MyAdapter(this, models, this);
        recyclerView.setAdapter(myAdapter);
    }
    @Override
    public void clickItem(Model model) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("nama", model.getNama());
        intent.putExtra("asal", model.getAsal());
        intent.putExtra("Harga", model.getHarga());
        intent.putExtra("Jenis", model.getJenis());
        intent.putExtra("desc", model.getDesc());
        intent.putExtra(EXTRA_URL, model.getImg());
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {
                myAdapter.getFilter().filter(s);
                if(fileList()!=null){
                    Toast.makeText(ListActivity.this,"No Records Found!",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(ListActivity.this,"Records Found!",Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_sort){
            Toast.makeText(this, "Sort", Toast.LENGTH_SHORT).show();
            ShowSortDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowSortDialog() {
        String [] option = {"Ascending","Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ("Sort by");
        builder.setIcon ( R.drawable.ic_action_sort );
        builder.setItems ( option, new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    SharedPreferences.Editor editor = preferences.edit ();
                    editor.putString ( "Sort", "Ascending" );
                    editor.apply ();
                    getPlayers ();
                }
                if(which == 1){
                    SharedPreferences.Editor editor = preferences.edit ();
                    editor.putString ( "Sort", "Descending" );
                    editor.apply ();
                    getPlayers ();
                }
            }
        } );
        builder.create ().show ();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    //    action bar
}
