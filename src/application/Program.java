package application;

import animations.Design;
import boardgame.Piece;
import game.GameException;
import game.Game;
import game.GamePiece;
import game.GamePosition;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        Game match = new Game();
        List<GamePiece> captured = new ArrayList<>();
        String sourcePosition = "h1";

        Design.initGameAnimation();
        int anwser = sc.nextInt();
        sc.nextLine();

       final boolean init = match.gameInit(anwser);
        while (init) {
            try {
                UI.clearConsole();

                GamePosition source = UI.readGamePosition(sourcePosition);

                boolean[][] possibleMoves = match.possibleMoves(source);
                UI.clearConsole();
                UI.printBoard(match.getPieces(), possibleMoves, captured);

                System.out.println();
                System.out.print("Posição destino: ");
                String targetPosition = sc.nextLine();
                GamePosition target = UI.readGamePosition(targetPosition);


                match.validateTreasure(target, captured);
                Piece peca = match.piece(match.positionValue(targetPosition));

                GamePiece capturedPiece = match.performGameMove(source, target);

                if (capturedPiece != null) {
                   boolean captura = match.validateDoorCapture(peca, captured);
                    if (captura) {
                        captured.add(capturedPiece);
                        match.fragments(captured.size());
                    }
                }

                System.out.println();

                sourcePosition = targetPosition;

            } catch (GameException e) {
                System.out.println(e.getMessage());
                sc.nextLine(); // programa aguardar um enter para prosseguir o jogo
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
