import java.util.ArrayList;

public class _2048
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
    public _2048()
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
    public _2048(int[][] board)
    {
        previousBoard = new int[rows][cols];
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
        boolean full = false;

        int tile = 0;
        int generated = (int)(Math.random() * 10 + 1);

        if(generated >= 3)
        {
            tile = 2;
        }
        else
            tile = 4;

        for(int i = 0; i <board.length; i++)
        {
            if(!full)
            {
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
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                if (board[r][c] == 0)
                    full = false;
                else
                    full = true;

                    //full = board[rows][cols] != 0;
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
        if ( direction == "down" || direction == "Down" || direction == "DOWN")
        {
            moveDown();
        }
        if ( direction == "left" || direction == "Left" || direction == "LEFT")
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
        ArrayList<Integer> name = new ArrayList<>();

        for (int c = 0; c < board[0].length; c++) //Iterate through all columns
        {
            for (int[] ints : board)
            {
                if (ints[c] != 0)
                    name.add(ints[c]);
            }

            while (name.contains(0))
                name.remove((Integer) 0);
            while (name.size() < 4)
                name.add(0, 0);

            int ro = 0;
            while (ro < name.size() - 1)
            {
                if(name.get(ro) == (name.get(ro + 1)))
                {
                    name.set(ro, name.get(ro) * 2);
                    score+= name.get(ro);
                    name.set(ro + 1, 0);
                }
                ro++;
            }

            while(name.size() < board.length)
                name.add(0);

            for (int row = 0; row < board.length; row++)
            {
                board[row][c] = name.get(row);
            }
            name.clear();
        }
    }

    /**
     * Shifts all the tiles down, combines like tiles that collide.
     */
    private void moveDown()
    {
        ArrayList<Integer> name = new ArrayList<>();
        for (int c = 0; c < board[0].length; c++) //Iterate through all columns
        {
            for (int[] ints : board) {
                if (ints[c] != 0)
                    name.add(ints[c]);
            }

            while (name.contains(0))
                name.remove((Integer) 0);
            while (name.size() < 4)
                name.add(0, 0);

            int r = name.size() - 1;
            while (r > 0)
            {
                if (name.get(r) == (name.get(r - 1)))
                {
                    name.set(r, name.get(r) + name.get(r - 1));
                    name.remove(r -1);
                }
                r--;
            }

            for (int ro = 0; ro < board.length; ro++)
            {
                board[ro][c] = name.get(ro);
            }
            name.clear();

        }
    }

    /**
     * Shifts all the tiles left, combines like tiles that collide.
     */
    private void moveLeft()
    {
        ArrayList<Integer> name = new ArrayList<>();
        for (int r = 0; r < board.length; r++) //Iterate through all rows
        {
            for (int c = 0; c < board[0].length; c++)
            {
                name.add(board[r][c]);
            }

            while (name.contains(0))
                name.remove((Integer) 0);
            while (name.size() < 4)
                name.add(0, 0);

            int c = 0;
            while (c < name.size() - 1)
            {
                if (name.get(c) == (name.get(c + 1)))
                {
                    name.set(c, name.get(c) + name.get(c + 1));
                    name.remove(c + 1);
                    c--;
                }
                c++;
            }

            while (name.size() < board[0].length)
                name.add(0);

            for (int co = 0; co < board[0].length; co++)
            {
                board[r][co] = name.get(co);
            }
            name.clear();
        }
    }

    /**
     * Shifts all the tiles right, combines like tiles that collide.
     */
    private void moveRight()
    {
        ArrayList<Integer> name = new ArrayList<>();
        for (int r = 0; r < board[0].length; r++) //Iterate through all columns
        {
            for (int[] ints : board) {
                if (ints[r] != 0)
                    name.add(ints[r]);
            }

            while (name.contains(0))
                name.remove((Integer) 0);
            while (name.size() < 4)
                name.add(0, 0);

            int co = name.size() - 1;
            while (co > 0)
            {
                if (name.get(co) == (name.get(co - 1)))
                {
                    name.set(co, name.get(co) + name.get(co - 1));
                    name.remove(co -1);
                }
                co--;
            }

            while (name.size() < board.length)
                name.add(0);

            for (int com = 0; com < board.length; com++)
            {
                board[r][com] = name.get(com);
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
        score = previousScore;
    }

    public boolean Equal(int[][] board, int [][] previousBoard)   // helper method to compare 2D Arrays
    {
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[r].length; c++)
            {
                if(board[r][c] != previousBoard[r][c])
                    return false;
            }
        }
        return true;
    }
    /**
     * Returns true if the game is over, false otherwise.
     * @return
     */
    public boolean gameOver()
    {
        move("right");
        if (!Equal(previousBoard, board))
        {
            return false;
        }
        undo();

        move("left");
        if (!Equal(previousBoard, board))
        {
            return false;
        }
        undo();

        move("up");
        if (!Equal(previousBoard, board))
        {
            return false;
        }
        undo();

        move("down");
        if (!Equal(previousBoard, board))
        {
            return false;
        }
        undo();

        return true;
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


