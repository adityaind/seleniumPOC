package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
import pageobjects.CartPage;
import pageobjects.OrderPage;
import pageobjects.ProductCatalog;
import pageobjects.ProductDescPage;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider= "getData")
	public void regression(HashMap<String,String> input) throws IOException {
		ProductCatalog pc = lp.loginOnWeb(input.get("username"), input.get("password"));
		ProductDescPage pdp = pc.clickOnDesiredProduct(input.get("product"));
		CartPage cp = pdp.clickAddToCart();
		Boolean match = cp.verifyProductInCart(input.get("product"));
		Assert.assertTrue(match);
		OrderPage op = cp.buyButtonClick();
		op.enterDetails("Aditya", "Canada", "Kitchener", "459810007895", "09", "2028");
		String confMsg = op.getConfirmationMessage();
		Assert.assertEquals(confMsg, "Thank you for your purchase!");
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> values = getDataFromJson(
				System.getProperty("user.dir") + "\\src\\test\\java\\testdata\\testdata.json");
		return new Object[][] {{values.get(0)},{values.get(1)}};
	}
}
