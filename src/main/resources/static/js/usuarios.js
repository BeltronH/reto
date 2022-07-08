// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#persons').DataTable();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}


async function cargarUsuarios() {
  const request = await fetch('api/persons', {
    method: 'GET',
    headers: getHeaders()
  });
  const persons = await request.json();


  let listadoHtml = '';
  for (let person of persons) {
    //let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let identificationTexto = person.identification == null ? '-' : person.identification;
    let personHtml = '<tr><td>'+person.personId+'</td><td>' + person.firstName + ' ' + person.lastName + '</td><td>'+ person.email+'</td><td>'+identificationTexto +'</td></tr>';
    listadoHtml += personHtml;
  }

document.querySelector('#usuarios tbody').outerHTML = listadoHtml;

}

function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
     'Authorization': localStorage.token
   };
}

