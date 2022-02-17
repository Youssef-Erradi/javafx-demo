package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Insets insets = new Insets(5);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);

		Label label = new Label("Entrez un nom :");
		label.setPadding(insets);
		TextField nameTextField = new TextField();
		nameTextField.setPadding(insets);
		Button addBtn = new Button("Add");
		addBtn.setPadding(insets);

		HBox hBox = new HBox();
		hBox.setPadding(insets);
		hBox.setSpacing(10);
		hBox.getChildren().addAll(label, nameTextField, addBtn);
		root.setTop(hBox);

		ObservableList<String> observableList = FXCollections.observableArrayList();
		ListView<String> listView = new ListView<>(observableList);
		root.setCenter(listView);

		primaryStage.setTitle("Demo JavaFX Application");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
