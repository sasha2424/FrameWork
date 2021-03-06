package handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

/**
 * INPUTHANDLER
 * 
 * This keeps track of the mouse position, mouse scroll, and certain keys. keys
 * can be set with the trackNewKey() method
 */
public class InputHandler implements KeyListener, MouseWheelListener {

	private ArrayList<Key> keys;

	private int scroll;

	private int SCROLL_SENSITIVITY;

	public InputHandler() {
		super();
		scroll = 0;
		keys = new ArrayList<Key>();

	}

	public void trackNewKey(String c, String isToggle) {
		keys.add(new Key(c, !isToggle.equals("toggle")));
	}

	public void setScrollSensitivity(int k) {
		SCROLL_SENSITIVITY = k;
	}

	public boolean getKeyPressed(String s) {
		for (Key k : keys) {
			if (k.getName().equals(s))
				return k.isPressed();
		}
		return false;
	}

	public String getAllKeyStatus() {
		String r = "";
		for (Key k : keys) {
			r += k.getName() + "\t" + k.isPressed() + "\n";
		}
		return r.substring(0, r.length() - 1);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for (Key k : keys) {
			if (k.getName().equals(KeyEvent.getKeyText(e.getKeyCode())))
				k.toggle();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (Key k : keys) {
			if (k.isResetValueOnRelease() && k.getName().equals(KeyEvent.getKeyText(e.getKeyCode())))
				k.setNotPressed();
		}
	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

	public int getScroll() {
		return scroll;

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll += e.getWheelRotation() * SCROLL_SENSITIVITY;
	}

	private class Key { // TODO give key codes and make streamlined
		private String name;
		private Boolean pressed;
		private Boolean resetValueOnRelease;

		public Key(String name, Boolean resetValueOnRelease) {
			this.name = name;
			pressed = false;
			this.resetValueOnRelease = resetValueOnRelease;
		}

		public boolean isPressed() {
			return pressed;
		}

		public boolean isResetValueOnRelease() {
			return resetValueOnRelease;
		}

		public String getName() {
			return name;
		}

		public void toggle() {
			pressed = !pressed;
		}

		public void keySet(boolean t) {
			pressed = t;
		}

		public void setPressed() {
			pressed = true;
		}

		public void setNotPressed() {
			pressed = false;
		}
	}

}
