package it.polito.tdp.poweroutages;

import it.polito.tdp.poweroutages.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Scene.fxml"));
    	Parent root = loader.load();
        Scene scene = new Scene(root);
         
        Model model = new Model();
        FXMLController controller = loader.getController();
        controller.setModel(model);
        
        stage.setTitle("2019-06-05-simulazione");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


}
