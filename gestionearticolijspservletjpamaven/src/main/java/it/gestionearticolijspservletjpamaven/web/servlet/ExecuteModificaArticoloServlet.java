package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;
import it.gestionearticolijspservletjpamaven.utility.Utility;


@WebServlet("/ExecuteModificaArticoloServlet")
public class ExecuteModificaArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    public ExecuteModificaArticoloServlet() {
        super();
     
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idArticoloDaModificare=request.getParameter("articoloId");
		String codiceInput = request.getParameter("codice");
		String descrizioneInput= request.getParameter("descrizione");
		String prezzoInput= request.getParameter("prezzo");
		String dataArrivoInput= request.getParameter("dataArrivo");
		
		Date dataArrivoParsed = Utility.parseDateArrivoFromString(dataArrivoInput);
		
		Long idArticoloConvertito=Long.parseLong(idArticoloDaModificare);
		int prezzoConvertito=Integer.parseInt(prezzoInput);
		
		try {
			Articolo articoloInstance=MyServiceFactory.getArticoloServiceInstance().caricaSingoloElemento(idArticoloConvertito);
			if (!Utility.validateInput(codiceInput, descrizioneInput, prezzoInput, dataArrivoInput)
					|| dataArrivoParsed == null) {
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.setAttribute("articoloAttributeModifica", articoloInstance);
				request.getRequestDispatcher("/articolo/update.jsp").forward(request, response);
				return;
			}
			articoloInstance.setCodice(codiceInput);
			articoloInstance.setDescrizione(descrizioneInput);
			articoloInstance.setPrezzo(prezzoConvertito);
			articoloInstance.setDataArrivo(dataArrivoParsed);
			
			MyServiceFactory.getArticoloServiceInstance().aggiorna(articoloInstance);
			
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
