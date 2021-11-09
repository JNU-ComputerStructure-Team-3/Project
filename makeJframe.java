import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JList;
import java.awt.Button;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.JEditorPane;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

public class makeJframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					makeJframe frame = new makeJframe();
					frame.setVisible(true);                  //시작과 동시에 보이도록
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public makeJframe() {
		setFont(new Font("Agency FB", Font.PLAIN, 16));
		setType(Type.POPUP);
		setTitle("입력창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 611);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setForeground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		textField = new JTextField();
		textField.setBounds(12, 10, 693, 25);
		textField.setToolTipText("");
		textField.setForeground(new Color(0, 0, 0));
		textField.setEditable(false);
		textField.setBackground(new Color(255, 255, 153));
		textField.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("컴퓨터구조 3조 - ComputerStructure"); 
		contentPane.add(textField);
		textField.setColumns(10);
		
		Button button = new Button("실행하기");
		button.setBounds(582, 503, 109, 39);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("Dialog", Font.BOLD, 15));
		button.setActionCommand("buttonCheck");
		contentPane.add(button);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setText("내용필드 : " +testprint());                 //여기에 작성해주세요
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(255, 255, 153));
		textField_1.setBounds(76, 95, 566, 369);
		contentPane.add(textField_1);

	}
	String testprint() {
		System.out.println("TEST 123");
		return "TESTTEST";
	}
}

