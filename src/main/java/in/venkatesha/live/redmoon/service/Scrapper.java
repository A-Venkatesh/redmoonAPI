/**
 * 
 */
package in.venkatesha.live.redmoon.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;
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
			if(!searchKey.contains("https://www.amazon.in")) {
			System.out.println("CMAE HERE"+amazonURL+amazonKey+URLEncoder.encode(searchKey, "UTF-8")+amazonSufix);
			
			json = new Scrapper().fromAmazon(amazonURL+amazonKey+URLEncoder.encode(searchKey, "UTF-8")+amazonSufix);
			}
			else {
				json = new Scrapper().contentPage(searchKey);
			}
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
	
	
	private String contentPage(String URL){
		HtmlPage hp = getHTMLofPage(URL);
		Book book = new Book();
		String price;
		try {
			price = hp.querySelector("span[class=\"a-size-medium a-color-price inlineBlock-display offer-price a-text-normal price3P\"]").asText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			price = hp.querySelector("span[id=\"a-autoid-8\"]").asText();
System.out.println(price);
			
		}
		//System.out.println(price.substring(price.indexOf('?')+2));
		book.setPrice(price.substring(price.indexOf('?')+2));
		String mrp;
		try {
			mrp = hp.querySelector("span[class=\"a-color-secondary a-text-strike\"]").asText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mrp = price;
		}
		book.setMrp(mrp.substring(mrp.indexOf('?')+2));
		
		
		String pd;
		
		try {
			pd= hp.querySelector("div[id=\"productDescription\"]").asXml();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			pd="";
					}
		//System.out.println(pd);
		book.setProductDescription(pd);
		
	
		//String cat = hp.querySelector("li[id=\"SalesRank\"]").asXml();
		//System.out.println(cat);
		//Node child = hp.querySelector("li[id=\"SalesRank\"]");
		DomNodeList<DomNode> dm = hp.querySelector("div[id=\"detail_bullets_id\"]").querySelectorAll("li");
		//String prod = hp.querySelector("div[id=\"detail_bullets_id\"]").asXml().replace(cat, "");
//		Node dm2 = dm.removeChild(dm.getLastChild());
//		System.out.println(dm.getLastChild().getTextContent());
		StringBuilder sb = new StringBuilder("<ul>");
		for(DomNode dmm : dm) {
			
			if(dmm.asText().contains("Average Customer"))break;
			else {
				//System.out.println(dmm.asXml());
				
				//System.out.println("**********************");
				sb.append(dmm.asXml());
			}
		}
		sb.append("</ul>");
		
		book.setProductDetail(sb.toString());
		
		String cat = hp.querySelector("div[id=\"detail_bullets_id\"]").querySelector("ul").querySelector("ul").asText();
		//System.out.println(cat);
		String cats[] = cat.split("\n");
		List<String> list = new ArrayList<String>();
		for(String temp:cats) {
			list.add(temp.substring(temp.indexOf("in")+2).trim());
		}
		
		book.setSegment(list);
		
		
//		DomNodeList<DomNode> de = hp.querySelector("div[id=\"imageBlockOuter\"]").querySelectorAll("img");
//		for(DomNode dee : de) {
//			//System.out.println(dee);
//		}
		
		DomNode h =hp.querySelector("div[id=\"booksImageBlock_feature_div\"]").querySelector("script");
		//System.out.println();
		String allcontent[] =h.asXml().split("\n");
		String ImageURL = "";
		for(String temp:allcontent) {
			if(temp.contains("mainUrl")) {
				ImageURL = temp.substring(temp.indexOf("["),temp.length() -1);
				System.out.println(ImageURL);
				Gson sd = new Gson();

				
			}
		}

		book.setImgUrl(ImageURL);

		System.out.println("--------------------------------------------------------------");
		String json = new GsonBuilder().disableHtmlEscaping().create().toJson(book);
		return json;
		
	}

}
