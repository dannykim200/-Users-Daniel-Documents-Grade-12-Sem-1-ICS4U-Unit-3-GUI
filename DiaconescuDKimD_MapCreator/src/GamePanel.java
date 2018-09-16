/*
Assignment: Map Creator and Game Panel
Name: Daniel Kim and Dan Diaconescu
Teacher: Ms. Strelkovska
Course Code: ICS4U
Date: December 6, 2017
*/

/*
 * INSTRUCTIONS ON HOW TO PLAY THE GAME *
 * CONTROLS:
 * W TO MOVE UP
 * A TO MOVE LEFT
 * S TO MOVE DOWN
 * D TO MOVE RIGHT
 * I FOR INVENTORY
 * B TO NAVIGATE THROUGH INVENTORY
 * 
 * IN ORDER TO BEGIN PLAYING GAME, YOU MUST PRESS THE PLAY BUTTON AT THE BOTTOM LEFT CORNER 
 * LOAD BUTTON LOADS THE GAME
 * 
 * THE OBJECTIVE OF GAME IS TO STEP ONTO THE PURPLE BOX (THE END BLOCK)
 * IN ORDER TO GO THROUGH THE ATM MACHINES, YOU MUST HAVE PICKED UP ONE KEY 
 * IN ORDER TO STEP ON PURPLE BLOCK, YOU MUST HAVE PICKED UP THE KEY
 * 
 * LOAD GameMap.txt as sample map 
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GamePanel {

	public static void main(String[] args) {
		Game map = new Game();
		map.setVisible(true);
		map.setDefaultCloseOperation(map.EXIT_ON_CLOSE);
	}
 
}

class Game extends JFrame implements ActionListener, KeyListener{

	private static final Writer SAVE_FILE_NAME = null;
	//Variables
	Container c;
	JPanel p1, p2, p3;
	String color;
	JLabel boxes[];
	JLabel invent[];
	JLabel inventory;
	JButton play, load, exit;
	JButton blueBlock, brownBlock, greenBlock, ice, hay, purple, rockBlock;
	String colors= "";
	ImageIcon imgs[] = new ImageIcon[7];
	ImageIcon inv[] = new ImageIcon[4];
	String[] imgsNames = new String[400]; 

	int p;
	int code; 
	boolean i;
	boolean moneyC;
	boolean keyC;
	
	public Game() {
		super("GAME");
		c = getContentPane();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		boxes = new JLabel[400];
		p1.setLayout(new GridLayout(20, 20));
		
		invent = new JLabel[3];
		
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
		
		invent[0] = new JLabel(inv[1]);
		invent[1] = new JLabel(inv[2]);
		invent[2] = new JLabel(inv[3]);
		inventory = new JLabel("  INVENTORY  ");
		inventory.setForeground(Color.WHITE);
		
		for (int i = 0; i < 400; i++) {
			boxes[i] = new JLabel(imgs[0]);
			boxes[i].setBorder(null);
			p1.add(boxes[i]);
			boxes[i].addKeyListener(this);
			
		}
		//Initialize buttons
		play = new JButton("PLAY");
		load = new JButton("LOAD");
		exit = new JButton("EXIT");
		
		play.addActionListener(this);
		exit.addActionListener(this);
		load.addActionListener(this);
		
		play.addKeyListener(this);
		
		//panel and container 
		setSize(1250, 1000);
		p1.setSize(1250, 800);
		p2.setSize(1250, 200);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c.setLayout(new BorderLayout());
		c.add(p1, BorderLayout.CENTER);
		c.add(p2, BorderLayout.SOUTH);
		c.add(p3, BorderLayout.EAST);

		p2.setLayout(new GridLayout(1, 3));
		p3.setLayout(new GridLayout(4, 1));
		p3.setBackground(Color.BLACK);

		p2.add(play);
		p2.add(load);
		p2.add(exit);
		
		p3.add(inventory);
		p3.add(invent[0]);
		p3.add(invent[1]);
		p3.add(invent[2]);

		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent evt) {

		JButton b = (JButton) evt.getSource();
		String act = b.getActionCommand();
		
		//exit
		if(act.equals("EXIT"))
		{
			System.exit(0); 
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
						  if(imgsNames[i].equals("images/heart.png"))
						  {
							  p = i;
						  }
							  
						  boxes[i].setIcon(new ImageIcon(imgsNames[i]));  
					  }else {
						  boxes[i].setIcon(new ImageIcon("images/blueblock.png"));
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
	
	public void keyPressed( KeyEvent e )
	{
		code = e.getKeyCode();
		//press W to go up
		if(code == 87 && (imgsNames[p-20].equals("images/key.png")))
		{
			p-=20;
			boxes[p+20].setIcon(imgs[preMap(p+20)]);
			boxes[p].setIcon(inv[0]);
			keyC = true;
		}
		else if(code==87 &&(imgsNames[p-20].equals("images/purple.png")) && keyC){
			p-=20;
			boxes[p+20].setIcon(imgs[preMap(p+20)]);
			boxes[p].setIcon(inv[0]);
			
			JOptionPane.showMessageDialog( null, "YOU WON!", "YOU WON!", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (code == 87 && (imgsNames[p-20].equals("images/money.png")))
		{
			p-=20;
			boxes[p+20].setIcon(imgs[preMap(p+20)]);
			boxes[p].setIcon(inv[0]);
			moneyC = true;
		}
		else if(code == 87 && (imgsNames[p-20].equals("images/ATM.png")) && moneyC)
		{
			p-=20;
			boxes[p+20].setIcon(imgs[preMap(p+20)]);
			boxes[p].setIcon(inv[0]);
			imgsNames[p] = "images/blueblock.png";
			moneyC = false;
		}
		else if (code==87 && p > 19 && ((imgsNames[p-20].equals("images/blueblock.png"))||(imgsNames[p-20].equals("images/heart.png")) ||(imgsNames[p-20].equals("images/money.png"))||(imgsNames[p-20].equals("images/key.png"))))
		{
			p-=20;
			boxes[p+20].setIcon(imgs[preMap(p+20)]);
			boxes[p].setIcon(inv[0]);
		}
		
		//press A to go left
		else if(code == 65 && (imgsNames[p-1].equals("images/key.png")))
		{
			p--;
			boxes[p+1].setIcon(imgs[preMap(p+1)]);
			boxes[p].setIcon(inv[0]);
			keyC = true;
		}
		else if(code==65 &&(imgsNames[p-1].equals("images/purple.png")) && keyC){
			p--;
			boxes[p+1].setIcon(imgs[preMap(p+1)]);
			boxes[p].setIcon(inv[0]);
			
			JOptionPane.showMessageDialog( null, "YOU WON!", "YOU WON!", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (code == 65 && (imgsNames[p-1].equals("images/money.png")))
		{
			p--;
			boxes[p+1].setIcon(imgs[preMap(p+1)]);
			boxes[p].setIcon(inv[0]);
			moneyC = true;
		}
		else if(code == 65 && (imgsNames[p-1].equals("images/ATM.png")) && moneyC)
		{
			p--;
			boxes[p+1].setIcon(imgs[preMap(p+1)]);
			boxes[p].setIcon(inv[0]);
			imgsNames[p] = "images/blueblock.png";
			moneyC = false;
		}
		else if (code==65 && p > 0 && (p%20)!=0 && ((imgsNames[p-1].equals("images/blueblock.png"))||(imgsNames[p-1].equals("images/heart.png")) ||(imgsNames[p-1].equals("images/money.png"))||(imgsNames[p-1].equals("images/key.png"))))
		{
			p--;
			boxes[p+1].setIcon(imgs[preMap(p+1)]);
			boxes[p].setIcon(inv[0]);
		}
		
		//press S to go down 
		else if(code == 83 && (imgsNames[p+20].equals("images/key.png")))
		{
			p+=20;
			boxes[p-20].setIcon(imgs[preMap(p-20)]);
			boxes[p].setIcon(inv[0]);
			keyC = true;
		}
		else if(code == 83 &&(imgsNames[p+20].equals("images/purple.png")) && keyC){
			p-=20;
			boxes[p-20].setIcon(imgs[preMap(p-20)]);
			boxes[p].setIcon(inv[0]);
			
			JOptionPane.showMessageDialog( null, "YOU WON!", "YOU WON!", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (code == 83 && (imgsNames[p+20].equals("images/money.png")))
		{
			p+=20;
			boxes[p-20].setIcon(imgs[preMap(p-20)]);
			boxes[p].setIcon(inv[0]);
			moneyC = true;
		}
		else if(code == 83 && (imgsNames[p+20].equals("images/ATM.png")) && moneyC)
		{
			p+=20;
			boxes[p-20].setIcon(imgs[preMap(p-20)]);
			boxes[p].setIcon(inv[0]);
			imgsNames[p] = "images/blueblock.png";
			moneyC = false;
		}
		else if (code == 83 && p < 380 && ((imgsNames[p+20].equals("images/blueblock.png"))||(imgsNames[p+20].equals("images/heart.png")) || (imgsNames[p+20].equals("images/money.png"))||(imgsNames[p+20].equals("images/key.png"))))
		{
			p+=20;
			boxes[p-20].setIcon(imgs[preMap(p-20)]);
			boxes[p].setIcon(inv[0]);
		}
		
		//press D to go right
		else if(code == 68 && (imgsNames[p+1].equals("images/key.png")))
		{
			p++;
			boxes[p-1].setIcon(imgs[preMap(p-1)]);
			boxes[p].setIcon(inv[0]);
			keyC = true;
		}
		else if(code == 68 &&(imgsNames[p+1].equals("images/purple.png")) && keyC){
			p++;
			boxes[p-1].setIcon(imgs[preMap(p-1)]);
			boxes[p].setIcon(inv[0]);
			
			JOptionPane.showMessageDialog( null, "YOU WON!", "YOU WON!", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (code == 68 && (imgsNames[p+1].equals("images/money.png")))
		{
			p++;
			boxes[p-1].setIcon(imgs[preMap(p-1)]);
			boxes[p].setIcon(inv[0]);
			moneyC = true;
		}
		else if(code == 68 && (imgsNames[p+1].equals("images/ATM.png")) && moneyC)
		{
			p++;
			boxes[p-1].setIcon(imgs[preMap(p-1)]);
			boxes[p].setIcon(inv[0]);
			imgsNames[p] = "images/blueblock.png";
			moneyC = false;
			
		}
		else if (code == 68 && p < 399 && (p%20)!=19 && ((imgsNames[p+1].equals("images/blueblock.png"))||(imgsNames[p+1].equals("images/heart.png")) ||(imgsNames[p+1].equals("images/money.png"))||(imgsNames[p+1].equals("images/key.png"))))
		{
			p++;
			boxes[p-1].setIcon(imgs[preMap(p-1)]);
			boxes[p].setIcon(inv[0]);
		}
		
		//when you press i, the inventory opens 
		//when you press i again, the inventory closes
		else if (code == 73){
			
			if(invent[0].getBorder() == null && invent[1].getBorder() == null && invent[2].getBorder() == null)
			{
				invent[0].setBorder(BorderFactory.createLineBorder(Color.RED));
				i = true;
			}
			else if(!(invent[0].getBorder() == null))
			{
				invent[0].setBorder(null);
				i = false; 
			}

			if(!(invent[1].getBorder() == null))
			{
				invent[1].setBorder(null);
			}
			else if(!(invent[2].getBorder() == null))
			{
				invent[2].setBorder(null);
			}
				
		}
		
		//if you press b, it goes to the next item in the inventory 
		else if(code==66)
		{
			if(i) {
				
				if(!(invent[0].getBorder() == null))
				{
					invent[0].setBorder(null);
					invent[1].setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				else if(!(invent[1].getBorder()==null))
				{
					invent[1].setBorder(null);
					invent[2].setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				else if(!(invent[2].getBorder()==null))
				{
					invent[2].setBorder(null);
					invent[0].setBorder(BorderFactory.createLineBorder(Color.RED));
				}
			}
		}	
	}
	
	public int preMap (int index)
	{
		if(imgsNames[index].equals("images/greenblock.png"))
		{
			return 1;
		}
		else if(imgsNames[index].equals("images/brownblock.png"))
		{
			return 2;
		}
		else if(imgsNames[index].equals("images/ice.png"))
		{
			return 3;
		}
		else if(imgsNames[index].equals("images/rockblock.png"))
		{
			return 4;
		}
		else if(imgsNames[index].equals("images/hay.png"))
		{
			return 5;
		}
		else if(imgsNames[index].equals("images/purple.png"))
		{
			return 6;
		}
		return 0;
	}

	public void keyReleased( KeyEvent e )
	{
	}

	public void keyTyped( KeyEvent e )   {   }

	
	public void paint(Graphics g) {
		super.paint(g);
		c.setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g, int num) {
		super.paintComponents(g);
	}

}