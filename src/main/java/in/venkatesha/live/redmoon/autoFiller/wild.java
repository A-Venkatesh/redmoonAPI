/**
 * 
 */
package in.venkatesha.live.redmoon.autoFiller;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.commons.net.util.SSLSocketUtils;
import org.apache.xpath.operations.Bool;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import in.venkatesha.live.redmoon.models.Book;

/**
 * @author Anbu
 *
 */
public class wild {

	/**
	 * 
	 */
	public ArrayList<Book> fromISBNdB(String URL) {
		// TODO Auto-generated constructor stub
		HtmlPage hp =	getHTMLofPage(URL);
		System.out.println("came here");
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {
			String[] pageHtml = hp.asXml().split("\n");
			int index = 0, endIndex = pageHtml.length;
			while (index != endIndex) {
				System.out.println(endIndex);
				String line = pageHtml[index];
				Book book = new Book();

				while (!line.contains("search-result-title")) {
					index++;
					line = pageHtml[index];
				}

				index++;
				line = pageHtml[index];
				book.setUrl(line);
				index++;
				line = pageHtml[index];
				book.setTitle(line);
				System.out.println(book.getUrl());
				System.out.println(book.getTitle());

				while (!line.contains("Authors:")) {
					index++;
					line = pageHtml[index];
				}
				index += 3;
				line = pageHtml[index];
				book.setAuthors(line);
				System.out.println(book.getAuthors());

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
						System.out.println(index);
						line = pageHtml[index];
					}
					index += 2;
					line = pageHtml[index];
					book.setBinding(line);

				} catch (Exception e) {
					// TODO: handle exception
					book.setBinding("");
				}

				System.out.println(book.getPublisher());
				System.out.println(book.getBinding());
				bookList.add(book);

			} 
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		System.out.println(bookList);
		return bookList;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ArrayList<Book> list = new wild().fromISBNdB("https://isbndb.com/search/books/moomin%20lift");
		try {
			Book book = new wild().fromAmazon("https://www.amazon.in/s?k=Moomin%27s+Lift-The-Flap+Hide+and+Seek%3A+with+Big+Flaps+for+Little+Hands&i=stripbooks&ref=nb_sb_noss");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				

	
		}
	
	private Book fromAmazon(String URL) throws IOException {
		// TODO Auto-generated method stub
		HtmlPage hp =	getHTMLofPage(URL);
		
		System.out.println(hp.asXml());
		BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\log.txt"));
		bw.write(hp.asXml());
		bw.close();
		return null;
	}

	HtmlPage getHTMLofPage(String searchQuery){
		// = "Iphone 6s" ;
		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  
		try {  
		  String searchUrl;
		 // searchUrl = URLEncoder.encode(searchQuery, "UTF-8");
		  searchUrl = searchQuery;
		  System.out.println(searchUrl);
		  //searchUrl = "https://newyork.craigslist.org/search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");
		  //searchUrl = "https://www.amazon.in/Hello-World-Hannah-Fry/dp/0857525255/ref=sr_1_1?crid=25DEXCVBLOT99&keywords=hello+world&qid=1573914060&s=books&sprefix=hello%2Caps%2C425&sr=1-1";
		  HtmlPage page = client.getPage(searchUrl);
		  client.close();
		  return page;
		  
		}catch(Exception e){
		  e.printStackTrace();
		  return null;
		}
		
		
	}
	

}
