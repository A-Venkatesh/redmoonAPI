/**
 * 
 */
package in.venkatesha.live.redmoon.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.GsonBuilder;

import in.venkatesha.live.redmoon.models.Book;

/**
 * @author Anbu
 *
 */
@Service
public class Scrapper implements ProductService {
static String amazonURL = "https://www.amazon.in";
static String amazonKey = "/s?k=";
static String amazonSufix ="&i=stripbooks&ref=nb_sb_noss_2";


@Override
	public String suggestion(String searchKey) {
		String json ="";
		try {
			System.out.println("CMAE HERE"+amazonURL+amazonKey+URLEncoder.encode(searchKey, "UTF-8")+amazonSufix);
			
			json = new Scrapper().fromAmazon(amazonURL+amazonKey+URLEncoder.encode(searchKey, "UTF-8")+amazonSufix);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
		
	}

	/**
	 * 
	 */
	public ArrayList<Book> fromISBNdB(String URL) {
		// TODO Auto-generated constructor stub
		HtmlPage hp = getHTMLofPage(URL);
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {
			String[] pageHtml = hp.asXml().split("\n");
			int index = 0, endIndex = pageHtml.length;
			while (index != endIndex) {
				String line = pageHtml[index];
				Book book = new Book();

				while (!line.contains("search-result-title")) {
					index++;
					line = pageHtml[index];
				}

				index++;
				line = pageHtml[index];
				book.setUrlISDB(line);
				index++;
				line = pageHtml[index];
				book.setTitle(line);

				while (!line.contains("Authors:")) {
					index++;
					line = pageHtml[index];
				}
				index += 3;
				line = pageHtml[index];
				book.setAuthors(line);

				while (!line.contains("/publisher/")) {
					index++;
					line = pageHtml[index];
				}
				index++;
				line = pageHtml[index];
				book.setPublisher(line);

				try {
					while (!line.contains("Binding:")) {
						index++;

						line = pageHtml[index];
					}
					index += 2;
					line = pageHtml[index];
					book.setBinding(line);

				} catch (Exception e) {
					// TODO: handle exception
					book.setBinding("");
				}
				bookList.add(book);

			}
		} catch (Exception e) {
			// TODO: handle exception

		}
		System.out.println(bookList);
		return bookList;

	}


	private String fromAmazon(String URL) throws IOException {
		// TODO Auto-generated method stub
		HtmlPage hp = getHTMLofPage(URL);
		ArrayList<Book> bookList = new ArrayList<Book>();
		System.out.println("CAME HERE DA");
		//System.out.println(hp.asXml());

		DomNodeList<DomNode> dnl = hp.querySelectorAll("div[data-asin]");
		for (DomNode dn : dnl) {

			Book book = new Book();

			// System.out.println(dn.asXml());

			book.setTitle(dn.querySelector("span[class=\"a-size-medium a-color-base a-text-normal\"]").asText());
			// System.out.println(dn.querySelector("span[class=\"a-size-medium a-color-base
			// a-text-normal\"]").asText());
			String bookURL = dn.querySelector("a[class=\"a-link-normal a-text-normal\"]").getAttributes()
					.getNamedItem("href").getNodeValue();
			bookURL = "https://www.amazon.in" + bookURL;
//System.out.println(bookURL);
			book.setUrl(bookURL);
			// System.out.println(dn.querySelector("div[class=\"a-row a-size-base
			// a-color-secondary\"]").asText());
			book.setAuthors(dn.querySelector("div[class=\"a-row a-size-base a-color-secondary\"]").asText());
			// System.out.println(dn.querySelector("div[class=\"a-section aok-relative
			// s-image-fixed-height\"]").querySelector("img").getAttributes().getNamedItem("src").getNodeValue());
			// .getChildNodes().get(0).getAttributes().getNamedItem("src").getNodeValue()
			book.setImgUrl(dn.querySelector("div[class=\"a-section aok-relative s-image-fixed-height\"]")
					.querySelector("img").getAttributes().getNamedItem("src").getNodeValue());

			bookList.add(book);
		}
		String json = new GsonBuilder().disableHtmlEscaping().create().toJson(bookList);
		return json;
	}

	HtmlPage getHTMLofPage(String searchQuery) {
		// = "Iphone 6s" ;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			String searchUrl;
			// searchUrl = URLEncoder.encode(searchQuery, "UTF-8");
			searchUrl = searchQuery;
			// System.out.println(searchUrl);
			// searchUrl = "https://newyork.craigslist.org/search/sss?sort=rel&query=" +
			// URLEncoder.encode(searchQuery, "UTF-8");
			// searchUrl =
			// "https://www.amazon.in/Hello-World-Hannah-Fry/dp/0857525255/ref=sr_1_1?crid=25DEXCVBLOT99&keywords=hello+world&qid=1573914060&s=books&sprefix=hello%2Caps%2C425&sr=1-1";
			HtmlPage page = client.getPage(searchUrl);
			client.close();
			return page;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
