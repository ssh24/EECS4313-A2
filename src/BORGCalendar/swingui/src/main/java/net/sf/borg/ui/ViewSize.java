/*
This file is part of BORG.
 
    BORG is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.
 
    BORG is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
 
    You should have received a copy of the GNU General Public License
    along with BORG; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 
Copyright 2003 by Mike Berger
 */
package net.sf.borg.ui;

import lombok.Data;

/**
 * ViewSize contains the data associated with a windows's position, size, and
 * maximization state It also contains the logic to convert this data to and
 * from a String so that it can be stored.
 */
@Data
class ViewSize {

	/**
	 * DockType stores the various dock statuses for a view - always DOCK,
	 * always UNDOCK, or NOT_SET - whoch means to use the default preference
	 */
	static public enum DockType {
		DOCK, UNDOCK
	}

	/**
	 * creates a ViewSize instance From a string.
	 * 
	 * @param s
	 *            the string
	 * 
	 * @return the ViewSize instance
	 */
	static public ViewSize fromString(String s) {
		ViewSize vs = new ViewSize();
		String toks[] = s.split(",");
		vs.x = Integer.parseInt(toks[0]);
		vs.y = Integer.parseInt(toks[1]);
		vs.width = Integer.parseInt(toks[2]);
		vs.height = Integer.parseInt(toks[3]);
		if (toks[4].equals("Y"))
			vs.maximized = true;
		else
			vs.maximized = false;
		if (toks.length > 5) {
			try {
				vs.dock = DockType.valueOf(toks[5]);
			} catch (Exception e) {
				vs.dock = DockType.DOCK;
			}
		}

		return (vs);
	}


	// size and position
	private int height = -1;
	private int width = -1;
	private int x = -1;
	private int y = -1;
	private boolean maximized = false;

	// state
	private DockType dock = DockType.DOCK;

	
	/**
	 * get the ViewSize data in a string
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {

		return (Integer.toString(x) + "," + Integer.toString(y) + ","
				+ Integer.toString(width) + "," + Integer.toString(height)
				+ "," + ((maximized == true) ? "Y" : "N") + "," + dock
				.toString());
	}
}
