<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title>Home Page</title>
</head>
<body>

<h1>New Product Form</h1>
<p>Enter product data</p>

<form action="./search" method="post">
    <label for="seed-id">Seed:</label><br>
    <input type="text" id="seed-id" name="seed" value="https://en.wikipedia.org/wiki/Elon_Musk"  size="100"><br>

    <label for="terms-id">Termname:</label><br>
     <input type="text" id="terms-id" name="term" value="Tesla, Musk, Gigafactory, Elon Mask" size="100"><br>



    <br>
    <input type="submit" value="S E A R C H">
</form>

</body>
</html>