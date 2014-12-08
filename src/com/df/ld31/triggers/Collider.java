package com.df.ld31.triggers;

import com.df.ld31.triggers.*;
import com.df.ld31.Bitmap;
import com.df.ld31.level.Level;
import com.df.ld31.level.Player;

public class Collider extends Trigger
{

	public Collider(int x, int y, int w, int h)
	{
		super(x, y, w, h);
	}
	
	public void tick(Level level)
	{
		
		
	}
	
	public boolean collides(Player player)
	{
		boolean c = false;
		int xoff = this.x - player.x;
		int yoff = this.y - player.y;
		if(Math.abs(xoff) < this.w && Math.abs(yoff) < this.h)
			c = true;
		else
			return false;
			
		if(this.w - Math.abs(xoff) < this.h - Math.abs(yoff))
		{
			//System.out.println(xoff + " " + yoff);
			player.push(xoff < 0 ? - this.w - xoff : this.w - xoff, 0);
		}
		else
		{
			//System.out.println(xoff + " " + yoff);
			player.push(0, yoff < 0 ? -this.h - yoff : this.h - yoff);
		}
		
		return true;
	}
	
	public void render(Bitmap screen)
	{
		//screen.box(this.x - this.w, this.y - this.h, this.x + this.w, this.y + this.h, 0xff424EFF);
	}

}
