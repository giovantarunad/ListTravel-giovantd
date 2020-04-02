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
        Model p = new Model("Gunung Bromo", "Probolinggo","Gunung Bromo (dari bahasa Sanskerta: Brahma, salah seorang Dewa Utama dalam agama Hindu) atau dalam bahasa Tengger dieja \"Brama\", adalah sebuah gunung berapi aktif di Jawa Timur, Indonesia. Gunung ini memiliki ketinggian 2.329 meter di atas permukaan laut dan berada dalam empat wilayah kabupaten, yakni Kabupaten Probolinggo, Kabupaten Pasuruan, Kabupaten Lumajang, dan Kabupaten Malang. Gunung Bromo terkenal sebagai objek wisata utama di Jawa Timur. Sebagai sebuah objek wisata, Bromo menjadi menarik karena statusnya sebagai gunung berapi yang masih aktif. Gunung Bromo termasuk dalam kawasan Taman Nasional Bromo Tengger Semeru. bromo itu sendiri sudah menjadi salah satu destinasi wisata favorite yang ada di indonesia. banyak wisatawan yang berkunjung ke bromo mulai dari wisatawan domestik sama asing.","https://www.google.com/url?sa=i&url=https%3A%2F%2Fpngimage.net%2Fgunung-bromo-png-2%2F&psig=AOvVaw3ZTGqla78mVqm_v-bM8eSw&ust=1585887400307000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIiPo6LxyOgCFQAAAAAdAAAAABAD");
        models.add(p);
        p = new Model("Kawah Ijen","Banyuwangi","Gunung Ijen adalah sebuah gunung berapi yang terletak di perbatasan antara Kabupaten Banyuwangi dan Kabupaten Bondowoso, Jawa Timur, Indonesia. Gunung ini memiliki ketinggian 2.386 mdpl dan terletak berdampingan dengan Gunung Marapi. Gunung Ijen terakhir meletus pada tahun 1999. Salah satu fenomena alam yang paling terkenal dari Gunung Ijen adalah blue fire di dalam kawah yang terletak di puncaknya. Pendakian gunung ini bisa dimulai dari dua tempat. Pendaki bisa berangkat dari Banyuwangi ataupun dari Bondowoso.","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.asliindonesia.net%2Fbeauty-of-ijen-crater%2Fkawah-ijen-jpg%2F&psig=AOvVaw0K_-vzzG4iUzcz_QjaENQg&ust=1585887926989000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOCksMXzyOgCFQAAAAAdAAAAABAD");
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
