package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enums.EIdioma;

public abstract class GeneradorDePalabras {
	
	public static String conseguirPalabra(EIdioma idioma) throws IOException {
		
		List<String> list = new ArrayList<String>();
		
		if(idioma == EIdioma.Spanish){
			list = loadListFromFile("wordsSpanish.txt");	
		}else if(idioma == EIdioma.English){
			list = loadListFromFile("wordsEnglish.txt");			
		}
		
		return list.get(new Random().nextInt(list.size()));
	}

	private static List<String> loadListFromFile(String file) throws IOException {
		List<String> a = new ArrayList<String>();
		String line;

		InputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		
		while ((line = br.readLine()) != null) {
			a.add(line);
		}

		br.close();	

		return a;
	}
}
