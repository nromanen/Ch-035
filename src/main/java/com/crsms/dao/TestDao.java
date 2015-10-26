package com.crsms.dao;

import com.crsms.domain.Test;

import java.util.List;

/**
 * @author Petro Andriets
 */

public interface TestDao {

    public void saveTest(Test test);

    public Test getTestById(Long id);

    public List<Test> getAllTests();

    public void updateTest(Test test);

    public void deleteTest(Test test);

    public void deleteTestById(Long id);

}
