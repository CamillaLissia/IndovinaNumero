package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	
	private int NMAX=100;
	private int TMAX=7;
	
	private int segreto; // num da indovinare
	private int tentativi; // tentativi gia fatti
	private boolean inGame= false; //mi dice se c'è una partita in corso
	
	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtCur"
    private TextField txtCur; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxGioco"
    private HBox boxGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNuova(ActionEvent event) {
    	
    	this.segreto=(int)(Math.random()*NMAX)+1;
    	this.tentativi=0;
    	this.inGame=true;
    	
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	txtCur.setText(String.format("%d", this.tentativi));
    	txtMax.setText(String.format("%d", this.TMAX));
    	txtLog.clear();
    	txtTentativo.clear();
    	txtLog.setText(String.format("indovina numero tra %d e %d\n", 1, NMAX));
    	
    	

    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS= txtTentativo.getText();
    	if (numS.length()==0) {
    		txtLog.appendText("inserisci!");
    		return;
    	}
    	try {
    	int num=Integer.parseInt(numS);
    	// il numero è effettivamente un intero
    	
    	if(num<1 || num > NMAX) {
    		txtLog.appendText("valore fuori range\n");
    		return;
    	}
    	
    	if(num==segreto) {
    		txtLog.appendText("hai vinto\n");
    		// chiudi la partita
    		boxGioco.setDisable(true);
    		btnNuova.setDisable(false);
    		inGame=false;
    		
    	}else { //tentativo errato-> troppo basso/troppo alto/ultimo
    		tentativi++;
    		txtCur.setText(String.format("%d", this.tentativi));
    		
    		if(tentativi==TMAX) { // hai perso
    			txtLog.appendText(String.format("hai perso: era:%d\n", segreto));
    			// chiudi la partita
        		boxGioco.setDisable(true);
        		btnNuova.setDisable(false);
        		inGame=false;
    		}
    		else { //ancora in gioco
    			if(num<segreto) { //troppo basso
    				txtLog.appendText("troppo basso\n");
    			}
    			else { //troppo alto
    				txtLog.appendText("troppo alto\n");
    			}
    		}
    	}
    	
    	} catch (NumberFormatException ex) {
    		txtLog.appendText("non va bene, non è un numero!!\n");
    		return;
    	}
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtCur != null : "fx:id=\"txtCur\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

    }
}
