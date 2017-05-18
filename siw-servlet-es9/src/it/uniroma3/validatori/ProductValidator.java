package it.uniroma3.validatori;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import it.uniroma3.model.Prodotto;

public class ProductValidator {

	public boolean validate(HttpServletRequest request) {
		
		boolean tuttoOK = true;
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzo");
		String dataScadenza = request.getParameter("dataScadenza");
		Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
		
		//vincoli: sono tutti campi obbligatori (nella form c'è *)
		if(nome == null || nome.equals("")) {
			request.setAttribute("errNome", "Campo Obbligatorio!");
			tuttoOK = false;
		} else {
			prodotto.setNome(nome);
		}
		if(descrizione == null || descrizione.equals("")) {
			request.setAttribute("errDescirizione", "Campo Obbligatorio!");
			tuttoOK = false;
		} else {
			prodotto.setDescrizione(descrizione);
		}
		//PREZZO --> verifichiamo che sia veramente un float
		if(prezzo == null || prezzo.equals("")) {
			request.setAttribute("errPrezzo", "Campo Obbligatorio!");
			tuttoOK = false;
		} else {
			try{
				//request.setAttribute("prezzo", Float.parseFloat(prezzo)); // al posto di creare il prodotto sopra
				//ritorna il prezzo (dalla stringa ritorna un float;
				//se non è un float lancia un'eccezione
				prodotto.setPrezzo(Float.parseFloat(prezzo));
			} catch (NumberFormatException exc) {
				request.setAttribute("errPrezzo", "Prezzo non valido! Deve essere un numero!");
				tuttoOK = false;
			}
		}
		//DATA SCADENZA --> verifichiamo che sia una data
		if(dataScadenza == null || dataScadenza.equals("")) {
			request.setAttribute("errDataScadenza", "Campo Obbligatorio!");
			tuttoOK = false;
		} else {
			try{
				//il metodo che ci serve in DateFormat non è static, quindi definiamo un oggetto DateFormat
				DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
				prodotto.setDataScadenza(dateFormat.parse(dataScadenza));
			} catch (ParseException exc) {
				request.setAttribute("errDataScadenza", "Formato non valido!");
				tuttoOK = false;
			}
		}
		return tuttoOK;
	}
	
}
