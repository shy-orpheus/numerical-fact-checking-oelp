package com.example.wikiterm;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        String wikipediaUrl1 = "https://en.wikipedia.org/wiki/Mount_Everest";
        String wikipediaUrl2 = "https://en.wikipedia.org/wiki/Height";
        String HEIGHT = "8900 metre";

        System.out.println(HEIGHT);

        // Set a timeout value (in milliseconds) for the web request
        int timeout = 5000; // You can adjust the timeout as needed
        JsoupCrawlUtil crawlUtil = new JsoupCrawlUtil();
        stops stop_word = new stops();

        // Call the readPage method to fetch the text content from the Wikipedia pages
        String pageContent1 = crawlUtil.readPage(wikipediaUrl1, timeout);
        String pageContent2 = crawlUtil.readPage(wikipediaUrl2, timeout);

        // Get keywords for each page
        List<Word> words1 = WikipediaPageCrawler.getKeywordsSortedByFrequency(pageContent1);
        List<Word> words2 = WikipediaPageCrawler.getKeywordsSortedByFrequency(pageContent2);

        // Print the retrieved text content
        System.out.println("Keywords from " + wikipediaUrl1 + ": " + words1);
        System.out.println("Keywords from " + wikipediaUrl2 + ": " + words2);

        // Merge the top ten terms from each list
        List<Word> mergedResult = mergeTopTerms(words1, words2);
        //Word height = new Word("8848m", 1); // Assuming Word has a constructor that takes a String as an argument for the word
        //mergedResult.add(height);
        System.out.println("Merged Top Terms: " + mergedResult);

        // Consider user-provided websites
        List<WebSite> userWebsites = getUserProvidedWebsites();

        //Check if each term in the merged result is a topic term for the user-provided websites
       List<Word> topicTerms = filterTopicTerms(userWebsites, mergedResult);


       if(isQuantityPresent(userWebsites, HEIGHT)){
           Word qty = new Word( HEIGHT, 1); // Assuming Word has a constructor that takes a String as an argument for the word
           topicTerms.add(qty);
       }
       System.out.println("Filtered Topic Terms: " + topicTerms);
    }


    private static List<Word> mergeTopTerms(List<Word> list1, List<Word> list2) {
        // Merge the top ten terms from each list
        List<Word> mergedResult = list1.stream()
                .sorted(Comparator.comparingInt(Word::getFrequency).reversed())
                .limit(10)
                .collect(Collectors.toList());

        List<Word> topTermsList2 = list2.stream()
                .sorted(Comparator.comparingInt(Word::getFrequency).reversed())
                .limit(10)
                .collect(Collectors.toList());

        // Combine the lists without duplicates
        mergedResult.addAll(topTermsList2.stream()
                .filter(word -> mergedResult.stream().noneMatch(existingWord -> existingWord.getWord().equalsIgnoreCase(word.getWord())))
                .collect(Collectors.toList()));

        return mergedResult;
    }

   private static List<WebSite> getUserProvidedWebsites() {
        // Implement the logic to get user-provided websites
        // You can use a scanner or any other means to get the websites from the user
        // For this example, I'm creating dummy websites
        List<WebSite> userWebsites = new ArrayList<>();
       // Set a timeout value (in milliseconds) for the web request
       int timeout = 10000; // You can adjust the timeout as needed
       JsoupCrawlUtil crawlUtil = new JsoupCrawlUtil();
       stops stop_word = new stops();
     //  String pageContent1 = crawlUtil.read Page("", timeout);

       String pageContent1 = crawlUtil.readPage("https://www.toppr.com/ask/question/the-height-of-mount-everest-is-8848m-write-it-in-standard-from/", timeout);
       String pageContent2 = crawlUtil.readPage("https://www.toppr.com/ask/question/what-is-the-acceleration-due-to-gravity-on-the-top-of-mount-everest-mount/", timeout);
       String pageContent3 = crawlUtil.readPage("https://www.doubtnut.com/qna/642646389", timeout);
       String pageContent4 = crawlUtil.readPage("https://byjus.com/question-answer/what-is-the-height-above-sea-level-of-mt-everest-8848-m7848-m8048-m9848-m/", timeout);
       String pageContent5 = crawlUtil.readPage("https://www.thehindubusinessline.com/news/world/mount-everest-is-884886-metres-tall-say-nepal-and-china-after-remeasurement/article33281085.ece", timeout);

       // String pageContent1 = crawlUtil.readPage("http://www.nightporter.co.uk/pages/japan80.htm", timeout);


     //  userWebsites.add(new WebSite("https://www.york.ac.uk/physics-engineering-technology/about/newtons-apple-tree/#:~:text=Newton's%20discovery,is%20value%20in%20the%20story.", pageContent1.toLowerCase(), "Issac Newton's apple tree"));
       // userWebsites.add(new WebSite("http://www.nightporter.co.uk/pages/japan80.htm", pageContent1.toLowerCase(), "Nightporter Japan"));
      // userWebsites.add(new WebSite("", pageContent1.toLowerCase(), ""));

       userWebsites.add(new WebSite("https://www.toppr.com/ask/question/the-height-of-mount-everest-is-8848m-write-it-in-standard-from/", pageContent1.toLowerCase(), "The height of Mount Everest is 8848m .Write it in standard from. "));
       userWebsites.add(new WebSite("https://www.toppr.com/ask/question/what-is-the-acceleration-due-to-gravity-on-the-top-of-mount-everest-mount/", pageContent2.toLowerCase(), "What is the acceleration due to gravity on the top of Mount Everest ? Mount Everest is the highest mountain peak of the world at the height of 8848 m. The value at sea level is 9.80"));
       userWebsites.add(new WebSite("https://www.doubtnut.com/qna/642646389", pageContent3.toLowerCase(), "The Mount Everest is 8848 m above sea level. Estimate the acceleration due to gravity at this height, given that mean g on the surface of the earth is 9.8"));
       userWebsites.add(new WebSite("https://byjus.com/question-answer/what-is-the-height-above-sea-level-of-mt-everest-8848-m7848-m8048-m9848-m/", pageContent4.toLowerCase(), "What is the height above sea level of Mt. Everest?"));
      userWebsites.add(new WebSite("https://www.thehindubusinessline.com/news/world/mount-everest-is-884886-metres-tall-say-nepal-and-china-after-remeasurement/article33281085.ece", pageContent5.toLowerCase(), "Mount Everest is 8,848.86 metres tall, say Nepal and China after remeasurement"));
        return userWebsites;
    }

    private static List<Word> filterTopicTerms(List<WebSite> webSites, List<Word> potentialTopicTerms) {
        return potentialTopicTerms.stream()
                .filter(topicTerm -> isTopicTerm(webSites, "Mount Everest", "tallest", topicTerm))
                .collect(Collectors.toList());
    }

    private static boolean isQuantityPresent(List<WebSite> webSites, String quantity){
        int trueCount = 0;
        int falseCount = 0;

        for (WebSite website : webSites) {
            MeasurementChecker checker = new MeasurementChecker();
            boolean result = checker.confirmMeasurement(website.getLowerCaseText(),quantity, 100); // Adjust deviation as needed
            if (result) {
                trueCount++;
            } else {
                falseCount++;
            }
        }

        // Return true if the quantity is confirmed in majority
        return trueCount >= falseCount;
    }
    private static boolean isTopicTerm(List<WebSite> webSites, String subjectLabel, String objectLabel, Word potentialTopicTerm) {


        // number of results returned by the search engine for a query
        int numberOfSearchResults = webSites.size();
        // number of results returned by the search engine in which the potential Topic Term also appears

        int numberOfSearchResultsWithTopicTerm = 0;
        // the number of pages that contain query terms in title

        int numberOfSearchResultsWithQueryTermsInTitle = 0;
        // the number of pages that contain query terms in title, and also contain the potential topic term in body

        int numberOfSearchResultsWithQueryTermsInTitleAndTopicTermInBody = 0;

        boolean topicTermFoundInBody;

        for (WebSite webSite : webSites) {
            topicTermFoundInBody = false;

            // if the term appears in the webpage body, then numberOfSearchResultsWithTopicTerm should be incremented
            if (webSite.getLowerCaseText().contains(potentialTopicTerm.getWord().toLowerCase())) {
                numberOfSearchResultsWithTopicTerm++;
                topicTermFoundInBody = true;
            }
            // if the subject or the object label appears in the title of the page
            // then we should increment numberOfSearchResultsWithQueryTermsInTitle
            if (webSite.getLowerCaseTitle().contains(subjectLabel.toLowerCase())
                    || webSite.getLowerCaseTitle().contains(objectLabel.toLowerCase())) {
                numberOfSearchResultsWithQueryTermsInTitle++;

                // if the potential topic term was also found in body, then we should also
                // increment numberOfSearchResultsWithQueryTermsInTitleAndTopicTermInBody
                if (topicTermFoundInBody) {
                    numberOfSearchResultsWithQueryTermsInTitleAndTopicTermInBody++;
                }
            }
        }

        float numberOfSearchResultsWithTopicTermProbability = 0;
        float numberOfSearchResultsWithQueryTermsInTitleAndTopicTermInBodyProbability = 0;

        if (numberOfSearchResults > 0) {
            numberOfSearchResultsWithTopicTermProbability = (float) numberOfSearchResultsWithTopicTerm
                    / (float) numberOfSearchResults;
        }

        if (numberOfSearchResultsWithQueryTermsInTitle > 0) {
            numberOfSearchResultsWithQueryTermsInTitleAndTopicTermInBodyProbability = (float) numberOfSearchResultsWithQueryTermsInTitleAndTopicTermInBody
                    / (float) numberOfSearchResultsWithQueryTermsInTitle;
        }

        return numberOfSearchResultsWithQueryTermsInTitleAndTopicTermInBodyProbability > 0.99 * (numberOfSearchResultsWithTopicTermProbability);
    }


}
