/**
 * 
 */
package mconta.web.client.app.ui;

import java.util.List;

import mconta.web.client.app.event.AppCellTableEvent;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

/**
 * @author Marc
 *
 */
public class AppCellTable<T> extends Composite {

	private static AppCellTableUiBinder uiBinder = GWT
			.create(AppCellTableUiBinder.class);
	
	HandlerManager handlerManager;

	@SuppressWarnings("rawtypes")
	interface AppCellTableUiBinder extends UiBinder<Widget, AppCellTable> {}
	
	public interface CellTableResource extends CellTable.Resources {
		
		public interface CellTableStyle extends CellTable.Style {};
		
		@Source({CellTable.Style.DEFAULT_CSS, "AppCellTable.css"})
		CellTableStyle cellTableStyle();
		
	}
	
	public ListDataProvider<T> dataProvider;
	public ListHandler<T> listHandler;
	
	public MultiSelectionModel<T> selectionModel;
	
	@UiField(provided = true) public CellTable<T> cellTable;
	@UiField(provided = true) public SimplePager simplePager;
	@UiField Button deleteButton;
	
	public AppCellTable() {
		handlerManager = new HandlerManager(this);
		
		dataProvider = new ListDataProvider<T>();
		listHandler = new ListHandler<T>(dataProvider.getList());
		
		cellTable = new CellTable<T>(5, (CellTableResource) GWT.create(CellTableResource.class));
		cellTable.addColumnSortHandler(listHandler);
		
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		simplePager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		simplePager.setDisplay(cellTable);		
		
		dataProvider.addDataDisplay(cellTable);
		
		cellTable.setEmptyTableWidget(new HTML("No Data to Display"));
		
		selectionModel = new MultiSelectionModel<T>();
		cellTable.setSelectionModel(selectionModel,
		        DefaultSelectionEventManager.<T> createCheckboxManager(0));
		
		cellTable.setSelectionModel(selectionModel);
		
		initTableColumns();
		
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("deleteButton")
	public void onDeleteButtonClicked(ClickEvent event) {
		handlerManager.fireEvent(AppCellTableEvent.delete());
		
	}
	
	@UiHandler("addNewButton")
	public void onAddNewButtonClicked(ClickEvent event) {
		handlerManager.fireEvent(AppCellTableEvent.addnew());
		
	}
	
	public HandlerRegistration addAppCellTableHandler(
			AppCellTableEvent.AppCellTableHandler handler) {
		return handlerManager.addHandler(AppCellTableEvent.getType(), handler);
		
	}
	
	private void initTableColumns() {		
		Column<T, Boolean> checkColumn = new Column<T, Boolean>(
		        new CheckboxCell(true, false)) {
			
		      @Override
		      public Boolean getValue(T object) {
		        return selectionModel.isSelected(object);
		        
		      }
		      
		    };
		    
	    cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
	    cellTable.setColumnWidth(checkColumn, 40, Unit.PX);
	}
	
	public void setData(List<T> data) {
		List<T> list = dataProvider.getList();
		
		selectionModel.clear();
		
		list.clear();
		list.addAll(data);
	}

}
