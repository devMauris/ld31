package com.df.ld31;

import java.io.File;
import com.df.ld31.*;
import com.df.ld31.level.*;
public class Game
{
	private Input input;
	
	private Level level;
	
	
	public Game(Input input, int width, int height)
	{
		this.input = input;
		this.level = new Level(input, 160, 0);
	}
	
	
	public void tick()
	{
		this.level.tick();
		if(this.level.getPlayer().restart)
		{
			this.level = new Level(input, 160, 0);
		}
		
		if(this.level.getPlayer().won)
		{
			
			
		}
		
	}
	
	public void render(Bitmap screen)
	{
		screen.clear(0xFF4C4C3F);
		
		this.level.render(screen);
		screen.fill(10, 40, 70, 100, 0xffB7B796);
		screen.fill(80, 40, 140, 100, 0xffB7B796);
		screen.draw(Art.a.bigkey[this.level.getPlayer().inventory[0]][0], 12, 42);
		screen.draw(Art.a.bigkey[this.level.getPlayer().inventory[1]][0], 82, 42);
		
		if(this.level.getPlayer().dead)
			screen.draw(Art.a.loss, 0, 0);
		if(this.level.getPlayer().won)
			screen.draw(Art.a.win, 0, 0);
		
		screen.draw(Art.a.splash, 450, -60);
		
		
	}
}
