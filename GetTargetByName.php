<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //возвращает цель по ее имени в бд(ее имя, ее ветки, названия этапов в ее ветках и все айдишники) нужно для отображения структуры цели, возможных веток развития, шагов на каждой ветке
    $name = $_GET['targetName'];

    $result = mysqli_query($con,"SELECT branch.ID_Target, branch.ID, step.ID, step.Number, target.Name, step.Name FROM branch
        LEFT JOIN step ON branch.ID = step.ID_Branch
        LEFT JOIN target ON branch.ID_Target = target.ID
        WHERE target.Name = '". $name ."'
        ORDER BY step.ID_Branch, step.Number");

    $columnValues = Array();

    while ( $row = mysqli_fetch_array($result) ) {
        //echo html_entity_decode(json_encode($row, JSON_UNESCAPED_UNICODE))."\n";
        for($i = 0; $i < 6; $i++){
            $columnValues[$i][] = $row[$i];
        }
    }

    if($columnValues){
        echo html_entity_decode(json_encode($columnValues, JSON_UNESCAPED_UNICODE));
    }
    mysqli_close($con);
?>