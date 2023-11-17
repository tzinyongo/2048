import java.util.ArrayList;
import java.util.List;

public class _20488
{
    private final int rows = 4;
    private final int cols = 4;
    private int[][] board;
    private int[][] previousBoard;
    private int score;
    private int previousScore;

    /**
     * Initializes board and previousBoard using rows and cols.
     * Uses the generateTile method to add two random tiles to board.
     */
    public _20488() //Creating board with 1 tile
    {
        score = 0;
        board = new int[rows][cols];
        previousBoard = new int[rows][cols];
        generateTile();
    }

    /**
     * Initializes the board of this object using the specified board.
     * Initializes previousBoard using rows and cols.
     *
     * Precondition: the specified board is a 4x4 2D Array.
     *
     * @param board
     */
    public _20488(int[][] board) //Creating board with custom board
    {
        previousBoard = board;
        this.board = board;
        score = 0;
    }

    /**
     * Generates a tile and add it to an empty spot on the board.
     * 80% chance to generate a 2
     * 20% chance to generate a 4
     *
     * Does nothing if the board is full.
     */
    private void generateTile()
    {
        int tile = 0;
        int generated = (int)(Math.random() * 10 + 1);

        if(generated >= 3)
        {
            tile = 2;
        }
        else
            tile = 4;

        boolean full = false;

        for(int i = 0; i <board.length; i++)
        {
            if(!full) {
                int column = (int) (Math.random() * cols);
                int row = (int) (Math.random() * rows);
                if (board[row][column] == 0)
                {
                    board[row][column] = tile;
                    full = true;
                }
            }

        }
    }

    /**
     * Returns false if the board contains a 0, true otherwise.
     * @return
     */
    private boolean full()
    {

        boolean full = false;
        for (int[] ints : board)
        {
            for (int c = 0; c < board[0].length; c++) {
                full = board[rows][cols] != 0;
            }
        }
        return full;
    }

    /**
     * Returns the board.
     * @return
     */
    public int[][] getBoard()
    {
        return board;
    }

    /**
     * Returns the score.
     * @return
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Saves board into previousBoard and score into previousScore
     * then performs a move based on the specified direction:
     *
     * Valid directions (not case sensitive):
     *  up
     *  down
     *  left
     *  right
     *
     * Adds a new tile to the board using the generateTile method.
     *
     * @param direction
     */
    public void move(String direction)
    {
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                previousBoard[r][c] = board[r][c];
            }
        }
        previousScore = score;

        if (direction.equals("up") || direction.equals("Up") || direction.equals("UP") )
        {
            moveUp();
        }
        if ( direction.equals("down") || direction.equals("Down") || direction.equals("DOWN"))
        {
            moveDown();
        }
        if ( direction.equals("left") || direction.equals("Left") || direction.equals("LEFT"))
        {
            moveLeft();
        }
        if (direction.equals("right") || direction.equals("Right") || direction.equals("RIGHT"))
        {
            moveRight();
        }

        generateTile();
    }

    /**
     * Shifts all the tiles up, combines like tiles that collide.
     */
    private void moveUp()
    {
        List<Integer> name = new ArrayList<>();
        for (int c = 0; c < board[0].length; c++) //Iterate through all columns
        {
            for (int[] ints : board) {
                if (ints[c] != 0)
                    name.add(ints[c]);
            }

            for (int r = 0; r < name.size() - 1; r++)
            {
                if (name.get(r).equals(name.get(r + 1)))
                {
                    name.set(r, name.get(r) * 2);
                    name.set(r + 1, 0);
                }
            }

            while (name.size() < board.length)
                name.add(0);

            for (int r = 0; r < board.length; r++)
            {
                board[r][c] = name.get(r);
            }
            name.clear();

            for (int r = 0; r < name.size() ; r++)
            {
                if (name.get(r) == 0) {
                    name.remove(r);
                    r--;
                }
            }

        }

    }

    /**
     * Shifts all the tiles down, combines like tiles that collide.
     */
    private void moveDown()
    {
        List<Integer> name = new ArrayList<>();
        for (int c = 0; c < board[0].length; c++) //Iterate through all columns
        {
            for (int[] ints : board) {
                if (ints[c] != 0)
                    name.add(ints[c]);
            }

            for (int r = name.size() - 1; r > 0; r--)
            {
                if (name.get(r).equals(name.get(r - 1)))
                {
                    name.set(r, name.get(r) * 2);
                    name.set(r + 1, 0);
                }
            }

            while (name.size() < board.length)
                name.add(0);

            for (int r = 0; r < board.length; r++)
            {
                board[r][c] = name.get(r);
            }
            name.clear();

            for (int r = 0; r < name.size() - 1; r++)
            {
                if(name.get(r) == 0)
                {
                    name.remove(r);
                    r--;
                }
            }
        }
    }

    /**
     * Shifts all the tiles left, combines like tiles that collide.
     */
    private void moveLeft()
    {
        List<Integer> name = new ArrayList<>();
        for (int r = 0; r < board.length; r++) //Iterate through all rows
        {
            for (int c = 0; c < board[0].length; c++)
            {
                name.add(board[r][c]);
            }

            for (int c = 0; c < name.size(); c++)
            {
                if(name.get(c) == 0)
                {
                    name.remove(c);
                    c--;
                }

            }

            for (int c = 0; c < name.size() - 1; c++)
            {
                if (name.get(c).equals(name.get(c + 1)))
                {
                    name.set(c, name.get(c) * 2);
                    name.remove(c + 1);
                    c--;
                }
            }

            while (name.size() < board[0].length)
                name.add(0);

            for (int c = 0; c < board[0].length; c++)
            {
                board[r][c] = name.get(c);
            }
            name.clear();
        }
    }

    /**
     * Shifts all the tiles right, combines like tiles that collide.
     */
    private void moveRight()
    {
        List<Integer> name = new ArrayList<>();
        for (int r = 0; r < board.length; r++) //Iterate through all rows
        {
            for (int c = 0; c < board[0].length; c++)
            {
                name.add(board[r][c]);
            }

            for (int c = 0; c < name.size(); c++)
            {
                if(name.get(c) == 0)
                {
                    name.remove(c);
                    c--;
                }
            }

            for (int c = name.size() - 1; c > 0; c--)
            {
                if(name.get(c).equals(name.get(c - 1)))
                {
                    name.set(c, name.get(c) + name.get(c - 1));
                    name.remove(c - 1);
                }
            }

            while (name.size() < board[0].length)
                name.add(0,0);

            for (int c = 0; c < board[0].length; c++)
            {
                board[r][c] = name.get(c);
            }
            name.clear();
        }
    }

    /**
     * Sets board to previousBoard and score to previousScore
     */
    public void undo()
    {
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                board[r][c] = previousBoard[r][c];
            }
        }
    }

    /**
     * Returns true if the game is over, false otherwise.
     * @return
     */
    public boolean gameOver()
    {
        int count = 0;

        moveRight();
        if (board == previousBoard)
            count++;
        undo();

        moveLeft();
        if (board == previousBoard)
            count++;
        undo();

        moveDown();
        if (board == previousBoard)
            count++;
        undo();

        moveUp();
        if (board == previousBoard)
            count++;
        undo();

        if(count == 4)
            return true;
        return false;
    }

    /**
     * Returns a String representation of this object.
     */
    public String toString()
    {
        String rtn = "";

        for(int[] row : board)
        {
            rtn += "|";
            for(int num : row)
                if(num != 0)
                {
                    String str = num + "";
                    while(str.length() < 4)
                        str = " " + str;
                    rtn += str;

                }
                else
                    rtn += "    ";
            rtn += "|\n";
        }

        rtn += "Score: " + score + "\n";

        return rtn;
    }
}
