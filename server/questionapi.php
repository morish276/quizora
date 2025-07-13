<?php

// Create a connection to the MySQL database
$conn = mysqli_connect("localhost","YOUR_DB_USER","YOUR_PASSWORD","YOUR_DB");

// Prepare SQL statement to select quiz questions and options
$stmt = $conn->prepare("
    SELECT 
        `question`, 
        `option1`, 
        `option2`, 
        `option3`, 
        `option4`, 
        `correct_option` 
    FROM `math_quiz`
");

// Execute the prepared SQL statement
$stmt->execute();

// Bind the columns in result set to PHP variables
$stmt->bind_result($question, $option1, $option2, $option3, $option4, $correct_option);

// Create an empty array to hold all questions
$questions_array = array();

// Loop through all results and format each row into an associative array
while ($stmt->fetch()) {
    $temp = array();
    $temp['question'] = $question;
    $temp['option1'] = $option1;
    $temp['option2'] = $option2;
    $temp['option3'] = $option3;
    $temp['option4'] = $option4;
    $temp['correct_option'] = $correct_option;

    // Add each question to the main array
    array_push($questions_array, $temp);
}

// Convert the array to JSON and send it as the response
echo json_encode($questions_array);

?>
