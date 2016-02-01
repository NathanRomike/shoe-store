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

    post("/update-store", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Store store = Store.find(Integer.parseInt(request.queryParams("storeUpdateSelection")));
      String storeUpdate = request.queryParams("update-store");
      store.update(storeUpdate);
      response.redirect("/");
      return null;
    });

    post("/delete-store", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Store store = Store.find(Integer.parseInt(request.queryParams("storeDeleteSelection")));
      store.delete();
      response.redirect("/");
      return null;
    });

    get("/stores", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stores", Store.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/brands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("brands", Brand.all());
      model.put("template", "templates/brands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete-brand", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Brand brand = Brand.find(Integer.parseInt(request.queryParams("brandDeleteSelection")));
      brand.delete();
      response.redirect("/brands");
      return null;
    });

    get("/stores/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Store store = Store.find(Integer.parseInt(request.params("id")));
      model.put("store", store);
      model.put("stores", Store.class);
      model.put("brands", Brand.class);
      model.put("template", "templates/store.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stores/:id/assignbrand", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Store store = Store.find(Integer.parseInt(request.params("id")));
      Brand brand = Brand.find(Integer.parseInt(request.queryParams("brandSelection")));
      store.assign(brand);
      response.redirect("/stores/" + store.getId());
      return null;
    });

    post("/stores/:id/removebrand", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Store store = Store.find(Integer.parseInt(request.params("id")));
      Brand brand = Brand.find(Integer.parseInt(request.queryParams("removebrandselect")));
      store.remove(brand);
      response.redirect("/stores/" + store.getId());
      return null;
    });

    get("/brands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Brand brand = Brand.find(Integer.parseInt(request.params("id")));
      model.put("brand", brand);
      model.put("brands", Brand.class);
      model.put("stores", Store.class);
      model.put("template", "templates/brand.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("brands/:id/assignstore", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Brand brand = Brand.find(Integer.parseInt(request.params("id")));
      Store store = Store.find(Integer.parseInt(request.queryParams("storeSelection")));
      brand.assign(store);
      response.redirect("/brands/" + brand.getId());
      return null;
    });

    post("/brands/:id/removestore", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Brand brand = Brand.find(Integer.parseInt(request.params("id")));
      Store store = Store.find(Integer.parseInt(request.queryParams("removestoreselect")));
      brand.remove(store);
      response.redirect("/brands/" + brand.getId());
      return null;
    });
  }
}
