/*
    A simple bowling score calculator
    
    Copyright (C) 2020 Sylvain Hallé
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.
    
    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import stev.bowling.Frame;
import stev.bowling.Game;
import stev.bowling.LastFrame;
import stev.bowling.NormalFrame;

/**
 * Simple file showing the usage of the {@link Game} and {@link Frame} objects
 * to calculate and display the score in a bowling game. 
 */
public class GameDemo
{
	/**
	 * The main method of the program
	 * @param args Command line arguments (ignored)
	 */
	public static void main(String[] args)
	{
		Game g = new Game();
		g.addFrame(new NormalFrame(0).setPinsDown(1, 8).setPinsDown(2, 2));
		System.out.println(g);
	}


}
