package graph;

import java.math.BigInteger;
import java.util.ArrayList;

public class Vertice implements Comparable<Vertice> {
	private String nome;
	private long peso;
	private BigInteger valorTotal;
	private ArrayList<Aresta> incidentes = new ArrayList<Aresta>();
	private ArrayList<Vertice> vizinhos;
	private boolean visitado = false;
	
	
	
	public Vertice(String nome, long peso) {
		vizinhos = new ArrayList<Vertice>();
		this.nome = nome;
		this.peso = peso;

	}


	public BigInteger getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(BigInteger valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public long getPeso() {
		return peso;
	}
	
	public void setPeso(long l) {
		this.peso = l;
	}

	public boolean isVisitado() {
		return visitado;
	}
	
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public ArrayList<Aresta> getIncidentes() {
		return incidentes;
	}
	
	public void addAdj(Aresta incide) {
		this.incidentes.add(incide);
		
		
		if ( (incide.getOrigem().getNome().equals(this.getNome())) &&
				(!this.isVizinho(incide.getDestino())) ){
			
			this.addVizinhos(incide.getDestino());
			
		}else if ( (incide.getDestino().getNome().equals(this.getNome())) &&
				(!this.isVizinho(incide.getOrigem())) ){
			
			this.addVizinhos(incide.getOrigem());
		}
	}
	
	public void addVizinhos(Vertice vizinho) {
		this.vizinhos.add(vizinho);
	}

	public ArrayList<Vertice> getVizinhos() {
		return vizinhos;
	}
	
	public boolean isVizinho(Vertice vizinho){
		for (int i=0; i<this.vizinhos.size() ; i++)
			if (this.vizinhos.get(i).getNome().equals(vizinho.getNome()))
				return true;		
		
		return false;
	}
	

	@Override
	public int compareTo(Vertice vertice) {
		
        if(this.getPeso() < vertice.getPeso()) 
        	return -1;
        else if(this.getPeso() == vertice.getPeso()) 
        	return 0;

        return 1;  
	}

	@Override
	public String toString() {
		String s = " ";
		s+= this.getNome();
		return s;
	}
	 
}