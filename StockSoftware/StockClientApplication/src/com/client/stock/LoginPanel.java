package com.client.stock;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.client.stock.loginObserver.ILoginObserver;
import com.db.Exception.DBException;
import com.db.models.authentication.AuthModel;
import com.db.utility.AppUtils;
import com.stock.content.ContentHelper;

public class LoginPanel implements ILoginObserver {

	private JFrame frame;
	private JTextField textField_username;
	private JTextField textField_password;
	private ILoginObserver loginObserver;

	/**
	 * Create the application.
	 */
	public LoginPanel(ILoginObserver loginObserver) {
		this.loginObserver = loginObserver;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Authentication");
		frame.setBounds(100, 100, 450, 296);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Calibri", Font.BOLD, 35));
		lblLogin.setBounds(12, 13, 116, 40);
		panel.add(lblLogin);

		JLabel lblEnterUsername = new JLabel("Enter username");
		lblEnterUsername.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblEnterUsername.setBounds(42, 66, 140, 23);
		panel.add(lblEnterUsername);

		textField_username = new JTextField();
		textField_username.setFont(new Font("Calibri", Font.PLAIN, 21));
		textField_username.setBounds(194, 66, 212, 23);
		panel.add(textField_username);
		textField_username.setColumns(10);

		JLabel lblEnterPassword = new JLabel("Enter password");
		lblEnterPassword.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblEnterPassword.setBounds(42, 117, 140, 23);
		panel.add(lblEnterPassword);

		textField_password = new JTextField();
		textField_password.setFont(new Font("Calibri", Font.PLAIN, 21));
		textField_password.setColumns(10);
		textField_password.setBounds(194, 117, 212, 23);
		panel.add(textField_password);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 20));
		btnLogin.setBounds(290, 169, 116, 49);
		panel.add(btnLogin);

		btnLogin.addActionListener((c) -> {
			String userName = textField_username.getText().toString();
			String password = textField_password.getText().toString();
			if (AppUtils.isNullOrEmptyString(userName) || AppUtils.isNullOrEmptyString(password)) {
				JOptionPane.showMessageDialog(frame, "Please fill both fields username and password!!!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				AuthModel authModel = new AuthModel();
				authModel.setUsername(userName);
				authModel.setPassword(password);

				try {
					if (ContentHelper.getInstance().isValidLoginAuth(authModel)) {
						notifyLoginApproval(true);
					} else {
						JOptionPane.showMessageDialog(frame, "Invalid username or password!!!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (DBException | IllegalArgumentException e) {
					JOptionPane.showMessageDialog(frame,
							"ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void notifyLoginApproval(boolean isSuccess) {
		getFrame().dispose();
		loginObserver.notifyLoginApproval(isSuccess);
	}
}
