/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class Ralfcala {
    public ArrayList<Integer> p1Board = new ArrayList<>();
    public ArrayList<Integer> p2Board = new ArrayList<>();
    public boolean turn, isFinished = false;
    public static int startSeeds;
    public static boolean kalah;

    public Ralfcala() {
    }

    public void initializeBoard(){
        for (int i = 1; i < 7; ++i) {
            p1Board.add(startSeeds);
        }
        p1Board.add(0);;
        for (int i = 8; i < 14; ++i) {
            p2Board.add(startSeeds);
        }
        p2Board.add(0);
        turn = true;
    }

    public void clear(){
        p1Board.clear();
        p2Board.clear();
    }

    public Ralfcala(ArrayList<Integer> top, ArrayList<Integer> bottom) {
        p1Board = (ArrayList<Integer>) top.clone();
        p2Board = (ArrayList<Integer>) bottom.clone();
        turn = true;

    }

    public int getBestNextMove() {
        return getBestNextMove(10);
    }

    public int getBestNextMove(int searchDepth) {
        double bestValue = -1000;
        double newValue = 0;
        int bestMove = -1;
        for (int i = 5; i > -1; --i) {
                Ralfcala testMancala;

                if (turn) {
                        if (p1Board.get(i) == 0)
                                continue;
                        testMancala = Ralfcala.newGameWithMove(p1Board, p2Board, i);
                } else {
                        if (p2Board.get(i) == 0)
                                continue;
                        testMancala = Ralfcala.newGameWithMove(p2Board, p1Board, i);
                }

                if (testMancala.turn) {
                        newValue = Ralfcala.findBestNextMove(testMancala.p1Board,
                                        testMancala.p2Board, 50, bestValue, searchDepth);
                } else {
                        newValue = -Ralfcala.findBestNextMove(testMancala.p2Board,
                                        testMancala.p1Board, -bestValue, -50, searchDepth);
                }

                System.out.println("CHECK udxMOVE " + i + ", score " + newValue);
                if (newValue > bestValue) {

                        bestMove = i;
                        bestValue = newValue;
                }

        }

        System.out.println("best move: " + bestMove + " best score: " + bestValue);
        Sound.sound4.playSound();

        if (turn) {
                return bestMove;
        } else {
                return bestMove + 7;
        }
    }

    //this method returns false when the game is over
    public boolean move(int position){
       boolean needFinishedCheck;
       if (turn) {
               if (position > 6 || position < 0) {
                       System.out.println("tried to move position " + position
                                       + " on top player's turn");
                       return true;
               }
               needFinishedCheck = doMove(position, p1Board, p2Board);
       } else {
               if (position > 13 || position < 7) {
                       System.out.println("tried to move position " + position
                                       + " on bottom player's turn");
                       return true;
               }
               needFinishedCheck = doMove(position - 7, p2Board, p1Board);
       }

       if (needFinishedCheck) {

               int bottomCount = 0;
               int topCount = 0;
               for (int i = 0; i < 6; ++i) {
                       bottomCount += p2Board.get(i);
                       topCount += p1Board.get(i);
               }
               if (topCount == 0 || bottomCount == 0) {
                       finishGame(topCount, bottomCount);
                       return false;
               }

       }
       return true;
    }//end method move

    public void finishGame(int topBonus, int bottomBonus) {
        int topScore = p1Board.get(6) + topBonus;
        int bottomScore = p2Board.get(6) + bottomBonus;
        p1Board.set(6, topScore);
        p2Board.set(6, bottomScore);
        isFinished = true;
    }

    public boolean isGameFinished() {
        return isFinished;
    }
    
    public boolean doMove(int move, ArrayList<Integer> startingBoard,
                    ArrayList<Integer> otherBoard) {
        boolean possiblyOver = false;
        int stonesToBeMoved = startingBoard.get(move);
        if (stonesToBeMoved == 0) {
                System.out.println("tried to move an empty spot");
                return possiblyOver;
        }

        startingBoard.set(move, 0);

        int currentPosition = move + 1;
        for (int stonesLeft = stonesToBeMoved; stonesLeft > 0; --stonesLeft) {
            if (currentPosition < 7){
                startingBoard.set(currentPosition, startingBoard.get(currentPosition) + 1);
                currentPosition++;
            } else if (currentPosition < 13){
                otherBoard.set(currentPosition - 7, otherBoard.get(currentPosition - 7) + 1);
                currentPosition++;
            } else{
                startingBoard.set(0, startingBoard.get(0) + 1);
                currentPosition = 1;
            }
        }

        currentPosition--; // undo last increment since it ran out of stones
        if(kalah){
            if (currentPosition < 6 && startingBoard.get(currentPosition) == 1) {
                // special rule to capture opponent's seeds if last stone lands in an empty pit

                startingBoard.set(6, startingBoard.get(6)
                                + otherBoard.get(5 - currentPosition) + 1);
                startingBoard.set(currentPosition, 0);
                otherBoard.set(5 - currentPosition, 0);
                // System.out.println("o snap");
                possiblyOver = true;
            }
        }else{
            if (currentPosition < 6 && startingBoard.get(currentPosition) == 1 && otherBoard.get(5-currentPosition) != 0) {
                // special rule to capture opponent's seeds if last stone lands in an empty pit

                startingBoard.set(6, startingBoard.get(6)
                                + otherBoard.get(5 - currentPosition) + 1);
                startingBoard.set(currentPosition, 0);
                otherBoard.set(5 - currentPosition, 0);
                possiblyOver = true;
            }
        }

        if (currentPosition != 6) {
            turn = !turn;

        }

        return possiblyOver || move == 5;

    }//end method domove

    public double getBoardValue() {
        return p1Board.get(6) - p2Board.get(6);
    }

    public int getBoardValueAt(int position) {
        if (position > 0 && position < 7) {
            return p1Board.get(position);
        } else if (position > 6 && position < 14) {
            return p2Board.get(position - 7);
        } else {
            System.out.println("bad position asked for " + position);
            return -1;
        }

    }

    public static Ralfcala newGameWithMove(ArrayList<Integer> myTopBoard,
                    ArrayList<Integer> myBottomBoard, int position) {
            Ralfcala myMancala = new Ralfcala(myTopBoard, myBottomBoard);
            myMancala.move(position);
            return myMancala;

    }

    public static double findBestNextMove(ArrayList<Integer> myBoard,
                ArrayList<Integer> otherBoard, double bestGuaranteed,
                double worstGuaranteed, int depthLeft) {
        if (depthLeft <= 0 || myBoard.get(6) + otherBoard.get(6) == 48) {
            return myBoard.get(6) - otherBoard.get(6) + .4;
        }

        double best = bestGuaranteed;
        double worst = worstGuaranteed;
        double currentValue = -99999999;
        for (int i = 5; i > -1; --i) {
            //when a move is valid
            if (myBoard.get(i) != 0)
            {
                Ralfcala nextBoard = newGameWithMove(myBoard, otherBoard, i);
                double boardValue;

                if (nextBoard.turn) {
                        boardValue = findBestNextMove(nextBoard.p1Board,
                                        nextBoard.p2Board, best, worst, depthLeft - 1);
                } else {
                        boardValue = -findBestNextMove(nextBoard.p2Board,
                                        nextBoard.p1Board, -worst, -best, depthLeft - 1);
                }
                if (best <= boardValue) {
                        return boardValue;
                }
                if (boardValue > worst) {
                        worst = boardValue;
                }
                if (boardValue > currentValue) {
                        currentValue = boardValue;
                }
            }
        }
        return currentValue;
    }

    public void printBoard(final boolean gameEnd) {
        Game.but[0].setText(p2Board.get(6).toString());
        Game.but[7].setText(p1Board.get(6).toString());

        Game.but[1].setText(p1Board.get(0).toString());
        Game.but[2].setText(p1Board.get(1).toString());
        Game.but[3].setText(p1Board.get(2).toString());
        Game.but[4].setText(p1Board.get(3).toString());
        Game.but[5].setText(p1Board.get(4).toString());
        Game.but[6].setText(p1Board.get(5).toString());

        Game.but[8].setText(p2Board.get(0).toString());
        Game.but[9].setText(p2Board.get(1).toString());
        Game.but[10].setText(p2Board.get(2).toString());
        Game.but[11].setText(p2Board.get(3).toString());
        Game.but[12].setText(p2Board.get(4).toString());
        Game.but[13].setText(p2Board.get(5).toString());

        if (gameEnd) {
            for(int i= 0; i<14; i++){
                Game.but[i].setEnabled(false);
                if(i!=0 && i!=7){
                    Game.but[i].setText("0");
                }
            }
        } else {
            Game.but[1].setEnabled(turn);
            Game.but[2].setEnabled(turn);
            Game.but[3].setEnabled(turn);
            Game.but[4].setEnabled(turn);
            Game.but[5].setEnabled(turn);
            Game.but[6].setEnabled(turn);

            Game.but[8].setEnabled(!turn);
            Game.but[9].setEnabled(!turn);
            Game.but[10].setEnabled(!turn);
            Game.but[11].setEnabled(!turn);
            Game.but[12].setEnabled(!turn);
            Game.but[13].setEnabled(!turn);

            if (!turn) {
                System.out.println("Computer's Turn: " + Game.compName + " calculating...");
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                NextMove();
                        }
                });
                Game.gameStarted = true;
            }
        }
    }//end method printBoard
        
        
    public void printBoard2(final boolean gameEnd) {
        if("ANTICLOCKWISE".equals(CustomMenu.pStyleL2.getText())){
            CustomGame.but[0].setText(p2Board.get(6).toString());
            CustomGame.but[7].setText(p1Board.get(6).toString());

            CustomGame.but[1].setText(p1Board.get(0).toString());
            CustomGame.but[2].setText(p1Board.get(1).toString());
            CustomGame.but[3].setText(p1Board.get(2).toString());
            CustomGame.but[4].setText(p1Board.get(3).toString());
            CustomGame.but[5].setText(p1Board.get(4).toString());
            CustomGame.but[6].setText(p1Board.get(5).toString());

            CustomGame.but[8].setText(p2Board.get(0).toString());
            CustomGame.but[9].setText(p2Board.get(1).toString());
            CustomGame.but[10].setText(p2Board.get(2).toString());
            CustomGame.but[11].setText(p2Board.get(3).toString());
            CustomGame.but[12].setText(p2Board.get(4).toString());
            CustomGame.but[13].setText(p2Board.get(5).toString());
        }else{
            CustomGame.but[7].setText(p2Board.get(6).toString());
            CustomGame.but[0].setText(p1Board.get(6).toString());

            CustomGame.but[1].setText(p1Board.get(5).toString());
            CustomGame.but[2].setText(p1Board.get(4).toString());
            CustomGame.but[3].setText(p1Board.get(3).toString());
            CustomGame.but[4].setText(p1Board.get(2).toString());
            CustomGame.but[5].setText(p1Board.get(1).toString());
            CustomGame.but[6].setText(p1Board.get(0).toString());

            CustomGame.but[8].setText(p2Board.get(5).toString());
            CustomGame.but[9].setText(p2Board.get(4).toString());
            CustomGame.but[10].setText(p2Board.get(3).toString());
            CustomGame.but[11].setText(p2Board.get(2).toString());
            CustomGame.but[12].setText(p2Board.get(1).toString());
            CustomGame.but[13].setText(p2Board.get(0).toString());
        }

        if (gameEnd) {
            for(int i= 0; i<14; i++){
                CustomGame.but[i].setEnabled(false);
                if(i!=0 && i!=7){
                    CustomGame.but[i].setText("0");
                    Sound.soundLose.playSound();
                    Sound.soundWaiting.playSong();
                }
            }
            
        } else {
            CustomGame.but[1].setEnabled(turn);
            CustomGame.but[2].setEnabled(turn);
            CustomGame.but[3].setEnabled(turn);
            CustomGame.but[4].setEnabled(turn);
            CustomGame.but[5].setEnabled(turn);
            CustomGame.but[6].setEnabled(turn);

            CustomGame.but[8].setEnabled(!turn);
            CustomGame.but[9].setEnabled(!turn);
            CustomGame.but[10].setEnabled(!turn);
            CustomGame.but[11].setEnabled(!turn);
            CustomGame.but[12].setEnabled(!turn);
            CustomGame.but[13].setEnabled(!turn);

            if (!turn) {
                System.out.println("Computer's Turn: " + CustomGame.compName + " calculating...");
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                NextMove2();
                        }
                });
                CustomGame.gameStarted = true;
            }
        }   
    }//end method printBoard2    
    
    private void NextMove() {
        System.out.println("Search Depth = " + Game.sDepth);
        printBoard(!move(getBestNextMove(Integer.parseInt(Game.sDepth))));
    }
    
    private void NextMove2() {
        System.out.println("Search Depth = " + CustomGame.sDepth);
        printBoard2(!move(getBestNextMove(Integer.parseInt(CustomGame.sDepth))));
    }
    
}//end class Ralfcala

