package main;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Stack<Location> pathLocation = new Stack<Location>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	public int[] directionPro = {1, 1, 1, 1};

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = null;
		next = null;
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if(stepCount==0){
			Location local = this.getLocation();
			ArrayList<Location> first = new ArrayList<Location>();
			first.add(local);
			pathLocation.push(local);
			crossLocation.add(first);
		}
		
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} else {
			goBack();
		}
	}

	private void goBack() {
		// TODO Auto-generated method stub
		if (crossLocation.size() <= 0) return;
			
		crossLocation.pop();
		pathLocation.pop();
			
		if (crossLocation.size() <= 0) return;
			
		Grid grid = getGrid();
		if (grid == null) return;
		ArrayList<Location> backWay = crossLocation.peek();
		Location backLocation = backWay.get(0);
		
		Location current = getLocation();
		
		int dir = current.getDirectionToward(backLocation);
		if (grid.isValid(backLocation)) {
			setDirection(dir);
			moveTo(backLocation);
			stepCount++;
		}
		else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(grid, current);
		
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		
		int []direction = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		
		for (int i = 0; i < 4; i++) {
			Location location = loc.getAdjacentLocation(direction[i]);
			if (gr.isValid(location)) {
				Actor actor = gr.get(location);
				if ((actor instanceof Rock) && actor.getColor().equals(Color.RED)) {
					next = location;
					valid.add(next);
					return valid;
				}
				else if (actor == null) {
					valid.add(location);
				}
			}
		}
		 
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> locations = new ArrayList<Location>();
		Location currentLocation = getLocation();
		locations = getValid(currentLocation);
		if (locations.size() > 0) return true;
		else return false;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		
		ArrayList<Location> validLocation = getValid(loc);
		
		int maxPro = 0;
		int dirIndex = 0;
		int index = 0;
		int LocIndex = 0;
		for (Location location : validLocation) {
			int dir = loc.getDirectionToward(location);
			if (directionPro[dir/90] > maxPro) {
				maxPro = directionPro[dir/90];
				dirIndex = dir/90;
				LocIndex = index;
			}
			index++;
		}
		
		if (validLocation.size() == 1) {
			next = validLocation.get(LocIndex);
			directionPro[dirIndex]++;
		}
		else {
			int randNUM = (int)(Math.random() * 10);
			if (randNUM >=0 && randNUM < 6) {
				next = validLocation.get(LocIndex);
			}
			else {
				next = validLocation.get(randNUM % validLocation.size());
				int dir = loc.getDirectionToward(next);
				dirIndex = dir / 90;
				directionPro[dirIndex]++;
			}
		}
		
		for( Location l : validLocation ){
			if( this.getDirection() == this.getLocation().getDirectionToward(l) ){
				next = l;
				int dire = loc.getDirectionToward(next);
				dirIndex = dire / 90;
				directionPro[dirIndex]++;
				break;
			}
		}
		
		if (gr.isValid(next)) {
			Actor actor = gr.get(next);
			
			if (actor instanceof Rock && actor.getColor().equals(Color.RED)) {
				isEnd = true;
			}
			
			moveTo(next);
			pathLocation.push(next);
			setDirection(loc.getDirectionToward(next));
			
			ArrayList<Location> temp = crossLocation.pop();
			temp.add(next);
			crossLocation.push(temp);
			
			ArrayList<Location> latest = new ArrayList<Location>();
			latest.add(next);
			crossLocation.push(latest);
			
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
