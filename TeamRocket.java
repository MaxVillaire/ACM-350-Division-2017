import java.util.Arrays;
import java.util.Scanner;

public class TeamRocket {
	
	//Infinity for the purposes of this problem
	public static final int oo = Integer.MAX_VALUE-1000;
	//board holds the input characters
	public static char[][] board;
	//steps will hold the minimum number of steps to reach that point
	public static int[][] steps;
	//size is the size of the current floor
	public static int size;
	//shortPath is the total number of steps for the shortest path across floors
	public static int shortPath;
	
	
	public static int min(int a, int b, int c, int d) {
		//returns the minimum of these 4 integers
        return Math.min(Math.min(a,b), Math.min(c,d));
	}
	
	
	public static int shortestPath(int x, int y, int len) {
		if(x>=size || y>=size || x<0 || y<0) {
			//Out of bounds check
			return oo;
		} else if(len >= steps[y][x]) {
			//If you are on a long/duplicate path, stop following it
			return oo;
		} else if(board[y][x] == '#') {
			//We are currently standing on a wall, which is not possible
			return oo;
		} else if(board[y][x] == ' ') {
			//We can take a step here with no issues
			//Record the number of steps to this point
			steps[y][x] = len;
			//Try taking a step to all surrounding tiles - return the minimum of this
			return min(shortestPath(x+1,y,len+1),shortestPath(x-1,y,len+1),shortestPath(x,y+1,len+1),shortestPath(x,y-1,len+1));
		} else if(board[y][x] == 's' || board[y][x] == 'p') {
			steps[y][x] = len;
			//Exit found
			return len;
		} else if(board[y][x] == 'w') {
			steps[y][x] = len;
			//we hit a warp point, fist find the other warp point
			int wx = 0;
			int wy = 0;
			for(int ys=0; ys<size;ys++) {
				for(int xs=0; xs<size;xs++) {//Check every index, make sure you aren't at the same warp point
					if(board[ys][xs] == 'w' && (xs != x || ys != y)) {
						//Found it, now record the indecies
						wx = xs;
						wy = ys;
						break;
					}
				}
			}//Now we have found the warp point
            //Try taking a step in every direction and return the minimum
			return min(shortestPath(wx+1,wy,len+1),shortestPath(wx-1,wy,len+1),shortestPath(wx,wy+1,len+1),shortestPath(wx,wy-1,len+1));
		} else {
			return oo;
		}
	}
	
	
	public static void main(String[] args) {
		//Initialize shortPath
		shortPath = 0;
		//Scanner to read inputs
		Scanner sc = new Scanner(System.in);
		//First input N is the number of floors to search through
		int numFloors = sc.nextInt();
		int[] sizeFloor = new int[numFloors];
		//Read in the size of each floor. floors are n x n
		for(int i=0; i<numFloors; i++) {
			sizeFloor[i] = sc.nextInt();
		}
		//We need to iterate through each floor
		for(int floorInd=0; floorInd<numFloors; floorInd++) {
			//Skip the blank line of input
			sc.nextLine();
			//Get the size
			size = sizeFloor[floorInd];
			//create the board
			board = new char[size][size];
			for(int i=0; i<size; i++) {
				//Read in the next line as a character array
				char[] temp = sc.nextLine().toCharArray();
				for(int j=0; j<size; j++) {
					//Set the arrays equal to each other
					board[i][j] = temp[j];
				}
			}//The board is now filled
			//Initialize steps
			steps = new int[size][size];
			for(int i=0; i<size; i++) {
				Arrays.fill(steps[i], oo);
			}
			//Find entrance
			int px = 0;
			int py = 0;
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {//Iterate through the board
					if(board[i][j] == 'e') {
						//Found it, record the indecies
						px = j;
						py = i;
						break;
					}
				}
			}
			//This will try taking a step to each of the tiles around the entrance, and return the minimum number of steps to the exit
			shortPath += min(shortestPath(px+1,py,1),shortestPath(px-1,py,1),shortestPath(px,py+1,1),shortestPath(px,py-1,1));
		}
		System.out.println(shortPath);
		sc.close();
	}
	
	
}