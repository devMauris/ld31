package com.df.ld31;

import java.awt.*;
import java.util.*;
import java.awt.image.*;

import javax.swing.JFrame;

import com.df.ld31.Game;
import com.df.ld31.Bitmap;

public class ld31 extends Canvas implements Runnable
{
	private InputHandler inputhandler;
	private Input input;
	private boolean g_done = false;
	
	private BufferedImage screenImage; //screen image
	private Bitmap screenBitmap;
	
	private int width = 800;
	private int height = 640;
	private Game game;
	
	

	public ld31()
	{
		this.inputhandler = new InputHandler(this);
		
		Dimension size = new Dimension(this.width, this.height);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}
	
	public void init()
	{
		Art.init();
		
		this.screenImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		this.screenBitmap = new Bitmap(this.screenImage);
		
		this.input = this.inputhandler.updateMouseStatus();
		this.game = new Game(this.input, this.width, this.height);
		
		//creating player view
		requestFocus();
	}
	
	public void stop()
	{
		this.g_done = true;
	}
	
	public void start()
	{
		new Thread(this, "Game thread").start();
	}
	
	public void run()
	{
		this.init();
		
		double nsPerFrame = 1000000000.0 / 60.0;
		double unprocessedTime = 0;
		double maxSkipFrames = 10;

		long lastTime = System.nanoTime();
		long lastFrameTime = System.currentTimeMillis();
		int frames = 0;
		
		
		while(!this.g_done)
		{
			long now = System.nanoTime();
			double passedTime = (now - lastTime) / nsPerFrame;
			lastTime = now;

			if (passedTime < -maxSkipFrames) passedTime = -maxSkipFrames;
			if (passedTime > maxSkipFrames) passedTime = maxSkipFrames;

			unprocessedTime += passedTime;
			while(unprocessedTime > 1)
			{
				unprocessedTime --;
				
				this.input = this.inputhandler.updateMouseStatus();
				if(this.input.typed != "")
				{
					//System.out.println(this.keyboard.typed);
				}
				
				this.game.tick();
				
				
			}
			//RENDER
			this.game.render(this.screenBitmap);
			frames++;
			
			
			if (System.currentTimeMillis() > lastFrameTime + 1000) 
			{
				//System.out.println(frames + " fps");
				lastFrameTime += 1000;
				frames = 0;
			}
			
			//OMG JAVA TOO FAST!
			try
			{
				Thread.sleep(1);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			swap();
		}
		
	}
	
	
	
	private void swap() 
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		int screenW = getWidth();
		int screenH = getHeight();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenW, screenH);
		g.drawImage(screenImage, (screenW - this.width) / 2, (screenH - this.height) / 2, this.width, this.height, null);
		g.dispose();

		bs.show();
	}
	
	public static void main(String[] args) 
	{
		ld31 gameComponent = new ld31();
		JFrame frame = new JFrame("ld31");
		frame.add(gameComponent);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		gameComponent.start();
	}
}
