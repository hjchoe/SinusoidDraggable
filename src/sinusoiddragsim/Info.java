package sinusoiddragsim;

import javax.swing.JLabel;

@SuppressWarnings("serial")
class Info extends JLabel
{
	StringBuilder str;
	
	Info()
	{
		setText("θ = 0°");
		setSize(100, 80);
		setLocation(20, 25);
		setVisible(true);
		str = new StringBuilder();
	}
	
	void custsetText(double angle, double x, double y)
	{
		str.setLength(0);
		
		str.append("<html>");
		str.append("θ = ");
		str.append(Double.toString(roundDouble(angle, 2)));
		str.append("°");
		
		str.append("<br>");
		str.append("θ = ");
		double rangle = (angle * Math.PI) / 180.0d;
		str.append(Double.toString(roundDouble(rangle, 3)));
		str.append(" rad");
		
		str.append("<br>");
		str.append("x = ");
		str.append(Double.toString(roundDouble(x/125, 3)));
		
		str.append("<br>");
		str.append("y = ");
		str.append(Double.toString(roundDouble(y/125, 3)));
		
		str.append("</html>");
		
		setText(str.toString());
	}
	
	double roundDouble(double val, int length)
	{
		double mult = 1;
		for (int i = 0; i < length; i++)
		{
			mult *= 10;
		}
		return Math.round(val * mult) / mult;
	}
}
