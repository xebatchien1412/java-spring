package com.login.entites;



public class Settings {
   
   private String version;
   private String auto_play_video;
   private boolean auto_play_video_is_changed;
   private String recept_notifies;
   private boolean recept_notifies_is_changed;
   private String language;
   private boolean language_is_changed;
   
   public boolean isAuto_play_video_is_changed() {
	return auto_play_video_is_changed;
}
public void setAuto_play_video_is_changed(boolean auto_play_video_is_changed) {
	this.auto_play_video_is_changed = auto_play_video_is_changed;
}
public boolean isRecept_notifies_is_changed() {
	return recept_notifies_is_changed;
}
public void setRecept_notifies_is_changed(boolean recept_notifies_is_changed) {
	this.recept_notifies_is_changed = recept_notifies_is_changed;
}
public boolean isLanguage_is_changed() {
	return language_is_changed;
}
public void setLanguage_is_changed(boolean language_is_changed) {
	this.language_is_changed = language_is_changed;
}
public Settings() {};
   public Settings(String version, String auto_play_video, String recept_notifies, String language) {
	   this.version = version;
	   this.auto_play_video = auto_play_video;
	   this.recept_notifies = recept_notifies;
	   this.language = language;
	   
   }
   public String getVersion() {
	return version;
}
   
   public void setVersion(String version) {
	this.version = version;
}
   
   public String getAuto_play_video() {
	return auto_play_video;
}
   public void setAuto_play_video(String auto_play_video) {
	this.auto_play_video = auto_play_video;
}
   public String getRecept_notifies() {
	return recept_notifies;
}
   public void setRecept_notifies(String recept_notifies) {
	this.recept_notifies = recept_notifies;
}
   public String getLanguage() {
	return language;
}
   public void setLanguage(String language) {
	this.language = language;
}
   
}
