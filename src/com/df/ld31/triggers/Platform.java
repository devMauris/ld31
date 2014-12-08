package com.df.ld31.triggers;

import com.df.ld31.triggers.*;
import com.df.ld31.level.*;
import com.df.ld31.*;

public class Platform extends Opener
{
	private boolean stepped = false;
	public Platform(int x, int y, int w, int h, int cellx0, int celly0, int dir0, int cellx1, int celly1, int dir1)
	{
		super(x, y, w, h, cellx0, celly0, dir0, cellx1, celly1, dir1);
	}
	
	public void tick(Level level)
	{
		if(this.stepped && !this.activated)
		{
			super.activate(level);
		}
		
		
	}
	
	public boolean collides(Player player)
	{
		boolean c = super.collides(player);
		if(c)
			this.stepped = true;
		else
			this.stepped = false;
		return c;
		
	}
	
	public void render(Bitmap screen)
	{
		if(this.activated)
			screen.box(this.x - this.w, this.y - this.h, this.x + this.w, this.y + this.h, 0xff60604F);
		else
			screen.box(this.x - this.w, this.y - this.h, this.x + this.w, this.y + this.h, 0xffDBD8B6);
	}
	
	
}
