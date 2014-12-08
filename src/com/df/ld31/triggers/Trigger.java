package com.df.ld31.triggers;

import com.df.ld31.level.*;
import com.df.ld31.Bitmap;

public class Trigger
{
	public int x, y, w, h;
	
	public Trigger(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
			return true;
		
		return false;
	}
	
	public void render(Bitmap screen)
	{
		screen.box(this.x - this.w, this.y - this.h, this.x + this.w, this.y + this.h, 0xffffffff);
	}
	
}
