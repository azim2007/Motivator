<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //возвращает имена текущих целей юзера
    $userId = $_GET['userId'];

    //поиск целей юзера
    $result = mysqli_query($con,"SELECT target.ID, usertarget.ID, target.Name FROM usertarget
        LEFT JOIN branch ON usertarget.ID_Branch = branch.ID
        LEFT JOIN target ON branch.ID_Target = target.ID
        WHERE usertarget.ID_User = ". $userId ." AND usertarget.IsEnded = 0");

    $columnValues = Array(Array());

    while ( $row = mysqli_fetch_array($result) ) {
        for($i = 0; $i < 2; $i++){
            $columnValues[$i][] = $row[$i];
        }
    }

    if($columnValues){
        echo html_entity_decode(json_encode($columnValues, JSON_UNESCAPED_UNICODE));
    }
    mysqli_close($con);
    
?>