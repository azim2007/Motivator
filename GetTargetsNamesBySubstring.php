<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //���������� ����� ���� ����� � �� ����, � ����� ������� ���������� $_GET['substringOfName'] (������ 7), ����� ��� ���������� ������ ����� ������ � ����� ������ ��������, ��������� ����� �������� �� ������, � ����� ��� ������� �� ��� �������� GetTargetByID ��� GetTargetByName
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