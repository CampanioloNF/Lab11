package it.polito.tdp.bar;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class BarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> Combo10;

    @FXML
    private ComboBox<Integer> Combo8;

    @FXML
    private ComboBox<Integer> Combo6;

    @FXML
    private ComboBox<Integer> Combo4;

    @FXML
    private RadioButton radio25;

    @FXML
    private RadioButton radio50;

    @FXML
    private RadioButton radio75;

    @FXML
    private TextArea txtResult;

	private Model model;
	
	private static final int MAX_TAVOLI = 20;
	private static final double PERC25 = 0.25;
	private static final double PERC50 = 0.5;
	private static final double PERC75 = 0.75;

    @FXML
    void doClear(ActionEvent event) {

    	txtResult.clear();
    	this.doRadio50(event);
    	
    }

    @FXML
    void doRadio25(ActionEvent event) {

    	if(!this.radio25.isSelected()) 
    		this.radio25.setSelected(true);
    	if(this.radio50.isSelected())
    		this.radio50.setSelected(false);
    	if(this.radio75.isSelected())
    		this.radio75.setSelected(false);
    	
    	
    }

    @FXML
    void doRadio50(ActionEvent event) {

    	if(this.radio25.isSelected()) 
    		this.radio25.setSelected(false);
        if(!this.radio50.isSelected())
    		this.radio50.setSelected(true);
    	if(this.radio75.isSelected())
    		this.radio75.setSelected(false);
    	
    	
    }

    @FXML
    void doRadio75(ActionEvent event) {


    	if(this.radio25.isSelected()) 
    		this.radio25.setSelected(false);
    	if(this.radio50.isSelected())
    		this.radio50.setSelected(false);
    	if(!this.radio75.isSelected())
    		this.radio75.setSelected(true);
    	
    	
    }

    @FXML
    void doSimulate(ActionEvent event) {

    	if(this.Combo10.getValue()!=null) {
    		if(this.Combo8.getValue()!=null) {
    			if(this.Combo6.getValue()!=null) {
    				if(this.Combo4.getValue()!=null) {

    					if(radio25.isSelected())
    						model.init(Combo10.getValue(), Combo8.getValue(), Combo6.getValue(), Combo4.getValue(), PERC25);
    					else if(radio50.isSelected())
    						model.init(Combo10.getValue(), Combo8.getValue(), Combo6.getValue(), Combo4.getValue(), PERC50);
    					else if(radio75.isSelected())
    						model.init(Combo10.getValue(), Combo8.getValue(), Combo6.getValue(), Combo4.getValue(), PERC75);
    					
    					txtResult.setText(model.getStat());
    					
    				}
    				else
    				txtResult.setText("Selezionare il numero di tavoli da 4");
    				
    			}
    			else
    			txtResult.setText("Selezionare il numero di tavoli da 6");
    		}
    		else
    		txtResult.setText("Selezionare il numero di tavoli da 8");
    	}
    	else
    	txtResult.setText("Selezionare il numero di tavoli da 10");
    	
    	
    }

    @FXML
    void initialize() {
        assert Combo10 != null : "fx:id=\"Combo10\" was not injected: check your FXML file 'Bar.fxml'.";
        assert Combo8 != null : "fx:id=\"Combo8\" was not injected: check your FXML file 'Bar.fxml'.";
        assert Combo6 != null : "fx:id=\"Combo6\" was not injected: check your FXML file 'Bar.fxml'.";
        assert Combo4 != null : "fx:id=\"Combo4\" was not injected: check your FXML file 'Bar.fxml'.";
        assert radio25 != null : "fx:id=\"radio25\" was not injected: check your FXML file 'Bar.fxml'.";
        assert radio50 != null : "fx:id=\"radio50\" was not injected: check your FXML file 'Bar.fxml'.";
        assert radio75 != null : "fx:id=\"radio75\" was not injected: check your FXML file 'Bar.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";
        
        caricaBox();
        
    }

	private void caricaBox() {
	
		List<Integer> carica = new ArrayList<>();
		
		for(int i=1; i<=MAX_TAVOLI; i++) {
			carica.add(i);
		}

		this.Combo10.setItems(FXCollections.observableList(carica));
		this.Combo8.setItems(FXCollections.observableList(carica));
		this.Combo6.setItems(FXCollections.observableList(carica));
		this.Combo4.setItems(FXCollections.observableList(carica));
		
		
	}

	public void setModel(Model model) {
		this.model=model;
		
	}
}
