package main;


import java.util.ArrayList;

import controller.BackwardChaining;
import controller.ForwardChaining;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DataBaseController;
import model.Fact;
import model.Rule;
import view.MainStage;

public class Main extends Application {

	public static void main(String[] args) {

	        launch(args);
	        
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		new MainStage().show();
	}
	

}
