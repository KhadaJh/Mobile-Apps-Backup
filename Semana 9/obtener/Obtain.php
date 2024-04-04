<?php

require_once('Connect.php');
$id = $_GET["id"];
$query = "SELECT nombre FROM usuarios WHERE id='" . $id . "'";
$resultado = $mysqli->query($query);

if ($resultado->num_rows > 0) {

    $response = array();
    while ($row = $resultado->fetch_assoc()) {

        $response['nombre'] = $row['nombre'];
    }

    echo json_encode($response);
} else {
    echo "No hay registros";
}

$resultado->close();
$mysqli->close();
?>