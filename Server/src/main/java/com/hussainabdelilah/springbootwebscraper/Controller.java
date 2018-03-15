package com.hussainabdelilah.springbootwebscraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/web")
public class Controller {

    private HelperFunctions helperFunctions;

    @Autowired
    public Controller(HelperFunctions helperFunctions){
        this.helperFunctions = helperFunctions;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public Map<String, Object> runApp(@RequestBody Map<String, Object> def){

        // Getting URL from JSON object
        String url = helperFunctions.getURLFromJSON(def);

        // Deleting URL from JSON object
        def = helperFunctions.deleteURLFromJSON(def);

        //Getting HTML from URL
        String html = helperFunctions.getHTMLFromURL(url);
        if(html.equals("error"))
        {
            System.out.println("Error!");
            return null;
        }

        //Calling run function
        IWebScraperExtractor iWebScraperExtractor = new WebScraperJsoupExtractorImpl(def);
        Map<String, Object> result = iWebScraperExtractor.run(html);
        return result;
    }
}
