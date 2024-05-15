package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import coches.Auto;
import coches.Autos;
import coches.Transmision;

public class Main {
	public static Scanner scannerauto = new Scanner(System.in);
	public static void main(String[] args) {
		// https://howtodoinjava.com/gson/gson/
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("src\\main\\resources\\Coches.json"));
			FileWriter fw = new FileWriter("src\\main\\resources\\Coches1.json")) {
			
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				Autos coches = gson.fromJson(reader, Autos.class);
				
				
				try {
					Boolean consola = true;	
					while (consola == true) {
						System.out.println("\nDara la tecla enter para acceder a la consola");
						scannerauto.nextLine();
						System.out.println("\nPorfavor, elija una de las siguientes opciones mediante el uso de números enteros:\n");
						System.out.println("1. Mostrar el catálogo entero.\n" + "2. Añadir nuevos coches al catálogo.\n" 
						+ "3. Buscar coches mediante el tipo de marca.\n" + "4. Salir de la consola.\n");
						int numero = scannerauto.nextInt();
						scannerauto.nextLine();
				        
						switch (numero) {
						    case 1:
						    	coches.imprimirAutos();
	                            break;
	                        case 2:
	                        	Transmision transmision = new Transmision(1);
	                        	int identificadorfinal =  Integer.parseInt(coches.getAutos().get((coches.getAutos().size())-1).getId()) + 1;
	                        	//String identificadorfinal =  coches.getAuto().get((coches.getAuto().size())-1).getIdentificacion();
	                        	String identificador = ("00" + identificadorfinal);
	                        	Auto nuevoauto = new Auto(transmision,identificador);
	                        	coches.añadirAutos(nuevoauto);
	               
	                        	break;
	                        case 3:
	                        	try {
	                        		ArrayList<String> modelos = new ArrayList<String>();
	                        		System.out.println("Elija que coches quieres ver según su marca mediante el uso de números enteros:\n");                
	                        		//Nos coge todos los modelos que existen, pero sin repetirlos
	                        		for (Auto coche : coches.getAutos()) {
	                        			if (!modelos.contains(coche.getMarca())) {
	                        				modelos.add(coche.getMarca());
	                        			}
	                        		}
	                        		// Nos muestra todos los tipos de marcas que hay
	                        		for (int i = 0; i <modelos.size(); i++) {
	                        			System.out.println((i+1) + ". " + modelos.get(i));
	                        		}
	                        		int numero1 = scannerauto.nextInt();
	                        		String modelo = coches.getAutos().get(numero1-1).getMarca();
	                        		
	                        		// Recorre los cohces que hay y muestra solo los que coinciden con la marca que le hemos dado
	                        		for (Auto coche : coches.getAutos()) {
	                        				if (coche.getMarca().equals(modelo)) {
	                                        coche.imprimirAuto();
	                                    } 
	                        		}
	                        	} catch (InputMismatchException | IndexOutOfBoundsException e) {
	                        		System.out.println("Debes usar números enteros y que esten dentro de las opciones");
	                        	}
	                        	break;
	                        case 4:
	                        	consola = false;
	                            break;
	                        default:
	                        	System.out.println("Procure que el dato que hayas insertado este dentro de las opciones posibles");
						}
					}	
					scannerauto.close();
				} catch (InputMismatchException e) {
					System.out.println("Procure que el dato que hayas insertado sea un número entero");
				}
				String cochesJson = gson.toJson(coches);
				fw.write(cochesJson);
				System.out.println(cochesJson);
				
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
	}
}
