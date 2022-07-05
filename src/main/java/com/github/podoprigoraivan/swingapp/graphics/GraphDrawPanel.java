package com.github.podoprigoraivan.swingapp.graphics;

import javax.swing.*; 
import java.awt.Color;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;
import com.github.podoprigoraivan.swingapp.graph.*;

public class GraphDrawPanel extends JPanel {
	private DrawableGraph drawableGraph;
	public boolean showBridges = false;
	private Integer draggedVertex = null;
	int circleSize = 50;
		
	GraphDrawPanel(DrawableGraph drawableGraph){
		this.setPreferredSize(new Dimension(400, 400));
		this.drawableGraph = drawableGraph;
		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				drawableGraph.AddVertex(e.getX() - circleSize /2, e.getY() - circleSize /2);
				repaint();
			}
			public void mousePressed(MouseEvent e){
				if (draggedVertex != null) return;
				int rSquare = circleSize * circleSize;
				for (int i = 0; i < drawableGraph.VertexAmount(); i++){
					int x = drawableGraph.GetX(i);
					int y = drawableGraph.GetY(i);
					if ((e.getX() - x)*(e.getX() - x) + (e.getY() - y)*(e.getY() - y) <= rSquare){
						draggedVertex = i;
						repaint();
					}
				}
			}
			public void mouseReleased(MouseEvent e){
				draggedVertex = null;
			}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (draggedVertex == null) return;
				drawableGraph.changeX(draggedVertex, e.getX() - circleSize/2);
				drawableGraph.changeY(draggedVertex, e.getY() - circleSize/2);
				repaint();
			}
			@Override
			public void mouseMoved(MouseEvent e) {}
		});
	}

	public void changeCircleSize(int new_size){
		this.circleSize = new_size;
		repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g.setFont(new Font("Courier", Font.PLAIN, circleSize /3));
		g2D.setStroke(new BasicStroke(4));
		g2D.setPaint(Color.BLUE);
		for (int i = 0; i < drawableGraph.VertexAmount(); i++) {
			for (int j = 0; j < drawableGraph.GetVertexAdjacencyList(i).size(); j++){
				int x1 = drawableGraph.GetX(i) + circleSize / 2;
				int x2 = drawableGraph.GetX(drawableGraph.GetVertexAdjacencyList(i).get(j)) + circleSize / 2;
				int y1 = drawableGraph.GetY(i) + circleSize / 2;
				int y2 = drawableGraph.GetY(drawableGraph.GetVertexAdjacencyList(i).get(j)) + circleSize / 2;
				g2D.drawLine(x1, y1, x2, y2);
			}
		}
		g2D.setPaint(Color.GREEN);
		if (showBridges && drawableGraph.bridgesList != null){
			for (int i = 0; i < drawableGraph.bridgesList.size(); i++){
				int x1 = drawableGraph.GetX(drawableGraph.bridgesList.get(i)[0]) + circleSize / 2;
				int y1 = drawableGraph.GetY(drawableGraph.bridgesList.get(i)[0]) + circleSize / 2;
				int x2 = drawableGraph.GetX(drawableGraph.bridgesList.get(i)[1]) + circleSize / 2;
				int y2 = drawableGraph.GetY(drawableGraph.bridgesList.get(i)[1]) + circleSize / 2;
				g2D.drawLine(x1, y1, x2, y2);
			}
		}
		for (int i = 0; i < drawableGraph.VertexAmount(); i++){
			g2D.setPaint(new Color(60, 140, 60));
			int x = drawableGraph.GetX(i);
			int y = drawableGraph.GetY(i);
			g2D.fillOval(x- circleSize /12, y- circleSize /12, circleSize, circleSize);
			g2D.setPaint(Color.GRAY);
			g2D.fillOval(x, y, circleSize *5/6, circleSize *5/6);
			g2D.setColor(Color.RED);
			g2D.drawString(String.valueOf(i), x + circleSize /3, y + circleSize /2);
		}
	}
}