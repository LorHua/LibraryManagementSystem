package util;


	import java.net.URL;
	import javax.swing.ImageIcon;
	import MainPack.LibrarySystem;

	public class CreatedIcon {
		public static ImageIcon add(String ImageName){
			URL IconUrl = LibrarySystem.class.getResource("/"+ImageName);
			ImageIcon icon=new ImageIcon(IconUrl);
			return icon;
		}
	}


