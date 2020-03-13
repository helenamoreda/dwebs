<?php
echo "DWEBS", " Versión actual de PHP: " . phpversion() . "<br/>";

// Extraer hora con date
print "Fecha con date: " . date("d/m/Y") . "<br/>";

// Extraer hora con localtime
$f = localtime(time(), true);
echo "Hora con localtime: " . $f["tm_hour"] . ":" . $f["tm_min"] . ":" . $f["tm_sec"] . "<br/>";
?>