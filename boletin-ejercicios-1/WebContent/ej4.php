<?php
$mb = $_POST["mb"];
if (isset($mb) && !empty($mb) && is_numeric($mb)) {
    echo $mb*1024;
}
?>