$(document).ready(function () {
    $.get("/get_client_info", function (data) {
        if (data == '') {
            $.get("/is_login", function (login) {
                if (login == true) {
                    $('li[name ="user"]').children("a").text("user");
                    $('li[name ="user"]').removeAttr('hidden');
                    $('div[name ="prediction"]').removeAttr('hidden');
                    //$('div[name ="no_registration"]').removeAttr('hidden');
                } else {
                    $('li[name ="sign"]').removeAttr('hidden');
                    $('div[name ="no_registration"]').removeAttr('hidden');
                }
            })
        } else {
            $('li[name ="user"]').children("a").text(data.name);
            $('li[name ="user"]').removeAttr('hidden');
            $('div[name ="prediction"]').removeAttr('hidden');
            //$('div[name ="no_registration"]').removeAttr('hidden');
        }
        $.get("/is_admin", function (admin) {
            if (admin == true) {
                $('li[name ="admin"]').removeAttr('hidden');
            }
        })
    });
})