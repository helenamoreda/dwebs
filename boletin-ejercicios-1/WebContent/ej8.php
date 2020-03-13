<!doctype html>
<html>
<head>
<meta charset="utf-8" />
</head>
<body>
      <?php
    $n1 = $_POST["n1"];
    $n2 = $_POST["n2"];
    if (isset($n1) && isset($n2)) {
        if ($n1 < $n2) {
            
            define("N", rand($n1, $n2));
            
            $a = array(); // $a[];
            
            for ($i = 0; $i < N; $i ++)
                $a[$i] = rand(0, N - 1) + 1;
            
            if (defined("N") && is_array($a)) {
                echo "<span>ARRAY CON " . N . " VALORES:</span>";
                echo "<table>";
                
                foreach ($a as $elem) {
                    echo "<tr><td>" . $elem . "</td></tr>";
                }
                
                echo "</table>";
                
                var_dump($a);
            }
        } else {
            echo '<p>El primer numero debe ser menor que el segundo</p>';
        }
        echo '<p>No hay valores</p>';
    }
    
    ?>
   </body>
</html>
