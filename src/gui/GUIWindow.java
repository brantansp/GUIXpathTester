package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import selenium.BaseClass;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIWindow {

	private static Properties prop;
	private JFrame frame;
	private JTextField txtEnterTheUrl;
	private JTextField xpathTextField;
	private JTextField txtdriverchromedriverexe;
	private JTextField txtdriverfirefoxdriverexe;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	static BaseClass obj = new BaseClass();
	private JTextField sendKeysText;

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
		frame.setBounds(100, 100, 640, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton openBrowserBtn = new JButton("Open Browser");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JPanel panel = new JPanel();
		JRadioButton chromeRadioButton = new JRadioButton("Chrome");
		buttonGroup.add(chromeRadioButton);
		JRadioButton firefoxRadioButton = new JRadioButton("Firefox");
		buttonGroup.add(firefoxRadioButton);
		JButton closeBrowserBtn = new JButton("Close Browser");
		JPanel panel_1 = new JPanel();
		JButton navigateBtn = new JButton("Navigate");
		navigateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		navigateBtn.setEnabled(false);
		JButton openConsoleBtn = new JButton("Open Console");
		JComboBox<String> httpComboBox = new JComboBox<String>();
		JComboBox<String> actionsDropdown = new JComboBox<String>();
		JComboBox<String> jsActionDropdown = new JComboBox<String>();
		actionsDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("sendKeys()")) {
					sendKeysText.setVisible(true);
					sendKeysText.setEnabled(true);
				} else {
					sendKeysText.setVisible(false);
					sendKeysText.setEnabled(false);
				}
			}
		});
		JButton executeBtn = new JButton("EXECUTE");
		JLabel lblXpath = new JLabel("XPath :");
		JButton btnNewButton_1 = new JButton("");
		
		executeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (executeBtn.isEnabled()) {
					String action = (String) actionsDropdown.getSelectedItem();
					String xpath = xpathTextField.getText();
					String text = sendKeysText.getText();
					switch (action) {
					case "click()":
						obj.click(xpath);
						break;
					case "clickAll()":
						obj.clickAll(xpath);
						break;
					case "sendKeys()":
						obj.sendKeys(xpath, text);
						break;
					case "clear()":
						obj.clear(xpath);
						break;
					case "submit()":
						obj.submit(xpath);
						break;
					case "getText()":
						obj.getText(xpath);
						break;
					case "isDisplayed()":
						obj.isDisplayed(xpath);
						break;
					case "isSelected()":
						obj.isSelected(xpath);
						break;
					case "isVisible()":
						obj.isDisplayed(xpath);
						break;
					}
				}
			}
		});

		httpComboBox.setEnabled(false);

		tabbedPane.setBounds(10, 0, 614, 600);
		frame.getContentPane().add(tabbedPane);

		tabbedPane.addTab("Action", null, panel, null);
		panel.setLayout(null);

		chromeRadioButton.setSelected(true);
		chromeRadioButton.setBounds(24, 19, 101, 23);
		panel.add(chromeRadioButton);

		firefoxRadioButton.setBounds(157, 19, 119, 23);
		panel.add(firefoxRadioButton);

		openBrowserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (openBrowserBtn.isEnabled()) {
					if (chromeRadioButton.isSelected()) {
						obj.openChrome();
					} else if (firefoxRadioButton.isSelected()) {
						obj.openFirefox();
					}
					closeBrowserBtn.setEnabled(true);
					openConsoleBtn.setEnabled(true);
					openBrowserBtn.setEnabled(false);
					httpComboBox.setEnabled(true);
					txtEnterTheUrl.setEnabled(true);
					navigateBtn.setEnabled(true);
					xpathTextField.setEnabled(true);
					actionsDropdown.setEnabled(true);
					executeBtn.setEnabled(true);
					jsActionDropdown.setEnabled(true);
				}
			}
		});
		openBrowserBtn.setBounds(10, 60, 119, 23);
		panel.add(openBrowserBtn);

		closeBrowserBtn.setEnabled(false);
		closeBrowserBtn.setBounds(273, 60, 119, 23);
		panel.add(closeBrowserBtn);

		navigateBtn.setBounds(398, 206, 101, 23);
		panel.add(navigateBtn);

		openConsoleBtn.setEnabled(false);
		openConsoleBtn.setBounds(140, 60, 119, 23);
		panel.add(openConsoleBtn);

		txtEnterTheUrl = new JTextField();
		txtEnterTheUrl.setEnabled(false);
		txtEnterTheUrl.setToolTipText("Enter the URL to Navigate");
		txtEnterTheUrl.setColumns(10);
		txtEnterTheUrl.setBounds(67, 207, 331, 20);
		panel.add(txtEnterTheUrl);

		httpComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "http", "https" }));
		httpComboBox.setSelectedIndex(0);
		httpComboBox.setBounds(10, 207, 56, 20);
		panel.add(httpComboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 333, 589, 228);
		panel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));

		xpathTextField = new JTextField();
		xpathTextField.setEnabled(false);
		xpathTextField.setBounds(67, 235, 331, 20);
		panel.add(xpathTextField);
		xpathTextField.setColumns(10);

		lblXpath.setBounds(10, 238, 46, 14);
		panel.add(lblXpath);

		actionsDropdown.setEnabled(false);
		actionsDropdown.setModel(new DefaultComboBoxModel<String>(new String[] { "click()", "clickAll()", "sendKeys()", "clear()",
				"submit()", "getText()", "isDisplayed()", "isSelected()", "isVisible()" }));
		actionsDropdown.setBounds(399, 235, 101, 20);
		panel.add(actionsDropdown);

		executeBtn.setEnabled(false);
		executeBtn.setBounds(208, 290, 89, 23);
		panel.add(executeBtn);

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
			}
		});
		btnNewButton_1.setToolTipText("Clear Console");
		btnNewButton_1.setBounds(582, 313, 15, 15);
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
				} else {
					JOptionPane.showMessageDialog(null, "Console is empty", "Copy", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCopyConsoleText.setToolTipText("Copy console text");
		btnCopyConsoleText.setBounds(568, 313, 15, 15);
		panel.add(btnCopyConsoleText);
		
		sendKeysText = new JTextField();
		sendKeysText.setEnabled(false);
		sendKeysText.setBounds(67, 267, 131, 20);
		sendKeysText.setVisible(false);
		panel.add(sendKeysText);
		sendKeysText.setColumns(10);
		
		jsActionDropdown.setModel(new DefaultComboBoxModel(new String[] {"click()", "clickAll()", "clear()", "enterInnerHTML()", "enterValue()", "focus()", "getAttribute()", "getTagName()", "getText()", "isSelected()", "scrollUntilElement()"}));
		jsActionDropdown.setEnabled(false);
		jsActionDropdown.setBounds(398, 267, 101, 20);
		panel.add(jsActionDropdown);
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

		txtdriverfirefoxdriverexe = new JTextField();
		txtdriverfirefoxdriverexe.setText(prop.getProperty("firefoxdriver"));
		txtdriverfirefoxdriverexe.setBounds(10, 134, 489, 20);
		panel_1.add(txtdriverfirefoxdriverexe);
		txtdriverfirefoxdriverexe.setColumns(10);

		JButton btnSaveConfiguration = new JButton("Save Configuration");
		btnSaveConfiguration.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try (OutputStream output = new FileOutputStream(
						System.getProperty("user.dir") + "\\configuration.ini");) {
					if (txtdriverchromedriverexe.getText().contains(".exe")
							&& txtdriverfirefoxdriverexe.getText().contains(".exe")) {
						prop.setProperty("chromedriver", txtdriverchromedriverexe.getText());
						prop.setProperty("firefoxdriver", txtdriverfirefoxdriverexe.getText());
						prop.store(output, null);
						JOptionPane.showMessageDialog(null, "Save was Successful", "Configuration" + " setting",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Does the Path contains .exe executable. \nPlease point to .exe file or try Reset Default",
								"Configuration" + " setting", JOptionPane.QUESTION_MESSAGE);
					}
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
				txtdriverchromedriverexe.setText(driverPath + "\\driver\\chromedriver.exe");
				txtdriverfirefoxdriverexe.setText(driverPath + "\\driver\\geckodriver.exe");
				try (OutputStream output = new FileOutputStream(
						System.getProperty("user.dir") + "\\configuration.ini");) {
					prop.setProperty("chromedriver", driverPath + "\\driver\\chromedriver.exe");
					prop.setProperty("firefoxdriver", driverPath + "\\driver\\geckodriver.exe");
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

		navigateBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (navigateBtn.isEnabled()) {
					obj.navigateToUrl(txtEnterTheUrl.getText(), "http");
				}
			}
		});

		closeBrowserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (closeBrowserBtn.isEnabled()) {
					try {
						obj.closeChrome();
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						closeBrowserBtn.setEnabled(false);
						openConsoleBtn.setEnabled(false);
						openBrowserBtn.setEnabled(true);
						httpComboBox.setEnabled(false);
						txtEnterTheUrl.setEnabled(false);
						navigateBtn.setEnabled(false);
						xpathTextField.setEnabled(false);
						actionsDropdown.setEnabled(false);
						executeBtn.setEnabled(false);
						sendKeysText.setVisible(false);
						sendKeysText.setEnabled(false);
					}
					
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
