package com.example.lab5java;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloApplication extends Application {

    private Label productIdLabel;
    private Label productNameLabel;
    private Label orderDateLabel;
    private Label manufacturerLabel;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        VBox vbox = fxmlLoader.load();

        productIdLabel = (Label) vbox.lookup("#productIdLabel");
        productNameLabel = (Label) vbox.lookup("#productNameLabel");
        orderDateLabel = (Label) vbox.lookup("#orderDateLabel");
        manufacturerLabel = (Label) vbox.lookup("#manufacturerLabel");

        Button button = (Button) vbox.lookup("#parseButton");
        button.setOnAction(e -> parseJSON());

        Scene scene = new Scene(vbox, 300, 200);
        stage.setTitle("GSON DEMO");
        stage.setScene(scene);
        stage.show();
    }

    public void parseJSON() {
        try {
            // Reading JSON from a file
            String jsonFilePath = "src/main/resources/products.json";
            String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            Gson gson = new Gson();
            Product product = gson.fromJson(jsonString, Product.class);

            // Display the parsed data in the UI
            productIdLabel.setText("Product ID: " + product.productID);
            productNameLabel.setText("Product Name: " + product.productName);
            orderDateLabel.setText("Order Date: " + product.orderDate);
            manufacturerLabel.setText("Manufacturer: " + product.manufacturer);

            // Converting object back to JSON and writing to a file
            String jsonOutput = gson.toJson(product);
            try (FileWriter fileWriter = new FileWriter("src/main/resources/output.json")) {
                fileWriter.write(jsonOutput);
            }

            System.out.println("Output JSON: " + jsonOutput);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Product {
        public int productID;
        public String productName;
        public String orderDate;
        public String manufacturer;
    }

    public static void main(String[] args) {
        launch();
    }
}