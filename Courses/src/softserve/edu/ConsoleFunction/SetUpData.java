package softserve.edu.ConsoleFunction;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SetUpData {

	private static final String COURSE_FORMAT = "Please, Enter Course data in format:"
			+ " 'Course name | start date(yyyy.MM.dd) | end date(yyyy.MM.dd)'";

	protected void readLine() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'exit' to exit from concole.");
		System.out.println(COURSE_FORMAT);
		boolean infinityLoop = true;
		while (infinityLoop) {
			String courseInfo = br.readLine();
			if (!courseInfo.equalsIgnoreCase("exit")) {

				if (courseInfo.split("|").length == 3) {
					System.out
							.println("Please enter Y or N if course has modules: ");

				}
			} else {
				br.close();
				infinityLoop = false;
			}
		}
	}

	protected void parseDataFromConsole() {
	}

}
