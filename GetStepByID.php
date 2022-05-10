<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //возвращает шаг на пути к цели (его имя, подробное описание, а так же все причитающиеся ссылки и все там нужные айдишники), нужно для отображения шага в соответствующей этому активити
    $stepId = $_GET['stepId'];

    $result = mysqli_query($con,"SELECT step.ID_Branch, step.ID, step.Number, reference.ID, step.Name, step.Description, reference.Reference FROM step
        LEFT JOIN reference ON Reference.ID_Step = step.ID
        WHERE step.ID = ". $stepId ."
        ORDER BY reference.ID");

    $columnValues = Array(Array());

    while ( $row = mysqli_fetch_array($result) ) {
        for($i = 0; $i < 7; $i++){
            $columnValues[$i][] = $row[$i];
        }
    }

    if($columnValues){
        echo html_entity_decode(json_encode($columnValues, JSON_UNESCAPED_UNICODE));
    }
    mysqli_close($con);
?>