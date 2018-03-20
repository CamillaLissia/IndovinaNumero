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
	
	private Model model;
	

    public void setModel(Model model) { //riferimento a un modello che qualcun altro ha creato
		this.model = model;
	}

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
    	
    	model.newGame();
    	
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	txtCur.setText(String.format("%d", model.getTentativi()));
    	txtMax.setText(String.format("%d", model.getTMAX()));
    	txtLog.clear();
    	txtTentativo.clear();
    	txtLog.setText(String.format("indovina numero tra %d e %d\n", 1, model.getNMAX()));
    	
    	

    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS= txtTentativo.getText();		// continua a essere responsabilità del CONTROLLER
    												// cioè tutto ciò che è acquisizione/validazione dei dati
    	if (numS.length()==0) {
    		txtLog.appendText("inserisci!");
    		return;
    	}
    	try {
    	int num=Integer.parseInt(numS);			
    	// il numero è effettivamente un intero
    	
    	if(!model.valoreValido(num)) {
    		txtLog.appendText("valore fuori range\n");
    		return;
    	}
    	
    	int risultato= model.tentativo(num);
    	if(risultato==0)
    		txtLog.appendText("hai vinto\n");
    	else if(risultato<0)
    		txtLog.appendText("troppo basso\n");
    	else
    		txtLog.appendText("troppo alto\n");
    	
    	if(!model.isInGame()) {
    		// partita finita
    		if(risultato!=0) {
    			txtLog.appendText("HAI PERSO");
    			txtLog.appendText(String.format("il numero segreto era: %d\n", model.getSegreto()));
    		}
    		// chiudi la partita
    		boxGioco.setDisable(true);
    		btnNuova.setDisable(false);
    	}
    		
    	
    	/* 
    	 *  //LE COSE SEGUENTI NON SERVONO PIU', SONO GIA' NEL MODEL!
    	 * 	if(num==segreto) {
    		txtLog.appendText("hai vinto\n");
    
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
    	*/
    	}
    	 catch (NumberFormatException ex) {
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
