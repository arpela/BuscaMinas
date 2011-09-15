package uy.com.s4b.table;

import net.rim.device.api.command.Command;
import net.rim.device.api.command.CommandHandler;
import net.rim.device.api.command.ReadOnlyCommandMetadata;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;

/**
 * 
 * @author Pablo
 *
 */
public class Busqueda extends MainScreen {

	private static Screen busquedaScreen;
	private BasicEditField dni;
	private BasicEditField matricula;

	public Busqueda() {
		super(Manager.NO_VERTICAL_SCROLL);
		busquedaScreen = this;
		Manager mainManager = getMainManager();
		
		dni = new BasicEditField("DNI: ", "11810233D", 10, BasicEditField.FILTER_DEFAULT);
		mainManager.add(dni);
		
		matricula = new BasicEditField("Matrícula: ", "0000BBB", 9, BasicEditField.FILTER_DEFAULT);
		mainManager.add(matricula);
		
		ButtonField buscar = new ButtonField("Buscar Multas", ButtonField.CONSUME_CLICK);
        mainManager.add(buscar);
        
        DatosBusqueda datosBusqueda = new DatosBusqueda() {
			
			public String getMatricula() {
				return matricula.getText();
			}
			
			public String getDni() {
				return dni.getText();
			}
		};
		
		buscar.setCommandContext(datosBusqueda);
		buscar.setCommand(new Command(new CommandHandler() {
			

			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				DatosBusqueda datosBusqueda = (DatosBusqueda) context;
				if (datosBusqueda.getDni().equals("") || datosBusqueda.getMatricula().equals("")){
					Dialog.alert("Datos incorrectos");
				} else {
					UiApplication.getUiApplication().pushScreen(new Resultados(datosBusqueda.getDni(), datosBusqueda.getMatricula()));
					UiApplication.getUiApplication().popScreen(busquedaScreen);
				}
			}
		}));
	}
	
	interface DatosBusqueda{
		public String getDni();
		public String getMatricula();
	}
}

