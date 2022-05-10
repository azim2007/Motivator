<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //возвращает имена всех целей и их айди, в имени которых содержится $_GET['substringOfName'] (строка 7), нужно для реализации поиска целей ЮЗЕРОМ в какой нибудь активити, результат можно повесить на кнопки, а потом при нажатии на них вызывать GetTargetByID или GetTargetByName
    $substring = $_GET['substringOfName'];

    $result = mysqli_query($con,"SELECT target.ID, target.Name FROM target
        WHERE target.Name LIKE '%". $substring ."%'
        ORDER BY target.Name");

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