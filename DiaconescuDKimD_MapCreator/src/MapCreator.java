import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MapCreator {

	public static void main(String[] args) {
		Creator map = new Creator();
		map.setVisible(true);
		map.setDefaultCloseOperation(map.EXIT_ON_CLOSE);
	}

}

class Creator extends JFrame implements ActionListener, MouseMotionListener {

	private static final Writer SAVE_FILE_NAME = null;
	//Variables
	Container c;
	JPanel p1, p2, p3, p4;
	String color;
	JButton[] buttons;
	JButton save, load, exit;
	JButton blocks, items, player, reset; 
	JButton blueBlock, brownBlock, greenBlock, ice, hay, purple, rockBlock;
	
	JButton heart, key, atm, money;
	boolean p = false;
	
	String colors= "";
	
	ImageIcon imgs[] = new ImageIcon[7];
	ImageIcon inv[] = new ImageIcon[4];
	
	String[] imgsNames = new String[400]; 
	
	public Creator() {
		super("MAP CREATOR");
		c = getContentPane();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		buttons = new JButton[400];
		p1.setLayout(new GridLayout(20, 20));
		
		p1.addMouseMotionListener(this);
		imgs[0] = new ImageIcon("images/blueblock.png");
		imgs[1] = new ImageIcon("images/greenblock.png");
		imgs[2] = new ImageIcon("images/browngrey.png");
		imgs[3] = new ImageIcon("images/ice.png");
		imgs[4] = new ImageIcon("images/rockblock.png");
		imgs[5] = new ImageIcon("images/hay.png");
		imgs[6] = new ImageIcon("images/purple.png");
		
		inv[0] = new ImageIcon("images/heart.png");
		inv[1] = new ImageIcon("images/key.png");
		inv[2] = new ImageIcon("images/money.png");
		inv[3] = new ImageIcon("images/atm.png");
		
		
		for (int i = 0; i < 400; i++) {
			buttons[i] = new JButton(imgs[0]);
			buttons[i].addActionListener(this);
			buttons[i].setBorder(null);
			p1.add(buttons[i]);
			buttons[i].addMouseMotionListener(this);
			imgsNames[i] = "images/blueblock.png";
			buttons[i].setActionCommand(i+"");
		}
		//Initialize buttons
		save = new JButton("SAVE");
		load = new JButton("LOAD");
		exit = new JButton("EXIT");
		blocks = new JButton("BLOCKS");
		items = new JButton("INVENTORY");
		player = new JButton("PLAYER");
		reset = new JButton("RESET");

		blueBlock = new JButton("Background");
		blueBlock.setContentAreaFilled(false);
		blueBlock.setBorderPainted(false);
		blueBlock.setForeground(Color.WHITE);
		
		brownBlock = new JButton("Dirt");
		brownBlock.setContentAreaFilled(false);
		brownBlock.setBorderPainted(false);
		brownBlock.setForeground(Color.WHITE);
		
		rockBlock = new JButton("Rock");
		rockBlock.setContentAreaFilled(false);
		rockBlock.setBorderPainted(false);
		rockBlock.setForeground(Color.white);
		
		greenBlock = new JButton("Green Block");
		greenBlock.setContentAreaFilled(false);
		greenBlock.setBorderPainted(false);
		greenBlock.setForeground(Color.white);
		
		ice = new JButton("Ice");
		ice.setContentAreaFilled(false);
		ice.setBorderPainted(false);
		ice.setForeground(Color.white);
		
		hay = new JButton("Hay");
		hay.setContentAreaFilled(false);
		hay.setBorderPainted(false);
		hay.setForeground(Color.white);
		
		purple = new JButton("Purple");
		purple.setContentAreaFilled(false);
		purple.setBorderPainted(false);
		purple.setForeground(Color.white);
		
		heart = new JButton("Heart");
		heart.setContentAreaFilled(false);
		heart.setBorderPainted(false);
		heart.setForeground(Color.white);
		
		key = new JButton("Key");
		key.setContentAreaFilled(false);
		key.setBorderPainted(false);
		key.setForeground(Color.white);
		
		money = new JButton("Key");
		money.setContentAreaFilled(false);
		money.setBorderPainted(false);
		money.setForeground(Color.white);
		
		atm = new JButton("Key");
		atm.setContentAreaFilled(false);
		atm.setBorderPainted(false);
		atm.setForeground(Color.white);
		
		//Fill buttons with color
		
		greenBlock.setIcon(imgs[1]);
		blueBlock.setIcon(imgs[0]);
		brownBlock.setIcon(imgs[2]);
		ice.setIcon(imgs[3]);
		rockBlock.setIcon(imgs[4]);
		hay.setIcon(imgs[5]);
		purple.setIcon(imgs[6]);
		
		heart.setIcon(inv[0]);
		key.setIcon(inv[1]);
		money.setIcon(inv[2]);
		atm.setIcon(inv[3]);
		
		blueBlock.addActionListener(this);
		brownBlock.addActionListener(this);
		rockBlock.addActionListener(this);
		greenBlock.addActionListener(this);
		ice.addActionListener(this);
		hay.addActionListener(this);
		purple.addActionListener(this);
		exit.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		blocks.addActionListener(this);
		items.addActionListener(this);
		player.addActionListener(this);
		heart.addActionListener(this);
		key.addActionListener(this);
		money.addActionListener(this);
		atm.addActionListener(this);
		reset.addActionListener(this);
		
		//panel and container 
		setSize(1300, 1000);
		p1.setSize(1000, 1000);
		p2.setSize(300, 1000);
		
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c.setLayout(new BorderLayout());
		c.add(p1, BorderLayout.CENTER);
		c.add(p2, BorderLayout.SOUTH);
		c.add(p3, BorderLayout.EAST);
		c.add(p4, BorderLayout.NORTH);
		
		p2.setLayout(new GridLayout(1, 3));
		p3.setLayout(new GridLayout(7, 1));
		p4.setLayout(new GridLayout(1, 4));

		p2.add(load);
		p2.add(save);
		p2.add(exit);

		p3.setBackground(Color.black);
		p3.add(blueBlock);
		p3.add(brownBlock);
		p3.add(rockBlock);
		p3.add(greenBlock);
		p3.add(ice);
		p3.add(hay);
		p3.add(purple);
		
		p4.add(blocks);
		p4.add(items);
		p4.add(player);
		p4.add(reset);

		setVisible(true);

	}

	public void actionPerformed(ActionEvent evt) {

		JButton b = (JButton) evt.getSource();
		String act = b.getActionCommand();

		if (act.equals("Background")) {
			colors = "Background";
		} else if (act.equals("Dirt")) {
			colors = "Dirt";
		} else if (act.equals("Rock")) {
			colors = "Rock";
		} else if (act.equals("Ice")) {
			colors = "Ice";
		} else if (act.equals("Green Block")) {
			colors = "Green Block";
		} else if (act.equals("Hay")) {
			colors = "Hay";
		} else if (act.equals("Purple")) {
			colors = "Purple";
		} else if(act.equals("Heart Player")){
			colors = "Heart Player";
		} else if (act.equals("Key")){
			colors = "Key";
		} else if(act.equals("Money")){
			colors = "Money";
		} else if(act.equals("ATM")){
			colors = "ATM";
		}
		
		if(act.equals("RESET"))
		{
			for(int j = 0; j < 400; j++)
			{
				buttons[j].setIcon(imgs[0]);
			}
		}
		
		if(act.equals("BLOCKS")){
		
			p3.setLayout(new GridLayout(7, 1));
			p3.add(brownBlock);
			p3.add(rockBlock);
			p3.add(greenBlock);
			p3.add(ice);
			p3.add(hay);
			p3.add(purple);
			
			
			blueBlock.setIcon(imgs[0]);
			blueBlock.setText("Background");
			greenBlock.setIcon(imgs[1]);
			greenBlock.setText("Green Block");
			brownBlock.setIcon(imgs[2]);
			brownBlock.setText("Dirt");
			ice.setIcon(imgs[3]);
			rockBlock.setIcon(imgs[4]);
			rockBlock.setText("Rock");
			hay.setIcon(imgs[5]);
			purple.setIcon(imgs[6]);
		} 
		else if (act.equals("INVENTORY"))
		{
			p3.setLayout(new GridLayout(3, 1));
			p3.add(brownBlock);
			p3.add(rockBlock);
			p3.remove(ice);
			p3.remove(hay);
			p3.remove(purple);
			p3.remove(greenBlock);
			
			blueBlock.setIcon(inv[1]);
			blueBlock.setText("Key");
			
			brownBlock.setIcon(inv[2]);
			brownBlock.setText("Money");
			
			rockBlock.setIcon(inv[3]);
			rockBlock.setText("ATM");
		}
		else if (act.equals("PLAYER"))
		{
			p3.setLayout(new GridLayout(1,1));
			p3.remove(brownBlock);
			p3.remove(rockBlock);
			p3.remove(greenBlock);
			p3.remove(ice);
			p3.remove(hay);
			p3.remove(purple);
			
			blueBlock.setIcon(inv[0]);
			blueBlock.setText("Heart Player");
		}
		
		for(int i = 0; i < 400; i++)
		{
			if (act.equals(""+i))
			{
				if(colors.equals("Background"))
				{
					buttons[i].setIcon(imgs[0]);
					imgsNames[i] = "images/blueblock.png";
				}
				else if(colors.equals("Dirt"))
				{
					buttons[i].setIcon(imgs[2]);
					imgsNames[i] = "images/browngrey.png";
				}
				else if(colors.equals("Rock"))
				{
					buttons[i].setIcon(imgs[4]);
					imgsNames[i] = "images/rockblock.png";
				}
				else if(colors.equals("Green Block"))
				{
					buttons[i].setIcon(imgs[1]);
					imgsNames[i] = "images/greenblock.png";
				}
				else if(colors.equals("Ice"))
				{
					buttons[i].setIcon(imgs[3]);
					imgsNames[i] = "images/ice.png";
				}
				else if(colors.equals("Hay"))
				{
					buttons[i].setIcon(imgs[5]);
					imgsNames[i] = "images/hay.png";
				}
				else if(colors.equals("Purple"))
				{
					buttons[i].setIcon(imgs[6]);
					imgsNames[i] = "images/purple.png";
				}
				else if(colors.equals("Heart Player")&&p == false)
				{
					buttons[i].setIcon(inv[0]);
					p = true;
					imgsNames[i] = "images/heart.png";
				}
				else if(colors.equals("Key"))
				{
					buttons[i].setIcon(inv[1]);
					imgsNames[i] = "images/key.png";
				}
				else if(colors.equals("Money"))
				{
					buttons[i].setIcon(inv[2]);
					imgsNames[i] = "images/money.png";
				}
				else if(colors.equals("ATM"))
				{
					buttons[i].setIcon(inv[3]);
					imgsNames[i] = "images/ATM.png";
				}
			}
			

		}	
			for(int j = 0; j < 400; j++){
				if (buttons[j].getIcon() == inv[0]){
					p=true;
					break;
				} 
				else{
					p=false;
				}
			}
		//exit
		if(act.equals("EXIT"))
		{
			System.exit(0); 
		}
		//save
		if(act.equals("SAVE")) {
			JFileChooser fileChooser = new JFileChooser();
			 FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Select a text file to save", "txt");
			 fileChooser.setFileFilter(filter);
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				PrintWriter writer = null;
				try {
					writer = new PrintWriter(file);
					for (int i = 0; i < 400; i++) {
						if (imgsNames[i] != null) {
							writer.println(imgsNames[i]);
						} 
						else {
							writer.println();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (writer !=null) {
						writer.close();
					}
				}
			}
		}
		//load
		if(act.equals("LOAD")) {
			JFileChooser fileChooser = new JFileChooser();
			 FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Saved Text files", "txt");
			 fileChooser.setFileFilter(filter);
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			  File file = fileChooser.getSelectedFile();
			  imgsNames = new String[400];
			  BufferedReader reader = null;
			  try {
				  reader = new BufferedReader(new FileReader(file));
				  String image = null;
				  int pos = 0;
				  while((image=reader.readLine()) != null) {
					  imgsNames[pos++] = image;
				  }
				  for(int i = 0; i < imgsNames.length; i++) {
					  if (!"null".equals(imgsNames[i])){
						  buttons[i].setIcon(new ImageIcon(imgsNames[i]));  
					  }else {
						  buttons[i].setIcon(new ImageIcon("images/blueblock.png"));
					  }
				  }
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} 
		

	}

	public void mouseDragged(MouseEvent me) {
		
		 int mAtX=me.getXOnScreen();
		 int mAtY=me.getYOnScreen();
		 
		 int w = buttons[0].getWidth();
		 int h = buttons[0].getHeight();
		 
		 for (int i = 0; i < buttons.length; i++ ) {
			 int btnX= buttons[i].getLocationOnScreen().x;       // x and y of the button, relative to Screen
		     int btnY=buttons[i].getLocationOnScreen().y; 
		     
			 if(mAtX>=btnX && mAtX<=btnX+w && mAtY>=btnY && mAtY<=btnY+h )
			 {
				 if(colors.equals("Background"))
					{
						buttons[i].setIcon(imgs[0]);
						imgsNames[i] = "images/blueblock.png";
					}
					else if(colors.equals("Dirt"))
					{
						buttons[i].setIcon(imgs[2]);
						imgsNames[i] = "images/browngrey.png";
					}
					else if(colors.equals("Rock"))
					{
						buttons[i].setIcon(imgs[4]);
						imgsNames[i] = "images/rockblock.png";
					}
					else if(colors.equals("Green Block"))
					{
						buttons[i].setIcon(imgs[1]);
						imgsNames[i] = "images/greenblock.png";
					}
					else if(colors.equals("Ice"))
					{
						buttons[i].setIcon(imgs[3]);
						imgsNames[i] = "images/ice.png";
					}
					else if(colors.equals("Hay"))
					{
						buttons[i].setIcon(imgs[5]);
						imgsNames[i] = "images/hay.png";
					}
					else if(colors.equals("Purple"))
					{
						buttons[i].setIcon(imgs[6]);
						imgsNames[i] = "images/purple.png";
					}
					else if(colors.equals("Heart Player"))
					{
						buttons[i].setIcon(inv[0]);
						imgsNames[i] = "images/heart.png";
					}
					else if(colors.equals("Key"))
					{
						buttons[i].setIcon(inv[1]);
						imgsNames[i] = "images/key.png";
					}
					else if(colors.equals("Money"))
					{
						buttons[i].setIcon(inv[2]);
						imgsNames[i] = "images/money.png";
					}
					else if(colors.equals("ATM"))
					{
						buttons[i].setIcon(inv[3]);
						imgsNames[i] = "images/ATM.png";
					}
			 }
		 }
	}

	public void mouseMoved(MouseEvent me) {

	}

	public void paint(Graphics g) {
		super.paint(g);
		

	}
	public void draw(Graphics g) {
		
		
	}

}


