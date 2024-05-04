var questions = []
var index = 0
var result = []
var currentQuestion =undefined
$(".nextBtn").on("click", function(){
    var options = document.querySelectorAll('#options input[type="radio"]');
    var selectedValue = null;

    // Loop through each radio button
    for (var i = 0; i < options.length; i++) {
        // Check if the radio button is checked
        if (options[i].checked) {
            // Get the value associated with the checked radio button
            selectedValue = $("#option"+(i+1)).text();
            options[i].checked = false
            break; // Exit the loop once a checked radio button is found
        }
    }
    if(index<=questions.length-1){
        result.push({
            id:currentQuestion.question,
            rightAnswer: selectedValue
        })
    }
    if(index==questions.length) {
        submitResult(result)
    }
    index++;
    currentQuestion = questions[index]

    if(index<=questions.length-1){
        doOnNext(currentQuestion)
    }
})
document.addEventListener("DOMContentLoaded", function() {
    fetchQuizQuestions()
});

function fetchQuizQuestions() {
    $.ajax({
        url:  window.origin+"/quiz/list/"+$("#quizId").val(),
        type: "GET",
        dataType: "json", // Assuming the controller returns JSON data
        success: function(data) {
            questions =data
            currentQuestion = questions[index]
            doOnNext(currentQuestion)
        }
    });
}
function submitResult(data) {
    console.log(data)
}
function doOnNext(currentQuestion) {
    $("#currentQuestion").text(currentQuestion.question)
    $("#option1").text(currentQuestion.option1)
    $("#option2").text(currentQuestion.option2)
    $("#option3").text(currentQuestion.option3)
    $("#option4").text(currentQuestion.option4)
    if(index==questions.length-1) {
        $(".nextBtn").text("submit")
    }
}

function submitResult(data) {
    $.ajax({
        url:  window.origin+"/quiz/submit/"+$("#quizId").val(),
        type: "POST",
        data: data,
        dataType: "json", // Assuming the controller returns JSON data
        success: function(data) {
            debugger
            console.log(data)
        }
    });
}