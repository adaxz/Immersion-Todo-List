import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * 
 * This class is basically a JPanel that holds my unrestricted 20 questions game
 * with the ability to add new characters that the user thinks of
 * 
 * @author Kuan Chi Chen
 *
 */
@SuppressWarnings({ "serial", "restriction" })
public class ToDoListView extends JPanel implements ActionListener {
	private JButton fire = new JButton();
	private JButton coffee = new JButton();
	private JButton rain = new JButton();
	private JButton sea = new JButton();
	private JButton mute = new JButton("Mute");
	private Clip clip;

	private Color titleColor = new Color(153, 204, 255);

	private static final int BORDER_STROKE = 5;

	private boolean firePlayed = false;

	/**
	 * List of all the image files to load.
	 */
	//private String[] imageFileNames = { "firecamp_icon.png", "rain_icon.png" };

	//private static final int ICON_SIZE = 50;

	/**
	 * The constructor of the Unrestricted Game
	 * 
	 * @param JFrame
	 *            frame
	 * @throws IOException 
	 */
	public ToDoListView() throws IOException {

		// uses grid layout
		super(new BorderLayout());

		repaint();

		// add the text
		add(displayTime(), BorderLayout.CENTER);
		// add the controls
		// add(musicControlPane());
		add(addTaskPanel(), BorderLayout.WEST);

	}

	@Override
	/**
	 * The class paints the background of the panel using imported image
	 */
	protected void paintComponent(Graphics g) {
		BufferedImage background = null;

		// set up the image
		try {
			background = ImageIO.read(new File("background.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		// paint the image on the panel
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
	}

	/**
	 * creates the text JPanel that holds the title and the list of characters
	 * 
	 * @return JPanel textPanel
	 */
	private JPanel displayTime() {
		JPanel textPanel = new JPanel();
		textPanel.setOpaque(false);
		textPanel.setLayout(new GridLayout(2, 1));

		JLabel title = new JLabel("17:00");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("ChalkboardSE", Font.BOLD, 130));
		title.setForeground(titleColor);

		textPanel.add(title);
		try {
			textPanel.add(musicControlPanel());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return textPanel;
	}

	private JPanel addTaskPanel() throws IOException {

		JPanel taskPanel = new JPanel(new GridLayout(2, 1));
		taskPanel.setPreferredSize(new Dimension(350, 1000));

		taskPanel.setOpaque(false);



		taskPanel.add(createListPanel());
		taskPanel.add(createDonePanel());

		return taskPanel;
	}

	private JPanel createListPanel() throws IOException{
		
		JPanel listPanel = new JPanel();
		listPanel.setOpaque(false);
		listPanel.setLayout(new GridLayout(8, 1)); 



		JButton addTask = new JButton("Add Tasks");
		addTask.setFont(new Font("Century Gothic", Font.BOLD, 18));
		addTask.setForeground(new Color(115, 115, 115));
		addTask.setBackground(new Color(242, 242, 242));
		addTask.setOpaque(true);
		addTask.setBorderPainted(false);

		JLabel label = new JLabel("To-Do List", SwingConstants.CENTER);
		label.setFont(new Font("Chalkduster", Font.BOLD, 24));
		label.setForeground(titleColor);



		JPanel[] oneTask = new JPanel[10];
		for (int i = 0; i < oneTask.length; i ++){
			oneTask[i] = new JPanel(new BorderLayout());
			oneTask[i].setOpaque(false); 
		}

		JCheckBox[] list = new JCheckBox[8];

		for (int i = 0; i < list.length; i++) {
			list[i] = new JCheckBox("Data Structure PSA6");
			list[i].setFont(new Font("Century Gothic", Font.BOLD, 18));
			list[i].setForeground(new Color(115, 115, 115));
			oneTask[i].add(list[i], BorderLayout.CENTER);
			
			listPanel.add(oneTask[i]);
		}
		
		Image trash = ImageIO.read(new File("trashbinIcon.png"));
		
		JButton[] deleteButtons = new JButton[8];
		
		for (int i = 0; i < deleteButtons.length; i ++){
			deleteButtons[i] = new JButton();
			deleteButtons[i].setIcon(new ImageIcon(trash));
			deleteButtons[i].setOpaque(false);
			deleteButtons[i].setContentAreaFilled(false);
			deleteButtons[i].setBorderPainted(false);
			oneTask[i].add(deleteButtons[i], BorderLayout.EAST);
		}

		JPanel panel = new JPanel (new BorderLayout()); 
		setBorderLine(panel);
		panel.setOpaque(false);
		panel.add(label, BorderLayout.NORTH);
		panel.add(listPanel, BorderLayout.CENTER);
		panel.add(addTask, BorderLayout.SOUTH);

		return panel; 

	}

	private JPanel createDonePanel(){
		JPanel listPanel = new JPanel();
		listPanel.setOpaque(false);
		listPanel.setLayout(new GridLayout(8, 1)); 

		JLabel label = new JLabel("Completed Tasks", SwingConstants.CENTER);
		label.setFont(new Font("Chalkduster", Font.BOLD, 24));
		label.setForeground(titleColor);

		JCheckBox[] list = new JCheckBox[8];


		for (int i = 0; i < list.length; i++) {
			list[i] = new JCheckBox("Data Structure PSA6");
			list[i].setFont(new Font("Century Gothic", Font.BOLD, 18));
			list[i].setForeground(new Color(115, 115, 115));
			listPanel.add(list[i]);
		}

		JPanel panel = new JPanel (new BorderLayout()); 
		setBorderLine(panel);
		panel.setOpaque(false);
		panel.add(label, BorderLayout.NORTH);
		panel.add(listPanel, BorderLayout.CENTER);

		return panel; 
	}

	private void setBorderLine(JComponent comp) {

		Border border = BorderFactory. createMatteBorder(BORDER_STROKE,BORDER_STROKE,BORDER_STROKE/2,BORDER_STROKE,titleColor);

		comp.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	}

	/**
	 * creates the JPanel that holds the control area
	 * 
	 * @return JPanel controlPanel
	 * @throws IOException
	 */
	private JToolBar musicControlPanel() throws IOException {

		JToolBar buttonBar = new JToolBar();
		buttonBar.setOpaque(false);
		buttonBar.add(Box.createGlue());

		JButton[] buttons = { fire, coffee, rain, sea, mute };

		Image fireImage = ImageIO.read(new File("fireIcon.png"));
		Image coffeeImage = ImageIO.read(new File("coffee.png"));
		Image rainImage = ImageIO.read(new File("rainIcon.png"));
		Image seaImage = ImageIO.read(new File("sea.png"));

		fire.setIcon(new ImageIcon(fireImage));
		coffee.setIcon(new ImageIcon(coffeeImage));
		rain.setIcon(new ImageIcon(rainImage));
		sea.setIcon(new ImageIcon(seaImage));

		for (JButton button : buttons) {
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.addActionListener(this);
			buttonBar.add(button);
		}

		buttonBar.add(Box.createGlue());
		return buttonBar;
	}

	// /**
	// * Resizes an image using a Graphics2D object backed by a BufferedImage.
	// *
	// * @param srcImg
	// * - source image to scale
	// * @param w
	// * - desired width
	// * @param h
	// * - desired height
	// * @return - the new resized image
	// */
	// private Image getScaledImage(Image srcImg, int w, int h) {
	// BufferedImage resizedImg = new BufferedImage(w, h,
	// BufferedImage.TYPE_INT_RGB);
	// Graphics2D g2 = resizedImg.createGraphics();
	// g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	// RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	// g2.drawImage(srcImg, 0, 0, w, h, null);
	// g2.dispose();
	// return resizedImg;
	// }

	public void play(String filename) {
		try {
			File file = new File(filename);
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			// Thread.sleep(clip.getMicrosecondLength());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fire) {
			if (!firePlayed){
				play("fire.wav");
				firePlayed = true;
			}
			else{
				clip.stop();
				firePlayed = false;
			}
		}
		if (e.getSource() == sea) {
			play("sea.wav");
		}
		if (e.getSource() == coffee) {
			play("coffee.wav");
		}
		if (e.getSource() == rain) {
			play("rain.mp3");
		}
		if (e.getSource() == mute) {
			clip.stop();
		}
	}

}
