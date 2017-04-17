
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		super(new GridLayout(2, 1));
		// add the text
		add(addText());
		// add the controls
		add(addControl());
	}

	/**
	 * creates the text JPanel that holds the title and the list of characters
	 * 
	 * @return JPanel textPanel
	 */
	private JPanel addText() {
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2, 1));

		JLabel title = new JLabel("To Do List");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Apple Chancery", Font.BOLD, 48));
		title.setForeground(new Color(86, 10, 0));

		textPanel.add(title);
		return textPanel;
	}

	/**
	 * creates the JPanel that holds the control area
	 * 
	 * @return JPanel controlPanel
	 */
	private JPanel addControl() {
		controlPanel.setLayout(new GridLayout(2, 1));

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		JButton music = new JButton("<html><center>" + "Would you like some music?" + "</center></html>");
		music.setFont(new Font("Apple Chancery", Font.BOLD, 18));
		music.setForeground(new Color(86, 10, 0));
		music.setBackground(new Color(206, 213, 224));
		music.setOpaque(true);
		music.setBorderPainted(false);
		music.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		buttonPanel.add(music);
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
