package it.uniroma3.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma3.validatori.ProductValidator;
import it.uniroma3.model.Prodotto;
import it.uniroma3.service.ProductService;

import javax.servlet.http.HttpServlet;

@WebServlet("/prodotto")
public class ControllerProdotto extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage;
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzo"); // non abbiamo più un float, restituisce una stringa anche il prezzo
		String dataScadenza = request.getParameter("dataScadenza");
		
		//servono altri controlli: che quello che abbiamo scritto per il prezzo sia veramente un numero, ma il codice diventa lungo
		//lo organizziamo meglio facendo un metodo (o una classe: ValidatorProdotto) che fa dei controlli
		//su quello che arriva da input
		
		ProductValidator validator = new ProductValidator();
		if(validator.validate(request)) {  // se  i dati sono corretti crea un oggetto prodotto e chiede al servizio di inserire il prodotto
			Prodotto prodotto = new Prodotto();
			prodotto.setNome(nome);
			prodotto.setDescrizione(descrizione);
			prodotto.setPrezzo(Float.parseFloat(prezzo)); //vedi ProductValidator
			DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
			try {
				//per togliere questo try-catch deleghiamo anche la conversione a ProductValidator
				//e il risultato della conversione lo mettiamo nella request. FALLO TU!
				prodotto.setDataScadenza(dateFormat.parse(dataScadenza));
			}catch (ParseException e) {
				
			}
			
			ProductService service = new ProductService();
			service.inserisciProdotto(prodotto);
			
			nextPage = "/prodotti.jsp"; //prossima pag è quella che mi mostra i dati
		}else { 
			nextPage = "/index.jsp"; //altrimenti torna alla form
		}
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
	}
}
	
	

