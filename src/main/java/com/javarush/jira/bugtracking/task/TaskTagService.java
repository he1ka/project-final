package com.javarush.jira.bugtracking.task;

import com.javarush.jira.bugtracking.task.mapper.TagMapper;
import com.javarush.jira.bugtracking.task.to.TagTo;
import com.javarush.jira.bugtracking.task.to.TagToEx;
import com.javarush.jira.common.error.DataConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskTagService {
    static final String TAG_EXIST = "Cannot add tag %s to task, already exists";
    static final String TAG_NOT_EXIST = "Cannot remove tag %s from task, not exists";

    private final TaskRepository taskRepository;
    private final TagMapper tagMapper;

    public TagToEx addTagToTask(long taskId, TagTo tag) {
        Task task = taskRepository.getExisted(taskId);
        if (task.getTags().contains(tag.getTag())) {
            throw new DataConflictException(String.format(TAG_EXIST, tag));
        }

        task.getTags().add(tag.getTag());
        taskRepository.save(task);

        return tagMapper.map(taskId, tag.getTag());
    }

    public void deleteTagFromTask(long taskId, String tag) {
        Task task = taskRepository.getExisted(taskId);

        if (!task.getTags().contains(tag)) {
            throw new DataConflictException(String.format(TAG_NOT_EXIST, tag));
        }

        task.getTags().remove(tag);
        taskRepository.save(task);
    }

    public List<TagToEx> getAllTagsForTask(long taskId) {
        Task task = taskRepository.getExisted(taskId);

        return task.getTags().stream()
                .map(tag -> tagMapper.map(taskId, tag))
                .collect(Collectors.toList());
    }
}
