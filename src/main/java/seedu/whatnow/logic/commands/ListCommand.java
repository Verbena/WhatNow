package seedu.whatnow.logic.commands;


/**
 * Lists all tasks in WhatNow to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all the tasks in the specified list with the specified filter.\n"
            + "Parameters: todo/schedule/completed/all on/at/with DATE/TIME/PRIORITY\n"
            + "Example: " + COMMAND_WORD + " todo on 23 Dec";
    
    public static final String MESSAGE_SUCCESS = "Listed all tasks";
    
    private String listType = null;
    
    private String filterType = null;
    
    private String filter = null;

    public ListCommand() {}
    
    public ListCommand(String[] arguments) {
        this.listType = arguments[0];
        
        if (arguments.length > 1)
            this.filterType = arguments[1];
        
        if (arguments.length > 2)
            this.filter = arguments[2];
    }

    @Override
    public CommandResult execute() {
        if (listType == null)
            model.updateFilteredListToShowAll();
        
        if (listType.equals("todo")) {
            
        }
        
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
