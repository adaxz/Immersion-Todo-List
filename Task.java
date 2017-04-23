import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable<Task> {
	private String taskCotent;
	private String startTime;
	private String endTime;

	public Task (String taskCotent, String startTime, String endTime){
		this.taskCotent = taskCotent;
		this.startTime = startTime;
		this.endTime = endTime;

	}

	public Date getStartTime(){
		return convertStringToDate(startTime);

	}

	public String getTaskContent(){
		return taskCotent;
	}

	public Date getEndTime(){
		return convertStringToDate(endTime);
	}

	private Date convertStringToDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		Date convertedDate = new Date();

		try {
			convertedDate = (Date) sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return convertedDate;
	}
	
	public int CompareTo(String anthorStartTime){
		Date firstDate = convertStringToDate(startTime);
		Date secondDate = convertStringToDate(anthorStartTime);
		return firstDate.compareTo(secondDate);
	}

	@Override
	public int compareTo(Task anotherTask) {
		Date firstDate = convertStringToDate(startTime);
		Date secondDate = anotherTask.getStartTime();
		return firstDate.compareTo(secondDate);
	}
}
