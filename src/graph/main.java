package graph;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;


public class main extends graph {

	public static void main(String[] args) throws IOException {
		
		graph a = new graph();	
		Path path1 = Paths.get("caso0100");
		try (Scanner sc = new Scanner(Files.newBufferedReader(path1, Charset.forName("utf8")))) {
			sc.useDelimiter("[\n\r]");
			int header = sc.nextInt();
			
			String origem;
			String destino;
			long peso;
			long quantidade;
		
			for (int i=0;i<header;i++) {
				
				String[] parts= sc.next().split(" ");
				origem = parts[0];
				peso = Long.parseLong(parts[1], 10) ;
				
				a.addVertice(origem, peso);
				//System.out.println(origem + "  " + peso);
			}
			
			header = sc.nextInt();
			
			for (int i=0;i<header;i++) {
				
				String[] parts= sc.next().split(" ");
				origem = parts[0];
				destino = parts[1];
				quantidade = Long.parseLong(parts[2], 10) ;
				
				a.addAresta(a.achavertice(origem), a.achavertice(destino), quantidade);
				a.achavertice(destino).addDependente(a.achavertice(destino), a.achavertice(origem), quantidade);
				//System.out.println(origem + "  " + destino + " " + quantidade);
			}
			
			//System.out.println("\n\n\n\n" + a);
			//a.percorreProfundidade(a, "FWATRRQP");
			a.calculaCustos(a,a.prucuraUltimo(a));
			a.imprimePesos();
		//	System.out.println(a.gastoTotal);
			
		}

	}
}


