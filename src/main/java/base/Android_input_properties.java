package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Android_input_properties {

	protected Properties prop;
	protected FileInputStream fis = new FileInputStream(
			System.getProperty("user.dir") + "/src/test/resources/Android_input.properties");

	public Android_input_properties() throws IOException {
		prop = new Properties();
		prop.load(fis);
	}

	public String getenterPinCode_fortheFirsttime() {
		return prop.getProperty("enterPinCode_fortheFirsttime");
	}
	
	public String getpinChangeText() {
		return prop.getProperty("pinChangeText");
	}
	
	public String getreconfirmPinChangeText() {
		return prop.getProperty("reconfirmPinChangeText");
	}
	
	public String getpinValue() {
		return prop.getProperty("pinValue");
	}
	
	public String getnewPin() {
		return prop.getProperty("newPin");
	}
	
	public String newSecondPin() {
		return prop.getProperty("newSecondPin");
	}
	
	public String wrongPin() {
		return prop.getProperty("wrongPin");
	}
	
	public String getfeedback() {
		return prop.getProperty("feedback");
	}
	
	public String getemail() {
		return prop.getProperty("email");
	}
	
        public String getLiveTvPageTitle() {
    		return prop.getProperty("liveTvPageTitle");
        }
    
        public String getAll() {
    		return prop.getProperty("all");
        }
    
        public String getMovies() {
    		return prop.getProperty("movies");
        }
    
        public String getSeries() {
    		return prop.getProperty("series");
        }
    
        public String getEntertainment() {
    		return prop.getProperty("entertainment");
        }
    
        public String getSports() {
    		return prop.getProperty("sports");
        }
    
        public String getDiscovery() {
    		return prop.getProperty("discovery");
        }
    
        public String getKids() {
    		return prop.getProperty("kids");
        }
    
        public String getMusic() {
    		return prop.getProperty("music");
        }
    
        public String getNews() {
    		return prop.getProperty("news");
        }
    
        public String getRecord() {
    		return prop.getProperty("record");
        }
    
        public String getManageRecording() {
    		return prop.getProperty("manage_recording");
        }
    
        public String getStopRecording() {
    		return prop.getProperty("stop_recording");
        }
    
        public String getCancelRecording() {
    		return prop.getProperty("cancel_recording");
        }
    
        public String getRecordEpisode() {
    		return prop.getProperty("record_episode");
        }
    
        public String getStopEpisodeRecording() {
    		return prop.getProperty("stop_episode_recording");
        }
    
        public String getYesterday() {
    		return prop.getProperty("yesterday");
        }
    
        public String getTomorrow() {
    		return prop.getProperty("tomorrow");
        }
    
        public String getRecommendedMoviesAndSeriesSwimlane() {
    		return prop.getProperty("recommended_movies_and_series_swimlane");
        }

        public String getchromecast_devicename() {
		return prop.getProperty("chromecast_devicename");
	}
        
        public String getChannelWithNonPlayableItem() {
    		return prop.getProperty("channel_with_non_playable_item");
        }
    
        public String getRecordSeries() {
    		return prop.getProperty("record_series");
        }
        
        public String getDeleteRecording() {
    		return prop.getProperty("delete_recording");
        }
        
        public String getStopSeriesRecording() {
    		return prop.getProperty("stop_series_recording");
        }
        
        public String getRecommendedForYou() {
    		return prop.getProperty("recommended_for_you");
        }
        
        public String getComingUpOnTv() {
    		return prop.getProperty("coming_up_on_tv");
        }
        
        public String getCancelRecordingThisEpisode() {
    		return prop.getProperty("cancel_recording_this_episode");
        }
        
        public String getRecommendedInSports() {
    		return prop.getProperty("recommended_in_sports");
        }
        
        public String getBottom_navigation_LiveTv() {
    		return prop.getProperty("Bottom_navigation_LiveTv");
        }
        
        public String getBottom_navigation_TVguide() {
    		return prop.getProperty("Bottom_navigation_TVguide");
        }
        
        public String getBottom_navigation_MyVideos() {
    		return prop.getProperty("Bottom_navigation_MyVideos");
        }

        public String getElementString(String element_name,String language) {	   
        	
    	    String currentConfig = prop.getProperty(element_name);
    	    List<String> sList = Arrays.asList(currentConfig.split(","));
    	    if(language.equals("EN")) {
    		return sList.get(0);
    	    }
    	    else if(language.equals("FR")) {
    		return sList.get(1);
    	    }
    	    else {
    		return sList.get(2);
    	    }
    	}
        
        public String getyesterday() {
    		return prop.getProperty("Yesterday");
        }
        
        public String gettomorrow() {
    		return prop.getProperty("Tomorrow");
        }
        
        public String getToday() {
    		return prop.getProperty("Today");
        }
}
