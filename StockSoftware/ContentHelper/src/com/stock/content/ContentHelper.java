package com.stock.content;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.db.Exception.DBException;
import com.db.business.applicationbusiness.ApplicationBusiness;
import com.db.business.authentication.AuthBusiness;
import com.db.business.client.ClientBusiness;
import com.db.business.footerbusiness.FooterBusiness;
import com.db.business.headerbusiness.HeaderBusiness;
import com.db.business.menubusiness.MenuBusiness;
import com.db.business.product.ProductBusiness;
import com.db.business.salebusiness.SaleBusiness;
import com.db.business.screencontentbusiness.ScreenContentBusiness;
import com.db.business.screens.ScreensBusiness;
import com.db.business.userbusiness.UserBusiness;
import com.db.business.userclientrelationlogsbusiness.UserClientRelationBusiness;
import com.db.business.userprodrelationbusiness.UserProdRelationBusiness;
import com.db.models.SaleModel;
import com.db.models.app.contents.ApplicationModel;
import com.db.models.app.contents.FooterModel;
import com.db.models.app.contents.HeaderModel;
import com.db.models.authentication.AuthModel;
import com.db.models.client.ClientModel;
import com.db.models.menus.MenuModel;
import com.db.models.product.ProductModel;
import com.db.models.screencontents.ScreenContentModel;
import com.db.models.screens.ScreenModel;
import com.db.models.userclientrelationlogsmodel.UserClientRelationLogsModel;
import com.db.models.userprodrelation.UserProdRelationModel;
import com.db.models.users.UserModel;
import com.db.utility.AppUtils;

public class ContentHelper {

	private ApplicationBusiness applicationBusiness = new ApplicationBusiness();
	private MenuBusiness menuBusiness = new MenuBusiness();
	private FooterBusiness footerBusiness = new FooterBusiness();
	private HeaderBusiness headerBusiness = new HeaderBusiness();
	private ScreensBusiness screensBusiness = new ScreensBusiness();
	private ScreenContentBusiness screenContentBusiness = new ScreenContentBusiness();
	private ProductBusiness productBusiness = new ProductBusiness();
	@Deprecated
	private UserBusiness userBusiness = new UserBusiness();
	private AuthBusiness authBusiness = new AuthBusiness();
	private UserProdRelationBusiness userProdRelationBusiness = new UserProdRelationBusiness();
	private ClientBusiness clientBusiness = new ClientBusiness();
	private UserClientRelationBusiness userClientRelationBusiness = new UserClientRelationBusiness();
	private SaleBusiness saleBusiness = new SaleBusiness();

	private ApplicationModel applicationModel;
	private FooterModel footerModel;
	private HeaderModel headerModel;
	private Map<String, MenuModel> menus;
	private Map<String, ScreenModel> screens;
	private Map<String, ScreenContentModel> screenContentModel;
	@Deprecated
	private UserModel user;
	private AuthModel authModel;
	private static ContentHelper contentLoader = null;

	private ContentHelper() {

	}

	public void initContents() throws IllegalArgumentException, DBException {
		applicationModel = applicationBusiness.getApplication();

		if (!Objects.isNull(applicationModel)) {
			menus = menuBusiness.getAllMenus();
			footerModel = footerBusiness.getFooter();
			headerModel = headerBusiness.getHeader();
			if (!menus.isEmpty())
				screens = screensBusiness.getScreensByMenuId(menus.get(menus.keySet().iterator().next()).getId());
			if (!screens.isEmpty())
				screenContentModel = screenContentBusiness
						.getScreenContentsByMenuId(screens.get(screens.keySet().iterator().next()).getId());

		}
	}

	public Map<String, ScreenModel> getScreens() {
		return screens;
	}

	public Map<String, ScreenContentModel> getScreenContentModel() {
		return screenContentModel;
	}

	public Map<String, ScreenContentModel> getScreenContentModelByScreenId(int id)
			throws IllegalArgumentException, DBException {
		return screenContentBusiness.getScreenContentsByMenuId(id);
	}

	public void refreshScreens(int menu_id) throws IllegalArgumentException, DBException {
		screens = screensBusiness.getScreensByMenuId(menu_id);
	}

	public Map<String, ScreenModel> getScreensByMenuId(int menu_id) throws IllegalArgumentException, DBException {
		return screensBusiness.getScreensByMenuId(menu_id);
	}

	public ProductModel getProductByProdId(ProductModel prod) throws IllegalArgumentException, DBException {
		return productBusiness.getProductById(prod);
	}

	public void refreshApplication() throws IllegalArgumentException, DBException {
		applicationModel = applicationBusiness.getApplication();
	}

	public void refreshHeader() throws IllegalArgumentException, DBException {
		headerModel = headerBusiness.getHeader();
	}

	public void refreshFooter() throws IllegalArgumentException, DBException {
		footerModel = footerBusiness.getFooter();
	}

	public void refreshMenus() throws IllegalArgumentException, DBException {
		menus = menuBusiness.getAllMenus();
	}

	public void refreshScreentContents(int screen_id) throws IllegalArgumentException, DBException {
		screenContentModel = screenContentBusiness.getScreenContentsByMenuId(screen_id);
	}

	public Map<String, MenuModel> getMenus() {
		return menus;
	}

	public HeaderModel getHeaderModel() {
		return headerModel;
	}

	public ApplicationModel getApplicationModel() {
		return applicationModel;
	}

	public FooterModel getFooterModel() {
		return footerModel;
	}

	public AuthModel getAuthModel() {
		return authModel;
	}

	public ScreenContentModel updateScreenContent(ScreenContentModel screenContentModel)
			throws IllegalArgumentException, DBException {
		return screenContentBusiness.updateScreenContentModel(screenContentModel);
	}

	public int updateApplication(ApplicationModel applicationModel) throws IllegalArgumentException, DBException {
		return applicationBusiness.updateApplicationModel(applicationModel);
	}

	public ScreenModel updateScreen(ScreenModel screenModel) throws IllegalArgumentException, DBException {
		return screensBusiness.updateScreenModel(screenModel);
	}

	public int updateMenu(MenuModel menu) throws IllegalArgumentException, DBException {
		return menuBusiness.updateMenu(menu);
	}

	public HeaderModel updateHeader(HeaderModel model) throws IllegalArgumentException, DBException {
		return headerBusiness.updateHeader(model);
	}

	public FooterModel updateFooter(FooterModel footerModel) throws IllegalArgumentException, DBException {
		return footerBusiness.update(footerModel);
	}

	public List<ProductModel> getAllProducts() throws IllegalArgumentException, DBException {
		return productBusiness.getALLProducts();
	}

	public List<ClientModel> getAllClients() throws DBException {
		return clientBusiness.getAllClients();
	}

	public ProductModel saveProducts(ProductModel productModel) throws IllegalArgumentException, DBException {
		return productBusiness.insertProduct(productModel);
	}

	public ProductModel updateProducts(ProductModel productModel) throws IllegalArgumentException, DBException {
		return productBusiness.updateProduct(productModel);
	}

	public int deleteProductById(ProductModel productModel) throws DBException {
		return productBusiness.deleteProductById(productModel);
	}

	public int deleteProducts() throws DBException {
		return productBusiness.deleteProducts();
	}

	public List<UserProdRelationModel> getAllProdLogs(int pageIndex) throws DBException {
		return userProdRelationBusiness.getAllProdLogs(pageIndex);
	}

	public List<SaleModel> getAllSaleStats(int page) throws DBException {
		return saleBusiness.getAllSalePaggination(page);
	}

	public boolean isValidLoginAuth(AuthModel authModel) throws DBException {
		this.authModel = authBusiness.selectByUserNameAndPassword(authModel);
		if (AppUtils.isNull(this.authModel))
			return false;
		return true;
	}

	public AuthModel insertAuth(AuthModel authModel) throws DBException {
		return authBusiness.insert(authModel);
	}

	public int insertUserProdRelationLogs(UserProdRelationModel userProdRelationModel) throws DBException {
		return userProdRelationBusiness.inserLogs(userProdRelationModel);
	}

	@Deprecated
	public boolean isValidLogin(UserModel userModel) throws DBException {
		user = userBusiness.getUserByIdAndName(userModel);
		if (AppUtils.isNull(user))
			return false;
		return true;
	}

	@Deprecated
	public UserModel getUser() {
		return user;
	}

	public int deleteProdsLogsAll() throws DBException {
		return userProdRelationBusiness.deleteAll();
	}

	public int deleteAllClientsLogs() throws DBException {
		return userClientRelationBusiness.deleteAll();
	}

	public int deleteAllSaleLogs() throws DBException {
		return saleBusiness.deleteAll();
	}

	public ClientModel insertClient(ClientModel clientModel) throws DBException {
		return clientBusiness.inserClient(clientModel);
	}

	public ClientModel updateClient(ClientModel clientModel) throws DBException {
		return clientBusiness.updateClient(clientModel);
	}

	public List<SaleModel> getAllSalePagginationForDays(int page, List<String> dates) throws DBException {
		return saleBusiness.getAllSalePagginationForDays(page, dates);
	}

	public int deleteAllSale() throws DBException {
		return saleBusiness.deleteAll();
	}

	public ClientModel deleteClientById(ClientModel clientModel) throws DBException {
		return clientBusiness.deleteById(clientModel);
	}

	public int deleteAllClients() throws DBException {
		return clientBusiness.deleteAll();
	}

	public UserClientRelationLogsModel insertUserClientRelationLogs(UserClientRelationLogsModel logs)
			throws DBException {
		return userClientRelationBusiness.insertLogs(logs);
	}

	public List<UserClientRelationLogsModel> getClientLogs(int page) throws DBException {
		return userClientRelationBusiness.getAllLogs(page);
	}

	public List<ProductModel> getALLProductsLike(ProductModel productModel) throws DBException {
		return productBusiness.getALLProductsLike(productModel);
	}

	public List<ClientModel> getAllClientsLike(ClientModel clientModel) throws DBException {
		return clientBusiness.getAllClientsLike(clientModel);
	}

	public int deleteSaleByProdId(int prodId) throws DBException {
		return saleBusiness.deleteByProdId(prodId);
	}

	public int deleteSaleByClientId(int clientId) throws DBException {
		return saleBusiness.deleteByClientId(clientId);
	}

	public SaleModel inserSale(SaleModel model) throws DBException {
		return saleBusiness.inserSale(model);
	}

	public static ContentHelper getInstance() {
		if (Objects.isNull(contentLoader)) {
			synchronized (ContentHelper.class) {
				if (Objects.isNull(contentLoader)) {
					contentLoader = new ContentHelper();

				}
			}

		}
		return contentLoader;
	}

}
