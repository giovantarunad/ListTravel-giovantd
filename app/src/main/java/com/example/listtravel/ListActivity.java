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
        Model p = new Model("Gunung Ijen", "Banyuwangi","Rp 10.000","Jawa Timur","Kawah Ijen adalah sebuah danau kawah yang bersifat asam yang berada di puncak Gunung Ijen dengan kedalaman danau 200 meter dan luas kawah mencapai 5.466 Hektar. Danau kawah Ijen dikenal merupakan danau air asam kuat terbesar di dunia[1].  Dari Kawah Ijen, kita dapat melihat pemandangan gunung lain yang ada di kompleks Pegunungan Ijen, di antaranya adalah puncak Gunung Marapi yang berada di timur Kawah Ijen, Gunung Raung, Gunung Suket, dan Gunung Rante.","https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Danau_Belerang_Ijen.jpg/800px-Danau_Belerang_Ijen.jpg");
        models.add(p);
        p = new Model("Telaga Sarangan", "Magetan","Rp 15.000","Jawa timur","Telaga Sarangan, juga dikenal sebagai Telaga Pasir adalah telaga alami yang berada di ketinggian 1.200 meter di atas permukaan laut dan terletak di lereng Gunung Lawu,Kecamatan Plaosan,Kabupaten Magetan,Jawa Timur.Telaga ini berjarak sekitar 16 kilometer arah barat kota Magetan.Telaga ini luasnya sekitar 30 hektare dan berkedalaman 28 meter.Dengan suhu udara antara 15 hingga 20 derajat Celsius."
                ,"https://upload.wikimedia.org/wikipedia/id/a/af/Telaga_Sarangan_Magetan.jpg");
        models.add(p);
        p = new Model("Benteng Van Den Bosch", "Ngawi","Rp 5.000","Jawa Timur","Benteng Van den Bosch, lebih dikenal sebagai Benteng Pendem adalah sebuah benteng yang terletak di Kelurahan Pelem, Kecamatan Ngawi, Kabupaten Ngawi. Benteng ini memiliki ukuran bangunan 165 m x 80 m dengan luas tanah 15 Ha. Lokasinya mudah dijangkau yakni dari Kantor Pemerintah Kabupaten Ngawi +/- 1 Km arah timur laut. Letak benteng ini sangat strategis karena berada di sudut pertemuan sungai Bengawan Solo dan Sungai Madiun. Benteng ini dulu sengaja dibuat lebih rendah dari tanah sekitar yang dikelilingi oleh tanah tinggi sehingga terlihat dari luar terpendam.","https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Benteng_van_den_bosch.jpg/800px-Benteng_van_den_bosch.jpg");
        models.add(p);
        p = new Model("Pulau Noko Selayar", "Gresik","Rp 350.000","Jawa Timur","Pulau ini merupakan sebuah gundukan menyerupai bukit atau gunung yang ada di tepi laut tepatnya di wilayah Dusun Taubat, Kecamatan Sangkapura, Bawean Kabupaten Gresik. Pulau ini adalah pulau yang tak berpenghuni (kondisi sekarang). Kita dapat mencapai pulau Selayar ini dengan hanya berjalan kaki menyeberangi laut pada saat air laut sedang surut.","https://upload.wikimedia.org/wikipedia/id/d/dc/Noko_keren.JPG");
        models.add(p);
        p = new Model("WBL", "Lamongan","Rp 8.500","Jawa Timur","Wisata Bahari Lamongan atau disingkat WBL adalah tempat wisata bahari yang terletak di Kecamatan Paciran, Kabupaten Lamongan, Jawa Timur. Tempat wisata ini dibuka sejak 14 November 2004. Wisata Bahari Lamongan dikelola oleh PT Bumi Lamongan Sejati, sebuah perusahaan patungan Pemkab Lamongan dengan PT Bunga Wangsa Sejati.","https://upload.wikimedia.org/wikipedia/id/thumb/2/25/Gerbang_Wisata_Bahari_Lamongan.png/250px-Gerbang_Wisata_Bahari_Lamongan.png");
        models.add(p);
        p = new Model("Pantai Balekambang", "Malang","Rp 15.000","Jawa Timur"," Daya tarik Balekambang utamanya tentu panorama alam, gelombang ombak yang memanjang hampir dua kilometer, serta hamparan pasir nan luas. Area pasir putih terlihat bersih dari sampah maupun kotoran sehingga cukup nyaman bagi pengunjung untuk bermain dan berolahraga","https://upload.wikimedia.org/wikipedia/commons/0/05/Balekambang_beach%2C_Pura_Amarta_Jati_in_the_middle_of_Ismoyo_island.jpg");
        models.add(p);
        p = new Model("Air terjun sedudo", "Nganjuk","Rp 10.000","Jawa Timur","Air Terjun Sedudo adalah sebuah air terjun dan objek wisata yang terletak di Desa Ngliman Kecamatan Sawahan, Kabupaten Nganjuk, Jawa Timur. Jaraknya sekitar 30 km arah selatan ibu kota kabupaten Nganjuk. Berada pada ketinggian 1.438 meter dpl, ketinggian air terjun ini sekitar 105 meter.","https://upload.wikimedia.org/wikipedia/id/thumb/5/57/Sedudo.jpg/452px-Sedudo.jpg");
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
        Intent intent = new Intent(ListActivity.this, DetailActivity.class);
        intent.putExtra("nama", model.getNama());
        intent.putExtra("asal", model.getAsal());
        intent.putExtra("harga", model.getHarga());
        intent.putExtra("jenis", model.getJenis());
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
