<!DOCTYPE html>
<html>
<body>
	<div>
<?php
$escalones = $_POST['alturaIntroducida'];
$figura = $_POST['figura'];
$elementos = 1;

if (isset($figura)) {
    switch ($figura) {
        case "bolita":
            $figura = "&#9917;";
            break;
        case "ladrillo":
            $figura = "&#11197;";
            break;
        case "smiley":
            $figura = "&#9786;";
            break;
        case "sol":
            $figura = "&#9728;";
            break;
        case "luna":
            $figura = "&#9790;";
            break;
        default:
            break;
    }
    echo '';
    for ($i = 1; $i <= $escalones; $i ++) {
        for ($i = 0; $i < $elementos; $i ++) {
            echo $figura;
        }
        $elementos ++;
        echo '<br>';
    }
}
?>
</div>
</body>
</html>