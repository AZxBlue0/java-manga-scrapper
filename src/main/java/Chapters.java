import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.lang.IllegalArgumentException;

public class Chapters {
    ArrayList<Chapter> chapters;
    int maxChapter =0, minChapter =0;

    public void init() {
        chapters = new ArrayList<Chapter>();
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
        if (minimumValue > maximumValue) {
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
}
