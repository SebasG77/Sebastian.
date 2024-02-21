package Restaurante;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuFX extends Application {

    private Map<String, Plato> platos;
    private ComboBox<String> platosComboBox;
    private TextField cantidadField;
    private TableView<Plato> table;
    private Map<Plato, Integer> pedido = new HashMap<>();
    private List<Map<Plato, Integer>> pedidosTotales = new ArrayList<>();
    private int numeroCliente = 1;

    @Override
    public void start(Stage primaryStage) {
        inicializarMenu();

        Scene scene = new Scene(inicializarUI(), 400, 400);
        scene.setFill(Color.GREEN);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menú");
        primaryStage.show();
    }

    private GridPane inicializarUI() {
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: lightgreen;");
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Agregamos las imágenes
        ImageView pizzaImageView = new ImageView(new Image("C:/Users/juans/Downloads/ParcialRestaurante/src/Restaurante/pizza.jpg"));
        pizzaImageView.setOnMouseClicked(e -> platosComboBox.setValue("Pizza"));
        grid.add(pizzaImageView, 0, 0);

        ImageView hamburguesaImageView = new ImageView(new Image("C:/Users/juans/Downloads/ParcialRestaurante/src/Restaurante/hamburgueza.jpg"));
        hamburguesaImageView.setOnMouseClicked(e -> platosComboBox.setValue("Hamburguesa"));
        grid.add(hamburguesaImageView, 1, 0);

        ImageView ensaladaImageView = new ImageView(new Image("C:/Users/juans/Downloads/ParcialRestaurante/src/Restaurante/ensalada.jpg"));
        ensaladaImageView.setOnMouseClicked(e -> platosComboBox.setValue("Ensalada"));
        grid.add(ensaladaImageView, 2, 0);

        // Para la imagen de la pizza
        pizzaImageView.setFitWidth(100);
        pizzaImageView.setFitHeight(100);

        // Para la imagen de la hamburguesa
        hamburguesaImageView.setFitWidth(100);
        hamburguesaImageView.setFitHeight(100);

        // Para la imagen de la ensalada
        ensaladaImageView.setFitWidth(100);
        ensaladaImageView.setFitHeight(100);

        // ComboBox para la selección de platos
        Label seleccionLabel = new Label("Seleccione un plato:");
        grid.add(seleccionLabel, 0, 1);
        platosComboBox = new ComboBox<>();
        for (String key : platos.keySet()) {
            platosComboBox.getItems().add(platos.get(key).getNombre());
        }
        grid.add(platosComboBox, 1, 1);

        // Campo de cantidad
        Label cantidadLabel = new Label("Cantidad:");
        grid.add(cantidadLabel, 0, 2);
        cantidadField = new TextField();
        grid.add(cantidadField, 1, 2);

        // Botón de agregar al pedido
        Button agregarButton = new Button("Agregar al Pedido");
        agregarButton.setOnAction(e -> agregarAlPedido());
        grid.add(agregarButton, 2, 2);

        // Tabla de pedido
        table = new TableView<>();
        TableColumn<Plato, String> platoColumn = new TableColumn<>("Plato");
        platoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        TableColumn<Plato, Integer> cantidadColumn = new TableColumn<>("Cantidad");
        cantidadColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(pedido.get(data.getValue())));
        TableColumn<Plato, Double> precioColumn = new TableColumn<>("Precio");
        precioColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPrecio()));
        table.getColumns().addAll(platoColumn, cantidadColumn, precioColumn);
        grid.add(table, 0, 3, 3, 1);

        // Botones de pedidos y calcular total
        Button verPedidosButton = new Button("Ver Pedidos");
        verPedidosButton.setOnAction(e -> mostrarPedidos());
        grid.add(verPedidosButton, 0, 4);

        Button calcularButton = new Button("Calcular Total");
        calcularButton.setOnAction(e -> calcularTotal());
        grid.add(calcularButton, 2, 4);

        return grid;
    }

    private void inicializarMenu() {
        platos = new HashMap<>();
        platos.put("1", new Plato("Pizza", 30000));
        platos.put("2", new Plato("Hamburguesa", 25000));
        platos.put("3", new Plato("Ensalada", 15000));
    }

    private void agregarAlPedido() {
        String seleccion = platosComboBox.getValue();
        int cantidad = Integer.parseInt(cantidadField.getText());
        Plato platoSeleccionado = platos.values().stream().filter(plato -> plato.getNombre().equals(seleccion)).findFirst().orElse(null);

        if (platoSeleccionado != null) {
            pedido.put(platoSeleccionado, pedido.getOrDefault(platoSeleccionado, 0) + cantidad);
            table.getItems().clear();
            table.getItems().addAll(pedido.keySet());
        }
    }

    private void mostrarPedidos() {
        Stage pedidosStage = new Stage();
        VBox vbox = new VBox(10);
        int count = 1;
        for (Map<Plato, Integer> ped : pedidosTotales) {
            ListView<String> listView = new ListView<>();
            for (Map.Entry<Plato, Integer> entry : ped.entrySet()) {
                listView.getItems().add(entry.getKey().getNombre() + " x" + entry.getValue());
            }
            listView.setPrefHeight(100);
            vbox.getChildren().add(new Label("Cliente " + count++));
            vbox.getChildren().add(listView);
        }
        ScrollPane scrollPane = new ScrollPane(vbox);
        Scene scene = new Scene(scrollPane, 250, 400);
        pedidosStage.setScene(scene);
        pedidosStage.setTitle("Pedidos Actuales");
        pedidosStage.show();
    }

    private void calcularTotal() {
        double total = 0;
        int diferentes = pedido.size();

        for (Map.Entry<Plato, Integer> entry : pedido.entrySet()) {
            double precioPlato = entry.getKey().getPrecio();
            total += precioPlato * entry.getValue();
        }

        double descuento = 0;
        if (diferentes > 1) {
            descuento = total * 0.05 * diferentes;
        }

        double iva = 0;
        if (total > 125000) {
            iva = total * 0.19;
        }

        double propina = 0;
        if (diferentes == 1) {
            propina = total * 0.10;
        }

        total = total + iva + propina - descuento;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Total de la Factura");
        alert.setHeaderText(null);
        alert.setContentText("Subtotal: $" + (total - iva + descuento - propina)
                + "\nDescuento: -$" + descuento
                + "\nIVA: $" + iva
                + "\nPropina: $" + propina
                + "\nTotal: $" + total);
        alert.showAndWait();

        pedidosTotales.add(new HashMap<>(pedido));
        numeroCliente++;
        resetPedido();
    }

    private void resetPedido() {
        pedido.clear();
        table.getItems().clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
