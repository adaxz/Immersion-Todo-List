import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class TaskPanel extends JPanel {

	private JCheckBox done = new JCheckBox("Data Structure PSA6");
	private JButton deleteButton = new JButton();
	private boolean isIncomplete;

	public TaskPanel(boolean isIncomplete) {
		super(new BorderLayout());
		this.setOpaque(false);
		this.isIncomplete = isIncomplete;
		setCheckBox();
		if (isIncomplete)
			setDeleteButton();
	}

	private void setCheckBox() {
		done.setFont(new Font("Century Gothic", Font.BOLD, 18));
		done.setForeground(new Color(115, 115, 115));
		this.add(done, BorderLayout.CENTER);
	}

	public void setTaskName(String str) {
		done.setText(str);
	}

	private void setDeleteButton() {
		Image trash = null;
		try {
			trash = ImageIO.read(new File("trashbinIcon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deleteButton.setIcon(new ImageIcon(trash));
		deleteButton.setOpaque(false);
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBorderPainted(false);

		deleteButton.addActionListener(ToDoListBackend.deleteTask);

		this.add(deleteButton, BorderLayout.EAST);
	}

}
