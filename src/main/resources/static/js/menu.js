let listElements = document.querySelectorAll('.lista-item');

listElements.forEach(listElement => {
    listElement.addEventListener('click', () => { //detecta el evento click en los items de la lista del menú
        let menu = listElement.nextElementSibling;
        menu.classList.toggle('active');
    });
});

// Contenido de script.js

function confirmarOcultar() {
	return confirm('¿Estás seguro de que deseas eliminar esta entrada?');
}


function closeNotification(notificationId) {
    var notification = document.getElementById(notificationId);
    notification.style.display = "none";
}