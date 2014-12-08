package com.df.ld31.level;

import com.df.ld31.*;


public class Player
{
	public int x,y,vx,vy;
	public Input input;
	public int score = 0;
	public boolean dead = false;
	public boolean restart = false;
	public boolean won = false;
	
	public static int empty = 0;
	public static int key1 = 1;
	public static int key2 = 2;
	public static int key3 = 3;
	public static int key4 = 4;
	public static int key5 = 5;
	public static int key6 = 6;
	public static int key7 = 7;
	
	private double walk = 0.0;
	private double death = 0.0;
	private int frame = 0;
	
	public int[] inventory = new int[2];
	
	public Player(Input input, int x, int y, int vx, int vy)
	{
		this.input = input;
		this.x =  x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.inventory[0] = this.empty;
		this.inventory[1] = this.empty;
	}
	
	public void push(int x, int y)
	{
		this.vx = 0;
		this.vy = 0;
		this.x -= x;
		this.y -= y;
	}
	
	public void tick()
	{
		//Fixed timestep
		this.x += this.vx;
		this.y += this.vy;
		int newvx = 0;
		int newvy = 0;
		
		if(!this.dead)
		{
			if(this.input.down.down)
				newvy += 1;
			if(this.input.up.down)
				newvy -= 1;
			if(this.input.right.down)
				newvx += 1;
			if(this.input.left.down)
				newvx -= 1;
		}
		this.vx = newvx;
		this.vy = newvy;
		if(Math.abs(this.vx) > 0 || Math.abs(this.vy) > 0)
		{
			this.walk += 0.1;
			if(this.walk > 2.0)
				this.walk = 0.0;
		}
		if(this.dead)
		{
			this.death += 0.1;
			if(this.death > 2.0)
				this.death = 2.0;
		}
		if(this.input.restart.typed)
			this.restart = true;
		
		
	}
	
	public void render(Bitmap screen)
	{
		//screen.box(this.x-8/2, this.y - 12, this.x + 8/2, this.y, 0xffffffff);
		this.frame = (int) this.walk;
		if(this.dead)
			this.frame = (int) this.death;
		if(!this.dead)
			screen.draw(Art.a.man[this.frame][0], this.x - 5, this.y - 16);
		else
			screen.draw(Art.a.man[this.frame + 1][0], this.x - 5, this.y - 16);
		
		
		
	}
	
	public void kill()
	{
		this.dead = true;
	}
	
}
