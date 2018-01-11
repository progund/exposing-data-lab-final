package examples;

import org.json.*;
import java.util.List;
import java.util.ArrayList;

import se.itu.systemet.domain.Product;

public class ProductParser {
  public List<Product> parse(String json) {
    List<Product> products = new ArrayList<>();    
    JSONArray jsonArray = new JSONArray(json);
    for (int i = 0; i < jsonArray.length(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      String name = jsonObject.getString("name");
      double price = jsonObject.getDouble("price");
      double alcohol = jsonObject.getDouble("alcohol");
      int volume = jsonObject.getInt("volume");
      int nr = jsonObject.getInt("nr");
      String productGroup = jsonObject.getString("product_group");
      String type = "";
      products.add(new Product.Builder()
                   .name(name)
                   .price(price)
                   .alcohol(alcohol)
                   .volume(volume)
                   .nr(nr)
                   .productGroup(productGroup)
                   .type(type)
                   .build());
    }
    return products;
  }

}
