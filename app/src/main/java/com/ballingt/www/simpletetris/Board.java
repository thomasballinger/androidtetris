package com.ballingt.www.simpletetris;

import android.util.Log;

import java.util.ArrayList;

public class Board {

    int width;
    int height;

    public Board (){
        this.width = 5;
        this.height = 8;
    }
    public Board(int w, int h){
        this.width = w;
        this.height = h;
    }

    ArrayList<Piece> pieces = new ArrayList<Piece>();
    Piece cur_piece = new Piece();

    int[][] repr(){
        return repr(true);
    }

    int[][] repr(Boolean with_cur_piece){
        int[][] board = new int[this.height][this.width];

        for (int i = 0; i < pieces.size(); i++){
            for (int j = 0; j < 4; j++){
                board[pieces.get(i).locations()[j][0]][pieces.get(i).locations()[j][1]] = 2;
            }
        }
        if (with_cur_piece){
            for (int j = 0; j < 4; j++){
                int a = 1;
                board[cur_piece.locations()[j][0]][cur_piece.locations()[j][1]] = 1;
            }
        }
        return board;
    }

    void rotate(){
        cur_piece.rotate_right();
        if (!currentPieceOverlaps()){
            for (int j = 0 ; j < 3; j++) {
                cur_piece.rotate_right();
            }
        }
    }

    void moveDown(){
        cur_piece.row += 1;
        if (currentPieceOverlaps()) {
            cur_piece.row -= 1;
            pieces.add(cur_piece);
            cur_piece = new Piece();
        }
    }

    boolean currentPieceOverlaps(){
        int[][] locs = cur_piece.locations();
        int[][] board = repr(false);
        for (int i = 0; i < 4; i++){
            if (locs[i][0] >= height || locs[i][1] < 0 || locs[i][1] >= width ||
                board[locs[i][0]][locs[i][1]] != 0){
                return true;
            }
        }
        return false;
    }

    void moveLeft(){
        cur_piece.col -= 1;
        if (currentPieceOverlaps()){
            cur_piece.col += 1;
        }
    }

    void moveRight(){
        cur_piece.col += 1;
        if (currentPieceOverlaps()){
            cur_piece.col -= 1;
        }
    }
}
