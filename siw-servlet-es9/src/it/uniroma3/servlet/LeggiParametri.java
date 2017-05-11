package it.uniroma3.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma3.model.Prodotto;

@WebServlet("/controllerDati")
public class LeggiParametri extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Prodotto> prodotti = new ArrayList<>();
		
		Prodotto prodotto = new Prodotto();
		prodotto.setNome("penna");
		prodotto.setPrezzo(3F);
		prodotti.add(prodotto);
		
		Prodotto prodotto2 = new Prodotto();
		prodotto2.setNome("quadro");
		prodotto2.setPrezzo(550F);
		prodotti.add(prodotto2);
		
		
		request.setAttribute("prodotti", prodotti);
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/prodotti.jsp");
		rd.forward(request, response);
		return;
	}

}
