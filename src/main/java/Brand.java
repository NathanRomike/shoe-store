import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sql2o.*;

public class Brand {
  private int mId;
  private String mName;

  public String getName() {
    return mName;
  }

  public int getId() {
    return mId;
  }

  public Brand (String name) {
    this.mName = name;
  }

  @Override
  public boolean equals(Object otherBrand) {
    if (!(otherBrand instanceof Brand)) {
      return false;
    } else {
      Brand newBrand = (Brand) otherBrand;
      return this.getName().equals(newBrand.getName()) &&
             this.getId() == newBrand.getId();
    }
  }

  public void save() {
    String sql = "INSERT INTO brands(brand_name) VALUES (:name)";
    try(Connection con = DB.sql2o.open()) {
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", this.mName)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Brand> all() {
    String sql = "SELECT id AS mId, brand_name AS mName FROM brands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Brand.class);
    }
  }

  public static Brand find(int id) {
    String sql = "SELECT id AS mId, brand_name AS mName FROM brands WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Brand.class);
    }
  }

  public void update(String newName) {
    this.mName = newName;
    String sql = "UPDATE brands SET brand_name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("id", mId)
        .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM brands WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", mId)
        .executeUpdate();
    }
  }

  public List<Store> getStores() {
    String sql = "SELECT stores.id AS mId, stores.store_name AS mName FROM brands " +
                 "INNER JOIN store_brand AS s_b ON brands.id = s_b.brand_id INNER JOIN " +
                 "stores ON stores.id = s_b.store_id WHERE brands.id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", this.mId)
        .executeAndFetch(Store.class);
    }
  }

  public void assign(Store store) {
    String sql = "INSERT INTO store_brand (store_id, brand_id) VALUES (:storeId, :brandId)";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("storeId", store.getId())
        .addParameter("brandId", this.mId)
        .executeUpdate();
    }
  }

  public void remove(Store store) {
    String sql = "DELETE FROM store_brand WHERE store_id = :storeId";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("storeId", store.getId())
        .executeUpdate();
    }
  }
}
