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
    Brand secondBrand = new Brand("Yo");
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
}
