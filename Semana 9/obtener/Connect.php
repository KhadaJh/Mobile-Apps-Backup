<?php

$mysqli=new mysqli("localhost","root","","android");    

if ($mysqli->connect_errno) {

   die("No se pudo conectar");
} else {

    //echo "Conexion exitosa";
}
?>