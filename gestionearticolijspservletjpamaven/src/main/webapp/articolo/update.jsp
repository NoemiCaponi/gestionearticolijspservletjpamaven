<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="it.gestionearticolijspservletjpamaven.model.Articolo"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp" />
<title>Aggiorna Dati</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Aggiorna articolo</h5>
			</div>

			<div class='card-body'>
			
			<% Articolo articoloSelezionato=(Articolo)request.getAttribute("articoloAttributeModifica"); %>
			<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
			
			<form method="post" action="ExecuteModificaArticoloServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Codice <span class="text-danger">*</span></label>
								<input type="text" name="codice" id="codice" class="form-control" value="<%=articoloSelezionato.getCodice() %>" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Descrizione <span class="text-danger">*</span></label>
								<input type="text" name="descrizione" id="descrizione" class="form-control" value="<%=articoloSelezionato.getDescrizione() %>" required>
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>Prezzo <span class="text-danger">*</span></label>
								<input type="number" class="form-control" name="prezzo" id="prezzo" value="<%=articoloSelezionato.getPrezzo() %>" required>
							</div>
							<div class="form-group col-md-3">
								<label>Data di Arrivo<span class="text-danger">*</span></label>
                        		<input class="form-control" id="dataArrivo" type="date" value="<%=articoloSelezionato.getDataArrivo() %>"
                            		title="formato : gg/mm/aaaa"  name="dataArrivo" required>
							</div>
							<input type="hidden" name="articoloId" value="<%=articoloSelezionato.getId()%>">
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Modifica</button>
									
			

					</form>

			</div>
		</div>
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>