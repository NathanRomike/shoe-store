import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/shoe_store_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteBrandQuery = "DELETE FROM brands*;";
      String deleteStoreQuery = "DELETE FROM stores *;";
      con.createQuery(deleteBrandQuery).executeUpdate();
      con.createQuery(deleteStoreQuery).executeUpdate();
    }
  }
}
