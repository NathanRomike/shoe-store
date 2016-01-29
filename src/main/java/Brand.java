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
}
