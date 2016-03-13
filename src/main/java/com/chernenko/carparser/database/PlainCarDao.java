package com.chernenko.carparser.database;

import com.chernenko.carparser.entity.Car;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PlainCarDao {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("fail");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Slava?useUnicode=true&characterEncoding=utf8", "root", "root");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException ex) { // обработка отсутствует
        }
    }


    public void insert(Car car) {
        Connection connection = null;
        try {

            String sqlToProduct = "INSERT INTO oc_product SET " +
                    "model = '" + car.getTitle() + "', " +
                    "quantity = '16'," +
                    "minimum = '1', " +
                    "subtract = '1', " +
                    "stock_status_id = '5', " +
                    "date_available = '2016-03-12', " +
                    "manufacturer_id = '0', " +
                    "shipping = '1', " +
                    "price = '" + car.getPrice() + "', " +
                    "weight_class_id = '2', " +
                    "length_class_id = '2', " +
                    "status = '1', " +
                    "tax_class_id = '0', " +
                    "sort_order = '1', " +
                    "image = '" + car.getMainPhoto() + "'," +
                    "sku = '', " +
                    "upc = '', " +
                    "ean = '', " +
                    "jan = '', " +
                    "isbn = ''," +
                    "mpn = '', " +
                    "location = '', " +
                    "date_added = NOW()";

            System.out.println(sqlToProduct);

            String sqlGetProductId = "select product_id from oc_product order by 1 DESC limit 0,1";


            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlToProduct);
            statement.execute();

            ResultSet set = statement.executeQuery(sqlGetProductId);
            set.next();
            int product_id = set.getInt("product_id");


            String sqlToDescription = "INSERT INTO oc_product_description SET " +
                    "product_id = '" + product_id + "', " +
                    "language_id = '1', " +
                    "name = '" + car.getTitle() + "', " +
                    "meta_keyword = '', " +
                    "meta_description = '', " +
                    "description = '" + car.getDescription() + "', " +
                    "description_mini = '" + car.getShortDescription() + "', " +
                    "tag = '', " +
                    "seo_title = '', " +
                    "seo_h1 = ''";


            statement = connection.prepareStatement(sqlToDescription);
            statement.execute();

            String sqlToAlias = "INSERT INTO oc_url_alias SET query = " +
                    "'product_id = " + product_id + "', " +
                    "keyword = '" + car.getUrl() + "'";

            statement = connection.prepareStatement(sqlToAlias);
            statement.execute();

            String sqlToGroup = "INSERT INTO oc_product_to_category SET product_id = '" +
                    product_id + "', category_id = '74', main_category = 1";

            statement = connection.prepareStatement(sqlToGroup);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }


}