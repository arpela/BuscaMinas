/**
 * 
 */
package uy.com.s4b.table;

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
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.component.table.DataTemplate;
import net.rim.device.api.ui.component.table.RegionStyles;
import net.rim.device.api.ui.component.table.TableController;
import net.rim.device.api.ui.component.table.TableModel;
import net.rim.device.api.ui.component.table.TableView;
import net.rim.device.api.ui.component.table.TemplateColumnProperties;
import net.rim.device.api.ui.component.table.TemplateRowProperties;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

/**
 * @author Pablo
 *
 */
public class DetalleMulta extends MainScreen {
	private static Screen detalleMultaScreen;
	
	private TableModel _tableModel;
	
	private final static int BITMAP_LOGO 		= 0;
	private final static int SANCION_PUNTOS 	= 1;
	private final static int FECHA 				= 2;
	private final static int DNI				= 3;
	private final static int MATRICULA 			= 4;
	private final static int RECURRIDA 			= 5;
	private final static int BITMAP_BOTON 		= 6;
	private final static int BITMAP_IMPORTANTE 	= 7;
	
	public DetalleMulta(Object[] datosMulta) {
		super(Manager.NO_VERTICAL_SCROLL);
		detalleMultaScreen = this;
		ButtonField volverBtn = new ButtonField("Volver");
		volverBtn.setCommand(new Command(new CommandHandler() {
			
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				UiApplication.getUiApplication().pushScreen(new Busqueda());
				UiApplication.getUiApplication().popScreen(detalleMultaScreen);
			}
		}));
		
		ButtonField registroBtn = new ButtonField("Dar alta");
		registroBtn.setCommand(new Command(new CommandHandler() {
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				UiApplication.getUiApplication().pushScreen(new Registro());
				UiApplication.getUiApplication().popScreen(detalleMultaScreen);
			}
		}));
		add(volverBtn);
		add(new LabelField("Multa", LabelField.FIELD_HCENTER));
		add(registroBtn);
		
		setTitle("Detalle de multa");
		add(new SeparatorField());
		
		_tableModel = new TableModel();
		_tableModel.addRow(getTableModel(datosMulta));

		// Create and apply style
		RegionStyles style = new RegionStyles(BorderFactory.createSimpleBorder(
				new XYEdges(0, 0, 0, 0), Border.STYLE_TRANSPARENT), null, null, null,
				RegionStyles.ALIGN_LEFT, RegionStyles.ALIGN_TOP);
		
		
		// Create the view and controller
		TableView tableView = new TableView(_tableModel);
		TableController tableController = new TableController(_tableModel, tableView);
		// Set the controller focus policy to highlight rows
		tableController.setFocusPolicy(TableController.ROW_FOCUS);
		
		// Set the behavior of the controller when a table item is clicked
		tableController.setCommand(new CommandHandler() {
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
//				TableView view = (TableView) context;
			}
		}, null, tableView);
		
		tableView.setController(tableController);
		
		// Create a DataTemplate that suppresses the third column
		DataTemplate dataTemplate = new DataTemplate(tableView, 5, 2) {
			public Field[] getDataFields(int modelRowIndex) {
				Object[] data = (Object[]) ((TableModel) getView().getModel()).getRow(modelRowIndex);
				Field[] fields = new Field[6];
				fields[BITMAP_LOGO] = new BitmapField((Bitmap) data[BITMAP_LOGO], BitmapField.VCENTER | BitmapField.HCENTER);
				fields[SANCION_PUNTOS] = new RichTextField((String) data[SANCION_PUNTOS], Field.NON_FOCUSABLE| DrawStyle.HCENTER);
				fields[FECHA] = new RichTextField((String) data[FECHA], Field.NON_FOCUSABLE);
				fields[DNI] = new RichTextField((String) data[DNI], Field.NON_FOCUSABLE);
				fields[MATRICULA] = new RichTextField((String) data[MATRICULA], Field.NON_FOCUSABLE);
				fields[RECURRIDA] = new RichTextField((String) data[RECURRIDA], Field.NON_FOCUSABLE);
//				fields[BITMAP_BOTON] = new BitmapField((Bitmap) data[BITMAP_BOTON], BitmapField.VCENTER | BitmapField.HCENTER);
//				fields[BITMAP_IMPORTANTE] = new BitmapField((Bitmap) data[BITMAP_IMPORTANTE], BitmapField.VCENTER | BitmapField.HCENTER);
				return fields;
			}
		};
		
		// Set up regions
		dataTemplate.createRegion(new XYRect(0, 0, 1, 5), style);
		dataTemplate.createRegion(new XYRect(1, 0, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 1, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 2, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 3, 1, 1), style);
		dataTemplate.createRegion(new XYRect(1, 4, 1, 1), style);
//		dataTemplate.createRegion(new XYRect(0, 5, 2, 1), style);
//		dataTemplate.createRegion(new XYRect(0, 6, 2, 1), style);

		// Specify the size of each column by percentage, and the height of a
		// row
		dataTemplate.setColumnProperties(0, new TemplateColumnProperties(25,TemplateColumnProperties.PERCENTAGE_WIDTH));
		dataTemplate.setColumnProperties(1, new TemplateColumnProperties(75,TemplateColumnProperties.PERCENTAGE_WIDTH));
		dataTemplate.setRowProperties(0, new TemplateRowProperties(30));
		dataTemplate.setRowProperties(1, new TemplateRowProperties(30));
		dataTemplate.setRowProperties(2, new TemplateRowProperties(30));
		dataTemplate.setRowProperties(3, new TemplateRowProperties(30));
		dataTemplate.setRowProperties(4, new TemplateRowProperties(40));
//		dataTemplate.setRowProperties(5, new TemplateRowProperties(80));
//		dataTemplate.setRowProperties(6, new TemplateRowProperties(581));

		// Apply the template to the view
		tableView.setDataTemplate(dataTemplate);
		
		dataTemplate.useFixedHeight(true);

		add(tableView);
	}

	private Object getTableModel(Object[] datosMulta) {
		Bitmap bitmap_logo = Bitmap.getBitmapResource("icono_euro_detalle_multa.png");
		String importeSancion = (String) datosMulta[Resultados.IMPORTE_SANCION];
		String puntos = (String) datosMulta[Resultados.PUNTOS];
		String fechaInfraccion = (String) datosMulta[Resultados.FECHA_INFRACCION];
		String dni = (String) datosMulta[Resultados.DNI];
		String matricula = (String) datosMulta[Resultados.MATRICULA];
		String recurrible = (String) datosMulta[Resultados.RECURRIBLE];
//		Bitmap bitmap_boton = Bitmap.getBitmapResource("btn_quieres_on.png");
//		Bitmap bitmap_importante = Bitmap.getBitmapResource("fondo.png");
		
		
		Object[] result = new Object[]{
				bitmap_logo,
				"Sanción: " + importeSancion + ", " + puntos,
				"Fecha: " + fechaInfraccion,
				"DNI: " + dni,
				"Matrícula: " + matricula,
				"Esta multa está " + recurrible + " para ser recurrida"
		}; 
		return result;
	}
}
