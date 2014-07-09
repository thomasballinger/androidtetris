package com.ballingt.www.simpletetris;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class TetrisActivity extends Activity {

    static int numRows = 10;
    static int numCols = 10;
    Button start;
    ArrayList<ArrayList<Button>> grid = new ArrayList<ArrayList<Button>>();
    TextView display;
    Board board = new Board(numRows, numCols);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tetris);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onStart()
    {
        // TODO Auto-generated method stub
        super.onStart();
        setUp();
    }

    public void setUp() {

        View mainView = findViewById(R.id.main);

        display = (TextView) mainView.findViewById(R.id.helloworld);
        TableLayout table = (TableLayout) findViewById(R.id.board);
        Log.d("tomstuff", "table" + table.toString());
        for (int i = 0; i < numRows; i++){
            TableRow row = new TableRow(this);
            grid.add(new ArrayList<Button>());
            for (int j = 0; j < numCols; j++){
                Button b = new Button(this);
                b.setBackgroundColor(Color.BLUE);
                b.setWidth(table.getWidth() / numCols);
                b.setHeight(table.getHeight() / numRows);
                row.addView(b);
                grid.get(i).add(b);
            }
            table.addView(row);
        }

    }

    public void down(View downButton){
        board.moveDown();
        updateGrid(board.repr());
    }

    public void rotate(View downButton){
        board.rotate();
        updateGrid(board.repr());
    }

    public void updateGrid(int[][] data){
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){
                Button b = grid.get(row).get(col);
                int value = data[row][col];
                Log.d("yay", "row" + row);
                Log.d("yay", "column" + col);
                Log.d("yay", "value" + value);
                Log.d("yay", "button" + b);
                if (value == 0){
                    b.setBackgroundColor(Color.WHITE);
                } else if (value == 1){
                    b.setBackgroundColor(Color.RED);
                } else if (value == 2) {
                    b.setBackgroundColor(Color.BLUE);
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tetris, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tetris, container, false);
            return rootView;
        }
    }
}
