import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.timer.Timer;

public class ToDoListBackend {

	/**
	 * ActionListener to start the slide show
	 */
	ActionListener startShow = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// create a TimerDemo object
			Timer t = new Timer();
			// start the timer
			t.start();
		}
	};

	/**
	 * Add a slide to my slide show priority queue
	 */
	ActionListener addSlide = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			// /**
			// * Check if user input is an integer of 1 to 10
			// */
			// if (SlideshowDesign.slideTime.getText().equals("1") ||
			// SlideshowDesign.slideTime.getText().equals("2")
			// || SlideshowDesign.slideTime.getText().equals("3")
			// || SlideshowDesign.slideTime.getText().equals("4")
			// || SlideshowDesign.slideTime.getText().equals("5")
			// || SlideshowDesign.slideTime.getText().equals("6")
			// || SlideshowDesign.slideTime.getText().equals("7")
			// || SlideshowDesign.slideTime.getText().equals("8")
			// || SlideshowDesign.slideTime.getText().equals("9")
			// || SlideshowDesign.slideTime.getText().equals("10")) {
			//
			// // get the String and time
			// Slide s = new Slide(SlideshowDesign.slideText.getText(),
			// Integer.valueOf(SlideshowDesign.slideTime.getText()));
			// insert the slide into the slide show priority queue
			// SlideshowDesign.pq.insert(s);
		}

	};

}
