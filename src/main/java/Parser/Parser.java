package Parser;

import Commands.Command;
import Commands.ByeCommand;
import Commands.DeleteCommand;
import Commands.InvalidCommand;
import Commands.ListCommand;
import Commands.MarkCommand;
import Commands.TaskCommand;

public class Parser {

    public static Command parse(String fullCommand) {
        String lowercaseCommand = fullCommand.toLowerCase();
        Command command;
        if (lowercaseCommand.equals("bye"))
            command = new ByeCommand();
        else if (lowercaseCommand.equals("list"))
            command = new ListCommand();
        else if (lowercaseCommand.startsWith("todo"))
            command = new TaskCommand('T', fullCommand);
        else if (lowercaseCommand.startsWith("deadline"))
            command = new TaskCommand('D', fullCommand);
        else if (lowercaseCommand.startsWith("event"))
            command = new TaskCommand('E', fullCommand);
        else if (lowercaseCommand.startsWith("delete"))
            command = new DeleteCommand(fullCommand);
        else if (lowercaseCommand.contains("mark"))
            command = new MarkCommand(!lowercaseCommand.startsWith("unmark"), fullCommand);
        else
            command = new InvalidCommand();
        return command;
    }

}
