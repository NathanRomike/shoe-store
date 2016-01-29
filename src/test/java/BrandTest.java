import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;
import java.util.Arrays;

public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void brand_createNewBrandWorking_save() {
    Brand newBrand = new Brand("Glo");
    newBrand.save();
    assertTrue(newBrand instanceof Brand);
  }

  @Test
  public void brand_equalsMethodWorking_equals() {
    Brand firstBrand = new Brand("Glo");
    Brand secondBrand = new Brand("Glo");
    assertTrue(firstBrand.equals(secondBrand));
  }

  @Test
  public void brand_allMethodWorking_all() {
    Brand firstBrand = new Brand("Glo");
    firstBrand.save();
    Brand secondBrand = new Brand("Yo");
    secondBrand.save();
    Brand [] allBrands = new Brand [] {firstBrand, secondBrand};
    assertTrue(Brand.all().containsAll(Arrays.asList(allBrands)));
  }

  @Test
  public void brand_getNameWorking_getName() {
    Brand newBrand = new Brand("Glo");
    newBrand.save();
    assertEquals("Glo", newBrand.getName());
  }

  @Test
  public void brand_getIdWorking_getId() {
    Brand newBrand = new Brand("Glo");
    newBrand.save();
    assertEquals(newBrand.getId(), newBrand.getId());
  }

  @Test
  public void brand_findMethodWorking_find() {
    Brand newBrand = new Brand("Glo");
    newBrand.save();
    Brand savedStore = Brand.find(newBrand.getId());
    assertTrue(newBrand.equals(savedStore));
  }

  @Test
  public void brand_updateMethodWorking_update() {
    Brand newBrand = new Brand("Glo");
    newBrand.save();
    newBrand.update("glow");
    assertEquals("glow", newBrand.getName());
  }

  @Test
  public void brand_deleteMethodWorking_delete() {
    Brand newBrand = new Brand("Glo");
    newBrand.save();
    newBrand.delete();
    assertEquals(0, newBrand.all().size());
  }

  @Test
  public void brand_getStoresFromBrandMethod_getStores() {
    Brand newBrand = new Brand("Glo");
    newBrand.save();
    assertEquals(0, newBrand.getStores().size());
  }

  @Test
  public void brand_assignStoreToBrand_assign() {
    Brand brand = new Brand("Glo");
    brand.save();
    Store store = new Store("Gloria's Shoes.");
    store.save();
    brand.assign(store);
    assertEquals(1, brand.getStores().size());
  }
}
