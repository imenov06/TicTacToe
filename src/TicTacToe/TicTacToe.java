package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    // проводим инициализацию
    final char SIGN_X = 'x';
    final char SIGN_O = 'o';
    final char SIGN_EMPTY = '.';
    char[][] table;
    Random rand;
    Scanner scan;

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    // Заполняем переменные в конструкторе
    TicTacToe() {
        this.rand = new Random();
        this.scan = new Scanner(System.in);
        this.table = new char[3][3];
    }
    //  основная логика игры
    void game() {
        initTable();
        while (true) {

            turnHuman();
            printTable();

            if (checkWin(SIGN_X)) {
                System.out.println("Ты выиграл!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Ничья");
                break;
            }

            turnAI();
            printTable();

            if (checkWin(SIGN_O)) {
                System.out.println("Ты проиграл!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Ничья");
                break;
            }


        }
        System.out.println("Конец игры!");
        printTable();
    }

    void initTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                table[row][col] = SIGN_EMPTY;
            }
        }
        // задаем координатную сетку
        int[][] cords = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cords[i][j] = 0;
            }
        }
        for (int i = 1; i < 4; i++) {
            cords[i][0] = i;
        }
        for (int i = 1; i < 4; i++) {
            cords[0][i] = i;
        }

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                System.out.print(cords[row][col] + " ");
            }
            System.out.println();
        }
    }
    // печатаем текущее состояние стола
    void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(table[row][col] + " ");
            }
            System.out.println();
        }
    }
    //  логика хода игрока.
    void turnHuman() {
        int x, y;
        do {
            System.out.println("Введите координаты вашего хода (1..3)");
            x = scan.nextInt() - 1;
            y = scan.nextInt() - 1;
        }
        while (!isCellValid(x, y));
        {
            table[x][y] = SIGN_X;
            System.out.println();
            System.out.println("Твой ход");
        }
    }
    // проверяет возможность хода в эту ячейку
    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3 || y >= 3) {
            return false;
        }
        return (table[x][y] != SIGN_X && table[x][y] == SIGN_EMPTY);
    }
    // логика хода псевдо ИИ
    void turnAI() {
        int x, y;
        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        } while (!isCellValid(x, y));
        {
            table[y][x] = SIGN_O;
            System.out.println();
            System.out.println("Ход ИИ");
        }
    }
    // проверка на окончание игры
    boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++) {
            if ((table[i][0] == dot && table[i][1] == dot && table[i][2] == dot) ||
                    (table[0][i] == dot && table[1][i] == dot && table[2][i] == dot)) {
                return true;
            }
        }
        if ((table[0][0] == dot && table[1][1] == dot && table[2][2] == dot) ||
                (table[2][0] == dot && table[1][1] == dot && table[0][2] == dot)) {
            return true;
        }
        return false;
    }
    // стол заполнен т.е. ничья
    boolean isTableFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == SIGN_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

}