<?php
$f = localtime(time(), true);
$hora = $f["tm_hour"];
if (($hora >= 6) && ($hora <= 12)) {
    echo "Son las " . $hora . " Buenos dias";
} elseif (($hora >= 13) && ($hora <= 20)) {
    echo "Son las " . $hora . " Buenas tardes";
} elseif (($hora >= 21) && ($hora <= 5)) {
    echo "Son las " . $hora . " Buenas noches";
}
?>