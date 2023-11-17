package animations;

import application.UI;

import java.util.concurrent.TimeUnit;

public class Design {

    public static void initGameAnimation() {
        System.out.println("  ____          _       ___                  _   ");
        System.out.println(" / ___|___   __| | ___ / _ \\ _   _  ___  ___| |_ ");
        System.out.println("| |   / _ \\ / _` |/ _ \\ | | | | | |/ _ \\/ __| __|");
        System.out.println("| |__| (_) | (_| |  __/ |_| | |_| |  __/\\__ \\ |_ ");
        System.out.println(" \\____\\___/ \\__,_|\\___|\\__\\_\\\\__,_|\\___||___/\\__|");
        System.out.println();
        System.out.println("1 - JOGAR");
        System.out.println("2 - INSTRUÇÕES");
        System.out.println("3 - SOBRE");
        System.out.println("4 - SAIR");
    }


    public static void openLockAnimation() {
        System.out.println("──▄▀▀▀▄───────────────");
        System.out.println("──█───█───────────────");
        System.out.println("──█───█───────────────");
        System.out.println("─███████─────────▄▀▀▄─");
        System.out.println("░██─▀─██░░█▀█▀▀▀▀█░░█░");
        System.out.println("░███▄███░░▀░▀░░░░░▀▀░░");


    }

    public static void doorAnimation() throws InterruptedException {
        System.out.println("Porta encontrada");


        System.out.println(" ----- ");
        System.out.println("|     |");
        System.out.println("|  |  |");
        System.out.println("|  |  |");
        System.out.println("|-----|");
        System.out.println("   O   ");
        System.out.println("  /|\\ ");
        System.out.println("  / \\  ");
        UI.timeSet(2);
        // UI.clearConsole();
    }

    public static void fullKeyAnimation() {
        System.out.println("         ▄▀▀▄ ");
        System.out.println("  █▀█▀▀▀▀█  █ ");
        System.out.println("  ▀ ▀     ▀▀  ");
    }

    public static void fragment() {
        System.out.println("  █▀");
        System.out.println("  ▀ ");
    }

    public static void fragment2() {
        System.out.println("  █▀█▀ ");
        System.out.println("  ▀ ▀   ");

    }

    public static void fragment3() {
        System.out.println("  █▀█▀▀▀▀");
        System.out.println("  ▀ ▀     ");
    }

    public static void treasureAnimation() {
        System.out.println("                        _.-'_:-'||\n");
        System.out.println("                    _.-'_.-::::'||\n");
        System.out.println("               _.-:'_.-::::::'  ||\n");
        System.out.println("             .'`-.-:::::::'     ||\n");
        System.out.println("            /.'`;|:::::::'      ||_\n");
        System.out.println("           ||   ||::::::'     _.;._'-._\n");
        System.out.println("           ||   ||:::::'  _.-!oo @.!-._'-.\n");
        System.out.println("           \\'.  ||:::::.-!()oo @!()@.-'_.|\n");
        System.out.println("            '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n");
        System.out.println("              `>'-.!@%()@'@_%-'_.-o _.|'||\n");
        System.out.println("               ||-._'-.@.-'_.-' _.-o  |'||\n");
        System.out.println("               ||=[ '-._.-\\U/.-'    o |'||\n");
        System.out.println("               || '-.]=|| |'|      o  |'||\n");
        System.out.println("               ||      || |'|        _| ';\n");
        System.out.println("               ||      || |'|    _.-'_.-'\n");
        System.out.println("               |'-._   || |'|_.-'_.-'\n");
        System.out.println("            \t '-._'-.|| |' `_.-'\n");
        System.out.println("                    '-.||_/.-'\n");
    }
}
