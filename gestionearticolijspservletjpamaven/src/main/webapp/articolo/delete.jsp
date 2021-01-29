<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="it.gestionearticolijspservletjpamaven.model.Articolo"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../header.jsp" />
	<title>Elimina Articolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Conferma Eliminazione
		    </div>
		    <% Articolo articoloDaEliminare = (Articolo)request.getAttribute("articoloAttributeElimina"); %>
		
		    <div class='card-body'>
		    <form method="post" action="ExecuteEliminaArticoloServlet" novalidate="novalidate">
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Codice</dt>
				  <dd class="col-sm-9"><%=articoloDaEliminare.getCodice() %></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione:</dt>
				  <dd class="col-sm-9"><%=articoloDaEliminare.getDescrizione() %></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Prezzo:</dt>
				  <dd class="col-sm-9"><%=articoloDaEliminare.getPrezzo() %></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data di Arrivo:</dt>
				  <dd class="col-sm-9"><%=articoloDaEliminare.getDataArrivo()!=null? new SimpleDateFormat("dd/MM/yyyy").format(articoloDaEliminare.getDataArrivo()):"N.D."  %></dd>
		    	</dl>
		    	
		    
		    <input type="hidden" name="articoloId" value="<%=articoloDaEliminare.getId()%>">
		    
		 <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Elimina</button>
		
		</form>
		
		</div>	
	
	</div>
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>