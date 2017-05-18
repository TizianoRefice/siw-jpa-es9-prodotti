<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prodotti</title>
</head>
<body>
	<h1>Prodotto:</h1>
	<p>Nome: ${prodotto.nome}
	<p>Descrizione: ${prodotto.descrizione}
	<p>Prezzo: ${prodotto.prezzo}
	<p>Data Scadenza: ${prodotto.dataScadenza}
</body>
</html>