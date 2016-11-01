//@@author A0139772U
package seedu.whatnow.model.task;

import java.util.Objects;

import seedu.whatnow.commons.util.CollectionUtil;
import seedu.whatnow.model.tag.UniqueTagList;

/**
 * Represents a Task in WhatNow.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask, Comparable<Task> {

    private Name name;
    private String taskDate;
    private String startDate;
    private String endDate;
    private String taskTime;
    private String startTime;
    private String endTime;
    private String period;
    private String endPeriod;
    private UniqueTagList tags;
    private String status;
    private String taskType;
    
    private static final String FLOATING = "floating";
    private static final String NOT_FLOATING = "not_floating";
    private static final int COMPARE_TO_IS_EQUAL = 0;
    private static final int COMPARE_TO_IS_NOT_EQUAL = 0;
    
    public Task() {
        
    }
    
  //@@author A0126240W
    /**
     * Every field must be present and not null.
     */
    public Task(Name name, String taskDate, String startDate, String endDate, String taskTime, String startTime, String endTime, String period, String endPeriod, UniqueTagList tags, String status, String taskType) {
        assert !CollectionUtil.isAnyNull(name, tags);
        this.name = name;
        this.period = period;
        this.endPeriod = endPeriod;
        this.tags = new UniqueTagList(tags);
        this.status = status;
        this.taskType = FLOATING;
         
        if (taskDate != null) {
            this.taskDate = taskDate;
            this.taskType = NOT_FLOATING;
        }
        
        if (startDate != null) {
            this.startDate = startDate;
            this.taskType = NOT_FLOATING;
        }
            
        if (endDate != null) {
            this.endDate = endDate;
            this.taskType = NOT_FLOATING;
        }
            
        if (taskTime != null) {
            this.taskTime = taskTime;
            this.taskType = NOT_FLOATING;
        }
            
        if (startTime != null) {
            this.startTime = startTime;
            this.taskType = NOT_FLOATING;
        }
            
        if (endTime != null) {
            this.endTime = endTime;
            this.taskType = NOT_FLOATING;
        }
        
        
        if (taskType != null) {
            this.taskType = taskType;
        }
    }
    
    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getTaskDate(), source.getStartDate(), source.getEndDate(), source.getTaskTime(), source.getStartTime(), source.getEndTime(), source.getPeriod(), source.getEndPeriod(), source.getTags(), source.getStatus(), source.getTaskType());
    }
   
    @Override
    public Name getName() {
        return name;
    }

    @Override
    public String getTaskDate() {
        return taskDate;
    }
    
    @Override
    public String getStartDate() {
        return startDate;
    }
    
    @Override
    public String getEndDate() {
        return endDate;
    }
    
    @Override
    public String getTaskTime() {
        return taskTime;
    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }
    
    @Override
    public String getPeriod() {
        return period;
    }
    
    @Override
    public String getEndPeriod() {
        return endPeriod;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }
    
    @Override
    public String getStatus() {
        return status;
    }
    
    @Override
    public String getTaskType() {
        return taskType;
    }
    
    public void setName(Name name) {
        this.name = name;
    }
    
    public void setTaskDate(String date) {
        this.taskDate = date;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public void setTaskTime(String time) {
        this.taskTime = time;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public void setPeriod(String period) {
        this.period = period;
    }
    
    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }
    
    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    //@@author A0139772U
    public int compareTo(Task task) {
        if (isBothFloating(task)) {
            return COMPARE_TO_IS_EQUAL;
        } else if (isBothDeadline(task)) {
            if (this.taskDate.equals(task.taskDate)) {
                if (this.taskTime == null && task.taskTime == null) {
                    return COMPARE_TO_IS_EQUAL;
                } else if (this.taskTime != null && task.taskTime != null) {
                    if (this.taskTime.equals(task.taskTime)) {
                        return COMPARE_TO_IS_EQUAL;
                    } else {
                        return this.taskTime.compareToIgnoreCase(task.taskTime);
                    }
                } else {
                    return COMPARE_TO_IS_NOT_EQUAL;
                }
            } else {
                return this.taskDate.compareToIgnoreCase(task.taskDate);
            }
        } else if (isBothEvent(task)) {
            if (this.startDate.equals(task.startDate)) {
                if (this.getStartTime() == null && task.getStartTime() == null) {
                    return COMPARE_TO_IS_EQUAL;
                } else if (this.startTime != null && task.startTime != null) {
                    if (this.startTime.equals(task.startTime) && this.endTime.equals(task.endTime)) {
                        return COMPARE_TO_IS_EQUAL;
                    } else if (this.startTime.equals(task.startTime) && !this.endTime.equals(task.endTime)){
                        return this.endTime.compareToIgnoreCase(task.endDate);
                    } else {
                        return this.startTime.compareToIgnoreCase(task.startTime);
                    }
                } else {
                    return COMPARE_TO_IS_NOT_EQUAL;
                }
            } else {
                return this.startDate.compareToIgnoreCase(task.startDate);
            }
        } else {
            return COMPARE_TO_IS_EQUAL;
        }
    }
    
    private boolean isBothFloating(Task task) {
        return this.taskDate == null && task.taskDate == null
                && this.startDate == null && task.startDate == null;
    }
    
    private boolean isBothDeadline(Task task) {
        return this.taskDate != null && task.taskDate != null
                && this.startDate == null && task.startDate == null;
    }
    
    private boolean isBothEvent(Task task) {
        return this.taskDate == null && task.taskDate == null
                && this.startDate != null && task.startDate != null;
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, taskDate, startDate, endDate, taskTime, startTime, endTime, tags, status, taskType);
    }

    @Override
    public String toString() {
        return getAsText();
    }
}
