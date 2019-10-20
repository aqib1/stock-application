package com.stock.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.db.Exception.DBException;
import com.db.models.app.contents.ApplicationModel;
import com.db.models.app.contents.FooterModel;
import com.db.models.app.contents.HeaderModel;
import com.db.models.menus.MenuModel;
import com.db.models.screencontents.ScreenContentModel;
import com.db.models.screens.ScreenModel;
import com.db.utility.AppUtils;
import com.stock.content.ContentHelper;
import com.stock.content.utility.Utility;
import com.stock.controller.contenteditor.ContentConsumerType;
import com.stock.controller.contenteditor.ContentFrame;
import com.stock.controller.contenteditor.IContentObserver;

public class AdminController implements IContentObserver {

	private JFrame frame;
	private JTextField textField_app_name;
	private JTextField textField_window;
	private ApplicationModel applicationModel = null;
	private FooterModel footerModel = null;
	private HeaderModel headerModel = null;
	private Map<String, MenuModel> menus = null;
	private Map<String, ScreenModel> screens = null;
	private Map<String, ScreenContentModel> screenContents = null;
	private JTextField textField_newApplicationName;
	private JTextField textField_new_window_title;
	private JTextField textField_new_menu_name;
	private JTextField textField_new_screen_name;
	private JTextField textField_new_content_name;
	private JPanel panel_error;
	private JComboBox<MenuModel> comboBox_menus;
	private JComboBox<ScreenModel> comboBox_screen_title;
	private JComboBox<ScreenContentModel> comboBoxScreenContents;
	private JTextArea textArea_preview_content_footer;
	private JTextArea textArea_preview_header_content;

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			try {
				ContentHelper.getInstance().initContents();
			} catch (DBException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error",
						"Some Error occurs while fetching data, Please contact developer", JOptionPane.ERROR_MESSAGE);
			}
		});
		t1.start();

		try {
			t1.join();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

		new Thread(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				AdminController window = new AdminController();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	/**
	 * Launch the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public AdminController() {
		intializeData();
		initialize();
	}

	private void intializeData() {
		applicationModel = ContentHelper.getInstance().getApplicationModel();
		footerModel = ContentHelper.getInstance().getFooterModel();
		headerModel = ContentHelper.getInstance().getHeaderModel();
		menus = ContentHelper.getInstance().getMenus();
		screens = ContentHelper.getInstance().getScreens();
		screenContents = ContentHelper.getInstance().getScreenContentModel();
	}

	private void initialize() {
		frame = new JFrame("Admin panel");
		frame.setSize(new Dimension(958, 837));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel_background_fotter_color = new JPanel();
		panel_background_fotter_color.setBackground(UIManager.getColor("CheckBox.light"));
		panel_background_fotter_color.setLayout(null);
		
		JScrollPane jsp = new JScrollPane(panel_background_fotter_color);
		panel_background_fotter_color.setPreferredSize(frame.getSize());
		frame.getContentPane().add(jsp, BorderLayout.CENTER);
		JLabel lblApplicationAdmin = new JLabel("Application - Admin");
		lblApplicationAdmin.setFont(new Font("Calibri", Font.BOLD, 32));
		lblApplicationAdmin.setBounds(12, 13, 323, 32);
		panel_background_fotter_color.add(lblApplicationAdmin);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(UIManager.getColor("Button.foreground"));
		separator.setBackground(UIManager.getColor("Button.focus"));
		separator.setBounds(166, 83, 2, 706);
		panel_background_fotter_color.add(separator);

		JLabel lblApplicationName = new JLabel("Application - name");
		lblApplicationName.setFont(new Font("Calibri", Font.BOLD, 16));
		lblApplicationName.setBounds(12, 100, 142, 24);
		panel_background_fotter_color.add(lblApplicationName);

		textField_app_name = new JTextField();
		textField_app_name.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_app_name.setBounds(183, 100, 191, 24);
		textField_app_name.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textField_app_name.setText(applicationModel.getApp_name());
		panel_background_fotter_color.add(textField_app_name);
		textField_app_name.setColumns(10);

		JLabel lblMainMenus = new JLabel("Main - Menus");
		lblMainMenus.setFont(new Font("Calibri", Font.BOLD, 16));
		lblMainMenus.setBounds(12, 214, 142, 24);
		panel_background_fotter_color.add(lblMainMenus);

		comboBox_menus = new JComboBox<>();
		addingMenusToComboBox_Menus();
		comboBox_menus.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 1, true));
		comboBox_menus.setBounds(180, 214, 191, 22);
		panel_background_fotter_color.add(comboBox_menus);
		addingListenerOnCheckBoxMenuTitle();

		JLabel label_2 = new JLabel("Window - title");
		label_2.setFont(new Font("Calibri", Font.BOLD, 16));
		label_2.setBounds(12, 158, 142, 24);
		panel_background_fotter_color.add(label_2);

		textField_window = new JTextField();
		textField_window.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textField_window.setBounds(180, 158, 191, 22);
		textField_window.setText(applicationModel.getWindow_name());
		panel_background_fotter_color.add(textField_window);
		textField_window.setColumns(10);

		JLabel lblScreenTitle = new JLabel("Screen - title");
		lblScreenTitle.setFont(new Font("Calibri", Font.BOLD, 16));
		lblScreenTitle.setBounds(12, 273, 142, 24);
		panel_background_fotter_color.add(lblScreenTitle);

		comboBox_screen_title = new JComboBox<ScreenModel>();
		comboBox_screen_title.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 1, true));
		comboBox_screen_title.setBounds(180, 273, 191, 22);
		addingScreensToScreentitleComboBox();
		addingListenerOnCheckBoxScreenTitle();
		panel_background_fotter_color.add(comboBox_screen_title);

		JLabel lblFooterEnable = new JLabel("Footer - availability");
		lblFooterEnable.setFont(new Font("Calibri", Font.BOLD, 16));
		lblFooterEnable.setBounds(12, 487, 142, 24);
		panel_background_fotter_color.add(lblFooterEnable);

		JCheckBox chckbxRequiredFotter = new JCheckBox("Required");
		chckbxRequiredFotter.setBackground(UIManager.getColor("CheckBox.light"));
		chckbxRequiredFotter.setBounds(176, 486, 79, 25);
		chckbxRequiredFotter.setSelected(Utility.YES.equals(footerModel.getIsActive()));
		panel_background_fotter_color.add(chckbxRequiredFotter);

		JPanel panel_fotter_settings = new JPanel();
		panel_fotter_settings.setBorder(null);
		panel_fotter_settings.setLayout(null);
		panel_fotter_settings.setBackground(UIManager.getColor("CheckBox.light"));
		panel_fotter_settings.setBounds(263, 447, 635, 121);
		panel_background_fotter_color.add(panel_fotter_settings);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 13, 2, 81);
		panel_fotter_settings.add(separator_3);
		separator_3.setBackground(SystemColor.activeCaptionText);
		separator_3.setOrientation(SwingConstants.VERTICAL);

		JPanel footerBackgroundColor = new JPanel();
		footerBackgroundColor.setBounds(22, 24, 78, 24);
		panel_fotter_settings.add(footerBackgroundColor);
		footerBackgroundColor.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		footerBackgroundColor.setBackground(Color
				.decode(footerModel.getBackground() == null ? Utility.DEFAULT_COLOR : footerModel.getBackground()));

		JButton btn_footer_choose_color = new JButton("colors");
		btn_footer_choose_color.setBounds(22, 54, 78, 25);
		panel_fotter_settings.add(btn_footer_choose_color);
		btn_footer_choose_color.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		addingColorAction(footerBackgroundColor, btn_footer_choose_color);

		JCheckBox chckbxBorderForFooter = new JCheckBox("Border");
		chckbxBorderForFooter.setBounds(108, 23, 76, 25);
		panel_fotter_settings.add(chckbxBorderForFooter);
		chckbxBorderForFooter.setSelected(Utility.YES.equals(footerModel.getIsBorder()));
		chckbxBorderForFooter.setBackground(UIManager.getColor("CheckBox.light"));

		JCheckBox checkBoxRoundCorner_footer = new JCheckBox("Round");
		checkBoxRoundCorner_footer.setBounds(108, 54, 76, 25);
		panel_fotter_settings.add(checkBoxRoundCorner_footer);
		checkBoxRoundCorner_footer.setSelected(Utility.YES.equals(footerModel.getIsRound()));
		checkBoxRoundCorner_footer.setBackground(SystemColor.controlHighlight);

		JCheckBox chckbxClock = new JCheckBox("Clock");
		chckbxClock.setBounds(188, 24, 76, 25);
		panel_fotter_settings.add(chckbxClock);
		chckbxClock.setSelected(Utility.YES.equals(footerModel.getIsClock()));
		chckbxClock.setBackground(SystemColor.controlHighlight);

		JButton btnContentFooter = new JButton("content");
		btnContentFooter.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnContentFooter.setBounds(186, 54, 78, 25);
		panel_fotter_settings.add(btnContentFooter);
		addingActionListenerOnBtnContentFooter(btnContentFooter);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(276, 13, 2, 81);
		panel_fotter_settings.add(separator_1);

		JScrollPane scrollPaneForFooterAreaPreview = new JScrollPane((Component) null);
		scrollPaneForFooterAreaPreview.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneForFooterAreaPreview.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneForFooterAreaPreview.setBorder(new LineBorder(new Color(153, 51, 0), 2, true));
		scrollPaneForFooterAreaPreview.setBounds(290, 20, 333, 70);
		panel_fotter_settings.add(scrollPaneForFooterAreaPreview);

		textArea_preview_content_footer = new JTextArea();
		textArea_preview_content_footer.setText(footerModel.getContent());
		textArea_preview_content_footer.setFont(new Font(footerModel.getContentTextFont(),
				Utility.parseStyleStringToFont(footerModel.getContentTextStyle()), footerModel.getContentTextSize()));
		textArea_preview_content_footer.setForeground(Color.decode(footerModel.getContentTextColor()));
		textArea_preview_content_footer.setEditable(false);
		textArea_preview_content_footer.setBackground(new Color(248, 248, 255));
		scrollPaneForFooterAreaPreview.setViewportView(textArea_preview_content_footer);

		JLabel lblHeaderAvailability = new JLabel("Header - availability");
		lblHeaderAvailability.setFont(new Font("Calibri", Font.BOLD, 16));
		lblHeaderAvailability.setBounds(12, 621, 142, 24);
		panel_background_fotter_color.add(lblHeaderAvailability);

		JCheckBox checkBox_header_required = new JCheckBox("Required");
		checkBox_header_required.setBackground(SystemColor.controlHighlight);
		checkBox_header_required.setBounds(176, 620, 79, 25);
		checkBox_header_required.setSelected(Utility.YES.equals(headerModel.getIsActive()));
		panel_background_fotter_color.add(checkBox_header_required);

		JPanel panel_hold_header_attr = new JPanel();
		panel_hold_header_attr.setBorder(null);
		panel_hold_header_attr.setBackground(UIManager.getColor("CheckBox.light"));
		panel_hold_header_attr.setBounds(263, 590, 635, 121);
		panel_background_fotter_color.add(panel_hold_header_attr);
		panel_hold_header_attr.setVisible(false);
		panel_hold_header_attr.setLayout(null);

		JCheckBox checkBox_border_header = new JCheckBox("Border");
		checkBox_border_header.setBounds(112, 24, 67, 25);
		checkBox_border_header.setSelected(Utility.YES.equals(headerModel.getIsBorder()));
		panel_hold_header_attr.add(checkBox_border_header);
		checkBox_border_header.setBackground(SystemColor.controlHighlight);

		JCheckBox checkBox_header_round = new JCheckBox("Round");
		checkBox_header_round.setBounds(183, 24, 76, 25);
		checkBox_header_round.setSelected(Utility.YES.equals(headerModel.getIsRound()));
		panel_hold_header_attr.add(checkBox_header_round);
		checkBox_header_round.setBackground(SystemColor.controlHighlight);

		JPanel headerBackgroundColor = new JPanel();
		headerBackgroundColor.setBounds(26, 24, 78, 24);
		panel_hold_header_attr.add(headerBackgroundColor);
		headerBackgroundColor.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		headerBackgroundColor.setBackground(
				Color.decode(Utility.isNullOrEmptyString(headerModel.getBackground()) ? Utility.DEFAULT_COLOR
						: headerModel.getBackground()));

		JButton button_choosecolor_header = new JButton("colors");
		button_choosecolor_header.setBounds(26, 54, 78, 25);
		panel_hold_header_attr.add(button_choosecolor_header);
		button_choosecolor_header.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addingColorAction(headerBackgroundColor, button_choosecolor_header);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBackground(Color.BLACK);
		separator_4.setBounds(12, 13, 2, 81);
		panel_hold_header_attr.add(separator_4);

		JButton btnHeadertitle = new JButton("Title");
		btnHeadertitle.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnHeadertitle.setBounds(112, 54, 147, 25);
		addActionListenerOnBtnHeaderTitle(btnHeadertitle);
		panel_hold_header_attr.add(btnHeadertitle);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(276, 13, 2, 81);
		panel_hold_header_attr.add(separator_2);

		JScrollPane jScrollPaneForHeaderAreaPreview;
		textArea_preview_header_content = new JTextArea();
		textArea_preview_header_content.setEditable(false);
		textArea_preview_header_content.setFont(new Font(headerModel.getTitleFont(),
				Utility.parseStyleStringToFont(headerModel.getTitleStyle()), headerModel.getTitleSize()));
		textArea_preview_header_content.setText(headerModel.getTitle());
		textArea_preview_header_content.setForeground(Color.decode(headerModel.getTitleColor()));
		textArea_preview_header_content.setBackground(new Color(248, 248, 255));
		jScrollPaneForHeaderAreaPreview = new JScrollPane(textArea_preview_header_content);
		jScrollPaneForHeaderAreaPreview
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPaneForHeaderAreaPreview.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPaneForHeaderAreaPreview.setBounds(290, 20, 333, 70);
		jScrollPaneForHeaderAreaPreview.setBorder(new LineBorder(new Color(153, 51, 0), 2, true));
		panel_hold_header_attr.add(jScrollPaneForHeaderAreaPreview);

		JButton saveBtn = new JButton("Save");
		saveBtn.setFont(new Font("Calibri", Font.BOLD, 18));
		saveBtn.setBounds(829, 744, 69, 45);
		panel_background_fotter_color.add(saveBtn);

		comboBoxScreenContents = new JComboBox<ScreenContentModel>();
		comboBoxScreenContents.setBorder(new LineBorder(UIManager.getColor("Button.focus"), 1, true));
		comboBoxScreenContents.setBounds(180, 335, 191, 22);
		addingScreenContentsTocomboBoxScreenContents();
		panel_background_fotter_color.add(comboBoxScreenContents);

		JLabel lblScreenContents = new JLabel("Screen - contents");
		lblScreenContents.setFont(new Font("Calibri", Font.BOLD, 16));
		lblScreenContents.setBounds(12, 335, 142, 24);
		panel_background_fotter_color.add(lblScreenContents);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(196, 420, 702, 2);
		panel_background_fotter_color.add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(196, 733, 702, 2);
		panel_background_fotter_color.add(separator_6);

		JLabel lblAddAuthentication = new JLabel("Authentication");
		lblAddAuthentication.setFont(new Font("Calibri", Font.BOLD, 16));
		lblAddAuthentication.setBounds(12, 754, 142, 24);
		panel_background_fotter_color.add(lblAddAuthentication);

		JPanel panel_add_users_auth = new JPanel();
		panel_add_users_auth.setBackground(UIManager.getColor("CheckBox.light"));
		panel_add_users_auth.setBounds(180, 754, 637, 24);
		panel_background_fotter_color.add(panel_add_users_auth);
		panel_add_users_auth.setLayout(new BorderLayout());

		JButton btnAddUsers = new JButton("Add Users");
		panel_add_users_auth.add(btnAddUsers, BorderLayout.WEST);

		JLabel lblNewName = new JLabel("New - name");
		lblNewName.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewName.setBounds(570, 98, 104, 24);
		panel_background_fotter_color.add(lblNewName);

		textField_newApplicationName = new JTextField();
		textField_newApplicationName.setText((String) null);
		textField_newApplicationName.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_newApplicationName.setColumns(10);
		textField_newApplicationName.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textField_newApplicationName.setBounds(691, 98, 191, 24);
		panel_background_fotter_color.add(textField_newApplicationName);

		textField_new_window_title = new JTextField();
		textField_new_window_title.setText((String) null);
		textField_new_window_title.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_new_window_title.setColumns(10);
		textField_new_window_title.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textField_new_window_title.setBounds(691, 156, 191, 24);
		panel_background_fotter_color.add(textField_new_window_title);

		JLabel lblNewTitle = new JLabel("New - title");
		lblNewTitle.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewTitle.setBounds(570, 156, 104, 24);
		panel_background_fotter_color.add(lblNewTitle);

		JLabel lblNewMenu = new JLabel("New - menu");
		lblNewMenu.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewMenu.setBounds(570, 212, 104, 24);
		panel_background_fotter_color.add(lblNewMenu);

		textField_new_menu_name = new JTextField();
		textField_new_menu_name.setText((String) null);
		textField_new_menu_name.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_new_menu_name.setColumns(10);
		textField_new_menu_name.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textField_new_menu_name.setBounds(691, 212, 191, 24);
		panel_background_fotter_color.add(textField_new_menu_name);

		JLabel lblNewScreen = new JLabel("New - screen");
		lblNewScreen.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewScreen.setBounds(570, 271, 104, 24);
		panel_background_fotter_color.add(lblNewScreen);

		textField_new_screen_name = new JTextField();
		textField_new_screen_name.setText((String) null);
		textField_new_screen_name.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_new_screen_name.setColumns(10);
		textField_new_screen_name.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textField_new_screen_name.setBounds(691, 271, 191, 24);
		panel_background_fotter_color.add(textField_new_screen_name);

		JLabel lblNewContent = new JLabel("New - content");
		lblNewContent.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewContent.setBounds(570, 333, 104, 24);
		panel_background_fotter_color.add(lblNewContent);

		textField_new_content_name = new JTextField();
		textField_new_content_name.setText((String) null);
		textField_new_content_name.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_new_content_name.setColumns(10);
		textField_new_content_name.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textField_new_content_name.setBounds(691, 333, 191, 24);
		panel_background_fotter_color.add(textField_new_content_name);

		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBackground(Color.BLACK);
		separator_7.setBounds(489, 100, 2, 257);
		panel_background_fotter_color.add(separator_7);

		JButton btnSaveForContents = new JButton("Save");
		btnSaveForContents.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSaveForContents.setBounds(813, 370, 69, 45);
		panel_background_fotter_color.add(btnSaveForContents);

		panel_error = new JPanel();
		panel_error.setVisible(false);
		panel_error.setBorder(new MatteBorder(5, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_error.setBackground(new Color(160, 82, 45));
		panel_error.setBounds(492, 38, 390, 32);
		panel_background_fotter_color.add(panel_error);
		panel_error.setLayout(null);

		JButton btnErrorX = new JButton("X");
		btnErrorX.setCursor(new Cursor(Cursor.HAND_CURSOR));
		makeButtonTrans(btnErrorX);
		btnErrorX.setForeground(SystemColor.text);
		btnErrorX.setFont(new Font("Calibri", Font.BOLD, 15));
		btnErrorX.setBounds(361, 5, 29, 19);
		panel_error.add(btnErrorX);

		JLabel lblSomeErrorOccurs = new JLabel("Some Error Occurs, Please consult developer");
		lblSomeErrorOccurs.setForeground(SystemColor.text);
		lblSomeErrorOccurs.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSomeErrorOccurs.setBounds(12, 8, 337, 19);
		panel_error.add(lblSomeErrorOccurs);

		btnErrorX.addActionListener((x) -> {
			panel_error.setVisible(false);
		});

		btnSaveForContents.addActionListener((z) -> {
			String appNewName = textField_newApplicationName.getText().toString();
			String titleNewName = textField_new_window_title.getText().toString();
			String newMenuName = textField_new_menu_name.getText().toString();
			String newScreenName = textField_new_screen_name.getText().toString();
			String newScreenContentName = textField_new_content_name.getText().toString();
			try {
				updateApplicationName(appNewName, titleNewName);
				updateNewMenuName(newMenuName);
				updateScreenName(newScreenName);
				updateScreenContentName(newScreenContentName);
				JOptionPane.showMessageDialog(frame, "All Changes saved successfully !!!");
			} catch (Exception e) {
				e.printStackTrace();
				panel_error.setVisible(true);
			}

		});

		checkBox_header_required.addActionListener((a) -> {
			if (checkBox_header_required.isSelected()) {
				panel_hold_header_attr.setVisible(true);
			} else {
				panel_hold_header_attr.setVisible(false);
			}
		});

		if (checkBox_header_required.isSelected()) {
			panel_hold_header_attr.setVisible(true);
		} else {
			panel_hold_header_attr.setVisible(false);
		}

		if (chckbxRequiredFotter.isSelected()) {
			panel_fotter_settings.setVisible(true);
		} else {
			panel_fotter_settings.setVisible(false);
		}

		chckbxRequiredFotter.addActionListener((c) -> {
			if (chckbxRequiredFotter.isSelected()) {
				panel_fotter_settings.setVisible(true);
			} else {
				panel_fotter_settings.setVisible(false);
			}
		});

		saveBtn.addActionListener((y) -> {
			// header props
			headerModel.setIsActive(checkBox_header_required.isSelected() ? Utility.YES : Utility.NO);
			headerModel.setBackground(headerBackgroundColor.getBackground() == null ? Utility.DEFAULT_COLOR
					: Utility.toHexString(headerBackgroundColor.getBackground()));
			headerModel.setIsBorder(checkBox_border_header.isSelected() ? Utility.YES : Utility.NO);
			headerModel.setIsRound(checkBox_header_round.isSelected() ? Utility.YES : Utility.NO);

			// Footer props
			footerModel.setIsActive(chckbxRequiredFotter.isSelected() ? Utility.YES : Utility.NO);
			footerModel.setIsBorder(chckbxBorderForFooter.isSelected() ? Utility.YES : Utility.NO);
			footerModel.setIsRound(checkBoxRoundCorner_footer.isSelected() ? Utility.YES : Utility.NO);
			footerModel.setIsClock(chckbxClock.isSelected() ? Utility.YES : Utility.NO);
			footerModel.setBackground(footerBackgroundColor.getBackground() == null ? Utility.DEFAULT_COLOR
					: Utility.toHexString(footerBackgroundColor.getBackground()));

			try {
				updateHeader();
				updateFooter();
				JOptionPane.showMessageDialog(frame, "All changes saved successfully!!!");
			} catch (Exception e) {
				e.printStackTrace();
				panel_error.setVisible(true);
			}

		});
		
		
		btnAddUsers.addActionListener((z)->{
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						UserAuthController window = new UserAuthController();
						window.getFrame().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});
	}

	private void updateFooter() {
		try {
			if (Utility.isNull(ContentHelper.getInstance().updateFooter(footerModel))) {
				panel_error.setVisible(true);
			}
		} catch (DBException e) {
			e.printStackTrace();
			panel_error.setVisible(true);
		}

	}

	private void updateHeader() {
		try {
			if (Utility.isNull(ContentHelper.getInstance().updateHeader(headerModel))) {
				panel_error.setVisible(true);
			}
		} catch (DBException e) {
			e.printStackTrace();
			panel_error.setVisible(true);
		}
	}

	private void addActionListenerOnBtnHeaderTitle(JButton btnHeadertitle) {
		btnHeadertitle.addActionListener((f) -> {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ContentFrame windowForContent = new ContentFrame(AdminController.this);
						windowForContent.initialize();
						windowForContent.setConsumerType(ContentConsumerType.header);
						windowForContent.getFrame().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});

	}

	private void addingActionListenerOnBtnContentFooter(JButton btnContentFooter) {
		btnContentFooter.addActionListener((i) -> {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ContentFrame windowForContent = new ContentFrame(AdminController.this);
						windowForContent.initialize();
						windowForContent.setConsumerType(ContentConsumerType.footer);
						windowForContent.getFrame().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});

	}

	private void updateScreenContentName(String newScreenContentName) {
		if (!Utility.isNullOrEmptyString(newScreenContentName)) {
			if (comboBoxScreenContents.getItemCount() != 0) {
				ScreenContentModel screenContentModel = (ScreenContentModel) comboBoxScreenContents.getSelectedItem();
				screenContentModel.setTitle(newScreenContentName);
				try {
					if (AppUtils.isNull(ContentHelper.getInstance().updateScreenContent(screenContentModel))) {
						panel_error.setVisible(true);
					} else {
						ScreenModel screenModel = (ScreenModel) comboBox_screen_title.getSelectedItem();
						ContentHelper.getInstance().refreshScreentContents(screenModel.getId());
						addingScreenContentsTocomboBoxScreenContents();
					}
				} catch (DBException e) {
					e.printStackTrace();
					panel_error.setVisible(true);
				}
			}
		}

	}

	private void addingListenerOnCheckBoxMenuTitle() {
		comboBox_menus.addItemListener((e) -> {
			MenuModel model = (MenuModel) e.getItem();
			try {
				ContentHelper.getInstance().refreshScreens(model.getId());
				addingScreensToScreentitleComboBox();
			} catch (DBException e1) {
				e1.printStackTrace();
				panel_error.setVisible(true);
			}
		});
	}

	private void addingListenerOnCheckBoxScreenTitle() {
		comboBox_screen_title.addItemListener((e) -> {
			ScreenModel model = (ScreenModel) e.getItem();
			try {
				ContentHelper.getInstance().refreshScreentContents(model.getId());
				addingScreenContentsTocomboBoxScreenContents();
			} catch (DBException e1) {
				e1.printStackTrace();
				panel_error.setVisible(true);
			}
		});
	}

	private void updateScreenName(String newScreenName) {
		if (!Utility.isNullOrEmptyString(newScreenName)) {
			if (comboBox_screen_title.getItemCount() != 0) {
				ScreenModel screenModel = (ScreenModel) comboBox_screen_title.getSelectedItem();
				screenModel.setTitle(newScreenName);
				try {
					if (Utility.isNull(ContentHelper.getInstance().updateScreen(screenModel))) {
						panel_error.setVisible(true);
					} else {
						MenuModel menu = (MenuModel) comboBox_menus.getSelectedItem();
						ContentHelper.getInstance().refreshScreens(menu.getId());
						addingScreensToScreentitleComboBox();
					}
				} catch (DBException e) {
					e.printStackTrace();
					panel_error.setVisible(true);
				}
			}
		}

	}

	private void updateNewMenuName(String newMenuName) {
		if (!Utility.isNullOrEmptyString(newMenuName)) {
			if (comboBox_menus.getItemCount() != 0) {
				MenuModel menu = (MenuModel) comboBox_menus.getSelectedItem();
				menu.setTitle(newMenuName);
				try {
					if (ContentHelper.getInstance().updateMenu(menu) == 0) {
						panel_error.setVisible(true);
					} else {
						ContentHelper.getInstance().refreshMenus();
						addingMenusToComboBox_Menus();
					}
				} catch (DBException e) {
					e.printStackTrace();
					panel_error.setVisible(true);
				}
			}
		}
	}

	private void updateApplicationName(String appNewName, String titleNewName) {
		if (!Utility.isNullOrEmptyString(appNewName)) {
			applicationModel.setApp_name(appNewName);
			if (!Utility.isNullOrEmptyString(appNewName))
				applicationModel.setWindow_name(titleNewName);
			try {
				if (ContentHelper.getInstance().updateApplication(applicationModel) == 0) {
					panel_error.setVisible(true);
				} else {
					textField_app_name.setText(appNewName);
					textField_window.setText(titleNewName);
				}
			} catch (DBException e) {
				e.printStackTrace();
				panel_error.setVisible(true);
			}
		}
	}

	private void addingScreensToScreentitleComboBox() {
		if (comboBox_screen_title.getItemCount() > 0)
			comboBox_screen_title.removeAllItems();
		screens = ContentHelper.getInstance().getScreens();
		if (!Objects.isNull(screens) && !screens.isEmpty()) {
			Set<String> keys = screens.keySet();
			comboBox_screen_title.setRenderer(new ItemRendererForScreen());
			for (String key : keys) {
				comboBox_screen_title.addItem(screens.get(key));
			}
		} else {
			if (comboBoxScreenContents.getItemCount() > 0)
				comboBoxScreenContents.removeAllItems();
		}
	}

	private void addingMenusToComboBox_Menus() {
		if (comboBox_menus.getItemCount() > 0)
			comboBox_menus.removeAllItems();
		menus = ContentHelper.getInstance().getMenus();
		if (!Objects.isNull(menus) && !menus.isEmpty()) {
			Set<String> keys = menus.keySet();
			comboBox_menus.setRenderer(new ItemRendererForMenu());
			for (String key : keys) {
				comboBox_menus.addItem(menus.get(key));
			}
		} else {
			if (comboBox_screen_title.getItemCount() > 0)
				comboBox_screen_title.removeAllItems();
		}
	}

	private void addingScreenContentsTocomboBoxScreenContents() {
		if (comboBoxScreenContents.getItemCount() > 0)
			comboBoxScreenContents.removeAllItems();
		screenContents = ContentHelper.getInstance().getScreenContentModel();
		if (!AppUtils.isNull(screenContents) && !screenContents.isEmpty()) {
			Set<String> keys = screenContents.keySet();
			comboBoxScreenContents.setRenderer(new ItemRendererForScreenContents());
			for (String key : keys) {
				comboBoxScreenContents.addItem(screenContents.get(key));
			}
		}
	}

	private void addingColorAction(JPanel colorSamplePanel, JButton btnChooseColors) {
		Color color = Color.decode(Utility.DEFAULT_COLOR);
		btnChooseColors.addActionListener((x) -> {
			Color newColor = JColorChooser.showDialog(frame, "Choose", color);
			if (!AppUtils.isNull(newColor)) {
				colorSamplePanel.setBackground(newColor);
			}
		});
	}

	private void makeButtonTrans(JButton productsMenu) {
		productsMenu.setBorder(null);
		productsMenu.setOpaque(true);
		productsMenu.setFocusable(false);
		productsMenu.setContentAreaFilled(false);
		productsMenu.setBorderPainted(false);
	}

	class ItemRendererForMenu extends BasicComboBoxRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1616966464656301615L;

		public Component getListCellRendererComponent(JList<Object> list, MenuModel value, int index,
				boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				setText(value.getTitle());
			}
			if (index == -1) {
				setText(value.getTitle());
			}
			return this;
		}
	}

	class ItemRendererForScreen extends BasicComboBoxRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1616966464656301615L;

		public Component getListCellRendererComponent(JList<Object> list, ScreenModel value, int index,
				boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				setText(value.getTitle());
			}
			if (index == -1) {
				setText(value.getTitle());
			}
			return this;
		}
	}

	class ItemRendererForScreenContents extends BasicComboBoxRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1616966464656301615L;

		public Component getListCellRendererComponent(JList<Object> list, ScreenContentModel value, int index,
				boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				setText(value.getTitle());
			}
			if (index == -1) {
				setText(value.getTitle());
			}
			return this;
		}
	}

	@Override
	public void observeForFotter(FooterModel footerModel) {
		textArea_preview_content_footer.setText(footerModel.getContent());
		textArea_preview_content_footer.setFont(new Font(footerModel.getContentTextFont(),
				Utility.parseStyleStringToFont(footerModel.getContentTextStyle()), footerModel.getContentTextSize()));
		textArea_preview_content_footer.setForeground(Color.decode(footerModel.getContentTextColor()));

		this.footerModel.setContent(footerModel.getContent());
		this.footerModel.setContentTextColor(footerModel.getContentTextColor());
		this.footerModel.setContentTextFont(footerModel.getContentTextFont());
		this.footerModel.setContentTextSize(footerModel.getContentTextSize());
		this.footerModel.setContentTextStyle(footerModel.getContentTextStyle());

	}

	@Override
	public void observeForHeader(HeaderModel headerModel) {
		textArea_preview_header_content.setText(headerModel.getTitle());
		textArea_preview_header_content.setFont(new Font(headerModel.getTitleFont(),
				Utility.parseStyleStringToFont(headerModel.getTitleStyle()), headerModel.getTitleSize()));
		textArea_preview_header_content.setForeground(Color.decode(headerModel.getTitleColor()));

		this.headerModel.setTitle(headerModel.getTitle());
		this.headerModel.setTitleColor(headerModel.getTitleColor());
		this.headerModel.setTitleFont(headerModel.getTitleFont());
		this.headerModel.setTitleStyle(headerModel.getTitleStyle());
		this.headerModel.setTitleSize(headerModel.getTitleSize());

	}
}
