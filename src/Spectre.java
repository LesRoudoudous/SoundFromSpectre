import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Spectre
{
	private List<Integer> values;
	
	public Spectre(int amps[], int nbFreq)
	{
		values = new ArrayList<Integer>();
		for(int i = 0 ; i < nbFreq ; i++)
		{
			values.add(amps[i]);
		}
	}

	
	public Spectre(List<Integer> values2) 
	{
		values = values2;
	}


	public List<Integer> getAmplitudes()
	{
		return values;
	}
	
	public int getAmplitude(int index)
	{
		return values.get(index);
	}
	
	public int getNbFreq()
	{
		return values.size();
	}
}
