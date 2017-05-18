<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prodotto</title>
</head>
<body>
	<h1>Nome: ${prodotto.nome}</h1>
	<div>Descrizione: ${prodotto.descrizione}</div>
	<div>Prezzo: ${prodotto.prezzo}</div>
	<div>Data Scadenza: ${prodotto.dataScadenza}</div>
</body>
</html>

<!-- prodotti diventa quella dell'altra volta con il forEach -->