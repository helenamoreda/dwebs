<?php
$manana = getdate(strtotime("tomorrow"));
$hoy = getdate();
echo "Desde las " . $hoy['hours'] . ":" . $hoy['minutes'] . " hasta la medianoche faltan " . ($manana[0] - $hoy[0]) . " segundos."
?>
