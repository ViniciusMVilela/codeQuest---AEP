package game;

import animations.Design;
import application.UI;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import game.pieces.Door;
import game.pieces.Barrier;
import game.pieces.Player;
import game.pieces.Treasure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public static List<Pergunta> questions = new ArrayList<>();

    public Game() {
        board = new Board(8, 8);
        initialSetup();
    }

    public GamePiece[][] getPieces() {
        GamePiece[][] mat = new GamePiece[board.getRows()][board.getColums()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColums(); j++) {
                mat[i][j] = (GamePiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(GamePosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public GamePiece performGameMove(GamePosition sourcePosition, GamePosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);

        Piece capturedPiece = makeMove(source, target);

        return (GamePiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);

        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return capturedPiece;
    }

    public void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new GameException("There is no piece on source position");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new GameException("There is no possible moves for the chosen piece");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new GameException("The chosen piece can't move to target position.");
        }
    }

    public void validateTreasure(GamePosition p, List<GamePiece> captured) throws InterruptedException {
        if (p.getColumn() == 'h' && p.getRow() == 8) {
            if (captured.size() != 4) {
                throw new GameException("Ainda faltam fragmentos a serem coletados");
            } else {
                this.finishGame();
            }
        }
    }

    private void placeNewPiece(char column, int row, GamePiece piece) {
        board.placePiece(piece, new GamePosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }


    public Position positionValue(String posicao) {
        char column = posicao.charAt(0);
        int row = Integer.parseInt(posicao.substring(1));

        GamePosition p = new GamePosition(column, row);

        return p.toPosition();

    }

    public Piece piece(Position position) {
        return board.piece(position);
    }

    public boolean validateDoorCapture(Piece p, List<GamePiece> captured) throws InterruptedException {
        if (p instanceof Door) {

            this.quest((Door) p, captured);
        }
        return true;
    }

    public boolean gameInit(int anwser) throws InterruptedException {
        if (anwser == 1) {
            UI.clearConsole();
            return true;
        } else if (anwser == 2) {
            return this.instructions();
        } else if (anwser == 3) {
            return this.about();
        } else {
            System.exit(0);
            return false;
        }
    }

    public void fragments(int size) throws InterruptedException {
        if (size == 1) {
            System.out.println(size + "° fragmento capturado");
            Design.fragment();
        } else if (size == 2) {
            System.out.println(size + "° fragmento capturado");
            Design.fragment2();
        } else if (size == 3) {
            System.out.println(size + "° fragmento capturado");
            Design.fragment3();
        } else if (size == 4) {
            System.out.println("Chave completa");
            Design.fullKeyAnimation();
        }

        UI.timeSet(2);
    }

    public void quest(Door d, List<GamePiece> captured) throws InterruptedException {
        Design.doorAnimation();
        this.releaseTip(d, captured.size());
        System.out.println(d.getPergunta().toString());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Resposta: ");
        String r = scanner.nextLine().toUpperCase();

        if (r.equals(d.getPergunta().getCorreta())) {
            //Desenho.openLock();
            System.out.println("Resposta correta =)");
            System.out.println();
        } else {
            System.out.println("Resposta errada =(");
            System.out.println();
            System.out.println(d.getPergunta().getFeedback());
            System.out.println("Fim de jogo");
            System.exit(0);
        }

    }

    private void releaseTip(Door d, int size) throws InterruptedException {
        if (size > 0) {
            System.out.println("Dica: " + d.getPergunta().getDica());
            UI.timeSet(4);
        }

        UI.clearConsole();
    }


    private boolean instructions() throws InterruptedException {
        UI.clearConsole();
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                CodeQuest é um jogo desenvolvido para testar seus conhecimentos frente as matérias de:
                Banco de Dados II, Teste e Qualidade de Software, Estrutura de Dados e Programação Orientada a Objetos.
                                
                Seu objetivo será percorrer um labirinto através das cordenadas presentes no labirinto, utilizando os movimentos possíveis delimitados pelas casas azuis,
                os movimentos do Player somente são possíveis na horizontal e vertical. 
                E assim, encontrar as portas escondidas, respondendo as perguntas corretamente, receberá fragmentos da chave que abre o tesouro localizado na saída do labirinto.
                                
                A cada fragmento coletado uma dica é liberada antes de cada adentrar a porta seguinte.              
                                
                """);
        return this.gameContinuation();
    }


    private boolean about() throws InterruptedException {
        UI.clearConsole();
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Desenvolvido pelos acadêmicos Ricardo Fransico Filho e Vinícius Monteiro de Araújo Vilela da turma ESOFT4C,
                CodeQuest é a nossa proposta da AEP atual, um jogo utilizando um labirinto que desafia o jogador a encontrar as portas 
                e responder as perguntas corretamente relacionadas as matérias do semestre para seguir no jogo.
                O labirinto e movimentações são baseados nas propostas do xadrez e as animações do jogo auxiliadas por ASCII ART.
                """);
        return this.gameContinuation();
    }


    private void certificateDowload() {
        System.out.println("https://drive.google.com/uc?export=download&id=11sfOHgFBcfotHLcPOgYp5we3nHknGJMA");
    }

    private void finishGame() throws InterruptedException {
        System.out.println("Abrindo fechadura");
        Design.openLockAnimation();
        UI.timeSet(2);
        UI.clearConsole();
        Design.treasureAnimation();
        System.out.println("Parabéns você conclui o CodeQuest");
        System.out.println("Sua recompensa pode ser acessada em: ");
        this.certificateDowload();
        System.exit(0);

    }

    private boolean gameContinuation() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("""
                1 - JOGAR
                2 - INICIO
                3 - SAIR
                """);
        int anwser = sc.nextInt();

        if (anwser == 1) {
            return true;
        } else if (anwser == 2) {
            UI.clearConsole();
            Design.initGameAnimation();
            return this.gameInit(sc.nextInt());
        } else {
            return false;
        }
    }

    public void initialSetup() {

        // player
        placeNewPiece('h', 1, new Player(board, Color.BLACK));
        // obstacles
        placeNewPiece('d', 6, new Barrier(board, Color.BLACK));
        placeNewPiece('a', 5, new Barrier(board, Color.BLACK));
        placeNewPiece('e', 2, new Barrier(board, Color.BLACK));
        placeNewPiece('c', 1, new Barrier(board, Color.BLACK));
        placeNewPiece('h', 4, new Barrier(board, Color.BLACK));
        placeNewPiece('d', 4, new Barrier(board, Color.BLACK));
        placeNewPiece('b', 8, new Barrier(board, Color.BLACK));
        placeNewPiece('b', 5, new Barrier(board, Color.BLACK));
        placeNewPiece('d', 7, new Barrier(board, Color.BLACK));
        placeNewPiece('g', 7, new Barrier(board, Color.BLACK));
        placeNewPiece('g', 5, new Barrier(board, Color.BLACK));


        // treasure
        placeNewPiece('h', 8, new Treasure(board, Color.WHITE));

        // questions
        placeNewPiece('d', 1, new Door(board, Color.WHITE, 4, new Pergunta(
                """
                        Considere uma coleção no MongoDB chamada "employees" com os seguintes documentos:
                                                
                        { "_id": 1, "name": "Alice", "department": "HR", "age": 32 }
                        { "_id": 2, "name": "Bob", "department": "IT", "age": 28 }
                        { "_id": 3, "name": "Charlie", "department": "Marketing", "age": 35 }
                                                      
                        """,
                "A) db.employees.find({ age: { $gt: 30 } })",
                "B) db.employees.find({ age: { $lt: 30 } })",
                "C) db.employees.find({ age: { $gte: 30 } })",
                "A",
                """
                        A consulta correta para encontrar funcionários com idade superior a 30 anos é utilizando o operador $gt, que significa "greater than" (maior que). 
                        Essa consulta específica busca documentos na coleção "employees" onde o campo "age" seja maior do que 30, retornando todos os funcionários que atendem a esse critério.
                        """,
                2,
                "Operadores são extremamente importantes nas consultadas em MongoDB")));


        placeNewPiece('a', 3, new Door(board, Color.WHITE, 1, new Pergunta(
                """
                        public class Circle {
                            private double radius;
                                                
                            public Circle(double r) {
                                radius = r;
                            }
                                                
                            public double calculateArea() {
                                return 3.14 * radius * radius;
                            }
                        }
                                                
                        public class Main {
                            public static void main(String[] args) {
                                Circle myCircle = new Circle(5);
                                System.out.println("Área do círculo: " + myCircle.calculateArea());
                            }
                        }
                        """,
                "A) A fórmula do cálculo da área do círculo está incorreta.",
                "B) Não é possível acessar radius a partir do método calculateArea.",
                "C) A classe Circle precisa de um método getRadius para acessar o raio.",
                "B",
                """
                        Na POO, a visibilidade de um atributo pode restringir o acesso a ele de certos métodos. 
                        No código fornecido, a tentativa de acessar radius diretamente de calculateArea não é permitida devido à privacidade do atributo radius. 
                        Uma solução seria criar um método na classe Circle para acessar radius ou modificar a visibilidade do atributo para permitir acesso direto.
                        """,
                3,
                "Em Programação Orientada a Objetos (POO), a visibilidade dos atributos de uma classe pode limitar seu acesso a determinados métodos. ")));

        placeNewPiece('c', 7, new Door(board, Color.WHITE, 2, new Pergunta(
                """
                        Considere uma lista inicialmente vazia e as seguintes operações são realizadas nela:
                                                
                        1. Insere 5 no final da lista.
                        2. Insere 8 no final da lista.
                        3. Remove o elemento do início da lista.
                        4. Insere 3 no final da lista.
                        5. Remove o elemento do início da lista.
                                                
                        Qual é a ordem final da lista após essas operações?
                        """,
                "A) 5, 8, 3",
                "B) 8, 3",
                "C) 5, 3",
                "C",
                """
                        A sequência de operações inicialmente insere o 5 e o 8 na lista, seguido pela remoção do elemento inicial (5). 
                        Depois, o 3 é inserido na lista, e novamente é removido o elemento inicial (8). 
                        Portanto, ao final dessas operações, a ordem final da lista será 5, 3.
                        """,
                4,
                "Preste atenção na sequência das operações realizadas na lista e como cada inserção e remoção afeta a ordem dos elementos.")));

        placeNewPiece('f', 5, new Door(board, Color.WHITE, 3, new Pergunta(
                """
                        Qual das afirmações a seguir melhor descreve o propósito dos testes unitários?
                                                
                        """,
                "A) Verificar se todos os componentes de um sistema estão integrados corretamente.",
                "B) Testar o sistema como um todo para garantir que atenda aos requisitos do usuário.",
                "C) Validar se unidades individuais de código funcionam conforme o esperado.",
                "C",
                """
                        A opção correta, "C) Validar se unidades individuais de código funcionam conforme o esperado", reflete o propósito principal dos testes unitários. 
                        Eles se concentram em testar partes específicas do código, como funções ou métodos, garantindo que cada unidade execute suas tarefas conforme o esperado, independentemente da interação com outros componentes ou da interface do usuário. 
                        Este tipo de teste é valioso para identificar e corrigir problemas em unidades específicas de código durante o desenvolvimento do software.
                        """,
                5,
                "Essa prática contribui para a confiabilidade, manutenibilidade e qualidade do software.")));

    }


}
