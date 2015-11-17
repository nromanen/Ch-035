package com.crsms.dao;

import com.crsms.domain.Test;

import java.util.List;

/**
 * @author Petro Andriets
 */

public interface TestDao extends BaseDao<Test> {
    
    public List<Test> getAllByModuleId(Long moduleId);

    public void deleteTestById(Long id);
    
    public void disableTestById(Long id);
    
    void disable(Test test);
    
}
