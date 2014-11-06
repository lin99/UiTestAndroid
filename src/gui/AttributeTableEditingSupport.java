package gui;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import com.android.uiautomator.tree.AttributePair;

class AttributeTableEditingSupport extends EditingSupport {
    private TableViewer mViewer;
    public AttributeTableEditingSupport(TableViewer viewer) {
        super(viewer);
        mViewer = viewer;
    }
    @Override
    protected boolean canEdit(Object arg0) {
        return true;
    }
    @Override
    protected CellEditor getCellEditor(Object arg0) {
        return new TextCellEditor(mViewer.getTable());
    }
    @Override
    protected Object getValue(Object o) {
        return ((AttributePair)o).value;
    }
    @Override
    protected void setValue(Object arg0, Object arg1) {
    }
}