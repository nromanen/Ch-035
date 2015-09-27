package softserve.edu.Modules;

import java.util.List;

public class ModuleData {

	private int moduleId;
	private String moduleName;
	private List<Integer> testIds;

	public int getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public List<Integer> getTestIds() {
		return testIds;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setTestIds(List<Integer> testIds) {
		this.testIds = testIds;
	}
}
