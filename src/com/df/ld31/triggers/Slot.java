package com.df.ld31.triggers;

import com.df.ld31.*;
import com.df.ld31.level.*;
import com.df.ld31.triggers.*;

public class Slot extends Button
{
	private int key = Player.empty;
	private boolean clickedacc = false;
	
	
	public Slot(int x, int y, int w, int h, int cellx0, int celly0, int dir0, int cellx1, int celly1, int dir1, int key)
	{
		super(x, y, w, h, cellx0, celly0, dir0, cellx1, celly1, dir1);
		this.key = key;
	}
	
	public void tick(Level level)
	{
		if(this.clickedacc && !this.activated)
		{
			this.activate(level);
		}
		
	}
	
	public boolean collides(Player player)
	{
		boolean c = super.collides(player);
		int xoff = this.x - player.x;
		int yoff = this.y - player.y;
		if(c && this.clicked && Math.abs(xoff) < this.w*2 && Math.abs(yoff) < this.h*2)
		{
			//Take key?
			boolean take = false;
			for(int i=0; i<2; i++)
			{
				if(player.inventory[i] == this.key)
				{
					take = true;
					player.inventory[i] = Player.empty;
					break;
				}
				
			}
			if(take)
				this.clickedacc = true;
			else
				this.clickedacc = false;
		}
		else
		{
			this.clickedacc = false;
		}
		
		return c;
	}
	
	public void render(Bitmap screen)
	{
		screen.draw(Art.a.trigger[0][1], this.x - 10, this.y - 10);
		
	}
}
