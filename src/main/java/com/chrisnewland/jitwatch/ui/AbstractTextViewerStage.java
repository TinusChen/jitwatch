/*
 * Copyright (c) 2013 Chris Newland. All rights reserved.
 * Licensed under https://github.com/chriswhocodes/jitwatch/blob/master/LICENSE-BSD
 * http://www.chrisnewland.com/jitwatch
 */
package com.chrisnewland.jitwatch.ui;

import java.util.List;
import com.chrisnewland.jitwatch.ui.triview.Viewer;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public abstract class AbstractTextViewerStage extends Stage
{
	private Viewer viewer;

	// make this a TextFlow in Java8
	public AbstractTextViewerStage(final JITWatchUI parent, String title)
	{
		initStyle(StageStyle.DECORATED);

		setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent arg0)
			{
				parent.handleStageClosed(AbstractTextViewerStage.this);
			}
		});

		viewer = new Viewer();

		setTitle(title);

		Scene scene = new Scene(viewer, 640, 480);

		setScene(scene);
	}

	protected void setContent(List<Text> items, int maxLineLength)
	{
		viewer.setContent(items);

		int x = Math.min(80, maxLineLength);
		int y = Math.min(30, items.size());

		x = Math.max(x, 20);
		y = Math.max(y, 20);

		setWidth(x * 12);
		setHeight(y * 19);
	}

	protected String padLineNumber(int number, int maxWidth)
	{
		int len = Integer.toString(number).length();

		StringBuilder builder = new StringBuilder();

		for (int i = len; i < maxWidth; i++)
		{
			builder.append(' ');
		}

		builder.append(number);

		return builder.toString();
	}

	public void jumpTo(final String regex)
	{
		viewer.jumpTo(regex);
	}
}