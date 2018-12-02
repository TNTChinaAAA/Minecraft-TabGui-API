package TNTChina.TabGui;

import TNTChina.Gui.Button;
import TNTChina.Gui.Label;
import TNTChina.Main.Module;

public class TabButton extends Button {

	private volatile Module module;
	
	public TabButton(Module module) {
		super(new Label(module.getName()));
		this.module = module;
	}

	public Module getModule() {
		return this.module;
	}
}
