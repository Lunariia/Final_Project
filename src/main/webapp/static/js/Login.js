const mobileNavBtn = document.querySelector('.mobile-btn');
const menu = document.querySelector('main aside');
const backdrop = document.querySelector('.backdrop');

mobileNavBtn.addEventListener('click', () => {
    menu.style.display = 'block';
    backdrop.style.display = 'block';
});

backdrop.addEventListener('click', () => {
    backdrop.style.display = 'none';
    menu.style.display = 'none';
});

function DropDown(el) {
    this.dd = el;
    this.initEvents();
}

DropDown.prototype = {
    initEvents: function() {
        var obj = this;

        obj.dd.on('click', function(event) {
            $(this).toggleClass('active');
            event.stopPropagation();
        });
    }
}