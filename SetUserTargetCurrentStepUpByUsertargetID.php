<?php
    header('Content-Type: application/json;charset=utf-8');  
    $con = mysqli_connect("motivator2.ru","root","");
    $database = mysqli_select_db($con, "motivatordatabase");

    //������ ������� ��� ������������ �� ���� 1, �������� ��� �������� ������, ������ ���, ������ ��� ������������, ���� ������ ���������� true, � ������, ���� ���������� ���� ���(���� �������� ����) ���������� false
    $usertargetId = $_GET['usertargetId'];

    //����� ���� �������� ���� (������� �� ��������), ���� ������� ��������� ��� ������� ����� �����
    $result = mysqli_query($con,"SELECT usertarget.ID_CurrentStep FROM usertarget
        WHERE usertarget.ID = ". $usertargetId ."");

    $row = mysqli_fetch_array($result);
    $stepId = $row[0];

    //������ ���� �����
    $result = mysqli_query($con,"SELECT usertarget.ID_Branch FROM usertarget
        WHERE usertarget.ID = ". $usertargetId ."");

    $row = mysqli_fetch_array($result);
    $branchId = $row[0];

    //������ ���������� ����� ����������� ����
    $result = mysqli_query($con,"SELECT step.Number FROM step
        WHERE step.ID = ". $stepId ."");

    $row = mysqli_fetch_array($result);
    $stepNumber = $row[0];
    $stepNumber = $stepNumber + 1;

    //������ ���� ������ ���� �� ��� ������
    $result = mysqli_query($con,"SELECT step.ID FROM step
        WHERE step.Number = ". $stepNumber ." AND step.ID_Branch = ". $branchId ."");

    $row = mysqli_fetch_array($result);
    //�������� �� ��, ���� �� ��������� ���, ���� ���, �� ���� �������� ����
    if($row != null){
        $stepId1 = $row[0];
        //������ ���� ������ ���� � ����
        $result = mysqli_query($con,"UPDATE usertarget SET ID_CurrentStep = ". $stepId1 ." 
            WHERE usertarget.ID = ". $usertargetId ."");

        if($result){
            echo 'true';
        }
    }
    else{
        //������ ���� �����
        $result = mysqli_query($con,"SELECT usertarget.ID_User FROM usertarget
            WHERE usertarget.ID = ". $usertargetId ."");

        $row = mysqli_fetch_array($result);
        $userId = $row[0];
        $result = mysqli_query($con,"UPDATE usertarget SET IsEnded = 1 
            WHERE usertarget.ID_User = ". $userId ." AND usertarget.ID_Branch = ". $branchId ."");
        echo 'false';
    }

    
?>