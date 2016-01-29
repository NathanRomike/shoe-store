import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void store_emptyAtFirst_all() {
    assertEquals(Store.all().size(), 0);
  }

  @Test
  public void store_createNewStoreWorking_save() {
    Store newStore = new Store("Shoes and Junk!");
    newStore.save();
    assertTrue(newStore instanceof Store);
  }
}
