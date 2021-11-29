var closeAlert = document.querySelectorAll('.alert .close');
for (var i = 0; i < closeAlert.length; i++) {
    closeAlert[i].addEventListener('click', function(event) {
        event.preventDefault;
        this.parentNode.style.display = "none";
    }, false);
}