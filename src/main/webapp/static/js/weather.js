var unitIsCelcius = true;
var globalForecast = [];

// Maps the API's icons to the ones from https://erikflowers.github.io/weather-icons/
var weatherIconsMap = {
    "01d": "wi-day-sunny",
    "01n": "wi-night-clear",
    "02d": "wi-day-cloudy",
    "02n": "wi-night-cloudy",
    "03d": "wi-cloud",
    "03n": "wi-cloud",
    "04d": "wi-cloudy",
    "04n": "wi-cloudy",
    "09d": "wi-showers",
    "09n": "wi-showers",
    "10d": "wi-day-hail",
    "10n": "wi-night-hail",
    "11d": "wi-thunderstorm",
    "11n": "wi-thunderstorm",
    "13d": "wi-snow",
    "13n": "wi-snow",
    "50d": "wi-fog",
    "50n": "wi-fog"
};
var sportIconsDayMap = ["fa-biking", "fa-dumbbell", "fa-running", "fa-volleyball-ball", "fa-table-tennis", "fa-futbol"];


$(function(){
    getClientPosition();
    startClock();
});


function startClock(){
    setInterval(function(){
        $("#localTime").text(new Date().toLocaleTimeString());
    }, 1000);
}


function getClientPosition(){
    $.getJSON("https://ipapi.co/json/", function(position) {
        $("#cityName").text(position.city);
        $("#cityCode").text(position.country);

        getWeatherData(position.latitude, position.longitude);
    });
}


function getWeatherData(latitude, longitude){
    $.ajax({
        type: "GET",
        url: "https://cors-anywhere.herokuapp.com/http://api.openweathermap.org/data/2.5/forecast/daily?APPID=9b4bbf30228eb8528d36e79d05da1fac&lat=" + latitude + "&lon=" + longitude + "&units=metric&cnt=5",
        cache: true,
        headers: {
            "Access-Control-Allow-Headers": "x-requested-with"
        },
        success: function(forecast){
            globalForecast = forecast;
            updateForecast(forecast);

            // Stops Refresh button's spinning animation
            $("#refreshButton").html("<i class='fa fa-refresh fa-fw'></i> Refresh");
        },
        error: function(error){
            console.log("Error with ajax: "+ error);
        }
    });
}


// Update view values from passed forecast
function updateForecast(forecast){

    // Present day
    var today = forecast.list[0];
    $("#tempDescription").text(toCamelCase(today.weather[0].description));
    $("#humidity").text(today.humidity);
    $("#wind").text(today.speed);
    $("#localDate").text(getFormattedDate(today.dt));
    $("#main-icon").addClass(weatherIconsMap[today.weather[0].icon]);
    $("#mainTemperature").text(Math.round(today.temp.day));
    $("#mainTempHot").text(Math.round(today.temp.max));
    $("#mainTempLow").text(Math.round(today.temp.min));

    $("#main1").addClass(sportIconsMainFirstMap[today.weather[0].icon]);
    $("#main2").addClass(sportIconsMainSecondMap[today.weather[0].icon]);
    $("#main3").addClass(sportIconsMainThirdMap[today.weather[0].icon]);
    $("#1day").addClass(sportIconsDayMap[Math.floor(Math.random() * sportIconsDayMap.length)]);
    $("#2day").addClass(sportIconsDayMap[Math.floor(Math.random() * sportIconsDayMap.length)]);
    $("#3day").addClass(sportIconsDayMap[Math.floor(Math.random() * sportIconsDayMap.length)]);
    $("#4day").addClass(sportIconsDayMap[Math.floor(Math.random() * sportIconsDayMap.length)]);


    // Following days data
    for(var i = 1; i < (forecast.list).length; i++){
        var day = forecast.list[i];

        // Day short format e.g. Mon
        var dayName = getFormattedDate(day.dt).substring(0,3);

        // weather icon from map
        var weatherIcon = weatherIconsMap[day.weather[0].icon];

        $("#forecast-day-" + i + "-name").text(dayName);
        $("#forecast-day-" + i + "-icon").addClass(weatherIcon);
        $("#forecast-day-" + i + "-main").text(Math.round(day.temp.day));
        $("#forecast-day-" + i + "-ht").text(Math.round(day.temp.max));
        $("#forecast-day-" + i + "-lt").text(Math.round(day.temp.min));
    }
}


// Refresh button handler
$("#refreshButton").on("click", function(){
    // Starts Refresh button's spinning animation
    $("#refreshButton").html("<i class='fa fa-refresh fa-spin fa-fw'></i>");
    getWeatherData();
});


// Celcius button handler.
// Converts every shown value to Celcius
$("#celcius").on("click", function(){
    if(!unitIsCelcius){
        $("#farenheit").removeClass("active");
        this.className = "active";

        // main day
        var today = globalForecast.list[0];
        today.temp.day = toCelcius(today.temp.day);
        today.temp.max = toCelcius(today.temp.max);
        today.temp.min = toCelcius(today.temp.min);
        globalForecast.list[0] = today;

        // week
        for(var i = 1; i < 5; i ++){
            var weekDay = globalForecast.list[i];
            weekDay.temp.day = toCelcius(weekDay.temp.day);
            weekDay.temp.max = toCelcius(weekDay.temp.max);
            weekDay.temp.min = toCelcius(weekDay.temp.min);
            globalForecast[i] = weekDay;
        }

        // update view with updated values
        updateForecast(globalForecast);

        unitIsCelcius = true;
    }
});


// Farenheit button handler
// Converts every shown value to Farenheit
$("#farenheit").on("click", function(){
    if(unitIsCelcius){
        $("#celcius").removeClass("active");
        this.className = "active";

        // main day
        var today = globalForecast.list[0];
        today.temp.day = toFerenheit(today.temp.day);
        today.temp.max = toFerenheit(today.temp.max);
        today.temp.min = toFerenheit(today.temp.min);
        globalForecast.list[0] = today;

        // week
        for(var i = 1; i < 5; i ++){
            var weekDay = globalForecast.list[i];
            weekDay.temp.day = toFerenheit(weekDay.temp.day);
            weekDay.temp.max = toFerenheit(weekDay.temp.max);
            weekDay.temp.min = toFerenheit(weekDay.temp.min);
            globalForecast[i] = weekDay;
        }

        // update view with updated values
        updateForecast(globalForecast);

        unitIsCelcius = false;
    }
});


// Applies the following format to date: WeekDay, Month Day, Year
function getFormattedDate(date){
    var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(date * 1000).toLocaleDateString("en-US",options);
}


// Formats the text to CamelCase
function toCamelCase(str) {
    var arr = str.split(" ").map(
        function(sentence){
            return sentence.charAt(0).toUpperCase() + sentence.substring(1);
        }
    );
    return arr.join(" ");
}


// Converts to Celcius
function toCelcius(val){
    return Math.round((val - 32) * (5/9));
}


// Converts to Farenheit
function toFerenheit(val){
    var degrees = (val * 1.8) + 32;
    var rounded = Math.round(degrees);
    return rounded;
}

var sportIconsMainFirstMap = {
    "01d": "fa-biking",
    "01n": "fa-dumbbell",
    "02d": "fa-running",
    "02n": "fa-dumbbell",
    "03d": "fa-running",
    "03n": "fa-dumbbell",
    "04d": "fa-running",
    "04n": "fa-dumbbell",
    "09d": "fa-shuttlecock",
    "09n": "fa-dumbbell",
    "10d": "fa-biking",
    "10n": "fa-dumbbell",
    "11d": "fa-dumbbell",
    "11n": "fa-dumbbell",
    "13d": "fa-skating",
    "13n": "fa-dumbbell",
    "50d": "fa-dumbbell",
    "50n": "fa-dumbbell"
};

var sportIconsMainSecondMap = {
    "01d": "fa-running",
    "01n": "fa-bowling-ball",
    "02d": "fa-dumbbell",
    "02n": "fa-bowling-ball",
    "03d": "fa-biking",
    "03n": "fa-bowling-ball",
    "04d": "fa-biking",
    "04n": "fa-bowling-ball",
    "09d": "fa-dumbbell",
    "09n": "fa-bowling-ball",
    "10d": "fa-running",
    "10n": "fa-bowling-ball",
    "11d": "fa-bowling-ball",
    "11n": "fa-bowling-ball",
    "13d": "fa-snowboarding",
    "13n": "fa-bowling-ball",
    "50d": "fa-running",
    "50n": "fa-bowling-ball"
};

var sportIconsMainThirdMap = {
    "01d": "fa-swimmer",
    "01n": "fa-table-tennis",
    "02d": "fa-swimmer",
    "02n": "fa-table-tennis",
    "03d": "fa-dumbbell",
    "03n": "fa-table-tennis",
    "04d": "fa-dumbbell",
    "04n": "fa-table-tennis",
    "09d": "fa-golf-ball",
    "09n": "fa-table-tennis",
    "10d": "fa-table-tennis",
    "10n": "fa-table-tennis",
    "11d": "fa-table-tennis",
    "11n": "fa-table-tennis",
    "13d": "fa-skiing-nordic",
    "13n": "fa-table-tennis",
    "50d": "fa-dumbbell",
    "50n": "fa-table-tennis"
};

var sportIconsForecastDayMap = {
    "01d": "fa-biking",
    "01n": "fa-dumbbell",
    "02d": "fa-running",
    "02n": "fa-dumbbell",
    "03d": "fa-volleyball-ball",
    "03n": "fa-dumbbell",
    "04d": "fa-baseball-ball",
    "04n": "fa-dumbbell",
    "09d": "fa-shuttlecock",
    "09n": "fa-dumbbell",
    "10d": "fa-biking",
    "10n": "fa-dumbbell",
    "11d": "fa-dumbbell",
    "11n": "fa-dumbbell",
    "13d": "fa-skating",
    "13n": "fa-dumbbell",
    "50d": "fa-dumbbell",
    "50n": "fa-dumbbell"
};

