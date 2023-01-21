import java.io.File;
import Commands.Command;
import Exceptions.DukeException;
import Parser.Parser;
import Ui.Ui;
import TaskList.TaskList;
import Storage.Storage;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ui.showLoading(filePath);
            tasks = new TaskList(storage.loadTasks());
            ui.showSuccessfulLoad(tasks);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        String filePath = String.format("data%sduke.txt", File.separator);
        new Duke(filePath).run();
    }
}