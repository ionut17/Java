<html>
<head>
  <title>consoleMusic - Favorites list</title>
</head>
<body>
  <h1>${user} - Favorites</h1>
  <p>Favorites list</p>
  <ul>
	<#list songs as song>
		<li>${song}</li>
	</#list>
  </ul>
</body>
</html>