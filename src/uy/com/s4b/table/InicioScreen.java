package uy.com.s4b.table;

import net.rim.device.api.command.Command;
import net.rim.device.api.command.CommandHandler;
import net.rim.device.api.command.ReadOnlyCommandMetadata;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;

public class InicioScreen extends MainScreen {
	
	private static Screen inicioScreen;
	public static final long GUID = 0x2051fd67b72d11L;
	public static final String APP_NAME = "BuscaMultas";
	
	public InicioScreen() {
		super(Manager.NO_VERTICAL_SCROLL);
		EventLogger.register(GUID, APP_NAME, EventLogger.VIEWER_STRING);
		inicioScreen = this;
		try {
			String inicioResolutionName = Util.getImageByResolution("previo");
			Bitmap inicioBmp = Bitmap.getBitmapResource(inicioResolutionName);
			BitmapField previoField = new BitmapField(inicioBmp, BitmapField.FIELD_HCENTER);
			add(previoField);
			EventLogger.logEvent(GUID, new String("---> Image name: " + inicioResolutionName).getBytes(), EventLogger.ALWAYS_LOG);

			ButtonField inicio = new ButtonField("Inicio", ButtonField.FIELD_HCENTER | ButtonField.CONSUME_CLICK);
			inicio.setCommand(new Command(new CommandHandler() {
				public void execute(ReadOnlyCommandMetadata metadata, Object context) {
					UiApplication.getUiApplication().pushScreen(new Busqueda());
					UiApplication.getUiApplication().popScreen(inicioScreen);
				}
			}));
			add(inicio);
		} catch (Exception e) {
			Dialog.alert(e.getMessage());
		}
	}
}
