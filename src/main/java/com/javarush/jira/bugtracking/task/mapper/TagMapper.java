package com.javarush.jira.bugtracking.task.mapper;

import com.javarush.jira.bugtracking.task.to.TagToEx;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public TagToEx map(long taskId, String tag) {
        return TagToEx.builder()
                .taskId(taskId)
                .tag(tag)
                .build();
    }
}
