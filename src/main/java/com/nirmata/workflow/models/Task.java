package com.nirmata.workflow.models;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Task
{
    private final TaskId taskId;
    private final Optional<TaskType> taskType;
    private final List<Task> childrenTasks;
    private final Map<String, String> metaData;

    public Task(TaskId taskId, TaskType taskType)
    {
        this(taskId, taskType, Lists.newArrayList(), Maps.newHashMap());
    }

    public Task(TaskId taskId, List<Task> childrenTasks)
    {
        this(taskId, null, childrenTasks, Maps.newHashMap());
    }

    public Task(TaskId taskId, TaskType taskType, List<Task> childrenTasks)
    {
        this(taskId, taskType, childrenTasks, Maps.newHashMap());
    }

    public Task(TaskId taskId, TaskType taskType, List<Task> childrenTasks, Map<String, String> metaData)
    {
        metaData = Preconditions.checkNotNull(metaData, "metaData cannot be null");
        childrenTasks = Preconditions.checkNotNull(childrenTasks, "childrenTasks cannot be null");
        this.taskId = Preconditions.checkNotNull(taskId, "taskId cannot be null");
        this.taskType = Optional.ofNullable(taskType);

        this.metaData = ImmutableMap.copyOf(metaData);
        this.childrenTasks = ImmutableList.copyOf(childrenTasks);
    }

    public List<Task> getChildrenTasks()
    {
        return childrenTasks;
    }

    public TaskId getTaskId()
    {
        return taskId;
    }

    public TaskType getTaskType()
    {
        return taskType.get();
    }

    public boolean isExecutable()
    {
        return taskType.isPresent();
    }

    public Map<String, String> getMetaData()
    {
        return metaData;
    }

    @Override
    public boolean equals(Object o)
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        Task task = (Task)o;

        if ( !childrenTasks.equals(task.childrenTasks) )
        {
            return false;
        }
        if ( !taskId.equals(task.taskId) )
        {
            return false;
        }
        //noinspection RedundantIfStatement
        if ( !taskType.equals(task.taskType) )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = taskId.hashCode();
        result = 31 * result + taskType.hashCode();
        result = 31 * result + childrenTasks.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "Task{" +
            "taskId=" + taskId +
            ", taskType=" + taskType +
            ", childrenTasks=" + childrenTasks +
            '}';
    }
}