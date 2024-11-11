import java.io.IOException;

import src.com.datarun.Menu;
import src.com.datarun.Database;
import src.com.datarun.ClearScreen;

public class MainProgram {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClearScreen.clearScreen();                                                                              
        Database database = new Database();                                                                                                                      
        Menu menu = new Menu(database);
        menu.displayMenu();
        
    }
}
