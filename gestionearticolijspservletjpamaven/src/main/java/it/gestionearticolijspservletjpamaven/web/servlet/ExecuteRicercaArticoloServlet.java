package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;
import it.gestionearticolijspservletjpamaven.utility.Utility;


@WebServlet("/ExecuteRicercaArticoloServlet")
public class ExecuteRicercaArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String dataArrivoStringParam = request.getParameter("dataArrivo");
		Integer prezzoInputParsed=Utility.parsePrezzoFromString(prezzoInputStringParam);
		Date dataArrivoParsed = Utility.parseDateArrivoFromString(dataArrivoStringParam);

		
		
		try {
			Articolo input=new Articolo(codiceInputParam, descrizioneInputParam, prezzoInputParsed, dataArrivoParsed);
		
							
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().findByExample(input));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);
	}

}
