package com.df.ld31.triggers;

import com.df.ld31.triggers.*;
import com.df.ld31.level.*;
import com.df.ld31.Art;
import com.df.ld31.Bitmap;

public class Button extends Opener
{
	protected boolean activated = false;
	protected boolean pressed;
	protected boolean clicked;
	protected boolean released;
	
	public Button(int x, int y, int w, int h, int cellx0, int celly0, int dir0, int cellx1, int celly1, int dir1)
	{
		super(x, y, w, h, cellx0, celly0, dir0, cellx1, celly1, dir1);
	}
	
	public boolean collides(Player player)
	{
		boolean c = false;
		int xoff = this.x - player.input.x;
		int yoff = this.y - player.input.y;
		int pxoff = this.x - player.x;
		int pyoff = this.y - player.y;
		if(Math.abs(xoff) < this.w && Math.abs(yoff) < this.h && Math.abs(pxoff) < this.w*2 && Math.abs(pyoff) < this.h*2)
		{
			this.pressed = player.input.b0;
			this.clicked = player.input.b0Clicked;
			this.released = player.input.b0Released;
			return true;
		}
		
		this.pressed = false;
		return false;
	}
	
	public void tick(Level level)
	{
		if(this.clicked && !this.activated)
		{
			this.activate(level);
			this.activated = true; //?
		}
		this.clicked = false;
		this.released = false;
	}
	
	public void render(Bitmap screen)
	{
			if(this.activated)
			{
				screen.draw(Art.a.trigger[1][0], this.x - 10, this.y - 10);
			}
			else
				screen.draw(Art.a.trigger[0][0], this.x - 10, this.y - 10);
	}
	
}
