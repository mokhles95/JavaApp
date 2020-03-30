/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author q
 */
public class SkillsAPI {

    public static List<String>  getSkills() throws IOException {
        List<String> data = new ArrayList<>();
        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_programming_languages").get();
        Elements elements = doc.select(".columns > ul >li > a");
        for (Element liElement : elements) {
            String text = liElement.text();
            data.add(text);
        }
        return data;
    }
}
