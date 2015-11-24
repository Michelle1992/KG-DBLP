package startingPoint.KG_DBLP;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder;
	Document document = null;
	NodeList nodeList;
	ArrayList<ArrayList<String>> articleList = new ArrayList<>();
	
	public XmlParser() {
		try {
			builder = factory.newDocumentBuilder();
			String filePath = "/Users/Michelle/Documents/CMU/courses/18656WorkFlow/Homework/18656FinalProject/KG-DBLP/src/input.xml";
			document = builder.parse(new File(filePath));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		document.getDocumentElement().normalize();
		nodeList = document.getDocumentElement().getElementsByTagName("article");
	}
	
	public void getXml2List() throws IOException {
		PrintWriter writer = new PrintWriter("outputPub.csv", "UTF-8");
		Node articleNode;
		for(int i=0; i<nodeList.getLength(); i++) {
			ArrayList<String> singleArticle = new ArrayList<>();
			for(int count=0; count<=11; count++) {
				singleArticle.add("NULL");
			}
			articleNode = nodeList.item(i);
			singleArticle.set(0, String.valueOf(i));
			singleArticle.set(2,articleNode.getAttributes().getNamedItem("mdate").getTextContent());
			singleArticle.set(3,articleNode.getAttributes().getNamedItem("key").getTextContent());
			NodeList childNodes = articleNode.getChildNodes();
			StringBuilder author = new StringBuilder();
			for(int j=0; j<childNodes.getLength(); j++) {
				Node curNode = childNodes.item(j);
				if(curNode.getNodeName().equals("author")) {
					//String tmp = "`" + curNode.getTextContent() + "`";
					author.append(curNode.getTextContent()+"\t");
				}
				else if(curNode.getNodeName().equals("title")) {
					//String tmp = "`" + curNode.getTextContent() + "`";
					singleArticle.set(1, curNode.getTextContent());
				}
				else if(curNode.getNodeName().equals("pages")) {
					singleArticle.set(5, curNode.getTextContent());
				}
				else if(curNode.getNodeName().equals("year")) {
					singleArticle.set(6, curNode.getTextContent());
				}
				else if(curNode.getNodeName().equals("volume")) {
					singleArticle.set(7, curNode.getTextContent());
				}
				else if(curNode.getNodeName().equals("journal")) {
					singleArticle.set(8, curNode.getTextContent());
				}
				else if(curNode.getNodeName().equals("number")) {
					singleArticle.set(9, curNode.getTextContent());
				}
				else if(curNode.getNodeName().equals("url")) {
					singleArticle.set(10, curNode.getTextContent());
				}
				else if(curNode.getNodeName().equals("ee")) {
					singleArticle.set(11, curNode.getTextContent());
				}
			}
			author.deleteCharAt(author.length()-1);
			singleArticle.set(4,author.toString());
			articleList.add(singleArticle);
			StringBuilder sb = new StringBuilder();
			sb.append(singleArticle.get(0));
			sb.append("%");
			sb.append(singleArticle.get(1));
			sb.append("%");
			sb.append(singleArticle.get(2));
			sb.append("%");
			sb.append(singleArticle.get(3));
			sb.append("%");
			sb.append(singleArticle.get(4));
			sb.append("%");
			sb.append(singleArticle.get(5));
			sb.append("%");
			sb.append(singleArticle.get(6));
			sb.append("%");
			sb.append(singleArticle.get(7));
			sb.append("%");
			sb.append(singleArticle.get(8));
			sb.append("%");
			sb.append(singleArticle.get(9));
			sb.append("%");
			sb.append(singleArticle.get(10));
			sb.append("%");
			sb.append(singleArticle.get(11));
			//sb.append("%");
			writer.println(sb.toString());
		}
		
		writer.close();
	}
	
	public static void main(String args[]) throws IOException {
		XmlParser parser = new XmlParser();
		parser.getXml2List();
//		System.out.println(parser.articleList.get(2).size());
//		for(String s: parser.articleList.get(2)) {
//			System.out.println(s);
//		}
	}
	
}

