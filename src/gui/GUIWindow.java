package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import selenium.BaseClass;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Choice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JTextArea;

public class GUIWindow {

	private JFrame frame;
	private JTextField txtEnterTheUrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIWindow window = new GUIWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 540, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		BaseClass obj = new BaseClass();
		JButton button = new JButton("Open Browser");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JPanel panel = new JPanel();
		JRadioButton radioButton = new JRadioButton("Chrome");
		JRadioButton radioButton_1 = new JRadioButton("Firefox");
		JButton button_1 = new JButton("Close Browser");
		JPanel panel_1 = new JPanel();
		JButton button_2 = new JButton("Navigate");
		JButton button_3 = new JButton("Open Console");
		JComboBox<?> comboBox = new JComboBox();
		
		tabbedPane.setBounds(10, 0, 514, 500);
		frame.getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		radioButton.setSelected(true);
		radioButton.setBounds(24, 19, 101, 23);
		panel.add(radioButton);
		
		radioButton_1.setBounds(157, 19, 119, 23);
		panel.add(radioButton_1);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				obj.openChrome();
			}
		});
		button.setBounds(10, 60, 119, 23);
		panel.add(button);
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setEnabled(false);
		button_1.setBounds(273, 60, 119, 23);
		panel.add(button_1);
		
		button_2.setBounds(398, 98, 101, 23);
		panel.add(button_2);
		
		button_3.setEnabled(false);
		button_3.setBounds(140, 60, 119, 23);
		panel.add(button_3);
		
		txtEnterTheUrl = new JTextField();
		txtEnterTheUrl.setToolTipText("Enter the URL to Navigate");
		txtEnterTheUrl.setColumns(10);
		txtEnterTheUrl.setBounds(67, 99, 331, 20);
		panel.add(txtEnterTheUrl);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"http", "https"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(10, 99, 56, 20);
		panel.add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 230, 489, 231);
		panel.add(textArea);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		System.setOut(printStream);
		System.setErr(printStream);
		
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				obj.navigateToUrl(txtEnterTheUrl.getText());
			}
		});
		
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					obj.closeChrome();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
