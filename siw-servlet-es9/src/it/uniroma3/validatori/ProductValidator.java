package it.uniroma3.validatori;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

public class ProductValidator {

	public boolean validate(HttpServletRequest request) {
		boolean tuttoOK = true;
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzo");
		String dataScadenza = request.getParameter("dataScadenza");
		
		//vincoli: sono tutti campi obbligatori (nella form c'è *)
		if(nome == null || nome.equals("")) {
			request.setAttribute("errNome", "Campo Obbligatorio!");
			tuttoOK = false;
		}
		if(descrizione == null || descrizione.equals("")) {
			request.setAttribute("errDescirizione", "Campo Obbligatorio!");
			tuttoOK = false;
		}
		if(prezzo == null || prezzo.equals("")) {
			request.setAttribute("errPrezzo", "Campo Obbligatorio!");
			tuttoOK = false;
		}
		if(dataScadenza == null || dataScadenza.equals("")) {
			request.setAttribute("errDataScadenza", "Campo Obbligatorio!");
			tuttoOK = false;
		}
		//verifichiamo che il float sia un float
		try {
			Float.parseFloat(prezzo); // ritorna il prezzo (dalla stringa ritorna un float, se non è un float lancia un'eccezione)
		}
		catch(NumberFormatException exc) {
			request.setAttribute("errPrezzo", "Prezzo non valido: deve essere un numero!");
			tuttoOK = false;
		}
		//verifichiamo che la data sia una data
		try {
			//il metodo di DateFormat che ci serve non è static, devi definire un oggetto DateFormat
			DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
			dateFormat.parse(dataScadenza);
		}
		catch (ParseException exc) {
			request.setAttribute("errDataScadenza", "Formato data non valido!");
			tuttoOK = false;
		}
		return tuttoOK;
	}
	
}
