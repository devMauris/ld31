package com.df.ld31.level;

import java.util.*;

import com.df.ld31.Art;
import com.df.ld31.Bitmap;
import com.df.ld31.Input;
import com.df.ld31.level.*;
import com.df.ld31.triggers.*;

public class Level
{
	private int x = 0, y = 0;
	private Player player;
	private Input input;
	
	public Cell test;
	public Cell test2;
	public Cell test3;
	private Cell[][] map = new Cell[12][12];
	
	public List<Trigger> triggers = new ArrayList<Trigger>();
	
	public Level(Input input, int x, int y)
	{
		this.input = input;
		this.x = x;
		this.y = y;
		this.player = new Player(input, this.x + 26, this.y + 598, 0, 0);
		
		this.loadmap(Art.a.map);
		this.loadTriggers();
	}
	
	public void tick()
	{
		this.player.tick();
		for(int i =0; i<this.triggers.size(); i++)
		{
			Trigger t = this.triggers.get(i);
			if(t.collides(this.player))
			{
				//System.out.println("C");
			}
			t.tick(this);
		}
		for(int i =0; i<12; i++)
			for(int j =0; j<12; j++)
				if(this.map[i][j] != null)
					this.map[i][j].tick();
		
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public Cell getCell(int x, int y)
	{
		return this.map[x][y];
	}
	
	public void addCell(int x, int y, int type, int rotation)
	{
		this.map[x][y] = new Cell(this.x + x*52 + 26, this.y + y*52 + 26, type, rotation, this);
	}
	
	private void loadmap(Bitmap mapimg)
	{
		//A0A0 - Cell.TYPEFULL
		//A0FF - Cell.TYPECORNER
		//A000 - CELL.N
		//00 - top
		//55 - right
		//A0 - bottom
		//FF - left
		for(int i =0; i<mapimg.w; i++)
			for(int j =0; j<mapimg.h; j++)
			{
				int pix = mapimg.pixels[j*mapimg.w + i];
				int type = pix >> 8 & 0x00ffff;
				int rotation = pix & 0x000000ff;
				switch(type)
				{
				case 0xa0a0:
					type = Cell.TYPEFULL;
					break;
				
				case 0xa0ff:
					type = Cell.TYPECORNER;
					break;
				
				case 0xa000:
					type = Cell.TYPEN;
					break;
				}
				switch(rotation)
				{
				case 0x00:
					rotation = Cell.TOP;
					break;
					
				case 0x55:
					rotation = Cell.RIGHT;
					break;
					
				case 0xa0:
					rotation = Cell.BOTTOM;
					break;
				case 0xff:
					rotation = Cell.LEFT;
					break;
				}
				
				if(pix!=0)
				{
					this.addCell(i, j, type, rotation);
				}
				
			}
		
		
	}
	
	public void render(Bitmap screen)
	{
		
		/*
		for(int i =0; i<12; i++)
			for(int j =0; j<12; j++)
				screen.box(i*52 + this.x, j*52 + this.y, i*52 + 52 + this.x, j*52 + 52 + this.y, 0xffffffff);
		*/
		for(int i =0; i<12; i++)
			for(int j =0; j<12; j++)
				if(this.map[i][j] != null)
					this.map[i][j].render(screen);
		
		for(int i =0; i<this.triggers.size(); i++)
		{
			Trigger t = this.triggers.get(i);
			t.render(screen);
		}
		
		//player always on top
		this.player.render(screen);
	}
	
	
	private void loadTriggers()
	{
		//Goal
		this.triggers.add(new Goal(this.x + 52*5, this.y + 52*5, 40,40));
				
		
		//Tutorial platform
		this.triggers.add(new Platform(this.x + 26, this.y + 11*52 - 26, 10, 10, 0, 10, Cell.RIGHT, 1, 10, Cell.LEFT));
		
		this.triggers.add(new Platform(this.x + 52 *2 + 36, this.y + 11*52 - 26, 10, 10, 2, 10, Cell.RIGHT, 3, 10, Cell.LEFT));
		this.triggers.add(new Button(this.x + 52*4 - 4, this.y + 10*52 + 40, 10, 10, 3, 10, Cell.BOTTOM, 3, 11, Cell.TOP));
		this.triggers.add(new Dispenser(this.x + 26, this.y + 52*12 - 6, 10, 10, Player.key1));
		this.triggers.add(new Dispenser(this.x + 52*3 + 26, this.y + 52*12 - 6, 10, 10, Player.key2));
		this.triggers.add(new Slot(this.x + 52*3 + 10, this.y + 52*10 + 10, 10, 10, 3, 10, Cell.TOP, 3, 9, Cell.BOTTOM, Player.key2));
		this.triggers.add(new Button(this.x + 52*4 - 4, this.y + 9*52 + 10, 10, 10, 3, 10, Cell.RIGHT, 4, 10, Cell.LEFT));
		this.triggers.add(new Button(this.x + 52*6 - 4, this.y + 10*52 + 40, 10, 10, 5, 10, Cell.RIGHT, 6, 10, Cell.LEFT));
		this.triggers.add(new Platform(this.x + 52 *6 + 36, this.y + 11*52 - 26, 10, 10, 6, 11, Cell.TOP, 6, 10, Cell.BOTTOM));
		this.triggers.add(new Platform(this.x + 52 *6 + 36, this.y + 11*52 - 26, 10, 10, 6, 11, Cell.TOP, 6, 10, Cell.BOTTOM));
		this.triggers.add(new Slot(this.x + 52*7 + 10, this.y + 52*11 + 10, 10, 10, 7, 11, Cell.RIGHT, 8, 11, Cell.LEFT, Player.key1));
		this.triggers.add(new Dispenser(this.x + 52*8 + 26, this.y + 52*12 - 6, 10, 10, Player.key3));
		
		this.triggers.add(new Slot(this.x + 10, this.y + 52*10 + 10, 10, 10, 0, 10, Cell.TOP, 0, 9, Cell.BOTTOM, Player.key3));
		this.triggers.add(new Button(this.x + 52 - 4, this.y + 8*52 + 40, 10, 10, 0, 8, Cell.TOP, 0, 7, Cell.BOTTOM));
		this.triggers.add(new Platform(this.x + 52 + 26, this.y + 7*52 - 36, 10, 10, 1, 6, Cell.TOP, 1, 5, Cell.BOTTOM));
		this.triggers.add(new Button(this.x + 52*2 - 4, this.y + 4*52 + 10, 10, 10, 1, 4, Cell.TOP, 1, 3, Cell.BOTTOM));
		this.triggers.add(new Platform(this.x + 52 + 36, this.y + 3*52 - 36, 10, 10, 1, 2, Cell.LEFT, 0, 2, Cell.RIGHT));
		this.triggers.add(new Dispenser(this.x + 26, this.y + 52*3 - 6, 10, 10, Player.key4));
		
		this.triggers.add(new Slot(this.x + 52 * 6 + 10, this.y + 52*10 + 10, 10, 10, 6, 10, Cell.TOP, 6, 9, Cell.BOTTOM, Player.key4));
		this.triggers.add(new Platform(this.x + 52 * 7 + 36, this.y + 10*52 - 36, 10, 10, 7, 9, Cell.RIGHT, 8, 9, Cell.LEFT));
		this.triggers.add(new Button(this.x + 52*9 - 4, this.y + 8*52 + 40, 10, 10, 8, 8, Cell.LEFT, 7, 8, Cell.RIGHT));
		this.triggers.add(new Dispenser(this.x + 52*7 + 26, this.y + 52*9 - 6, 10, 10, Player.key5));
		
		this.triggers.add(new Slot(this.x + 10, this.y + 52*6 + 10, 10, 10, 0, 8, Cell.RIGHT, 1, 8, Cell.LEFT, Player.key5));
		
		this.triggers.add(new Dispenser(this.x + 52 + 26, this.y + 52*9 - 6, 10, 10, Player.key7));
		
		this.triggers.add(new Slot(this.x + 52 + 10, this.y + 52*4 + 10, 10, 10, 1, 4, Cell.RIGHT, 2, 4, Cell.LEFT, Player.key7));
		this.triggers.add(new Platform(this.x + 52 * 3 + 36, this.y + 5*52 - 36, 10, 10, 3, 4, Cell.RIGHT, 4, 4, Cell.LEFT));
		
		
		this.triggers.add(new Trap(this.x + 52*2, this.y + 11*52 - 26, 10, 10));
		this.triggers.add(new Trap(this.x + 52*1 + 26, this.y + 11*52 - 16, 10, 10));
		this.triggers.add(new Trap(this.x + 52*3 + 13, this.y + 11*52 - 13, 10, 10));
		this.triggers.add(new Trap(this.x + 52*3 + 26, this.y + 10*52 - 26, 10, 10));
		this.triggers.add(new Trap(this.x + 52*5 + 26, this.y + 11*52 - 16, 10, 10));
		this.triggers.add(new Trap(this.x + 52*4 + 26, this.y + 11*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 52*6 + 26, this.y + 12*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 52*7 + 26, this.y + 12*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 52*6 + 16, this.y + 10*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 52*7, this.y + 10*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 52*8 + 26, this.y + 9*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 26, this.y + 9*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 26, this.y + 7*52, 10, 10));
		this.triggers.add(new Trap(this.x + 52, this.y + 7*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 52 + 26, this.y + 8*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 52 + 36, this.y + 6*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 52 + 26, this.y + 4*52 - 27, 10, 10));
		
		this.triggers.add(new Trap(this.x + 52*3, this.y + 5*52 - 27, 10, 10));
		this.triggers.add(new Trap(this.x + 52*3 - 27, this.y + 5*52 - 16, 10, 10));
	}
}
