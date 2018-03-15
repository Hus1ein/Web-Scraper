package com.hussainabdelilah.springbootwebscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Component
public class HelperFunctions {

    public String getHTMLFromURL(String url){
        String html;
        try {
            Document doc = Jsoup.connect(url).get();
            html = doc.outerHtml();
            return html;
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return "error";
        }
    }

    public Map<String, Object> deleteURLFromJSON (Map<String, Object> def){
        // Deleting URL from JSON object.
        for(Iterator<Map.Entry<String, Object>> it = def.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> entry = it.next();
            if(entry.getKey().equals("url")) {
                it.remove();
            }
        }
        return def;
    }

    public String getURLFromJSON(Map<String, Object> def) {
        // Getting URL from JSON object.
        Map.Entry<String, Object> entry = def.entrySet().iterator().next();
        String url = entry.getValue().toString();
        return url;
    }

}
