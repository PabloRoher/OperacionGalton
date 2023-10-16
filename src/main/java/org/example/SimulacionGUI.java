package org.example;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SimulacionGUI extends Application{

    private Simulacion simulacion;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Simulacion");

        HBox hBox = new HBox(10);
        Button button = new Button("Iniciar Simulacion");
        button.setOnAction(event -> {
            if(simulacion == null){
                simulacion = new Simulacion();
                simulacion.iniciarSimulacion();

            }else {
                simulacion.iniciarSimulacion();
            }
        });

        hBox.getChildren().add(button);

        Scene scene = new Scene(hBox, 300, 300);
        stage.setScene(scene);
        stage.show();

    }
}
