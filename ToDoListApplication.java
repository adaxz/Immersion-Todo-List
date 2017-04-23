import java.io.IOException;

import javax.swing.JFrame;

public class ToDoListApplication {

	/** the width of the frame **/
	public static int FRAME_WIDTH = 1500;
	/** the height of the frame **/
	public static int FRAME_HEIGHT = 1000;
	// static BufferedImage img;

	public static void main(String[] args) throws IOException {

		JFrame frame = new JFrame("To Do List");

		// try {
		// img = ImageIO.read(new File("background.png"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// Image dimg = img.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT,
		// Image.SCALE_SMOOTH);
		// ImageIcon imageIcon = new ImageIcon(dimg);
		// JLabel label = new JLabel(imageIcon);
		// label.setLayout(new FlowLayout());
		// label.setOpaque(true);
		//
		// frame.setContentPane(label);

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

		frame.add(new ToDoListView());
		// frame.pack();
		// frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}