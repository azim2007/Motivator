<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //возвращает выбранную ветку цели юзера и прогресс в этой ветке
    $usertargetId = $_GET['usertargetId'];

    //поиск ветки
    $result = mysqli_query($con,"SELECT target.ID, usertarget.ID_Branch, step.ID, usertarget.ID_CurrentStep, step.Name FROM usertarget
        LEFT JOIN branch ON usertarget.ID_Branch = branch.ID
        LEFT JOIN step ON branch.ID = step.ID_Branch
        LEFT JOIN target ON branch.ID_Target = target.ID
        WHERE usertarget.ID = ". $usertargetId ." AND usertarget.IsEnded = 0
        ORDER BY step.Number");

    $columnValues = Array(Array());

    while ( $row = mysqli_fetch_array($result) ) {
        for($i = 0; $i < 5; $i++){
            $columnValues[$i][] = $row[$i];
        }
    }

    if($columnValues){
        echo html_entity_decode(json_encode($columnValues, JSON_UNESCAPED_UNICODE));
    }
    mysqli_close($con);
    
?>