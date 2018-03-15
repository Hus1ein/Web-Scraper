package com.hussainabdelilah.springbootwebscraper;

import java.util.Map;


public interface IWebScraperExtractor {
    Map<String, Object> run(String inputData);
}
