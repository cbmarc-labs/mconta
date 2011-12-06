/**
 * 
 */
package mconta.web.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.view.client.ListDataProvider;

/**
 * @author Marc
 * 
 * Thanks to: http://rvsoni.wordpress.com
 *
 */
public abstract class PagingDataGrid<T> extends Composite {
	private DataGrid<T> dataGrid;
	private SimplePager pager;
	private String height;
	private ListDataProvider<T> dataProvider;
	private List<T> dataList = null;
	private DockPanel dock = new DockPanel();

	public PagingDataGrid() {
		initWidget(dock);
		dataGrid = new DataGrid<T>();
		dataGrid.setWidth("100%");
		dataGrid.setPageSize(10);

		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(dataGrid);
		dataProvider = new ListDataProvider<T>();
		dataProvider.setList(new ArrayList<T>());
		dataGrid.setEmptyTableWidget(new HTML("No Data to Display"));
		ListHandler<T> sortHandler = new ListHandler<T>(dataProvider.getList());

		initTableColumns(dataGrid, sortHandler);

		dataGrid.addColumnSortHandler(sortHandler);

		dataProvider.addDataDisplay(dataGrid);
		pager.setVisible(true);
		dataGrid.setVisible(true);

		dock.add(dataGrid, DockPanel.CENTER);
		dock.add(pager, DockPanel.SOUTH);
		dock.setWidth("100%");
		dock.setCellWidth(dataGrid, "100%");
		dock.setCellWidth(pager, "100%");

	}

	/**
	 * 
	 * Abstract Method to implements for adding Column into Grid
	 * 
	 * @param dataGrid
	 * @param sortHandler
	 */
	public abstract void initTableColumns(DataGrid<T> dataGrid,	
			ListHandler<T> sortHandler);

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
		dataGrid.setHeight(height);
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
		List<T> list = dataProvider.getList();
		list.clear();
		list.addAll(this.dataList);
		dataGrid.redraw();
		//dataProvider.flush();
		//dataProvider.refresh();
	}

	public ListDataProvider<T> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(ListDataProvider<T> dataProvider) {
		this.dataProvider = dataProvider;
	}

}
