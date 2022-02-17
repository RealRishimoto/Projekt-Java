import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;

import javax.swing.*;

import java.util.Random;
import java.util.random.*;

public class PanelGry extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 900;
	static final int SCREEN_HEIGHT = 900;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int [GAME_UNITS];
	final int y[] = new int [GAME_UNITS];
	int bodyParts = 3;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	PanelGry(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGry();
	}
	public void startGry() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics a) {
		super.paintComponent(a);
		draw(a);
	}
	public void draw(Graphics a) {
		a.setColor(Color.green);
		a.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
		for(int i = 0; i< bodyParts; i++) {
			if(i == 0) {
				a.setColor(Color.yellow);
				a.fillRect(x[i],y[i], UNIT_SIZE, UNIT_SIZE);
			}
			else {
				a.setColor(Color.orange);
				a.fillRect(x[i],y[i], UNIT_SIZE, UNIT_SIZE);
			}
		}
	}
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	public void move() {
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	public void jablkospr() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	public void kolizjaspr() {
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i])&&(y[0] == y[i])) {
				running = false;
			}
		}
		if(x[0] <0) {
			running = false;
		}
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		if(y[0] < 0) {
			running = false;
		}
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		if(!running) {
			timer.stop();
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
	move();
	jablkospr();
	kolizjaspr();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}

}
