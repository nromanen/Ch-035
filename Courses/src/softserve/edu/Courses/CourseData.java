package softserve.edu.Courses;

import java.util.Date;
import java.util.List;

public class CourseData {

	private int courseId;
	private String courseName;
	private Date startDate;
	private Date endDate;
	private List<Integer> moduleIds;

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public Date getEndDate() {
		return endDate;
	}

	public List<Integer> getModuleIds() {
		return moduleIds;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setModuleIds(List<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
