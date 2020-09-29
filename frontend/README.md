# Exámen

A continuación se detallarán los pasos a seguir para poder instalar y correr el exámen.


## Instalación

La tecnología utilizada para el front-end es Angular 8 y para el back-end ASP NET CORE 2.2.
Los pasos de instalación son los siguientes:

- Clonar el proyecto
- Instalar [NodeJs](https://nodejs.org/es/)
- Luego instalar Angular CLI a con el siguiente comando: 
	>npm install -g @angular/cli 

Donde se instalará la última versión de Angular (actualmente es la versión 8).
Para finalizar la instalación para poder correr el cliente, debemos ingresar por línea de comando a la carpeta raíz del proyecto y luego pararse dentro de la carpeta del cliente:

- Raíz > ... > comex-cl > wwwroot

Y ejecutar el siguiente comando:
> npm install

Ya con todos estos pasos finalizados, puede correrse el proyecto a nivel cliente.

Para poder utilizar las API's del proyecto y dejar funcionando la aplicación por completo, debemos instalar el [SDK de ASP NET Core](https://dotnet.microsoft.com/download) (se desarrolló con la versión 2.2.3, pero no debería tener ningún inconveniente con la versión 3.0).

Al finalizar la instalación, ya estamos en condiciones de correr la aplicación.

## Ejecutar aplicación

Para poder compilar y levantar el cliente, debemos ir a la siguiente ruta:

- Raíz > ... > comex-cl > wwwroot

Y ejecutar el siguiente comando:
> ng serve

Cambiar los datos de acceso a la BD en las siguientes rutas:

- Raíz > ... > comex-cl > comexApi > appsettings.json
- Raíz > ... > comex-cl > comexApi > Repositories > ComexDbContext.cs

Para poder actualizar, compilar y levantar el servidor, debemos ir a la siguiente ruta:

- Raíz > ... > comex-cl > comexApi

Y ejecutar los siguientes comandos:
> dotnet ef database update (para actualizar la bd)
> dotnet run (para compilar y levantar el servidor)

Para finalizar, ingresar a siguiente ruta para poder ingresar al sitio:

> http://localhost:4200/


