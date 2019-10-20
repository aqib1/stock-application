package com.client.stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.client.stock.loginObserver.ILoginObserver;
import com.db.Exception.DBException;
import com.db.models.SaleModel;
import com.db.models.app.contents.ApplicationModel;
import com.db.models.app.contents.FooterModel;
import com.db.models.app.contents.HeaderModel;
import com.db.models.client.ClientModel;
import com.db.models.menus.MenuModel;
import com.db.models.menus.MenuTypes;
import com.db.models.product.ProductModel;
import com.db.models.screencontents.ScreenContentModel;
import com.db.models.screencontents.ScreenContentTypes;
import com.db.models.screens.ScreenModel;
import com.db.models.screens.ScreenTypes;
import com.db.models.userclientrelationlogsmodel.UserClientRelationLogsModel;
import com.db.models.userprodrelation.UserProdRelationModel;
import com.db.utility.AppUtils;
import com.stock.content.ContentHelper;
import com.stock.content.utility.Utility;

public class StockClient implements ILoginObserver {

	private JFrame frame;
	private JPanel panel_add_edit_products;
	private JTextField textField_prodName;
	private JTextField textField_prod_quantity;
	private JTextField textField_bar_code;
	private JTextField textField_price;
	private JTextField textField_tax;
	private JTextField textField_product_company;
	private JTextField textField_supplier_name;
	private JTextField textField_supplier_contact;
	private JTextField textField_supplier_account;
	private JTextField textField_paid_blnc;
	private JTextField textField_net_balance;
	private HeaderModel headerModel;
	private ApplicationModel applicationModel;
	private FooterModel footerModel;
	private JPanel panel_main;
	private Map<String, MenuModel> menus;
	private Map<String, ScreenModel> menu_add_prod_screen;
	private Map<String, ScreenModel> menu_view_prod_screen;
	private Map<String, ScreenModel> menu_view_client_stats;
	private Map<String, ScreenModel> menu_client_add_screen;
	private Map<String, ScreenModel> menu_sale_add_sale;
	private Map<String, ScreenModel> menu_sale_sale_stats;
	private Map<String, ScreenModel> menu_profit_stat_screen;
	private Map<String, ScreenContentModel> menu_add_prod_add_prod_screen_content;
	private Map<String, ScreenContentModel> menu_add_prod_suplir_info_screen_content;
	private Map<String, ScreenContentModel> menu_add_prod_paymnt_info_screen_content;
	private Map<String, ScreenContentModel> menu_client_add_screen_screen_content;
	private Map<String, ScreenContentModel> menu_sale_add_sale_slct_prod_content;
	private Map<String, ScreenContentModel> menu_sale_add_sale_facts_sale_content;
	private Map<String, ScreenContentModel> menu_sale_select_client_content;
	private JTextField textField_prod_name_edit;
	private JTextField textField_prod_quantity_edit;
	private JTextField textField_bar_code_edit;
	private JTextField textField_price_edit;
	private JTextField textField_tax_edit;
	private JTextField textField_EditProductCompany;
	private JTextField textField_Sup_edit;
	private JTextField textField_SupplierContact_edit;
	private JTextField textField_SupplierAccount_edit;
	private JTextField textField_net_balance_edit;
	private JTextField textField_paid_blnc_edit;
	private JComboBox<String> comboBox_payment_methods_1;
	private JComboBox<String> comboBox_paymentMethod_edit;
	private JComboBox<ProductModel> combobox_prod;
	private JPanel panel_product_screen;
	private JTable table;
	private DefaultTableModel columnModel;
	private JButton btnNext_prods;
	private JButton btnPrevious_prods;
	private JTextField textField_client_name;
	private JTextField textField_clientNumber;
	private JTextField textField_clientEmail;
	private JTextField textField_client_age;
	private JTextField textField_client_gender;
	private JTextField textField_client_account;
	private JTextField textField_client_country;
	private JTextField textField_client_district;
	private JTextField textField_client_state;
	private JTextField textField_client_address;
	private JTextField textField_client_referral;
	private JTextField textField_referral_address;
	private JPanel panel_edit_add_client_screen;
	private JDatePickerImpl datePicker;
	private JTextField textField_cleint_name_edit;
	private JTextField textField_edit_client_no;
	private JTextField textField_edit_client_email;
	private JTextField textField_edit_client_age;
	private JTextField textField_client_edit_gender;
	private JTextField textField_client_edit_acc_no;
	private JTextField textField_client_edit_country;
	private JTextField textField_client_edit_district;
	private JTextField textField_client_edit_addr;
	private JTextField textField_client_edit_state;
	private JTextField textField_client_edit_refferal_name;
	private JTextField textField_client_edit_refferal_addr;
	private JDatePickerImpl client_datePickerEdit;
	private JComboBox<ClientModel> comboBox_select_client;
	private JPanel panel_client_screen;
	private JTable tableForClient;
	private DefaultTableModel columnModelForClient;
	private JButton btnNext_client;
	private JButton btnPrevious_client;
	private JPanel panel_add_sale_screen;
	private JComboBox<ProductModel> comboBox_choose_prod_add_sale;
	private JTextField searchForProd;
	private JLabel productName_sale_screen;
	private JLabel labelProductPrice;
	private JLabel labelProductTax;
	private JLabel labelAvailableQuantity_sale_screen;
	private JTextField textField_selectClient;
	private JComboBox<ClientModel> comboBox_choose_client;
	private JLabel label_clientName_show;
	private JLabel label_contact_no_show;
	private JLabel label_clientEmail_show;
	private JLabel label_client_account_no_show;
	private JLabel label_client_address_show;
	private JLabel label_client_date;
	private JLabel label_client_reference_name;
	private JLabel label_client_ref_address_show;
	private JTextField textField_saller_name;
	private JTextField textFiled_Sold_price;
	private JTextField txtQuantitynumericValue;
	private JTable tableForSale;
	private DefaultTableModel columnModelForSale;
	private JPanel panel_sale;
	private JButton btnNext_sale;
	private JButton btnPrevious_sale;
	private JLabel label_ShowTotalPriceWithQuant;
	private JPanel panel_profit;
	private JTable tableForWeeklyProfit;
	private DefaultTableModel columnModelForWeeklyProfit;
	private JButton btnNextWeeklyProfit;
	private JButton btnPreviousWeeklyProfit;
	private JTable tableForMonthlyProfit;
	private DefaultTableModel columnModelForMonthlyProfit;
	private JButton button_monthly_stats_prev;
	private JButton button_monthly_stat_next;
	private JPanel panel_report;
	private DefaultCategoryDataset dataset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockClient stockClient = new StockClient();
					LoginPanel window = new LoginPanel(stockClient);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public StockClient() {

	}

	private void intializeData() {
		headerModel = ContentHelper.getInstance().getHeaderModel();
		applicationModel = ContentHelper.getInstance().getApplicationModel();
		footerModel = ContentHelper.getInstance().getFooterModel();
		menus = ContentHelper.getInstance().getMenus();
		try {
			menu_add_prod_screen = ContentHelper.getInstance()
					.getScreensByMenuId(menus.get(MenuTypes.adprodcts_01.toString()).getId());
			menu_view_prod_screen = ContentHelper.getInstance()
					.getScreensByMenuId(menus.get(MenuTypes.prod_01.toString()).getId());
			menu_client_add_screen = ContentHelper.getInstance()
					.getScreensByMenuId(menus.get(MenuTypes.adclnts_01.toString()).getId());
			menu_view_client_stats = ContentHelper.getInstance()
					.getScreensByMenuId(menus.get(MenuTypes.clnts_01.toString()).getId());
			menu_sale_add_sale = ContentHelper.getInstance()
					.getScreensByMenuId(menus.get(MenuTypes.add_sale_01.toString()).getId());
			menu_sale_sale_stats = ContentHelper.getInstance()
					.getScreensByMenuId(menus.get(MenuTypes.sales_01.toString()).getId());
			menu_profit_stat_screen = ContentHelper.getInstance()
					.getScreensByMenuId(menus.get(MenuTypes.prft_01.toString()).getId());

			menu_add_prod_add_prod_screen_content = ContentHelper.getInstance().getScreenContentModelByScreenId(
					menu_add_prod_screen.get(ScreenTypes.prod_add_product.toString()).getId());
			menu_add_prod_suplir_info_screen_content = ContentHelper.getInstance().getScreenContentModelByScreenId(
					menu_add_prod_screen.get(ScreenTypes.prod_supplier_info.toString()).getId());
			menu_add_prod_paymnt_info_screen_content = ContentHelper.getInstance().getScreenContentModelByScreenId(
					menu_add_prod_screen.get(ScreenTypes.prod_payment_info.toString()).getId());
			menu_client_add_screen_screen_content = ContentHelper.getInstance().getScreenContentModelByScreenId(
					menu_client_add_screen.get(ScreenTypes.clients_add_edit.toString()).getId());
			menu_sale_add_sale_slct_prod_content = ContentHelper.getInstance().getScreenContentModelByScreenId(
					menu_sale_add_sale.get(ScreenTypes.sale_screen_select_prod.toString()).getId());
			menu_sale_add_sale_facts_sale_content = ContentHelper.getInstance().getScreenContentModelByScreenId(
					menu_sale_add_sale.get(ScreenTypes.sale_screen_sale_facts.toString()).getId());
			menu_sale_select_client_content = ContentHelper.getInstance().getScreenContentModelByScreenId(
					menu_sale_add_sale.get(ScreenTypes.sale_screen_slct_client.toString()).getId());
		} catch (DBException | IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */

	private void initialize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		frame = new JFrame(applicationModel.getWindow_name());
		frame.setSize(toolkit.getScreenSize());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel_main = new JPanel();
		JScrollPane jsp = new JScrollPane(panel_main);
		panel_main.setPreferredSize(new Dimension(1920,1080));
		frame.getContentPane().add(jsp, BorderLayout.CENTER);
		panel_main.setLayout(null);

		JPanel panel_header = new JPanel();
		panel_header.setBackground(UIManager.getColor("Button.light"));
		panel_header.setBounds(0, 0, (int) frame.getSize().getWidth(), 70);
		panel_main.add(panel_header);
		panel_header.setLayout(null);

		JLabel application_name = new JLabel();
		application_name.setHorizontalAlignment(SwingConstants.LEADING);
		application_name.setText(applicationModel.getApp_name());
		application_name.setFont(new Font("Calibri", Font.BOLD, 39));
		application_name.setBounds(20, 0, 665, 70);
		panel_header.add(application_name);

		JTextArea txtrHeaderdetails = new JTextArea();
		txtrHeaderdetails.setBackground(Color.decode(headerModel.getBackground()));
		txtrHeaderdetails.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtrHeaderdetails.setText(headerModel.getTitle());
		txtrHeaderdetails.setLineWrap(true);
		txtrHeaderdetails.setFont(new Font(headerModel.getTitleFont(),
				Utility.parseStyleStringToFont(headerModel.getTitleStyle()), headerModel.getTitleSize()));
		txtrHeaderdetails.setForeground(Color.decode(headerModel.getTitleColor()));
		txtrHeaderdetails.setEditable(false);
		txtrHeaderdetails.setBounds(1189, 5, 719, 59);
		panel_header.add(txtrHeaderdetails);
		setVisibilityForHeader(txtrHeaderdetails);
		setBorderForHeader(txtrHeaderdetails);

		JSeparator separator = new JSeparator();
		separator.setBackground(UIManager.getColor("CheckBox.focus"));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(233, 83, 10, 879);
		panel_main.add(separator);

		JLabel lblMainMenu = new JLabel("Main - Menu");
		lblMainMenu.setFont(new Font("Calibri", Font.BOLD, 24));
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setBounds(10, 83, 211, 36);
		panel_main.add(lblMainMenu);

		JButton btnProductsMenu = new JButton(menus.get(MenuTypes.prod_01.toString()).getTitle());
		makeButtonTrans(btnProductsMenu);
		btnProductsMenu.setFont(new Font("Calibri", Font.BOLD, 19));
		btnProductsMenu.setHorizontalAlignment(SwingConstants.CENTER);
		btnProductsMenu.setBounds(56, 169, 112, 23);
		btnProductsMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_main.add(btnProductsMenu);

		JPanel panel_fotter = new JPanel();
		panel_fotter.setBackground(UIManager.getColor("Button.light"));
		panel_fotter.setBounds(0, 975, (int) frame.getSize().getWidth(), 70);
		panel_main.add(panel_fotter);
		panel_fotter.setLayout(null);

		JTextArea fotterText = new JTextArea();
		fotterText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		fotterText.setBounds(1189, 7, 707, 57);
		fotterText.setEditable(false);
		fotterText.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		fotterText.setLineWrap(true);
		fotterText.setFont(new Font(footerModel.getContentTextFont(),
				Utility.parseStyleStringToFont(footerModel.getContentTextStyle()), footerModel.getContentTextSize()));
		fotterText.setText(" " + footerModel.getContent() + " ");
		fotterText.setForeground(Color.decode(footerModel.getContentTextColor()));
		fotterText.setBackground(Color.decode(footerModel.getBackground()));
		setVisibilityForFotter(fotterText);
		setBorderForFotter(fotterText);
		panel_fotter.add(fotterText);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 132, 190, 2);
		panel_main.add(separator_1);

		JButton btnClientMenu = new JButton(menus.get(MenuTypes.clnts_01.toString()).getTitle());
		makeButtonTrans(btnClientMenu);
		btnClientMenu.setHorizontalAlignment(SwingConstants.CENTER);
		btnClientMenu.setFont(new Font("Calibri", Font.BOLD, 19));
		btnClientMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnClientMenu.setBounds(56, 217, 112, 23);
		panel_main.add(btnClientMenu);

		JButton btnSalesMenu = new JButton(menus.get(MenuTypes.sales_01.toString()).getTitle());
		makeButtonTrans(btnSalesMenu);
		btnSalesMenu.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalesMenu.setFont(new Font("Calibri", Font.BOLD, 19));
		btnSalesMenu.setBounds(56, 268, 112, 23);
		btnSalesMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_main.add(btnSalesMenu);

		JButton btnProfitMenu = new JButton(menus.get(MenuTypes.prft_01.toString()).getTitle());
		makeButtonTrans(btnProfitMenu);
		btnProfitMenu.setHorizontalAlignment(SwingConstants.CENTER);
		btnProfitMenu.setFont(new Font("Calibri", Font.BOLD, 19));
		btnProfitMenu.setBounds(56, 324, 112, 23);
		btnProfitMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_main.add(btnProfitMenu);

		JButton btnReportingMenu = new JButton(menus.get(MenuTypes.rprtng_01.toString()).getTitle());
		makeButtonTrans(btnReportingMenu);
		btnReportingMenu.setHorizontalAlignment(SwingConstants.CENTER);
		btnReportingMenu.setFont(new Font("Calibri", Font.BOLD, 19));
		btnReportingMenu.setBounds(56, 372, 112, 23);
		btnReportingMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_main.add(btnReportingMenu);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 487, 190, 2);
		panel_main.add(separator_2);

		JButton btnAddProductsMenu = new JButton(menus.get(MenuTypes.adprodcts_01.toString()).getTitle());
		makeButtonTrans(btnAddProductsMenu);
		btnAddProductsMenu.setHorizontalAlignment(SwingConstants.CENTER);
		btnAddProductsMenu.setFont(new Font("Calibri", Font.BOLD, 19));
		btnAddProductsMenu.setBounds(56, 527, 112, 23);
		btnAddProductsMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_main.add(btnAddProductsMenu);

		JButton btnAddClientsMenu = new JButton(menus.get(MenuTypes.adclnts_01.toString()).getTitle());
		makeButtonTrans(btnAddClientsMenu);
		btnAddClientsMenu.setHorizontalAlignment(SwingConstants.CENTER);
		btnAddClientsMenu.setFont(new Font("Calibri", Font.BOLD, 19));
		btnAddClientsMenu.setBounds(56, 583, 112, 23);
		btnAddClientsMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_main.add(btnAddClientsMenu);

		JButton btnAddSale = new JButton(menus.get(MenuTypes.add_sale_01.toString()).getTitle());
		makeButtonTrans(btnAddSale);
		btnAddSale.setHorizontalAlignment(SwingConstants.CENTER);
		btnAddSale.setFont(new Font("Calibri", Font.BOLD, 19));
		btnAddSale.setBounds(56, 638, 112, 23);
		btnAddSale.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_main.add(btnAddSale);

		// TODO: FOR SCREENS
		addingAddSaleScreen();
		addingEditProductScreenToMainScreen();
		addingProductScreenToMainScreen();
		addReportingScreen();
		addingProfitScreen();
		addingSaleLogsScreen();
		addingEditClientScreenToMainScreen();
		addingClientsLogsScreen();

		btnReportingMenu.addActionListener(r -> {
			addingReportingScreen();
		});

		btnProfitMenu.addActionListener(p -> {
			addProfitStatsScreen();
		});

		btnSalesMenu.addActionListener((s) -> {
			addSaleStatScreen();
		});

		btnAddSale.addActionListener((a) -> {
			addSaleScreen();
		});

		btnClientMenu.addActionListener((t) -> {
			addingClientLogsScreen();
		});

		btnAddClientsMenu.addActionListener(t -> {
			addingClientScreenPanel();
		});

		btnAddProductsMenu.addActionListener((a) -> {
			addingAddProdPanel();
		});

		btnProductsMenu.addActionListener((p) -> {
			showProductScreen();
		});

		frame.setVisible(true);
	}

	private void addingReportingScreen() {
		panel_report.setVisible(true);
		panel_add_edit_products.setVisible(false);
		panel_product_screen.setVisible(false);
		panel_edit_add_client_screen.setVisible(false);
		panel_client_screen.setVisible(false);
		panel_add_sale_screen.setVisible(false);
		panel_sale.setVisible(false);
		panel_profit.setVisible(false);
		createDataReportBar();

	}

	private void addReportingScreen() {
		panel_report = new JPanel();
		panel_report.setVisible(false);
		panel_report.setBounds(243, 83, 1659, 865);
		panel_main.add(panel_report);
		panel_report.setLayout(new BorderLayout(0, 0));
		dataset = new DefaultCategoryDataset();
		JFreeChart chart = ChartFactory.createBarChart("Monthly Profit Report", "", "Profit stats of latest records",
				dataset, PlotOrientation.VERTICAL, false, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		panel_report.add(chartPanel);

	}

	private void createDataReportBar() {
		List<SaleModel> data;
		try {
			dataset.clear();
			data = ContentHelper.getInstance().getAllSalePagginationForDays(1, AppUtils.monthDates());
			if (!AppUtils.isNullOrEmptyList(data)) {
				for (SaleModel model : data) {
					dataset.setValue(model.getProfit(), "Product",
							model.getId() + "" + model.getProductModel().getProductName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addProfitStatsScreen() {
		panel_profit.setVisible(true);
		panel_add_edit_products.setVisible(false);
		panel_product_screen.setVisible(false);
		panel_edit_add_client_screen.setVisible(false);
		panel_client_screen.setVisible(false);
		panel_add_sale_screen.setVisible(false);
		panel_sale.setVisible(false);
		panel_report.setVisible(false);
		settingdataToSaleTableWeekly(1);
		settingdataToSaleTableMonthly(1);
	}

	private void addingProfitScreen() {
		panel_profit = new JPanel();
		panel_profit.setVisible(false);
		panel_profit.setBounds(243, 83, 1659, 865);
		panel_main.add(panel_profit);
		panel_profit.setLayout(null);
		JLabel labelProfitWeekly = new JLabel(
				menu_profit_stat_screen.get(ScreenTypes.profit_screen_weekly_profit.toString()).getTitle());
		labelProfitWeekly.setBounds(12, 13, 379, 65);
		labelProfitWeekly.setFont(new Font("Calibri", Font.BOLD, 37));
		panel_profit.add(labelProfitWeekly);

		tableForWeeklyProfit = new JTable();
		columnModelForWeeklyProfit = new DefaultTableModel();
		columnModelForWeeklyProfit.setColumnIdentifiers(AppUtils.SALE_WEEKLY_PROFIT_STATS);
		tableForWeeklyProfit.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableForWeeklyProfit.setFillsViewportHeight(true);
		tableForWeeklyProfit.setModel(columnModelForWeeklyProfit);
		JScrollPane jScrollPane = new JScrollPane(tableForWeeklyProfit);
		jScrollPane.setBounds(22, 91, 650, 718);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_profit.add(jScrollPane);

		tableForMonthlyProfit = new JTable();
		columnModelForMonthlyProfit = new DefaultTableModel();
		columnModelForMonthlyProfit.setColumnIdentifiers(AppUtils.SALE_WEEKLY_PROFIT_STATS);
		tableForMonthlyProfit.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableForMonthlyProfit.setFillsViewportHeight(true);
		tableForMonthlyProfit.setModel(columnModelForMonthlyProfit);
		JScrollPane jScrollPane1 = new JScrollPane(tableForMonthlyProfit);
		jScrollPane1.setBounds(952, 91, 650, 718);
		jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_profit.add(jScrollPane1);

		btnNextWeeklyProfit = new JButton("Next");
		btnNextWeeklyProfit.setBounds(570, 822, 97, 25);
		btnNextWeeklyProfit.setName("1");
		panel_profit.add(btnNextWeeklyProfit);

		btnPreviousWeeklyProfit = new JButton("Previous");
		btnPreviousWeeklyProfit.setName((Integer.parseInt(btnNextWeeklyProfit.getName()) - 1) + "");
		btnPreviousWeeklyProfit.setBounds(461, 822, 97, 25);
		panel_profit.add(btnPreviousWeeklyProfit);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(820, 131, 2, 553);
		panel_profit.add(separator);

		JLabel labelProfitReportMonthly = new JLabel(
				menu_profit_stat_screen.get(ScreenTypes.profit_screen_monthly_profit.toString()).getTitle());
		labelProfitReportMonthly.setFont(new Font("Calibri", Font.BOLD, 37));
		labelProfitReportMonthly.setBounds(952, 13, 379, 65);
		panel_profit.add(labelProfitReportMonthly);

		button_monthly_stat_next = new JButton("Next");
		button_monthly_stat_next.setName("1");
		button_monthly_stat_next.setBounds(1505, 822, 97, 25);
		panel_profit.add(button_monthly_stat_next);

		button_monthly_stats_prev = new JButton("Previous");
		button_monthly_stats_prev.setName((Integer.parseInt(button_monthly_stat_next.getName()) - 1) + "");
		button_monthly_stats_prev.setBounds(1396, 822, 97, 25);
		panel_profit.add(button_monthly_stats_prev);

		button_monthly_stats_prev.addActionListener(i -> {
			int page = Integer.parseInt(button_monthly_stats_prev.getName() + "");
			--page;
			if (page >= 1) {
				settingdataToSaleTableMonthly(page);
				button_monthly_stats_prev.setName(page + "");
				button_monthly_stat_next.setName(page + "");
			} else {
				JOptionPane.showMessageDialog(frame, "This is first sale-monthly-logs page, there is no previous data",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		button_monthly_stat_next.addActionListener(i -> {
			int page = Integer.parseInt(button_monthly_stat_next.getName() + "");
			++page;
			settingdataToSaleTableMonthly(page);
		});

		btnPreviousWeeklyProfit.addActionListener(i -> {
			int page = Integer.parseInt(btnPreviousWeeklyProfit.getName() + "");
			--page;
			if (page >= 1) {
				settingdataToSaleTableWeekly(page);
				btnPreviousWeeklyProfit.setName(page + "");
				btnNextWeeklyProfit.setName(page + "");
			} else {
				JOptionPane.showMessageDialog(frame, "This is first sale-weekly-logs page, there is no previous data",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnNextWeeklyProfit.addActionListener(i -> {
			int page = Integer.parseInt(btnNextWeeklyProfit.getName() + "");
			++page;
			settingdataToSaleTableWeekly(page);
		});

	}

	private void addSaleStatScreen() {
		panel_sale.setVisible(true);
		panel_add_edit_products.setVisible(false);
		panel_product_screen.setVisible(false);
		panel_edit_add_client_screen.setVisible(false);
		panel_client_screen.setVisible(false);
		panel_add_sale_screen.setVisible(false);
		panel_profit.setVisible(false);
		panel_report.setVisible(false);
		settingdataToSaleTable(1);
	}

	private void addingSaleLogsScreen() {
		panel_sale = new JPanel();
		panel_sale.setVisible(false);
		panel_sale.setBounds(243, 83, 1659, 865);
		panel_main.add(panel_sale);
		panel_sale.setLayout(null);

		JLabel lblClientsLogs = new JLabel(
				menu_sale_sale_stats.get(ScreenTypes.sale_stats_sale_stat.toString()).getTitle());
		lblClientsLogs.setBounds(12, 13, 379, 65);
		lblClientsLogs.setFont(new Font("Calibri", Font.BOLD, 37));
		panel_sale.add(lblClientsLogs);

		tableForSale = new JTable();
		columnModelForSale = new DefaultTableModel();
		columnModelForSale.setColumnIdentifiers(AppUtils.SALE_STAT_TABLE_HEADERS);
		tableForSale.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableForSale.setFillsViewportHeight(true);
		tableForSale.setModel(columnModelForSale);
		JScrollPane jScrollPane = new JScrollPane(tableForSale);
		jScrollPane.setBounds(22, 91, 1620, 718);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_sale.add(jScrollPane);

		btnNext_sale = new JButton("Next");
		btnNext_sale.setBounds(1545, 816, 97, 25);
		btnNext_sale.setName("1");
		panel_sale.add(btnNext_sale);

		btnPrevious_sale = new JButton("Previous");
		btnPrevious_sale.setBounds(1436, 816, 97, 25);
		btnPrevious_sale.setName((Integer.parseInt(btnNext_sale.getName()) - 1) + "");
		panel_sale.add(btnPrevious_sale);

		JButton btnClearClientsLogs = new JButton("Clear logs");
		btnClearClientsLogs.setBounds(1327, 816, 97, 25);
		panel_sale.add(btnClearClientsLogs);

		btnPrevious_sale.addActionListener(i -> {
			int page = Integer.parseInt(btnPrevious_sale.getName() + "");
			--page;
			if (page >= 1) {
				settingdataToSaleTable(page);
				btnPrevious_sale.setName(page + "");
				btnNext_sale.setName(page + "");
			} else {
				JOptionPane.showMessageDialog(frame, "This is first sale-logs page, there is no previous data",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnNext_sale.addActionListener(i -> {
			int page = Integer.parseInt(btnNext_sale.getName() + "");
			++page;
			settingdataToSaleTable(page);
		});

		btnClearClientsLogs.addActionListener(t -> {
			clearAllSaleLogs();
		});

	}

	private void addingAddSaleScreen() {
		panel_add_sale_screen = new JPanel();
		panel_add_sale_screen.setVisible(false);
		panel_add_sale_screen.setBounds(243, 83, 1659, 865);
		panel_main.add(panel_add_sale_screen);
		panel_add_sale_screen.setLayout(null);

		JLabel lblSelectProduct = new JLabel(
				menu_sale_add_sale.get(ScreenTypes.sale_screen_select_prod.toString()).getTitle());
		lblSelectProduct.setForeground(new Color(0, 0, 139));
		lblSelectProduct.setFont(new Font("Calibri", Font.BOLD, 35));
		lblSelectProduct.setBounds(12, 13, 405, 51);
		panel_add_sale_screen.add(lblSelectProduct);

		JLabel lblEnterProduct = new JLabel(menu_sale_add_sale_slct_prod_content
				.get(ScreenContentTypes.sale_screen_select_prod_choose_prod.toString()).getTitle());
		lblEnterProduct.setFont(new Font("Calibri", Font.BOLD, 26));
		lblEnterProduct.setBounds(35, 77, 208, 43);
		panel_add_sale_screen.add(lblEnterProduct);

		JPanel searchPanelForProd = new JPanel();
		searchForProd = new JTextField();
		searchForProd.setFont(new Font("Calibri", Font.PLAIN, 23));
		searchForProd.setBackground(UIManager.getColor("CheckBox.light"));
		searchForProd.setBorder(new LineBorder(UIManager.getColor("CheckBox.darkShadow")));
		comboBox_choose_prod_add_sale = new JComboBox<>();
		comboBox_choose_prod_add_sale.setFont(new Font("Calibri", Font.PLAIN, 23));
		comboBox_choose_prod_add_sale.setBorder(null);
		comboBox_choose_prod_add_sale.setOpaque(false);
		comboBox_choose_prod_add_sale.setFocusable(false);
		searchPanelForProd.setBounds(315, 69, 328, 58);
		searchPanelForProd.setLayout(null);
		searchForProd.setBounds(0, 0, 328, 31);
		comboBox_choose_prod_add_sale.setBounds(0, 31, 328, 26);
		comboBox_choose_prod_add_sale.setRenderer(new ItemRendererForProducts());
		comboBox_choose_prod_add_sale.setSelectedIndex(-1);
		searchPanelForProd.add(comboBox_choose_prod_add_sale);
		searchPanelForProd.add(searchForProd);
		panel_add_sale_screen.add(searchPanelForProd);

		JLabel lblProductName = new JLabel(menu_sale_add_sale_slct_prod_content
				.get(ScreenContentTypes.sale_screen_select_prod_prod_name.toString()).getTitle());
		lblProductName.setFont(new Font("Calibri", Font.BOLD, 26));
		lblProductName.setBounds(35, 168, 208, 43);
		panel_add_sale_screen.add(lblProductName);

		productName_sale_screen = new JLabel("");
		productName_sale_screen.setFont(new Font("Calibri", Font.PLAIN, 26));
		productName_sale_screen.setBounds(315, 168, 328, 43);
		panel_add_sale_screen.add(productName_sale_screen);

		labelAvailableQuantity_sale_screen = new JLabel("");
		labelAvailableQuantity_sale_screen.setFont(new Font("Calibri", Font.PLAIN, 26));
		labelAvailableQuantity_sale_screen.setBounds(315, 392, 328, 43);
		panel_add_sale_screen.add(labelAvailableQuantity_sale_screen);

		JLabel lblAvaliablequantity = new JLabel(menu_sale_add_sale_slct_prod_content
				.get(ScreenContentTypes.sale_screen_select_prod_avlbl_quant.toString()).getTitle());
		lblAvaliablequantity.setFont(new Font("Calibri", Font.BOLD, 26));
		lblAvaliablequantity.setBounds(35, 392, 208, 43);
		panel_add_sale_screen.add(lblAvaliablequantity);

		JLabel lblProductPrice = new JLabel(menu_sale_add_sale_slct_prod_content
				.get(ScreenContentTypes.sale_screen_select_prod_prod_price.toString()).getTitle());
		lblProductPrice.setFont(new Font("Calibri", Font.BOLD, 26));
		lblProductPrice.setBounds(35, 241, 208, 43);
		panel_add_sale_screen.add(lblProductPrice);

		labelProductPrice = new JLabel("");
		labelProductPrice.setFont(new Font("Calibri", Font.PLAIN, 26));
		labelProductPrice.setBounds(315, 241, 328, 43);
		panel_add_sale_screen.add(labelProductPrice);

		JLabel lblProductTax = new JLabel(menu_sale_add_sale_slct_prod_content
				.get(ScreenContentTypes.sale_screen_select_prod_prod_tax.toString()).getTitle());
		lblProductTax.setFont(new Font("Calibri", Font.BOLD, 26));
		lblProductTax.setBounds(35, 321, 208, 43);
		panel_add_sale_screen.add(lblProductTax);

		labelProductTax = new JLabel("");
		labelProductTax.setFont(new Font("Calibri", Font.PLAIN, 26));
		labelProductTax.setBounds(315, 321, 328, 43);
		panel_add_sale_screen.add(labelProductTax);

		JSeparator separator = new JSeparator();
		separator.setBounds(35, 494, 447, 2);
		panel_add_sale_screen.add(separator);

		JLabel lblSale = new JLabel(menu_sale_add_sale.get(ScreenTypes.sale_screen_sale_facts.toString()).getTitle());
		lblSale.setForeground(new Color(0, 0, 139));
		lblSale.setFont(new Font("Calibri", Font.BOLD, 35));
		lblSale.setBounds(35, 530, 405, 51);
		panel_add_sale_screen.add(lblSale);

		JLabel lblSaller = new JLabel(menu_sale_add_sale_facts_sale_content
				.get(ScreenContentTypes.sale_screen_sale_facts_saller_name.toString()).getTitle());
		lblSaller.setFont(new Font("Calibri", Font.BOLD, 26));
		lblSaller.setBounds(56, 604, 208, 43);
		panel_add_sale_screen.add(lblSaller);

		textField_saller_name = new JTextField();
		textField_saller_name.setFont(new Font("Calibri", Font.PLAIN, 26));
		textField_saller_name.setBounds(336, 604, 328, 43);
		panel_add_sale_screen.add(textField_saller_name);

		JLabel lblSoldPrice = new JLabel(menu_sale_add_sale_facts_sale_content
				.get(ScreenContentTypes.sale_screen_sale_facts_sld_price.toString()).getTitle());
		lblSoldPrice.setFont(new Font("Calibri", Font.BOLD, 26));
		lblSoldPrice.setBounds(56, 677, 208, 43);
		panel_add_sale_screen.add(lblSoldPrice);

		textFiled_Sold_price = new JTextField();
		textFiled_Sold_price.setToolTipText("price (numeric value)");
		textFiled_Sold_price.setFont(new Font("Calibri", Font.PLAIN, 26));
		textFiled_Sold_price.setBounds(336, 677, 328, 43);
		panel_add_sale_screen.add(textFiled_Sold_price);

		JLabel lblSoldQuantity = new JLabel(menu_sale_add_sale_facts_sale_content
				.get(ScreenContentTypes.sale_screen_sale_facts_sld_qntity.toString()).getTitle());
		lblSoldQuantity.setFont(new Font("Calibri", Font.BOLD, 26));
		lblSoldQuantity.setBounds(56, 751, 208, 43);
		panel_add_sale_screen.add(lblSoldQuantity);

		txtQuantitynumericValue = new JTextField("");
		txtQuantitynumericValue.setToolTipText("quantity (numeric value)");
		txtQuantitynumericValue.setFont(new Font("Calibri", Font.PLAIN, 26));
		txtQuantitynumericValue.setBounds(336, 751, 328, 43);
		panel_add_sale_screen.add(txtQuantitynumericValue);
		addDocumentListenerForQuantityField();

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(848, 190, 2, 358);
		panel_add_sale_screen.add(separator_1);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(1199, 69, 328, 58);
		panel_add_sale_screen.add(panel);

		comboBox_choose_client = new JComboBox<>();
		comboBox_choose_client.setRenderer(new ItemRendererForClients());
		comboBox_choose_client.setSelectedIndex(-1);
		comboBox_choose_client.setOpaque(false);
		comboBox_choose_client.setFont(new Font("Calibri", Font.PLAIN, 23));
		comboBox_choose_client.setFocusable(false);
		comboBox_choose_client.setBorder(null);
		comboBox_choose_client.setBounds(0, 31, 328, 26);
		panel.add(comboBox_choose_client);
		addItemSelectionListenerOnChooseClientCombobox();

		textField_selectClient = new JTextField();
		textField_selectClient.setFont(new Font("Calibri", Font.PLAIN, 23));
		textField_selectClient.setBorder(new LineBorder(UIManager.getColor("CheckBox.darkShadow")));
		textField_selectClient.setBackground(SystemColor.controlHighlight);
		textField_selectClient.setBounds(0, 0, 328, 31);
		panel.add(textField_selectClient);
		addingDocumentListenerOnClientSelectTextField();

		JLabel labelChooseClient = new JLabel(menu_sale_select_client_content
				.get(ScreenContentTypes.sale_screen_slct_client_choose_client.toString()).getTitle());
		labelChooseClient.setFont(new Font("Calibri", Font.BOLD, 26));
		labelChooseClient.setBounds(919, 77, 208, 43);
		panel_add_sale_screen.add(labelChooseClient);

		JLabel labelSelectClient = new JLabel(
				menu_sale_add_sale.get(ScreenTypes.sale_screen_slct_client.toString()).getTitle());
		labelSelectClient.setForeground(new Color(0, 0, 139));
		labelSelectClient.setFont(new Font("Calibri", Font.BOLD, 35));
		labelSelectClient.setBounds(896, 13, 405, 51);
		panel_add_sale_screen.add(labelSelectClient);

		JLabel lblClientName = new JLabel(menu_sale_select_client_content
				.get(ScreenContentTypes.sale_screen_slct_client_client_name.toString()).getTitle());
		lblClientName.setFont(new Font("Calibri", Font.BOLD, 26));
		lblClientName.setBounds(919, 168, 208, 43);
		panel_add_sale_screen.add(lblClientName);

		label_clientName_show = new JLabel("");
		label_clientName_show.setFont(new Font("Calibri", Font.PLAIN, 26));
		label_clientName_show.setBounds(1199, 168, 328, 43);
		panel_add_sale_screen.add(label_clientName_show);

		JLabel lblContactNumber = new JLabel(menu_sale_select_client_content
				.get(ScreenContentTypes.sale_screen_slct_client_client_number.toString()).getTitle());
		lblContactNumber.setFont(new Font("Calibri", Font.BOLD, 26));
		lblContactNumber.setBounds(919, 241, 208, 43);
		panel_add_sale_screen.add(lblContactNumber);

		label_contact_no_show = new JLabel("");
		label_contact_no_show.setFont(new Font("Calibri", Font.PLAIN, 26));
		label_contact_no_show.setBounds(1199, 241, 328, 43);
		panel_add_sale_screen.add(label_contact_no_show);

		JLabel lblClientEmail = new JLabel(menu_sale_select_client_content
				.get(ScreenContentTypes.sale_screen_slct_client_client_email.toString()).getTitle());
		lblClientEmail.setFont(new Font("Calibri", Font.BOLD, 26));
		lblClientEmail.setBounds(919, 321, 208, 43);
		panel_add_sale_screen.add(lblClientEmail);

		JLabel lblAccountNumber = new JLabel(menu_sale_select_client_content
				.get(ScreenContentTypes.sale_screen_slct_client_account_no.toString()).getTitle());
		lblAccountNumber.setFont(new Font("Calibri", Font.BOLD, 26));
		lblAccountNumber.setBounds(919, 392, 208, 43);
		panel_add_sale_screen.add(lblAccountNumber);

		label_clientEmail_show = new JLabel("");
		label_clientEmail_show.setFont(new Font("Calibri", Font.PLAIN, 26));
		label_clientEmail_show.setBounds(1199, 321, 328, 43);
		panel_add_sale_screen.add(label_clientEmail_show);

		label_client_account_no_show = new JLabel("");
		label_client_account_no_show.setFont(new Font("Calibri", Font.PLAIN, 26));
		label_client_account_no_show.setBounds(1199, 392, 328, 43);
		panel_add_sale_screen.add(label_client_account_no_show);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(1043, 494, 447, 2);
		panel_add_sale_screen.add(separator_2);

		JLabel lblClientAddress_show = new JLabel(menu_sale_select_client_content
				.get(ScreenContentTypes.sale_screen_slct_client_client_addr.toString()).getTitle());
		lblClientAddress_show.setFont(new Font("Calibri", Font.BOLD, 26));
		lblClientAddress_show.setBounds(919, 530, 208, 43);
		panel_add_sale_screen.add(lblClientAddress_show);

		label_client_address_show = new JLabel("");
		label_client_address_show.setFont(new Font("Calibri", Font.PLAIN, 26));
		label_client_address_show.setBounds(1199, 530, 328, 43);
		panel_add_sale_screen.add(label_client_address_show);

		JLabel lblContactDate = new JLabel(menu_sale_select_client_content
				.get(ScreenContentTypes.sale_screen_slct_client_client_date.toString()).getTitle());
		lblContactDate.setFont(new Font("Calibri", Font.BOLD, 26));
		lblContactDate.setBounds(919, 603, 208, 43);
		panel_add_sale_screen.add(lblContactDate);

		label_client_date = new JLabel("");
		label_client_date.setFont(new Font("Calibri", Font.PLAIN, 26));
		label_client_date.setBounds(1199, 603, 328, 43);
		panel_add_sale_screen.add(label_client_date);

		JLabel lblClientReference = new JLabel(menu_sale_select_client_content
				.get(ScreenContentTypes.sale_screen_slct_client_ref_name.toString()).getTitle());
		lblClientReference.setFont(new Font("Calibri", Font.BOLD, 26));
		lblClientReference.setBounds(919, 683, 208, 43);
		panel_add_sale_screen.add(lblClientReference);

		label_client_reference_name = new JLabel("");
		label_client_reference_name.setFont(new Font("Calibri", Font.PLAIN, 26));
		label_client_reference_name.setBounds(1199, 683, 328, 43);
		panel_add_sale_screen.add(label_client_reference_name);

		JLabel lblReferenceAddress = new JLabel(menu_sale_select_client_content
				.get(ScreenContentTypes.sale_screen_slct_client_ref_addr.toString()).getTitle());
		lblReferenceAddress.setFont(new Font("Calibri", Font.BOLD, 26));
		lblReferenceAddress.setBounds(919, 754, 208, 43);
		panel_add_sale_screen.add(lblReferenceAddress);

		label_client_ref_address_show = new JLabel("");
		label_client_ref_address_show.setFont(new Font("Calibri", Font.PLAIN, 26));
		label_client_ref_address_show.setBounds(1199, 754, 328, 43);
		panel_add_sale_screen.add(label_client_ref_address_show);

		JButton btnSale = new JButton("Sale");
		btnSale.setBorder(new LineBorder(new Color(128, 0, 0), 2));
		btnSale.setForeground(new Color(128, 0, 0));
		btnSale.setFont(new Font("Calibri", Font.BOLD, 30));
		btnSale.setBounds(1425, 810, 177, 42);
		panel_add_sale_screen.add(btnSale);

		JLabel labelTotalSalePerQuant = new JLabel(menu_sale_add_sale_facts_sale_content
				.get(ScreenContentTypes.sale_screen_Sale_facts_total_price_per_quant.toString()).getTitle());
		labelTotalSalePerQuant.setForeground(new Color(128, 0, 0));
		labelTotalSalePerQuant.setFont(new Font("Calibri", Font.BOLD, 26));
		labelTotalSalePerQuant.setBounds(56, 822, 208, 43);
		panel_add_sale_screen.add(labelTotalSalePerQuant);

		label_ShowTotalPriceWithQuant = new JLabel("");
		label_ShowTotalPriceWithQuant.setForeground(new Color(128, 0, 0));
		label_ShowTotalPriceWithQuant.setFont(new Font("Calibri", Font.PLAIN, 26));
		label_ShowTotalPriceWithQuant.setBounds(336, 826, 328, 43);
		panel_add_sale_screen.add(label_ShowTotalPriceWithQuant);
		addingEditorListenerOnComboBoxProdAddSale();
		addingItemListenerForSelectProdSaleScrn();

		btnSale.addActionListener((a) -> {
			addSaleToDb();
		});

	}

	private void addDocumentListenerForQuantityField() {
		txtQuantitynumericValue.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					int quantity = Integer.parseInt(txtQuantitynumericValue.getText().toString());
					int price = Integer.parseInt(textFiled_Sold_price.getText().toString());
					label_ShowTotalPriceWithQuant.setText(Integer.toString((price * quantity)));
				} catch (NumberFormatException e1) {

				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					int quantity = Integer.parseInt(txtQuantitynumericValue.getText().toString());
					int price = Integer.parseInt(textFiled_Sold_price.getText().toString());
					label_ShowTotalPriceWithQuant.setText(Integer.toString((price * quantity)));
				} catch (NumberFormatException e1) {

				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

	}

	private void addSaleToDb() {
		try {
			SaleModel saleModel = new SaleModel();
			ProductModel productModel = (ProductModel) comboBox_choose_prod_add_sale.getSelectedItem();
			ClientModel clientModel = (ClientModel) comboBox_choose_client.getSelectedItem();
			saleModel.setClientModel(clientModel);
			saleModel.setProductModel(productModel);
			saleModel.setSallerName(textField_saller_name.getText().toString());
			saleModel.setSalePrice(Integer.parseInt(textFiled_Sold_price.getText()));
			saleModel.setSoldQuantity(Integer.parseInt(txtQuantitynumericValue.getText().toString()));
			saleModel.setAuthModel(ContentHelper.getInstance().getAuthModel());
			saleModel.setTotalBill(Integer.parseInt(label_ShowTotalPriceWithQuant.getText().toString()));
			if (!AppUtils.isNull(ContentHelper.getInstance().inserSale(saleModel))) {
				JOptionPane.showMessageDialog(frame, "Sold successfully, please check stats for details", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				addingElementsToComboBox_prod(comboBox_choose_prod_add_sale);
				initSallerInfoFields();
			} else {
				JOptionPane.showMessageDialog(frame, "Sold unsuccessfull, please contact developer", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Please enter numeric values for numeric fields", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	//TODO
	private void initSallerInfoFields() {
		productName_sale_screen.setText("");
		textField_saller_name.setText("");
		textFiled_Sold_price.setText("");
		txtQuantitynumericValue.setText("");
		labelProductPrice.setText("");
		labelProductTax.setText("");
		labelAvailableQuantity_sale_screen.setText("");
		label_ShowTotalPriceWithQuant.setText("");
		label_clientName_show.setText("");
		label_contact_no_show.setText("");
		label_clientEmail_show.setText("");
		label_client_account_no_show.setText("");
		label_client_address_show.setText("");
		label_client_date.setText("");
		label_client_reference_name.setText("");
		label_client_ref_address_show.setText("");
		
	}

	private void addItemSelectionListenerOnChooseClientCombobox() {
		comboBox_choose_client.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				ClientModel cm = (ClientModel) e.getItem();
				if (!AppUtils.isNull(cm)) {
					label_clientName_show.setText(cm.getName());
					label_contact_no_show.setText(cm.getContactNo());
					label_clientEmail_show.setText(cm.getEmail());
					label_client_account_no_show.setText(cm.getAccountNumber());
					label_client_address_show.setText(cm.getAddress());
					label_client_date.setText(cm.getDate());
					label_client_reference_name.setText(cm.getReferralName());
					label_client_ref_address_show.setText(cm.getReferralAddr());

				}
			}
		});

	}

	private void addingDocumentListenerOnClientSelectTextField() {
		textField_selectClient.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				String data = textField_selectClient.getText().toString();
				SwingUtilities.invokeLater(() -> {
					ClientModel clientModel = new ClientModel();
					clientModel.setName(data);
					try {
						addingElementsToComboBox_client_like(
								ContentHelper.getInstance().getAllClientsLike(clientModel));
					} catch (DBException e1) {
						e1.printStackTrace();
					}
				});
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String data = textField_selectClient.getText().toString();
				SwingUtilities.invokeLater(() -> {
					ClientModel clientModel = new ClientModel();
					clientModel.setName(data);
					try {
						addingElementsToComboBox_client_like(
								ContentHelper.getInstance().getAllClientsLike(clientModel));
					} catch (DBException e1) {
						e1.printStackTrace();
					}
				});

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
	}

	private void addingItemListenerForSelectProdSaleScrn() {
		comboBox_choose_prod_add_sale.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							ProductModel productModel = (ProductModel) e.getItem();
							if (!AppUtils.isNull(productModel)) {
								productName_sale_screen.setText(productModel.getProductName());
								labelProductPrice.setText(Integer.toString(productModel.getPrice()));
								labelProductTax.setText(Integer.toString(productModel.getTax()));
								labelAvailableQuantity_sale_screen
										.setText(Integer.toString(productModel.getProductQuantity()));
							}
						}
					});
				}

			}
		});

	}

	private void addingEditorListenerOnComboBoxProdAddSale() {
		searchForProd.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				String data = searchForProd.getText().toString();
				SwingUtilities.invokeLater(() -> {
					ProductModel productModel = new ProductModel();
					productModel.setProductName(data);
					try {
						addingElementsToComboBox_prod_like(
								ContentHelper.getInstance().getALLProductsLike(productModel));
					} catch (DBException e1) {
						e1.printStackTrace();
					}
				});

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String data = searchForProd.getText().toString();
				SwingUtilities.invokeLater(() -> {
					ProductModel productModel = new ProductModel();
					productModel.setProductName(data);
					try {
						addingElementsToComboBox_prod_like(
								ContentHelper.getInstance().getALLProductsLike(productModel));
					} catch (DBException e1) {
						e1.printStackTrace();
					}
				});
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}

	private void addingClientLogsScreen() {
		panel_client_screen.setVisible(true);
		panel_add_edit_products.setVisible(false);
		panel_product_screen.setVisible(false);
		panel_edit_add_client_screen.setVisible(false);
		panel_add_sale_screen.setVisible(false);
		panel_sale.setVisible(false);
		panel_profit.setVisible(false);
		panel_report.setVisible(false);
		settingClientStatsToTable(1);

	}

	private void settingClientStatsToTable(int i) {
		try {
			List<UserClientRelationLogsModel> data = ContentHelper.getInstance().getClientLogs(i);
			if (!AppUtils.isNullOrEmptyList(data)) {
				if (!AppUtils.isNullOrZeroInteger(columnModelForClient.getRowCount())) {
					removeAllClientLogs(i);
				}
				for (UserClientRelationLogsModel model : data) {
					columnModelForClient.addRow(new Object[] { model.getId(), model.getClientModel().getName(),
							model.getClientModel().getContactNo(), model.getClientModel().getEmail(),
							model.getClientModel().getAge(), model.getClientModel().getGender(),
							model.getClientModel().getAccountNumber(), model.getClientModel().getDate(),
							model.getClientModel().getCountry(), model.getClientModel().getAddress(),
							model.getClientModel().getReferralName(), model.getAuthModel().getUsername(),
							model.getAuthModel().getContactNumber(), AppUtils.getProdLogStatus(model.getStatus()) });
				}
			} else {
				JOptionPane.showMessageDialog(frame, "No more logs found", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void addingClientsLogsScreen() {
		panel_client_screen = new JPanel();
		panel_client_screen.setVisible(false);
		panel_client_screen.setBounds(243, 83, 1659, 865);
		panel_main.add(panel_client_screen);
		panel_client_screen.setLayout(null);

		JLabel lblClientsLogs = new JLabel(
				menu_view_client_stats.get(ScreenTypes.client_view_client_stats.toString()).getTitle());
		lblClientsLogs.setBounds(12, 13, 379, 65);
		lblClientsLogs.setFont(new Font("Calibri", Font.BOLD, 37));
		panel_client_screen.add(lblClientsLogs);

		tableForClient = new JTable();
		columnModelForClient = new DefaultTableModel();
		columnModelForClient.setColumnIdentifiers(AppUtils.CLIENT_STAT_TABLE_HEADERS);
		tableForClient.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableForClient.setFillsViewportHeight(true);
		tableForClient.setModel(columnModelForClient);
		JScrollPane jScrollPane = new JScrollPane(tableForClient);
		jScrollPane.setBounds(22, 91, 1620, 718);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_client_screen.add(jScrollPane);

		btnNext_client = new JButton("Next");
		btnNext_client.setBounds(1545, 816, 97, 25);
		btnNext_client.setName("1");
		panel_client_screen.add(btnNext_client);

		btnPrevious_client = new JButton("Previous");
		btnPrevious_client.setBounds(1436, 816, 97, 25);
		btnPrevious_client.setName((Integer.parseInt(btnNext_client.getName()) - 1) + "");
		panel_client_screen.add(btnPrevious_client);

		JButton btnClearClientsLogs = new JButton("Clear logs");
		btnClearClientsLogs.setBounds(1327, 816, 97, 25);
		panel_client_screen.add(btnClearClientsLogs);

		btnClearClientsLogs.addActionListener((t) -> {
			clearAllClientLogs();
		});

		btnPrevious_client.addActionListener(p -> {
			int page = Integer.parseInt(btnPrevious_client.getName() + "");
			--page;
			if (page >= 1) {
				settingClientStatsToTable(page);
				btnPrevious_client.setName(page + "");
				btnNext_client.setName(page + "");
			} else {
				JOptionPane.showMessageDialog(frame, "This is first client-logs page, there is no previous data",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnNext_client.addActionListener(i -> {
			int page = Integer.parseInt(btnNext_client.getName() + "");
			++page;
			settingClientStatsToTable(page);
		});

	}

	private void clearAllClientLogs() {
		try {
			String password = JOptionPane.showInputDialog(frame, "For delete all please enter your password?",
					"Security alert", JOptionPane.WARNING_MESSAGE);
			if (ContentHelper.getInstance().getAuthModel().getPassword().equals(password)) {
				if (ContentHelper.getInstance().deleteAllClientsLogs() == 0) {
					JOptionPane.showMessageDialog(frame, "Logs not deleted successfully", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "All logs deleted successfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					removeAllClientLogs(0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Enter password is wrong!!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (DBException | IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearAllSaleLogs() {
		try {
			String password = JOptionPane.showInputDialog(frame, "For delete all please enter your password?",
					"Security alert", JOptionPane.WARNING_MESSAGE);
			if (ContentHelper.getInstance().getAuthModel().getPassword().equals(password)) {
				if (ContentHelper.getInstance().deleteAllSaleLogs() == 0) {
					JOptionPane.showMessageDialog(frame, "Logs not deleted successfully", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "All logs deleted successfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					removeAllSaleLogs(0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Enter password is wrong!!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void addingClientScreenPanel() {
		panel_add_edit_products.setVisible(false);
		panel_product_screen.setVisible(false);
		panel_edit_add_client_screen.setVisible(true);
		panel_client_screen.setVisible(false);
		panel_add_sale_screen.setVisible(false);
		panel_profit.setVisible(false);
		panel_sale.setVisible(false);
		panel_report.setVisible(false);
	}

	private void addingEditClientScreenToMainScreen() {
		panel_edit_add_client_screen = new JPanel();
		panel_edit_add_client_screen.setBounds(243, 83, 1659, 879);
		panel_main.add(panel_edit_add_client_screen);
		panel_edit_add_client_screen.setVisible(false);
		panel_edit_add_client_screen.setLayout(null);

		JLabel lblClient = new JLabel(menu_client_add_screen.get(ScreenTypes.clients_add_edit.toString()).getTitle());
		lblClient.setFont(new Font("Calibri", Font.BOLD, 33));
		lblClient.setBounds(12, 13, 213, 53);
		panel_edit_add_client_screen.add(lblClient);

		JLabel lblClientName = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_name.toString()).getTitle());
		lblClientName.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientName.setBounds(34, 79, 161, 29);
		panel_edit_add_client_screen.add(lblClientName);

		textField_client_name = new JTextField();
		textField_client_name.setBounds(211, 79, 336, 29);
		panel_edit_add_client_screen.add(textField_client_name);
		textField_client_name.setColumns(10);

		textField_clientNumber = new JTextField();
		textField_clientNumber.setColumns(10);
		textField_clientNumber.setBounds(211, 141, 336, 29);
		panel_edit_add_client_screen.add(textField_clientNumber);

		JLabel lblClientNumber = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_contact_no.toString()).getTitle());
		lblClientNumber.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientNumber.setBounds(34, 141, 161, 29);
		panel_edit_add_client_screen.add(lblClientNumber);

		textField_clientEmail = new JTextField();
		textField_clientEmail.setColumns(10);
		textField_clientEmail.setBounds(211, 201, 336, 29);
		panel_edit_add_client_screen.add(textField_clientEmail);

		JLabel lblClientEmail = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_email.toString()).getTitle());
		lblClientEmail.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientEmail.setBounds(34, 201, 161, 29);
		panel_edit_add_client_screen.add(lblClientEmail);

		textField_client_age = new JTextField();
		textField_client_age.setToolTipText("Enter numeric age");
		textField_client_age.setColumns(10);
		textField_client_age.setBounds(211, 258, 336, 29);
		panel_edit_add_client_screen.add(textField_client_age);

		JLabel lblClientAge = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_age.toString()).getTitle());
		lblClientAge.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientAge.setBounds(34, 258, 161, 29);
		panel_edit_add_client_screen.add(lblClientAge);

		textField_client_gender = new JTextField();
		textField_client_gender.setToolTipText("Enter M or F");
		textField_client_gender.setColumns(10);
		textField_client_gender.setBounds(211, 316, 336, 29);
		panel_edit_add_client_screen.add(textField_client_gender);

		JLabel lblClientGender = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_gender.toString()).getTitle());
		lblClientGender.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientGender.setBounds(34, 316, 161, 29);
		panel_edit_add_client_screen.add(lblClientGender);

		textField_client_account = new JTextField();
		textField_client_account.setColumns(10);
		textField_client_account.setBounds(211, 372, 336, 29);
		panel_edit_add_client_screen.add(textField_client_account);

		JLabel lblAccountNo = new JLabel(menu_client_add_screen_screen_content
				.get(ScreenContentTypes.client_account_number.toString()).getTitle());
		lblAccountNo.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblAccountNo.setBounds(34, 372, 161, 29);
		panel_edit_add_client_screen.add(lblAccountNo);

		textField_client_country = new JTextField();
		textField_client_country.setColumns(10);
		textField_client_country.setBounds(211, 434, 336, 29);
		panel_edit_add_client_screen.add(textField_client_country);

		JLabel lblClientCountry = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_country.toString()).getTitle());
		lblClientCountry.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientCountry.setBounds(34, 434, 161, 29);
		panel_edit_add_client_screen.add(lblClientCountry);

		textField_client_district = new JTextField();
		textField_client_district.setColumns(10);
		textField_client_district.setBounds(211, 494, 336, 29);
		panel_edit_add_client_screen.add(textField_client_district);

		JLabel lblClientDistrict = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_district.toString()).getTitle());
		lblClientDistrict.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientDistrict.setBounds(34, 494, 161, 29);
		panel_edit_add_client_screen.add(lblClientDistrict);

		textField_client_state = new JTextField();
		textField_client_state.setColumns(10);
		textField_client_state.setBounds(211, 553, 336, 29);
		panel_edit_add_client_screen.add(textField_client_state);

		JLabel lblClientState = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_state.toString()).getTitle());
		lblClientState.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientState.setBounds(34, 553, 161, 29);
		panel_edit_add_client_screen.add(lblClientState);

		JLabel lblClientAddress = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_addr.toString()).getTitle());
		lblClientAddress.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientAddress.setBounds(34, 613, 161, 29);
		panel_edit_add_client_screen.add(lblClientAddress);

		textField_client_address = new JTextField();
		textField_client_address.setColumns(10);
		textField_client_address.setBounds(211, 613, 336, 29);
		panel_edit_add_client_screen.add(textField_client_address);

		JLabel lblReferralName = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_ref_name.toString()).getTitle());
		lblReferralName.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblReferralName.setBounds(34, 720, 161, 29);
		panel_edit_add_client_screen.add(lblReferralName);

		textField_client_referral = new JTextField();
		textField_client_referral.setColumns(10);
		textField_client_referral.setBounds(211, 720, 336, 29);
		panel_edit_add_client_screen.add(textField_client_referral);

		JLabel lblReferralAddress = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_ref_address.toString()).getTitle());
		lblReferralAddress.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblReferralAddress.setBounds(34, 777, 161, 29);
		panel_edit_add_client_screen.add(lblReferralAddress);

		textField_referral_address = new JTextField();
		textField_referral_address.setColumns(10);
		textField_referral_address.setBounds(211, 777, 336, 29);
		panel_edit_add_client_screen.add(textField_referral_address);

		JLabel lblDate = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_date.toString()).getTitle());
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblDate.setBounds(34, 665, 161, 29);
		panel_edit_add_client_screen.add(lblDate);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(211, 665, 336, 29);
		panel_edit_add_client_screen.add(datePicker);

		JButton btnSaveClient = new JButton("Save");
		btnSaveClient.setBounds(452, 829, 97, 25);
		panel_edit_add_client_screen.add(btnSaveClient);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.GRAY);
		separator.setBounds(755, 81, 10, 725);
		panel_edit_add_client_screen.add(separator);

		JLabel lbl_edit_client = new JLabel(
				menu_client_add_screen.get(ScreenTypes.clients_add_edit.toString()).getTitle());
		lbl_edit_client.setFont(new Font("Calibri", Font.BOLD, 33));
		lbl_edit_client.setBounds(892, 13, 213, 53);
		panel_edit_add_client_screen.add(lbl_edit_client);

		JLabel lblSelectClient = new JLabel(menu_client_add_screen_screen_content
				.get(ScreenContentTypes.client_select_clients.toString()).getTitle());
		lblSelectClient.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblSelectClient.setBounds(892, 66, 161, 29);
		panel_edit_add_client_screen.add(lblSelectClient);

		comboBox_select_client = new JComboBox<>();
		comboBox_select_client.setBounds(1069, 65, 336, 32);
		comboBox_select_client.setRenderer(new ItemRendererForClients());
		panel_edit_add_client_screen.add(comboBox_select_client);
		addingElementsToComboBox_client();
		addingItemListenerOnEditClientComboBox();

		JLabel lblClientName_1 = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_name.toString()).getTitle());
		lblClientName_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientName_1.setBounds(892, 119, 161, 29);
		panel_edit_add_client_screen.add(lblClientName_1);

		textField_cleint_name_edit = new JTextField();
		textField_cleint_name_edit.setColumns(10);
		textField_cleint_name_edit.setBounds(1069, 119, 336, 29);
		panel_edit_add_client_screen.add(textField_cleint_name_edit);

		JLabel lblContactNo = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_contact_no.toString()).getTitle());
		lblContactNo.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblContactNo.setBounds(892, 171, 161, 29);
		panel_edit_add_client_screen.add(lblContactNo);

		textField_edit_client_no = new JTextField();
		textField_edit_client_no.setColumns(10);
		textField_edit_client_no.setBounds(1069, 171, 336, 29);
		panel_edit_add_client_screen.add(textField_edit_client_no);

		JLabel lblEmail = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_email.toString()).getTitle());
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblEmail.setBounds(892, 224, 161, 29);
		panel_edit_add_client_screen.add(lblEmail);

		textField_edit_client_email = new JTextField();
		textField_edit_client_email.setColumns(10);
		textField_edit_client_email.setBounds(1069, 224, 336, 29);
		panel_edit_add_client_screen.add(textField_edit_client_email);

		JLabel lblAge = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_age.toString()).getTitle());
		lblAge.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblAge.setBounds(892, 274, 161, 29);
		panel_edit_add_client_screen.add(lblAge);

		textField_edit_client_age = new JTextField();
		textField_edit_client_age.setColumns(10);
		textField_edit_client_age.setBounds(1069, 274, 336, 29);
		panel_edit_add_client_screen.add(textField_edit_client_age);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.GRAY);
		separator_1.setBounds(960, 327, 286, 8);
		panel_edit_add_client_screen.add(separator_1);

		JLabel lblGender = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_gender.toString()).getTitle());
		lblGender.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblGender.setBounds(892, 358, 161, 29);
		panel_edit_add_client_screen.add(lblGender);

		textField_client_edit_gender = new JTextField();
		textField_client_edit_gender.setColumns(10);
		textField_client_edit_gender.setBounds(1069, 358, 336, 29);
		panel_edit_add_client_screen.add(textField_client_edit_gender);

		JLabel lblAccountNo_1 = new JLabel(menu_client_add_screen_screen_content
				.get(ScreenContentTypes.client_account_number.toString()).getTitle());
		lblAccountNo_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblAccountNo_1.setBounds(892, 400, 161, 29);
		panel_edit_add_client_screen.add(lblAccountNo_1);

		textField_client_edit_acc_no = new JTextField();
		textField_client_edit_acc_no.setColumns(10);
		textField_client_edit_acc_no.setBounds(1069, 400, 336, 29);
		panel_edit_add_client_screen.add(textField_client_edit_acc_no);

		JLabel lblClientCountry_1 = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_country.toString()).getTitle());
		lblClientCountry_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientCountry_1.setBounds(892, 442, 161, 29);
		panel_edit_add_client_screen.add(lblClientCountry_1);

		textField_client_edit_country = new JTextField();
		textField_client_edit_country.setColumns(10);
		textField_client_edit_country.setBounds(1069, 442, 336, 29);
		panel_edit_add_client_screen.add(textField_client_edit_country);

		JLabel lblClientDistrict_1 = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_district.toString()).getTitle());
		lblClientDistrict_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientDistrict_1.setBounds(892, 484, 161, 29);
		panel_edit_add_client_screen.add(lblClientDistrict_1);

		textField_client_edit_district = new JTextField();
		textField_client_edit_district.setColumns(10);
		textField_client_edit_district.setBounds(1069, 484, 336, 29);
		panel_edit_add_client_screen.add(textField_client_edit_district);

		JLabel lblClientAddress_1 = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_addr.toString()).getTitle());
		lblClientAddress_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientAddress_1.setBounds(892, 575, 161, 29);
		panel_edit_add_client_screen.add(lblClientAddress_1);

		textField_client_edit_addr = new JTextField();
		textField_client_edit_addr.setColumns(10);
		textField_client_edit_addr.setBounds(1069, 575, 336, 29);
		panel_edit_add_client_screen.add(textField_client_edit_addr);

		JLabel lblClientState_1 = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_state.toString()).getTitle());
		lblClientState_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblClientState_1.setBounds(892, 533, 161, 29);
		panel_edit_add_client_screen.add(lblClientState_1);

		textField_client_edit_state = new JTextField();
		textField_client_edit_state.setColumns(10);
		textField_client_edit_state.setBounds(1069, 533, 336, 29);
		panel_edit_add_client_screen.add(textField_client_edit_state);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.GRAY);
		separator_2.setBounds(960, 628, 286, 8);
		panel_edit_add_client_screen.add(separator_2);

		JLabel lblRefferalName = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_ref_name.toString()).getTitle());
		lblRefferalName.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblRefferalName.setBounds(892, 698, 161, 29);
		panel_edit_add_client_screen.add(lblRefferalName);

		JLabel lblDate_1 = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_date.toString()).getTitle());
		lblDate_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblDate_1.setBounds(892, 649, 161, 29);
		panel_edit_add_client_screen.add(lblDate_1);

		JLabel lblRefferalAddress = new JLabel(
				menu_client_add_screen_screen_content.get(ScreenContentTypes.client_ref_address.toString()).getTitle());
		lblRefferalAddress.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblRefferalAddress.setBounds(892, 740, 161, 29);
		panel_edit_add_client_screen.add(lblRefferalAddress);

		UtilDateModel modelEdit = new UtilDateModel();
		JDatePanelImpl datePanelEdit = new JDatePanelImpl(modelEdit, p);
		client_datePickerEdit = new JDatePickerImpl(datePanelEdit, new DateLabelFormatter());
		client_datePickerEdit.setBounds(1069, 649, 336, 29);
		panel_edit_add_client_screen.add(client_datePickerEdit);
		textField_client_edit_refferal_name = new JTextField();
		textField_client_edit_refferal_name.setColumns(10);
		textField_client_edit_refferal_name.setBounds(1069, 698, 336, 29);
		panel_edit_add_client_screen.add(textField_client_edit_refferal_name);

		textField_client_edit_refferal_addr = new JTextField();
		textField_client_edit_refferal_addr.setColumns(10);
		textField_client_edit_refferal_addr.setBounds(1069, 740, 336, 29);
		panel_edit_add_client_screen.add(textField_client_edit_refferal_addr);

		JButton btnUpdateClient = new JButton("Update");
		btnUpdateClient.setActionCommand("");
		btnUpdateClient.setBounds(1326, 796, 79, 24);
		panel_edit_add_client_screen.add(btnUpdateClient);

		JButton buttonClientDlt = new JButton("Delete");
		buttonClientDlt.setBounds(1229, 796, 79, 24);
		panel_edit_add_client_screen.add(buttonClientDlt);

		JButton buttonDeleteAllClient = new JButton("Delete All");
		buttonDeleteAllClient.setBounds(1122, 796, 87, 24);
		panel_edit_add_client_screen.add(buttonDeleteAllClient);

		buttonDeleteAllClient.addActionListener((d) -> {
			deleteAllClients();
		});

		buttonClientDlt.addActionListener((d) -> {
			deleteBySelectedClientId();
		});

		btnUpdateClient.addActionListener(u -> {
			updateClient();
		});

		btnSaveClient.addActionListener((u) -> {
			saveClient();
		});

	}

	private void deleteAllClients() {
		try {
			String password = JOptionPane.showInputDialog(frame, "For delete all clients please enter your password?",
					"Security alert", JOptionPane.WARNING_MESSAGE);
			if (ContentHelper.getInstance().getAuthModel().getPassword().equals(password)) {
				ContentHelper.getInstance().deleteAllSaleLogs();
				int result = ContentHelper.getInstance().deleteAllClients();
				if (!AppUtils.isNullOrZeroInteger(result)) {
					ClientModel clientModel = new ClientModel();
					clientModel.setId(result);
					UserClientRelationLogsModel logs = new UserClientRelationLogsModel();
					logs.setAuthModel(ContentHelper.getInstance().getAuthModel());
					logs.setClientModel(clientModel);
					logs.setStatus(AppUtils.DELETE_ALL_STATUS);
					ContentHelper.getInstance().insertUserClientRelationLogs(logs);
					JOptionPane.showMessageDialog(null, "Clients deleted sucessfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					addingElementsToComboBox_client();
					resetEditClientFields();
				} else {
					JOptionPane.showMessageDialog(null, "ERROR: Clients not deleted!!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Wrong password entered!!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteBySelectedClientId() {
		String password = JOptionPane.showInputDialog(frame, "For delete all products enter your password?",
				"Security alert", JOptionPane.WARNING_MESSAGE);
		if (ContentHelper.getInstance().getAuthModel().getPassword().equals(password)) {
			ClientModel clientModel = (ClientModel) comboBox_select_client.getSelectedItem();
			if (!AppUtils.isNull(clientModel)) {
				try {
					ContentHelper.getInstance().deleteSaleByClientId(clientModel.getId());
					clientModel = ContentHelper.getInstance().deleteClientById(clientModel);
					if (!AppUtils.isNull(clientModel)) {

						UserClientRelationLogsModel logs = new UserClientRelationLogsModel();
						logs.setAuthModel(ContentHelper.getInstance().getAuthModel());
						logs.setClientModel(clientModel);
						logs.setStatus(AppUtils.DELETE_STATUS);
						ContentHelper.getInstance().insertUserClientRelationLogs(logs);
						JOptionPane.showMessageDialog(null, "Client deleted sucessfully", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						addingElementsToComboBox_client();
						resetEditClientFields();

					} else {
						JOptionPane.showMessageDialog(null, "ERROR: Client not deleted!!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Client not selected", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(null, "password is not valid!!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void updateClient() {
		ClientModel clientModel = (ClientModel) comboBox_select_client.getSelectedItem();
		if (!AppUtils.isNull(clientModel)) {
			try {
				clientModel.setName(textField_cleint_name_edit.getText().toString());
				clientModel.setContactNo(textField_edit_client_no.getText().toString());
				clientModel.setEmail(textField_edit_client_email.getText().toString());
				clientModel.setAge(Integer.parseInt(textField_edit_client_age.getText().toString()));
				clientModel.setGender(textField_client_edit_gender.getText().toString());
				clientModel.setAccountNumber(textField_client_edit_acc_no.getText().toString());
				clientModel.setCountry(textField_client_edit_country.getText().toString());
				clientModel.setDistrict(textField_client_edit_district.getText().toString());
				clientModel.setState(textField_client_edit_state.getText().toString());
				clientModel.setAddress(textField_client_edit_addr.getText().toString());
				clientModel.setDate(client_datePickerEdit.getJFormattedTextField().getText());
				clientModel.setReferralName(textField_client_edit_refferal_name.getText().toString());
				clientModel.setReferralAddr(textField_client_edit_refferal_addr.getText().toString());
				ClientModel c = ContentHelper.getInstance().updateClient(clientModel);
				if (!AppUtils.isNull(c)) {
					UserClientRelationLogsModel logs = new UserClientRelationLogsModel();
					logs.setAuthModel(ContentHelper.getInstance().getAuthModel());
					logs.setClientModel(c);
					logs.setStatus(AppUtils.UPDATE_STATUS);
					ContentHelper.getInstance().insertUserClientRelationLogs(logs);
					JOptionPane.showMessageDialog(null, "Client updated sucessfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					addingElementsToComboBox_client();
					resetEditClientFields();
				} else {
					JOptionPane.showMessageDialog(null, "ERROR: Client not updated sucessfully", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "ERROR: Client not selected", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveClient() {
		ClientModel clientModel = new ClientModel();
		try {
			clientModel.setName(textField_client_name.getText().toString());
			clientModel.setContactNo(textField_clientNumber.getText().toString());
			clientModel.setEmail(textField_clientEmail.getText().toString());
			clientModel.setAge(Integer.parseInt(textField_client_age.getText().toString()));
			clientModel.setGender(textField_client_gender.getText().toString());
			clientModel.setAccountNumber(textField_client_account.getText().toString());
			clientModel.setCountry(textField_client_country.getText().toString());
			clientModel.setDistrict(textField_client_district.getText().toString());
			clientModel.setState(textField_client_state.getText().toString());
			clientModel.setAddress(textField_client_address.getText().toString());
			clientModel.setDate(datePicker.getJFormattedTextField().getText());
			clientModel.setReferralName(textField_client_referral.getText().toString());
			clientModel.setReferralAddr(textField_referral_address.getText().toString());
			ClientModel c = ContentHelper.getInstance().insertClient(clientModel);
			if (!AppUtils.isNull(c)) {
				UserClientRelationLogsModel logs = new UserClientRelationLogsModel();
				logs.setAuthModel(ContentHelper.getInstance().getAuthModel());
				logs.setClientModel(c);
				logs.setStatus(AppUtils.CREATE_STATUS);
				ContentHelper.getInstance().insertUserClientRelationLogs(logs);
				JOptionPane.showMessageDialog(null, "Client saved sucessfully", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				addingElementsToComboBox_client();
				resetAddClientFields();
				resetEditClientFields();
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Client not saved sucessfully", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void resetAddClientFields() {
		textField_client_name.setText("");
		textField_clientNumber.setText("");
		textField_clientEmail.setText("");
		textField_client_age.setText("");
		textField_client_gender.setText("");
		textField_client_account.setText("");
		textField_client_country.setText("");
		textField_client_district.setText("");
		textField_client_state.setText("");
		textField_client_address.setText("");
		datePicker.getJFormattedTextField().setText("");
		textField_client_referral.setText("");
		textField_referral_address.setText("");
	}

	private void resetEditClientFields() {
		textField_cleint_name_edit.setText("");
		textField_edit_client_no.setText("");
		textField_edit_client_email.setText("");
		textField_edit_client_age.setText("");
		textField_client_edit_gender.setText("");
		textField_client_edit_acc_no.setText("");
		textField_client_edit_country.setText("");
		textField_client_edit_district.setText("");
		textField_client_edit_state.setText("");
		textField_client_edit_addr.setText("");
		client_datePickerEdit.getJFormattedTextField().setText("");
		textField_client_edit_refferal_name.setText("");
		textField_client_edit_refferal_addr.setText("");
	}

	private void addingProductScreenToMainScreen() {
		panel_product_screen = new JPanel();
		panel_product_screen.setVisible(false);
		panel_product_screen.setBounds(243, 83, 1659, 865);
		panel_main.add(panel_product_screen);
		panel_product_screen.setLayout(null);

		JLabel lblProductStats = new JLabel(
				menu_view_prod_screen.get(ScreenTypes.prod_view_prod_stats.toString()).getTitle());
		lblProductStats.setFont(new Font("Calibri", Font.BOLD, 37));
		lblProductStats.setBounds(12, 13, 379, 65);
		panel_product_screen.add(lblProductStats);

		table = new JTable();
		columnModel = new DefaultTableModel();
		columnModel.setColumnIdentifiers(AppUtils.PROD_STAT_TABLE_HEADERS);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.setModel(columnModel);
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setBounds(22, 91, 1620, 718);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_product_screen.add(jScrollPane);

		btnNext_prods = new JButton("Next");
		btnNext_prods.setBounds(1545, 816, 97, 25);
		btnNext_prods.setName("1");
		panel_product_screen.add(btnNext_prods);

		btnPrevious_prods = new JButton("Previous");
		btnPrevious_prods.setBounds(1436, 816, 97, 25);
		btnPrevious_prods.setName((Integer.parseInt(btnNext_prods.getName()) - 1) + "");
		panel_product_screen.add(btnPrevious_prods);

		JButton btnClearLogs = new JButton("Clear logs");
		btnClearLogs.setBounds(1327, 816, 97, 25);
		panel_product_screen.add(btnClearLogs);

		btnClearLogs.addActionListener((t) -> {
			try {
				String password = JOptionPane.showInputDialog(frame, "For delete all please enter your password?",
						"Security alert", JOptionPane.WARNING_MESSAGE);
				if (ContentHelper.getInstance().getAuthModel().getPassword().equals(password)) {
					ContentHelper.getInstance().deleteProdsLogsAll();
					JOptionPane.showMessageDialog(frame, "All logs deleted successfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					removeAllProdsLogs(0);
				} else {
					JOptionPane.showMessageDialog(null, "ERROR: Enter password is wrong!!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} catch (DBException | IllegalArgumentException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		btnPrevious_prods.addActionListener(r -> {
			int page = Integer.parseInt(btnPrevious_prods.getName() + "");
			--page;
			if (page >= 1) {
				settingdataToTable(page);
				btnPrevious_prods.setName(page + "");
				btnNext_prods.setName(page + "");
			} else {
				JOptionPane.showMessageDialog(frame, "This is first prod-logs page, there is no previous data",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnNext_prods.addActionListener((q) -> {
			int page = Integer.parseInt(btnNext_prods.getName() + "");
			++page;
			settingdataToTable(page);
		});

	}

	private void settingdataToTable(int page) {
		try {
			List<UserProdRelationModel> data = ContentHelper.getInstance().getAllProdLogs(page);
			if (!AppUtils.isNullOrEmptyList(data)) {
				if (!AppUtils.isNullOrZeroInteger(columnModel.getRowCount())) {
					removeAllProdsLogs(page);
				}
				for (UserProdRelationModel model : data) {
					columnModel.addRow(new Object[] { model.getId(), model.getProductModel().getProductName(),
							model.getProductModel().getProductQuantity(), model.getProductModel().getBarCode(),
							model.getProductModel().getPrice(), model.getProductModel().getTax(),
							model.getProductModel().getProductCompany(), model.getProductModel().getSupplierName(),
							model.getProductModel().getSupplierContact(), model.getProductModel().getSupplierAccount(),
							model.getProductModel().getPaymentMethod(), model.getProductModel().getPaidBalance(),
							model.getProductModel().getNetBalance(), model.getAuthModel().getUsername(),
							model.getTimestamp(), model.getAuthModel().getContactNumber(),
							model.getAuthModel().getEmail(), model.getAuthModel().getAddress(),
							AppUtils.getProdLogStatus(model.getStatus()) });
				}
			} else {
				removeAllProdsLogs(page);
				JOptionPane.showMessageDialog(frame, "No more logs found", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (DBException | IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void settingdataToSaleTable(int page) {
		try {
			List<SaleModel> data = ContentHelper.getInstance().getAllSaleStats(page);
			if (!AppUtils.isNullOrEmptyList(data)) {
				if (!AppUtils.isNullOrZeroInteger(columnModelForSale.getRowCount())) {
					removeAllSaleLogs(page);
				}
				for (SaleModel model : data) {
					columnModelForSale.addRow(new Object[] { model.getId(), model.getProductModel().getProductName(),
							model.getProductModel().getProductQuantity(), model.getProductModel().getPrice(),
							model.getProductModel().getTax(), model.getProductModel().getBarCode(),
							model.getSallerName(), model.getSalePrice(), model.getSoldQuantity(),
							model.getClientModel().getName(), model.getClientModel().getAccountNumber(),
							model.getClientModel().getReferralName(), model.getAuthModel().getUsername(),
							model.getDateOfSale(), model.getProfit(), model.getTotalBill() });
				}
			} else {
				removeAllSaleLogs(page);
				JOptionPane.showMessageDialog(frame, "No more logs found", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (DBException | IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void settingdataToSaleTableWeekly(int page) {
		try {
			List<SaleModel> data = ContentHelper.getInstance().getAllSalePagginationForDays(page, AppUtils.weekDates());
			if (!AppUtils.isNullOrEmptyList(data)) {
				if (!AppUtils.isNullOrZeroInteger(columnModelForWeeklyProfit.getRowCount())) {
					removeAllSaleLogsDays(page);
				}
				for (SaleModel model : data) {
					columnModelForWeeklyProfit.addRow(new Object[] { model.getId(),
							model.getProductModel().getProductName(), model.getProductModel().getProductQuantity(),
							model.getClientModel().getName(), model.getClientModel().getAccountNumber(),
							model.getAuthModel().getUsername(), model.getProfit(), model.getDateOfSale() });
				}
			} else {
				removeAllSaleLogsDays(page);
				JOptionPane.showMessageDialog(frame, "No more logs found", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void settingdataToSaleTableMonthly(int page) {
		try {

			List<SaleModel> data = ContentHelper.getInstance().getAllSalePagginationForDays(page,
					AppUtils.monthDates());
			if (!AppUtils.isNullOrEmptyList(data)) {
				if (!AppUtils.isNullOrZeroInteger(columnModelForMonthlyProfit.getRowCount())) {
					removeAllSaleLogsDaysMonthly(page);
				}
				for (SaleModel model : data) {
					columnModelForMonthlyProfit.addRow(new Object[] { model.getId(),
							model.getProductModel().getProductName(), model.getProductModel().getProductQuantity(),
							model.getClientModel().getName(), model.getClientModel().getAccountNumber(),
							model.getAuthModel().getUsername(), model.getProfit(), model.getDateOfSale() });
				}
			} else {
				removeAllSaleLogsDaysMonthly(page);
				JOptionPane.showMessageDialog(frame, "No more logs found", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void removeAllProdsLogs(int page) {
		columnModel.setRowCount(0);
		btnNext_prods.setName(page + "");
		btnPrevious_prods.setName(page + "");
	}

	private void removeAllSaleLogs(int page) {
		columnModelForSale.setRowCount(0);
		btnNext_sale.setName(page + "");
		btnPrevious_sale.setName(page + "");
	}

	private void removeAllSaleLogsDays(int page) {
		columnModelForWeeklyProfit.setRowCount(0);
		btnNextWeeklyProfit.setName(page + "");
		btnPreviousWeeklyProfit.setName(page + "");
	}

	private void removeAllSaleLogsDaysMonthly(int page) {
		columnModelForMonthlyProfit.setRowCount(0);
		button_monthly_stat_next.setName(page + "");
		button_monthly_stats_prev.setName(page + "");
	}

	private void removeAllClientLogs(int page) {
		columnModelForClient.setRowCount(0);
		btnNext_client.setName(page + "");
		btnPrevious_client.setName(page + "");
	}

	private void showProductScreen() {
		panel_add_edit_products.setVisible(false);
		panel_product_screen.setVisible(true);
		panel_edit_add_client_screen.setVisible(false);
		panel_client_screen.setVisible(false);
		panel_add_sale_screen.setVisible(false);
		panel_sale.setVisible(false);
		panel_profit.setVisible(false);
		panel_report.setVisible(false);
		settingdataToTable(1);
	}

	private void addSaleScreen() {
		panel_add_sale_screen.setVisible(true);
		panel_add_edit_products.setVisible(false);
		panel_product_screen.setVisible(false);
		panel_edit_add_client_screen.setVisible(false);
		panel_client_screen.setVisible(false);
		panel_sale.setVisible(false);
		panel_profit.setVisible(false);
		panel_report.setVisible(false);
		addProdsToSaleScreen();
		addingElementsToComboBox_selectClient();
		initSallerInfoFields();
		
	}

	private void addProdsToSaleScreen() {
		addingElementsToComboBox_prod(comboBox_choose_prod_add_sale);
	}

	private void saveProduct() {
		try {
			ProductModel model = getProductForSave();
			if (!AppUtils.isNull(model)) {
				model = ContentHelper.getInstance().saveProducts(model);
				if (AppUtils.isNull(model)) {
					JOptionPane.showMessageDialog(frame, "Product not saved successfully!!!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					saveProdsLogs(AppUtils.CREATE_STATUS, model);
					addingElementsToComboBox_prod(combobox_prod);
					resetFiledsOfAddProdScreen();
					JOptionPane.showMessageDialog(frame, "Product save successfully!!!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (DBException | IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveProdsLogs(String createStatus, ProductModel model) throws DBException {
		UserProdRelationModel userProdRelationModel = new UserProdRelationModel();
		userProdRelationModel.setProductModel(model);
		userProdRelationModel.setAuthModel(ContentHelper.getInstance().getAuthModel());
		userProdRelationModel.setStatus(createStatus);
		if (-1 == ContentHelper.getInstance().insertUserProdRelationLogs(userProdRelationModel)) {
			JOptionPane.showMessageDialog(frame, "logs not saved successfully!!!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void updateProduct() {
		try {
			ProductModel model = getProductForUpdate();
			if (!AppUtils.isNull(model)) {
				if (AppUtils.isNull(ContentHelper.getInstance().updateProducts(model))) {
					JOptionPane.showMessageDialog(frame, "Product not updateed successfully!!!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "Product updated successfully!!!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					saveProdsLogs(AppUtils.UPDATE_STATUS, model);
					addingElementsToComboBox_prod(combobox_prod);
					resetFiledsOfEditProdScreen();
				}
			}
		} catch (DBException | IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addingItemListenerOnEditProductComboBox(JComboBox<ProductModel> combobox_prod) {
		combobox_prod.addItemListener((item) -> {
			if (item.getStateChange() == ItemEvent.SELECTED)
				if (item.getItem() instanceof ProductModel) {
					ProductModel productModel = (ProductModel) item.getItem();
					try {
						productModel = ContentHelper.getInstance().getProductByProdId(productModel);
						setToViewDataForProduct(productModel);
					} catch (DBException | IllegalArgumentException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(frame,
								"ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
		});
	}

	private void addingItemListenerOnEditClientComboBox() {
		comboBox_select_client.addItemListener((item) -> {
			if (item.getStateChange() == ItemEvent.SELECTED)
				if (item.getItem() instanceof ClientModel) {
					ClientModel clientModel = (ClientModel) item.getItem();
					setToViewDataForClient(clientModel);
				}
		});
	}

	private void setToViewDataForClient(ClientModel clientModel) {
		textField_cleint_name_edit.setText(clientModel.getName());
		textField_edit_client_no.setText(clientModel.getContactNo());
		textField_edit_client_email.setText(clientModel.getEmail());
		textField_edit_client_age.setText(Integer.toString(clientModel.getAge()));
		textField_client_edit_gender.setText(clientModel.getGender());
		textField_client_edit_acc_no.setText(clientModel.getAccountNumber());
		textField_client_edit_country.setText(clientModel.getCountry());
		textField_client_edit_state.setText(clientModel.getState());
		textField_client_edit_district.setText(clientModel.getDistrict());
		textField_client_edit_addr.setText(clientModel.getAddress());
		client_datePickerEdit.getJFormattedTextField().setText(clientModel.getDate());
		textField_client_edit_refferal_name.setText(clientModel.getReferralName());
		textField_client_edit_refferal_addr.setText(clientModel.getReferralAddr());
	}

	private void addingWatchListenerOnTextPaidBlnc(JTextField paidF, JTextField price, JTextField taxF,
			JTextField netF) {

		DocumentListener dcmntLstner = new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateNetBalance();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateNetBalance();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void updateNetBalance() {
				try {
					int pr = price.getText().toString().isEmpty() ? 0 : Integer.parseInt(price.getText().toString());
					int paid = paidF.getText().toString().isEmpty() ? 0 : Integer.parseInt(paidF.getText().toString());

					int tax = taxF.getText().toString().isEmpty() ? 0 : Integer.parseInt(taxF.getText().toString());
					int net = (pr + tax) - paid;

					netF.setText(Integer.toString(net));
				} catch (NumberFormatException e1) {
					return;
				}
			}
		};

		paidF.getDocument().addDocumentListener(dcmntLstner);
		price.getDocument().addDocumentListener(dcmntLstner);
		taxF.getDocument().addDocumentListener(dcmntLstner);

	}

	private void setToViewDataForProduct(ProductModel model) {
		textField_prod_name_edit.setText(model.getProductName());
		textField_prod_quantity_edit.setText(Integer.toString(model.getProductQuantity()));
		textField_bar_code_edit.setText(model.getBarCode());
		textField_price_edit.setText(Integer.toString(model.getPrice()));
		textField_tax_edit.setText(Integer.toString(model.getTax()));
		textField_EditProductCompany.setText(model.getProductCompany());
		textField_Sup_edit.setText(model.getSupplierName());
		textField_SupplierContact_edit.setText(model.getSupplierContact());
		textField_SupplierAccount_edit.setText(model.getSupplierAccount());
		comboBox_paymentMethod_edit.setSelectedItem(model.getPaymentMethod());
		textField_paid_blnc_edit.setText(Integer.toString(model.getPaidBalance()));
		textField_net_balance_edit.setText(Integer.toString(model.getNetBalance()));
	}

	private ProductModel getProductForUpdate() {
		ProductModel productModel = new ProductModel();
		try {
			productModel.setId(((ProductModel) combobox_prod.getSelectedItem()).getId());
			productModel.setProductName(textField_prod_name_edit.getText().toString());
			productModel.setProductQuantity(Integer.parseInt(textField_prod_quantity_edit.getText().toString()));
			productModel.setBarCode(textField_bar_code_edit.getText().toString());
			productModel.setPrice(Integer.parseInt(textField_price_edit.getText().toString()));
			productModel.setTax(Integer.parseInt(textField_tax_edit.getText().toString()));
			productModel.setProductCompany(textField_EditProductCompany.getText().toString());
			productModel.setSupplierName(textField_Sup_edit.getText().toString());
			productModel.setSupplierContact(textField_SupplierContact_edit.getText().toString());
			productModel.setSupplierAccount(textField_SupplierAccount_edit.getText().toString());
			productModel.setPaymentMethod((String) comboBox_paymentMethod_edit.getSelectedItem());
			productModel.setPaidBalance(Integer.parseInt(textField_paid_blnc_edit.getText().toString()));
			productModel.setNetBalance(Integer.parseInt(textField_net_balance_edit.getText().toString()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Please enter valid number in numeric fields", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return productModel;
	}

	private void resetFiledsOfEditProdScreen() {
		textField_prod_name_edit.setText("");
		textField_prod_quantity_edit.setText("");
		textField_bar_code_edit.setText("");
		textField_price_edit.setText("");
		textField_tax_edit.setText("");
		textField_EditProductCompany.setText("");
		textField_Sup_edit.setText("");
		textField_SupplierContact_edit.setText("");
		textField_SupplierAccount_edit.setText("");
		comboBox_paymentMethod_edit.setSelectedIndex(-1);
		textField_paid_blnc_edit.setText("");
		textField_net_balance_edit.setText("");
		combobox_prod.setSelectedIndex(-1);
	}

	private void resetFiledsOfAddProdScreen() {
		textField_prodName.setText("");
		textField_prod_quantity.setText("");
		textField_bar_code.setText("");
		textField_price.setText("");
		textField_tax.setText("");
		textField_product_company.setText("");
		textField_supplier_name.setText("");
		textField_supplier_contact.setText("");
		textField_supplier_account.setText("");
		comboBox_payment_methods_1.setSelectedIndex(0);
		textField_net_balance.setText("");
		resetFiledsOfEditProdScreen();
	}

	private ProductModel getProductForSave() {
		ProductModel productModel = new ProductModel();
		try {
			productModel.setProductName(textField_prodName.getText().toString());
			productModel.setProductQuantity(Integer.parseInt(textField_prod_quantity.getText().toString()));
			productModel.setBarCode(textField_bar_code.getText().toString());
			productModel.setPrice(Integer.parseInt(textField_price.getText().toString()));
			productModel.setTax(Integer.parseInt(textField_tax.getText().toString()));
			productModel.setProductCompany(textField_product_company.getText().toString());
			productModel.setSupplierName(textField_supplier_name.getText().toString());
			productModel.setSupplierContact(textField_supplier_contact.getText().toString());
			productModel.setSupplierAccount(textField_supplier_account.getText().toString());
			productModel.setPaymentMethod((String) comboBox_payment_methods_1.getSelectedItem());
			productModel.setPaidBalance(Integer.parseInt(textField_paid_blnc.getText().toString()));
			productModel.setNetBalance(Integer.parseInt(textField_net_balance.getText().toString()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Please enter valid number in numeric fields", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return productModel;
	}

	private void addingElementsToComboBox_prod(JComboBox<ProductModel> combobox_prod) {
		if (combobox_prod.getItemCount() > 0)
			combobox_prod.removeAllItems();
		try {
			List<ProductModel> products = ContentHelper.getInstance().getAllProducts();
			if (!AppUtils.isNull(products) && !products.isEmpty()) {
				for (ProductModel model : products) {
					combobox_prod.addItem(model);
				}
			}
		} catch (DBException | IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void addingElementsToComboBox_client_like(List<ClientModel> clients) {
		if (comboBox_choose_client.getItemCount() > 0)
			comboBox_choose_client.removeAllItems();
		try {
			if (!AppUtils.isNull(clients) && !clients.isEmpty()) {
				for (ClientModel client : clients) {
					comboBox_choose_client.addItem(client);
				}
				comboBox_choose_client.showPopup();
			} else {
				comboBox_choose_client.hidePopup();
			}
			comboBox_choose_client.setSelectedItem(-1);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addingElementsToComboBox_prod_like(List<ProductModel> products) {
		if (comboBox_choose_prod_add_sale.getItemCount() > 0)
			comboBox_choose_prod_add_sale.removeAllItems();
		try {
			if (!AppUtils.isNull(products) && !products.isEmpty()) {
				for (ProductModel model : products) {
					comboBox_choose_prod_add_sale.addItem(model);
				}
				comboBox_choose_prod_add_sale.showPopup();
			} else {
				comboBox_choose_prod_add_sale.hidePopup();
			}
			comboBox_choose_prod_add_sale.setSelectedItem(-1);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addingElementsToComboBox_selectClient() {
		if (comboBox_choose_client.getItemCount() > 0)
			comboBox_choose_client.removeAllItems();
		try {
			List<ClientModel> clients = ContentHelper.getInstance().getAllClients();
			if (!AppUtils.isNull(clients) && !clients.isEmpty()) {
				for (ClientModel model : clients) {
					comboBox_choose_client.addItem(model);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void addingElementsToComboBox_client() {
		if (comboBox_select_client.getItemCount() > 0)
			comboBox_select_client.removeAllItems();
		try {
			List<ClientModel> clients = ContentHelper.getInstance().getAllClients();
			if (!AppUtils.isNull(clients) && !clients.isEmpty()) {
				for (ClientModel model : clients) {
					comboBox_select_client.addItem(model);
				}
				comboBox_select_client.setSelectedIndex(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addingPaymentMethods(JComboBox<String> comboBox_payment_methods) {
		for (String item : Utility.paymentTypes)
			comboBox_payment_methods.addItem(item);

	}

	private void setBorderForFotter(JTextArea fotterText) {
		boolean isRound = Utility.YES.equals(footerModel.getIsRound()) ? true : false;
		if (Utility.YES.equals(footerModel.getIsBorder())) {
			fotterText.setBorder(new LineBorder(new Color(0, 0, 0), 1, isRound));
		} else {
			fotterText.setBorder(null);
		}
	}

	private void setBorderForHeader(JTextArea txtrHeaderdetails) {
		boolean isRound = Utility.YES.equals(headerModel.getIsRound()) ? true : false;
		if (Utility.YES.equals(headerModel.getIsBorder())) {
			txtrHeaderdetails.setBorder(new LineBorder(new Color(0, 0, 0), 1, isRound));
		} else {
			txtrHeaderdetails.setBorder(null);
		}

	}

	private void setVisibilityForHeader(JTextArea txtrHeaderdetails) {
		if (Utility.YES.equals(headerModel.getIsActive())) {
			txtrHeaderdetails.setVisible(true);
		} else
			txtrHeaderdetails.setVisible(false);
	}

	private void setVisibilityForFotter(JTextArea fotterText) {
		if (Utility.YES.equals(footerModel.getIsActive())) {
			fotterText.setVisible(true);
		} else
			fotterText.setVisible(false);
	}

	private void addingAddProdPanel() {
		panel_add_edit_products.setVisible(true);
		panel_product_screen.setVisible(false);
		panel_edit_add_client_screen.setVisible(false);
		panel_client_screen.setVisible(false);
		panel_add_sale_screen.setVisible(false);
		panel_profit.setVisible(false);
		panel_sale.setVisible(false);
		panel_report.setVisible(false);
	}

	private void makeButtonTrans(JButton productsMenu) {
		productsMenu.setBorder(null);
		productsMenu.setOpaque(true);
		productsMenu.setFocusable(false);
		productsMenu.setContentAreaFilled(false);
		productsMenu.setBorderPainted(false);

	}

	class ItemRendererForProducts extends BasicComboBoxRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1616966464656301615L;

		public Component getListCellRendererComponent(JList<Object> list, ProductModel value, int index,
				boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				setText(value.getProductName());
			}
			if (index == -1) {
				setText(value.getProductName());
			}
			return this;
		}
	}

	private void addingEditProductScreenToMainScreen() {
		panel_add_edit_products = new JPanel();
		panel_add_edit_products.setBorder(null);
		panel_add_edit_products.setBounds(255, 83, 1647, 879);
		panel_main.add(panel_add_edit_products);
		panel_add_edit_products.setLayout(null);

		JLabel lblAddProducts = new JLabel(
				menu_add_prod_screen.get(ScreenTypes.prod_add_product.toString()).getTitle());
		lblAddProducts.setFont(new Font("Calibri", Font.BOLD, 33));
		lblAddProducts.setBounds(12, 13, 213, 53);
		panel_add_edit_products.add(lblAddProducts);

		JLabel lblAddProductName = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_prod_name.toString()).getTitle());
		lblAddProductName.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblAddProductName.setBounds(64, 94, 161, 29);
		panel_add_edit_products.add(lblAddProductName);

		textField_prodName = new JTextField();
		textField_prodName.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_prodName.setBounds(237, 94, 342, 29);
		panel_add_edit_products.add(textField_prodName);
		textField_prodName.setColumns(10);

		JLabel lblProductQuantity = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_prod_quant.toString()).getTitle());
		lblProductQuantity.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblProductQuantity.setBounds(64, 147, 161, 29);
		panel_add_edit_products.add(lblProductQuantity);

		textField_prod_quantity = new JTextField();
		textField_prod_quantity.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_prod_quantity.setColumns(10);
		textField_prod_quantity.setBounds(237, 147, 342, 29);
		panel_add_edit_products.add(textField_prod_quantity);

		JLabel lblBarCode = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_bar_cod.toString()).getTitle());
		lblBarCode.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblBarCode.setBounds(64, 200, 161, 29);
		panel_add_edit_products.add(lblBarCode);

		textField_bar_code = new JTextField();
		textField_bar_code.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_bar_code.setColumns(10);
		textField_bar_code.setBounds(237, 200, 342, 29);
		panel_add_edit_products.add(textField_bar_code);

		JLabel lblPrice = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_price.toString()).getTitle());
		lblPrice.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblPrice.setBounds(64, 246, 84, 29);
		panel_add_edit_products.add(lblPrice);

		textField_price = new JTextField();
		textField_price.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_price.setColumns(10);
		textField_price.setBounds(149, 246, 161, 29);
		panel_add_edit_products.add(textField_price);

		JLabel lblTax = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_tax.toString()).getTitle());
		lblTax.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblTax.setBounds(322, 246, 72, 29);
		panel_add_edit_products.add(lblTax);

		textField_tax = new JTextField();
		textField_tax.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_tax.setColumns(10);
		textField_tax.setBounds(397, 246, 182, 29);
		panel_add_edit_products.add(textField_tax);

		JLabel lblProductCompany = new JLabel(menu_add_prod_suplir_info_screen_content
				.get(ScreenContentTypes.prod_supplier_info_prod_cmpny.toString()).getTitle());
		lblProductCompany.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblProductCompany.setBounds(64, 368, 161, 29);
		panel_add_edit_products.add(lblProductCompany);

		textField_product_company = new JTextField();
		textField_product_company.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_product_company.setColumns(10);
		textField_product_company.setBounds(237, 368, 342, 29);
		panel_add_edit_products.add(textField_product_company);

		JLabel lblSup = new JLabel(menu_add_prod_suplir_info_screen_content
				.get(ScreenContentTypes.prod_supplier_info_splier_name.toString()).getTitle());
		lblSup.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblSup.setBounds(64, 421, 161, 29);
		panel_add_edit_products.add(lblSup);

		textField_supplier_name = new JTextField();
		textField_supplier_name.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_supplier_name.setColumns(10);
		textField_supplier_name.setBounds(237, 421, 342, 29);
		panel_add_edit_products.add(textField_supplier_name);

		JLabel lblSupplierContact = new JLabel(menu_add_prod_suplir_info_screen_content
				.get(ScreenContentTypes.prod_supplier_info_splier_cntct.toString()).getTitle());
		lblSupplierContact.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblSupplierContact.setBounds(64, 476, 161, 29);
		panel_add_edit_products.add(lblSupplierContact);

		textField_supplier_contact = new JTextField();
		textField_supplier_contact.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_supplier_contact.setColumns(10);
		textField_supplier_contact.setBounds(237, 476, 342, 29);
		panel_add_edit_products.add(textField_supplier_contact);

		JLabel lblSupplierAccount = new JLabel(menu_add_prod_suplir_info_screen_content
				.get(ScreenContentTypes.prod_supplier_info_splier_acnt.toString()).getTitle());
		lblSupplierAccount.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblSupplierAccount.setBounds(64, 529, 161, 29);
		panel_add_edit_products.add(lblSupplierAccount);

		textField_supplier_account = new JTextField();
		textField_supplier_account.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_supplier_account.setColumns(10);
		textField_supplier_account.setBounds(237, 529, 342, 29);
		panel_add_edit_products.add(textField_supplier_account);

		JLabel lblSupplierInfo = new JLabel(
				menu_add_prod_screen.get(ScreenTypes.prod_supplier_info.toString()).getTitle());
		lblSupplierInfo.setFont(new Font("Calibri", Font.BOLD, 33));
		lblSupplierInfo.setBounds(12, 302, 213, 53);
		panel_add_edit_products.add(lblSupplierInfo);

		JLabel lblPaymentInfo = new JLabel(
				menu_add_prod_screen.get(ScreenTypes.prod_payment_info.toString()).getTitle());
		lblPaymentInfo.setFont(new Font("Calibri", Font.BOLD, 33));
		lblPaymentInfo.setBounds(12, 583, 213, 53);
		panel_add_edit_products.add(lblPaymentInfo);

		JLabel lblPaymentMethod = new JLabel(menu_add_prod_paymnt_info_screen_content
				.get(ScreenContentTypes.prod_payment_info_paymnt_mthds.toString()).getTitle());
		lblPaymentMethod.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblPaymentMethod.setBounds(64, 644, 161, 29);
		panel_add_edit_products.add(lblPaymentMethod);

		comboBox_payment_methods_1 = new JComboBox<String>();
		addingPaymentMethods(comboBox_payment_methods_1);
		comboBox_payment_methods_1.setBounds(237, 641, 342, 32);
		panel_add_edit_products.add(comboBox_payment_methods_1);

		JLabel lblPaidBalance = new JLabel(menu_add_prod_paymnt_info_screen_content
				.get(ScreenContentTypes.prod_payment_info_paid_blnc.toString()).getTitle());
		lblPaidBalance.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblPaidBalance.setBounds(64, 696, 161, 29);
		panel_add_edit_products.add(lblPaidBalance);

		textField_paid_blnc = new JTextField();
		textField_paid_blnc.setText("0");
		textField_paid_blnc.setEditable(false);
		textField_paid_blnc.setEnabled(false);
		textField_paid_blnc.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_paid_blnc.setColumns(10);
		textField_paid_blnc.setBounds(237, 696, 342, 29);
		panel_add_edit_products.add(textField_paid_blnc);

		JLabel lblNetBalance = new JLabel(menu_add_prod_paymnt_info_screen_content
				.get(ScreenContentTypes.prod_payment_info_net_blnc.toString()).getTitle());
		lblNetBalance.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNetBalance.setBounds(64, 745, 161, 29);
		panel_add_edit_products.add(lblNetBalance);

		textField_net_balance = new JTextField();
		textField_net_balance.setEditable(false);
		textField_net_balance.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_net_balance.setColumns(10);
		textField_net_balance.setBounds(237, 745, 342, 29);
		panel_add_edit_products.add(textField_net_balance);

		JButton btnSaveProduct = new JButton("Save");
		btnSaveProduct.setBounds(482, 801, 97, 25);
		panel_add_edit_products.add(btnSaveProduct);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(211, 211, 211));
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBackground(new Color(128, 128, 128));
		separator_3.setBounds(715, 179, 2, 500);
		panel_add_edit_products.add(separator_3);

		JLabel labelEditProduct = new JLabel(
				menu_add_prod_screen.get(ScreenTypes.prod_add_product.toString()).getTitle());
		labelEditProduct.setFont(new Font("Calibri", Font.BOLD, 33));
		labelEditProduct.setBounds(849, 13, 213, 53);
		panel_add_edit_products.add(labelEditProduct);

		JLabel labelSelectProd = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_payment_edit_prod_selct_prod.toString()).getTitle());
		labelSelectProd.setFont(new Font("Calibri", Font.PLAIN, 22));
		labelSelectProd.setBounds(901, 89, 161, 29);
		panel_add_edit_products.add(labelSelectProd);

		combobox_prod = new JComboBox<>();
		combobox_prod.setBounds(1074, 86, 342, 32);
		combobox_prod.setRenderer(new ItemRendererForProducts());
		panel_add_edit_products.add(combobox_prod);
		addingElementsToComboBox_prod(combobox_prod);
		addingItemListenerOnEditProductComboBox(combobox_prod);
		combobox_prod.setSelectedIndex(-1);

		JLabel lblNewName = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_prod_name.toString()).getTitle());
		lblNewName.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNewName.setBounds(901, 142, 161, 29);
		panel_add_edit_products.add(lblNewName);

		JLabel lblNewQuantity = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_prod_quant.toString()).getTitle());
		lblNewQuantity.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNewQuantity.setBounds(901, 195, 161, 29);
		panel_add_edit_products.add(lblNewQuantity);

		textField_prod_name_edit = new JTextField();
		textField_prod_name_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_prod_name_edit.setColumns(10);
		textField_prod_name_edit.setBounds(1074, 142, 342, 29);
		panel_add_edit_products.add(textField_prod_name_edit);

		textField_prod_quantity_edit = new JTextField();
		textField_prod_quantity_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_prod_quantity_edit.setColumns(10);
		textField_prod_quantity_edit.setBounds(1074, 195, 342, 29);
		panel_add_edit_products.add(textField_prod_quantity_edit);

		textField_bar_code_edit = new JTextField();
		textField_bar_code_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_bar_code_edit.setColumns(10);
		textField_bar_code_edit.setBounds(1074, 248, 342, 29);
		panel_add_edit_products.add(textField_bar_code_edit);

		JLabel lblNewBcode = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_bar_cod.toString()).getTitle());
		lblNewBcode.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNewBcode.setBounds(901, 248, 161, 29);
		panel_add_edit_products.add(lblNewBcode);

		JLabel lblPrice_1 = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_price.toString()).getTitle());
		lblPrice_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblPrice_1.setBounds(901, 294, 84, 29);
		panel_add_edit_products.add(lblPrice_1);

		textField_price_edit = new JTextField();
		textField_price_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_price_edit.setColumns(10);
		textField_price_edit.setBounds(986, 294, 161, 29);
		panel_add_edit_products.add(textField_price_edit);

		JLabel lblTax_1 = new JLabel(menu_add_prod_add_prod_screen_content
				.get(ScreenContentTypes.prod_add_product_tax.toString()).getTitle());
		lblTax_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblTax_1.setBounds(1159, 294, 72, 29);
		panel_add_edit_products.add(lblTax_1);

		textField_tax_edit = new JTextField();
		textField_tax_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_tax_edit.setColumns(10);
		textField_tax_edit.setBounds(1234, 294, 182, 29);
		panel_add_edit_products.add(textField_tax_edit);

		JLabel lblEditSupplier = new JLabel(
				menu_add_prod_screen.get(ScreenTypes.prod_supplier_info.toString()).getTitle());
		lblEditSupplier.setFont(new Font("Calibri", Font.BOLD, 33));
		lblEditSupplier.setBounds(849, 347, 213, 53);
		panel_add_edit_products.add(lblEditSupplier);

		JLabel lblEditProductCompany = new JLabel(menu_add_prod_suplir_info_screen_content
				.get(ScreenContentTypes.prod_supplier_info_prod_cmpny.toString()).getTitle());
		lblEditProductCompany.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblEditProductCompany.setBounds(901, 413, 161, 29);
		panel_add_edit_products.add(lblEditProductCompany);

		JLabel label_lblSup_edit = new JLabel(menu_add_prod_suplir_info_screen_content
				.get(ScreenContentTypes.prod_supplier_info_splier_name.toString()).getTitle());
		label_lblSup_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		label_lblSup_edit.setBounds(901, 466, 161, 29);
		panel_add_edit_products.add(label_lblSup_edit);

		JLabel label_lblSupplierContact_edit = new JLabel(menu_add_prod_suplir_info_screen_content
				.get(ScreenContentTypes.prod_supplier_info_splier_cntct.toString()).getTitle());
		label_lblSupplierContact_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		label_lblSupplierContact_edit.setBounds(901, 521, 161, 29);
		panel_add_edit_products.add(label_lblSupplierContact_edit);

		JLabel label_lblSupplierAccount_edit = new JLabel(menu_add_prod_suplir_info_screen_content
				.get(ScreenContentTypes.prod_supplier_info_splier_acnt.toString()).getTitle());
		label_lblSupplierAccount_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		label_lblSupplierAccount_edit.setBounds(901, 574, 161, 29);
		panel_add_edit_products.add(label_lblSupplierAccount_edit);

		textField_EditProductCompany = new JTextField();
		textField_EditProductCompany.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_EditProductCompany.setColumns(10);
		textField_EditProductCompany.setBounds(1074, 413, 342, 29);
		panel_add_edit_products.add(textField_EditProductCompany);

		textField_Sup_edit = new JTextField();
		textField_Sup_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_Sup_edit.setColumns(10);
		textField_Sup_edit.setBounds(1074, 466, 342, 29);
		panel_add_edit_products.add(textField_Sup_edit);

		textField_SupplierContact_edit = new JTextField();
		textField_SupplierContact_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_SupplierContact_edit.setColumns(10);
		textField_SupplierContact_edit.setBounds(1074, 521, 342, 29);
		panel_add_edit_products.add(textField_SupplierContact_edit);

		textField_SupplierAccount_edit = new JTextField();
		textField_SupplierAccount_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_SupplierAccount_edit.setColumns(10);
		textField_SupplierAccount_edit.setBounds(1074, 574, 342, 29);
		panel_add_edit_products.add(textField_SupplierAccount_edit);

		JLabel lblEditPayment = new JLabel(
				menu_add_prod_screen.get(ScreenTypes.prod_payment_info.toString()).getTitle());
		lblEditPayment.setFont(new Font("Calibri", Font.BOLD, 33));
		lblEditPayment.setBounds(849, 626, 266, 53);
		panel_add_edit_products.add(lblEditPayment);

		JLabel lblPaymentMethod_edit = new JLabel(menu_add_prod_paymnt_info_screen_content
				.get(ScreenContentTypes.prod_payment_info_paymnt_mthds.toString()).getTitle());
		lblPaymentMethod_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblPaymentMethod_edit.setBounds(901, 687, 161, 29);
		panel_add_edit_products.add(lblPaymentMethod_edit);

		JLabel lblPaidBalance_edit = new JLabel(menu_add_prod_paymnt_info_screen_content
				.get(ScreenContentTypes.prod_payment_info_paid_blnc.toString()).getTitle());
		lblPaidBalance_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblPaidBalance_edit.setBounds(901, 739, 161, 29);
		panel_add_edit_products.add(lblPaidBalance_edit);

		JLabel lblNetBalance_edit = new JLabel(menu_add_prod_paymnt_info_screen_content
				.get(ScreenContentTypes.prod_payment_info_net_blnc.toString()).getTitle());
		lblNetBalance_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNetBalance_edit.setBounds(901, 788, 161, 29);
		panel_add_edit_products.add(lblNetBalance_edit);

		textField_net_balance_edit = new JTextField();
		textField_net_balance_edit.setEditable(false);
		textField_net_balance_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_net_balance_edit.setColumns(10);
		textField_net_balance_edit.setBounds(1074, 788, 342, 29);
		panel_add_edit_products.add(textField_net_balance_edit);

		textField_paid_blnc_edit = new JTextField();
		textField_paid_blnc_edit.setFont(new Font("Calibri", Font.PLAIN, 22));
		textField_paid_blnc_edit.setColumns(10);
		textField_paid_blnc_edit.setBounds(1074, 739, 342, 29);
		panel_add_edit_products.add(textField_paid_blnc_edit);

		comboBox_paymentMethod_edit = new JComboBox<String>();
		addingPaymentMethods(comboBox_paymentMethod_edit);
		comboBox_paymentMethod_edit.setBounds(1074, 684, 342, 32);
		panel_add_edit_products.add(comboBox_paymentMethod_edit);

		JButton btnUpdate_prod = new JButton("update");
		btnUpdate_prod.setBounds(1329, 841, 97, 25);
		panel_add_edit_products.add(btnUpdate_prod);

		/************ Helper methods ***************/

		addingWatchListenerOnTextPaidBlnc(textField_paid_blnc, textField_price, textField_tax, textField_net_balance);
		addingWatchListenerOnTextPaidBlnc(textField_paid_blnc_edit, textField_price_edit, textField_tax_edit,
				textField_net_balance_edit);

		JButton btnDeleteProd = new JButton("Delete");
		btnDeleteProd.setBounds(1220, 841, 97, 25);
		panel_add_edit_products.add(btnDeleteProd);

		JButton btnDeleteAllProd = new JButton("Delete All");
		btnDeleteAllProd.setBounds(1111, 841, 97, 25);
		panel_add_edit_products.add(btnDeleteAllProd);

		/********************* adding action listeners *************************/

		btnDeleteAllProd.addActionListener(da -> {
			deleteAllProducts();
		});

		btnDeleteProd.addActionListener((d) -> {
			deleteProduct();
		});

		btnUpdate_prod.addActionListener((p) -> {
			updateProduct();
		});

		btnSaveProduct.addActionListener((t) -> {
			saveProduct();
		});

	}

	private void deleteAllProducts() {
		try {
			String password = JOptionPane.showInputDialog(frame, "For delete all products enter your password?",
					"Security alert", JOptionPane.WARNING_MESSAGE);
			if (ContentHelper.getInstance().getAuthModel().getPassword().equals(password)) {
				ContentHelper.getInstance().deleteAllSaleLogs();
				int id = ContentHelper.getInstance().deleteProducts();
				if (!AppUtils.isNullOrZeroInteger(id)) {
					ProductModel productModel = new ProductModel();
					productModel.setId(id);
					JOptionPane.showMessageDialog(frame, "Products deleted successfully!!!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					saveProdsLogs(AppUtils.DELETE_ALL_STATUS, productModel);
					addingElementsToComboBox_prod(combobox_prod);
					resetFiledsOfEditProdScreen();
				} else {
					JOptionPane.showMessageDialog(frame, "Product not deleted successfully!!!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Wrong password entered!!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void deleteProduct() {
		String password = JOptionPane.showInputDialog(frame, "For delete all products enter your password?",
				"Security alert", JOptionPane.WARNING_MESSAGE);
		if (ContentHelper.getInstance().getAuthModel().getPassword().equals(password)) {
			ProductModel productModel = new ProductModel();
			productModel.setId(((ProductModel) combobox_prod.getSelectedItem()).getId());
			try {
				ContentHelper.getInstance().deleteSaleByProdId(productModel.getId());
				int id = ContentHelper.getInstance().deleteProductById(productModel);
				if (id == 1) {
					JOptionPane.showMessageDialog(frame, "Product deleted successfully!!!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					saveProdsLogs(AppUtils.DELETE_STATUS, productModel);
					addingElementsToComboBox_prod(combobox_prod);
					resetFiledsOfEditProdScreen();
				} else {
					JOptionPane.showMessageDialog(frame, "Product not deleted successfully!!!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} catch (DBException | IllegalArgumentException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Password is not valid!!!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void notifyLoginApproval(boolean isSuccess) {
		if (!isSuccess) {

		} else {
			setUpClientApp();
		}
	}

	private void setUpClientApp() {
		Thread t1 = new Thread(() -> {
			try {
				ContentHelper.getInstance().initContents();
			} catch (DBException | IllegalArgumentException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
						"Error", JOptionPane.ERROR_MESSAGE);
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
				intializeData();
				initialize();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage() + " [" + e.getMessage() + "]",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}).start();
	}

	class DateLabelFormatter extends AbstractFormatter {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1583227751459293351L;
		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}

	class ItemRendererForClients extends BasicComboBoxRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1616966464656301615L;

		public Component getListCellRendererComponent(JList<Object> list, ClientModel value, int index,
				boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				setText(value.getName());
			}
			if (index == -1) {
				setText(value.getName());
			}
			return this;
		}
	}
}
