package sinusoiddragsim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
class Frame extends JFrame
{
	protected Panel p;
	protected TrigPanel tp;
	public static Boolean state = false;

	public Frame()
	{
	    initUI();
	}
	
	private void initUI()
	{  
	    p = new Panel();
        MouseSense ma = new MouseSense();
        p.addMouseMotionListener(ma);
        p.addMouseListener(ma);
        
	    tp = new TrigPanel();
		
	    setTitle("Sinusoid Simulation");
        setPreferredSize(new Dimension(1000, 650));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
    	pack();
		setLocationRelativeTo(null);
        setVisible(true);
		setFocusable(false);
		setLayout(null);
		
		add(p);
		add(tp);
	}
	
	class MouseSense extends MouseAdapter
    {
        private int x;
        private int y;
        private Boolean grabbed;

        @Override
        public void mouseReleased(MouseEvent e)
        {
        	grabbed = false;
        	p.release();
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
        	x = e.getX();
            y = e.getY();
            
        	if (e.getButton() == MouseEvent.BUTTON1)
        	{
        		grabbed = p.pressed(x, y);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
        	x = e.getX();
            y = e.getY();
            
            if (grabbed)
            {
            	p.tick(x, y);
            	tp.sinTick(p.y, p.x);
            }    
        }
    }
}

