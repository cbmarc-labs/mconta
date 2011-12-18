package mconta.web.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.ListBox;

/**
 * Thanks to: http://code.google.com/p/google-web-toolkit/issues/detail?id=2893
 */
public class ObjectListBox<T extends Object> extends ListBox {
	
	private List<T> items = null;
	
	public ObjectListBox() {
		super();
	}
	
	/**
     * Adds an {@link Object} to the list.
     * 
     * @param item
     *            the name to display in the {@link ListBox}
     * @param obj
     *            the {@link Object} to associate to the location in the list
     */
    public void addItem(final String item, final T obj) {
        if(items == null) {
            items = new ArrayList<T>();
        }

        items.add(obj);
        addItem(item, item);
    }

    /**
     * Returns the {@link Object} that is stored in the given index location.
     * 
     * @param index
     *            the location in the list to retrieve
     * @return the {@link Object} associated to that location in the list
     */
    public T getItem(final int index) {
        return items.get(index);
    }

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ListBox#clear()
	 */
	@Override
	public void clear() {
		if(items != null)
			if(items.isEmpty() == false)
				items.clear();
			
		super.clear();
	}

}
