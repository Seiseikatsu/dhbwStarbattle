package com.starbattle.mapeditor.map;

public class AutotilePlacement {

	public static Tile getAutotileID(boolean[] neighbours, Tile center) throws Exception {
		int id = 0;

		/*
		 * neighbour ids
		 * 
		 * 4 = target tile
		 * 
		 * 0 1 2 
		 * 3 4 5
		 * 6 7 8
		 * 
		 * true if same tile
		 * 
		 * autotile ids
		 * 
		 * 0 = unused 
		 * 1= inner corner top left
		 *  2= inner corner top right
		 *  3= single block 
		 *  4= inner corner bottom left
		 *   5= inner corner bottem right
		 * 6= left top corner 
		 * 7= top 
		 * 8= right top corner
		 *  9= left 
		 *  10= center 
		 *  11=right
		 *   12= left bottom corner
		 *    13= bottom 
		 *    14= right bottom corner
		 */

		// count missing sides
		int anzMissing = 0;
		for (int i = 1; i < 8; i += 2) {
			if (!neighbours[i]) {
				anzMissing++;
			}
		}

		if (anzMissing >= 3) {// single block
			id = 3;

		} else if (anzMissing == 2) {// must be corner block
			if (neighbours[1]) {
				// top
				if (neighbours[3]) {// left
					id = 14;
				}
				if (neighbours[5]) {
					// right
					id = 12;

				}
			}
			if (neighbours[3]) {
				// left
				if (neighbours[7]) {
					// bottom
					id = 8;
				}
			}
			if (neighbours[7]) {
				// bottom
				if (neighbours[3]) {// left
					id = 8;
				}
				if (neighbours[5]) {
					// right
					id = 6;
				}
			}
		} else if (anzMissing == 1) {// must be side block
			if (!neighbours[1]) {
				// top
				id = 7;
			} else if (!neighbours[3]) {
				// left
				id = 9;
			} else if (!neighbours[5]) {
				// right
				id = 11;
			} else if (!neighbours[7]) {
				// bottom
				id = 13;
			}
		} else {// must be center block

			// check inner corners
			if (!neighbours[0] && neighbours[2] && neighbours[6] && neighbours[8]) {// top
																						// left
				id = 5;
			} else if (neighbours[0] && !neighbours[2] && neighbours[6] && neighbours[8]) {// top
																								// right
				id = 4;
			} else if (neighbours[0] && neighbours[2] && !neighbours[6] && neighbours[8]) {// bottom
																								// left
				id = 2;
			} else if (neighbours[0] && neighbours[2] && neighbours[6] && !neighbours[8]) {// bottom
																								// right
				id = 1;
			} else {
				// true center tile
		
				id = 10;
			}
		}

		// convert id to x and y addition
		
	
		int tix=id%3;
		int tiy=id/3-1;
		if(tix<0||tix>2||tiy<-1||tiy>3)
		{
			throw new Exception("Out of bounds!");
		}
		return new Tile(center.getAutox()+tix,center.getAutoy()+tiy,center.getAutox(),center.getAutoy());
	}

}
