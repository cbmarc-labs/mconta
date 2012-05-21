package mconta.web.client.app.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.ListBox;

/**
 * ObjectListBox class
 * 
 * @author marc
 *
 * @param <T>
 */
public class ObjectListBox<T> extends ListBox 
		implements LeafValueEditor<List<T>> {
	
	/**
	 * items
	 */
	private List<T> items = null;
	
	/**
	 * Constructor
	 */
	public ObjectListBox() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param isMultipleSelect
	 */
	public ObjectListBox(boolean isMultipleSelect) {
		super(isMultipleSelect);
	}
	
	/**
	 * addItem
	 * 
	 * @param item
	 * @param obj
	 */
    public void addItem(final String item, T obj) {
        if(items == null) {
            items = new ArrayList<T>();
        }
        
        
        items.add(obj);
        addItem(item, item);
    }
    
    /**
     * getItem
     * 
     * @param index
     * @return
     */
    public T getItem(final int index) {
        return items.get(index);
    }

    /**
     * clear
     */
	@Override
	public void clear() {
		if(items != null)
			if(items.isEmpty() == false)
				items.clear();
			
		super.clear();
	}
	
	/**
	 * unselecAll
	 */
	public void unselectAll() {
		for(int i = 0 ; i < items.size() ; i ++) {
				setItemSelected(i, false);
				
		}
		
	}

	/**
	 * setValue
	 * 
	 * @param values
	 */
	public void setValue(List<T> values) {
		if(values == null) {
			return;
		}
		
		unselectAll();
		
		for(T value : values) {
			for (int i = 0; i < getItemCount(); i++) {
				if (getItem(i).toString().equals(value.toString())) {
					setItemSelected(i, true);
				}
			}
			
		}
	}
	
	/**
	 * getValue
	 * 
	 * @return values
	 */
	public List<T> getValue() {
		List<T> values = new ArrayList<T>();
		
        for (int i = 0; i < getItemCount(); i++) {
            if (isItemSelected(i)) {
                values.add(getItem(i));
                
            }
        }

        return values;
	}

}
