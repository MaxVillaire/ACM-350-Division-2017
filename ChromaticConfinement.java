/*
 * Solution to ACM 350 Division contest question - Chromatic Confinement
 * Question found here: https://www.hackerrank.com/contests/usc-acm-fall-2017-350-division/challenges/chromatic-confinement
 * This solution works for all input constraints
 * Input: A list of N approved colors, followed by a list of K colors which may or may not be approved
 * Output: A list of length K where all K colors were replaced with their closest counterpart on list N
 */


import java.util.ArrayList;
import java.util.Scanner;

public class ChromaticConfinement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<Color> approved = new ArrayList<Color>();
		ArrayList<Color> banned = new ArrayList<Color>();
		int N = sc.nextInt(); // N will be the array of approved
		int K = sc.nextInt(); // K is the array of banned colors
		//Banned colors need to output the nearest approved color
		sc.nextLine();
		for(int i=0; i<N; i++) { // Initialize approved list
			//colors contain 3 int values 0-255 representing r-g-b colors
			String[] colors = sc.nextLine().split(",");
			approved.add(new Color(Integer.parseInt(colors[0]), 
					Integer.parseInt(colors[1]), 
					Integer.parseInt(colors[2])));
		}
		for(int i=0; i<K; i++) { // Initialize banned list
			String[] colors = sc.nextLine().split(",");
			banned.add(new Color(Integer.parseInt(colors[0]), 
					Integer.parseInt(colors[1]), 
					Integer.parseInt(colors[2])));
		}
		for(int i=0; i<K; i++) {
			//This will find the closest approved color and send it to the print method
			print(banned.get(i).findClosest(approved));
		}
		
	}
	public static void print(Color a){
		//Prints a color in the correct output format
		System.out.println(a.r+","+a.g+","+a.b);
	}
	static class Color {
		//Color contains 3 integers
		public int r,g,b;
		public Color() {
			r=0;
			g=0;
			b=0;
		}
		public Color(int r1, int g1, int b1) {
			//This is the only constructor that gets used
			r=r1;
			g=g1;
			b=b1;
		}
		public int distSq(Color a) {
			//Returns the distance between two colors
			//Distances are left squared since they are used only for the sake of comparison
			int retVal = (r-a.r)*(r-a.r);
			retVal += (g-a.g)*(g-a.g);
			retVal += (b-a.b)*(b-a.b);
			return retVal;
		}
		public Color findClosest(ArrayList<Color> a) {
			//Finds the closest distance from this.color to one on the list a
			//returns the color on the list which is closest to this.color
			int shortestDistance = Integer.MAX_VALUE;
			int index = 0;
			for(int i=0; i<a.size(); i++) {
				//Closest color determined by comparing the distances
				if(shortestDistance > this.distSq(a.get(i))) {
					shortestDistance = this.distSq(a.get(i));
					index = i;
				}
			}
			//Return the color that we determined to be closest
			return a.get(index);
		}
	}	
}

