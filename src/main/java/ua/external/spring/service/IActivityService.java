package ua.external.spring.service;

import ua.external.spring.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface IActivityService {
    Optional<Activity> findActivityById(Long id);

    List<Activity> findAllActivities();

    boolean create(Activity activity);
}
