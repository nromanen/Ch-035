package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Test;


/**
 * @author Petro Andriets
 */

public interface TestDao extends BaseDao<Test> {
    
    List<Test> getAllByModuleId(Long moduleId);
    
    Test getByQuestion(Long questionId);

    void deleteTestById(Long id);
    
    void disableTestById(Long id);
    
    void disable(Test test);
    
    public Test getTestById(Long id);
    
}
