<?php
$n1 = $_POST["n1"];
$n2 = $_POST["n2"];
$pares = 0;
$multiplos_3 = 0;
$multiplos_5 = 0;
$multiplos_11 = 0;
$suma = 0;
$contador = 0;

if (is_numeric($n1) && is_numeric($n2)) {
    if ($n1 < $n2) {
        if (isset($n1) && ! empty($n1) && isset($n2) && ! empty($n2)) {
            for ($i = $n1; $i <= $n2; $i ++) {
                echo $i;
                if ($i % 2 == 0) {
                    $pares ++;
                }
                if ($i % 3 == 0) {
                    $multiplos_3 ++;
                } else if ($i % 5 == 0) {
                    $multiplos_5 ++;
                } else if ($i % 11 == 0) {
                    $multiplos_11 ++;
                }
                $suma += $i;
                $contador ++;
            }
            echo 'Nº pares: ' . $pares;
            echo 'Nº multiplos 3: ' . $multiplos_3 . ' ';
            echo 'Nº multiplos 5: ' . $multiplos_5 . ' ';
            echo 'Nº multiplos 11: ' . $multiplos_11 . ' ';
            echo 'Suma secuencia: ' . $suma . ' ';
            echo 'Media secuencia: ' . $suma / $contador . ' ';
        }
    }
} else {
    echo 'El primer número debe ser menor que el segundo';
}

?>