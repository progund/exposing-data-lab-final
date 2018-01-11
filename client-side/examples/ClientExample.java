package examples;

import java.util.List;

import se.itu.systemet.domain.Product;

public class ClientExample {
  public static void main(String[] args) {
    String query = "";
    if (args.length == 1) {
      query = args[0];
    }
    
    Client client = new Client("http://localhost:8080/search/products/all?" + query);
    String JSON = client.getJson();
    ProductParser parser = new ProductParser();
    List<Product> products = parser.parse(JSON);
    System.out.println("Found: " + products.size() + " products.");
    if (products.size() < 40) {
      products.stream().forEach(System.out::println);
    }
  }
}
