package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Vod_Details_Screen {

	By vod_synopsis = MobileBy.AccessibilityId("VODItemDetailView_descriptionLabel");
	By vodView = MobileBy.AccessibilityId("VODItemView");
	By play_on_decoder = MobileBy.AccessibilityId("NonPlayableOttView_$__lazy_storage_$_contentView");
	By vod_download_title = MobileBy.id("Download");
	By availability = MobileBy.id("VODItemDetailView_availabilityLabel");
	By vod_program_title = MobileBy.AccessibilityId("VODItemDetailView_titleLabel");
	By vod_program_time = MobileBy.AccessibilityId("VODItemDetailView_metadataLabel");
	By vod_download_button = MobileBy.AccessibilityId("VODItemDetailView_button");
	By vod_program_subtitle = MobileBy.AccessibilityId("VODItemDetailView_titleLabel");
}
