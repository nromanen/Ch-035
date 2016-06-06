package com.crsms.controller;

import com.crsms.domain.TestResult;
import com.crsms.service.GroupService;
import com.crsms.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping(value = "/private/courses/{courseId}/modules/{moduleId}/tests/{testId}/results")
public class PrivateTestResultsController {

    public static final String TEST_RESULTS_PAGE = "show_group_test_result";

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    public String getAllTestResultsByTestAndGroup(@PathVariable Long testId, @PathVariable Long groupId, Model model) {
        List<TestResult> results = testResultService.getAllByTestIdAndGroupId(testId, groupId);
        model.addAttribute("results", results);
        model.addAttribute("group", groupService.getById(groupId));

        return TEST_RESULTS_PAGE;
    }

    @RequestMapping(value = "/{groupId}/{testResultId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void clearTestResultScore(@PathVariable Long testResultId) {
        testResultService.clearScore(testResultId);
    }
}
