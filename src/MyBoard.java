import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

//reference http://www.dreamincode.net
//http://stackoverflow.com

public class MyBoard extends JFrame{
    JFrame frame;
    boolean aWinner = false;
    boolean gameOver = false;

    MyBoard gb;

    JPanel panel;

    ;
    final int rowTiles = 6;

    final int colTiles = 7;

    int[][] grid = new int[6][7];

    int row, col, rowSelected, colSelected = 0;

    int pTurn = 0;

    boolean win = false;

    JButton[][] button = new JButton[rowTiles][colTiles];



    JButton clear;

    JLabel whoWon;

    GridLayout myGrid = new GridLayout(7,7);

    final ImageIcon c0 = new ImageIcon("boardpiece1.jpg");

    final ImageIcon c1 = new ImageIcon("p1.jpg");

    final ImageIcon c2 = new ImageIcon("p2.jpg");

    //final ImageIcon d = new ImageIcon("draw2.jpg");


    public MyBoard(){

        clear = new JButton("New Game");

        clear.addActionListener(new clearListener());

        clear.setPreferredSize(new Dimension(5,5));


        frame = new JFrame("Connect Four");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        panel = new JPanel();


        panel.setLayout(myGrid);
        panel.setBackground(Color.green);

        whoWon = new JLabel("");


        for (int x = rowTiles - 2; x >= 0; x--) {

            for (int y = colTiles - 1; y >= 0; y--) {

                grid[x][y] = -1;

            }

        }



        for (row = 0; row <= rowTiles - 1; row++) {

            for (col = 0; col <= colTiles - 1; col++) {

                button[row][col] = new JButton(c0);

                button[row][col].addActionListener(new buttonListener());

                button[row][col].setBackground(Color.GREEN);

                panel.add(button[row][col]);


            }

        }


        panel.add(clear);
        panel.add(whoWon);

        frame.setContentPane(panel);
        frame.setLocation(489,120);
        frame.pack();

        JOptionPane.showMessageDialog(null,"Let's See You Prove Me Wrong Batman");
        frame.setVisible(true);

    }



    class buttonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            for (row = rowTiles-1; row >= 0; row--) {

                for (col = colTiles-1; col >= 0; col--) {

                    if (button[row][col] == event.getSource()) {

                        if (pTurn % 2 == 0 && grid[row][col] == 0) {

                            button[row][col].setIcon(c1);

                            grid[row][col] = 1;

                            try {

                                grid[row-1][col] = 0;

                            }

                            catch (ArrayIndexOutOfBoundsException e) {

                                System.out.println("Reached top of column");

                            }
                            //player 1 wins
                            if (checkWin()) {
                                aWinner = true;
                                gameOver = true;
                                JOptionPane.showMessageDialog(null,"Congratulations Batman you proved me wrong!\n\nI guess the Jokers going back to Arkham Asylum.");

                                whoWon.setText("player 1 win");

                                for (int x = rowTiles - 1; x >=0; x--) {

                                    for (int y = colTiles - 1; y >= 0; y--) {

                                        grid[x][y] = -1;

                                    }

                                }

                            }

                            pTurn = pTurn + 1;

                            break;

                        }

                        if (pTurn % 2 == 1 && grid[row][col] == 0) {

                            button[row][col].setIcon(c2);

                            grid[row][col] = 2;

                            try {

                                grid[row-1][col] = 0;

                            }

                            catch (ArrayIndexOutOfBoundsException e) {

                                System.out.println("Reached top of column");

                            }
                            //player 2 wins
                            if (checkWin()) {
                                aWinner=true;
                                gameOver=true;
                                JOptionPane.showMessageDialog(null,"Too Bad Batman it seems the Joker has beaten you!\n\nI guess he is free to rain havoc on Gotham's streets.\n\n                      Congratulations Joker");


                                whoWon.setText("player 2 win");

                                for (int x = rowTiles - 1; x >=0; x--) {

                                    for (int y = colTiles - 1; y >= 0; y--) {

                                        grid[x][y] = -1;

                                    }

                                }

                            }



                            pTurn = pTurn + 1;

                            break;

                        }

                        else {

                            System.out.println("Error");

                        }



                    }

                }

            }

            if(!aWinner && gameOver)
                JOptionPane.showMessageDialog(null,"A draw");
        }



    }



    class clearListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            //StartScreen.getGameBoard().setVisible(false);
           /* MyBoard b;
            if(StartScreen.getGameBoard()==null)
                b = new MyBoard();*/

           //gb.dispose();
            gb = new MyBoard();


            for (int x = rowTiles - 1; x >= 0; x--) {

                for (int y = colTiles - 1; y >= 0; y--) {

                    grid[x][y] = -1;

                    button[x][y].setIcon(c0);

                }

            }

            for (int y = colTiles - 1; y >= 0; y--) {

                grid[5][y] = 0;

            }

            whoWon.setText("");

        }


    }

    //reference http://www.dreamincode.net
//http://stackoverflow.com

    public boolean checkWin() {


//horizontal win
        for (int x=0; x<6; x++) {

            for (int y=0; y<4; y++) {

                if (grid[x][y] != 0 && grid[x][y] != -1 &&

                        grid[x][y] == grid[x][y+1] &&

                        grid[x][y] == grid[x][y+2] &&

                        grid[x][y] == grid[x][y+3]) {

                    win = true;

                }

            }

        }

//vertical win
        for (int x=0; x<3; x++) {

            for (int y=0; y<7; y++) {

                if (grid[x][y] != 0 && grid[x][y] != -1 &&

                        grid[x][y] == grid[x+1][y] &&

                        grid[x][y] == grid[x+2][y] &&

                        grid[x][y] == grid[x+3][y]) {

                    win = true;

                }

            }

        }


//diagonal win positive
        for (int x=0; x<3; x++) {

            for (int y=0; y<4; y++) {

                if (grid[x][y] != 0 && grid[x][y] != -1 &&

                        grid[x][y] == grid[x+1][y+1] &&

                        grid[x][y] == grid[x+2][y+2] &&

                        grid[x][y] == grid[x+3][y+3]) {

                    win = true;

                }

            }

        }


//diagonal win negative
        for (int x=3; x<6; x++) {

            for (int y=0; y<4; y++) {

                if (grid[x][y] != 0 && grid[x][y] != -1 &&

                        grid[x][y] == grid[x-1][y+1] &&

                        grid[x][y] == grid[x-2][y+2] &&

                        grid[x][y] == grid[x-3][y+3]) {

                    win = true;

                }

            }

        }

        return win;

    }



    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new MyBoard();


            }

        });

    }

}






