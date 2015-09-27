package softserve.edu.Courses;

import java.util.List;

public interface CourseInterface {

	public List<CourseData> getAllCourses();

	public CourseData getCourseById(int courseId);

	public CourseData getCourseByName(String courseName);

}
