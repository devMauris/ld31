package com.df.ld31.triggers;

import com.df.ld31.*;
import com.df.ld31.level.*;
import com.df.ld31.triggers.*;

public class Dispenser extends Trigger
{
	private int key;
	private boolean activated = false;
	
	public Dispenser(int x, int y, int w, int h, int key)
	{
		super(x, y, w, h);
		this.key = key;
	}
	
	public void tick(Level level)
	{
		
		
	}
	
	public boolean collides(Player player)
	{
		boolean c = super.collides(player);
		if(c && !this.activated)
		{
			//Give key?
			boolean gave = false;
			for(int i=0; i<2; i++)
			{
				if(player.inventory[i] == Player.empty)
				{
					gave = true;
					player.inventory[i] = this.key;
					break;
				}
				
			}
			if(gave)
				this.activated = true;
		}
		
		return c;
	}
	public void render(Bitmap screen)
	{
		screen.draw(Art.a.trigger[0][2], this.x - 10, this.y - 10);
		if(!this.activated)
		{
			screen.draw(Art.a.key[this.key][0], this.x - 4, this.y - 6);
		}
		
	}
	
}
