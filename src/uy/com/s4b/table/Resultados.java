/*
 * TreeScreen.java
 *
 * Copyright © 1998-2010 Research In Motion Ltd.
 * 
 * Note: For the sake of simplicity, this sample application may not leverage
 * resource bundles and resource strings.  However, it is STRONGLY recommended
 * that application developers make use of the localization features available
 * within the BlackBerry development platform to ensure a seamless application
 * experience across a variety of languages and geographies.  For more information
 * on localizing your application, please refer to the BlackBerry Java Development
 * Environment Development Guide associated with this release.
 */

package uy.com.s4b.table;

import java.rmi.RemoteException;

import net.rim.device.api.command.Command;
import net.rim.device.api.command.CommandHandler;
import net.rim.device.api.command.ReadOnlyCommandMetadata;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.table.DataTemplate;
import net.rim.device.api.ui.component.table.RegionStyles;
import net.rim.device.api.ui.component.table.TableController;
import net.rim.device.api.ui.component.table.TableModel;
import net.rim.device.api.ui.component.table.TableView;
import net.rim.device.api.ui.component.table.TemplateColumnProperties;
import net.rim.device.api.ui.component.table.TemplateRowProperties;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;
import uy.com.s4b.webservice.ArrayOfLocalizadorBoletinesInfo;
import uy.com.s4b.webservice.LocalizadorBoletinesInfo;
import uy.com.s4b.webservice.WebServiceSoap_Stub;

public final class Resultados extends MainScreen {
	private static Screen resultadosScreen;
	
	private TableModel _tableModel;
	
	private static final int ROW_HEIGHT = 25;

	// posiciones dentro del array
	protected static final int BITMAP 			= 0;
	protected static final int IMPORTE_SANCION 	= 1;
	protected static final int MATRICULA_DNI 	= 2;
	protected static final int PUNTOS 			= 3;
	protected static final int CODIGO 			= 4;
	protected static final int FECHA_INFRACCION	= 5;
	protected static final int RECURRIBLE 		= 6;
	//datos no visibles
	protected static final int MATRICULA 		= 7;
	protected static final int DNI 				= 8;
	
	/*
	 * Creates a new TreeScreen object
	 * 
	 * @param deviceData Data read from file to be displayed in table
	 */
	public Resultados(String dni, String matricula ) {
		super(Manager.NO_VERTICAL_SCROLL);
		resultadosScreen = this;
		setTitle("Resultados");
		
		add(getHeaderGridFieldManager());
		
		// Initialize the model if it has not yet been loaded
		_tableModel = new TableModel();
		
		cargoDeWSDL(dni, matricula);
	
		// Create and apply style
		RegionStyles style = new RegionStyles(BorderFactory.createSimpleBorder(
				new XYEdges(0, 0, 0, 0), Border.STYLE_TRANSPARENT), null, null, null,
				RegionStyles.ALIGN_LEFT, RegionStyles.ALIGN_TOP);

		// Create the view and controller
		TableView tableView = new TableView(_tableModel);
		TableController tableController = new TableController(_tableModel, tableView);
		// Set the controller focus policy to highlight rows
		tableController.setFocusPolicy(TableController.ROW_FOCUS);

		// Set the behaviour of the controller when a table item is clicked
		tableController.setCommand(new CommandHandler() {
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				TableView view = (TableView) context;
				int focused = view.getRowNumberWithFocus();
				if (focused >= 0) {
					Object[] datosMulta = (Object[]) ((TableModel) view .getModel()).getRow(focused);
					DetalleMulta detalleMultaScreen = new DetalleMulta(datosMulta, resultadosScreen);
					try {
						UiApplication.getUiApplication().pushScreen(detalleMultaScreen);
					} catch (Exception e) {
						Dialog.alert(e.getMessage() + e.toString());
					}
				}
			}
		}, null, tableView);

		tableView.setController(tableController);

		// Create a DataTemplate that suppresses the third column
		DataTemplate dataTemplate = new DataTemplate(tableView, 3, 3) {
			public Field[] getDataFields(int modelRowIndex) {
				Object[] data = (Object[]) ((TableModel) getView().getModel()).getRow(modelRowIndex);
				Field[] fields = new Field[7];
				fields[0] = new BitmapField((Bitmap) data[BITMAP], BitmapField.VCENTER | BitmapField.HCENTER);
				fields[1] = new RichTextField((String) data[IMPORTE_SANCION], Field.FOCUSABLE | DrawStyle.HCENTER);
				fields[2] = new RichTextField((String) data[MATRICULA_DNI], Field.FOCUSABLE);
				fields[3] = new RichTextField((String) data[PUNTOS], Field.FOCUSABLE);
				fields[4] = new RichTextField((String) data[CODIGO], Field.FOCUSABLE);
				fields[5] = new RichTextField((String) data[FECHA_INFRACCION], Field.FOCUSABLE);
				fields[6] = new RichTextField((String) data[RECURRIBLE], Field.FOCUSABLE);
				return fields;
			}
		};

		// Set up regions
		dataTemplate.createRegion(new XYRect(0, 0, 1, 3), style);
		dataTemplate.createRegion(new XYRect(1, 0, 1, 1), style);
		dataTemplate.createRegion(new XYRect(2, 0, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 1, 1, 1), style);
		dataTemplate.createRegion(new XYRect(2, 1, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 2, 1, 1), style);
		dataTemplate.createRegion(new XYRect(2, 2, 1, 1), style);

		// Specify the size of each column by percentage, and the height of a
		// row
		dataTemplate.setColumnProperties(0, new TemplateColumnProperties(15,TemplateColumnProperties.PERCENTAGE_WIDTH));
		dataTemplate.setColumnProperties(1, new TemplateColumnProperties(40,TemplateColumnProperties.PERCENTAGE_WIDTH));
		dataTemplate.setColumnProperties(2, new TemplateColumnProperties(45, TemplateColumnProperties.PERCENTAGE_WIDTH));
		dataTemplate.setRowProperties(0, new TemplateRowProperties(ROW_HEIGHT));
		dataTemplate.setRowProperties(1, new TemplateRowProperties(ROW_HEIGHT));
		dataTemplate.setRowProperties(2, new TemplateRowProperties(ROW_HEIGHT));

		// Apply the template to the view
		tableView.setDataTemplate(dataTemplate);
		dataTemplate.useFixedHeight(true);
		add(tableView);
	}

	private void cargoDeWSDL(String dniString, String matString) {
		WebServiceSoap_Stub webservices = new WebServiceSoap_Stub();
		try {
			try {
				ArrayOfLocalizadorBoletinesInfo r =  webservices.leeBoletin("iphonewebsrv", "Webiphonex14k", dniString, matString);
				LocalizadorBoletinesInfo listaInfor [] = r.getLocalizadorBoletinesInfo();
				
				if (listaInfor != null){
					for (int i = 0; i < listaInfor.length; i++) {
						String imageFileName = "icono_euro.png";
						Bitmap bitmap = Bitmap.getBitmapResource(imageFileName);
						
						if (listaInfor[i].getResultado() == null){
							String dni = listaInfor[i].getDNI();
							String fechaInfraccion = listaInfor[i].getFechaInfraccion();
							String importeSancion = listaInfor[i].getImporteSancion();
							String matricula = listaInfor[i].getMatricula();
							String puntos = listaInfor[i].getPuntos();
							String recurrible = listaInfor[i].getRecurrible();
							String codigo = listaInfor[i].getCodigo();
	
							_tableModel.addRow(new Object[] { 
									bitmap, 
									importeSancion,
									matricula + " " + dni, 
									puntos + " puntos", 
									codigo,
									fechaInfraccion,
									recurrible,
									matricula,
									dni});
						}else{
							_tableModel.addRow(new Object[] { bitmap, listaInfor[i].getResultado(),
									"", "", "", ""});							
						}
					}
				}
				
			} catch (RemoteException e) {
				Dialog.alert(e.getMessage());
			}
		}catch (NullPointerException e){			
			Dialog.alert(e.getMessage());
		} catch (RuntimeException e) {
			Dialog.alert(e.getMessage());
		} catch (Exception e) {
			Dialog.alert(e.getMessage());
		}
	}
	
	private GridFieldManager getHeaderGridFieldManager(){
		Field[] headerFields = getHeaderFields();
		GridFieldManager gridFieldManager = new GridFieldManager(1, 3, GridFieldManager.FIELD_LEFT | GridFieldManager.FIELD_HCENTER | GridFieldManager.FIELD_RIGHT);
		
		for (int i = 0; i < headerFields.length; i++) {
			gridFieldManager.insert(headerFields[i], i);
		}
		return gridFieldManager;
	}
	
	private Field[] getHeaderFields(){
		ButtonField volverBtn = new ButtonField("Volver", ButtonField.FIELD_LEFT);
		volverBtn.setCommand(new Command(new CommandHandler() {
			
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				UiApplication.getUiApplication().pushScreen(new Busqueda());
				UiApplication.getUiApplication().popScreen(resultadosScreen);
			}
		}));
		
		RichTextField multa = new RichTextField("     Resultados      ", RichTextField.FIELD_HCENTER | RichTextField.FIELD_VCENTER){
		    public int getPreferredWidth() {
		        return this.getFont().getAdvance(this.getLabel() + this.getText());
		    }
		    public void layout(int width, int height) {
		    	super.layout(getPreferredWidth(), getPreferredHeight());
		        setExtent(getPreferredWidth(), getPreferredHeight());
		    }
		};
		
		ButtonField registroBtn = new ButtonField("Dar alta", ButtonField.FIELD_RIGHT);
		registroBtn.setCommand(new Command(new CommandHandler() {
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				UiApplication.getUiApplication().pushScreen(new Registro());
				UiApplication.getUiApplication().popScreen(resultadosScreen);
			}
		}));
		
		Field[] headerFields = new Field[]{volverBtn, multa, registroBtn};
		return headerFields;
	}
}
