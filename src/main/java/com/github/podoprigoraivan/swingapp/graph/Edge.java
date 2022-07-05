package com.github.podoprigoraivan.swingapp.graph;

public class Edge {
	public Vertex first;
	public Vertex second;
	public Edge(Vertex first, Vertex second){
		this.first = first;
		this.second = second;
	}
}