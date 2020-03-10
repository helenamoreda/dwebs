<?php
$cont = 0;

while ($cont < 50) {
    $num = rand(0, 100);
    if($num%2 == 0 && $num%11 == 0){
        echo $num.", ";
        $cont++;
    }
}
?>