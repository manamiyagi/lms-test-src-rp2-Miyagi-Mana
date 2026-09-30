package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		//上部メニューの「機能」プルダウンを開く
		WebElement pullDown = webDriver.findElement(By.linkText("機能"));
		pullDown.click();

		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//開いたプルダウンの中にある「ヘルプ」をクリック
		WebElement helpLink = webDriver.findElement(By.linkText("ヘルプ"));
		helpLink.click();

		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//遷移後の画面のタイトルを確認
		assertEquals("ヘルプ | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		//「よくある質問」をクリックし別タブが開く
		WebElement qesLink = webDriver.findElement(By.linkText("よくある質問"));
		qesLink.click();

		//別タブに切り替える
		String tab = webDriver.getWindowHandle();

		for (String tabs : webDriver.getWindowHandles()) {
			if (!tabs.equals(tab)) {
				webDriver.switchTo().window(tabs);
				break;
			}
		}
		//画面遷移が完了するまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//遷移後の画面のタイトルを確認
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		// カテゴリ内で検索するリンク
		String category = "【研修関係】";

		//カテゴリのリンクをクリック
		WebElement categoryLink = webDriver.findElement(By.linkText(category));
		categoryLink.click();

		//結果が表示されるまで1秒待機
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//結果を取得
		List<WebElement> resultList = webDriver.findElements(By.cssSelector("tbody tr"));

		assertEquals(2, resultList.size());

		// 検索結果の質問文を取得
		List<String> questions = resultList.stream().map(result -> result.findElement(
				By.cssSelector("dt")).getText()).toList();

		// 期待する質問が表示されていることを確認
		assertTrue(questions.contains("Q.キャンセル料・途中退校について"));
		assertTrue(questions.contains("Q.研修の申し込みはどのようにすれば良いですか？"));

		//タイトルを確認
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		//キャンセル料・途中退校についてを取得
		WebElement question = webDriver.findElement(
				By.xpath("//dt[contains(., 'キャンセル料・途中退校について')]"));

		//質問をクリックし、解答を取得
		question.click();
		WebElement ans = question.findElement(By.xpath("./following-sibling::dd"));

		//解答が表示されており、空欄ではないことを確認
		assertTrue(ans.isDisplayed());
		assertFalse(ans.getText().isEmpty());

		//タイトルを確認
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {

		});
	}

}
