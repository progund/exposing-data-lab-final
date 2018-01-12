package examples;

import java.util.function.Predicate;
import java.util.List;
import se.itu.systemet.domain.Product;
import se.itu.systemet.storage.ProductLine;
import se.itu.systemet.storage.ProductLineFactory;

public class ApiExample {
  public static void main(String[] args) {
    // You can achieve the same thing running this program with
    // the flag -DProductLine=DB
    System.setProperty("ProductLine", "DB");
    ProductLine productLine = ProductLineFactory.getProductLine();
    Predicate<Product> maxPriceFilter = p -> p.price() <= 20;
    List<Product> products = productLine.getProductsFilteredBy(maxPriceFilter);
    products.stream().forEach(System.out::println);
  }
}
