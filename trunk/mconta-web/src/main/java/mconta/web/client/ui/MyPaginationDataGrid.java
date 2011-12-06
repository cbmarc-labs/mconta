/**
 * 
 */
package mconta.web.client.ui;

import java.util.Comparator;

import mconta.core.persistence.Record;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;

/**
 * @author Marc
 * 
 * Thanks to: http://rvsoni.wordpress.com
 *
 */
public class MyPaginationDataGrid<T> extends PagingDataGrid<T>{
	
	@Override
	public void initTableColumns(DataGrid<T> dataGrid,
			ListHandler<T> sortHandler) {
		Column<T, String> firstNameColumn = new Column<T, String>(
				new TextCell()) {
			
			@Override
			public String getValue(T object) {
				return ((Record) object).getTitle();
				
			}
			
		};
		
		firstNameColumn.setSortable(true);
		sortHandler.setComparator(firstNameColumn, new Comparator<T>() {
			public int compare(T o1, T o2) {
				return ((Record) o1).getTitle().compareTo(
						((Record) o2).getTitle());
				
			}
		});
		
		dataGrid.addColumn(firstNameColumn, "First Name");
		dataGrid.setColumnWidth(firstNameColumn, 20, Unit.PCT);
	}

}
