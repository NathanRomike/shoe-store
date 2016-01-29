import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sql2o.*;

public class Brand {
  private int mId;
  private String mName;

  public Brand (String name) {
    this.mName = name;
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


}
