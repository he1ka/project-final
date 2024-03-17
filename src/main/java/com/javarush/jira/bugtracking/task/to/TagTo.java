package com.javarush.jira.bugtracking.task.to;

import com.javarush.jira.common.util.validation.NoHtml;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode
@SuperBuilder
public class TagTo {
    @NoHtml
    @Size(min = 1, max = 32)
    @Nullable
    protected String tag;
}
