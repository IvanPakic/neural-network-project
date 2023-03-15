package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import controller.MainController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Fact;

public class MainStage extends Stage {

	ComboBox<Fact> cbJedan;
	ComboBox<Fact> cbDva;
	private ObservableList<Fact>  listOfFacts;
	private ObservableList<Fact>  listOfRules;
	MainController controller;
	ToggleGroup tgGroup;
	RadioButton rbUnapred;
	TableView<Fact> tbView;
	TextField tfTekst;
	TextArea area;
	
	public MainStage() {
		controller = new MainController();
		
		Label lblNaslov = new Label("Pokreni program");
		lblNaslov.setStyle("-fx-font-size: 12px;\r\n" + 
							"    -fx-font-weight: bold;\r\n" + 
							"    -fx-text-fill: #333333;\r\n" + 
							"    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		VBox vbNaslov = new VBox(10);
		vbNaslov.setAlignment(Pos.CENTER);
		vbNaslov.setPadding(new Insets(20, 10, 30, 10));
		vbNaslov.getChildren().add(lblNaslov);
		
		Button btnDugme = new Button("Dugme");
		btnDugme.setStyle("-fx-background-color: \r\n" + 
				"        #090a0c,\r\n" + 
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\r\n" + 
				"        linear-gradient(#20262b, #191d22),\r\n" + 
				"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\r\n" + 
				"    -fx-background-radius: 5,4,3,5;\r\n" + 
				"    -fx-background-insets: 0,1,2,0;\r\n" + 
				"    -fx-text-fill: white;\r\n" + 
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\r\n" + 
				"    -fx-font-family: \"Arial\";\r\n" + 
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\r\n" + 
				"    -fx-font-size: 12px;\r\n" + 
				"    -fx-padding: 10 20 10 20");
		btnDugme.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				Stage stage = new Stage();
				cbJedan = new ComboBox<Fact>();
				cbJedan.setMaxWidth(200);
				cbJedan.setStyle("-fx-background-color: darkgrey;\r\n" + 
						"-fx-border-color:black;\r\n" + 
						"    -fx-font-size: 12px;\r\n" +
						"-fx-border-radius:4px;");
				listOfFacts = controller.getGreenController();
				listOfRules = controller.getPurpleController();
				setComboRules(listOfRules);
				
				cbDva = new ComboBox<Fact>();
				cbDva.setMaxWidth(200);
				cbDva.setStyle("-fx-background-color: darkgrey;\r\n" + 
						"-fx-border-color:black;\r\n" + 
						"    -fx-font-size: 12px;\r\n" +
						"-fx-border-radius:4px;");
				setComboFacts(listOfFacts); 
				
				tgGroup = new ToggleGroup();
				rbUnapred = new RadioButton("Ulancavanje unapred");
				rbUnapred.setToggleGroup(tgGroup);
				rbUnapred.setSelected(true);
				RadioButton rbUnazad = new RadioButton("Ulancavanje unazad");
				rbUnazad.setToggleGroup(tgGroup);
				
				HBox hbRadio = new HBox(10);
				hbRadio.getChildren().addAll(rbUnapred, rbUnazad);
				
				Button btnValue = new Button("Izracunaj");
				btnValue.setStyle("-fx-background-color: \r\n" + 
						"        #000000,\r\n" + 
						"        linear-gradient(#7ebcea, #2f4b8f),\r\n" + 
						"        linear-gradient(#426ab7, #263e75),\r\n" + 
						"        linear-gradient(#395cab, #223768);\r\n" + 
						"    -fx-background-radius: 5,4,3,5;\r\n" + 
						"    -fx-background-insets: 0,1,2,0;\r\n" + 
						"    -fx-text-fill: white;\r\n" + 
						"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\r\n" + 
						"    -fx-font-family: \"Arial\";\r\n" + 
						"    -fx-text-fill: linear-gradient(white, #d0d0d0);\r\n" + 
						"    -fx-font-size: 12px;\r\n" + 
						"    -fx-padding: 10 20 10 20");
				btnValue.setOnAction(new EventHandler<ActionEvent>() {
				
					
					@Override
					public void handle(ActionEvent event) {
						onIzracunaj();
					}
				});
				
				Button btnDadaj = new Button("Dadaj");
				btnDadaj.setStyle("-fx-background-color: \r\n" + 
						"        #000000,\r\n" + 
						"        linear-gradient(#7ebcea, #2f4b8f),\r\n" + 
						"        linear-gradient(#426ab7, #263e75),\r\n" + 
						"        linear-gradient(#395cab, #223768);\r\n" + 
						"    -fx-background-radius: 5,4,3,5;\r\n" + 
						"    -fx-background-insets: 0,1,2,0;\r\n" + 
						"    -fx-text-fill: white;\r\n" + 
						"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\r\n" + 
						"    -fx-font-family: \"Arial\";\r\n" + 
						"    -fx-text-fill: linear-gradient(white, #d0d0d0);\r\n" + 
						"    -fx-font-size: 12px;\r\n" + 
						"    -fx-padding: 10 20 10 20");
				btnDadaj.setOnAction(new EventHandler<ActionEvent>() {
				
					
					@Override
					public void handle(ActionEvent event) {
						onDodaj();
					}
				});
				
				Button btnObrisi = new Button("Obrisi");
				btnObrisi.setStyle("-fx-background-color: \r\n" + 
						"        #000000,\r\n" + 
						"        linear-gradient(#7ebcea, #2f4b8f),\r\n" + 
						"        linear-gradient(#426ab7, #263e75),\r\n" + 
						"        linear-gradient(#395cab, #223768);\r\n" + 
						"    -fx-background-radius: 5,4,3,5;\r\n" + 
						"    -fx-background-insets: 0,1,2,0;\r\n" + 
						"    -fx-text-fill: white;\r\n" + 
						"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\r\n" + 
						"    -fx-font-family: \"Arial\";\r\n" + 
						"    -fx-text-fill: linear-gradient(white, #d0d0d0);\r\n" + 
						"    -fx-font-size: 12px;\r\n" + 
						"    -fx-padding: 10 20 10 20");
				btnObrisi.setOnAction(new EventHandler<ActionEvent>() {
				
					
					@Override
					public void handle(ActionEvent event) {
						onObrisi();
					}
				});
				
				Button btnZasto = new Button("Zasto");
				btnZasto.setStyle("-fx-background-color: \r\n" + 
						"        #000000,\r\n" + 
						"        linear-gradient(#7ebcea, #2f4b8f),\r\n" + 
						"        linear-gradient(#426ab7, #263e75),\r\n" + 
						"        linear-gradient(#395cab, #223768);\r\n" + 
						"    -fx-background-radius: 5,4,3,5;\r\n" + 
						"    -fx-background-insets: 0,1,2,0;\r\n" + 
						"    -fx-text-fill: white;\r\n" + 
						"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\r\n" + 
						"    -fx-font-family: \"Arial\";\r\n" + 
						"    -fx-text-fill: linear-gradient(white, #d0d0d0);\r\n" + 
						"    -fx-font-size: 12px;\r\n" + 
						"    -fx-padding: 10 20 10 20");
				btnZasto.setOnAction(new EventHandler<ActionEvent>() {
				
					
					@Override
					public void handle(ActionEvent event) {
						onZasto();
					}
				});
				
				Button btnKraj = new Button("Kraj");
				btnKraj.setStyle("-fx-background-color: \r\n" + 
						"        #090a0c,\r\n" + 
						"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\r\n" + 
						"        linear-gradient(#20262b, #191d22),\r\n" + 
						"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\r\n" + 
						"    -fx-background-radius: 5,4,3,5;\r\n" + 
						"    -fx-background-insets: 0,1,2,0;\r\n" + 
						"    -fx-text-fill: white;\r\n" + 
						"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\r\n" + 
						"    -fx-font-family: \"Arial\";\r\n" + 
						"    -fx-text-fill: linear-gradient(white, #d0d0d0);\r\n" + 
						"    -fx-font-size: 12px;\r\n" + 
						"    -fx-padding: 10 20 10 20");
				btnKraj.setOnAction(new EventHandler<ActionEvent>() {
				
					
					@Override
					public void handle(ActionEvent event) {
						stage.close();
					}
				});
				
				
				tbView = new TableView<Fact>();
				TableColumn<Fact, Integer> tcId = new TableColumn<Fact, Integer>("Fact id");
				tcId.setMinWidth(100);
				tcId.setStyle("-fx-text-fill: blue;\r\n" + 
						"    -fx-fixed-cell-size: 50;\r\n" +
						"    -fx-alignment: CENTER;\r\n" +
						"	-fx-border-color: black;\r\n" + 
						"	-fx-table-border-color:black;\r\n");
				tcId.setResizable(false);
				tcId.setCellValueFactory(new PropertyValueFactory<>("factID"));
				tbView.setStyle("-fx-background-color: #d3d3d3 ;\r\n"  + 
								"-fx-font-size: 12px;\r\n"  + 
								"-fx-border-radius:4px;");
				
				TableColumn<Fact, String> tcDescription = new TableColumn<Fact, String>("Fact description");
				tcDescription.setMinWidth(200);
				tcDescription.setStyle( "-fx-text-fill: blue;\r\n" +
						"    -fx-fixed-cell-size: 50;\r\n" + 
						"	-fx-border-color: black;\r\n" + 
						"	-fx-table-border-color:black;\r\n" +
						"    -fx-alignment: CENTER;");
				tcDescription.setResizable(false);
				tcDescription.setCellValueFactory(new PropertyValueFactory<>("factDescription"));
				tbView.setMaxWidth(310);
				tbView.setMinHeight(145);
				tbView.getColumns().add(tcId);
				tbView.getColumns().add(tcDescription);
				
				HBox hbDugme = new HBox(10);
				hbDugme.setAlignment(Pos.BASELINE_LEFT);
				hbDugme.getChildren().addAll(btnDadaj, btnObrisi);
				
				HBox hbZasto = new HBox(10);
				hbZasto.setAlignment(Pos.BASELINE_RIGHT);
				hbZasto.getChildren().add(btnZasto);
				
				HBox hbKraj = new HBox(10);
				hbKraj.setAlignment(Pos.BASELINE_RIGHT);
				hbKraj.getChildren().add(btnKraj);
				
				tfTekst = new TextField();
				tfTekst.setStyle("-fx-background-color: #a9a9a9 ;\r\n" + 
						"-fx-background-insets: 0, 0 0 1 0 ;\r\n" + 
						"-fx-border-color:black;\r\n" + 
						"-fx-font-size: 12px;\r\n" +
						"-fx-border-radius:4px;");
				tfTekst.setMaxWidth(300);
				tfTekst.setMaxHeight(10);
				
				area = new TextArea();
				area.setStyle("-fx-background-color: #d3d3d3 ;\r\n" + 
						"-fx-border-color:black;\r\n" +
						"-fx-font-size: 12px;\r\n" +
						"-fx-border-radius:4px;");
				area.setPrefColumnCount(15);
				area.setMaxWidth(300);
				area.setMaxHeight(200);
				
				GridPane gpMain = new GridPane();
				gpMain.setPadding(new Insets(0, 20, 10, 20));
				gpMain.setVgap(20);
				gpMain.setHgap(20);
				gpMain.setAlignment(Pos.CENTER);
				//col, row
				gpMain.add(cbJedan, 0, 0);
				gpMain.add(cbDva, 0, 1);
				gpMain.add(hbDugme, 0, 2);
				gpMain.add(tbView, 0, 3);
				gpMain.add(hbRadio, 0, 4);
				gpMain.add(btnValue, 0, 5);
				gpMain.add(tfTekst, 1, 0);
				gpMain.add(hbZasto, 1, 2);
				gpMain.add(area, 1, 3);
				gpMain.add(hbKraj, 1, 5);
				
				VBox vblevo = new VBox(10);
				vblevo.setAlignment(Pos.CENTER_LEFT);
				vblevo.setPadding(new Insets(20));
				vblevo.getChildren().addAll(gpMain);
				
				VBox vbDesno = new VBox(10);
				vbDesno.setAlignment(Pos.CENTER_RIGHT);
				vbDesno.setPadding(new Insets(20));
				vbDesno.getChildren().addAll(gpMain);
				
				HBox hbMain = new HBox();
				hbMain.setAlignment(Pos.CENTER);
				hbMain.getChildren().addAll(vblevo, vbDesno);
				
				stage.setScene(new Scene(hbMain));
				stage.setTitle("Algoritmi");
				stage.setWidth(900);
				stage.setHeight(450);
				stage.setResizable(false);
				stage.show();
				
			}
		});
		
		HBox hbMain = new HBox(20);
		hbMain.setAlignment(Pos.CENTER);
		hbMain.getChildren().add(btnDugme);
		
		
		
		VBox vbMain = new VBox(10);
		vbMain.setPadding(new Insets(10, 10, 10, 10));
		vbMain.setAlignment(Pos.CENTER);
		vbMain.getChildren().addAll(vbNaslov,hbMain);
		
		setScene(new Scene(vbMain));
		setWidth(450);
		setHeight(200);
		setTitle("PVI-PROJEKAT");
		setResizable(false);

	}
	
	private void setComboRules(ObservableList<Fact> facts) {
		cbJedan.setItems(facts);
		
	}
	
	private void setComboFacts(ObservableList<Fact> facts) {
		cbDva.setItems(facts);
		
	}
	
	public void onDodaj() {
		tbView.getItems().add(cbDva.getValue());
	}
	
	public void onObrisi() {
		Fact selectedItem = tbView.getSelectionModel().getSelectedItem();
		tbView.getItems().remove(selectedItem);
	}
	
	public void onIzracunaj(){	
		List<Fact> showing = tbView.getItems();
		ArrayList<Fact> novaLista = new ArrayList<Fact>(showing);
		boolean isUnapred = rbUnapred.isSelected();
		Fact goal = cbJedan.getValue();

		boolean s = controller.chaning(goal, isUnapred, novaLista);

		tfTekst.setText(String.valueOf(s));
	}
	
	public void onZasto() {
		area.setText(controller.getMyConclusion());
	}
}
