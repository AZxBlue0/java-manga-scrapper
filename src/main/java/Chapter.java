import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Random;

public class Chapter {
    String title, url;

    public Chapter(String title, String url) {
        this.title = title;
        this.url = url;
    }
    public Elements getAllPages() throws IOException, NullPointerException {
        Document page = Jsoup.connect(url).get();
        return page.getElementById("vungdoc")
                .getElementsByTag("img");
    }

    public String getRandomPage(Elements allPages) {
        Random random = new Random();
        System.out.println(allPages.get(0).html());
        int randomPage = random.nextInt(allPages.size());
        return allPages.get(randomPage).attr("data-src");
    }

        public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
