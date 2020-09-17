package travel.mp.com.travel;

/**
 * Created by admin on 2015-06-16.
 */
public class List {

    int Key ;
    String folder ;
    String date ;
    String region ;
    String add_title1 ;
    String add_contents1 ;
    String add_title2 ;
    String add_contents2 ;
    String add_title3 ;
    String add_contents3 ;
    String add_title4 ;
    String add_contents4 ;
    String add_title5 ;
    String add_contents5 ;
    String tag1 ;
    String tag2 ;
    String tag3 ;


    List(){
        
    }

    List(String folder, String date, String region,
         String add_title1, String add_contents1, String add_title2, String add_contents2,
         String add_title3, String add_contents3, String add_title4, String add_contents4,
         String add_title5, String add_contents5, String tag1, String tag2, String tag3) {

        this.folder = folder ;
        this.date = date ;
        this.region = region ;
        this.add_title1 = add_title1 ;
        this.add_contents1 = add_contents1 ;
        this.add_title2 = add_title2 ;
        this.add_contents2 = add_contents2 ;
        this.add_title3 = add_title3 ;
        this.add_contents3 = add_contents3 ;
        this.add_title4 = add_title4 ;
        this.add_contents4 = add_contents4 ;
        this.add_title5 = add_title5 ;
        this.add_contents5 = add_contents5 ;
        this.tag1 = tag1 ;
        this.tag2 = tag2 ;
        this.tag3 = tag3 ;

    }

}
