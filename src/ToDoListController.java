import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class ToDoListController extends JPanel {

	/** a control panel that holds everything **/
	JPanel controlPanel = new JPanel();
	/** a button panel that holds the Jbuttons **/
	JPanel buttonPanel = new JPanel();


	/**
	 * The constructor of the Unrestricted Game
	 * 
	 * @param JFrame
	 *            frame
	 */
	public ToDoListController() {
		

		// uses grid layout
		super(new BorderLayout());
		
		repaint();
		
		// add the text
		add(displayTime(), BorderLayout.CENTER);
		// add the controls
		//add(musicControlPane());
		add(addTaskPanel(), BorderLayout.WEST);
	}
	
	@Override
	/**
	 * The class paints the background of the panel using imported image
	 */
    protected void paintComponent(Graphics g) {
		BufferedImage background = null;
		
		//set up the image
		try {
			background = ImageIO.read(new File ("background.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		//paint the image on the panel
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
		title.setFont(new Font("ChalkboardSE", Font.BOLD, 100));
		title.setForeground(new Color(153, 204, 255));
		title.setBackground(Color.RED);

		textPanel.add(title); 
		return textPanel;
	}
	
	private JPanel addTaskPanel(){ 
		
		JPanel taskPanel = new JPanel(new GridLayout (2, 1));
		taskPanel.setOpaque(false);
		
		String todo = "To Do List";
		createListPanel(todo);
		
		String done = "Completed Tasks";
		createListPanel(done);
		
		
		taskPanel.add(createListPanel(todo));
		taskPanel.add(createListPanel(done));
		
		
		return taskPanel;
	}
	
	private JPanel createListPanel(String listType){
		JPanel panel = new JPanel(); 
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
		
		JLabel label = new JLabel(listType);
		setBorderLine(label);
		JCheckBox[] list = new JCheckBox[8];  
		panel.add(label);
		for (int i = 0; i < list.length; i ++){
			list[i] = new JCheckBox("Data Structure PSA6");
			panel.add(list[i]);
		}
		
		return panel; 
		
	}
	
	private void setBorderLine(JComponent comp){
		
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		comp.setBorder(BorderFactory.createCompoundBorder(border, 
		            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	}

	/**
	 * creates the JPanel that holds the control area
	 * 
	 * @return JPanel controlPanel
	 */
	private JPanel musicControlPane() { 
		controlPanel.setLayout(new GridLayout(1, 1));
		controlPanel.setOpaque(false);

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		JButton music = new JButton("<html><center>" + "Would you like some music?" + "</center></html>");
		music.setFont(new Font("Apple Chancery", Font.BOLD, 18));
		music.setForeground(new Color(86, 10, 0));
		//music.setBackground(new Color(206, 213, 224));
		//music.setOpaque(true);
		//music.setBorderPainted(false);
		
		music.setOpaque(false);
		music.setContentAreaFilled(false);
		music.setBorderPainted(false);
		
		music.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		buttonPanel.add(music);
		buttonPanel.setOpaque(false);
		controlPanel.add(buttonPanel);
		return controlPanel;
	}

	public void play() {
		try {
			File file = new File("disneyThemeSong.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			// Thread.sleep(clip.getMicrosecondLength());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
