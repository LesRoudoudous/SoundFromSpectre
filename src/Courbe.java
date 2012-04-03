import java.awt.Point;
import java.util.List;


public class Courbe
{
	List<Point> points;
	
	public Courbe(List<Point> points)
	{
		this.points = points;
	}
	
	public List<Point> getPoints()
	{
		return points;
	}
	
	public double getValueAtIndex(int index)
	{
		return points.get(index).getY();
	}
	
	public int getNbPoints()
	{
		return points.size();
	}

}
