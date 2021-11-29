$('.rg-form').find('input, textarea').on('keyup blur focus', function (e) {
    var $this = $(this),
        label = $this.prev('label'),
        p = $this.next('p');

    if (e.type === 'keyup') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
        if( $this.val() === '' ) {
            p.text($( '#error_empty' ).text());
            $(this).addClass('invalid-input');
            label.removeClass('active highlight');
        } else {
            validate($this);
            label.removeClass('highlight');
        }
    } else if (e.type === 'focus') {
        p.text('');
        $(this).removeClass('invalid-input');
        if( $this.val() === '' ) {
            label.removeClass('highlight');
        }
        else if( $this.val() !== '' ) {
            label.addClass('highlight');
        }
    }
});

$('.rg-tab a').on('click', function (e) {

    e.preventDefault();

    $('.alert-danger').remove();

    $(this).parent().addClass('active');
    $(this).parent().siblings().removeClass('active');

    target = $(this).attr('href');

    $('.rg-tab-content > div').not(target).hide();

    $(target).fadeIn(600);
});

$(document).on('click', '.signup-sbm', function (event) {
    var inputs = $('.rg-form .singup').find('.invalid-input');
    if(inputs.length == 0){
        return true;
    }
    else return false;
});

