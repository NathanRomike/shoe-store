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
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, store_name AS mName FROM stores";
      return con.createQuery(sql)
        .executeAndFetch(Store.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores(store_name) VALUES (:name)";
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
}
