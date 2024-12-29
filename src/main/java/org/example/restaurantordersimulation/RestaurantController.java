// RestaurantController.java (Controller Layer)
package org.example.restaurantordersimulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Dish;

/**
 * Controller for the restaurant order application.
 */
public class RestaurantController {

    @FXML
    private ChoiceBox<Dish> dishChoiceBox;

    @FXML
    private TableView<Dish> orderTable;

    @FXML
    private TableColumn<Dish, String> nameColumn;

    @FXML
    private TableColumn<Dish, Double> priceColumn;

    @FXML
    private Label totalPriceLabel;

    private final ObservableList<Dish> menu = FXCollections.observableArrayList();
    private final ObservableList<Dish> order = FXCollections.observableArrayList();

    private double totalPrice = 0.0;

    /**
     * Initializes the controller and binds data to the UI components.
     */
    @FXML
    public void initialize() {
        // Populate the menu with dishes
        menu.addAll(
            new Dish("Burger", 8.99),
            new Dish("Pizza", 12.50),
            new Dish("Salad", 6.75),
            new Dish("Pasta", 10.25)
        );

        dishChoiceBox.setItems(menu);
        orderTable.setItems(order);

        // Bind columns to Dish properties
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()).asObject());

        updateTotalPrice();
    }

    /**
     * Handles adding the selected dish to the order.
     * @param event ActionEvent triggered by clicking the 'Add to Order' button.
     */
    @FXML
    private void handleAddToOrder(ActionEvent event) {
        Dish selectedDish = dishChoiceBox.getValue();
        if (selectedDish != null) {
            order.add(selectedDish);
            totalPrice += selectedDish.getPrice();
            updateTotalPrice();
        }
    }

    /**
     * Updates the total price label.
     */
    private void updateTotalPrice() {
        totalPriceLabel.setText(String.format("Total: $%.2f", totalPrice));
    }
}