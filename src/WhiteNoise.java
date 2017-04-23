import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class WhiteNoise extends JPanel implements ActionListener {
	private JButton fire = new JButton();
	private JButton coffee = new JButton();
	private JButton rain = new JButton();
	private JButton sea = new JButton();
	private JButton mute = new JButton("Mute");
	private Clip clip;

	private boolean firePlayed;
	private boolean seaPlayed;
	private boolean coffeePlayed;
	private boolean rainPlayed;

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
			if (!firePlayed) {
				play("fire.wav");
				firePlayed = true;
			} else {
				clip.stop();
				firePlayed = false;
			}
		}
		if (e.getSource() == sea) {
			if (!seaPlayed) {
				play("sea.wav");
				seaPlayed = true;
			} else {
				clip.stop();
				seaPlayed = false;
			}

		}
		if (e.getSource() == coffee) {
			if (!coffeePlayed) {
				play("coffee.wav");
				coffeePlayed = true;
			} else {
				clip.stop();
				coffeePlayed = false;
			}

		}
		if (e.getSource() == rain) {
			if (!rainPlayed) {
				play("rain.wav");
				rainPlayed = true;
			} else {
				clip.stop();
				rainPlayed = false;
			}
		}
		if (e.getSource() == mute) {
			clip.stop();
		}
	}
}