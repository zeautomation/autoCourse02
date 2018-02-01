package Loggingg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;






public class LogStep {

	public LogStep() {
		actions = new ArrayList<StepAction>();
		this.stepStatus = true;
	}

	String stepDesc;

	boolean stepStatus;

	String stepDateTime;

	List<StepAction> actions;

	public String getStepDesc() {
		
		System.out.println();
		return stepDesc;
		
		
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public boolean isStepStatus() {
		return stepStatus;
	}

	public void setStepStatus(boolean stepStatus) {
		this.stepStatus = stepStatus;
	}

	public String getStepDateTime() {
		return stepDateTime;
	}

	public void setStepDateTime(String stepDateTime) {
		this.stepDateTime = stepDateTime;
	}

	public List<StepAction> getActions() {
		return actions;
	}

	public void setActions(List<StepAction> actions) {
		this.actions = actions;
	}

	public LogStep(String stepDesc) {

		actions = new ArrayList<StepAction>();

		this.stepDesc = stepDesc;
		this.stepStatus = true;
		stepDateTime = getCurrentDateTime(false);
	}
	
	public  String getCurrentDateTime(boolean setTimeZone) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate;
		if (setTimeZone) {
			sdf.setTimeZone(TimeZone.getTimeZone("Israel"));

		} else {
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		formattedDate = sdf.format(date);
		return formattedDate;
	}

}
