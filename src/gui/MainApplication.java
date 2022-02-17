package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

		addBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			String name = nameTextField.getText().trim();
			if (name.isEmpty()) {
				showAlertBox(AlertType.ERROR, "Message d'erreur", "Valeur invalide",
						"Vous devez remplire le champs nom");
				return;
			}
			observableList.add(name);
			nameTextField.clear();
			showAlertBox(AlertType.INFORMATION, "Message d'information", "Ajouté avec succés",
					"La personne `" + name + "` ajoutée à la liste.");
		});

		listView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			String selectedItem = listView.getSelectionModel().getSelectedItem();
			showAlertBox(AlertType.INFORMATION, "Détails du personne", "informations sur `" + selectedItem + "`",
					"nom : test\nage : 45ans\ntaille : 175cm");
		});

		primaryStage.setTitle("Demo JavaFX Application");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showAlertBox(AlertType alertType, String title, String headerText, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

}
