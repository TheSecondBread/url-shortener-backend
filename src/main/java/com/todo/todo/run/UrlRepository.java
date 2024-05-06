package com.todo.todo.run;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.Random;
import java.util.List;

@Repository
public class UrlRepository {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final JdbcClient jdbcClient;
    public UrlRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    public List<Url> GetUrls(){
        return jdbcClient.sql("SELECT * FROM links")
                .query(Url.class)
                .list();
    }
    public String ShortenUrl(){
        StringBuilder x= new StringBuilder();
        Random random= new Random();
        for(int i=0;i<6;i++){
            int random_id=random.nextInt(CHARACTERS.length());
            x.append(CHARACTERS.charAt(random_id));
        }
        return x.toString();
    }
    public String GetLink(String  url){
            List<Url> a= jdbcClient
                    .sql("SELECT * FROM links WHERE url = ?")
                    .param(url)
                    .query(Url.class)
                    .list();
            return (a.get(0)).link();
    }
    public Url AddUrl(Url url){
        String x=ShortenUrl();
        jdbcClient.sql("INSERT INTO LINKS(link,url) VALUES(?,?)")
                .params(url.link(),x)
               .update();
        List<Url> a= jdbcClient.sql("SELECT * FROM links")
                .query(Url.class)
                .list();
        return a.get(a.size()-1);

    }
}
