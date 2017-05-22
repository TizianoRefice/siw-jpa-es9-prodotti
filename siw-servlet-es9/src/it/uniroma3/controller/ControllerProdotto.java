package it.uniroma3.controller;

import java.io.IOException;
import java.util.List;

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
	
	//abbiamo cambiato doGet in doPost perchè i dati delle form è meglio tenerli nascosti
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage;
		Prodotto prodotto = new Prodotto();
		request.setAttribute("prodotto", prodotto);
		
		//servono altri controlli: che quello che abbiamo scritto per il prezzo sia veramente un numero, ma il codice diventa lungo
		//lo organizziamo meglio facendo un metodo (o una classe: ValidatorProdotto) che fa dei controlli
		//su quello che arriva da input
		
		ProductValidator validator = new ProductValidator();
		if(validator.validate(request)) {  // se  i dati sono corretti crea un oggetto prodotto e chiede al servizio di inserire il prodotto
			ProductService service = new ProductService();
			service.inserisciProdotto(prodotto);
			nextPage = "/prodotto.jsp"; //prossima pag è quella che mi mostra i dati
		}else { 
			nextPage = "/index.jsp"; //altrimenti torna alla form
		}
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage = "/prodotti.jsp";
		
		ProductService service = new ProductService();
		
		//se ho l'id stampo questo, sennò stampo tutto
		if(request.getParameter("id") != null) {
			Long id = Long.parseLong(request.getParameter("id"));
			Prodotto prodotto = service.getOneProduct(id);
			request.setAttribute("prodotto", prodotto);
			nextPage = "/prodotto.jsp";
		} else {
			List<Prodotto> prodotti = service.getProdotti();
			request.setAttribute("prodotti", prodotti);
		}
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
	}
}
	
	

