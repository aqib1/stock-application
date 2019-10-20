package com.stock.controller.contenteditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.db.models.app.contents.FooterModel;
import com.db.models.app.contents.HeaderModel;
import com.stock.content.utility.Utility;

public class ContentFrame implements IContentObserver {

	private JFrame frame;
	private String[] textStyles = { "Plain", "Bold", "Italic" };
	private Integer[] textSizeArr = { 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 };
	private JComboBox<Integer> comboBox_textSize;
	private JComboBox<String> comboBox_fontType;
	private JComboBox<String> comboBox_textStyle;
	private JPanel panel_font_color;
	private IContentObserver iContentObserver;
	private ContentConsumerType consumerType;
	private JTextArea contentTextArea;
	private String font;
	private int textStyle = Font.PLAIN;
	private int textSize = textSizeArr[0];

	/**
	 * Create the application.
	 */
	public ContentFrame(IContentObserver iContentObserver) {
		this.iContentObserver = iContentObserver;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("Content Frame");
		frame.getContentPane().setBackground(UIManager.getColor("CheckBox.light"));
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblTextSize = new JLabel("Text size");
		lblTextSize.setBounds(12, 13, 56, 16);
		panel.add(lblTextSize);

		comboBox_textSize = new JComboBox<>();
		comboBox_textSize.setModel(new DefaultComboBoxModel<>(textSizeArr));
		comboBox_textSize.setBounds(80, 10, 130, 22);
		panel.add(comboBox_textSize);

		JLabel lblFontType = new JLabel("Font type");
		lblFontType.setBounds(246, 13, 56, 16);
		panel.add(lblFontType);

		comboBox_fontType = new JComboBox<>();
		comboBox_fontType.setModel(new DefaultComboBoxModel<>(
				GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
		comboBox_fontType.setBounds(314, 10, 130, 22);
		font = (String) comboBox_fontType.getSelectedItem();
		panel.add(comboBox_fontType);

		JLabel lblTextStyle = new JLabel("Text style");
		lblTextStyle.setBounds(12, 52, 56, 16);
		panel.add(lblTextStyle);

		comboBox_textStyle = new JComboBox<>();
		comboBox_textStyle.setModel(new DefaultComboBoxModel<>(textStyles));
		comboBox_textStyle.setBounds(80, 49, 130, 22);
		panel.add(comboBox_textStyle);

		JButton btnTextColro = new JButton("color");
		btnTextColro.setBounds(246, 50, 71, 25);
		panel.add(btnTextColro);
		addActionListenerForBtnColr(btnTextColro);

		panel_font_color = new JPanel();
		panel_font_color.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_font_color.setBounds(324, 52, 120, 23);
		panel_font_color.setBackground(Color.decode(Utility.DEFAULT_COLOR));
		panel.add(panel_font_color);

		JLabel lblEnterYourContent = new JLabel("Enter your Content here");
		lblEnterYourContent.setBounds(12, 103, 198, 16);
		panel.add(lblEnterYourContent);

		contentTextArea = new JTextArea();
		contentTextArea.setBorder(new MatteBorder(4, 2, 2, 2, (Color) new Color(0, 0, 0)));
		contentTextArea.setBackground(new Color(255, 250, 250));
		contentTextArea.setBounds(12, 134, 432, 147);
		contentTextArea.setFont(new Font(font, textStyle, textSize));
		panel.add(contentTextArea);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(347, 295, 97, 25);
		panel.add(btnOk);
		frame.setResizable(false);
		frame.setSize(new Dimension(475, 380));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		addingListenerForBtnOk(btnOk);
		addingListenerForComboBoxFontSize();
		addingListenerForComboBoxFontStyle();
		addingListenerForComboBoxFontFamily();
	}

	private void addingListenerForComboBoxFontFamily() {
		comboBox_fontType.addActionListener((x)->{
			font = (String) comboBox_fontType.getSelectedItem();
			contentTextArea.setFont(new Font(font, textStyle, textSize));
		});
		
	}

	private void addingListenerForComboBoxFontStyle() {
		comboBox_textStyle.addActionListener((c)->{
			textStyle = Utility.parseStyleStringToFont((String)comboBox_textStyle.getSelectedItem());
			contentTextArea.setFont(new Font(font, textStyle, textSize));
		});
	}

	private void addingListenerForComboBoxFontSize() {
		comboBox_textSize.addItemListener((c)->{
			textSize = (Integer)comboBox_textSize.getSelectedItem();
			contentTextArea.setFont(new Font(font, textStyle, textSize));
		});
	}

	private void addActionListenerForBtnColr(JButton btnTextColro) {
		btnTextColro.addActionListener((t) -> {
			Color color = Color.decode(Utility.DEFAULT_COLOR);
			Color newColor = JColorChooser.showDialog(frame, "Choose", color);
			panel_font_color.setBackground(newColor == null ? color : newColor);
			contentTextArea.setForeground(newColor == null ? color : newColor);
		});
	}

	private void addingListenerForBtnOk(JButton btnOk) {
		btnOk.addActionListener((i) -> {
			switch (consumerType) {
			case footer:
				FooterModel model = new FooterModel();
				model.setContentTextColor(panel_font_color.getBackground() == null ? Utility.DEFAULT_COLOR
						: Utility.toHexString(panel_font_color.getBackground()));
				model.setContent(Utility.isNullOrEmptyString(contentTextArea.getText().toString()) ? ""
						: contentTextArea.getText().toString());
				model.setContentTextStyle((String) comboBox_textStyle.getSelectedItem());
				model.setContentTextFont(font);
				model.setContentTextSize(textSize);
				observeForFotter(model);
				break;
			case header:
				HeaderModel headerModel = new HeaderModel();
				headerModel.setTitleColor(panel_font_color.getBackground() == null ? Utility.DEFAULT_COLOR
						: Utility.toHexString(panel_font_color.getBackground()));
				headerModel.setTitle(Utility.isNullOrEmptyString(contentTextArea.getText().toString()) ? ""
						: contentTextArea.getText().toString());
				headerModel.setTitleStyle((String) comboBox_textStyle.getSelectedItem());
				headerModel.setTitleFont(font);
				headerModel.setTitleSize(textSize);
				observeForHeader(headerModel);
				break;

			}
			frame.dispose();
		});

	}

	

	public JFrame getFrame() {
		return frame;
	}

	public void setConsumerType(ContentConsumerType consumerType) {
		this.consumerType = consumerType;
	}

	@Override
	public void observeForFotter(FooterModel footerModel) {
		iContentObserver.observeForFotter(footerModel);
	}

	@Override
	public void observeForHeader(HeaderModel headerModel) {
		iContentObserver.observeForHeader(headerModel);
	}
}
