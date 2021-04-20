package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//select a random cell to start
		int rx = randGen.nextInt(maze.getWidth());
		int ry = randGen.nextInt(maze.getHeight());
		
		//call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(rx, ry));
		
		return maze;
	}

	private static void selectNextPath(Cell currentCell) {
		// mark it as visited
		currentCell.setBeenVisited(true);

		// check for unvisited neighbors
		ArrayList<Cell> unvNbrs = getUnvisitedNeighbors(currentCell);
		// if has unvisited neighbors,
		if (unvNbrs.size() > 0) {
			// select one at random.
			Cell newCell = unvNbrs.get(randGen.nextInt(unvNbrs.size()));
			// push it to the stack
			uncheckedCells.push(newCell);
			// remove the wall between the two cells
			removeWalls(currentCell, newCell);
			// make the new cell the current cell and mark it as visited
			currentCell = newCell;
			selectNextPath(currentCell);
			
			// if all neighbors are visited
		} else {
			//if the stack is not empty
			if(!uncheckedCells.isEmpty()){
				// pop a cell from the stack
				// make that the current cell
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}
	}

	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getRow() == c2.getCol()) {
			if (c1.getRow() > c2.getCol()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			} else {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
			}
		} else {
			if (c1.getRow() > c2.getCol()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			} else {
				c2.setWestWall(false);
				c1.setEastWall(false);
			}
		}
	}

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		int x = c.getRow();
		int y = c.getCol();

		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

		if (x > 0 && !maze.getCell(x - 1, y).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(x - 1, y));
		}

		if (y > 0 && !maze.getCell(x, y - 1).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(x, y - 1));
		}

		if (x < width - 1 && !maze.getCell(x + 1, y).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(x + 1, y));
		}

		if (y < height - 1 && !maze.getCell(x, y + 1).hasBeenVisited()) {
			unvisitedNeighbors.add(maze.getCell(x, y + 1));
		}

		return unvisitedNeighbors;
	}
}