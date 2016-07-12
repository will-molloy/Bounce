package bounce.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import javax.swing.tree.TreePath;
import bounce.ShapeModel;
import bounce.ShapeModelEvent;
import bounce.ShapeModelListener;

/**
 * Task2 is a ShapeModel -> TreeModel adapter class? (like Task1).
 * Task2 represents the model of a JTree component TreeModel.
 * It is also the view/listener of a ShapeModel. It takes ShapeModelEvents,
 * converts them into TreeModelEvents and fires them at TreeModelListeners
 * such that the TreeModel is updated in conjunction with the ShapeModel.
 * 
 * @author Will
 *
 */
public class Task2 extends Task1 implements ShapeModelListener{

	private List<TreeModelListener> _listeners = new ArrayList<TreeModelListener>();

	public Task2(ShapeModel model) {
		super(model);
	}

	// Update TreeModel by unpacking a ShapeModelEvent into a TreeModel Event and firing
	// the TreeModelEvent at registered TreeModel Listeners
	@Override
	public void update(ShapeModelEvent shapeModelEvent) {

		TreeModelEvent treeModelEvent;

		try {
			treeModelEvent = unpackShapeModelEventAndCreateTreeModelEvent(shapeModelEvent);		
		} catch (NullPointerException e) {	
			return;	// NullPointer when event.parent() is invalid 
		}			// i.e. the Shape is the root NestingShape, don't update the TreeModel

		fireTreeModelEventAtTreeModelListeners(shapeModelEvent, treeModelEvent);
	}

	private TreeModelEvent unpackShapeModelEventAndCreateTreeModelEvent(ShapeModelEvent event){
		return new TreeModelEvent(
				event.source(),						// source ShapeModel event
				new TreePath(event.parent().path().toArray()), 	// Path to root/former parent
				new int[] {event.index()},			// index of Shape added/removed 
				new Object[] {event.operand()}  	// Shape added/removed
				);	
	}
	
	private void fireTreeModelEventAtTreeModelListeners(ShapeModelEvent shapeModelEvent, TreeModelEvent treeModelEvent){
		switch(shapeModelEvent.eventType()){
		case ShapeAdded:
			fireTreeNodesInserted(treeModelEvent);
			break;
		case ShapeRemoved:
			fireTreeNodesRemoved(treeModelEvent);
			break;
		default:
			break;
		}
	}

	/**
	 * Fires a TreeModelEvent into TreeModelListeners 
	 * when a shape is added.
	 */
	private void fireTreeNodesInserted(TreeModelEvent e) {	
		for (TreeModelListener l : _listeners){
			l.treeNodesInserted(e);
		}
	}

	/**
	 * Fires a TreeModelEvent into TreeModelListeners 
	 * when a shape is removed.
	 */
	private void fireTreeNodesRemoved(TreeModelEvent e) {		
		for (TreeModelListener l : _listeners){
			l.treeNodesRemoved(e);
		}
	}
	
	/**
	 * Adds a Treemodel Listener to task2
	 */
	@Override
	public void addTreeModelListener(TreeModelListener listener) {
		_listeners.add(listener);
	}

	/**
	 * Removes a Treemodel Listener from task2
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener listener) {
		_listeners.remove(listener);
	}
}