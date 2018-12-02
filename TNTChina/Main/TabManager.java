package TNTChina.Main;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;
import TNTChina.Module.Category;
import TNTChina.TabGui.Tab;
import TNTChina.TabGui.TabButton;

public class TabManager {

	private static final List<Tab> tabList = new ArrayList<Tab>();
	private static int currentID = 0;
	private static Tab currentTab;
	private static int tabSize = Category.values().length;
	private static Category[] categorys = Category.values();
	
	public static final List<? extends Tab> getTabs() {
		return TabManager.tabList; 
	}
	
	public static void initTabGui() {
		for (int i = 0; i < categorys.length; i++) {
			Category category = categorys[i];
			Tab tab = new Tab(category.getName());
			tab.setX(10);
			tab.setY((i + 2) * 20);
			tab.setWidth(50);
			tab.setHeight(20);
			
			for (Module<?> m : ModuleManager.getModules()) {
				if (m.getCategory().equals(category)) {
					tab.addButton(new TabButton(m));
				}
			}
			
			tabList.add(tab);
		}
		
		for (Tab tab : tabList) {
			for (int i = 0; i < tab.getButtons().size(); i++) {
				TabButton tabButton = tab.getButtons().get(i);
				tabButton.setX(tab.getX() + tab.getWidth());
				tabButton.setY(tab.getY() + i * 20);
				tabButton.setWidth(50);
				tabButton.setHeight(20);
			}
			
			if (tab.getButtons().size() == 0) {
				continue;
			}
			
			tab.setCurrentButton(tab.getButtons().get(0));
		}
		
		currentTab = tabList.get(0);
	}
	
	public static void onUpPresses() {
		currentID--;
	
		if (currentID < 0) {
			currentID += tabList.size();
		}
		
		currentTab = tabList.get(currentID);
	}
	
	public static void onDownPresses() {
		currentID++;
		
		if (currentID > (tabSize - 1)) {
			currentID = (currentID - (tabSize));
		}
		
		currentTab = tabList.get(currentID);
	}
	
	public static void onKeyPresses(int key) {
		if (!Keyboard.getEventKeyState()) {
			return;
		}
		
		switch (key) {
			case Keyboard.KEY_DOWN: {
				if (!currentTab.isSelect()) {
					onDownPresses();
				} else {
					currentTab.onDownPresses();
				}
				
				break;
			} case Keyboard.KEY_UP: {
				if (!currentTab.isSelect()) {
					onUpPresses();
				} else {
					currentTab.onUpPresses();
				}
				
				break;
			} case Keyboard.KEY_RETURN: {
				if (currentTab.isSelect()) {
					if (currentTab.getCurrentButton() == null) {
						if (currentTab.getButtons().size() == 0) {
							break;
						}
						
						currentTab.setCurrentButton(currentTab.getButtons().get(0));
					}
					
					currentTab.getCurrentButton().getModule().ToggledModule();
				}
				
				break;
			} case Keyboard.KEY_LEFT: {
				currentTab.setSelect(false);
				break;
			} case Keyboard.KEY_RIGHT: {
				currentTab.setSelect(true);
				break;
			}
		}
	}

	public static Tab getCurrentTab() {
		return currentTab;
	}
}
