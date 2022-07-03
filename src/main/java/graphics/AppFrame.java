package graphics;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class AppFrame extends JFrame {
	
	DrawPanel panel;
	JSlider slider;
	JLabel change_size_label;
	JLabel add_edge_label;
	JTextField first_vertex;
	JTextField second_vertex;
	JButton add_edge_button;
	JButton delete_graph_button;
	JTextField max_vertices;
	JTextField max_edges;
	JLabel max_vertices_label;
	JLabel max_edges_label;
	JButton generate_graph_button;
	JButton show_bridges_button;
	JMenuBar menu_bar;
	JMenu menu;
	JMenuItem save_file_menu_item;
	JMenuItem load_file_menu_item;
	
	public AppFrame(){
		super("Finding bridges in graph application");
		panel = new DrawPanel();
		panel.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 800);
		this.setLayout(new GridBagLayout());
		
		menu_bar = new JMenuBar();
		menu = new JMenu("Файл");
		save_file_menu_item = new JMenuItem("Сохранить граф в файл");
		load_file_menu_item = new JMenuItem("Загрузить граф из файла");
		menu.add(save_file_menu_item);
		menu.add(load_file_menu_item);
		menu_bar.add(menu);
		this.setJMenuBar(menu_bar);
		
		slider = new JSlider(30, 200, 50);
		change_size_label = new JLabel("Размер вершин ");
		add_edge_label = new JLabel("Вершины добавляемого ребра: ");
		slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // изменение отображаемого размера вершин графа
                int value = ((JSlider)e.getSource()).getValue();
                panel.changeCircleSize(value);
            }
        });
		
		first_vertex = new JTextField();
		second_vertex = new JTextField();
		JTextField max_vertices = new JTextField();
		JTextField max_edges = new JTextField();
		
		add_edge_button = new JButton("Добавить ребро");
		add_edge_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.addEdge(first_vertex.getText(), second_vertex.getText());
			}
		});
		
		delete_graph_button = new JButton("Очистить граф");
		delete_graph_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.clear();
			}
		});
		
		generate_graph_button = new JButton("Сгенерировать случайный граф");
		generate_graph_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.generateGraph(max_vertices.getText(), max_edges.getText());
			}
		});
		
		show_bridges_button = new JButton("Найти мосты");
		show_bridges_button.setBackground(Color.GREEN);
		show_bridges_button.setOpaque(true);
		show_bridges_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.showBridges();
			}
		});

		max_vertices_label = new JLabel("Максимальное число вершин: ");
		max_edges_label = new JLabel("Максимальное число рёбер: ");		
		
		this.add(delete_graph_button, new GridBagConstraints(0,0,1,1,0,0.05, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,2,0,0), 0,0));
		
		this.add(change_size_label, new GridBagConstraints(1,0,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1,2,0,0), 0,0));
		
		this.add(slider, new GridBagConstraints(2,0,2,1,0.2,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(add_edge_label, new GridBagConstraints(4,0,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(first_vertex, new GridBagConstraints(5,0,1,1,0.1,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(second_vertex, new GridBagConstraints(6,0,1,1,0.1,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(add_edge_button, new GridBagConstraints(7,0,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(max_vertices_label, new GridBagConstraints(0,1,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(max_vertices, new GridBagConstraints(1,1,1,1,0,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(max_edges_label, new GridBagConstraints(0,2,1,1,0,0.1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(max_edges, new GridBagConstraints(1,2,1,1,0.1,0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
		
		this.add(generate_graph_button, new GridBagConstraints(2,1,1,2,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,2,0,0), 0,0));
		
		this.add(show_bridges_button, new GridBagConstraints(3,1,1,2,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,2,0,0), 0,0));
		
		this.add(panel, new GridBagConstraints(0,3,10,10,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0));
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
}