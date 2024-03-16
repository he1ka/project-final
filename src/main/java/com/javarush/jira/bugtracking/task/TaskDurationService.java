package com.javarush.jira.bugtracking.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskDurationService {
    private final ActivityRepository activityRepository;

    @Transactional(readOnly = true)
    public int getInProgressDurationForTask(Task task) {
        LocalDateTime inProgressTime = activityRepository.findTop1UpdatedByTaskIdAndStatusCodeOrderByIdDesc(
                task.id(),
                Activity.STATUS_CODE_IN_PROGRESS
        ).orElse(null);

        LocalDateTime readyForReviewTime = activityRepository.findTop1UpdatedByTaskIdAndStatusCodeOrderByIdDesc(
                task.id(),
                Activity.STATUS_CODE_READY_FOR_REVIEW
        ).orElse(null);

        return calculateDuration(inProgressTime, readyForReviewTime);
    }

    @Transactional(readOnly = true)
    public int getInTestingDurationForTask(Task task) {
        LocalDateTime readyForReviewTime = activityRepository.findTop1UpdatedByTaskIdAndStatusCodeOrderByIdDesc(
                task.id(),
                Activity.STATUS_CODE_READY_FOR_REVIEW
        ).orElse(null);

        LocalDateTime doneTime = activityRepository.findTop1UpdatedByTaskIdAndStatusCodeOrderByIdDesc(
                task.id(),
                Activity.STATUS_CODE_DONE
        ).orElse(null);

        return calculateDuration(readyForReviewTime, doneTime);
    }

    private int calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return 0;
        }
        Duration duration = Duration.between(startTime, endTime);

        return (int) duration.getSeconds();
    }
}
