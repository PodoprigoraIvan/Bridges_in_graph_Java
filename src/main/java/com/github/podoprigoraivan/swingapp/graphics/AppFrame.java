package com.github.podoprigoraivan.swingapp.graphics;

import com.github.podoprigoraivan.swingapp.algorithm.BridgesFinder;
import com.github.podoprigoraivan.swingapp.graph.DrawableGraph;
import com.github.podoprigoraivan.swingapp.random.RandomGraphGenerator;

import java.awt.*;
import javax.swing.*;

public class AppFrame extends JFrame {
	private GraphDrawPanel panel;
	private JSlider slider;
	private JLabel changeSizeLabel;
	private JLabel addEdgeLabel;
	private JTextField firstVertex;
	private JTextField secondVertex;
	private JButton addEdgeButton;
	private JButton deleteGraphButton;
	private JTextField maxVertices;
	private JTextField maxEdges;
	private JLabel maxVerticesLabel;
	private JLabel maxEdgesLabel;
	private JButton generateGraphButton;
	private JButton showBridgesButton;
	private JMenuBar barMenu;
	private JMenu menu;
	private JMenuItem saveFileMenuItem;
	private JMenuItem loadFileMenuItem;
	
	public AppFrame(){
		super("Finding bridges in graph application");
		DrawableGraph drawableGraph = new DrawableGraph();
		panel = new GraphDrawPanel(drawableGraph);
		panel.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 800);
		this.setLayout(new GridBagLayout());
		
		barMenu = new JMenuBar();
		menu = new JMenu("Файл");
		saveFileMenuItem = new JMenuItem("Сохранить граф в файл");
		loadFileMenuItem = new JMenuItem("Загрузить граф из файла");
		menu.add(saveFileMenuItem);
		menu.add(loadFileMenuItem);
		barMenu.add(menu);
		this.setJMenuBar(barMenu);
		
		slider = new JSlider(30, 200, 50);
		changeSizeLabel = new JLabel("Размер вершин ");
		addEdgeLabel = new JLabel("Вершины добавляемого ребра: ");
		slider.addChangeListener(e -> {
			// изменение отображаемого размера вершин графа
			int value = ((JSlider)e.getSource()).getValue();
			panel.changeCircleSize(value);
		});
		
		firstVertex = new JTextField();
		secondVertex = new JTextField();
		maxVertices = new JTextField("10");
		maxEdges = new JTextField("10");
		
		addEdgeButton = new JButton("Добавить ребро");
		addEdgeButton.addActionListener(e -> {
			drawableGraph.AddEdge(Integer.parseInt(firstVertex.getText()), Integer.parseInt(secondVertex.getText()));
			drawableGraph.bridgesList = null;
			panel.repaint();
		});
		
		deleteGraphButton = new JButton("Очистить граф");
		deleteGraphButton.addActionListener(e -> {
			drawableGraph.clear();
			panel.repaint();
		});
		
		generateGraphButton = new JButton("Сгенерировать случайный граф");
		generateGraphButton.addActionListener(e -> {
			RandomGraphGenerator generator = new RandomGraphGenerator(drawableGraph, Integer.parseInt(maxVertices.getText()), Integer.parseInt(maxEdges.getText()), (int)(panel.getSize().getHeight() * 0.9), (int)(panel.getSize().getWidth() * 0.9));
			generator.Generate();
			panel.repaint();
		});
		
		showBridgesButton = new JButton("Показать мосты");
		showBridgesButton.setBackground(Color.GREEN);
		showBridgesButton.setOpaque(true);
		showBridgesButton.addActionListener(e -> {
			BridgesFinder bridgesFinder = new BridgesFinder(drawableGraph);
			drawableGraph.bridgesList = bridgesFinder.FindBridges();
			panel.ToggleShowBridges();
			panel.repaint();
		});

		maxVerticesLabel = new JLabel("Максимальное число вершин: ");
		maxEdgesLabel = new JLabel("Максимальное число рёбер: ");
		
		this.add(deleteGraphButton, new GridBagConstraints(0,0,1,1,0,0.05, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,2,0,0), 0,0));
		
		this.add(changeSizeLabel, new GridBagConstraints(1,0,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1,2,0,0), 0,0));
		
		this.add(slider, new GridBagConstraints(2,0,2,1,0.2,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(addEdgeLabel, new GridBagConstraints(4,0,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(firstVertex, new GridBagConstraints(5,0,1,1,0.1,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(secondVertex, new GridBagConstraints(6,0,1,1,0.1,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(addEdgeButton, new GridBagConstraints(7,0,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(maxVerticesLabel, new GridBagConstraints(0,1,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(maxVertices, new GridBagConstraints(1,1,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(maxEdgesLabel, new GridBagConstraints(0,2,1,1,0,0.1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(maxEdges, new GridBagConstraints(1,2,1,1,0.1,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(generateGraphButton, new GridBagConstraints(2,1,1,2,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,2,0,0), 0,0));
		
		this.add(showBridgesButton, new GridBagConstraints(3,1,1,2,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,2,0,0), 0,0));
		
		this.add(panel, new GridBagConstraints(0,3,10,10,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0));
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}