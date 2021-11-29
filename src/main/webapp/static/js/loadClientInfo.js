$(document).ready(function () {
    $.getJSON("/get_genders", function (data) {
        $.each(data, function (key, val) {
            $('select[name ="gender"]')
                .append($('<option>', {value: val.id})
                    .text(val.gender));
        });
    });

    $.getJSON("/get_activities", function (data) {
        $.each(data, function (key, val) {
            $('select[name ="activity"]')
                .append($('<option>', {value: val.id})
                    .text(val.activity));
        });
    });

    $.getJSON("/get_nutr_goals", function (data) {
        $.each(data, function (key, val) {
            $('select[name ="nutr_goal"]')
                .append($('<option>', {value: val.id})
                    .text(val.goal));
        });
    });

    $.get("/get_client_info", function (data) {
        if (data != '') {
            $('input[name ="name"]').val(data.name);
            $('input[name ="age"]').val(data.age);
            $('input[name ="height"]').val(data.height);
            $('input[name ="weight"]').val(data.weight);
            $('select[name="gender"] option[value="' + data.gender.id + '"]').attr('selected', 'selected');
            $('select[name="activity"] option[value="' + data.activity.id + '"]').attr('selected', 'selected');
            $('select[name="nutr_goal"] option[value="' + data.nutritionGoal.id + '"]').attr('selected', 'selected');
        }
    })
})