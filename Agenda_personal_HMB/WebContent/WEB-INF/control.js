
document.addEventListener("load", ()=> {
    getResponse(null, "");
});

function listarEmpleados(response) {
    var table = document.getElementById("tabla");
    var tr = document.createElement('tr');

    response.forEach(element => {
        var td = document.createElement('td');
        var tdOpciones = document.createElement('td');
        var text = element.nombre;
        var textBorrar = "Borrar";
        tdOpciones.appendChild(textBorrar);
        td.appendChild(text);
        tr.appendChild(td);
        table.appendChild(tr);
    });
}

function getResponse(id, action) {
    var URLparams;
    var empleado = {
        id: document.getElementById("idEmpleado").value,
        nombre: document.getElementById("nombre").value,
        apellidos: document.getElementById("apellidos").value,
        email: document.getElementById("email").value,
        telmovil: document.getElementById("movil").value,
        telfijo: document.getElementById("fijo").value,
        extension: document.getElementById("dept").value
    }
    
    // 2. Creación de objeto request
    var xhttp = new XMLHttpRequest();

    // 3. Definición del manejador cuando reciba el contenido asíncrono
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this);
            console.log('respuesta: ' + this.response);
            console.log('for:');
            this.response.forEach(element => {
                console.log(element);
            });
            /*
            var html = "<li>" + this.responseText + "</li>";
            document.getElementById(id).innerHTML += html;*/
            if (action == "") {
                empleado = null;
                listarEmpleados(this.response);
            }
        }
    };

    // 5. Método de envío
    xhttp.open("GET", action, true, empleado);

    // 6. Enviar petición
    xhttp.setRequestHeader("Content-type",
        "application/x-www-form-urlencoded");
    xhttp.send(URLparams);
}