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
class TrigPanel extends JPanel
{
	private ArrayList<Coord> spoints;
	private ArrayList<Coord> cpoints;
	private ArrayList<Coord> tpoints;
	double xvalue = -250;
	private Coord sPoint;
	private Coord cPoint;
	private Coord tPoint;
	Boolean ready = false;
	
	public TrigPanel()
	{
		spoints = new ArrayList<Coord>();
		cpoints = new ArrayList<Coord>();
		tpoints = new ArrayList<Coord>();
		sPoint = new Coord(0d, 0d, 5);
		cPoint = new Coord(0d, 0d, 5);
		tPoint = new Coord(0d, 0d, 5);

		initUI();
		setup();
	}

    private void initUI()
    {
    	setOpaque(true);
		setSize(new Dimension(500, 500));
		setLocation(500, 50);
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		setFocusable(true);
		requestFocus();
		setLayout(null);
    }
    
    @Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (Coord p : spoints)
        {
            g2d.setColor(Color.GREEN);
            g2d.fill(p);
        }
        for (Coord p : cpoints)
        {
            g2d.setColor(Color.ORANGE);
            g2d.fill(p);
        }
        for (Coord p : tpoints)
        {
            g2d.setColor(Color.MAGENTA);
            g2d.fill(p);
        }
        
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Float(0f, 250f, 500f, 250f));
        
        g2d.setColor(Color.GREEN);
        g2d.draw(sPoint);
        g2d.setColor(Color.ORANGE);
        g2d.draw(cPoint);
        g2d.setColor(Color.MAGENTA);
        g2d.draw(tPoint);
	}
    
    public void setup()
    {
    	for (int i = 0; i <= 500; i++)
    	{
    		double angle = (i/500d)*360d;
    		angle = ((angle * Math.PI)/180);
    		
    		double realx = Math.cos(angle) * 125;
    		double realy = Math.sin(angle) * 125;
    		
        	spoints.add(new Coord(i-250, realy, 1));
        	cpoints.add(new Coord(i-250, realx, 1));
        	tpoints.add(new Coord(i-250, realy/realx, 1));
    	}
    	ready = true;
    }
    
    public void sinTick(double a, double syvalue, double cyvalue)
    {
    	double xval = (a/360d)*500d;
		
		sPoint.x = cPoint.x = tPoint.x = xval-2.5d;
		
		double angle = (xval/500d)*360d;
		angle = ((angle * Math.PI)/180);
		
		double realx = Math.cos(angle) * 125;
		double realy = Math.sin(angle) * 125;
		
		sPoint.y = Coord.translateY(realy)-2.5d;
		cPoint.y = Coord.translateY(realx)-2.5d;
		tPoint.y = Coord.translateY(realy/realx)-2.5d;
    	
    	repaint();
    }
}
