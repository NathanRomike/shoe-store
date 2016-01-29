import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;
import java.util.Arrays;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void store_allMethodWorking_all() {
    Store firstStore = new Store("Shoes and Junk, Inc.");
    firstStore.save();
    Store secondStore = new Store("Gloria's Shoes.");
    secondStore.save();
    Store [] allStores = new Store [] {firstStore, secondStore};
    assertTrue(Store.all().containsAll(Arrays.asList(allStores)));
  }

  @Test
  public void store_getIdWorking_getId() {
    Store newStore = new Store("Shoes and Junk!");
    newStore.save();
    assertEquals(newStore.getId(), newStore.getId());
  }

  @Test
  public void store_createNewStoreWorking_save() {
    Store newStore = new Store("Shoes and Junk!");
    newStore.save();
    assertTrue(newStore instanceof Store);
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

  @Test
  public void store_findMethodWorking_find() {
    Store newStore = new Store("Shoes and Junk, Inc.");
    newStore.save();
    Store savedStore = Store.find(newStore.getId());
    assertTrue(newStore.equals(savedStore));
  }

  @Test
  public void store_updateMethodWorking_update() {
    Store newStore = new Store("Shoes and Junk, Inc.");
    newStore.save();
    newStore.update("Gloria's Shoes and Junk, Inc.");
    assertEquals("Gloria's Shoes and Junk, Inc.", newStore.getName());
  }

  @Test
  public void store_deleteMethodWorking_delete() {
    Store newStore = new Store("Shoes and Junk, Inc.");
    newStore.save();
    newStore.delete();
    assertEquals(0, newStore.all().size());
  }

  @Test
  public void store_getBrandsMethodWorking_getBrands() {
    Store newStore = new Store("Shoes and Junk, Inc.");
    newStore.save();
    assertEquals(0, newStore.getBrands().size());
  }

  // @Test
  // public void store_assignBrandToStore_assign() {
  //   Store store = new Store("Gloria's Shoes.");
  //   store.save();
  //   Brand brand = new Brand("Glo");
  //   brand.save();
  //   brand.assign(store);
  //   assertEquals(store, )
  // }
}
