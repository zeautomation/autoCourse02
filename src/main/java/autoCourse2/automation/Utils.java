package autoCourse2.automation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import Loggingg.ActionType;
import Loggingg.TestLog;


public class Utils {
	
	public static class logUtils{
		public static int saveTestLog(TestLog testlog) throws Exception {

			String fileName = String.valueOf(System.currentTimeMillis()) + ".html";

			PrintWriter printWriter = new PrintWriter(
					new FileOutputStream(System.getProperty("user.dir") + "\\files\\logs\\" + fileName));

			printWriter.println(addHtmlHeader());

			printWriter.println("<body>");
			for (int i = 0; i < testlog.getSteps().size(); i++) {
				printWriter.println("<div class='well'  data-toggle='collapse' data-target='#step" + i + "'>"
						+ testlog.getSteps().get(i).getStepDesc() + "</div>");

				for (int j = 0; j < testlog.getSteps().get(i).getActions().size(); j++) {
					printWriter.println("<div  id='step" + i + "' class='collapse'><div class='well'>"
							+ testlog.getSteps().get(i).getActions().get(j).getActionDesc() + "</div></div>");
				}

			}
			printWriter.println("</body>");
			printWriter.close();

			return 0;

		}

		private static String addHtmlHeader() {
			// TODO Auto-generated method stub
			return fileUtils.getFileContent(System.getProperty("user.dir") + "/files/headerHtml.txt");
		}

		public String getTestLog(Loggingg.TestLog testlog) throws Exception {

			String html = "";

			html += "<div>Fail reason: " + testlog.getFailReason() + "</div>";

			
			for (int i = 0; i < testlog.getSteps().size(); i++) {
				html += "<div>" + testlog.getSteps().get(i).getStepDesc() + "</div>";

				for (int j = 0; j < testlog.getSteps().get(i).getActions().size(); j++) {
					html += "<div>" + testlog.getSteps().get(i).getActions().get(j).getActionDesc() + "</div>";

					if (testlog.getSteps().get(i).getActions().get(j).getActionType()
							.equals(ActionType.Screenshot.toString())) {

						String fileName = testlog.getSteps().get(i).getActions().get(j).getScreenshot();
						fileName = fileName.substring(fileName.lastIndexOf('/') + 1);

						String url = "http://auto-dash.windward.com:8080/autoDashboard/generateS3Url.jsp?fileName="
								+ fileName;

						html += "<div><a href='" + url + "' target='_blank'>"
								+ testlog.getSteps().get(i).getActions().get(j).getActionDesc() + "</a></div>";

					}
				}
			}

			return html;
		}
	}
	
	public static class fileUtils{
		public static String getFileContent(String path) {

			String content = null;

			try {
				File file = new File(path);
				FileReader reader = null;
				reader = new FileReader(file);

				char[] chars = new char[(int) file.length()];
				reader.read(chars);
				content = new String(chars);
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return content;

		}
	}

}
