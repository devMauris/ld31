package com.df.ld31;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Art
{
	public static Art a;

	public static void init()
	{
		a = new Art();
	}
	
	public Bitmap map = load("/map.png");
	public Bitmap tile[][] = loadAndCut("/tile.png", 52, 52);
	public Bitmap door[][] = loadAndCut("/door.png", 12, 12);
	public Bitmap man[][] = loadAndCut("/man.png", 10, 16);
	public Bitmap trigger[][] = loadAndCut("/trigger.png", 20, 20);
	public Bitmap key[][] = loadAndCut("/key.png", 8, 8);
	public Bitmap bigkey[][] = loadAndCut("/bigkey.png", 48, 48);
	public Bitmap gold = load("/gold.png");
	public Bitmap win = load("/win.png");
	public Bitmap loss = load("/loss.png");
	public Bitmap splash = load("/splash.png");

	public static Bitmap[][] loadAndCut(String name, int sw, int sh)
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read(Art.class.getResource(name));
		}
		catch (IOException e)
		{
			throw new RuntimeException("Failed to load " + name);
		}

		int xSlices = img.getWidth() / sw;
		int ySlices = img.getHeight() / sh;

		Bitmap[][] result = new Bitmap[xSlices][ySlices];
		for(int x = 0; x < xSlices; x++)
		{
			for(int y = 0; y < ySlices; y++)
			{
				result[x][y] = new Bitmap(sw, sh);
				img.getRGB(x * sw, y * sh, sw, sh, result[x][y].pixels, 0, sw);
			}
		}
		return result;
	}

	public static Bitmap load(String name)
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read(Art.class.getResource(name));
		}
		catch (IOException e)
		{
			throw new RuntimeException("Failed to load " + name);
		}

		int sw = img.getWidth();
		int sh = img.getHeight();

		Bitmap result = new Bitmap(sw, sh);
		img.getRGB(0, 0, sw, sh, result.pixels, 0, sw);

		return result;
	}
}
