# FlotaSocketsAlumnos
Project from EI1021-Sistemas Distribuidos. Battleship game using a multithread server with StreamSocket

El proyecto está dividido en los siguientes paquetes:

- Servidor: Contiene un servidor iterativo (no concurrente), un servidor concurrente y el thread que este lanza. Se emplean Sockets Stream.
- Partida: Contiene la definición de una partida y de un barco.
- Comun: Contiene una clase recubridora de StreamSocket, facilitando su uso.
- Cliente: Contiene el cliente y una clase auxiliar que será con la que se pedirá la conexión y se realizará la comunicación con el thread.

Para ver el funcionamiento, primero arrancar un servidor y posteriormente uno o varios clientes. Si se ha arrancado el servidor concurrente podrá haber varios clientes a la vez.
