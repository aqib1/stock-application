package com.stock.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.db.models.authentication.AuthModel;
import com.db.utility.AppUtils;
import com.stock.content.ContentHelper;

public class UserAuthController {

	private JFrame frame;
	private JTextField textFieldUserName;
	private JTextField textField_Password;
	private JTextField textField_Cpassword;
	private JTextField textField_email;
	private JTextField textField_age;
	private JTextField textField_gender;
	private JTextField textField_contactno;
	private JTextField textField_address;

	/**
	 * Create the application.
	 */
	public UserAuthController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Auth-add window");
		frame.setBounds(100, 100, 484, 517);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblAuthUserCreation = new JLabel("Auth User Creation");
		lblAuthUserCreation.setForeground(new Color(128, 0, 0));
		lblAuthUserCreation.setFont(new Font("Calibri", Font.BOLD, 32));
		lblAuthUserCreation.setBounds(12, 13, 257, 47);
		panel.add(lblAuthUserCreation);

		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblUserName.setBounds(22, 73, 109, 21);
		panel.add(lblUserName);

		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(188, 73, 243, 22);
		panel.add(textFieldUserName);
		textFieldUserName.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblPassword.setBounds(22, 118, 109, 21);
		panel.add(lblPassword);

		textField_Password = new JTextField();
		textField_Password.setColumns(10);
		textField_Password.setBounds(188, 117, 243, 22);
		panel.add(textField_Password);

		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(22, 165, 154, 21);
		panel.add(lblConfirmPassword);

		textField_Cpassword = new JTextField();
		textField_Cpassword.setColumns(10);
		textField_Cpassword.setBounds(188, 164, 243, 22);
		panel.add(textField_Cpassword);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblEmail.setBounds(22, 210, 154, 21);
		panel.add(lblEmail);

		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(188, 209, 243, 22);
		panel.add(textField_email);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblAge.setBounds(22, 257, 154, 21);
		panel.add(lblAge);

		textField_age = new JTextField();
		textField_age.setColumns(10);
		textField_age.setBounds(188, 256, 243, 22);
		panel.add(textField_age);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblGender.setBounds(22, 300, 154, 21);
		panel.add(lblGender);

		textField_gender = new JTextField();
		textField_gender.setToolTipText("M or F");
		textField_gender.setColumns(10);
		textField_gender.setBounds(188, 299, 243, 22);
		panel.add(textField_gender);

		JButton btnSaveAuth = new JButton("Save Auth");
		btnSaveAuth.setBorder(new LineBorder(new Color(128, 0, 0), 2));
		btnSaveAuth.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSaveAuth.setForeground(new Color(128, 0, 0));
		btnSaveAuth.setBounds(316, 432, 115, 25);
		panel.add(btnSaveAuth);

		textField_contactno = new JTextField();
		textField_contactno.setColumns(10);
		textField_contactno.setBounds(188, 346, 243, 22);
		panel.add(textField_contactno);

		JLabel lblContactNumber = new JLabel("Contact number");
		lblContactNumber.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblContactNumber.setBounds(22, 347, 154, 21);
		panel.add(lblContactNumber);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblAddress.setBounds(22, 392, 154, 21);
		panel.add(lblAddress);

		textField_address = new JTextField();
		textField_address.setColumns(10);
		textField_address.setBounds(188, 391, 243, 22);
		panel.add(textField_address);

		btnSaveAuth.addActionListener(x -> {
			try {
				AuthModel authModel = new AuthModel();
				authModel.setUsername(textFieldUserName.getText().toString());
				authModel.setPassword(textField_Password.getText().toString());
				authModel.setC_password(textField_Cpassword.getText().toString());
				authModel.setContactNumber(textField_contactno.getText().toString());
				String gender = textField_gender.getText().toString();
				if (gender.equals(AppUtils.MALE) || gender.equals(AppUtils.FEMALE)) {
					authModel.setGender(gender);
				} else {
					JOptionPane.showMessageDialog(frame, "Gender can be [M or F]", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				authModel.setAge(Integer.parseInt(textField_age.getText().toString()));
				authModel.setAddress(textField_address.getText().toString());
				authModel.setEmail(textField_email.getText().toString());
				if (AppUtils.isNull(ContentHelper.getInstance().insertAuth(authModel))) {
					JOptionPane.showMessageDialog(frame, "Authentication not saved successfully", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "Authentication saved successfully!!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					SwingUtilities.invokeLater(() -> {
						textFieldUserName.setText("");
						textField_Password.setText("");
						textField_Cpassword.setText("");
						textField_contactno.setText("");
						textField_gender.setText("");
						textField_age.setText("");
						textField_address.setText("");
						textField_email.setText("");
					});

				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
						"Error", JOptionPane.ERROR_MESSAGE);
			}

		});
	}

	public JFrame getFrame() {
		return frame;
	}
}
