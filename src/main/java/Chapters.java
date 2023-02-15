import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.lang.IllegalArgumentException;

public class Chapters {
    ArrayList<Chapter> chapters;
    int maxChapter =0, minChapter =0;

    public void init() {
        chapters = new ArrayList<>();
    }

    public Chapters() {
        init();
    }

    public void addChapter(Chapter chapter){
        chapters.add(0,chapter);
        maxChapter++;
    }

    public String getRandomPage() throws IOException {
        Chapter chapter = getRandomChapter();
        Elements allPages = chapter.getAllPages();
        return chapter.getRandomPage(allPages);
    }

    private Chapter getRandomChapter() throws IllegalArgumentException{
        checkBounds(minChapter, maxChapter);
        Random random = new Random();
        int chapterIndex = random.nextInt(maxChapter - minChapter) + minChapter;
        return chapters.get(chapterIndex);
    }

    private void checkBounds(int minimumValue, int maximumValue) throws IllegalArgumentException{
        if (minimumValue >= maximumValue) {
            throw new IllegalArgumentException("Min value is greater than max value");
        }
    }

    public void setChapterBound(int min, int max){
        if(min < 0) min=0;
        if(max >= chapters.size()) max = chapters.size()-1;
        this.maxChapter = max;
        this.minChapter = min;
    }

    public void listChapters(){
        for(int i =0; i<chapters.size(); i++){
            System.out.println(i+"\t"+chapters.get(i).title);
        }
    }

    private String getBaseUrl(String url){
        int firstSlash = url.indexOf("/", 8);
        return url.substring(0,firstSlash);
    }

    public void fill(String url) throws IOException {
        Document page = Jsoup.connect(url).get();
        Elements chaptersTable = page.getElementsByClass("chapter-list")
                        .get(0).getElementsByTag("div");
        String baseUrl = getBaseUrl(url);
        for(Element tableRow: chaptersTable){
            String chaptersUrl = baseUrl+tableRow.getElementsByTag("a").get(0).attr("href");
            String title = tableRow.getElementsByTag("span").get(0).text();
                     addChapter(new Chapter(title,chaptersUrl));
                     this.maxChapter++;

        }

    }

}

