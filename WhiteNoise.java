import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.sun.media.jfxmedia.events.PlayerEvent;

public class WhiteNoise extends JPanel implements ActionListener {
	private JButton fire = new JButton();
	private JButton coffee = new JButton();
	private JButton rain = new JButton();
	private JButton sea = new JButton();
	private JButton mute = new JButton("Mute");

	private AudioPlayer firePlayer = new AudioPlayer(new File("fire.wav"));
	private AudioPlayer seaPlayer = new AudioPlayer(new File("sea.wav"));
	private AudioPlayer coffeePlayer = new AudioPlayer(new File("coffee.wav"));
	private AudioPlayer rainPlayer = new AudioPlayer(new File("rain.wav"));

	public WhiteNoise() {
		try {
			this.add(musicControlPanel());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setOpaque(false);

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
	
	private void control(AudioPlayer player) throws IOException, LineUnavailableException, UnsupportedAudioFileException{
		
		if (player.isPlaying()) {
			player.stop();
		}
		else{
			player.play();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fire) {
			try {
				control(firePlayer);
			} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == sea) {
			try {
				control(seaPlayer);
			} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == coffee) {
			try {
				control(coffeePlayer);
			} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == rain) {
			try {
				control(rainPlayer);
			} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == mute) {
			rainPlayer.stop();
			coffeePlayer.stop();
			firePlayer.stop();
			seaPlayer.stop();
		}
	}
}