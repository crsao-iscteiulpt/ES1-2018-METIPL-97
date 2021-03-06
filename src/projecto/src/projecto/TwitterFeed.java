package src.projecto.src.projecto;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * @author afluz
 * Returns tweets related to a defined word, or, if that word is included in the tweet itself;
 *
 */

public final class TwitterFeed  {
	private GUI gui;
	private String filter;
	private ArrayList<Publicacao> publicacoes;
	
	/**
	 * 
	 * @param filter 
	 * filters recent tweets by the last 24hrs;
	 * @param gui
	 * user interface;
	 */

	public TwitterFeed(String filter, GUI gui) {
		/**
		 * returns the twitter feed filtered by a defined word;
		 */
		this.filter=filter;
		this.gui=gui;
		publicacoes= new ArrayList<Publicacao>();

		//http://twitter4j.org
		// http://twitter4j.org/en/code-examples.html
		// https://www.youtube.com/watch?v=uYPmkzMpnxw
        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey("W1f0VvgWPfT8OBqVxvy4Mw")
        	  .setOAuthConsumerSecret("zKH2yAtRyefwsgOO8h8Szc4kru68iEm95QmIG7svw")
        	  .setOAuthAccessToken("36481851-VhzByC4f9MSsZES1QZQ4e4iBvA9bWGLyv9HKFpy7c")
        	  .setOAuthAccessTokenSecret("OahDuXF2Lhl5xlNYALhYZir6xSflAxKP9Zh89T05po");
        	TwitterFactory tf = new TwitterFactory(cb.build());
        	Twitter twitter = tf.getInstance();        		
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("----------------\n Showing home timeline \n---------------------");
    		int counter=0;
    		int counterTotal = 0;
            for (Status status : statuses) {
				// Filters only tweets from user String filter;
				if (status.getUser().getName() != null && status.getUser().getName().contains(filter)) {
					counter++;
					publicacoes.add(new Publicacao ("Twitter",status.getUser().getName(), status.getText() ,status.getCreatedAt()));
				}
				counterTotal++;
            }
    		gui.update(publicacoes,false);
            System.out.println("-------------\nN� of Results: " + counter+"/"+counterTotal);
        } catch (Exception e) { 
        	System.out.println(e.getMessage()); }
        	gui.setModoOffline();
	}
}
