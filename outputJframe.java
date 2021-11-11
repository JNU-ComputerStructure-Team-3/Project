import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
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

public class outputJframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					outputJframe frame = new outputJframe();
					frame.setVisible(true);                  //시작과 동시에 보이도록
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public outputJframe() {
		setFont(new Font("Agency FB", Font.PLAIN, 16));
		setType(Type.POPUP);
		setTitle("출력창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 611);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setForeground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setText("내용필드 : " +testPrint());                 //여기에 작성해주세요
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(255, 255, 153));
		textField_1.setBounds(76, 92, 566, 39);
		contentPane.add(textField_1);
		
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
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("");
		textField_2.setText("2");             //여기에 작성해주세요
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.BLACK);
		textField_2.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(255, 255, 153));
		textField_2.setBounds(76, 184, 566, 39);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("");
		textField_3.setText("3");		 //여기에 작성해주세요
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(Color.BLACK);
		textField_3.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(255, 255, 153));
		textField_3.setBounds(76, 282, 566, 39);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setToolTipText("");
		textField_4.setText("4");		 //여기에 작성해주세요
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setForeground(Color.BLACK);
		textField_4.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(255, 255, 153));
		textField_4.setBounds(76, 366, 566, 39);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setToolTipText("");
		textField_5.setText("5");		 //여기에 작성해주세요
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setForeground(Color.BLACK);
		textField_5.setFont(new Font("함초롬돋움", Font.BOLD, 14));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(new Color(255, 255, 153));
		textField_5.setBounds(76, 457, 566, 39);
		contentPane.add(textField_5);

	}
	String testPrint() {
		return "TESTTEST"+"TEST"+"T";
	}
}

