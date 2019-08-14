package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Properties;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class GUIWindow {

	private static Properties prop;
	private JFrame frame;
	private JTextField txtEnterTheUrl;
	private JTextField xpathTextField;
	private JTextField txtdriverchromedriverexe;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	static BaseClass obj = new BaseClass();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prop = obj.getProp();
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

		comboBox.setModel(new DefaultComboBoxModel(new String[] { "http", "https" }));
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
		comboBox_1.setModel(
				new DefaultComboBoxModel(new String[] { " click()", " sendKeys()", " clear()", " getText()" }));
		comboBox_1.setBounds(399, 151, 101, 20);
		panel.add(comboBox_1);

		JButton btnExecute = new JButton("EXECUTE");
		btnExecute.setBounds(208, 182, 89, 23);
		panel.add(btnExecute);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
			}
		});
		btnNewButton_1.setToolTipText("Clear Console");
		btnNewButton_1.setBounds(484, 215, 15, 15);
		panel.add(btnNewButton_1);

		JButton btnCopyConsoleText = new JButton("");
		btnCopyConsoleText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String textAreaText = textArea.getText();
				if (!textAreaText.equals("")) {
					StringSelection stringSelection = new StringSelection(textAreaText);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				} else{
					JOptionPane.showMessageDialog(null, "Console is empty",
							"Copy", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
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
		txtdriverchromedriverexe.setText(prop.getProperty("chromedriver"));
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
				try (OutputStream output = new FileOutputStream(System.getProperty("user.dir")+"\\configuration.ini");) {
					prop.setProperty("chromedriver", txtdriverchromedriverexe.getText());
					prop.store(output, null);
					JOptionPane.showMessageDialog(null, "Save was Successful", "Configuration" + " setting",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Save was Unsuccessful : \n" + ex.getMessage(),
							"Configuration" + " setting", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSaveConfiguration.setBounds(90, 190, 150, 23);
		panel_1.add(btnSaveConfiguration);

		JButton btnNewButton = new JButton("Reset Default");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String driverPath = System.getProperty("user.dir");
				txtdriverchromedriverexe.setText(driverPath+"\\driver\\chromedriver.exe");
				try (OutputStream output = new FileOutputStream(System.getProperty("user.dir")+"\\configuration.ini");) {
					prop.setProperty("chromedriver", driverPath+"\\driver\\chromedriver.exe");
					prop.store(output, null);
					JOptionPane.showMessageDialog(null, "Reset was Successful", "Configuration" + " setting",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Reset was Unsuccessful : \n" + ex.getMessage(),
							"Configuration" + " setting", JOptionPane.INFORMATION_MESSAGE);
				}
			
			}
		});
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

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
