/**
 * 
 */
package mconta.web.client.ui;

import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
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

	@SuppressWarnings("rawtypes")
	interface AppCellTableUiBinder extends UiBinder<Widget, AppCellTable> {
	}
	
	public ListDataProvider<T> dataProvider;
	public ListHandler<T> listHandler;
	
	public MultiSelectionModel<T> selectionModel;
	
	@UiField(provided = true) public CellTable<T> cellTable;
	@UiField(provided = true) public SimplePager simplePager;
	
	public AppCellTable() {
		dataProvider = new ListDataProvider<T>();
		listHandler = new ListHandler<T>(dataProvider.getList());
		
		cellTable = new CellTable<T>();
		cellTable.addColumnSortHandler(listHandler);
		
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		simplePager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		simplePager.setDisplay(cellTable);		
		
		dataProvider.addDataDisplay(cellTable);
		
		cellTable.setEmptyTableWidget(new HTML("No Data to Display"));
		
		selectionModel = new MultiSelectionModel<T>();
		cellTable.setSelectionModel(selectionModel,
		        DefaultSelectionEventManager.<T> createCheckboxManager());
		
		cellTable.setSelectionModel(selectionModel);
		
		initTableColumns();
		
		initWidget(uiBinder.createAndBindUi(this));
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
