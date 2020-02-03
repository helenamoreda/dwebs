
window.addEventListener("load", () => {
    getResponse(null, "/Agenda_personal_HMB/server?opcion=listar");
});

function getResponse(id, action) {
    var URLparams;
    var empleado;
    /* id: document.getElementById("idEmpleado").value,
     nombre: document.getElementById("nombre").value,
     apellidos: document.getElementById("apellidos").value,
     email: document.getElementById("email").value,
     telmovil: document.getElementById("movil").value,
     telfijo: document.getElementById("fijo").value,
     extension: document.getElementById("dept").value
 }*/
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            obj = JSON.parse(this.responseText);

            for (let index = 0; index < obj.length; index++) {
                const empleado = obj[index];
                console.log(obj[index]);
            }

            if (action.split("opcion=")[1] == "listar") {
                empleado = null;
                listarEmpleados(obj);
            }
        }
    };

    xhttp.open("GET", action, true, empleado);

    xhttp.setRequestHeader("Content-type",
        "application/x-www-form-urlencoded");
    xhttp.send(URLparams);
}


function listarEmpleados(obj) {
    let table = document.getElementById("tabla");
    let tr = document.createElement('tr');

    for (const key in obj) {
        let td = document.createElement('td');
        let tdOpciones = document.createElement('td');
        let textNombre = document.createTextNode(key.nombre);
        let textBorrar = document.createTextNode("Borrar");
        tdOpciones.appendChild(textBorrar);
        td.appendChild(textNombre);
        tr.appendChild(td);
        table.appendChild(tr);
    }
}