package com.tankreborn.helpers;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public class Debugger {
	private String displayText = "";
	private List<String> text;
	private static Debugger _instance = null;

	private Debugger() {
		text = new ArrayList<>();
	}

	public static Debugger getInstance() {
		if (_instance == null)
			_instance = new Debugger();
		return _instance;
	}

	public void render(Graphics g) {
		/*
		 * int i = text.size() > 20 ? 25 + (20 - text.size()) * 25 : 25; for (String str : text) { g.drawString(str, 10, i); i += 25; }
		 */
		final int maxSize = 25;
		int i = text.size() > maxSize ? text.size() - maxSize : 0;
		int j = 1;
		while (i < text.size()) {
			g.drawString(text.get(i), 10, 20 * (j++));
			i++;
		}

	}

	public void addText(String str) {
		text.add(str);
	}

	public void setText(String str) {
		text = new ArrayList<>();
		text.add(str);
	}

	public void clearText() {
		text = new ArrayList<>();
	}

	public String getText() {
		return displayText;
	}

}
