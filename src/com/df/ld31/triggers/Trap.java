package com.df.ld31.triggers;

import com.df.ld31.*;
import com.df.ld31.level.*;

public class Trap extends Trigger
{
	private boolean activated = false;
	public Trap(int x, int y, int w, int h)
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
		{
			player.kill();
			this.activated = true;
			return true;
		}
		return false;
	}
	
	public void render(Bitmap screen)
	{
		//screen.box(this.x - this.w, this.y - this.h, this.x + this.w, this.y + this.h, 0xffffd000);
		
		if(this.activated)
			screen.draw(Art.a.trigger[1][3], this.x - 10, this.y - 10);
		else
			screen.draw(Art.a.trigger[0][3], this.x - 10, this.y - 10);
	}
}
