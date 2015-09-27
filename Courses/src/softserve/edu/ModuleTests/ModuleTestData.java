package softserve.edu.ModuleTests;

public class ModuleTestData {

	private int moduleTestId;

	private ModuleTestType moduleTestType;

	public int getModuleTestId() {
		return moduleTestId;
	}

	public ModuleTestType getModuleTestType() {
		return moduleTestType;
	}

	public void setModuleTestId(int moduleTestId) {
		this.moduleTestId = moduleTestId;
	}

	public void setModuleTestType(ModuleTestType moduleTestType) {
		this.moduleTestType = moduleTestType;
	}

	enum ModuleTestType {
		MODULE_TEST, END_COURSE_TEST;
	}
}
