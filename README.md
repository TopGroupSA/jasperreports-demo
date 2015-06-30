# jasperreports-demo
#Ejemplo reportes con JasperReports en Vaadin

> Requiere Maven 3.2 o superior para poder utilizar el plugin de spring-boot para maven.

###Ejecutar la aplicación por línea de comando:

En el directorio raíz del proyecto, ejecutar:

*mvn spring-boot:run -Djasperdemo.configuration.dir="\\\ruta_config"*

###Ejecutar la aplicación en Eclipse:
Importar el proyecto a Eclipse y correrlo como una aplicación Java. Se debe agregar a la VM el argumento: 

*-Djasperdemo.configuration.dir="\\\ruta_config"*

>Nota: "\\ruta_config" es la ruta del filesystem donde se encuentra el archivo configuration.properties


###URL de la aplicación

http://localhost:8080/
