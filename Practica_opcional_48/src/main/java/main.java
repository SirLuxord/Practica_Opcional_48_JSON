import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import coches.Auto;
import coches.Autos;

public class main {
	
	public static void main(String[] args) {
		// https://howtodoinjava.com/gson/gson/
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("src\\main\\resources\\Coches.json"))) {
			Gson gson = new Gson();
			Autos coches = gson.fromJson(reader, Autos.class);
			coches.imprimirAutos();
			System.out.println(coches);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
