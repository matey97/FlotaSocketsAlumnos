package servidor.flota.sockets;

import java.net.ServerSocket;

import comun.flota.sockets.MyStreamSocket;
import partida.flota.sockets.Partida;

public class ServidorFlotaIterativo {
	
	public static void main (String[] args) {
		Partida partida = new Partida(8,8,6);
		try (ServerSocket soc = new ServerSocket(12345)){
			System.out.println("Servidor listo para aceptar peticiones");
			MyStreamSocket myDataSocket;
			boolean done;
			int operacion = 0;
		    String peticion;
			while(true) {
				done = false;
				myDataSocket = new MyStreamSocket(soc.accept());
				System.out.println("Conexion aceptada");
				while(!done) {
					peticion = myDataSocket.receiveMessage();
					String[] campos = peticion.split("#");
					operacion = Integer.parseInt(campos[0]);
					 switch (operacion) {
		             case 0:  // fin de conexión con el cliente
		            	 // ...
		            	 myDataSocket.close();
		            	 done = true;
		            	 break;

		             case 1: { // Crea nueva partida
		            	 // ...
		            	 partida = new Partida(Integer.parseInt(campos[1]),
		            			 					Integer.parseInt(campos[2]),
		            			 					Integer.parseInt(campos[3]));
		            	 break;
		             }             
		             case 2: { // Prueba una casilla y devuelve el resultado al cliente
		            	 // ...
		            	 int res = partida.pruebaCasilla(Integer.parseInt(campos[1]),
		            			 						Integer.parseInt(campos[2]));
		            	 myDataSocket.sendMessage(String.valueOf(res));
		                 break;
		             }
		             case 3: { // Obtiene los datos de un barco y se los devuelve al cliente
		            	 // ...
		            	 String barco = partida.getBarco(Integer.parseInt(campos[1]));
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
				}
			}
			
		}catch(Exception e) {
			System.out.println("Error servidor iterativo");
		}
	}
}
