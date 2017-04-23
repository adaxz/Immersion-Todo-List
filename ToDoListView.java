import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
public class ToDoListView extends JPanel {
	private Color titleColor = new Color(153, 204, 255);
	private static final int BORDER_STROKE = 5;
	protected static PriorityQueue<Task> toDoQueue = new PriorityQueue<Task>();
	protected static PriorityQueue<Task> doneQueue = new PriorityQueue<Task>();
	private ToDoListBackend backend = new ToDoListBackend();
	private JPanel listPanel = new JPanel();

	/**
	 * The constructor of the Unrestricted Game
	 * 
	 * @param JFrame
	 *            frame
	 * @throws IOException
	 */
	public ToDoListView() throws IOException {
		super(new BorderLayout());
		repaint();
		add(displayTime(), BorderLayout.CENTER);
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
		textPanel.add(new WhiteNoise());

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

	private JPanel createListPanel() throws IOException {
		// the list panel

		listPanel.setOpaque(false);
		listPanel.setLayout(new GridLayout(10, 1));
		/** TO DO the addTask JButton **/
		JButton addTask = setAddButton();

		// title of to do list
		JLabel label = new JLabel("To-Do List", SwingConstants.CENTER);
		label.setFont(new Font("Chalkduster", Font.BOLD, 24));
		label.setForeground(titleColor);
		// the tasks in the list

		// addTasks(listPanel, true);

		JPanel panel = new JPanel(new BorderLayout());

		setBorderLine(panel);
		panel.setOpaque(false);
		panel.add(label, BorderLayout.NORTH);
		panel.add(listPanel, BorderLayout.CENTER);
		panel.add(addTask, BorderLayout.SOUTH);

		return panel;
	}

	private void addTaskHonkiVersion(JPanel listPanel) {

		listPanel.removeAll();

		PriorityQueue<Task> temp = new PriorityQueue<Task>();

		while (toDoQueue.size() != 0) {
			// get most current slide to show
			Task currentTask = toDoQueue.poll();
			temp.add(currentTask);
			// if s is not null and the time to display this slide is
			// equal to the current time
			if (currentTask != null) {
				// print out time
				System.out.println("currentTask: " + currentTask.getTaskContent());
				TaskPanel currentTaskPanel = new TaskPanel(true);
				currentTaskPanel.setTaskName(currentTask.getTaskContent());
				listPanel.add(currentTaskPanel);
			}

		}

		toDoQueue = temp;

	}

	private JButton setAddButton() {
		JButton addTask = new JButton("Add Tasks");
		addTask.setFont(new Font("Century Gothic", Font.BOLD, 18));
		addTask.setForeground(new Color(115, 115, 115));
		addTask.setBackground(new Color(242, 242, 242));
		addTask.setOpaque(true);
		addTask.setBorderPainted(false);

		addTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField taskName = new JTextField(5);
				JTextField startTime = new JTextField(5);
				JTextField endTime = new JTextField(5);

				JPanel addPanel = new JPanel();
				addPanel.add(new JLabel("Task:"));
				addPanel.add(taskName);
				addPanel.add(Box.createHorizontalStrut(15)); // a spacer
				addPanel.add(new JLabel("Start time:"));
				addPanel.add(startTime);
				addPanel.add(Box.createHorizontalStrut(15)); // a spacer
				addPanel.add(new JLabel("End time:"));
				addPanel.add(endTime);

				int result = JOptionPane.showConfirmDialog(null, addPanel, "Please Enter Your Task",
						JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					Task newTask = new Task(taskName.getText(), startTime.getText(), endTime.getText());
					backend.addTask(newTask);
				}
				addTaskHonkiVersion(listPanel);
				repaint();
				revalidate();

			}
		});
		return addTask;
	}

	private void addTasks(JPanel listPanel, boolean isIncomplete) throws IOException {

		TaskPanel[] tasks = new TaskPanel[10];
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new TaskPanel(isIncomplete);
			listPanel.add(tasks[i]);
		}

	}

	private JPanel createDonePanel() {
		JPanel listPanel = new JPanel();
		listPanel.setOpaque(false);
		listPanel.setLayout(new GridLayout(10, 1));

		JLabel label = new JLabel("Completed Tasks", SwingConstants.CENTER);
		label.setFont(new Font("Chalkduster", Font.BOLD, 24));
		label.setForeground(titleColor);

		try {
			addTasks(listPanel, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel panel = new JPanel(new BorderLayout());
		setBorderLine(panel);
		panel.setOpaque(false);
		panel.add(label, BorderLayout.NORTH);
		panel.add(listPanel, BorderLayout.CENTER);

		return panel;
	}

	private void setBorderLine(JComponent comp) {
		Border border = BorderFactory.createMatteBorder(BORDER_STROKE, BORDER_STROKE, BORDER_STROKE / 2, BORDER_STROKE,
				titleColor);

		comp.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	}

}