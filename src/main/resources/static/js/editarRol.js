let listElements = document.querySelectorAll('.clic');

listElements.forEach(listElement => {
    listElement.addEventListener('click', () => { //detecta el evento click en los items de la lista del menú
        listElement.classList.toggle('arrow'); //agrega la clase arrow
        let menu = listElement.nextElementSibling;
        menu.classList.toggle('active');
    });
});

