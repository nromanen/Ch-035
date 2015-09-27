package softserve.edu.Modules;

import java.util.List;

public interface ModuleInterface {

	public List<ModuleData> getAllModules();

	public ModuleData getModuleById(int moduleId);

}
