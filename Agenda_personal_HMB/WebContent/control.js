
window.addEventListener("load", () => {
    getResponse(null, "/Agenda_personal_HMB/server?opcion=listar");
    aniadirDept();
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
    
    for (const key in obj) {
        let tr = document.createElement('tr');
        let tdNombre = document.createElement('td');
        let tdRadio = document.createElement('td');
        let tdFoto = document.createElement('td');
        let tdEmail = document.createElement('td');
        let tdTelPer = document.createElement('td');
        let tdTelMov = document.createElement('td');
        let tdExt = document.createElement('td');
        let tdOpciones = document.createElement('td');

        var radiob = document.createElement("INPUT");
        radiob.setAttribute("type", "radio");
        radiob.setAttribute("name", "seleccionado");
        radiob.onclick = function() {
            rellenarFormulario(obj[key]);
        }
        radiob.setAttribute("id", obj[key].id);
        tdRadio.appendChild(radiob);
        
        let textNombre = document.createTextNode(obj[key].nombre +" "+ obj[key].apellidos);
        tdNombre.appendChild(textNombre);
        
        let imgFoto = document.createElement("IMG");
        imgFoto.src = obj[key].foto;
        imgFoto.width = "40";
        imgFoto.height = "40";
        tdFoto.appendChild(imgFoto);

        let textEmail = document.createTextNode(obj[key].email);
        tdEmail.appendChild(textEmail);
        
        let textTelPer = document.createTextNode(obj[key].telfijo);
        tdTelPer.appendChild(textTelPer);

        let textTelMov = document.createTextNode(obj[key].telmovil);
        tdTelMov.appendChild(textTelMov);

        let textExt = document.createTextNode(obj[key].extension);
        tdExt.appendChild(textExt);

        let textBorrar = document.createElement("button");
        textBorrar.setAttribute("id", "borrar");
        textBorrar.onclick = function() {
            getResponse(null, '/Agenda_personal_HMB/server?opcion=delete&id='+obj[key].id);
        }
        textBorrar.appendChild(document.createTextNode("Borrar"));
        tdOpciones.appendChild(textBorrar);
        
        tr.appendChild(tdRadio);
        tr.appendChild(tdFoto);
        tr.appendChild(tdNombre);
        tr.appendChild(tdEmail);
        tr.appendChild(tdTelPer);
        tr.appendChild(tdTelMov);
        tr.appendChild(tdExt);
        tr.appendChild(tdOpciones);
        table.appendChild(tr);
    }

    function rellenarFormulario(obj) {
        document.getElementById("nombre").value=obj.nombre;
        document.getElementById("apellidos").value=obj.apellidos;
        document.getElementById("email").value=obj.email;
        document.getElementById("movil").value=obj.telmovil;
        document.getElementById("fijo").value=obj.telfijo;
        document.getElementById("dept").value=obj.extension;
    }
}