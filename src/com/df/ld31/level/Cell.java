package com.df.ld31.level;

import com.df.ld31.*;
import com.df.ld31.level.*;
import com.df.ld31.triggers.*;


public class Cell
{
	static public int TOP = 8;
	static public int RIGHT = 4;
	static public int BOTTOM = 2;
	static public int LEFT = 1;
	
	static public int TYPECORNER = 1;
	static public int TYPEN = 2;
	static public int TYPEFULL = 3;
	
	private boolean[] walls = new boolean[4];
	private Collider[] colliders = new Collider[8];
	
	int x, y;
			
			
	public Cell(int x, int y, int type, int rotation, Level level)
	{
		this.x = x;
		this.y = y;
		if(type == this.TYPEN)
		{
			this.walls[0] = true;
			this.walls[1] = true;
			this.walls[2] = false;
			this.walls[3] = true;
		}
		if(type == this.TYPEFULL)
		{
			this.walls[0] = true;
			this.walls[1] = true;
			this.walls[2] = true;
			this.walls[3] = true;
		}
		if(type == this.TYPECORNER)
		{
			this.walls[0] = true;
			this.walls[1] = false;
			this.walls[2] = false;
			this.walls[3] = true;
		}
		
		//Insanely stupid code ahead
		if(rotation == this.LEFT)
		{
			//1 shift
			boolean first = this.walls[0];
			for(int i =0; i<3; i++)
			{
				this.walls[i] = this.walls[i + 1];
			}
			this.walls[3] = first;
		}
		
		if(rotation == this.BOTTOM)
		{
			//2 shift
			boolean first = this.walls[0];
			for(int i =0; i<3; i++)
			{
				this.walls[i] = this.walls[i + 1];
			}
			this.walls[3] = first;
			
			first = this.walls[0];
			for(int i =0; i<3; i++)
			{
				this.walls[i] = this.walls[i + 1];
			}
			this.walls[3] = first;
		}
		
		if(rotation == this.RIGHT)
		{
			//3 shift
			boolean first = this.walls[0];
			for(int i =0; i<3; i++)
			{
				this.walls[i] = this.walls[i + 1];
			}
			this.walls[3] = first;
			
			first = this.walls[0];
			for(int i =0; i<3; i++)
			{
				this.walls[i] = this.walls[i + 1];
			}
			this.walls[3] = first;
			
			first = this.walls[0];
			for(int i =0; i<3; i++)
			{
				this.walls[i] = this.walls[i + 1];
			}
			this.walls[3] = first;
		}
		
		//Constructing walls
		if(this.walls[0])
		{
			this.colliders[0] = new Collider(x, y -26 + 2, 27, 4);
			level.triggers.add(this.colliders[0]);
		}
		if(this.walls[1])
		{
			this.colliders[2] = new Collider(x + 26 - 2, y, 4, 27);
			level.triggers.add(this.colliders[2]);
		}
		if(this.walls[2])
		{
			this.colliders[4] = new Collider(x, y + 26 - 2, 27, 4); 
			level.triggers.add(this.colliders[4]);
		}
		if(this.walls[3])
		{
			this.colliders[6] = new Collider(x - 26 + 2, y, 4, 27);
			level.triggers.add(this.colliders[6]);
		}
		
	}
	
	public void tick()
	{
		
		
	}
	
	public void render(Bitmap screen)
	{
		screen.draw(Art.a.tile[0][0], this.x - 26, this.y - 26);
		
		if(this.walls[0])
			screen.draw(Art.a.tile[1][0], this.x - 26, this.y - 26);
		if(this.walls[1])
			screen.draw(Art.a.tile[4][0], this.x - 26, this.y - 26);
		if(this.walls[2])
			screen.draw(Art.a.tile[3][0], this.x - 26, this.y - 26);
		if(this.walls[3])
			screen.draw(Art.a.tile[2][0], this.x - 26, this.y - 26);
		
		//doors
		if(this.colliders[1] != null)
		{
			screen.draw(Art.a.door[1][0], this.x - 6, this.y - 26 - 6);
		}
		if(this.colliders[3] != null)
		{
			screen.draw(Art.a.door[0][0], this.x + 26 - 6, this.y - 6);
			screen.box(this.x + 26 + 6, this.y + 6, 12, 12, 0xffff0000);
		}
		if(this.colliders[5] != null)
		{
			screen.draw(Art.a.door[1][0], this.x - 6, this.y + 26 - 6);
			screen.box(this.x - 6, this.y + 26 + 6, 12, 12, 0xffff0000);
		}
		if(this.colliders[7] != null)
		{
			screen.draw(Art.a.door[0][0], this.x - 26 - 6, this.y - 6);
		}
		
	}
	
	public void openwall(int side, Level level)
	{
		if(side == this.TOP && this.walls[0] && this.colliders[1] == null)
		{
			level.triggers.remove(this.colliders[0]);
			this.colliders[0] = new Collider(this.x - 17, this.y -26 + 2, 10, 4);
			this.colliders[1] = new Collider(this.x + 17, this.y - 26 + 2, 10, 4);
			level.triggers.add(this.colliders[0]);
			level.triggers.add(this.colliders[1]);
		}
		
		if(side == this.RIGHT && this.walls[1] && this.colliders[3] == null)
		{
			level.triggers.remove(this.colliders[2]);
			this.colliders[2] = new Collider(this.x + 26 - 2, this.y -17, 4, 10);
			this.colliders[3] = new Collider(this.x + 26 - 2, this.y + 17, 4, 10);
			level.triggers.add(this.colliders[2]);
			level.triggers.add(this.colliders[3]);
		}
		
		if(side == this.BOTTOM && this.walls[2] && this.colliders[5] == null)
		{
			level.triggers.remove(this.colliders[4]);
			this.colliders[4] = new Collider(this.x - 17, this.y + 26 - 2, 10, 4);
			this.colliders[5] = new Collider(this.x + 17, this.y + 26 - 2, 10, 4);
			level.triggers.add(this.colliders[4]);
			level.triggers.add(this.colliders[5]);
		}
		
		if(side == this.LEFT && this.walls[3] && this.colliders[7] == null)
		{
			level.triggers.remove(this.colliders[6]);
			this.colliders[6] = new Collider(this.x - 26 + 2, this.y -17, 4, 10);
			this.colliders[7] = new Collider(this.x - 26 + 2, this.y + 17, 4, 10);
			level.triggers.add(this.colliders[6]);
			level.triggers.add(this.colliders[7]);
		}
		
		
	}
	
	public void closewall(int side, Level level)
	{
		if(side == this.TOP && this.walls[0] && this.colliders[1] != null)	//if door is present
		{
			level.triggers.remove(this.colliders[0]);
			level.triggers.remove(this.colliders[1]);
			this.colliders[0] = new Collider(x, y -26 + 2, 27, 4);
			this.colliders[1] = null;
			level.triggers.add(this.colliders[0]);
		}
	}
}
