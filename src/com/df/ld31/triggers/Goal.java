package com.df.ld31.triggers;

import com.df.ld31.triggers.*;
import com.df.ld31.level.*;
import com.df.ld31.*;
public class Goal extends Trigger
{
	private boolean stepped = false;
	public Goal(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	
	public void tick()
	{
		
		
	}
	
	public boolean collides(Player player)
	{
		boolean c = super.collides(player);
		if(c)
		{
			player.won = true;
			this.stepped = true;
		}
		return c;
	}
	
	public void render(Bitmap screen)
	{
		//screen.box(this.x - this.w, this.y - this.h, this.x + this.w, this.y + this.h, 0xff10A010);
		screen.draw(Art.a.gold, this.x - 32, this.y - 32);
	}
}
