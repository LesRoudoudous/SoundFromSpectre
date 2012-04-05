import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TableauFrequences extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	protected List<NumericTextField> t1FieldsArray = new ArrayList<NumericTextField>();
	protected List<NumericTextField> t2FieldsArray = new ArrayList<NumericTextField>();
	protected List<NumericTextField> t3FieldsArray = new ArrayList<NumericTextField>();
	protected List<NumericTextField> t4FieldsArray = new ArrayList<NumericTextField>();
	
	protected ITableauFrequenceValue parent;
	
	public TableauFrequences(ITableauFrequenceValue parent)
	{
		super();
		this.parent = parent;
		build();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(allFieldFilled())
		{
			int values[][] = new int[4][9];
			for(int i = 0 ; i < 9; i++)
			{
				values[0][i] = Integer.parseInt(t1FieldsArray.get(i).getText());
				values[1][i] = Integer.parseInt(t2FieldsArray.get(i).getText());
				values[2][i] = Integer.parseInt(t3FieldsArray.get(i).getText());
				values[3][i] = Integer.parseInt(t4FieldsArray.get(i).getText());
			}
			parent.setTabFrequenceValue(values);
		}
	}
	
	private boolean allFieldFilled()
	{
		boolean isAllFilled = true;
		for(int i = 0 ; i < 9; i++)
		{
			if(t1FieldsArray.get(i).getText().isEmpty() || t2FieldsArray.get(i).getText().isEmpty() || t3FieldsArray.get(i).getText().isEmpty() || t4FieldsArray.get(i).getText().isEmpty())
			{
				isAllFilled = false;
				break;
			}
				
		}
		return isAllFilled;
	}
	
	private void build()
	{ 
		setBackground(Color.white);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel spectreLabel = new JLabel("Spectre :");
		
		JLabel freq1Label = new JLabel("32");
		JLabel freq2Label = new JLabel("64");
		JLabel freq3Label = new JLabel("128");
		JLabel freq4Label = new JLabel("256");
		JLabel freq5Label = new JLabel("512");
		JLabel freq6Label = new JLabel("1024");
		JLabel freq7Label = new JLabel("2048");
		JLabel freq8Label = new JLabel("4096");
		JLabel freq9Label = new JLabel("8192");
		
		JLabel t1Label = new JLabel("t1 : ");
		JLabel t2Label = new JLabel("t2 : ");
		JLabel t3Label = new JLabel("t3 : ");
		JLabel t4Label = new JLabel("t4 : ");
		
		JButton validBtn = new JButton("Valider");
		validBtn.addActionListener(this);
		
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(5,5,5,5);

		c.gridx = 0;
		c.gridy = 0;
		add(spectreLabel,c);
		
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 1;
		c.gridy = 1;
		add(freq1Label,c);
		
		c.gridx = 2;
		c.gridy = 1;
		add(freq2Label,c);
		
		c.gridx = 3;
		c.gridy = 1;
		add(freq3Label,c);
		
		c.gridx = 4;
		c.gridy = 1;
		add(freq4Label,c);
		
		c.gridx = 5;
		c.gridy = 1;
		add(freq5Label,c);
		
		c.gridx = 6;
		c.gridy = 1;
		add(freq6Label,c);
		
		c.gridx = 7;
		c.gridy = 1;
		add(freq7Label,c);
		
		c.gridx = 8;
		c.gridy = 1;
		add(freq8Label,c);
		
		c.gridx = 9;
		c.gridy = 1;
		add(freq9Label,c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(t1Label,c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(t2Label,c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(t3Label,c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(t4Label,c);
		
		for(int i = 0; i < 9 ; i++)
		{
			NumericTextField t1FreqField = new NumericTextField((i == 3) ? "127" :"0",4);
			NumericTextField t2FreqField = new NumericTextField((i == 3) ? "127" :"0",4);
			NumericTextField t3FreqField = new NumericTextField((i == 3) ? "127" :"0",4);
			NumericTextField t4FreqField = new NumericTextField((i == 3) ? "127" :"0",4);
			
			c.gridx = i+1;
			c.gridy = 2;
			add(t1FreqField,c);
			
			c.gridy = 3;
			add(t2FreqField,c);
			
			c.gridy = 4;
			add(t3FreqField,c);
			
			c.gridy = 5;
			add(t4FreqField,c);
			
			t1FieldsArray.add(t1FreqField);
			t2FieldsArray.add(t2FreqField);
			t3FieldsArray.add(t3FreqField);
			t4FieldsArray.add(t4FreqField);
		}
		
		c.gridx = 9;
		c.gridy = 6;
		add(validBtn,c);
	}
	


}
