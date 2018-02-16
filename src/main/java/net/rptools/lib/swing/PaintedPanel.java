/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.rptools.lib.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintedPanel extends JPanel {

	private Paint paint;

	public PaintedPanel() {
		this(null);
	}

	public PaintedPanel(Paint paint) {
		this.paint = paint;
		setMinimumSize(new Dimension(10, 10));
		setPreferredSize(getMinimumSize());
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		Dimension size = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, size.width, size.height);

		if (paint != null) {
			((Graphics2D) g).setPaint(paint);
			g.fillRect(0, 0, size.width, size.height);
		} else {
			try {
				BufferedImage texture;
				texture = ImageIO.read(getClass().getResource("/net/rptools/lib/image/icons/transparent2.png"));
				TexturePaint tp = new TexturePaint(texture, new Rectangle(0, 0, 28, 28));
				((Graphics2D) g).setPaint(tp);
				g.fillRect(0, 0, size.width, size.height);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				g.setColor(Color.white);
				g.fillRect(0, 0, size.width, size.height);
				g.setColor(Color.red);
				g.drawLine(size.width - 1, 0, 0, size.height - 1);
			}
		}
	}
}
