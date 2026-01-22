import java.util.Scanner;

public class Snaddy {
    public static void main(String[] args) {
        String divider = "      ____________________________________________________________\n";
        String logo = divider
                + "       _________                  .___  .___      \n"
                + "      /   _____/ ____ _____     __| _/__| _/__.__.\n"
                + "      \\_____  \\ /    \\\\__  \\   / __ |/ __ <   |  |\n"
                + "       /        \\   |  \\/ __ \\_/ /_/ / /_/ |\\___  |\n"
                + "       /_______  /___|  (____  /\\____ \\____ |/ ____|\n"
                + "               \\/     \\/     \\/      \\/    \\/\\/     \n";
        String goodbye = "Bye. Hope to see you again soon!\n";

        System.out.print(logo + "      Hello! I'm Snaddy\n      What can I do for you?\n" + divider);

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(divider + "      " + goodbye + divider);
            } else {
                System.out.println(divider + "      " + input + "\n" + divider);
            }
        }

        scanner.close();
    }
}
