package com.example.mem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MemWindow extends JFrame {
	/**
	 * 
	 */
	String keyText="RAMU";
	JPanel CardsPanel;
	double score=0;
	int levelCount=1;
	int moveSpeed=200;
	JButton btnStart = new JButton("start");
	JFrame frame=this;
	private static final long serialVersionUID = 1L;
	JLabel rightupperbutton, lowerbtn , leftLower, rightbtn, rightLower, leftUpperbtn, leftbtn, upperbtn, centerbtn;
	public static void main(String ...args)
	{
		new MemWindow().setVisible(true);
		
	}
	public MemWindow() {
	
		setBounds(new Rectangle(getToolkit().getScreenSize().width/2-250,getToolkit().getScreenSize().height/2-250, 700, 500));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 10, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardsPanel = new JPanel();
		panel.add(CardsPanel);
		CardsPanel.setLayout(new GridLayout(0, 3, 10,20));
		

		 leftUpperbtn = new JLabel("New button");
		 CardsPanel.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		levelCount++;
			 		moveSpeed*=.95;
			 		levelLabel.setText("Level:   "+levelCount);
			 		btnStart.doClick();
			 	}
			 });
		 leftUpperbtn.setOpaque(true);
		leftUpperbtn.setVisible(false);
		CardsPanel.add(leftUpperbtn);
		
		upperbtn = new JLabel("New button");
		upperbtn.setOpaque(true);
		upperbtn.setVisible(false);
		CardsPanel.add(upperbtn);
		
		 rightupperbutton = new JLabel("New button");
		 rightupperbutton.setOpaque(true);
		rightupperbutton.setVisible(false);
		CardsPanel.add(rightupperbutton);
		
		leftbtn = new JLabel(" ");
		leftbtn.setForeground(Color.PINK);
		//leftbtn.setIcon(new ImageIcon("C:\\Users\\shiva\\Pictures\\3-10.jpg"));
		leftbtn.setFont(new Font("Gill Sans MT", Font.BOLD, 35));
		leftbtn.setHorizontalAlignment(SwingConstants.CENTER);
		leftbtn.setHorizontalTextPosition(SwingConstants.CENTER);
		leftbtn.setOpaque(true);
		leftbtn.addMouseListener(mouselis);
		leftbtn.setBackground(Color.BLUE);
		CardsPanel.add(leftbtn);
		
		 centerbtn = new JLabel(keyText);
		 centerbtn.setForeground(Color.PINK);
		// centerbtn.setIcon(new ImageIcon("C:\\Users\\shiva\\Pictures\\3-10.jpg"));
		 centerbtn.setFont(new Font("Gill Sans MT", Font.BOLD, 35));
		 centerbtn.setHorizontalAlignment(SwingConstants.CENTER);
		 centerbtn.setHorizontalTextPosition(SwingConstants.CENTER);
		 centerbtn.setOpaque(true);
		 centerbtn.addMouseListener(mouselis);
		 centerbtn.setBackground(Color.BLUE);
		CardsPanel.add(centerbtn);
		
		rightbtn = new JLabel(" ");
		rightbtn.setForeground(Color.PINK);
		//rightbtn.setIcon(new ImageIcon("C:\\Users\\shiva\\Pictures\\3-10.jpg"));
		rightbtn.setFont(new Font("Gill Sans MT", Font.BOLD, 35));
		rightbtn.setHorizontalAlignment(SwingConstants.CENTER);
		rightbtn.setHorizontalTextPosition(SwingConstants.CENTER);
		rightbtn.setOpaque(true);
		rightbtn.setBackground(Color.BLUE);
		rightbtn.addMouseListener(mouselis);
		CardsPanel.add(rightbtn);
		
		 leftLower = new JLabel("New button");
		 leftLower.setOpaque(true);
		leftLower.setVisible(false);
		CardsPanel.add(leftLower);
		
		lowerbtn = new JLabel("New button");
		lowerbtn.setOpaque(true);
		lowerbtn.setVisible(false);
		CardsPanel.add(lowerbtn);
		
		 rightLower = new JLabel("New button");
		 rightLower.setOpaque(true);
		rightLower.setVisible(false);
		CardsPanel.add(rightLower);
		
		panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		if(levelCount>1)
		 		{
		 		levelCount--;
		 		moveSpeed*=1.05;
		 		levelLabel.setText("Level:   "+levelCount);
		 		}
		 	}
		 });
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		

		panel_1.add(btnStart);
		
		scoreLabel = new JLabel("Score   ");
		panel_1.add(scoreLabel);
		
		levelLabel = new JLabel("Level       ");
		panel_1.add(levelLabel);
		btnStart.addActionListener(nextLevel);
		//t.start();
	}
	
	void removeActionsFromButtons(Boolean kro)
	{
		if(kro)
		{	
		leftbtn.removeMouseListener(mouselis);
		rightbtn.removeMouseListener(mouselis);
		centerbtn.removeMouseListener(mouselis);
		}
		else
		{

			leftbtn.addMouseListener(mouselis);
			rightbtn.addMouseListener(mouselis);
			centerbtn.addMouseListener(mouselis);
		}
	}
	
	synchronized Thread moveCards()
	{
	 Thread t=new Thread(new Runnable() {
	public void run()  {
		try{
			removeActionsFromButtons(true);
			double i=0;
			while(i<levelCount)
		{
				int random=Math.abs(new Random().nextInt()%4);
				switch(random)
				{
				case 0:
					leftToMiddlecircleClockwise() ;
					break;
				case 1:
					leftToMiddlecircleAntiClockwise() ;
				break;
				case 2:
					leftTorightcircle();
					break;
				case 3:
					rightToleftcircle();
					
				}
				i=i+3.50;
		}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			removeActionsFromButtons(false);
		}
	
	
	}});
	 t.start();
	 return t;
	}
	 void move(JLabel moveTo,JLabel moveFrom)
	 {
		 moveTo.setText(moveFrom.getText());
		 moveTo.setForeground(moveFrom.getForeground());
		 moveTo.setBackground(moveFrom.getBackground());
		 //moveTo.setIcon(moveFrom.getIcon());
		 moveTo.setVisible(true);
		 moveFrom.setVisible(false);
	 }
	 void leftTorightcircle() throws Exception
	 {
		 Thread.sleep(moveSpeed);

			move(leftUpperbtn,leftbtn);

			Thread.sleep(moveSpeed);

			move(upperbtn,leftUpperbtn);
			
			Thread.sleep(moveSpeed);
			
			move(rightupperbutton,upperbtn);

			Thread.sleep(moveSpeed);
			
			move(rightLower,rightbtn);
			
				Thread.sleep(moveSpeed);
			
			move(rightLower,rightbtn);

			Thread.sleep(moveSpeed);
		
			move(rightbtn,rightupperbutton);

			Thread.sleep(moveSpeed);
			
			move(rightbtn,rightupperbutton);

			Thread.sleep(moveSpeed);
			
			move(lowerbtn,rightLower);
			
			Thread.sleep(moveSpeed);
			
			move(leftLower,lowerbtn);

			Thread.sleep(moveSpeed);
			

			move(leftbtn,leftLower);
	
	 }
	 void leftToMiddlecircleClockwise() throws Exception
	 {
		 Thread.sleep(moveSpeed);

			move(leftUpperbtn,leftbtn);

			Thread.sleep(moveSpeed);

			move(upperbtn,leftUpperbtn);
			
			Thread.sleep(moveSpeed);
			
			move(lowerbtn,centerbtn);

			Thread.sleep(moveSpeed);
			

			move(centerbtn,upperbtn);

			Thread.sleep(moveSpeed);
			

			move(leftLower,lowerbtn);

			Thread.sleep(moveSpeed);
			

			move(leftbtn,leftLower);
			
	 }
	 void leftToMiddlecircleAntiClockwise() throws Exception
	 {
		 Thread.sleep(moveSpeed);

			move(rightupperbutton,rightbtn);

			Thread.sleep(moveSpeed);

			move(upperbtn,rightupperbutton);
			
			Thread.sleep(moveSpeed);
			
			move(lowerbtn,centerbtn);

			Thread.sleep(moveSpeed);
			
			move(centerbtn,upperbtn);

			Thread.sleep(moveSpeed);
			
			move(leftLower,lowerbtn);
			

			Thread.sleep(moveSpeed);
			
			move(leftUpperbtn,leftbtn);

			Thread.sleep(moveSpeed);
			
			move(leftbtn,leftLower);
			

			Thread.sleep(moveSpeed);
			
			move(upperbtn,leftUpperbtn);
			

			Thread.sleep(moveSpeed);
			
			move(rightupperbutton,upperbtn);

			Thread.sleep(moveSpeed);
			
			move(rightbtn,rightupperbutton);
			
			
	 }
	 void rightToleftcircle() throws Exception
	 {
		 Thread.sleep(moveSpeed);

			move(rightupperbutton,rightbtn); // |

			Thread.sleep(moveSpeed);   

			move(upperbtn,rightupperbutton);    // -
			
			Thread.sleep(moveSpeed);
			
			move(leftUpperbtn,upperbtn);      //-

			Thread.sleep(moveSpeed);
			
			move(leftLower,leftbtn);     //   T
			
				Thread.sleep(moveSpeed);
			
			move(leftbtn,leftUpperbtn); // T

			Thread.sleep(moveSpeed);
		
			move(lowerbtn,leftLower); //-

			Thread.sleep(moveSpeed);
			
			move(rightLower,lowerbtn);

			Thread.sleep(moveSpeed);
			
			move(rightbtn,rightLower);
			
			Thread.sleep(moveSpeed);
		
	 }
	Thread t;
	ActionListener nextLevel=new ActionListener() {
		
		@Override
		
		public void actionPerformed(ActionEvent e) {
			if(!e.getActionCommand().equals("start"))
			{
			if((!t.isAlive())&&(proceedtonewGame==1))
		
				leftbtn.setBackground(Color.blue);
				rightbtn.setBackground(Color.blue);
				centerbtn.setBackground(Color.blue);
				leftbtn.setForeground(Color.blue);
				rightbtn.setForeground(Color.blue);
				centerbtn.setForeground(Color.blue);
				levelCount+=1;
				moveSpeed=(int) (moveSpeed*0.95);
				t=moveCards();
				proceedtonewGame=0;
			
	
			}
			else 
			{

				((JButton)e.getSource()).setText("next Level");
				t=moveCards();
				centerbtn.setForeground(Color.blue);
				proceedtonewGame=0;
				
				
				
				
			}
			levelLabel.setText("Level:   "+levelCount);
			block=0;
			
			
		}
	};
	MouseListener mouselis=new MouseAdapter() {
		

		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			proceedtonewGame=1;
			if(block==0)
			if(((JLabel) e.getSource()).getText().equals(keyText))
			{
				block=1;
				
				JLabel btn=(JLabel)e.getSource();
				btn.setBackground(Color.GREEN);
				JOptionPane.showMessageDialog(frame, "Correct");
				leftbtn.setForeground(Color.black);
				centerbtn.setForeground(Color.black);
				rightbtn.setForeground(Color.black);
				score=score*1.10+100*levelCount;
				scoreLabel.setText("Score:  "+(Math.floor(score))+"   ");		
			}
			else
			{
				block=1;
				JOptionPane.showMessageDialog(frame, "wrong");
				score=score/1.05;
				scoreLabel.setText("Score:  "+(score)+"   ");
				leftbtn.setForeground(Color.black);
				centerbtn.setForeground(Color.black);
				rightbtn.setForeground(Color.black);
				if(centerbtn.getText().equals(keyText))
					centerbtn.setBackground(Color.RED);
				else if(leftbtn.getText().equals(keyText))
				{
					leftbtn.setBackground(Color.red);
				}
				else if(rightbtn.getText().equals(keyText))
				{
					rightbtn.setBackground(Color.red);
				}
			}	

		}
	};
	int proceedtonewGame=0;
	int block=1;
	private JPanel panel_1;
	private JLabel scoreLabel;
	private JLabel levelLabel;
}
