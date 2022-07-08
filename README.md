# retokruger
 Reto Kruger
<h1>RETO KRUGER</h1>

Este programa API-REST lleva un registro del inventario del estado de vacunación de los empleados:

* Registra, edita, lista y elimina a los empleados.
* Los campos contienen validaciones de acuerdo al tipo de dato.
* Al momento de agregar un empleado, se genera la contraseña encriptada mediante la cédula.
* Al momento de hacer los request, tiene un control de seguridad en los endpoints mediante JWT.
* Después guardar una persona, se debe hacer el request "login" respectivo, para generar el token de autorización.
* Cuando se desee realizar algún otro request, se debe ingresar el Header, Autirization añadiendo el token generado anteriormente.
* Si el empleado está en estado vacunado, se ingresa información de las vacunas



<h1>Usar el proyecto</h1>

* Para usar el proyecto se lo debe clonar o descargar en .zip luego abrir el proyecto con cualquier IDE de desarrollo compatible con el lenguaje de programacion JAVA.
* El proyecto fue realizado usando JAVA 18, se debe cambiar en el pom.xml, la versión de JAVA al de la máquina que se esté ejecutando, y accediendo a File, Proyect Structure, se debe escoger el SDK según la versión de JAVA de la máquina que se esté ejecutando.
* Se debe tener instalada la libreria [Lombok](https://projectlombok.org/), la cual permite optimizar codigo evitando declarar los setters, gettets y constructores de la clase.
* Se debe ejecutar dentro del IDE, en este caso, Intellij IDEA, la función de Maven "Reload All Maven Proyect" para que se instalen todas las dependencias a usar.

* Creamos una base de datos en POSTGRESQL con el nombre employeesDB

* En la carpeta resources del proyecto abrimos el archivo application.properties y procedemos a cambiar las credenciales del usuario y contraseña de acceso a la base de datos

  * spring.jpa.database=POSTGRESQL
  * spring.datasource.platform=postgres
  * spring.datasource.url=jdbc:postgresql://localhost:5432/employeesDB
  * spring.datasource.username=postgres
  * spring.datasource.password=contraseña
  * spring.jpa.show-sql=true

* Para crear las tablas quitamos el # la primera ves antes de ejecutar la aplicacion y lo volvemos a colocar una ves que se ejecute

  * spring.jpa.generate-ddl=true
  * spring.jpa.hibernate.dll-auto=create-drop
  * spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

* Una vez instalada las librerias necesaria, procedemos a ejecutar el programa principal con el Plugin de Maven sprin-boot:run. Y volvemos a colocar el # en los campos.

  * #spring.jpa.generate-ddl=true
  * #spring.jpa.hibernate.dll-auto=create-drop
  * #spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

* El programa se ejecuta en http://localhost:8080.

<h1>Peticiones</h1>

Para hacer las pruebas de las peticiones se puede utilizar [Postman](https://www.postman.com/) copiamos la direccion http://localhost:8080 
<h3>Peticiones Get</h3>

* http://localhost:8080/api/persons devuelve todas las personas como empleados registrados activos en la empresa. 
* http://localhost:8080/api/employees devuelve todas los detalles del empleado activo en la empresa.
* http://localhost:8080/api/vaccineEmployee devuelve toda la información de la vacuna con información del estado respectivo.
* http://localhost:8080/api/filterstatusvaccine/{status} devuelve todos los empleados  con el estado de vacunado (1) o no vacunado (0)
* http://localhost:8080/api/filternamevaccine/{name} devuelve todos los empleados  vacunados con el mismo nombre 

<h3>Peticiones Post</h3>

* http://localhost:8080/api/person inserta a la persona y lo crea como empleado.
* http://localhost:8080/api/login se envia el email y contaseña del empleado, para comprobar si está registrado, en dicho caso, se retorna el token de autorización.
* http://localhost:8080/api/vaccineEmployee/{id} actualiza la información de la vacuna, en respecto a un empleado si está vacunado.

<h3>Peticiones Put</h3>

* http://localhost:8080/api/employees/{id} actualiza la informacion del empleado.

<h3>Peticiones Patch</h3>

* http://localhost:8080/api/persons/{id} desabilita el estado de una persona en caso de ser despedido, actualizandolo a 0, y ya no se mostrará cuando se enliste.
* http://localhost:8080/api/employees/   desabilita el estado de una empleado en caso de ser despedido, actualizandolo a 0, y ya no se mostrará cuando se enliste.



