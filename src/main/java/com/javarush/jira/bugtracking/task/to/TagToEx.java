package com.javarush.jira.bugtracking.task.to;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TagToEx extends TagTo {
    @NotNull
    Long taskId;
}
