import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("brands", Brand.all());
      model.put("stores", Store.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add-brand", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Brand brand = new Brand(request.queryParams("newBrand"));
      brand.save();
      response.redirect("/");
      return null;
    });

    post("/add-store", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Store store = new Store(request.queryParams("newStore"));
      store.save();
      response.redirect("/");
      return null;
    });

  }
}
