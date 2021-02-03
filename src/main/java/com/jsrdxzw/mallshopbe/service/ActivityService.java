package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.exception.NotFoundException;
import com.jsrdxzw.mallshopbe.model.Activity;
import com.jsrdxzw.mallshopbe.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public Activity getActivityByName(String name) {
        return activityRepository.findFirstByName(name).orElseThrow(() -> {
            throw new NotFoundException(40001);
        });
    }
}
