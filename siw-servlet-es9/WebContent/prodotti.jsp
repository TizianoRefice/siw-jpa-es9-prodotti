<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Prodotti</title>
</head>
<body>
	Lista prodotti:
	<ul> 				<!-- questo era controllerDati -->
		<c:forEach var = "prodotto" items = "${prodotti}">
		<!-- stiamo componendo un URL con il link al prodotto -->
			<li> <a href ="prodotto?id=${prodotto.id }">${prodotto.nome }</a>
			<!-- mettiamo un bottone per cancellare un prodotto --> 
			<form action="prodotto" method = "post"> <!-- dobbiamo passare il parametro che indica il prodotto da cancellare -->
				<input type = "hidden" value = "${prodotto.id }" name = "id"/> <!-- con hidden teniamo nascosto il parametro dell'oggetto da rimuovere -->
				<input type = "submit" value = "rimuovi"/>
			</form>
			</li>
		</c:forEach>
	</ul>
</body>
