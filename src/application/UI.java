package application;

import game.GamePiece;
import game.GamePosition;
import game.Color;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class UI {
    // importação de código para colorir o tabuleiro
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static void clearConsole() {
        for (int i = 0; i <= 20; i++) {
            System.out.println();
        }
    }

    public static void timeSet(int time) throws InterruptedException {
        TimeUnit.SECONDS.sleep(time);
    }

    public static void printBoard(GamePiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    // sobrecarga do método, agora recebendo também a matriz de movimentos possíveis
    // para conseguir imprí-las no tabuleiro
    public static void printBoard(GamePiece[][] pieces, boolean[][] possibleMoves, List<GamePiece> captured) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
        System.out.println();
        printCapturedPieces(captured);
    }

    public static GamePosition readGamePosition(String si) {
        try {
            char column = si.charAt(0);
            int row = Integer.parseInt(si.substring(1));

            return new GamePosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error readind Position. Valid values are from a1 to h8.");
        }
    }

    //sobrecarga para dar certo a leitura só do target
    public static GamePosition readGamePosition(Scanner sc) {
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));

            return new GamePosition(column, row);
        } catch (RuntimeException e) {

            throw new InputMismatchException("Error readind Position. Valid values are from a1 to h8.");
        }
    }

    private static void printPiece(GamePiece piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }

        }
        System.out.print(" ");
    }


    private static void printCapturedPieces(List<GamePiece> captured) {
        List<GamePiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());

        System.out.print(ANSI_BLUE);

        System.out.print("Fragmentos coletados: ");
        System.out.println(white.size() + "/4");
        System.out.print(ANSI_RESET);

    }
}
