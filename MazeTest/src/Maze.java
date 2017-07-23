// Project 4 - Maze Generator
// CS 3345.001
// Programmer: Stephen-Michael Brooks

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Maze extends JPanel {
	
	public class Cell{

		public Wall top, right, bottom, left;
		public int x, y;
		public List<Cell> adjacents;	// for tracking adjacent cells
		public int cellNumber;			// to number the cells
		public Cell previousCell;		// explicitly store previous cell


		public Cell(int x, int y){
		   this.x = x;
		   this.y = y;
		   adjacents = new LinkedList<Cell>();
		   previousCell = null;
		   cellNumber = 0;
		}
		   
		public int getcellNumber(){
		   return cellNumber++;
		}
	}
	
	public class Wall{
		   
		   public Cell currentcell, nextcell;	
		   public boolean deleted = false;		// set all walls visible
		   
		   public Wall(Cell a, Cell b){
		      currentcell = a;
		      nextcell = b;
		   }
		   
		   public Wall(Cell r){
		      currentcell = r;
		      nextcell = null;
		   }
		}
	
	public class DisjSets
	{
		
		private int [ ] s;
		
	    /**
	     * Construct the disjoint sets object.
	     * @param numElements the initial number of disjoint sets.
	     */
	    public DisjSets( int numElements )
	    {
	        s = new int [ numElements ];
	        for( int i = 0; i < s.length; i++ )
	            s[ i ] = -1;
	    }

	    /**
	     * Union two disjoint sets using the height heuristic.
	     * For simplicity, we assume root1 a and root2 are distinct
	     * and represent set names.
	     * @param root1 the root of set 1.
	     * @param root2 the root of set 2.
	     */
	    public void union( int root1, int root2 )
	    {
	        if( s[ root2 ] < s[ root1 ] )  // root2 is deeper
	            s[ root1 ] = root2;        // Make root2 new root
	        else
	        {
	            if( s[ root1 ] == s[ root2 ] )
	                s[ root1 ]--;          // Update height if same
	            s[ root2 ] = root1;        // Make root1 new root
	        }
	    }

	    /**
	     * Perform a find with path compression.
	     * Error checks omitted again for simplicity.
	     * @param x the element being searched for.
	     * @return the set containing x.
	     */
	    public int find( int x )
	    {
	        if( s[ x ] < 0 )
	            return x;
	        else
	            return s[ x ] = find( s[ x ] );
	    }
	}

    private Cell[][] cells;
    private DisjSets set;    
    private ArrayList<Wall> walls;
    private Random random;
    private int height, width, numElements, x_coord, y_coord, cellSize, selectedWall;
    
    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
        walls = new ArrayList<Wall>((height - 1) * (width - 1));
        generateMaze();
        setPreferredSize(new Dimension(800, 700));
    }
    
    private void generateMaze() {
        generateCells();
        set = new DisjSets(width * height);
        random = new Random();
        numElements = width * height;

        while (numElements > 1) {
            selectedWall = random.nextInt(walls.size());
            Wall temp = walls.get(selectedWall);
            int cellA = temp.currentcell.y + temp.currentcell.x * width;
            int cellB = temp.nextcell.y + temp.nextcell.x * width;

            if (set.find(cellA) != set.find(cellB)) {
                walls.remove(selectedWall);
                set.union(set.find(cellA), set.find(cellB));
                temp.deleted = true;
                temp.currentcell.adjacents.add(temp.nextcell);
                temp.nextcell.adjacents.add(temp.currentcell);
                numElements--;
            }
        }
    }
   
    private int cellNumber = 0;		// set to upper left cell

    // create all walls for each cell
    private void generateCells() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
                if (i == 0) {
                    cells[i][j].top = new Wall(cells[i][j]);
                } else {
                    cells[i][j].top = new Wall(cells[i - 1][j], cells[i][j]);
                    walls.add(cells[i][j].top);
                }
                if (i == height - 1) {
                    cells[i][j].bottom = new Wall(cells[i][j]);
                }
                if (j == 0) {
                    cells[i][j].left = new Wall(cells[i][j]);
                } else {
                    cells[i][j].left = new Wall(cells[i][j - 1], cells[i][j]);
                    walls.add(cells[i][j].left);
                }
                if (j == width - 1) {
                    cells[i][j].right = new Wall(cells[i][j]);
                }
                cells[i][j].cellNumber = cellNumber++;
            }
        }

        // set values for top left and bottom right cells
        cells[0][0].left.deleted = true;
        cells[0][0].cellNumber = 0;
        cells[height - 1][width - 1].right.deleted = true;
        cells[height - 1][width - 1].cellNumber = (height * width);
    }
	
	 public void paintComponent(Graphics g) {
        x_coord = 50;
        y_coord = 50;
        
        cellSize = (width - x_coord) / width + 7;
        
        int x = x_coord;
        int y = y_coord;

        for (int i = 0; i <= height - 1; i++) {
        	
            for (int j = 0; j <= width - 1; j++) {
            	// draw lines for top walls
                if (!(cells[i][j].top.deleted)) {
                    g.drawLine(x, y, x + cellSize, y);
                }
                // draw lines for left walls
                if (!(cells[i][j].left.deleted)) {
                    g.drawLine(x, y, x, y + cellSize);
                }
                // draw lines for bottom walls
                if ((i == height - 1) && !(cells[i][j].bottom.deleted)) {
                    g.drawLine(x, y + cellSize, x + cellSize,
                            y + cellSize);
                }
                // draw lines for right walls
                if ((j == width - 1) && !(cells[i][j].right.deleted)) {
                    g.drawLine(x + cellSize, y, x + cellSize,
                            y + cellSize);
                }
                x += cellSize;
            }
            x = x_coord;
            y += cellSize;
        }
	 }
    
    

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int h, w;
        System.out.print("Enter the width of your maze: ");
        h = userInput.nextInt();
        System.out.print("Enter the height of your maze: ");
        w = userInput.nextInt();
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(h+10, w+10);
        window.getContentPane().add(new Maze(h, w));
        window.pack();
        window.setVisible(true);
    }
    
    
}
