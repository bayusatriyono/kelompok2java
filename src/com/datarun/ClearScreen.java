package src.com.datarun;

import java.io.IOException;

public class ClearScreen {
    public static void clearScreen() throws IOException, InterruptedException {
        try {
            if (System.getProperty("os.name").contains("Linux")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("Tidak bisa clear screen");
            }
        } catch (Exception ex) {
            System.err.println("Tidak bisa clear screen");
            ex.printStackTrace();
        }
    }
}