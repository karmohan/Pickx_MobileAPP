package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Recordings_Screen {
    
    By recorded_tab= MobileBy.AccessibilityId("Recorded");
    By recordings_container = MobileBy.AccessibilityId("MyRecordingsView_collectionView");
    By programs_in_recordings = By.xpath("(//XCUIElementTypeCell[@name=\"myrecordings_cell\"])[1]");
    By recordings_description = By.xpath("(//XCUIElementTypeStaticText[@name=\"myrecordings_textview_details\"])[1]");
    By other_options_in_recordings = By.xpath("(//XCUIElementTypeButton[@name=\"option\"])[1]");
    By recordings_icons = By.xpath("(//XCUIElementTypeImage[@name=\"myrecordings_metadataicon_recording\"])[1]");
    By planned_tab= MobileBy.AccessibilityId("Planned");
    By recordings_pop_up = MobileBy.AccessibilityId("record_options_list");
    By delete_recording_option = MobileBy.AccessibilityId("Delete recording");
    By record_series_option = MobileBy.AccessibilityId("Record series");
    //By recorded_tab = MobileBy.xpath("//*[contains(@name,'Recorded')]");
   // By planned_tab = MobileBy.xpath("//*[contains(@name,'Planned')]");
    //By recordings_poster = MobileBy.xpath("//XCUIElementTypeCell[@name='myrecordings_cell']/XCUIElementTypeOther/XCUIElementTypeImage");
    //By recordings_title = MobileBy.name("myrecordings_textview_title");
    By recordings_poster = MobileBy.xpath("(//XCUIElementTypeCell[@name=\"myrecordings_cell\"])[1]/XCUIElementTypeOther/XCUIElementTypeImage");
    By recordings_title = By.xpath("(//XCUIElementTypeStaticText[@name=\"myrecordings_textview_title\"])[1]");
    
    By recordings_series_episode = By.xpath("//XCUIElementTypeTable[@name=\"SeriesDetailViewController_tableView\"]/XCUIElementTypeCell[2]");
  
    
   // By recordings_container = By.xpath("//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeCollectionView");

}
