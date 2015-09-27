package softserve.edu.ModuleTests;

import softserve.edu.ModuleTests.ModuleTestData.ModuleTestType;

public interface ModuleTestInterface {

	public ModuleTestData getModuleTestById(int moduleTestId);

	public ModuleTestType getModuleTestTypeById(int moduleTestId);

}
