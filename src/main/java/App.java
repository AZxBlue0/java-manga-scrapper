import java.io.IOException;

public class App {
    Chapters chapters;

    public App(){
        chapters = new Chapters();
    }


    public static void main(String[] args) {
        String mainUrl = "";

        //Naruto
        //mainUrl = "https://ww5.mangakakalot.tv/manga/manga-ng952689";
        //Vagabond
        //mainUrl = "https://ww5.mangakakalot.tv/manga/manga-je954913";
        //Shaman King
        //mainUrl = "https://ww5.mangakakalot.tv/manga/manga-id957386";
        //Bleach
        //mainUrl = "https://ww5.mangakakalot.tv/manga/manga-qq951425";
        //Berserk
        //mainUrl ="https://ww5.mangakakalot.tv/manga/manga-ma952557";
        //Prison school
        //mainUrl="https://ww5.mangakakalot.tv/manga/manga-nl952268";
        //MyHeroAcademia
        mainUrl = "https://ww5.mangakakalot.tv/manga/manga-jq951973";

        App app = new App();
        try {
            app.getChapters().fill(mainUrl);
            //app.getChapters().setChapterBound(30,70);
            app.getChapters().listChapters();
            System.out.println(app.getChapters().getRandomPage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Chapters getChapters(){
        return chapters;
    }

}
