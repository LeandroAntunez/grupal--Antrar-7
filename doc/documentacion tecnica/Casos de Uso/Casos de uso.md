# Caso de uso: CU-01  

### Descripción del escenario  

>Ante el llamado de un nuevo cliente, el usuario del sistema desea  registrarlo como tal,  solicitando los  datos correspondientes para ingresarlos en el sistema.  

### Actores  

* Usuario del sistema (Telefonista).  

### Secuencia de interacciones entre los actores y el sistema  
* 1) El usuario solicita ingresar un nuevo cliente.  
* 2) El sistema  ofrece una  interface  de carga  de carga de  datos.  
* 3) El usuario ingresa los  datos.  
* 4) El usuario confirma  los  datos ingresados.  
* 5) El sistema almacena  en la base de datos el nuevo  cliente.  
* 6) El sistema  notifica  al  usuario la finalización del proceso.  
* 7) El sistema   vuelve  al estado inicial.  


### Extensiones / Flujos secundarios  


* 1. El usuario puede cancelar la acción en cualquier momento. El sistema no realiza ninguna acción salvo volver al estado inicial.  
* 2. Si el usuario que solicita ingresar un  cliente  que  ya existe.  
   - 5 .a. El sistema informa  que  el usuario ya esta  registrado.  
   - 6.a. El sistema  vuelve a  presentar  la  interface de carga  para  verificar  los datos.  
   - 7.a. El usuario comprueba  que los datos  son  correctos y  cancela la operación  por que 	el  cliente  ya esta registrado.  
   - 8.a. El sistema  vuelve al  estado inicial.  

* 3. Si el usuario ingresa datos erroneos:  
    - 7.b. El usuario  comprueba que los datos ingresados fueron erróneos, los  corrige y  confirma, el sistema  vuelve al paso 5. de la “secuencia de interacciones entre los actores y el sistema”.  

### Tabla resumen  

| ID	                | CU-01                                        |
| --------------------- | -------------------------------------------- |
| Nombre                | Alta  de  cliente                            |
| Objetivo              | Dar de alta  un  nuevo cliente en el sistema |
| Actores               | Usuario telefonista                          |
| Condiciones iniciales | El cliente  no debe  estar  registrado.      |
| Condiciones finales   | El usuario consigue.                         |
