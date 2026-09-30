package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		//トップページURLにアクセス
		goTo("http://localhost:8080/lms");

		//ログイン画面のタイトルを確認
		assertEquals("ログイン | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		//登録されているユーザー
		String user = "StudentAA01";
		String pass = "Miyagimana0918";

		//IDを入力
		WebElement inputId = webDriver.findElement(By.id("loginId"));
		inputId.sendKeys(user);

		//パスワードを入力
		WebElement inputPass = webDriver.findElement(By.id("password"));
		inputPass.sendKeys(pass);

		//ログインボタン押下
		WebElement loginbtn = webDriver.findElement(By.cssSelector(".btn-primary"));
		loginbtn.click();

		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//遷移後の画面のタイトルチェック
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// 未提出詳細ボタン押下
		WebElement detailButton = webDriver
				.findElement(By.xpath("//tr[contains(., '2022年10月2日(日)')]//input[@value='詳細']"));
		detailButton.click();

		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//遷移後の画面のタイトルチェック
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		//確認ボタンを押下
		WebElement conButton = webDriver.findElement(By.cssSelector("#sectionDetail table input[value*='週報']"));
		conButton.click();

		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//遷移後の画面のタイトルチェック
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		//内容入力
		WebElement text = webDriver.findElement(By.id("content_1"));
		text.clear();

		text.sendKeys("テストコード演習");

		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 提出ボタン押下
		WebElement subButton = webDriver.findElement(By.cssSelector("button.btn.btn-primary"));
		// スクロール
		scrollBy("300");
		subButton.click();

		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//遷移後の画面のタイトルチェック
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		//「ようこそ○○さん」押下
		WebElement welLink = webDriver.findElement(By.cssSelector(".navbar-right small"));
		welLink.click();

		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//遷移後の画面のタイトルチェック
		assertEquals("ユーザー詳細", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		//詳細を押下
		WebElement detailButton = webDriver.findElement(
				By.xpath("//tr[contains(., '週報【デモ】')]//input[@value='詳細']"));
		//スクロール
		scrollBy("800");
		detailButton.click();

		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//遷移後の画面のタイトルチェック
		assertEquals("レポート詳細 | LMS", webDriver.getTitle());

		//内容を確認
		WebElement report = webDriver.findElement(
				By.xpath("//h3[text()='報告レポート']/following-sibling::table[1]"));
		String textString = report.getText();
		assertTrue(textString.contains("テストコード演習"));

		//エビデンス取得
		getEvidence(new Object() {

		});
	}

}
