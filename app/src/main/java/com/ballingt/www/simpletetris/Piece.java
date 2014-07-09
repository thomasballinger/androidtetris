package com.ballingt.www.simpletetris;

public class Piece {
    // what we mean is 3x3 pieces
    public Integer row = 0;
    public Integer col = 0;
    int[][] spots = {{0,1}, {1,1}, {1,0}, {1,2}};

    public int[][] locations(){
        int[][] locs = new int[4][2];
        for (int i = 0; i < 4; i++){
            locs[i][0] = spots[i][0] + row;
            locs[i][1] = spots[i][1] + col;
        }
        return locs;
    }
    public void rotate_right(){
        for (int i = 0; i < 4; i++){
            int old_row = this.spots[i][0];
            int old_col = this.spots[i][1];
            this.spots[i][0] = old_col;
            this.spots[i][1] = 2 - old_row;
        }
    }
}

