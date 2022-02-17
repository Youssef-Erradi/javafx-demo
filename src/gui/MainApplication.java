package gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pojos.Person;

public class MainApplication extends Application {
	private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		Insets insets = new Insets(5);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);

		VBox vBoxForm = new VBox(10);
		vBoxForm.setPadding(insets);

		HBox hBox = new HBox(10);
		hBox.setPadding(insets);

		// Full Name
		Label label = new Label("Nom complet :");
		label.setPadding(insets);
		TextField fullNameTextField = new TextField();
		fullNameTextField.setPadding(insets);
		hBox.getChildren().addAll(label, fullNameTextField);
		vBoxForm.getChildren().add(hBox);

		// Birthday
		hBox = new HBox(10);
		hBox.setPadding(insets);
		label = new Label("Date de naissance :");
		label.setPadding(insets);
		DatePicker datePicker = new DatePicker();
		hBox.getChildren().addAll(label, datePicker);
		vBoxForm.getChildren().add(hBox);

		// Sex
		hBox = new HBox(10);
		hBox.setPadding(insets);
		label = new Label("Sexe : ");
		label.setPadding(insets);
		ToggleGroup group = new ToggleGroup();
		RadioButton rbMale = new RadioButton();
		rbMale.setText("Homme");
		rbMale.setToggleGroup(group);
		rbMale.setSelected(true);
		RadioButton rbFemale = new RadioButton();
		rbFemale.setText("Femme");
		rbFemale.setToggleGroup(group);
		hBox.getChildren().addAll(label, rbMale, rbFemale);
		vBoxForm.getChildren().add(hBox);

		// Buttons
		hBox = new HBox(10);
		hBox.setPadding(insets);
		Button submitBtn = new Button("Valider");
		Button resetBtn = new Button("Réinitialiser");
		hBox.getChildren().addAll(submitBtn, resetBtn);
		vBoxForm.getChildren().add(hBox);

		// TableView
		ObservableList<Person> people = FXCollections.observableArrayList();
		TableView<Person> tableView = new TableView<>(people);
		tableView.setEditable(false);
		TableColumn<Person, String> idTableColumn = createColumn("Identifiant", "id");
		TableColumn<Person, String> fullNameTableColumn = createColumn("Nom complet", "fullName");
		TableColumn<Person, String> birthdayTableColumn = createColumn("Date de naissance", "formattedBirthday");
		TableColumn<Person, String> sexTableColumn = createColumn("Sexe", "sex");
		tableView.getColumns().addAll(idTableColumn, fullNameTableColumn, birthdayTableColumn, sexTableColumn);

		tableView.setOnMouseClicked(event -> {
			Person person = tableView.getSelectionModel().getSelectedItem();
			showAlertBox(AlertType.INFORMATION, "Détails de personne", "informations de " + person.getFullName(),
					person.toString());
		});

		resetBtn.setOnMouseClicked(event -> {
			fullNameTextField.clear();
			datePicker.setValue(null);
			rbMale.setSelected(true);
		});

		submitBtn.setOnMouseClicked(event -> {
			String fullname = fullNameTextField.getText().trim(),
					sexe = (RadioButton) group.getSelectedToggle() == rbMale ? "Homme" : "Femme";
			LocalDate date = datePicker.getValue();
			
			if (fullname.isEmpty() || date == null) {
				showAlertBox(AlertType.WARNING, "Attention !", "Erreur de validation",
						"veuillez remplir tous les champs");
				return;
			}
			try {
				Person person = new Person(fullname, sexe, DATE_FORMAT.parse(date.toString()) );
				people.add(person);
				showAlertBox(AlertType.INFORMATION, "Message d'information", "Personne ajouté avec succés",
						person.toString());
			} catch(ParseException e) {
				e.printStackTrace();
			}
		});

		root.setTop(vBoxForm);
		root.setCenter(tableView);

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

	private TableColumn<Person, String> createColumn(String columnHeader, String propertyValue) {
		TableColumn<Person, String> myTableColumn = new TableColumn<>(columnHeader);
		myTableColumn.setMinWidth(100);
		myTableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyValue));
		return myTableColumn;
	}

}
