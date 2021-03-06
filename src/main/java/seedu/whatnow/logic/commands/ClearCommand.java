//@@author A0139772U-reused
package seedu.whatnow.logic.commands;

import java.util.Stack;

import seedu.whatnow.model.WhatNow;

/**
 * Clears WhatNow.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "WhatNow has been cleared!";

    public static Stack<WhatNow> reqStack;



    // @@author A0139128A
    /**
     * Executes the ClearCommand to delete all task data on WhatNow
     */
    @Override
    public CommandResult execute() {
        assert model != null;

        model.resetData(WhatNow.getEmptyWhatNow());
        model.getUndoStack().push(COMMAND_WORD);
        model.clearRedoAll();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
