package com.example.wikiterm;

public class WebSite {

    private String url;
    private String content;
    private String title;

    public WebSite(String url, String content, String title) {
        this.url = url;
        this.content = content;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getLowerCaseText() {
       // if ( this.lowerCaseText  == null ) this.lowerCaseText = this.text.toLowerCase();
        return content.toLowerCase();
    }

    public String getLowerCaseTitle() {
        // Implement logic to get the lowercase title of the website
        return title.toLowerCase();
    }

    // Add any other methods or attributes as needed for your application
}
