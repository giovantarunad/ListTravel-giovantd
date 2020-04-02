package com.example.listtravel.Model;

import java.util.Comparator;

public class Model {
    private String namawisata, asalkota,desc;
    private String img;

    public Model(String namawisata, String asalkota, String desc, String img) {
        this.namawisata = namawisata;
        this.asalkota = asalkota;
        this.desc = desc;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNama() {
        return namawisata;
    }

    public void setNama(String nama) {
        this.namawisata = nama;
    }

    public String getAsal() {
        return asalkota;
    }

    public void setAsal(String asal) {
        this.asalkota = asal;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public  static final Comparator<Model> BY_TITTLE_ASCENDING = new Comparator<Model> ( ) {

        @Override
        public int compare(Model o1, Model o2) {
            return o1.getNama ().compareTo ( o2.getNama () );
        }
    };

    public  static final Comparator<Model> BY_TITTLE_DESCENDING = new Comparator<Model> ( ) {

        @Override
        public int compare(Model o1, Model o2) {
            return o2.getNama ().compareTo ( o1.getNama () );
        }
    };
}
