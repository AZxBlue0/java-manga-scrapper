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

    public Elements getAllPages() throws IOException {
        Document page = Jsoup.connect(url).get();
        Elements allPages = page.getElementsByClass("container").get(0)
                .getElementsByTag("img");

        return allPages;
    }

    public String getRandomPage(Elements allPages){
        Random random = new Random();
        int randomPage = random.nextInt(allPages.size());
        return allPages.get(randomPage).attr("src");
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
