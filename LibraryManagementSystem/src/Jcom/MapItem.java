package Jcom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

import Dao.Dao;
import TableModel.booktype;
import TableModel.readertype;

public class MapItem { 
	static Map map1 = new HashMap();
	//图书类别
	public static Map getMap() {
		List list = Dao.selectBookType();
		for (int i = 0; i < list.size(); i++) {
			booktype Booktype = (booktype) list.get(i);

			Item Item = new Item();
			Item.setId(Booktype.getTypeid());
			Item.setName(Booktype.getTypename());
			map1.put(Item.getId(), Item);

		}
		return map1;
	}
	//读者类别
	static Map map2 = new HashMap();
	public static Map getMap1() {
		List list = Dao.selectReaderType();
		for (int i = 0; i < list.size(); i++) {
			readertype Readertype = (readertype) list.get(i);

			Item Item = new Item();
			Item.setId(String.valueOf(Readertype.getTypeid()));
			Item.setName(Readertype.getTypename());
			map2.put(Item.getId(), Item);

		}
		return map2;
	}
}


