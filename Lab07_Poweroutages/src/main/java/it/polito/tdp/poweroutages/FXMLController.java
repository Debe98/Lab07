 package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Insieme;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private ComboBox<Nerc> cbNerc;

    @FXML
    private Button btnWCA;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRicerca(ActionEvent event) {
    	String rawYears = txtYears.getText();
    	String rawHours = txtHours.getText();
    	int anni;
    	int ore;
    	try {
    		anni = Integer.parseInt(rawYears);
    		ore = Integer.parseInt(rawHours);
    	} catch (Exception e) {
			txtResult.setText("Errore nell'inserimento dei dati");
			return;
		}
    	
    	Insieme risultato = model.getWorstPowerOutagesByNerc(cbNerc.getValue(), anni, ore);
    	if (risultato == null) {
    		txtResult.setText("Il Nerc selezionato non ha avuto Blackout!");
    		return;
    	}
    	
    	txtResult.setText("Trovato il valore richiesto: "+risultato.getTotPersoneCoinvolte()+
    	", in "+risultato.getTotOreDisservizio()+" ore di disservizio\n");
    	for (PowerOutage po : risultato.getPowerOutages()) {
    		txtResult.appendText(po.toString()+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cbNerc != null : "fx:id=\"cbNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnWCA != null : "fx:id=\"btnWCA\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    Model model;
	public void setModello(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
		cbNerc.getItems().setAll(model.getNercList());
		cbNerc.setValue(model.getNercList().get(0));
	}
}
