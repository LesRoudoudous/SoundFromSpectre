import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class NewtonInterpol {
    
	public Courbe interpoller(List<Point> points)
	{
		int nbPoint = points.size();
		double valeur[][] = new double [nbPoint][nbPoint+1];
		double coef[] = new double [nbPoint];
		
		System.out.println("nbPoint  - "+ nbPoint);
		
		for(int i = 0 ; i < nbPoint ; i++)
		{
			valeur[i][0] = points.get(i).getY();
		} 
			
		for(int j = 1 ; j < nbPoint ; j++)
		{
			for(int i = 0 ; i < nbPoint - j ; i++)
			{
				valeur[i][j] = (valeur[i+1][j-1] - valeur[i][j-1])/(points.get(j+i).getX() - points.get(i).getX());
			}
		}
		
		for(int i = 0 ; i < nbPoint ; i++)
		{
			coef[i] = valeur[0][i];
			//System.out.println("a" + i + " = " + coef[i]);
		}
		
		List<Point> pointsPoly = new ArrayList<Point>();
		
		for( int i = 0 ; i < 16000*2; i++)
		{
			pointsPoly.add(new Point(i,0));
			
			double Somme = 0;
			
			for (int j = 0 ; j < nbPoint ; j++)
			{
				double val = coef[j];
				
				for (int k=0; k<j; k++)
				{
					val *= i - points.get(k).getX();
				}
				Somme += val;
			}
			pointsPoly.get(i).y = (int) Somme;	
		}
		
		Courbe courbe = new Courbe(pointsPoly);
		
		return courbe;
	}
}
