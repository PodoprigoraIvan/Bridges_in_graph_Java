package graphics;

import javax.swing.*; 
import java.awt.Color;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;
import graph.*;
import random.*;
import algorithm.*;

public class DrawPanel extends JPanel {
	ArrayList<Vertex> vertex_list = new ArrayList<>();
	ArrayList<Edge> edges_list = new ArrayList<>();
	ArrayList<Edge> bridges_list = new ArrayList<>();
	int cur_num = 0;
	int circle_size = 50;
		
	DrawPanel(){
		this.setPreferredSize(new Dimension(400, 400));
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				vertex_list.add(new Vertex(e.getX() - circle_size/2, e.getY() - circle_size/2, ++cur_num));
				repaint();
			}
		});
	}

	public void clear(){
		vertex_list.clear();
		edges_list.clear();
		bridges_list.clear();
		cur_num = 0;
		repaint();
	}
	
	public void changeCircleSize(int new_size){
		this.circle_size = new_size;
		repaint();
	}
	
	public void addEdge(String first, String second){
		//if (isNumeric(first) == false || isNumeric(second) == false) return;
		int num1 = Integer.valueOf(first);
		int num2 = Integer.valueOf(second);
		if (num1 == num2) return;
		for (Edge edge : edges_list){
			if ((edge.first.num == num1 && edge.second.num == num2) || (edge.first.num == num2 && edge.second.num == num1)) return;
		}
		Vertex v1 = null, v2 = null;
		for (Vertex vertex : vertex_list){
			if (vertex.num == num1) v1 = vertex;
			if (vertex.num == num2) v2 = vertex;
		}
		if (v1 == null || v2 == null) return;
		edges_list.add(new Edge(v1, v2));
		bridges_list.clear();
		repaint();
	}
	
	public void generateGraph(String maxV, String maxE){
		clear();
		RandomGraphCreator.CreateRandomGraph(vertex_list, edges_list, Integer.valueOf(maxV), Integer.valueOf(maxE), 600, 900);
		cur_num = vertex_list.size();
		repaint();
	}
	
	public void showBridges() {
		bridges_list = BridgesFinder.findBridges(vertex_list, edges_list);
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g.setFont(new Font("Courier", Font.PLAIN, circle_size/3/*20*/));
		g2D.setStroke(new BasicStroke(4));
		
		g2D.setPaint(Color.BLUE);
		for (Edge edge : edges_list){
			g2D.drawLine(edge.first.x + circle_size/2, edge.first.y + circle_size/2, edge.second.x + circle_size/2, edge.second.y + circle_size/2);
		}
		g2D.setPaint(Color.GREEN);
		for (Edge bridge : bridges_list){
			g2D.drawLine(bridge.first.x + circle_size/2, bridge.first.y + circle_size/2, bridge.second.x + circle_size/2, bridge.second.y + circle_size/2);
		}
		for (Vertex v : vertex_list){
			g2D.setPaint(new Color(60, 140, 60));
			g2D.fillOval(v.x-circle_size/12, v.y-circle_size/12, circle_size, circle_size);
			g2D.setPaint(Color.GRAY);
			g2D.fillOval(v.x, v.y, circle_size*5/6, circle_size*5/6);
			g2D.setColor(Color.RED);
			g2D.drawString(String.valueOf(v.num), v.x + circle_size/3, v.y + circle_size/2);
		}
	}
}