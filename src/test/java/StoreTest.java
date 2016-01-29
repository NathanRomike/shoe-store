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

  @Test
  public void store_getIdWorking_getId() {
    Store newStore = new Store("Shoes and Junk!");
    newStore.save();
    assertEquals(newStore.getId(), newStore.getId());
  }

  @Test
  public void store_getNameWorking_getName() {
    Store newStore = new Store("Shoes and Junk, Inc.");
    newStore.save();
    assertEquals("Shoes and Junk, Inc.", newStore.getName());
  }

  @Test
  public void store_equalsWorking_equals() {
    Store firstStore = new Store("Gloria's Shoes.");
    Store secondStore = new Store("Gloria's Shoes.");
    assertTrue(secondStore.equals(firstStore));
  }
}
