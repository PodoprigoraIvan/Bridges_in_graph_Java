package com.github.podoprigoraivan.swingapp.graphics;

import com.github.podoprigoraivan.swingapp.SaveAndLoad.Loader;
import com.github.podoprigoraivan.swingapp.SaveAndLoad.Saver;
import com.github.podoprigoraivan.swingapp.algorithm.BridgesFinder;
import com.github.podoprigoraivan.swingapp.graph.DrawableGraph;
import com.github.podoprigoraivan.swingapp.random.RandomGraphGenerator;
import org.json.JSONException;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class AppFrame extends JFrame {
	private GraphDrawPanel panel;
	private JSlider slider;
	private JLabel changeSizeLabel;
	private JLabel addEdgeLabel;
	private JLabel incorrectInputLabel;
	private JSpinner firstVertex;
	private JSpinner secondVertex;
	private JButton addEdgeButton;
	private JButton deleteGraphButton;
	private JSpinner maxVertices;
	private JSpinner maxEdges;
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
		this.setMinimumSize(new Dimension(1300, 400));
		this.setSize(1300, 800);
		this.setLayout(new GridBagLayout());
		
		barMenu = new JMenuBar();
		menu = new JMenu("Файл");
		
		saveFileMenuItem = new JMenuItem("Сохранить граф в файл");
		saveFileMenuItem.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showSaveDialog(this);
			File file = fileChooser.getSelectedFile();
			Saver saver = new Saver(drawableGraph, file);
			saver.save();
			panel.repaint();
			incorrectInputLabel.setText("");
		});

		loadFileMenuItem = new JMenuItem("Загрузить граф из файла");
		loadFileMenuItem.addActionListener(e -> {
			try {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(this);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.getSelectedFile();
				File file = fileChooser.getSelectedFile();
				Loader loader = new Loader(drawableGraph, file);
				loader.load();
				panel.repaint();
				incorrectInputLabel.setText("");
			} catch (Exception exception) {
				incorrectInputLabel.setText("Неверный формат файла!");
			}
		});
		
		menu.add(saveFileMenuItem);
		menu.add(loadFileMenuItem);
		barMenu.add(menu);
		this.setJMenuBar(barMenu);
		
		slider = new JSlider(30, 200, 50);
		changeSizeLabel = new JLabel("Размер вершин ");
		addEdgeLabel = new JLabel("Вершины добавляемого ребра: ");
		slider.addChangeListener(e -> {
			int value = ((JSlider)e.getSource()).getValue();
			panel.changeCircleSize(value);
		});

		maxVertices = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		maxEdges = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		firstVertex = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		secondVertex = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		maxVertices.setValue(10);
		maxEdges.setValue(10);

		addEdgeButton = new JButton("Добавить ребро");
		addEdgeButton.addActionListener(e -> {
			drawableGraph.addEdge((int)firstVertex.getValue(), (int)secondVertex.getValue());
			drawableGraph.bridgesList = null;
			panel.showBridges = false;
			panel.repaint();
		});
		
		deleteGraphButton = new JButton("Очистить граф");
		deleteGraphButton.addActionListener(e -> {
			drawableGraph.clear();
			panel.repaint();
		});

		generateGraphButton = new JButton("Сгенерировать случайный граф");
		generateGraphButton.addActionListener(e -> {
			RandomGraphGenerator generator = new RandomGraphGenerator(drawableGraph, (int)maxVertices.getValue(), (int)maxEdges.getValue(), (int) (panel.getSize().getHeight() * 0.9), (int) (panel.getSize().getWidth() * 0.9));
			generator.generate();
			panel.repaint();
		});
		
		showBridgesButton = new JButton("Показать мосты");
		showBridgesButton.setBackground(Color.GREEN);
		showBridgesButton.setOpaque(true);
		showBridgesButton.addActionListener(e -> {
			BridgesFinder bridgesFinder = new BridgesFinder(drawableGraph);
			drawableGraph.bridgesList = bridgesFinder.findBridges();
			panel.showBridges = true;
			panel.repaint();
		});

		maxVerticesLabel = new JLabel("Максимальное число вершин: ");
		maxEdgesLabel = new JLabel("Максимальное число рёбер: ");
		incorrectInputLabel = new JLabel("");
		incorrectInputLabel.setForeground(Color.RED);
		
		this.add(deleteGraphButton, new GridBagConstraints(0,0,1,1,0,0.05, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,2,0,0), 0,0));
		this.add(changeSizeLabel, new GridBagConstraints(1,0,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1,2,0,0), 0,0));
		this.add(slider, new GridBagConstraints(2,0,1,1,0.1,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		this.add(addEdgeLabel, new GridBagConstraints(3,0,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		this.add(firstVertex, new GridBagConstraints(5,0,1,1,0.5,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		this.add(secondVertex, new GridBagConstraints(7,0,1,1,0.5,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		this.add(addEdgeButton, new GridBagConstraints(9,0,1,1,0.4,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		this.add(maxVerticesLabel, new GridBagConstraints(0,1,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		this.add(maxVertices, new GridBagConstraints(1,1,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		this.add(maxEdgesLabel, new GridBagConstraints(0,2,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		this.add(maxEdges, new GridBagConstraints(1,2,1,1,0.1,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		this.add(generateGraphButton, new GridBagConstraints(2,1,1,2,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,2,0,0), 0,0));
		this.add(showBridgesButton, new GridBagConstraints(3,1,1,2,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,2,0,0), 0,0));
		this.add(incorrectInputLabel, new GridBagConstraints(0,3,1,1,0,0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0));
		this.add(panel, new GridBagConstraints(0,4,10,10,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0));
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}