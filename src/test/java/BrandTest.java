import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;

public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void brand_createNewBrandWorking_save() {
    Brand newBrand = new Brand("Glo");
    newBrand.save();
    assertTrue(newBrand instanceof Brand);
  }
}
