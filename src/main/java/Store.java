import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sql2o.*;

public class Store {
  private int mId;
  private String mName;

  public String getName() {
    return mName;
  }

  public int getId() {
    return mId;
  }

  public Store (String name) {
    this.mName = name;
  }

  @Override
  public boolean equals(Object otherStore) {
    if (!(otherStore instanceof Store)) {
      return false;
    } else {
      Store newStore = (Store) otherStore;
      return this.getName().equals(newStore.getName()) &&
             this.getId() == newStore.getId();
    }
  }

  public static List<Store> all() {
    String sql = "SELECT id AS mId, store_name AS mName FROM stores";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Store.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO stores(store_name) VALUES (:name)";
    try(Connection con = DB.sql2o.open()) {
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", this.mName)
        .executeUpdate()
        .getKey();
    }
  }

  public static Store find(int id) {
    String sql = "SELECT id AS mId, store_name AS mName FROM stores WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Store.class);
    }
  }

  public void update(String newName) {
    this.mName = newName;
    String sql = "UPDATE stores SET store_name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("id", mId)
        .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM stores WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", mId)
        .executeUpdate();
    }
  }

  public List<Brand> getBrands() {
    String sql = "SELECT brands.id AS mId, brands.brand_name AS mName FROM stores " +
                 "INNER JOIN store_brand AS s_b ON stores.id = s_b.store_id INNER JOIN " +
                 "brands ON brands.id = s_b.brand_id WHERE stores.id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", this.mId)
        .executeAndFetch(Brand.class);
    }
  }

  public static List<Store> search(String searchInput) {
    String sql = "SELECT stores.id AS mId, stores.store_name AS mName FROM stores " +
                 "INNER JOIN store_brand AS s_b ON stores.id = s_b.store_id " +
                 "INNER JOIN brands ON brands.id = s_b.brand_id WHERE brands.brand_name LIKE :name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("name", "%"+searchInput+"%")
        .executeAndFetch(Store.class);
    }
  }

  public void assign(Brand brand) {
    String sql = "INSERT INTO store_brand (store_id, brand_id) VALUES (:storeId, :brandId)";
    try(Connection con = DB,sql2o.open()) {
      con.createQuery(sql)
        .addParameter("storeId", this.mId)
        .addParameter("brandId", brand.getId())
        .executeUpdate();
    }
  }
}
