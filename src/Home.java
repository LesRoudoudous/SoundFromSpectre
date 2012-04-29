

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Home extends JFrame implements ITableauFrequenceValue
{

	private static final long serialVersionUID = 1L;
	private JPanel courbePanel;

	public Home()
	{
		super();
		build();
	}
	
	public void setTabFrequenceValue(int values[][])
	{
		List<Spectre> spectres = new ArrayList<Spectre>();
		for(int i = 0; i < 4; i++)
		{
			Spectre spectre = new Spectre(values[i], 9);
			spectres.add(spectre);
		}
		interpolSpectre(spectres);
	}
	
	private void interpolSpectre(List<Spectre> spectres) 
	{
		InterpolSpectres interpol = new InterpolSpectres();
		List<Spectre> interpoledSPectres = interpol.getInterpolledSpectres(spectres);
		SoundPlayer player = new SoundPlayer();
		player.playSpectres(this,interpoledSPectres);
	}

	public JPanel getCourbePanel()
	{
		return courbePanel;
	}
	
	private void build(){
		setTitle("SoundFromSpectre");
		setSize(800,600); 
		setLocationRelativeTo(null); 
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(5,5,5,5);

		TableauFrequences tabFreq = new TableauFrequences(this);

		c.gridx = 0;
		c.gridy = 0;
		panel.add(tabFreq,c);
		
		courbePanel = new JPanel();
		//courbePanel.setPreferredSize(new Dimension(400,300));
		c.gridx = 0;
		c.gridy = 1;
		panel.add(courbePanel, c);

		return panel;
	}
}
