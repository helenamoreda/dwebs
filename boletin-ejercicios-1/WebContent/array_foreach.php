<?php 
$clave = array_keys($_GET);

for ($i = 0; $i < count($clave); $i++) {
    echo $clave[$i].":".$_GET[$clave[$i]];
}

foreach ($_GET as $k => $v) {
    echo $k.":".$v;
    
}


?>