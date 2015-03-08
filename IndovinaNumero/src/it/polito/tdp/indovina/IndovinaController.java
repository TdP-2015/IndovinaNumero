package it.polito.tdp.indovina ;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IndovinaController {

	private int secret ;
	private int trials ;
	private boolean inGame ;
	private final int MAX_VALUE = 100 ;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGuess;

    @FXML
    private TextField txtGuess;

    @FXML
    private Button btnStart;

    @FXML
    private TextField txtTrials;
    
    @FXML
    private Label lblStatus;

    @FXML
    void doStart(ActionEvent event) {
    	
    	if(!inGame) {
    		this.trials = 0 ;
    		this.secret = (int)(Math.random() * this.MAX_VALUE) ;
    		gameOn() ;
    		inGame = true ;
    		
    		System.out.println("Secret = "+this.secret) ;
    	}

    }

    @FXML
    void doGuess(ActionEvent event) {
    	
    	if(inGame) {
    		
    		int guess ;
    		try{
    			guess = Integer.parseInt(txtGuess.getText()) ;
    		} catch(NumberFormatException e) {
    			lblStatus.setText("Devi inserire un numero") ;
    			return ;
    			// decidiamo di non contarlo come tentativo
    		}
    		
    		
    		if (guess == secret) {
    			// you won!!!
    			lblStatus.setText("Hai vinto!!") ;
    			gameOver() ;
    			inGame = false ;
    			
    		} else {
    			this.trials++ ;
    			
    			if(guess<this.secret)
    				lblStatus.setText("Troppo basso") ;
    			else
    				lblStatus.setText("Troppo alto") ;
    			txtTrials.setText(Integer.toString(trials));
    		}
    	}

    }

    private void gameOn()  {
    	btnGuess.setDisable(false);
    	btnStart.setDisable(true);
    	txtGuess.setEditable(true);
    }
    
    private void gameOver() {
    	btnGuess.setDisable(true);
    	btnStart.setDisable(false);
    	txtGuess.setEditable(false);
    }

    @FXML
    void initialize() {
        assert btnGuess != null : "fx:id=\"btnGuess\" was not injected: check your FXML file 'Indovina.fxml'.";
        assert txtGuess != null : "fx:id=\"txtGuess\" was not injected: check your FXML file 'Indovina.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'Indovina.fxml'.";
        assert txtTrials != null : "fx:id=\"txtTrials\" was not injected: check your FXML file 'Indovina.fxml'.";

        inGame = false ;
        gameOver() ;
        
    }
}
