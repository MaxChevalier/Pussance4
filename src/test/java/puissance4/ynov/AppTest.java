package puissance4.ynov;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /*
    // a besoin de passer les methode en public
    @Test
    public void checkColoneIsCorrect() {
        try {
            int amount;
            int[][] grid = new int[6][8];

            grid[0][0] = 1;
            grid[0][1] = 1;
            grid[0][2] = 1;
            grid[0][3] = 1;
            amount = new GridVerif(grid).CheckColumn();
            assertEquals("Total amount is not correct", 1, amount, 0);
            grid[0][0] = 2;
            grid[0][1] = 2;
            grid[0][2] = 2;
            grid[0][3] = 2;
            amount = new GridVerif(grid).CheckColumn();
            assertEquals("Total amount is not correct", 2, amount, 0);
            grid[0][0] = 1;
            grid[0][1] = 2;
            grid[0][2] = 1;
            grid[0][3] = 2;
            amount = new GridVerif(grid).CheckColumn();
            assertEquals("Total amount is not correct", 0, amount, 0);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void CheckLineIsCorrect(){
        try {
            int amount;
            int[][] grid = new int[][]{};

            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 0, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {1,1,1,1,0,0}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 0, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {2,0,0,0,0,0},
                {2,0,0,0,0,0},
                {2,0,0,0,0,0},
                {2,0,0,0,0,0}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 2, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {2,0,0,0,0,0},
                {1,0,0,0,0,0},
                {2,0,0,0,0,0},
                {1,2,1,2,0,0}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 0, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,1,0,0,0,0},
                {0,1,0,0,0,0},
                {0,1,2,0,0,0},
                {0,1,0,2,0,0},
                {0,0,1,0,2,0},
                {1,2,0,0,1,0},
                {1,1,2,1,2,1}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 1, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,1,0,0,0,0},
                {0,2,0,0,2,0},
                {0,1,2,0,2,0},
                {0,1,0,2,2,0},
                {0,0,1,0,2,0},
                {1,2,0,0,1,0},
                {1,1,2,1,2,1}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 2, amount, 0);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void CheckDiagonalIsCorrect(){
        try {
            int amount;
            int[][] grid = new int[][]{};

            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 0, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,2},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,1,1,2,2},
                {0,0,0,1,1,2},
                {0,0,0,0,1,2},
                {0,0,0,0,0,1}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 1, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,2,0,0,0},
                {0,0,0,2,0,0},
                {0,0,0,0,2,0},
                {0,0,0,0,0,2}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 2, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {2,0,0,1,0,0},
                {1,0,1,0,0,0},
                {2,2,0,0,0,0},
                {1,2,1,2,0,0}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 0, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,1,0,0,0,0},
                {0,1,0,0,0,0},
                {0,2,2,1,0,0},
                {0,1,1,2,0,0},
                {0,1,1,0,2,0},
                {1,2,0,0,1,0},
                {1,1,2,1,2,1}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 1, amount, 0);
            grid = new int[][]{
                {0,0,0,0,0,0},
                {0,1,2,0,0,0},
                {0,2,0,2,2,0},
                {0,1,2,0,2,0},
                {0,1,0,2,1,2},
                {0,0,1,0,1,0},
                {1,2,0,0,1,0},
                {1,1,2,1,2,1}
            };
            amount = new GridVerif(grid).CheckLine();
            assertEquals("Total amount is not correct", 2, amount, 0);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void CheckIsFinish(){
        try {
            boolean amount;
            int[][] grid = new int[6][8];

            grid[0][0] = 1;
            grid[0][1] = 1;
            grid[0][2] = 1;
            grid[0][3] = 1;
            amount = GridVerif.IsFinish(grid);
            assertTrue("Total amount is not correct", amount);
            grid = new int[6][8];
            grid[0][0] = 2;
            grid[1][0] = 2;
            grid[2][0] = 2;
            grid[3][0] = 2;
            amount = GridVerif.IsFinish(grid);
            assertTrue("Total amount is not correct", amount);
            grid = new int[6][8];
            grid[0][0] = 3;
            grid[1][1] = 3;
            grid[2][2] = 3;
            grid[3][3] = 3;
            amount = GridVerif.IsFinish(grid);
            assertTrue("Total amount is not correct", amount);
            grid = new int[6][8];
            assertTrue("Total amount is not correct", amount);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void CheckWhoWin(){
        try {
            int amount;
            int[][] grid = new int[6][8];

            grid[0][0] = 1;
            grid[0][1] = 1;
            grid[0][2] = 1;
            grid[0][3] = 1;
            amount = GridVerif.WhoWin(grid);
            assertEquals("Total amount is not correct", 1, amount, 0);
            grid = new int[6][8];
            grid[0][0] = 2;
            grid[1][0] = 2;
            grid[2][0] = 2;
            grid[3][0] = 2;
            amount = GridVerif.WhoWin(grid);
            assertEquals("Total amount is not correct", 2, amount, 0);
            grid = new int[6][8];
            grid[0][0] = 3;
            grid[1][1] = 3;
            grid[2][2] = 3;
            grid[3][3] = 3;
            amount = GridVerif.WhoWin(grid);
            assertEquals("Total amount is not correct", 3, amount, 0);
            grid = new int[6][8];
            amount = GridVerif.WhoWin(grid);
            assertEquals("Total amount is not correct", 0, amount, 0);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    */
}
