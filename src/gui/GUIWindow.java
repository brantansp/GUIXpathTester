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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

public class GUIWindow {

	private JFrame frame;
	private JTextField txtEnterTheUrl;
	private JTextField xpathTextField;
	private JTextField txtdriverchromedriverexe;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIWindow window = new GUIWindow();
					window.frame.setVisible(true);
					File tmpDir = new File(System.getProperty("user.dir")+"\\configuration.ini");
					boolean exists = tmpDir.exists();
					if (!exists) {
						Writer writer = null;
						try {
						    writer = new BufferedWriter(new OutputStreamWriter(
						          new FileOutputStream(System.getProperty("user.dir")+"\\configuration.ini"), "utf-8"));
						    writer.write("chromedriver=C:\\Users\\pl58641\\EclipseNeonSE\\driver\\chromedriver.exe");
						} catch (IOException ex) {

						} finally {
						   try {writer.close();} catch (Exception ex) {/*ignore*/}
						}
					}
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
		buttonGroup.add(radioButton);
		JRadioButton radioButton_1 = new JRadioButton("Firefox");
		buttonGroup.add(radioButton_1);
		JButton button_1 = new JButton("Close Browser");
		JPanel panel_1 = new JPanel();
		JButton button_2 = new JButton("Navigate");
		JButton button_3 = new JButton("Open Console");
		JComboBox<?> comboBox = new JComboBox();
		
		tabbedPane.setBounds(10, 0, 514, 500);
		frame.getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("Action", null, panel, null);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 230, 489, 231);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		
		xpathTextField = new JTextField();
		xpathTextField.setBounds(67, 151, 331, 20);
		panel.add(xpathTextField);
		xpathTextField.setColumns(10);
		
		JLabel lblXpath = new JLabel("XPath :");
		lblXpath.setBounds(10, 154, 46, 14);
		panel.add(lblXpath);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {" click()", " sendKeys()", " clear()", " getText()"}));
		comboBox_1.setBounds(399, 151, 101, 20);
		panel.add(comboBox_1);
		
		JButton btnExecute = new JButton("EXECUTE");
		btnExecute.setBounds(208, 182, 89, 23);
		panel.add(btnExecute);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Clear Console");
		btnNewButton_1.setBounds(484, 215, 15, 15);
		panel.add(btnNewButton_1);
		
		JButton btnCopyConsoleText = new JButton("");
		btnCopyConsoleText.setToolTipText("Copy console text");
		btnCopyConsoleText.setBounds(470, 215, 15, 15);
		panel.add(btnCopyConsoleText);
		System.setOut(printStream);
		System.setErr(printStream);
		
		tabbedPane.addTab("Configuration", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblChromeDriverExecutable = new JLabel("Chrome Driver executable path : ");
		lblChromeDriverExecutable.setBounds(10, 42, 195, 14);
		panel_1.add(lblChromeDriverExecutable);
		
		txtdriverchromedriverexe = new JTextField();
		txtdriverchromedriverexe.setText(System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
		txtdriverchromedriverexe.setBounds(10, 67, 489, 20);
		panel_1.add(txtdriverchromedriverexe);
		txtdriverchromedriverexe.setColumns(10);
		
		JLabel lblFirefoxDriverExecutable = new JLabel("Firefox Driver executable path : ");
		lblFirefoxDriverExecutable.setBounds(10, 109, 195, 14);
		panel_1.add(lblFirefoxDriverExecutable);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 134, 489, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSaveConfiguration = new JButton("Save Configuration");
		btnSaveConfiguration.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(getChromeDriverPath());
			}
		});
		btnSaveConfiguration.setBounds(90, 190, 150, 23);
		panel_1.add(btnSaveConfiguration);
		
		JButton btnNewButton = new JButton("Reset Default");
		btnNewButton.setBounds(276, 190, 150, 23);
		panel_1.add(btnNewButton);
		
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
	
	public String getChromeDriverPath() {
		return txtdriverchromedriverexe.getText();
	}
}
