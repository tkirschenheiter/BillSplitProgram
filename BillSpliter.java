import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class BillSpliter extends Application {
	
	Label msg1;
	Label totalMsg;
	Label totalSplitMsg;
	Label errorMsg;
	private TextField amount;
	private TextField salesTax;
	ComboBox<Double> tipComboBox;
	Spinner<Integer> spinner1;
	
	public void start(Stage primaryStage) {
	    // Create a pane to display the menu
	    // Pane pane = new Pane();
		
	    /* Using GridPane instead of Pane */
	    GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setPadding(new Insets(25, 25, 25, 25));
			grid.setHgap(10);
			grid.setVgap(10);

	    // Create a scene and place it in the stage
	    Scene scene = new Scene(grid, 500, 340);
	    	primaryStage.setTitle("Split Bill Calculator"); // Set the stage title
	    
	    //amount and tax text fields
	    grid.add(new Label("Check amount: "), 0, 0);
		amount = new TextField();
		grid.add(amount, 1, 0);
	    
		grid.add(new Label("Sales tax: "), 0, 1);
		salesTax = new TextField();
		grid.add(salesTax, 1, 1);
	    
		//drop down menu for tip percentage
		tipComboBox = new ComboBox<Double>();
	    tipComboBox.getItems().addAll(5.0, 10.0, 15.0, 20.0, 25.0, 30.0);   
	        
	    grid.add(new Label("Tip percent: "), 0, 2);
	    grid.add(tipComboBox, 1, 2);
	     
	    grid.add(new Label("Split: "), 0, 3);
	    spinner1 = new Spinner<>(1, 100, 0);
	    grid.add(spinner1, 1, 3, 2, 1);
	    
	    //buttons with actions 
	    Button btn = new Button();
	    Button btn2 = new Button();
	    Button btn3 = new Button();
			btn.setText("Calculate");
			btn2.setText("Clear");
			btn3.setText("Exit");
			btn.setOnAction(event -> calculateButtonClicked());
			btn2.setOnAction(event -> clearButtonClicked());
			btn3.setOnAction(event -> exitButtonClicked());
		    
		HBox buttonBox = new HBox(15);
			buttonBox.getChildren().add(btn);
			buttonBox.getChildren().add(btn2);
			buttonBox.getChildren().add(btn3);
			
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		grid.add(buttonBox, 0, 5, 2, 1);
		
		// total and amount per person outputs
		grid.add(new Label("Total: "), 0, 7);
		totalMsg = new Label();
		totalMsg.setWrapText(true);
		totalMsg.setPrefWidth(300);
		totalMsg.setWrapText(true);
		grid.add(totalMsg, 1, 7);
			
		grid.add(new Label("Amount per person: "), 0, 8);
		totalSplitMsg = new Label();
		totalSplitMsg.setWrapText(true);
		totalSplitMsg.setPrefWidth(300);
		totalSplitMsg.setWrapText(true);
		grid.add(totalSplitMsg, 1, 8);
		
		//error message exception handling message
		errorMsg = new Label();
		errorMsg.setWrapText(true);
		errorMsg.setPrefWidth(300);
		errorMsg.setWrapText(true);
		grid.add(errorMsg, 1, 9);
	    
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage   
	}
	
	//calculates bill and split between however many
	private void calculateButtonClicked() {
		try {	
			
			double bill = Double.parseDouble(amount.getText());
			double tax = Double.parseDouble(salesTax.getText())/100;
			double tip = tipComboBox.getValue()/100;
			double split = Double.valueOf(spinner1.getValue());
		
			double total = bill + (bill*tax) + (bill*tip);
			double totalSplit = (bill + (bill*tax) + (bill*tip))/split;
		
			totalMsg.setText("$" + total);
			totalSplitMsg.setText("$" + totalSplit);
		
	    } 
		catch (NumberFormatException e) {
			
			errorMsg.setText("ERROR: Please enter a whole or decimal number");
			
	    }
	}
	
	//clears all inputs
	public void clearButtonClicked() {
	    amount.clear();
	    salesTax.clear();
	    totalMsg.setText("");
	    totalSplitMsg.setText("");
	    errorMsg.setText("");
	    tipComboBox.setValue(null);
	    spinner1.getValueFactory().setValue(1); 
	 }
	
	//exits window
	public void exitButtonClicked() {
		System.exit(0);	    
	}
	
	//launches window
	public static void main(String[] args) {
		    launch(args);
	}

}
