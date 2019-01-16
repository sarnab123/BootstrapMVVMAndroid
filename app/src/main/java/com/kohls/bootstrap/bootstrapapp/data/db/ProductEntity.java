package com.kohls.bootstrap.bootstrapapp.data.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "product")
public class ProductEntity {

    @PrimaryKey
    private String webID;

    @ColumnInfo(name = "product_title")
    private String productTitle;

    @ColumnInfo(name = "product_image")
    private String productImage;

    public String getWebID() {
        return webID;
    }

    public void setWebID(String webID) {
        this.webID = webID;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
