package com.ballingt.www.simpletetris;

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
        int[][] board = new int[8][5];

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
        int[][] locs = cur_piece.locations();
        int[][] board = repr(false);

        for (int i = 0; i < 4; i++) {
            // check for all borders in the future
            Log.d("rotateCheck", "i is " + i);
            Log.d("rotateCheck", "row of cur square" + locs[i][0]);
            if (locs[i][0] >= height || board[locs[i][0]][locs[i][1]] != 0){
                // collision detected, rotate right 3x to return to orig pos
                Log.d("rotateCheck", ""+locs[i][0]);
                for (int j = 0 ; j < 3; j++) {
                    cur_piece.rotate_right();
                }
                break;
            }

        }

    }

    void moveDown(){
        cur_piece.row += 1;
        int[][] locs = cur_piece.locations();
        int[][] board = repr(false);
        for (int i = 0; i < 4; i++){
            Log.d("yay", "each" + i);
            if (locs[i][0] >= height || board[locs[i][0]][locs[i][1]] != 0){
                Log.d("yay", "in" + i + ":" + locs[i][0] + ", " + locs[i][1]);
                cur_piece.row -= 1;
                pieces.add(cur_piece);
                cur_piece = new Piece();
                break;
            }
        }
    }
}
