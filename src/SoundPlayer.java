import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


public class SoundPlayer 
{
	private AudioFormat audioFormat;
	private AudioInputStream audioInputStream;
	private SourceDataLine sourceDataLine;
	
	int channels = 1;
    int bytesPerSamp = 2;
    float sampleRate = 16000.0F;
	private int kSampleSize = 16;
	private boolean kSigned = true;
	private boolean kBigEndian = true;
	
	private byte audioData[] = new byte[16000*4];
	
	List<Spectre> spectres;
	
	public void playSpectres(List<Spectre> spectres)
	{
		this.spectres = spectres;
		getSyntheticData();
		play();
	}
	
	private void play()
	{
		try{
	        InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
	        audioFormat = new AudioFormat(sampleRate, kSampleSize, channels, kSigned, kBigEndian);
	        audioInputStream = new AudioInputStream( byteArrayInputStream, audioFormat, audioData.length/audioFormat.getFrameSize());
	        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

	        sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
	        new ListenThread().start();
	    }
	 	catch (Exception e) 
	 	{
	 		e.printStackTrace();
	      	System.exit(0);
	    }
	}

	void getSyntheticData()
	{
	    ByteBuffer byteBuffer = ByteBuffer.wrap(audioData);
	    ShortBuffer shortBuffer = byteBuffer.asShortBuffer();

	    int byteLength = audioData.length;
	    int sampLength = byteLength/bytesPerSamp;
	    for(int i = 0; i <spectres.size() ; i++)
	    {
	        Spectre spectre = spectres.get(i);
	        double time = i/sampleRate;
	        double sinValue = 0;
	        double freq = 32.0;
	        for(int j = 0 ; j < spectre.getNbFreq() ; j++)
	        {
	        	//System.out.println("2 * " + Math.PI + " * " + freq + " * " + i + " / 16000 = " + (2*Math.PI*freq*i)/16000);
	        	  sinValue += (float)((float)spectre.getAmplitude(j)/127.0)*Math.sin((2*Math.PI*freq*time));
	        	//  System.out.println(sinValue);
	        	  freq *= 2;
	        }
	        sinValue = sinValue / 9.0;
	        
	        shortBuffer.put((short)(32000*sinValue));
	     } 
	   /* for(int cnt = 0; cnt < sampLength; cnt++){
	        double time = cnt/sampleRate;
	        double freq = 950.0;//arbitrary frequency
	        double sinValue =
	          (Math.sin(2*Math.PI*freq*time) +
	          Math.sin(2*Math.PI*(freq/1.8)*time) +
	          Math.sin(2*Math.PI*(freq/1.5)*time))/3.0;
	        shortBuffer.put((short)(16000*sinValue));
	      }*/
	}
	
	class ListenThread extends Thread
	{
		byte playBuffer[] = new byte[16384];

		public void run()
		{
			try
			{
			   sourceDataLine.open(audioFormat);
			   sourceDataLine.start();
			
			   int cnt;
			
			   while((cnt = audioInputStream.read(playBuffer, 0, playBuffer.length))!= -1)
			   {
				   if(cnt > 0)
				   {
					   sourceDataLine.write( playBuffer, 0, cnt);
				   }
			   }

			   sourceDataLine.drain();
		
			   sourceDataLine.stop();
			   sourceDataLine.close();
		 }catch (Exception e) 
		 {
			 	e.printStackTrace();
			 	System.exit(0);
		 }
		
		}
	}
}


