function create_food_dropdowns() {
    $.getJSON("/get_products", function (period) {
        $.each(period, function (key, val) {
            $('select[name ="food"]')
                .append($('<option>', {value: val.id})
                    .text(val.name));
        });
        $('.select-food').each(function (i, select) {
            if (!$(this).next().hasClass('dropdown-select')) {
                $(this).after('<div class="dropdown-select wide ' + ($(this).attr('class') || '') + '" tabindex="0"><span class="current"></span><div class="list"><ul></ul></div></div>');
                var dropdown = $(this).next();
                var options = $(select).find('option');
                var selected = $(this).find('option:selected');
                dropdown.find('.current').html(selected.data('display-text') || selected.text());
                options.each(function (j, o) {
                    var display = $(o).data('display-text') || '';
                    dropdown.find('ul').append('<li class="option ' + ($(o).is(':selected') ? 'selected' : '') + '" data-value="' + $(o).val() + '" data-display-text="' + display + '">' + $(o).text() + '</li>');
                });
            }
        });

        $('.select-food ul').before('<div class="dd-btn-area"><button class="genric-btn success-border circle dd-btn" data-toggle="modal" data-target="#addModal">' + $('#btn_add_new_food').text() + '</button></div>' +
            '<div class="dd-search"><input id="txtSearchValue" autocomplete="off" onkeyup="filter()" class="dd-searchbox" type="text"></div>');
    });
}

function create_time_dropdowns() {
    $.get("/get_eat_periods", function (period) {
        $.each(period, function (key, val) {
            $('select[name ="eat_period"]')
                .append($('<option>', {value: val.id})
                    .text(val.period));
        });
        $('.select-time').each(function (i, select) {
            if (!$(this).next().hasClass('dropdown-select')) {
                $(this).after('<div class="dropdown-select wide ' + ($(this).attr('class') || '') + '" tabindex="0"><span class="current"></span><div class="list"><ul></ul></div></div>');
                var dropdown = $(this).next();
                var options = $(select).find('option');
                var selected = $(this).find('option:selected');
                dropdown.find('.current').html(selected.data('display-text') || selected.text());
                options.each(function (j, o) {
                    var display = $(o).data('display-text') || '';
                    dropdown.find('ul').append('<li class="option ' + ($(o).is(':selected') ? 'selected' : '') + '" data-value="' + $(o).val() + '" data-display-text="' + display + '">' + $(o).text() + '</li>');
                });
            }
        });
    });
}

function create_meals() {
    $.getJSON("/get_meals", function (meals) {
        $.each(meals, function (key, val) {
            var html_result = '';
            if (val.length == 0) {
                html_result += '<tr class="d-flex">' +
                    '<th class="col-4">' + key + '</th>' +
                    '<td class="col-3"></td>' +
                    '<td class="col-3"></td>' +
                    '<td class="col-2"></td></tr>';
            } else {
                for (var i = 0; i < val.length; i++) {
                    var obj = JSON.parse(val[i]);
                    if (i == 0) {
                        html_result += '<tr class="d-flex">' +
                            '<th class="col-4">' + key + '</th>' +
                            '<td class="col-3">' + obj.product.name + '</td>' +
                            '<td class="col-3">' + obj.weight + '</td>' +
                            '<td class="col-2"><input class="cl_td mr-check" type="checkbox" name="toDelete" value="' + obj.id + '"></td></tr>';
                    } else {
                        html_result += '<tr class="d-flex">' +
                            '<th class="col-4"></th>' +
                            '<td class="col-3">' + obj.product.name + '</td>' +
                            '<td class="col-3">' + obj.weight + '</td>' +
                            '<td class="col-2"><input class="cl_td mr-check" type="checkbox" name="toDelete" value="' + obj.id + '"></td></tr>';
                    }
                }
            }
            $('.tbody-meals')
                .append(html_result);
        });
    });
}

// Event listeners

// Open/close
$(document).on('click', '.dropdown-select', function (event) {
    if ($(event.target).hasClass('dd-searchbox')) {
        return;
    }
    $('.dropdown-select').not($(this)).removeClass('open');
    $(this).toggleClass('open');
    if ($(this).hasClass('open')) {
        $(this).find('.option').attr('tabindex', 0);
        $(this).find('.selected').focus();
    } else {
        $(this).find('.option').removeAttr('tabindex');
        $(this).focus();
    }
});

// Close when clicking outside
$(document).on('click', function (event) {
    if ($(event.target).closest('.dropdown-select').length === 0) {
        $('.dropdown-select').removeClass('open');
        $('.dropdown-select .option').removeAttr('tabindex');
    }
    event.stopPropagation();
});

function filter() {
    var valThis = $('#txtSearchValue').val();
    $('.dropdown-select ul > li').each(function () {
        var text = $(this).text();
        (text.toLowerCase().indexOf(valThis.toLowerCase()) > -1) ? $(this).show() : $(this).hide();
    });
};
// Search

// Option click
$(document).on('click', '.dropdown-select .option', function (event) {
    $(this).closest('.list').find('.selected').removeClass('selected');
    $(this).addClass('selected');
    var text = $(this).data('display-text') || $(this).text();
    $(this).closest('.dropdown-select').find('.current').text(text);
    $(this).closest('.dropdown-select').prev('select').val($(this).data('value')).trigger('change');
});

// Keyboard events
$(document).on('keydown', '.dropdown-select', function (event) {
    var focused_option = $($(this).find('.list .option:focus')[0] || $(this).find('.list .option.selected')[0]);
    // Space or Enter
    //if (event.keyCode == 32 || event.keyCode == 13) {
    if (event.keyCode == 13) {
        if ($(this).hasClass('open')) {
            focused_option.trigger('click');
        } else {
            $(this).trigger('click');
        }
        return false;
        // Down
    } else if (event.keyCode == 40) {
        if (!$(this).hasClass('open')) {
            $(this).trigger('click');
        } else {
            focused_option.next().focus();
        }
        return false;
        // Up
    } else if (event.keyCode == 38) {
        if (!$(this).hasClass('open')) {
            $(this).trigger('click');
        } else {
            var focused_option = $($(this).find('.list .option:focus')[0] || $(this).find('.list .option.selected')[0]);
            focused_option.prev().focus();
        }
        return false;
        // Esc
    } else if (event.keyCode == 27) {
        if ($(this).hasClass('open')) {
            $(this).trigger('click');
        }
        return false;
    }
});

$(document).ready(function () {
    $.get("/get_client_info", function (data) {
        if (data == '') {
            $.get("/is_login", function (login) {
                if (login == true) {
                    $('li[name ="user"]').children("a").text("user");
                    $('li[name ="user"]').removeAttr('hidden');
                } else {
                    $('li[name ="sign"]').removeAttr('hidden');
                }
            })
        } else {
            $('li[name ="user"]').children("a").text(data.name);
            $('li[name ="user"]').removeAttr('hidden');
        }
        $.get("/is_admin", function (admin) {
            if (admin == true) {
                $('li[name ="admin"]').removeAttr('hidden');
            }
        })
    });

    create_food_dropdowns();
    create_time_dropdowns();
    create_meals();

    $(".show").show();
});

$('.fr-add').find('input').on('blur focus', function (e) {
    var $this = $(this),
        p = $this.next('p');

    if (e.type === 'blur') {
        if ($this.val() === '') {
            p.text($('#error_empty').text());
            $(this).addClass('invalid-input');
        } else {
            validate($this);
        }
    } else if (e.type === 'focus') {
        p.text('');
        $(this).removeClass('invalid-input');
    }
});

$(document).on('click', '.add-sbm', function (event) {
    $.each($('.fr-add :input'), function () {
        validate($(this));
    });

    var inputs = $('.fr-add').find('.invalid-input');
    if (inputs.length == 0) {
        return true;
    } else return false;
});