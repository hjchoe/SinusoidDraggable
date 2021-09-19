package sinusoiddragsim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Panel extends JPanel
{
	private ArrayList<Coord> points;
	private Coord xpoint;
	private Coord ypoint;
	private Info theta;
	double angle;
	private Indicator ind;
	private Dragger drag;
	double x = 0;
	double y = 0;
	
	public Panel()
	{
		points = new ArrayList<Coord>();
		theta = new Info();
		xpoint = new Coord(-2.5d, 0d, 5);
		ypoint = new Coord(0d, +2.5d, 5);
		xpoint.y = Coord.translateY(140d);
		ypoint.x = Coord.translateX(140d);
		ind = new Indicator();
		drag = new Dragger();
		angle = 0;
		
		initUI();
	}

    private void initUI()
    {
    	setOpaque(true);
		setSize(new Dimension(500, 500));
		setLocation(0, 50);
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		setFocusable(true);
		requestFocus();
		setLayout(null);
		
		add(theta);
    }
    
    @Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Float(250f, 0f, 250f, 500f));
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Float(0f, 250f, 500f, 250f));
        
        for (Coord p : points)
        {
            g2d.setColor(Color.RED);
            g2d.fill(p);
        }
        
        g2d.setColor(Color.ORANGE);
        g2d.draw(xpoint);
        g2d.setColor(Color.GREEN);
        g2d.draw(ypoint);
        
        g2d.setColor(Color.CYAN);
        g2d.draw(ind);
        
        g2d.setColor(drag.c);
        g2d.draw(drag);
	}
    
    Boolean pressed(int x, int y)
    {
        if (drag.isHit(x, y))
        {
        	drag.switchColor(true);
        	System.out.println("clicked");
        	repaint();
        	return true;
        }
        return false;
    }
    
    void release()
    {
    	drag.switchColor(false);
    	System.out.println("released");
    	repaint();
    }
    
    void tick(int xval, int yval)
    {
        System.out.println("dragging");
        
        double realx = Coord.reversetranslateX(xval);
        double realy = Coord.reversetranslateY(yval);
        angle = Math.atan(realy/realx);
        if (realx < 0d)
        {
        	angle = angle - (Math.PI);
        }
        realx = x = Math.cos(angle) * 125;
		realy = y = Math.sin(angle) * 125;
		realx = Coord.translateX(realx);
		realy = Coord.translateY(realy);
        
		xpoint.x = realx-2.5d;
		ypoint.y = realy-2.5d;
        ind.x2 = realx;
		ind.y2 = realy;
		drag.x = realx-drag.r;
		drag.y = realy-drag.r;
		
		angle = ((angle * 180)/Math.PI);
		if (angle < 0)
		{
			angle = 360 + angle;
		}
		System.out.println(angle);
		theta.custsetText(angle, x, y);
        
    	drag.switchColor(true);
    	repaint();
    }
}
