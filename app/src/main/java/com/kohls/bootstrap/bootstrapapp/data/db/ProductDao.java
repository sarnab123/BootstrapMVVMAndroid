package com.kohls.bootstrap.bootstrapapp.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    List<ProductEntity> all();

    @Query("SELECT COUNT(*) from product")
    int count();

    @Insert
    void insert(ProductEntity... productEntities);
}
