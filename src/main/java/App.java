import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class App {
    Chapters chapters;

    public App(){
        chapters = new Chapters();
    }


    public static void main(String[] args) {
        String mainUrl = "";

        mainUrl = "https://w32.readheroacademia.com/";

        App app = new App();
        try {
            app.fillChaptersList(mainUrl);
            app.getChapters().listChapters();
            app.getChapters().setChapterBound(30,70);
            System.out.println(app.getChapters().getRandomPage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Chapters getChapters(){
        return chapters;
    }
    public void fillChaptersList(String url) throws IOException {
        Document page = Jsoup.connect(url).get();
        Elements chaptersTable = page.getElementsByClass("chap_tab")
                .get(0).getElementsByTag("tr");

        for(Element tableRow: chaptersTable){
            Chapter chapter = getChapterFromTableRow(tableRow);
            chapters.addChapter(chapter);
        }
    }

    public Chapter getChapterFromTableRow(Element tableRow){
        String url = tableRow.getElementsByTag("a").get(0).attr("href");
        String title = tableRow.getElementsByTag("td").get(0).text();

        Chapter result = new Chapter(title,url);
        return result;
    }
}
