package graph;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class graph {
	
	private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
	private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	private boolean hasCycle = false;
	
	public void clearLists(){
		this.arestas.clear();
		this.vertices.clear();
		this.setHasCycle(false);
	}

	public boolean isHasCycle() {
		return hasCycle;
	}

	public void setHasCycle(boolean hasCycle) {
		this.hasCycle = hasCycle;
	}

	public void addAresta(long peso, String origem, String destino){
		int i,j,k;
		
		//adiciona vertices e retorna posicao
		i = achaVertice(origem);
		j = achaVertice(destino);
		
		//adiciona aresta na lista de arestas do grafo
		Aresta a = new Aresta(peso,this.vertices.get(i),this.vertices.get(j));
		
		this.arestas.add(a);
		k = this.arestas.size();
		
		//adiciona aresta como adjacente ao vertice de origem
		this.vertices.get(i).addAdj(this.arestas.get(k-1));
		//this.vertices.get(j).addAdj(this.arestas.get(k-1)); //Grafo dirigido
	}
			
	public int addVertice(String nome, long peso){
			this.vertices.add(new Vertice(nome, peso));
			return (this.vertices.size() - 1);
	
	}
	
	public Integer achaVertice(String nome) {
		for (int i = 0 ; i<vertices.size();i++) {
			if (vertices.get(i).getNome().equals(nome)) {
				return i;
			}
		}
		return null;
	}
	

	public void limparVerticeVisitado(){
		for(int i=0; i<this.getVertices().size() ;i++)
			this.getVertices().get(i).setVisitado(false);
	}
	
	public void imprimeArvore(){
		for (int i=0; i<arestas.size();i++)
			System.out.print(this.arestas.get(i).getOrigem().getNome() + this.arestas.get(i).getDestino().getNome() + " - " + this.arestas.get(i).getPeso() + " | ");
		System.out.println();
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}
	
	public ArrayList<Aresta> getArestas() {
		return arestas;
	}
	
    
    public void DFS(Vertice v, ArrayList<Vertice> l) {
    	v.setVisitado(true);
    	
    	for (Vertice visinho: v.getVizinhos()) {
    		if(!visinho.isVisitado())
    			DFS(visinho, l);
    	}
    	
    	l.add(v);
    }
    
    public ArrayList<Vertice> Sort() {
    	ArrayList<Vertice> order = new ArrayList<Vertice>();
    	for(Vertice v:vertices){
    		if(!v.isVisitado())
    			DFS(v, order);
    	}
    	
    	//Collections.reverse(order);
    	return order;
    }
    
    public void calculaPesos() {
    	ArrayList<Vertice> top = new ArrayList<Vertice>();
    	top = Sort();
    	BigInteger aux = new BigInteger("0");
    	
    	for (Vertice v : top) {
    		
    		for (Aresta adj : v.getIncidentes()) {
    			System.out.println(v.getNome() +  " - " + adj.getPeso() + " - " +  adj.getDestino().getValorTotal());
    			aux =aux.add(new BigInteger(String.valueOf(adj.getDestino().getValorTotal().multiply(new BigInteger(Long.toString(adj.getPeso()))))));
    		}
    		
    		v.setValorTotal(new BigInteger(String.valueOf(aux.add(new BigInteger(Long.toString(v.getPeso()))))));  		
    		aux =  new BigInteger("0");
    	}
    	
    	
    	//Imprime o valor de cada Vértice
    	for (Vertice v : top) {
    		System.out.println(v.getNome()+ " ------ " +v.getValorTotal());
    	}
    	
    }
    
    

}
