/*$(document).on('click', '.signup-sbm', function (event) {
    var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
    var pass_regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$/;
    alert("Tyt");*/

/*if (!email_regex.test($('input[name ="email"]').val())) {
    alert('this is not valid email!!!');
    return false;
}

if(!pass_regex.test($('input[name ="pass"]').val())) {
    alert('pass must be min 6 chars with at least 1 number, 1 letter uppercase and lowercase');
}

if($('input[name ="pass"]').val() != $('input[name ="conf-pass"]').val()){
    alert('different passwords');
    $('input[name ="pass"]').setCustomValidity('Different pass');
}*/

/*var email = document.querySelector('input[name="email"]');
email.setCustomValidity('Need your email bro!');
alert('4');

return false;
});*/

var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
/*var pass_regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$/;*/
var pass_regex = /^([\wа-яА-Я]{7,20})$/;
var name_regex = /^[a-zA-Zа-яА-Я\\s]{2,20}$/;
var age_num = /^[0-9]{1,4}$/;
var cal_num = /^[0-9]{1,4}$/;
var nutr_num = /^(\d*\.)?\d+$/;

function validate(input) {
    var label = input.prev('label'),
        p = input.next('p');
    var elem = input.attr('name');

    if (elem == 'login') {
        var email = input.val();
        if (!email_regex.test(email)) {
            p.text($( '#error_email' ).text());
            input.addClass('invalid-input');
        }
    }

    if (elem == 'password') {
        var pass = input.val();
        if (!pass_regex.test(pass)) {
            p.text($( '#error_pass' ).text());
            input.addClass('invalid-input');
        }
    }

    if (elem == 'conf-password') {
        var confpass = input.val();
        if (!pass_regex.test(confpass)) {
            p.text($( '#error_pass' ).text());
            input.addClass('invalid-input');
        } else {
            if ($('input[name ="password"]').val() != confpass) {
                p.text($( '#error_conf_pass' ).text());
                input.addClass('invalid-input');
            }
        }
    }

    if (elem == 'name') {
        var name = input.val();
        if (!name_regex.test(name)) {
            p.text($( '#error_name' ).text());
            input.addClass('invalid-input');
        }
    }

    if (elem == 'age') {
        var age = input.val();
        if (!age_num.test(age)) {
            p.text($( '#error_num' ).text());
            input.addClass('invalid-input');
        } else {
            if (age < 10 || age > 99) {
                p.text($( '#error_age' ).text());
                input.addClass('invalid-input');
            }
        }
    }

    if (elem == 'height') {
        var height = input.val();
        if (height < 90 || height > 250) {
            p.text($( '#error_height' ).text());
            input.addClass('invalid-input');
        }
    }

    if (elem == 'weight') {
        var weight = input.val();
        if (weight < 35 || weight > 300) {
            p.text($( '#error_weight' ).text());
            input.addClass('invalid-input');
        }
    }

    if (elem == 'calories') {
        var calories = input.val();
        if (!cal_num.test(calories)) {
            p.text($( '#error_num' ).text());
            input.addClass('invalid-input');
        } else {
            if (calories < 1 || calories > 2000) {
                p.text($( '#error_cal' ).text());
                input.addClass('invalid-input');
            }
        }
    }

    if (elem == 'protein' || elem == 'fats' || elem == 'carbohydrates') {
        var val = input.val();
        if (!nutr_num.test(val)) {
            p.text($( '#error_num' ).text());
            input.addClass('invalid-input');
        } else {
            if (val < 0 || val > 2000) {
                p.text($( '#error_nutr' ).text());
                input.addClass('invalid-input');
            }
        }
    }
};