import java.awt.Color;
import java.awt.Graphics;
import java.nio.ShortBuffer;

import javax.swing.JPanel;

public class SingleWaveformPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	protected static final Color BACKGROUND_COLOR = Color.white;
    protected static final Color REFERENCE_LINE_COLOR = Color.black;
    protected static final Color WAVEFORM_COLOR = Color.red;
    
    private ShortBuffer shortBuffer;

    public SingleWaveformPanel(ShortBuffer buffer)
    {
    	shortBuffer = buffer;
        setBackground(BACKGROUND_COLOR);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int lineHeight = getHeight() / 2;
        g.setColor(REFERENCE_LINE_COLOR);
        g.drawLine(0, lineHeight, (int)getWidth(), lineHeight);
        drawWaveform(g);

    }
    
    protected double getMaxValue()
    {
    	double maxValue = 0;
    	for(int i = 0; i < shortBuffer.capacity() ; i++)
    	{
    		double value = Math.abs(shortBuffer.get(i));
    		if(maxValue < value )
    			maxValue = value;
    	}
    	return maxValue;
    }

    protected void drawWaveform(Graphics g)
    {
    	g.setColor(WAVEFORM_COLOR);
    	 
    	int bufCap = shortBuffer.capacity();
    	double xScaleFactor = (double)((double)getWidth() / (double)bufCap);
    	double yScaleFactor = (double)((double)(getHeight() / 2) / getMaxValue());
    	
    	System.out.println(getWidth() + " / " + bufCap + " = " + xScaleFactor);
    	System.out.println("(" + getHeight() + " / 2) / " + getMaxValue() + " = " + yScaleFactor);
    	
    	int oldX = 0;
        int oldY = (int)((getHeight() / 2) - (shortBuffer.get(0) * yScaleFactor));

        for(int i = 0 ; i < bufCap ; i++)
        {
        	int X = (int)(i * xScaleFactor);
            int Y = (int)((getHeight() / 2) - (shortBuffer.get(i) * yScaleFactor));

            g.drawLine(oldX, oldY, X, Y);
            oldX = X;
            oldY = Y;
        }
    }
}