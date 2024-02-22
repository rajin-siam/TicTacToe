package org.hyperskill.tictactoe;
import java.util.Scanner;
public class Game {
    private String player1, player2;
    private int currentPlayer;
    private String [][] grid;

    Game()
    {
        grid = new String[3][3];
        askForUserName();
        gridFilling();
    }


    private void gridFilling() {
        for (int i = 0, cnt = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = "_";
            }
        }

    }
    private boolean userInputValidityChecker1 (int row, int col) {
        if(!(0<row && row<=3) || !(0<col && col<=3))return false;
        else return true;
    }

    private boolean userInputValidityChecker2 (int row, int col) {
        if(grid[row-1][col-1].equals("_")) {
            return true;
        }
        else {
            return false;
        }
    }
    private void userInput () {

        int cnt=0;
        while(true) {
            int row,col;
            if(spaceChecker())break;
            Scanner in = new Scanner(System.in);

            if(currentPlayer==1)System.out.println(player1 +"'s turn");
            else System.out.println(player2 +"'s turn");
            row = in.nextInt();
            col = in.nextInt();

            String input;
            if(!userInputValidityChecker1(row,col)) {
                System.out.println("Input out of range");
                continue;
            }

            if(userInputValidityChecker2(row,col)) {
                if(cnt%2==0) input = "X";
                else input = "O";
                grid[row-1][col-1]=input;
                printingGrid();
                cnt++;
                String s2 = analyzeBoard();
                if(!s2.equals("NotEnded")){
                    if(s2.substring(0,1).equals("D"))System.out.println("The Game is Draw");
                    else if(currentPlayer==1) System.out.println(player1 + " won the game");
                    else if(currentPlayer==2)System.out.println(player2 + " won the game");
                    break;
                }
                if(currentPlayer==1)currentPlayer=2;
                else currentPlayer=1;

            }
            else System.out.println("This cell is occupied!\nChoose another one!");
        }
    }

    private void printingGrid() {
        System.out.println(".........");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + grid[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println(".........");
    }

    private boolean spaceChecker () {
        int cnt=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++) {
                if(grid[i][j].equals("_"))cnt++;
            }
        }
        return (cnt==0);
    }
    private String analyzeBoard () {
        int cntX=0,cntO=0;
        StringBuilder str = new StringBuilder("");
        for(int i=0;i<3;i++)
        {
            if (grid[i][0].equals(grid[i][1]) && grid[i][1].equals(grid[i][2]) && !grid[i][0].equals("_")) {
                String s = grid[i][0];
                return s;
            }
        }
        for(int i=0;i<3;i++)
        {
            if (grid[0][i].equals(grid[1][i]) && grid[1][i].equals(grid[2][i]) && !grid[0][i].equals("_")) {
                String s = grid[0][i];
                return s;
            }
        }

        if (grid[0][0].equals(grid[1][1]) && grid[1][1].equals(grid[2][2]) && !grid[0][0].equals("_")) {
            String s = grid[0][0];
            return s;
        }

        if (grid[0][2].equals(grid[1][1]) && grid[1][1].equals(grid[2][0]) && !grid[1][1].equals("_")) {
            String s = grid[1][1];
            return s;
        }

        if(spaceChecker())return "Draw";
        else return "NotEnded";
    }
    private void askForUserName() {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter name of Player 1 : ");
        player1 = in.next();
        System.out.print("Enter name of Player 2 : ");
        player2 = in.next();

        System.out.println("Who is playing first\n");
        System.out.println("1 For " + player1);
        System.out.println("2 for "+ player2);
        currentPlayer = in.nextInt();

    }
    public void startGame () {
        printingGrid();
        userInput();
    }


}