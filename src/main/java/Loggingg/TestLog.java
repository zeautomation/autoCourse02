package Loggingg;

import java.util.ArrayList;
import java.util.List;

public class TestLog {

	String testName;

	String className;

	String testId;

	int testRunId;

	String failReason;

	List<LogStep> steps;
	/**
	 * false=failes,true=passed
	 */
	boolean testStatus;//

	/**
	 * test run time in seconds
	 */
	int testRunTime;

	String buildName;

	String env;

	String buildNumber;

	String testRunDateTIme;

	public TestLog(String testName, String className) {

		System.out.println("New test log object");

		steps = new ArrayList<LogStep>();
		steps.add(new LogStep("Init step"));

		this.testName = testName;
		this.className = className;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public List<LogStep> getSteps() {
		return steps;
	}

	public void setSteps(List<LogStep> steps) {
		this.steps = steps;
	}

	public void addStep(String stepDesc) {
		steps.add(new LogStep(stepDesc));
	}

	public boolean hasFails() {
		for (LogStep logStep : steps) {
			if (logStep.stepStatus == false) {

				System.out.println(logStep.stepDesc);
				return true;
			}
			for (StepAction action : logStep.actions) {
				if (action.actionStatus == false) {
					System.out.println(action.actionDesc);
					return true;
				}
			}
		}
		return false;
	}

	public void addAfailedAction(String description) {
		StepAction action = new StepAction(description, ActionType.failedAction, false);
		steps.get(steps.size() - 1).actions.add(action);
	}

	public void addActionToLastStep(StepAction action) {
		steps.get(steps.size() - 1).actions.add(action);
	}

	public void assertStrings(String expected, String actual, String message) {
		if (actual.equals(expected) == false) {
			addFailedAction(
					"String assertion failed between " + actual + "(" + actual + ") and expected (" + expected + ")");
		}
	}

	private void addFailedAction(String string) {
		addActionToLastStep(new StepAction(string, ActionType.failedAction, false));

	}

	public int getTestRunId() {
		return this.testRunId;
	}

	/**
	 * return true if test passes, false if test failed;
	 */
	public boolean isPassed() {
		// TODO Auto-generated method stub
		return testStatus;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public boolean isTestStatus() {
		return testStatus;
	}

	public void setTestStatus(boolean testStatus) {
		this.testStatus = testStatus;
	}

	public void setTestRunId(int testRunId) {
		this.testRunId = testRunId;
	}

	public int getTestRunTime() {
		return this.testRunTime;
	}

	public String getStartDateTime() {
		return this.testRunDateTIme;
	}

	public String getEnv() {
		return this.env;
	}

	public String getBuildName() {
		return this.buildName;
	}

	public String getBuildNumber() {
		return this.buildNumber;
	}

	public String getTestClass() {
		return this.className;
	}

	public String getTestRunDateTIme() {
		return testRunDateTIme;
	}

	public void setTestRunDateTIme(String testRunDateTIme) {
		this.testRunDateTIme = testRunDateTIme;
	}

	public void setTestRunTime(int testRunTime) {
		this.testRunTime = testRunTime;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public void addScreenshot(String printScreen) {
		addActionToLastStep(new StepAction("Screenshot added", ActionType.Screenshot, true));

	}

	public boolean assertArrayLists(List<String[]> list1, List<String[]> list2) {
		if (list1.size() != list2.size()) {
			addFailedAction("List are not in the same size");
			return false;
		} else {
			for (int i = 0; i < list1.size(); i++) {
				for (int j = 0; j < list1.get(i).length; j++) {
					if (list1.get(i)[j].equals(list2.get(i)[j]) == false) {
						addFailedAction("Items in index :" + i + "," + j + " are not the same . Compareed "
								+ list1.get(i)[j] + " and " + list2.get(i)[j]);
					}
				}
			}
		}

		return true;
	}

	public void addAction(String desc) {
		addActionToLastStep(new StepAction(desc, ActionType.passedAction, false));

	}
}
