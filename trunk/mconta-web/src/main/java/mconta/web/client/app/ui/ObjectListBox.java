package mconta.web.client.app.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.ListBox;

public class ObjectListBox<T> extends ListBox 
		implements LeafValueEditor<List<T>> {
	
	private List<T> items = null;
	
	public ObjectListBox() {
		super();
	}
	
	public ObjectListBox(boolean isMultipleSelect) {
		super(isMultipleSelect);
	}
	
    public void addItem(final String item, T obj) {
        if(items == null) {
            items = new ArrayList<T>();
        }
        
        
        items.add(obj);
        addItem(item, item);
    }
    
    public T getItem(final int index) {
        return items.get(index);
    }

	@Override
	public void clear() {
		if(items != null)
			if(items.isEmpty() == false)
				items.clear();
			
		super.clear();
	}
	
	public void unselectAll() {
		for(int i = 0 ; i < items.size() ; i ++) {
				setItemSelected(i, false);
				
		}
		
	}

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
