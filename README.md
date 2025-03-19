# parcial2-arquitectura
Parcial 2 Diseño y Arquitectura de software
Punto 6) a. 
Una vez Docker abierto:
Para crear una imagen con mysql se ponen los comandos: 
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=clave -e MYSQL_DATABASE=nombre

9. Para crear la imagen de docker y crear el contenedor respectivamente:
docker build -t parcial2-arquitectura-master:1.0.0 .      
     docker run -d -p 9000:80 parcial2-arquitectura-master:1.0.0

PARCIAL

# Parcial 2 Arquitectura

### Integrantes

- Daniela Puerto.
- Yeyson Pulido.

## Punto 1

### a.)

Según las necesidades del banco se pudieron identificar los siguientes atributos de calidad:

- Performance: Tiempos de respuesta inferiores a 200 milisegundos.
- Seguridad: El banco necesita manejar temas de privacidad.
- Extensibilidad: Agregar nuevas funcionalidades a medida que se necesiten.

### b.)

Por lo que, identificando los atributos de calidad, la infraestructura que mejor se acomodaría a la solución debería estar conformada por:

- Patrón de Access Token y SSO: Este patrón es perfecto para usar en aplicaciones o servicios que requieren autorización segura para acceder a recursos protegidos, como lo es precisamente un Banco, donde es indispensable el control de acceso a los recursos del usuario, en este caso, el dinero. Por lo que, este patrón estaría asegurando el atributo de seguridad.
- Patrón de microservicios con coreografía: Este patrón sirve para dividir las funcionalidades del sistema de manera independiente, y esto es justamente una de las necesidades del banco, la extensibilidad, por lo que, el Banco podría manejar cada uno de sus servicios (Clientes, pagos, etc.) de manera flexible y poder ir añadiendo las nuevas funcionalidades que necesite a través del tiempo sin tener que necesariamente intervenir los otros servicios.

## Punto 2

Para este punto elegimos **microservicios con orquestación:**

Tenemos un escenario donde es una aplicación para realizar reserva de citas médicas, entonces teniendo el servicio de reserva este se debe encargar de coordinar los demás servicios cómo el servicio de **logística** de el lugar donde se realiza la reserva, especialista y la persona encargada (consultorio y doctor), el servicio de **ventas** donde se realiza el pago de la consulta (pagos) y el servicio de **usuarios**. 

El servicio de reserva será el orquestador quién maneja la comunicación entre los diferentes microservicios.

## Punto 3

Para el ejemplo de Event-based-pattern se tiene una tienda real como Falabella, donde llega a tener 3 tipos de Event Producer, que son su sitio web, su aplicación movil y sus puntos de venta físicos.

A su vez, tiene diferentes Event Consumer, como el manejo del inventario en bodega almacenado en sus bases de datos, el sistema de pagos y el sistema de atención al usuario, cada uno de estos microservicios funcionan a partir de un evento desencadenado, ya sea por la solicitud  de un usuario através de la página web o su aplicación movil, de un empleado en sus puntos físicos o de bodega, etc. y son controlados según el Event Router para manejar todos estos eventos entre componentes, garantizando que entre ellos no se conozcan directamente, pero se devuelvan los request pertinentes a cada tipo de solicitud y usuario.

Ej: Un cliente pide una chaqueta X a través de la página web, la orden llega al Router y verifica que la nueva orden sea realizable verificando el stock en bodega, esto devuelvo la respuesta al Router a cerca de la existencia o no existencia de la chaqueta X para que finalmente el Router entregue el return exitoso o fallido de su orden en la página web.

## Punto 4

b.) La condición para poder efectuar transferencias es explícitamente que el usuario debe estar debidamente autenticado, es decir que tiene que cumplir con el requerimiento no funcional de seguridad, ya que se deben cuidar las acciones pertinentes a cada usuario, es decir hasta qué punto el usuario tiene permisos de interacción dentro del software.

## Punto 5 

Falso, ya que en el caso de la integración contínua los desarrolladores realizan sus commits en el repositorio central donde según las pruebas unitarias que se estén verificando, se puedan identificar y solucionar más fácilmente los errores. Al haber pasado las pruebas unitarias, los desarrolladores suben su parte del código, que deberá ser aprobado de manera manual o automática, sin embargo esto ya no sería continuous integration, sino ya implicaría escoger entre continuous delivery o continuous development para que finalmente se llegue al ambiente de producción.
