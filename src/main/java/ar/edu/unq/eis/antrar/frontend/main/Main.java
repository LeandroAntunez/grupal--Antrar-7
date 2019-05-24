package ar.edu.unq.eis.antrar.frontend.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stagePrincipal;

    public static void main(String[] args){
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent menuPrincipalParent = FXMLLoader.load(getClass().getResource("/view/menuPrincipalView.fxml"));
		Scene menuPrincipalScene = new Scene(menuPrincipalParent);

		primaryStage.setScene(menuPrincipalScene);
		primaryStage.show();
		primaryStage.setTitle("ANTRAR - Menu Principal");
		setStagePrincipal(primaryStage);
	}

    private void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }
}
