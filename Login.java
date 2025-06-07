package DashBoard;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;

public class Login {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Login window = new Login();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setBounds(100, 100, 533, 366);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(164, 85, 87, 17);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.getContentPane().add(lblUsername);

		usernameField = new JTextField();
		usernameField.setBounds(258, 85, 95, 18);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(164, 124, 87, 20);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(258, 125, 95, 18);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(102, 175, 324, 3);
		frame.getContentPane().add(separator);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(218, 205, 86, 32);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Please input your credentials");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(163, 39, 193, 17);
		frame.getContentPane().add(lblNewLabel);

	
		btnLogin.addActionListener(e -> {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());

		
			if (username.equals("admin") && password.equals("1234")) {
				JOptionPane.showMessageDialog(frame, "Login successful!");
				frame.dispose();
			
				Index index = new Index(); 
				index.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}
