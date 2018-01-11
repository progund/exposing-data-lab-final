package se.itu.systemet.storage;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
  public static class ColumnId {
    public static final int NAME = 1;
    public static final int PRODUCT_NR = 2;
    public static final int ALCOHOL = 3;
    public static final int PRICE = 4;
    public static final int VOLUME = 5;
    public static final int TYPE = 6;
    public static final int PRODUCT_GROUP = 7;
  }
  // Tables
  private static final String PRODUCT_TABLE = "product";
  private static final String PRODUCT_GROUP_TABLE = "productGroup";
  // Product table columns
  private static final String PRODUCT_NR = "nr";
  private static final String PRODUCT_NAME = "name";
  private static final String ALCOHOL = "alcohol";
  private static final String PRICE = "price";
  private static final String VOLUME = "volume";
  private static final String TYPE = "type";
  private static final String PRODUCT_GROUP_ID = "productGroupId";
  // productGroup table columns
  private static final String ID = "id";
  private static final String PRODUCT_GROUP = "name";

  private static final String DB_URL =
    "jdbc:sqlite:webroot/WEB-INF/db/bolaget.db";
  private static Connection connection;
  static {
    try {
      connection = DriverManager.getConnection(DB_URL);
    } catch (SQLException sqle) {
      System.err.println("Couldn't get connection to " + DB_URL +
                         sqle.getMessage());
    }
  }

  public static ResultSet productsResultSet() {
    try {
      Statement statement = connection.createStatement();
      StringBuilder SQL = new StringBuilder("SELECT ")
        .append(PRODUCT_TABLE).append(".").append(PRODUCT_NAME)
        .append(", ").append(PRODUCT_TABLE).append(".").append(PRODUCT_NR)
        .append(", ").append(PRODUCT_TABLE).append(".").append(ALCOHOL)
        .append(", ").append(PRODUCT_TABLE).append(".").append(PRICE)
        .append(", ").append(PRODUCT_TABLE).append(".").append(VOLUME)
        .append(", ").append(PRODUCT_TABLE).append(".").append(TYPE)
        .append(", ").append(PRODUCT_GROUP_TABLE).append(".").append(PRODUCT_GROUP)
        .append(" FROM ").append(PRODUCT_TABLE).append(" JOIN ").append(PRODUCT_GROUP_TABLE)
        .append(" ON ").append(PRODUCT_TABLE).append(".").append(PRODUCT_GROUP_ID)
        .append(" = ").append(PRODUCT_GROUP_TABLE).append(".").append(ID)
        .append(";");
      //System.out.println("SQL: " + SQL);
      return statement.executeQuery(SQL.toString());
    } catch (SQLException sqle) {
      System.err.println("Couldn't get resultset with products: " + sqle.getMessage());
      return null;
    }
  }
}
