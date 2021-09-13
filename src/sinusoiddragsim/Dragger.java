package sinusoiddragsim;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
class Dragger extends Ellipse2D.Double
{
	Color c;
	double r = 2.5d;
	
	Dragger()
	{
		setFrame(375d-2.5d, 250d-2.5d, r*2, r*2);
		c = Color.RED;
	}
	
	void switchColor(Boolean grabstate)
	{
		if (grabstate) c = Color.BLUE;
		else c = Color.RED;
	}
	
    public boolean isHit(float x, float y)
    {
        return getBounds2D().contains(x, y);
    }
}
