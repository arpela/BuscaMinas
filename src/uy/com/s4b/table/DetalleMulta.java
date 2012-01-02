/**
 * 
 */
package uy.com.s4b.table;

import net.rim.device.api.command.Command;
import net.rim.device.api.command.CommandHandler;
import net.rim.device.api.command.ReadOnlyCommandMetadata;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
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
import net.rim.device.api.ui.decor.Background;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

/**
 * @author Pablo
 *
 */
public class DetalleMulta extends MainScreen {
	private static Screen detalleMultaScreen;
	
	private static ButtonField registroBtn;
	
	// Create and apply style
	private RegionStyles style = new RegionStyles(BorderFactory.createSimpleBorder(
			new XYEdges(0, 0, 0, 0), Border.STYLE_TRANSPARENT), null, null, null,
			RegionStyles.ALIGN_CENTER, RegionStyles.ALIGN_TOP);
	
	private TableModel _tableModel;
	
	private final static int BITMAP_LOGO 		= 0;
	private final static int SANCION_PUNTOS 	= 1;
	private final static int FECHA 				= 2;
	private final static int DNI				= 3;
	private final static int MATRICULA 			= 4;
	private final static int RECURRIDA 			= 5;

	public DetalleMulta(Object[] datosMulta, Screen callbackScreen) {
		super(Manager.NO_VERTICAL_SCROLL);
		detalleMultaScreen = this;
		setTitle("Detalle de multa");
		
		add(getHeaderGridFieldManager());

		add(getDetailsTableView(datosMulta));
		
		
		/**
		 * Posiblemente haya que ir contra el WS con los datos seleccionados....
		 * TODO confirmar
		 */
//		ButtonField darAlta = new ButtonField("Dar Alta", ButtonField.FIELD_HCENTER | ButtonField.CONSUME_CLICK);
//		darAlta.setCommand(new Command(new CommandHandler() {
//			
//			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
//				UiApplication.getUiApplication().pushScreen(new Registro());
//				UiApplication.getUiApplication().popScreen(detalleMultaScreen);
//			}
//		}));
//		add(darAlta);
		
		Bitmap bitmap_importante = Bitmap.getBitmapResource(Util.getImageByResolution("importante"));
		BitmapField importante = new BitmapField(bitmap_importante, BitmapField.FIELD_HCENTER);
		add(importante);
	}

	/**
	 * 
	 * @param datosMulta
	 * @return Retorna una tabla con los detalles de la multa
	 */
	private TableView getDetailsTableView(Object[] datosMulta) {
		_tableModel = new TableModel();
		_tableModel.addRow(getTableModel(datosMulta));

		TableView tableView = new TableView(_tableModel);
		TableController tableController = new TableController(_tableModel, tableView);
		tableController.setFocusPolicy(TableController.ROW_FOCUS);
		tableController.setCommand(new CommandHandler() {
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
//				TableView view = (TableView) context;
			}
		}, null, tableView);
		
		tableView.setController(tableController);
		
		DataTemplate dataTemplate = new DataTemplate(tableView, 5, 2) {
			public Field[] getDataFields(int modelRowIndex) {
				Object[] data = (Object[]) ((TableModel) getView().getModel()).getRow(modelRowIndex);
				
				Field[] fields = new Field[6];
				fields[BITMAP_LOGO] = new BitmapField((Bitmap) data[BITMAP_LOGO], BitmapField.VCENTER | BitmapField.HCENTER);
				fields[SANCION_PUNTOS] = new BlueRichTextField((String) data[SANCION_PUNTOS], Field.NON_FOCUSABLE| DrawStyle.HCENTER);
				fields[FECHA] = new BlueRichTextField((String) data[FECHA], Field.NON_FOCUSABLE);
				fields[DNI] = new GreyRichTextField((String) data[DNI], Field.NON_FOCUSABLE);
				fields[MATRICULA] = new GreyRichTextField((String) data[MATRICULA], Field.NON_FOCUSABLE);
				fields[RECURRIDA] = new RichTextField((String) data[RECURRIDA], Field.NON_FOCUSABLE);
				return fields;
			}
		};

		dataTemplate.createRegion(new XYRect(0, 0, 1, 5), style);
		dataTemplate.createRegion(new XYRect(1, 0, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 1, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 2, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 3, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 4, 1, 1), style);

		// Specify the size of each column by percentage, and the height of a row
		dataTemplate.setColumnProperties(0, new TemplateColumnProperties(25,TemplateColumnProperties.PERCENTAGE_WIDTH));
		dataTemplate.setColumnProperties(1, new TemplateColumnProperties(75,TemplateColumnProperties.PERCENTAGE_WIDTH));
		dataTemplate.setRowProperties(0, new TemplateRowProperties(30));
		dataTemplate.setRowProperties(1, new TemplateRowProperties(30));
		dataTemplate.setRowProperties(2, new TemplateRowProperties(30));
		dataTemplate.setRowProperties(3, new TemplateRowProperties(30));
		dataTemplate.setRowProperties(4, new TemplateRowProperties(40));

		// Apply the template to the view
		tableView.setDataTemplate(dataTemplate);
		tableView.setBorder(BorderFactory.createSimpleBorder(new XYEdges(), Border.STYLE_TRANSPARENT));
		dataTemplate.useFixedHeight(true);
		return tableView;
	}

	private Object getTableModel(Object[] datosMulta) {
		Bitmap bitmap_logo = Bitmap.getBitmapResource(Util.getImageByResolution("icono_euro_detalle_multa"));
		String importeSancion = (String) datosMulta[Resultados.IMPORTE_SANCION];
		String puntos = (String) datosMulta[Resultados.PUNTOS];
		String fechaInfraccion = (String) datosMulta[Resultados.FECHA_INFRACCION];
		String dni = (String) datosMulta[Resultados.DNI];
		String matricula = (String) datosMulta[Resultados.MATRICULA];
		String recurrible = (String) datosMulta[Resultados.RECURRIBLE];
		String msg = recurrible.equals("Recurrible") ? "para ser recurrida" : " Fuera de Plazo";
		Object[] result = new Object[]{
				bitmap_logo,
				"Sanción: " + importeSancion + ", " + puntos,
				"Fecha: " + fechaInfraccion,
				"DNI: " + dni,
				"Matrícula: " + matricula,
				"Esta multa está " + msg
						}; 
		return result;
	}
	
	private GridFieldManager getHeaderGridFieldManager(){
		Field[] headerFields = getHeaderFields();
		GridFieldManager gridFieldManager = new GridFieldManager(1, 3, GridFieldManager.FIELD_LEFT | GridFieldManager.FIELD_HCENTER | GridFieldManager.FIELD_RIGHT);
		
		for (int i = 0; i < headerFields.length; i++) {
			gridFieldManager.insert(headerFields[i], i);
		}
		Background background = BackgroundFactory.createBitmapBackground(Bitmap.getBitmapResource(Util.getImageByResolution("barra_multa")));
		gridFieldManager.setBackground(background);
		return gridFieldManager;
	}
	
	private Field[] getHeaderFields(){
		ButtonField volverBtn = new ButtonField("Volver", ButtonField.FIELD_LEFT){
			public int getPreferredWidth(){
				return 70;			}
			public void layout(int width, int height){
				super.layout(getPreferredWidth(), getPreferredHeight());
				setExtent(getPreferredWidth(), getPreferredHeight());
			}
		};
		volverBtn.setCommand(new Command(new CommandHandler() {
			
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				UiApplication.getUiApplication().popScreen(detalleMultaScreen);
			}
		}));
		RichTextField detalle = new RichTextField(){
			public int getPreferredWidth(){
				return 135;
			}
			public void layout(int width, int height){
				super.layout(getPreferredWidth(), getPreferredHeight());
				setExtent(getPreferredWidth(), getPreferredHeight());
			}
		};
		
		registroBtn = new ButtonField("Dar alta", ButtonField.FIELD_RIGHT){
			public int getPreferredWidth(){
				return 70;
			}
			public void layout(int width, int height){
				super.layout(getPreferredWidth(), getPreferredHeight());
				setExtent(getPreferredWidth(), getPreferredHeight());
			}
		};
		registroBtn.setCommand(new Command(new CommandHandler() {
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				UiApplication.getUiApplication().pushScreen(new Registro());
				UiApplication.getUiApplication().popScreen(detalleMultaScreen);
			}
		}));
		
		Field[] headerFields = new Field[]{volverBtn, detalle, registroBtn};
		return headerFields;
	}
	
	public class BlueRichTextField extends RichTextField{
		
		public BlueRichTextField(String text, long style) {
			super(text, style);
		}
		
		protected void paint(Graphics graphics) {
			graphics.setColor(Color.DODGERBLUE);
			super.paint(graphics);
			Font font = graphics.getFont().derive(Font.BOLD, 7, Ui.UNITS_pt);
			this.setFont(font);
		}
	}
	public class GreyRichTextField extends RichTextField{
		
		public GreyRichTextField(String text, long style) {
			super(text, style);
		}
		
		protected void paint(Graphics graphics) {
			graphics.setColor(Color.DIMGRAY);
			super.paint(graphics);
			Font font = graphics.getFont().derive(Font.PLAIN, 7, Ui.UNITS_pt);
			this.setFont(font);
		}
	}
}
