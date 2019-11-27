import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Dimension;

public class GUICrypt {
	public static void main(String[] args) throws FileNotFoundException {
		// list of words
		File rockyou = new File("rockyou.txt");
		Scanner in = new Scanner(rockyou);

		// declare frame
		JFrame frame = new JFrame();
		final int FWIDTH = 800;
		final int FHEIGHT = 800;
		frame.setSize(FWIDTH, FHEIGHT);

		// init text fields and labels
		JLabel hashLabel = new JLabel("Hash: ");
		JTextField hashField = new JTextField(10);
		JLabel saltLabel = new JLabel("Salt: ");
		JTextField saltField = new JTextField(10);

		// init text area
		JTextArea textArea = new JTextArea(10, 10);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);

		// declare decrypt button
		JButton button = new JButton("Decrypt");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String hash = hashField.getText();
					String salt = saltField.getText();
					// f9bb7550503c6711e15ea768874fa7f1 is charlie with salt of "15"
					if (!hash.equals("") && !salt.equals("")) {
						String mypwd = Pwd.DictCrypt(hash, salt);
						textArea.append(mypwd  + '\n');
					}
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


		// init panel
		JPanel panel = new JPanel();
		panel.add(hashLabel);
		panel.add(hashField);
		panel.add(saltLabel);
		panel.add(saltField);
		panel.add(button);
		panel.add(scrollPane);


		// init frame
		frame.add(panel);
		frame.setTitle("GUI Crypt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// close the scanner
		in.close();
	}
}