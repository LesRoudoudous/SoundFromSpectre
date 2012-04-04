import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class InterpolSpectres
{	
	public List<Spectre> getInterpolledSpectres( List<Spectre> spectres)
	{
		List<List<Point>> points = getFreqPoints(spectres);
		List<Courbe> courbes = getInterpolledCourbes(points);
		List<Spectre> interpolledSpectres = getSpectresFromCourbes(courbes);
		return interpolledSpectres;
	}
	
	

	private List<List<Point>> getFreqPoints(List<Spectre> spectres)
	{
		int nbFreq = spectres.get(0).getNbFreq();
		List<List<Point>> points = new ArrayList<List<Point>>();
		
		for(int i = 0; i < nbFreq ; i++)
		{
			List<Point> freqPoints = new ArrayList<Point>();
			for(int j = 0; j < spectres.size() ; j++)
			{
				freqPoints.add(new Point(j*10666,spectres.get(j).getAmplitude(i)));
			}
			points.add(freqPoints);
		}
		return points;
	}
	
	private List<Courbe> getInterpolledCourbes(List<List<Point>> points)
	{
		NewtonInterpol newton = new NewtonInterpol();
		List<Courbe> courbes = new ArrayList<Courbe>();
		
		for(int i = 0 ; i < points.size() ; i++)
		{
			Courbe courbe = newton.interpoller(points.get(i));
			courbes.add(courbe);
		}
		return courbes;
	}
	
	private List<Spectre> getSpectresFromCourbes(List<Courbe> courbes)
	{
		List<Spectre> spectres = new ArrayList<Spectre>();
		int nbPoints = courbes.get(0).getNbPoints();
		for(int i = 0 ; i < nbPoints ; i++)
		{
			List<Integer> values = new ArrayList<Integer>();
			for(int j = 0 ; j < courbes.size(); j++)
			{
				Courbe courbe = courbes.get(j);
				values.add((int) courbe.getValueAtIndex(i));
			}
			spectres.add(new Spectre(values));
		}
		return spectres;
	}

}
