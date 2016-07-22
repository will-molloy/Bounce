package bounce.views;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;

/**
 * Task1 is a ShapeModel -> TreeModel adapter class, it allows the
 * ShapeModel class to be compatible with the JTree component TreeModel.
 * @author Will Molloy
 */
public class Task1 implements TreeModel {

	private ShapeModel _model;	// Adaptee
		
	public Task1(ShapeModel model) {
		_model = model;
	}
	
	/**
	 * Returns the Shape within the given 'root' NestingShape given the
	 * index of the Shape within the NestingShape. If the 'root' is not a
	 * NestingShape or the child is not found within the NestingShape,
	 * returns null.
	 */
	@Override
	public Object getChild(Object root, int index) {
		if (root instanceof NestingShape && index < getChildCount(root) && index >= 0){
			return ((NestingShape) root).shapeAt(index);
		} else {
			return null;
		}
	}

	/**
	 * Returns the number of children within the Root NestingShape.
	 * If the given 'root' is not a NestingShape, returns 0.
	 */
	@Override
	public int getChildCount(Object root) {
		if (root instanceof NestingShape){
			return ((NestingShape) root).shapeCount();
		} else {
			return 0;
		}
	}

	/**
	 * Given a Root (Nesting) Shape and a second Shape:
	 * returns the index of a Shape within its NestingShape parent
	 * returns -1 if the given 'root' shape is not a NestingShape or the
	 * shape is not a child of any NestingShape.
	 */
	@Override
	public int getIndexOfChild(Object root, Object shape) {
		if (root instanceof NestingShape && shape instanceof Shape){
			return ((NestingShape) root).indexOf((Shape) shape);
		} else {
			return -1;
		}
	}

	/**
	 * Returns null if no nodes in tree, else returns root NestingShape
	 */
	@Override
	public NestingShape getRoot() {
		return _model.root();
	}

	/**
	 * Determines if a node within the tree stuctor is a leaf or not.
	 * A node is a leaf if it is a NestingShape (even if it has no children), 
	 * otherwise it is a leaf.
	 */
	@Override
	public boolean isLeaf(Object node) {
		return (node instanceof NestingShape)? false : true;
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void addTreeModelListener(TreeModelListener listener) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void removeTreeModelListener(TreeModelListener listener) {
		throw new UnsupportedOperationException();
	}

}
