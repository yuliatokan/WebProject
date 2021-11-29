$(document).ready(function(){
    $.get( "/get_client_info", function( data ) {
        if(data==''){
            $.getJSON( "/get_progress", function(calInfo) {
                var currentCalories = calInfo["currentCalories"];
                var currentProtein = calInfo["currentProtein"];
                var currentFats = calInfo["currentFats"];
                var currentCarbohydrates = calInfo["currentCarbohydrates"];

                $('#left').text(1800 - currentCalories);
                $('#max_cal').text(1800);
                $('#curr_cal').text(currentCalories);//curr_protein

                $('#sp_protein').text(currentProtein);
                $('#protein').attr("max", 350);
                $('#protein').attr("value", currentProtein);

                $('#sp_fats').text(currentFats);
                $('#fats').attr("max", 250);
                $('#fats').attr("value", currentFats);

                $('#sp_carbohydrates').text(currentCarbohydrates);
                $('#carbohydrates').attr("max", 300);
                $('#carbohydrates').attr("value", currentCarbohydrates);

                var curr_percentage = currentCalories*100/1800;
                $('.circle').attr("stroke-dasharray", curr_percentage+", 100");
            })
        }
        else {
            $.getJSON( "/get_progress", function(calInfo) {
                var currentCalories = calInfo["currentCalories"];
                var currentProtein = calInfo["currentProtein"];
                var currentFats = calInfo["currentFats"];
                var currentCarbohydrates = calInfo["currentCarbohydrates"];

                $('#left').text(data.calories - currentCalories);
                $('#max_cal').text(data.calories);
                $('#curr_cal').text(currentCalories);//curr_protein

                $('#sp_protein').text(currentProtein);
                $('#protein').attr("max", data.protein);
                $('#protein').attr("value", currentProtein);

                $('#sp_fats').text(currentFats);
                $('#fats').attr("max", data.fats);
                $('#fats').attr("value", currentFats);

                $('#sp_carbohydrates').text(currentCarbohydrates);
                $('#carbohydrates').attr("max", data.carbohydrates);
                $('#carbohydrates').attr("value", currentCarbohydrates);

                var curr_percentage = currentCalories*100/data.calories;
                $('.circle').attr("stroke-dasharray", curr_percentage+", 100");
            })
        }
    });
})