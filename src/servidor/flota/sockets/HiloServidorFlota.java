package servidor.flota.sockets;


import java.io.IOException;
import java.net.SocketException;

import partida.flota.sockets.*;
import comun.flota.sockets.MyStreamSocket;

/**
 * Clase ejecutada por cada hebra encargada de servir a un cliente del juego Hundir la flota.
 * El metodo run contiene la logica para gestionar una sesion con un cliente.
 */

 // Revisar el apartado 5.5. del libro de Liu

class HiloServidorFlota implements Runnable {
   MyStreamSocket myDataSocket;
   private Partida partida = null;

	/**
	 * Construye el objeto a ejecutar por la hebra para servir a un cliente
	 * @param	myDataSocket	socket stream para comunicarse con el cliente
	 */
   HiloServidorFlota(MyStreamSocket myDataSocket) {  
      // Por implementar
	   this.myDataSocket = myDataSocket;
	   this.partida = new Partida(8,8,6);
   }
 
   /**
	* Gestiona una sesion con un cliente	
   */
   public void run( ) {
      boolean done = false;
      int operacion = 0;
      // ...
      String peticion;
      try {
         while (!done) {
        	 // Recibe una peticion del cliente
        	 peticion = myDataSocket.receiveMessage();
        	 // Extrae la operación y los argumentos
        	 String[] args = peticion.split("#");
        	 operacion = Integer.parseInt(args[0]);
                      
             switch (operacion) {
             case 0:  // fin de conexión con el cliente
            	 // ...
            	 myDataSocket.close();
            	 done = true;
            	 break;

             case 1: { // Crea nueva partida
            	 // ...
            	 this.partida = new Partida(Integer.parseInt(args[1]),
            			 					Integer.parseInt(args[2]),
            			 					Integer.parseInt(args[3]));
            	 break;
             }             
             case 2: { // Prueba una casilla y devuelve el resultado al cliente
            	 // ...
            	 int res = partida.pruebaCasilla(Integer.parseInt(args[1]),
            			 						Integer.parseInt(args[2]));
            	 myDataSocket.sendMessage(String.valueOf(res));
                 break;
             }
             case 3: { // Obtiene los datos de un barco y se los devuelve al cliente
            	 // ...
            	 String barco = partida.getBarco(Integer.parseInt(args[1]));
            	 myDataSocket.sendMessage(barco);
                 break;
             }
             case 4: { // Devuelve al cliente la solucion en forma de vector de cadenas
        	   // Primero envia el numero de barcos 
               // Despues envia una cadena por cada barco
            	 String[] barcos = partida.getSolucion();
            	 myDataSocket.sendMessage(String.valueOf(barcos.length));
            	 for (String barco : barcos)
            		 myDataSocket.sendMessage(barco);
                 break;
             }
         } // fin switch
       } // fin while   
     } // fin try
     catch (Exception ex) {
        System.out.println("Exception caught in thread: " + ex);
     } // fin catch
   } //fin run
   
} //fin class 
