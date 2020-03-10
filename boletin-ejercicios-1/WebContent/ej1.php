<?php
$result = 0;
$cont = 0;
$num = 0;
while ($cont < 100) {
    if ($num % 2 == 0) {
        $result += $num;
        $cont ++;
    }
    $num ++;
}

echo "La suma es " . $result . " Y la media es " . $result / $cont;
?>