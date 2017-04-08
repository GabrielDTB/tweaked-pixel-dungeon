/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2017 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard;

import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.Room;
import com.watabou.utils.Random;

import java.util.HashMap;

public abstract class StandardRoom extends Room {
	
	public enum SizeCategories{
		
		NORMAL(4, 10),
		LARGE(10, 14),
		GIANT(14, 18);
		
		public final int minDim, maxDim;
		
		SizeCategories(int min, int max){
			minDim = min;
			maxDim = max;
		}
		
	}
	
	public SizeCategories sizeCat = SizeCategories.NORMAL;
	
	@Override
	public int minWidth() { return sizeCat.minDim; }
	public int maxWidth() { return sizeCat.maxDim; }
	
	@Override
	public int minHeight() { return sizeCat.minDim; }
	public int maxHeight() { return sizeCat.maxDim; }
	
	private static HashMap<Class<?extends StandardRoom>, Float> chances = new HashMap<>();
	
	static {
		chances.put(EmptyRoom.class, 24f);
		
		chances.put(WalkwayRoom.class,      1f);
		chances.put(BurnedRoom.class,       1f);
		chances.put(FissureRoom.class,      1f);
		chances.put(GrassyGraveRoom.class,  1f);
		chances.put(StripedRoom.class,      1f);
		chances.put(StudyRoom.class,        1f);
	}
	
	public static StandardRoom createRoom(){
		try{
			return Random.chances(chances).newInstance();
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}
	
}
