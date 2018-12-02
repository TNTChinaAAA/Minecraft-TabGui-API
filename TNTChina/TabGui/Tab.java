package TNTChina.TabGui;

import java.util.ArrayList;
import java.util.List;
import TNTChina.Gui.Component;
import TNTChina.Module.KillAura;

public class Tab extends Component {

	public List<TabButton> buttons = new ArrayList<TabButton>();
	private boolean Select = false;
	private String name = "";
	private TabButton currentButton = new TabButton(KillAura.getKillAura());
	private int currentButtonID = 0;
	
	public Tab(String name) {
		this.name = name;
	}
	
	public TabButton getCurrentButton() {
		return this.currentButton;
	}
	
	public void onUpPresses() {
		this.currentButtonID--;
		
		if (this.currentButtonID < 0) {
			this.currentButtonID += this.getButtons().size();
		}
		
		if (this.getButtons().size() == 0) {
			return;
		}
		
		this.currentButton = this.getButtons().get(this.currentButtonID);
	}
	
	public void onDownPresses() {
		this.currentButtonID++;
		
		if (this.currentButtonID > (this.getButtons().size() - 1)) {
			this.currentButtonID = (this.currentButtonID - (this.getButtons().size()));
		}
		
		if (this.getButtons().size() == 0) {
			return;
		}
		
		this.currentButton = this.getButtons().get(this.currentButtonID);
	}
	
	public void addButton(TabButton button) {
		this.buttons.add(button);
	}
	
	public void setCurrentButton(TabButton currentButton) {
		this.currentButton = currentButton;
	}
	
	public List<? extends TabButton> getButtons() {
		return this.buttons;
	}
	
	public boolean hasName() {
		return !this.name.equals("");
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isSelect() {
		return this.Select;
	}

	public void setSelect(boolean select) {
		this.Select = select;
	}
}
