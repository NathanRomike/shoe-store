import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.Rule;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Welcome");
  }

  @Test
  public void newBrandInputOnHomepage() {
    goTo("http://localhost:4567/");
    fill("#newBrand").with("Glo");
    submit("Brandbutton");
    assertThat(pageSource()).contains("Glo");
  }

  @Test
  public void newStoreInputOnHomepage() {
    goTo("http://localhost:4567/");
    fill("#newStore").with("ABC Shoes");
    submit("Storebutton");
    assertThat(pageSource()).contains("ABC Shoes");
  }

  @Test
  public void specificStorePageListsBrandsInStore() {
    Store store = new Store("Gloria's Shoes");
    store.save();
    Brand brand = new Brand("Glos");
    brand.save();
    brand.assign(store);
    goTo("http://localhost:4567/Stores/" + store.getId());
    assertThat(pageSource()).contains("Glos");
  }

  @Test
  public void removeOptionOnStorePageWorks() {
    Store store = new Store("Gloria's Shoes");
    store.save();
    Brand brand = new Brand("Glos");
    brand.save();
    brand.assign(store);
    goTo("http://localhost:4567/Stores/" + store.getId());
    fillSelect("#removebrandselect").withText("Glos");
    submit("#remove-button");
    assertEquals(pageSource(), not("Glos"));
  }
}
