package com.example.listtravel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listtravel.CustomFilter;
import com.example.listtravel.Model.Model;
import com.example.listtravel.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {

    Context mContext;
    public ArrayList<Model> models;
    ArrayList<Model> filterList;
    private Onclick listener;
    CustomFilter filter;

    public MyAdapter(Context mContext, ArrayList<Model> models, Onclick onclick) {
        this.mContext = mContext;
        this.models = models;
        this.listener = onclick;
        this.filterList = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.list_item, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder myHolder, int position) {
        Model model = models.get(position);

        myHolder.mNamawisata.setText(model.getNama());
        myHolder.mAsalkota.setText(model.getAsal());
        myHolder.mHarga.setText(model.getHarga());
        myHolder.mDesc.setText(model.getDesc());
        Picasso.get().load(model.getImg()).into(myHolder.mImageIv);
        myHolder.bind(model, listener);

        Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
        myHolder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new CustomFilter(filterList, this);
        }
        return filter;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageIv;
        TextView mNamawisata, mAsalkota,mHarga, mJenis,mDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageIv = itemView.findViewById(R.id.ImageView);
            this.mNamawisata = itemView.findViewById(R.id.TextViewNama);
            this.mHarga = itemView.findViewById(R.id.TextViewHarga);
            this.mJenis = itemView.findViewById(R.id.TextViewJenis);
            this.mAsalkota = itemView.findViewById(R.id.TextViewAsal);
            this.mDesc = itemView.findViewById(R.id.TextViewdesc);
        }

        public void bind(final Model model, final Onclick onModelClick){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModelClick.clickItem(model);
                }
            });
        }
    }

    public interface Onclick{
        void clickItem(Model model);
    }
}
