package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;



@WebServlet("/ExecuteEliminaArticoloServlet")
public class ExecuteEliminaArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteEliminaArticoloServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idArticoloDaEliminare=request.getParameter("articoloId");
		Long idArticoloConvertito=Long.parseLong(idArticoloDaEliminare);
		
		try {
			Articolo articoloInstance=MyServiceFactory.getArticoloServiceInstance().caricaSingoloElemento(idArticoloConvertito);
			MyServiceFactory.getArticoloServiceInstance().rimuovi(articoloInstance);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/articolo/result.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);
	}

}
