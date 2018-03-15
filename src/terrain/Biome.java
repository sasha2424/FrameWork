package terrain;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import entities.Walker;
import entityHandling.Entity;
import entityHandling.Player;
import handlers.SpriteSheetLoader;
import main.RPGFrame;
import main.Variables;

public abstract class Biome implements Serializable {

	private Color surface;
	protected Image texture;
	private Color ground;

	private int tickCounter;
	protected int tickCounterMax = 100;

	// get the color of the surface
	public abstract Color getSurfaceColor();

	// get the color of the sub-surface
	public abstract Color getGroundColor();

	// return a texture for the surface
	public abstract Image getSurfaceTexture();

	public abstract ArrayList<Entity> generateEntitiesForTile(RPGFrame frame, Tile t);

	protected abstract ArrayList<Entity> getSpawnSet(RPGFrame frame, Player p, Tile t);

	protected double random(int x) {
		return (x + Math.random()) * Variables.TILE_SIZE;
	}

}

// no its completely useless