package Loggingg;


public class StepAction {

	String actionDesc;

	ActionType actionType;

	boolean actionStatus;
	
	String screenshot;

	public StepAction(String desc) {
		this.actionDesc = desc;
		this.actionType = ActionType.passedAction;
		this.actionStatus = true;
	}

	public StepAction(String desc, ActionType actionType, boolean status) {
		this.actionDesc = desc;
		this.actionType = actionType;
		this.actionStatus = status;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public boolean isActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(boolean actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	

}
