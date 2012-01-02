package uy.com.s4b.table;

import net.rim.device.api.command.Command;
import net.rim.device.api.command.CommandHandler;
import net.rim.device.api.command.ReadOnlyCommandMetadata;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.Background;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

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
		String imgName = Util.getImageByResolution("logo_buscaMultas");
		Bitmap logoBuscaMultas = Bitmap.getBitmapResource(imgName);
		BitmapField bitmapLogo = new BitmapField(logoBuscaMultas, BitmapField.FIELD_HCENTER);
		
		LabelField dniLF = new LabelField("DNI ", NON_FOCUSABLE);
		LabelField matriculaLF = new LabelField("Matrícula ", NON_FOCUSABLE);
		dni = new BasicEditField("", "11810233D", 9, BasicEditField.FILTER_DEFAULT | BasicEditField.NO_NEWLINE);
		matricula = new BasicEditField("", "0000BBB", 9, BasicEditField.FILTER_DEFAULT | BasicEditField.NO_NEWLINE);
		
//		XYEdges margin = new XYEdges(10, 0, 10, 0);
//		XYEdges edges = new XYEdges(2, 1, 1, 1);
//		Border border = BorderFactory.createRoundedBorder(edges, Color.GRAY, Border.STYLE_FILLED);
//		Background gradient = BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE, Color.LIGHTGRAY, Color.LIGHTGRAY);
//		
		XYEdges margin = new XYEdges(8, 0, 10, 0);
		XYEdges edges = new XYEdges(3, 3, 2, 2);
		Border border = BorderFactory.createRoundedBorder(edges, Color.GRAY, Border.STYLE_FILLED);
		Background gradient = BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE, Color.LIGHTGRAY, Color.LIGHTGRAY);

		
		dni.setBackground(gradient);
		matricula.setBackground(gradient);
		
		dni.setBorder(border);
		matricula.setBorder(border);
		
		dni.setMargin(margin);
		matricula.setMargin(margin);
		
		GridFieldManager grid1 = new GridFieldManager(1, 1, 0);
		grid1.insert(bitmapLogo, 0, FIELD_HCENTER);
		add(grid1);

		GridFieldManager grid2 = new GridFieldManager(2, 2, 0);
		grid2.insert(dniLF, 0, FIELD_RIGHT);
		grid2.insert(dni, 1);
		grid2.insert(matriculaLF, 2, FIELD_RIGHT);
		grid2.insert(matricula, 3);
		add(grid2);
		

		
        DatosBusqueda datosBusqueda = new DatosBusqueda() {
			
			public String getMatricula() {
				return matricula.getText();
			}
			
			public String getDni() {
				return dni.getText();
			}
		};
		
		ButtonField buscar = new ButtonField("Buscar Multas", FIELD_VCENTER);
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

		add(new BitmapField(Bitmap.getBitmapResource("whitespace.png")));
		add(buscar);
		add(new BitmapField(Bitmap.getBitmapResource("whitespace.png")));
		
		BitmapField leyenda = new BitmapField(Bitmap.getBitmapResource(Util.getImageByResolution("leyenda_busqueda")));
		add(leyenda);
	}
	
	interface DatosBusqueda{
		public String getDni();
		public String getMatricula();
	}
}

