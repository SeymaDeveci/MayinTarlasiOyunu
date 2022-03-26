
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row, col;
    String[][] map;
    String[][] board;
    boolean isWin = true;
    int finish;


    public MineSweeper(int row, int col){
        this.row = row;
        this.col = col;
        this.board = new String[this.row][this.col];
        this.map = new String[this.row][this.col];
        this.finish = (this.row * this.col) - (this.row * this.col / 4);
    }


    public void printBombMap(){
        System.out.println("Mayınların Konumu");
        int number_of_bomb = (this.row * this.col) / 4;
        while (number_of_bomb != 0){
            Random rand = new Random();
            int rowBomb = rand.nextInt(this.row);
            int colBomb = rand.nextInt(this.col);
            if (this.board[rowBomb][colBomb] == null){
                this.board[rowBomb][colBomb] = "* ";
                number_of_bomb--;
            }
        }
        printBoard();

    }

    public void printBoard(){
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[i].length; j++){
                if (this.board[i][j] == null){
                    board[i][j] = "- ";
                }
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void run(){
        printBombMap();
        System.out.println("============================");
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz !");
        printMap();
        do {
            Scanner inp = new Scanner(System.in);
            System.out.print("Satır Giriniz : ");
            int row_enter = inp.nextInt();
            System.out.print("Sütun Giriniz : ");
            int col_enter = inp.nextInt();
            if (row_enter < 0 || row_enter >= this.row || col_enter < 0 || col_enter >= this.row){
                System.out.println("Geçersiz koordinat !");
            }
            System.out.println("============================");
            if (row_enter >= 0 && row_enter < this.row && col_enter >= 0 && col_enter < this.col){
                printGame(row_enter,col_enter);
            }
        }while (isWin);
    }

    public void printGame(int rowEnter, int colEnter){
        int count = 0;

        if (this.board[rowEnter][colEnter].contains("* ")){
            printBoard();
            System.out.println("Oyunu Kaybettiniz !");
            isWin = false;
        }else {
                for (int i = rowEnter-1; i <= rowEnter+1; i++){
                    if (i < 0 || i == this.row){
                        continue;
                    }
                    for (int j = colEnter-1; j <= colEnter+1; j++){
                        if (j < 0 || j == this.col){
                            continue;
                        }
                        if (this.board[i][j].contains("* ")){
                            count++;
                        }
                    }
                }
                this.board[rowEnter][colEnter] =String.valueOf(count);
                this.map[rowEnter][colEnter] = String.valueOf(count);
                printMap();
                finish -= 1;
                if (finish == 0){
                    System.out.println("Oyunu Kazandınız !");
                    isWin = false;
                }
        }
    }

    public void printMap(){
        for (int i = 0; i < this.map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                if (this.map[i][j] == null)
                    map[i][j] = "-";
                System.out.print(" " + this.map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

