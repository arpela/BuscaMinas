package uy.com.s4b.table;

import net.rim.device.api.ui.UiApplication;

/**
 * @author Pablo
 *
 */
public class Run extends UiApplication {
	public static void main(String[] args) {
		UiApplication app = new Run();
		app.enterEventDispatcher();
	}

	public Run() {
		pushScreen(new InicioScreen());
	}
}
