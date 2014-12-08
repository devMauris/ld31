package com.df.ld31.triggers;

import com.df.ld31.triggers.*;
import com.df.ld31.level.*;
import com.df.ld31.Bitmap;

public class Opener extends Trigger
{
	int cellx0 = 0, celly0 = 0, cellx1 = 0, celly1 = 0;
	int dir0 = Cell.LEFT, dir1 = Cell.RIGHT;
	protected boolean activated = false;
	
	public Opener(int x, int y, int w, int h, int cellx0, int celly0, int dir0, int cellx1, int celly1, int dir1)
	{
		super(x, y, w, h);
		this.cellx0 = cellx0;
		this.celly0 = celly0;
		this.cellx1 = cellx1;
		this.celly1 = celly1;
		this.dir0 = dir0;
		this.dir1 = dir1;
		
	}
	
	public void activate(Level level)
	{
		
		Cell cell0 = level.getCell(this.cellx0, this.celly0);
		Cell cell1 = level.getCell(this.cellx1, this.celly1);
		if(cell0 == null)
			return;
		
		cell0.openwall(dir0, level);
		if(cell1 == null)
			return;
		cell1.openwall(dir1, level);
		this.activated = true;
	}
	
	public void tick(Level level)
	{
		
		
	}
	
	public void render(Bitmap screen)
	{
		
	}
	
	public boolean collides(Player player)
	{
		return super.collides(player);
	}
}
