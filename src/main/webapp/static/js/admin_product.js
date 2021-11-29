// Open/close
$(document).on('click', '.item', function (event) {
    var id = this.id;
    var name = $(this).find('#item-name').text();
    var calories = $(this).find('#item-calories').text();
    var protein = $(this).find('#item-protein').text();
    var fats = $(this).find('#item-fats').text();
    var carbohydrates = $(this).find('#item-carbohydrates').text();
    var public = $(this).data( "public" );

    $('#editModal #ed_id').val(id);
    $('#editModal #ed_name').val(name);
    $('#editModal #ed_calories').val(calories);
    $('#editModal #ed_fats').val(fats);
    $('#editModal #ed_protein').val(protein);
    $('#editModal #ed_carbohydrates').val(carbohydrates);
    if(public===true){
        $('#editModal #ed_privateCheck').prop('checked', true);
    }

    $('#editModal').modal('show');
});

$(document).on('click', '.btn-danger', function (event) {
    var id = $('#editModal #ed_id').val();
    $.post( "/admin/product/delete", { id: id }).done(function( data ) {
        location.reload();
    });
});

function filter(){
    var valThis = $('#txtSearchValue').val();
    $('.dropdown-select ul > li').each(function(){
        var text = $(this).text();
        (text.toLowerCase().indexOf(valThis.toLowerCase()) > -1) ? $(this).show() : $(this).hide();
    });
};
// Search

$('.fr-add').find('input').on('blur focus', function (e) {
    var $this = $(this),
        p = $this.next('p');

    if (e.type === 'blur') {
        if( $this.val() === '' ) {
            p.text('Please enter a value');
            $(this).addClass('invalid-input');
        } else {
            validate($this);
        }
    } else if (e.type === 'focus') {
        p.text('');
        $(this).removeClass('invalid-input');
    }
});

$('.fr-edit').find('input').on('blur focus', function (e) {
    var $this = $(this),
        p = $this.next('p');

    if (e.type === 'blur') {
        if( $this.val() === '' ) {
            p.text('Please enter a value');
            $(this).addClass('invalid-input');
        } else {
            validate($this);
        }
    } else if (e.type === 'focus') {
        p.text('');
        $(this).removeClass('invalid-input');
    }
});

$(document).on('click', '.edit-sbm', function (event) {
    $.each($('.fr-edit :input'),function(){
        validate($(this));
    });
    var inputs = $('.fr-edit').find('.invalid-input');
    if(inputs.length == 0){
        return true;
    }
    else return false;
});

$(document).on('click', '.add-sbm', function (event) {
    $.each($('.fr-add :input'),function(){
        validate($(this));
    });

    var inputs = $('.fr-add').find('.invalid-input');
    if(inputs.length == 0){
        return true;
    }
    else return false;
});