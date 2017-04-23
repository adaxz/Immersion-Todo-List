import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
	private String taskCotent;
	private String startTime;
	private String endTime;
	
	public Task (String taskCotent, String startTime, String endTime){
		this.taskCotent = taskCotent;
		this.startTime = startTime;
		this.endTime = endTime;
		
	}
	
	public Date getStartTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		Date start = new Date();
		
		try {
			start = (Date) sdf.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return start;
	}
	
	public String getTaskContent(){
		return taskCotent;
	}
	
	public Date getEndTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		Date start = new Date();
		
		try {
			start = (Date) sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return start;
	}
}
